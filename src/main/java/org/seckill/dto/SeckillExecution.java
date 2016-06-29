package org.seckill.dto;

import org.seckill.enums.SeckillStateEnum;

/**
 * 封装秒杀执行后的结果
 * Created by never615 on 6/16/16.
 */
public class SeckillExecution {


    private long seckillId;

    /**
     * 秒杀执行结果状态
     */
    private int state;

    /**
     * 状态表示
     */
    private String stateInfo;

    private SuccessKilledDto successKilledDto;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

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

    public SuccessKilledDto getSuccessKilledDto() {
        return successKilledDto;
    }

    public void setSuccessKilledDto(SuccessKilledDto successKilledDto) {
        this.successKilledDto = successKilledDto;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public SeckillExecution(long seckillId, SeckillStateEnum statEnum, SuccessKilledDto successKilledDto) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successKilledDto = successKilledDto;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilledDto=" + successKilledDto +
                '}';
    }
}
