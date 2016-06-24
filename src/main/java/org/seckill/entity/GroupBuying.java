package org.seckill.entity;

import java.util.Date;

/**
 * 团购商品model
 * Created by never615 on 6/22/16.
 */
public class GroupBuying {

    /**
     * 团购商品id
     */
    private long id;
    /**
     * 店铺id
     */
    private long merchatId;
    /**
     * 团购商品的名字
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
     * 团购所需要的积分
     */
    private long integral;
    /**
     * 团购原本所需要的积分
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
     * 每个人限制团购的数量
     */
    private int limit;
    /**
     * 商品的总数量
     */
    private long total;
    /**
     * 商品的剩余数量
     */
    private long remain;
    /**
     * 团购开始时间
     */
    private Date startTime;
    /**
     * 团购结束时间
     */
    private Date endTime;
    /**
     * 创建时间
     */
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMerchatId() {
        return merchatId;
    }

    public void setMerchatId(long merchatId) {
        this.merchatId = merchatId;
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

    public long getOriginalIntegral() {
        return originalIntegral;
    }

    public void setOriginalIntegral(long originalIntegral) {
        this.originalIntegral = originalIntegral;
    }

    public boolean isExamine() {
        return examine;
    }

    public void setExamine(boolean examine) {
        this.examine = examine;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "GroupBuying{" +
                "id=" + id +
                ", merchatId=" + merchatId +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", describe='" + describe + '\'' +
                ", integral=" + integral +
                ", originalIntegral=" + originalIntegral +
                ", examine=" + examine +
                ", publish=" + publish +
                ", images='" + images + '\'' +
                ", limit=" + limit +
                ", total=" + total +
                ", remain=" + remain +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}