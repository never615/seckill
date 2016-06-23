package org.seckill.enums;

/**
 * 秒杀相关的状态码及描述信息
 *
 * 使用枚举 表示 常量的数据字段
 * Created by never615 on 6/23/16.
 */
public enum SeckillStateEnum {
    SUCCESS(0, "秒杀成功"),
    END(8001, "秒杀结束"),   //不在秒杀时间/库存不足/未审核/未发布
    REPEAT_KILL(8002, "重复秒杀"),
    INNER_ERROR(8003, "秒杀内部异常"),
    DATA_REWRITE(8004, "数据篡改"),
    FAIL_REDUCE_INTEGRAL(8005, "扣减用户积分失败,积分不足");

    private int state;

    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(int index) {
        for (SeckillStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
