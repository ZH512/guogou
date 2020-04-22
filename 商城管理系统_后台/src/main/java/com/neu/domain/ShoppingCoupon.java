package com.neu.domain;

import org.checkerframework.checker.units.qual.min;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShoppingCoupon {
    private LocalDateTime addTime;//创建时间
    private String code;//优惠券兑换码
    private int days;//基于领取时间的有效天数days。
    private boolean deleted;//逻辑删除
    private String desc;//惠券介绍
    private BigDecimal discount;//优惠金额，
    private LocalDateTime endTime;//使用券截至时间
    private int goodsType;//商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。
    private String goodsValue;//商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。
    private Integer id;
    private int limit;//用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.
    private BigDecimal min;//最少消费金额才能使用优惠券。
    private String name;//优惠券名称
    private LocalDateTime startTime;//使用券开始时间
    private int status;//优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。
    private String tag;//优惠券标签，例如新人专用
    private int timeType;//有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；
    private int total;//优惠券数量，如果是0，则是无限量
    private int type;//优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；
    private LocalDateTime updateTime;//更新时间

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
