package com.neu.vo;

import java.time.LocalDateTime;

public class ShoppingSearchHistory {
    //历史ID
    private int Id;
    //用户表的用户ID
    private int userId;
    //搜索关键字
    private String keyword;
    //搜索来源，如pc、wx、app
    private String from;
    //创建时间
    private LocalDateTime addTime;
    //更新时间
    private LocalDateTime updateTime;
    //逻辑删除
    private Boolean deleted;

    public ShoppingSearchHistory() {
    }

    public ShoppingSearchHistory(int id, int userId, String keyword, String from, LocalDateTime addTime, LocalDateTime updateTime, Boolean deleted) {
        Id = id;
        this.userId = userId;
        this.keyword = keyword;
        this.from = from;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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
