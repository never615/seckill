package org.seckill.dto;

import org.seckill.entity.SuccessGroupBuying;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.GroupBuyingStateEnum;
import org.seckill.enums.SeckillStateEnum;

/**
 * 封装团购执行后的结果
 * Created by never615 on 6/16/16.
 */
public class GroupBuyingExecution {


    public GroupBuyingExecution(GroupBuyingStateEnum groupBuyingStateEnum, SuccessGroupBuying successGroupBuying) {
        this.state = groupBuyingStateEnum.getState();
        this.stateInfo = groupBuyingStateEnum.getStateInfo();
        this.successGroupBuying = successGroupBuying;
    }

    public GroupBuyingExecution(GroupBuyingStateEnum groupBuyingStateEnum) {
        this.state = groupBuyingStateEnum.getState();
        this.stateInfo = groupBuyingStateEnum.getStateInfo();
    }

    /**
     * 团购执行结果状态
     */
    private int state;

    /**
     * 团购结果描述
     */
    private String stateInfo;

    /**
     * 成功团购记录
     */
    private SuccessGroupBuying successGroupBuying;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessGroupBuying getSuccessGroupBuying() {
        return successGroupBuying;
    }

    public void setSuccessGroupBuying(SuccessGroupBuying successGroupBuying) {
        this.successGroupBuying = successGroupBuying;
    }

    @Override
    public String toString() {
        return "GroupBuyingExecution{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successGroupBuying=" + successGroupBuying +
                '}';
    }
}
