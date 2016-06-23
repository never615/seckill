package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.GroupBuying;

import java.util.Date;
import java.util.List;

/**
 * 操作团购商品表
 * Created by never615 on 6/15/16.
 */
public interface GroupBuyingDao {


    /**
     * 减少库存
     *
     * @param groupBuyingId 团购商品id
     * @param buyingTime    购买时间
     * @return
     */
    int reduceNumber(@Param("groupBuyingId") long groupBuyingId, @Param("buyingTime") Date buyingTime);

    /**
     * 根据id查询团购商品
     *
     * @param groupBuyingId 团购商品id
     * @return 团购商品
     */
    GroupBuying queryById(long groupBuyingId);

    /**
     * 查询所有团购商品
     *
     * @param offset 查询偏移量
     * @param limit  查询数量限制
     * @return
     */
    List<GroupBuying> queryAll(@Param("offset") long offset, @Param("limit") long limit);
}
