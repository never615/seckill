package org.seckill.dto;

import java.util.Date;

/**
 * 成功秒杀记录表
 * Created by never615 on 6/15/16.
 */
public class SuccessKilledDto {

    /**
     * 一个秒杀seckill对应多个成功记录
     */
    private SeckillDto seckillDto;

    /**
     * 商品库存ID
     */
    private long seckillId;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 状态标识:-1:无效 0:成功
     */
    private short state;

    /**
     * 核销码
     */
    private String verificationCode;
    /**
     * 核销时间
     */
    private Date exchangeTime;
    /**
     * 秒杀时间
     */
    private Date seckillAt;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;

    public SeckillDto getSeckillDto() {
        return seckillDto;
    }

    public void setSeckillDto(SeckillDto seckillDto) {
        this.seckillDto = seckillDto;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Date getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public Date getSeckillAt() {
        return seckillAt;
    }

    public void setSeckillAt(Date seckillAt) {
        this.seckillAt = seckillAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "SuccessKilledDto{" +
                "seckillDto=" + seckillDto +
                ", seckillId=" + seckillId +
                ", userId=" + userId +
                ", state=" + state +
                ", verificationCode='" + verificationCode + '\'' +
                ", exchangeTime=" + exchangeTime +
                ", seckillAt=" + seckillAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
