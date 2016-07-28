package org.seckill.utils;

/**
 * Created by never615 on 6/23/16.
 */
public class AppUtils {

    /**
     * 生成核销码
     * 核销码由三部分组成  type(1位)+userId(9位)+id(10位)
     * type:  1-抽奖;2-秒杀;3-团购;0-卡券;
     * @param type
     * @param userId
     * @param id
     * @return
     */
    public static String createVerificationCode(String type, long userId, long id) {

//        String part1 = String.format("%09d", userId);
//        String part2 = String.format("%010d", id);
        String part2 = String.format("%015d", id);
//        return type + part1 + part2;
        return type  + part2;
    }

}
