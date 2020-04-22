package com.neu.vo;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class BrandVo {
    private Integer id;
    private String name;	//品牌商名称
    private String desc;	//品牌商简介
    private String pic_url;	//品牌商页的品牌商图片
    private Integer sort_order;
    private BigDecimal floor_price;  //品牌商的商品低价，仅用于页面展示
    private LocalDateTime add_time;	//创建时间
    private LocalDateTime update_time;		//更新时间
    private Integer deleted;		//逻辑删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public BigDecimal getFloor_price() {
        return floor_price;
    }

    public void setFloor_price(BigDecimal floor_price) {
        this.floor_price = floor_price;
    }

    public LocalDateTime getAdd_time() {
        return add_time;
    }

    public void setAdd_time(LocalDateTime add_time) {
        this.add_time = add_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
