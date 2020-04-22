package com.neu.vo;

import java.time.LocalDateTime;

public class ShoppingCollect {
    //收藏ID
    private int Id;
    //用户表的用户ID
    private int userId;
    //如果type=0，则是商品ID；如果type=1，则是专题ID
    private int valueId;
    //收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
    private int type;
    //创建时间
    private LocalDateTime addTime;
    //更新时间
    private LocalDateTime updateTime;
    //逻辑删除
    private Boolean deleted;

    public ShoppingCollect() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public ShoppingCollect(int id, int userId, int valueId, int type, LocalDateTime addTime, LocalDateTime updateTime, Boolean deleted) {
        Id = id;
        this.userId = userId;
        this.valueId = valueId;
        this.type = type;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }
}
