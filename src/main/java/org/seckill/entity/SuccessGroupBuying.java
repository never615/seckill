package org.seckill.entity;

import java.util.Date;

/**
 * 成功团购明细model
 * Created by never615 on 6/22/16.
 */
public class SuccessGroupBuying {

    private long id;

    private GroupBuying groupBuying;
    /**
     * 秒杀商品id
     */
    private long groupBuyingId;

    /**
     * 用户id
     */
    private long userId;
    /**
     * 状态标识:-1:无效 0:成功
     */
    private short state;
    /**
     * 秒杀时间
     */
    private Date groupBuyingAt;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 更新时间
     */
    private Date updateAt;

    /**
     * 核销码
     */
    private String verificationCode;
    /**
     * 核销时间
     */
    private Date exchangeTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GroupBuying getGroupBuying() {
        return groupBuying;
    }

    public void setGroupBuying(GroupBuying groupBuying) {
        this.groupBuying = groupBuying;
    }

    public long getGroupBuyingId() {
        return groupBuyingId;
    }

    public void setGroupBuyingId(long groupBuyingId) {
        this.groupBuyingId = groupBuyingId;
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

    public Date getGroupBuyingAt() {
        return groupBuyingAt;
    }

    public void setGroupBuyingAt(Date groupBuyingAt) {
        this.groupBuyingAt = groupBuyingAt;
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
        return "SuccessGroupBuying{" +
                "id=" + id +
                ", groupBuying=" + groupBuying +
                ", groupBuyingId=" + groupBuyingId +
                ", userId=" + userId +
                ", state=" + state +
                ", groupBuyingAt=" + groupBuyingAt +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", verificationCode='" + verificationCode + '\'' +
                ", exchangeTime=" + exchangeTime +
                '}';
    }
}
