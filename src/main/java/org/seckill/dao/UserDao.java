package org.seckill.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 用户操作表
 * Created by never615 on 6/15/16.
 */
public interface UserDao {


    /**
     * 根据用户id查询用户是否存在
     *
     * @param userId
     * @return
     */
    long isExistUser(long userId);

    /**
     * 减少用户积分
     * @param userId 用户id
     * @param integral 要减少的积分
     * @return
     */
    int reduceUserIntegral(@Param("userId") long userId,@Param("integral") long integral);

}
