package com.neu.vo;

import java.time.LocalDateTime;

public class ShoppingFootprint {
    //足迹ID
    private int Id;
    //用户表的用户ID
    private int userId;
    //浏览商品ID
    private int goodsId;
    //创建时间
    private LocalDateTime addTime;
    //更新时间
    private LocalDateTime updateTime;
    //逻辑删除
    private Boolean deleted;

    public ShoppingFootprint() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public ShoppingFootprint(int id, int userId, int goodsId, LocalDateTime addTime, LocalDateTime updateTime, Boolean deleted) {
        Id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
