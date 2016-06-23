package org.seckill.service;

import org.seckill.dto.GroupBuyingExecution;
import org.seckill.entity.GroupBuying;
import org.seckill.exception.GroupBuyingCloseException;
import org.seckill.exception.GroupBuyingException;
import org.seckill.exception.OutOfGroupBuyingLimitException;
import org.seckill.exception.UserIntegralNotEnoughException;

import java.util.List;


/**
 * 团购业务类
 * Created by never615 on 6/16/16.
 */
public interface GroupBuyingService {

    /**
     * 查询团购列表
     *
     * @param offset 偏移量
     * @param limit  限制
     * @return 团购商品列表
     */
    List<GroupBuying> getGroupBuyingList(long offset, long limit);

    /**
     * 查询单个团购商品
     *
     * @param id 商品id
     * @return 团购商品
     */
    GroupBuying getById(long id);

    /**
     * 执行团购操作
     *
     * @param groupBuyingId 团购商品id
     * @param userId        用户id
     * @return 团购成功业务对象
     * @throws OutOfGroupBuyingLimitException 超出团购数量限制
     * @throws GroupBuyingCloseException      团购关闭
     * @throws GroupBuyingException           团购异常
     */
    GroupBuyingExecution executeGroupBuying(long groupBuyingId, long userId) throws OutOfGroupBuyingLimitException
            , GroupBuyingCloseException, GroupBuyingException, UserIntegralNotEnoughException;
}
