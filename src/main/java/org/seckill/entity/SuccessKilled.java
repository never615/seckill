package org.seckill.entity;

import java.util.Date;

/**
 * 成功秒杀记录表
 * Created by never615 on 6/15/16.
 */
public class SuccessKilled {

    /**
     * 一个秒杀seckill对应多个成功记录
     */
    private Seckill seckill;
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
    private Date createAt;
    /**
     * 更新时间
     */
    private Date updateAt;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
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

    public Date getSeckillAt() {
        return seckillAt;
    }

    public void setSeckillAt(Date seckillAt) {
        this.seckillAt = seckillAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckill=" + seckill +
                ", seckillId=" + seckillId +
                ", userId=" + userId +
                ", state=" + state +
                ", verificationCode='" + verificationCode + '\'' +
                ", exchangeTime=" + exchangeTime +
                ", seckillAt=" + seckillAt +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
