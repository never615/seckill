package org.seckill.service.impl;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dao.config.Config;
import org.seckill.dao.redis.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillDto;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SuccessKilledDto;
import org.seckill.dto.mapper.SeckillDtoMapper;
import org.seckill.dto.mapper.SuccessKillDtoMapper;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.service.SeckillService;
import org.seckill.utils.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理秒杀业务
 * Created by never615 on 6/15/16.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private final static Logger LOG = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired private SeckillDao seckillDao;
    @Autowired private SuccessKilledDao successKilledDao;
    @Autowired private TransactionTemplate txTemplate;
    @Autowired private RedisDao redisDao;
    @Autowired private Config config;
    @Autowired private SuccessKillDtoMapper successKillDtoMapper;
    @Autowired private SeckillDtoMapper seckillDtoMapper;

    // FIXME: 6/16/16
    //md5盐值字符串,用于混淆md5
    private final String slat = "JHJÓGJ$%^&*(wew34567eIHFv456789KBMB#$%^&*";


    @Override
    public List<SeckillDto> getSeckillList(long offset, long limit) {
        List<Seckill> seckills = seckillDao.queryAll(offset, limit);
        return seckillDtoMapper.mapperList(seckills);
    }

    @Override
    public SeckillDto getById(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        return seckillDtoMapper.mapper(seckill);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        //优化到缓存 先查缓存，然后缓存到redis.
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                redisDao.putSeckill(seckill);
            }
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();

        Date nowTime = new Date();

        //秒杀未开始
        if (nowTime.getTime() > endTime.getTime() || nowTime.getTime() < startTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        //转化特定字符串的过程,不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }


    @Override
    public SeckillExecution executeSeckillProcedure(long seckillId, long userId, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            return new SeckillExecution(seckillId, SeckillStateEnum.DATA_REWRITE);
        }


        //生成核销码
        String verificationCode = AppUtils.createVerificationCode("2", userId, seckillId);


        Date killTime = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", (int) seckillId);
        map.put("userId", (int) userId);
        map.put("seckillAt", killTime);
        map.put("verificationCode", verificationCode);
        map.put("result", -201);


        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);


        PlatformTransactionManager transactionManager = txTemplate.getTransactionManager();

        TransactionStatus status = transactionManager.getTransaction(def);

        //存储过程执行完之后result被赋值


        try {
            seckillDao.killByProcedure(map);

            //获取result
            int result = MapUtils.getInteger(map, "result", -2);

            //result -1:(秒杀失败)已经秒杀过   -2:(秒杀失败)sql异常   0:秒杀失败(不在秒杀时间或者库存不足) 1:秒杀成功
            if (result == 0) {
                //秒杀成功查询秒杀对象返回
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userId);

                transactionManager.commit(status);

                SuccessKilledDto successKilledDto = successKillDtoMapper.mapper(successKilled);


                return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilledDto);
            } else {
                return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
            }
        } catch (DuplicateKeyException e) {
            transactionManager.rollback(status);
            return new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
        } catch (Exception e) {
            System.out.println("-----------------异常:" + e);

            transactionManager.rollback(status);
            return new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
        }


    }


    /**
     * 获取MD5值
     *
     * @param seckillId
     * @return
     */
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }


    //
//    /**
//     * 使用注解控制事务的优点:
//     * 1.开发团队达成一致约定,明确标注事务方法的编程风格.
//     * 2.保证事务方法的执行时间尽可能短,不要穿插其他网络操作RPC/HTTP请求或者玻璃到事务方法外部.
//     * 3.不是所有的方法都需要事务.如一些查询的service.只有一条修改操作的service.
//     */
//    @Transactional
//    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
//        if (StringUtils.isEmpty(md5) || !md5.equals(getMD5(seckillId))) {
//            throw new SeckillException("seckill data rewrite");
//        }
//
//        //执行秒杀逻辑:1.减库存.2.记录购买行为
//        Date nowTime = new Date();
//
//        try {
//            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
//
//            if (updateCount <= 0) {
//                throw new SeckillCloseException("seckill is closed!");
//            } else {
//                //记录购买行为
//                int inserCount = successKilledDao.insertSuccessKilled(seckillId, userPhone,nowTime);
//
//                if (inserCount <= 0) {
//                    //重复秒杀
//                    throw new RepeatKillException("seckill repeated!");
//                } else {
//                    //秒杀成功
//                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
//                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
//                }
//            }
//        } catch (SeckillCloseException e1) {
//            throw e1;
//        } catch (RepeatKillException e2) {
//            throw e2;
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//            //所有的编译期异常转化为运行期异常,spring的声明式事务做rollback
//            throw new SeckillException("seckill inner error: " + e.getMessage());
//        }
//    }

}
