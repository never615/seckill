package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * 秒杀明细操作对象
 * Created by never615 on 6/16/16.
 */
public interface SuccessKilledDao {

//    /**
//     * 插入购买明细,可过滤重复(数据库有联合主键)
//     *
//     * @param seckilledId
//     * @param userId
//     * @return
//     */
//    int insertSuccessKilled(@Param("seckilledId") long seckilledId, @Param("userId") long userId, @Param("seckillAt") Date seckillTime);


    /**
     * 根据ID查询SuccessKilled并携带秒杀产品对象实体
     *
     * @param seckilledId
     * @param userId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckilledId") long seckilledId, @Param("userId") long userId);

}
