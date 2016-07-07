package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.List;
import java.util.Map;

/**
 * 操作秒杀表
 * Created by never615 on 6/15/16.
 */
public interface SeckillDao {

//    /**
//     * 减库存
//     *
//     * @param seckillId
//     * @param killTime
//     * @return　如果更新行数大于1,表示更新的行数
//     */
//    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);


    /**
     * 根据ID查询秒杀对象
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);


    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") long offset, @Param("limit") long limit);

    /**
     * 根据店铺id查询响应的秒杀商品
     *
     * @param merchantId 店铺id
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAllbyMerchant(@Param("merchant_id") long merchantId, @Param("offset") long offset, @Param("limit") long limit);

    /**
     * 查询商场对应的秒杀商品
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAllbyMall(@Param("offset") long offset, @Param("limit") long limit);

    /**
     * 使用存储过程执行秒杀,效率更高
     *
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);
}
