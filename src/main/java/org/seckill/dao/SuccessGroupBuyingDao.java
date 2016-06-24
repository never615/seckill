package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessGroupBuying;

import java.util.Date;
import java.util.List;

/**
 * 团购成功明细表操作类
 * Created by never615 on 6/16/16.
 */
public interface SuccessGroupBuyingDao {


    /**
     * 插入购买明细
     *
     * @param groupBuyingId 团购商品id
     * @param userId        用户id
     * @param groupBuyingAt 团购时间
     * @return 明细记录的id
     */
    long insertSuccessGroupBuying(@Param("groupBuyingId") long groupBuyingId, @Param("userId") long userId,
                                  @Param("groupBuyingAt") Date groupBuyingAt);

    /**
     * 根据明细id查询记录
     *
     * @param id 明细id
     * @return
     */
    SuccessGroupBuying queryByIdWithGroupBuying(@Param("id") long id);

    /**
     * 根据用户id和团购商品id查询记录条数
     *
     * @param userId        用户id
     * @param groupBuyingId 团购商品id
     * @return 记录条数
     */
    long queryByUserIdAndGroupBuyingId(@Param("userId") long userId, @Param("groupBuyingId") long groupBuyingId);

    /**
     * 根据用户id查询购买记录
     *
     * @param userId 用户id
     * @return
     */
    List<SuccessGroupBuying> queryByUserIdWithGroupBuying(@Param("userId") long userId);


    // FIXME: 6/23/16 优化核销码生成逻辑

    /**
     * 更新核销码
     *
     * @param id               团购记录的id
     * @param verificationCode 核销码
     * @return
     */
    int updateVerificationCode(@Param("id") long id, @Param("verificationCode") String verificationCode);

}
