package org.seckill.enums;

/**
 * 团购相关的状态码及信息
 * Created by never615 on 6/23/16.
 */
public enum GroupBuyingStateEnum {
    SUCCESS(0, "团购成功"),
    END(7001, "团购结束"),  //不在团购时间/库存不足/没有审核通过/没有发布
    OUT_LIMIT(7002, "超出本商品的团购次数"),
    INNER_ERROR(7003, "团购内部异常"),
    FAIL_REDUCE_INTEGRAL(7005, "扣减用户积分失败,积分不足");

    private int state;

    private String stateInfo;

    GroupBuyingStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static GroupBuyingStateEnum stateOf(int index) {
        for (GroupBuyingStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
