package org.seckill.entity;

import java.util.Date;

/**
 * 秒杀商品的model
 * Created by never615 on 6/15/16.
 */
public class Seckill {

    /**
     * 秒杀商品id
     */
    private long id;
    /**
     * 店铺id
     */
    private long merchantId;
    /**
     * 秒杀商品的名字
     */
    private String name;
    /**
     * 商品logo
     */
    private String logo;
    /**
     * 商品描述
     */
    private String describe;
    /**
     * 秒杀所需要的积分
     */
    private long integral;
    /**
     * 原价积分
     */
    private long originalIntegral;
    /**
     * 是否审核通过
     */
    private boolean examine;
    /**
     * 是否发布
     */
    private boolean publish;
    /**
     * 商品的图片描述
     */
    private String images;
    /**
     * 商品的总数量
     */
    private long total;
    /**
     * 商品的剩余数量
     */
    private long remain;
    /**
     * 秒杀商品的富文本详细介绍
     */
    private String details;
    /**
     * 秒杀开始时间
     */
    private Date startTime;
    /**
     * 秒杀结束时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createdAt;

    private Date updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public long getIntegral() {
        return integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }



    public boolean isPublish() {
        return publish;
    }

    public void setPublish(boolean publish) {
        this.publish = publish;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRemain() {
        return remain;
    }

    public void setRemain(long remain) {
        this.remain = remain;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isExamine() {
        return examine;
    }

    public void setExamine(boolean examine) {
        this.examine = examine;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getOriginalIntegral() {
        return originalIntegral;
    }

    public void setOriginalIntegral(long originalIntegral) {
        this.originalIntegral = originalIntegral;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "id=" + id +
                ", merchantId=" + merchantId +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", describe='" + describe + '\'' +
                ", integral=" + integral +
                ", originalIntegral=" + originalIntegral +
                ", examine=" + examine +
                ", publish=" + publish +
                ", images='" + images + '\'' +
                ", total=" + total +
                ", remain=" + remain +
                ", details='" + details + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
