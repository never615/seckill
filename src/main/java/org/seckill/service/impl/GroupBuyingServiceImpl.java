package org.seckill.service.impl;

import org.seckill.dao.GroupBuyingDao;
import org.seckill.dao.SuccessGroupBuyingDao;
import org.seckill.dao.UserDao;
import org.seckill.dto.GroupBuyingDto;
import org.seckill.dto.GroupBuyingExecution;
import org.seckill.dto.SuccessGroupBuyingDto;
import org.seckill.dto.mapper.GroupBuyingDtoMapper;
import org.seckill.dto.mapper.SuccessGroupBuyingDtoMapper;
import org.seckill.entity.GroupBuying;
import org.seckill.entity.SuccessGroupBuying;
import org.seckill.enums.GroupBuyingStateEnum;
import org.seckill.exception.GroupBuyingCloseException;
import org.seckill.exception.GroupBuyingException;
import org.seckill.exception.OutOfGroupBuyingLimitException;
import org.seckill.exception.UserIntegralNotEnoughException;
import org.seckill.service.GroupBuyingService;
import org.seckill.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 团购业务逻辑实现类
 * Created by never615 on 6/23/16.
 */
@Service
public class GroupBuyingServiceImpl implements GroupBuyingService {

    @Autowired GroupBuyingDao groupBuyingDao;
    @Autowired SuccessGroupBuyingDao successGroupBuyingDao;
    @Autowired UserDao userDao;
    @Autowired SuccessGroupBuyingDtoMapper successGroupBuyingDtoMapper;
    @Autowired GroupBuyingDtoMapper groupBuyingDtoMapper;

    @Override
    public List<GroupBuyingDto> getGroupBuyingList(long offset, long limit) {
        return groupBuyingDtoMapper.mapperList(groupBuyingDao.queryAll(offset, limit));
    }

    @Override
    public GroupBuyingDto getById(long id) {
        return groupBuyingDtoMapper.mapper(groupBuyingDao.queryById(id));
    }

    @Override
    @Transactional
    public GroupBuyingExecution executeGroupBuying(long groupBuyingId, long userId)
            throws OutOfGroupBuyingLimitException, GroupBuyingCloseException, GroupBuyingException, UserIntegralNotEnoughException {

        //执行团购操作所需要的步骤
        //1.减少库存 2.判断是否查出团购数量,没有则插入明细 3. 减少用户积分  (事务控制)

        Date nowTime = new Date();

        //1.减少库存
        int count = groupBuyingDao.reduceNumber(groupBuyingId, nowTime);
        if (count <= 0) {
            //减少库存失败 不在时间或者库存不足
            throw new GroupBuyingCloseException("团购结束");
        }

        //2.检查团购数量限制,插入明细
        //2.1 查询当前用户已经购买了多少次
        long recordNum = successGroupBuyingDao.queryByUserIdAndGroupBuyingId(userId, groupBuyingId);

        //2.2 查询商品的每个人限制购买数量
        GroupBuying groupBuying = groupBuyingDao.queryById(groupBuyingId);

        int limit = groupBuying.getLimit();

        if (limit == 0 || limit > recordNum) {
            //还可以购买
            //插入购买明细
            long successGroupBuyingId = successGroupBuyingDao.insertSuccessGroupBuying(groupBuyingId, userId, nowTime);
            if (successGroupBuyingId <= 0) {
                //插入明细失败
                throw new GroupBuyingException("团购失败");
            } else {
                //3.减少用户积分
                count = userDao.reduceUserIntegral(userId, groupBuying.getIntegral());
                if (count <= 0) {
                    //减少用户积分失败
                    throw new UserIntegralNotEnoughException("用户积分不足");
                } else {
                    //团购成功

                    SuccessGroupBuying successGroupBuying = successGroupBuyingDao.queryByIdWithGroupBuying(successGroupBuyingId);

                    SuccessGroupBuyingDto successGroupBuyingDto = successGroupBuyingDtoMapper.mapper(successGroupBuying);

                    // FIXME: 6/23/16 type写到枚举 现在的核销码规则,其实不用生成核销码
                    //生成核销码
                    String verificationCode = AppUtils.createVerificationCode("3", userId, successGroupBuyingId);
                    count = successGroupBuyingDao.updateVerificationCode(successGroupBuyingId, verificationCode);
                    if (count <= 0) {
                        throw new GroupBuyingException("团购失败");
                    }
                    return new GroupBuyingExecution(GroupBuyingStateEnum.SUCCESS, successGroupBuyingDto);
                }
            }
        } else {
            //超出购买限制
            throw new OutOfGroupBuyingLimitException("超出出本商品的团购次数");
        }
    }
}
