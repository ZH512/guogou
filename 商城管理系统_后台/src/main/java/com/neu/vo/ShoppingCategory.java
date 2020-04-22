package com.neu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ShoppingCategory {
    private Integer id;
    private String name;
    private String keywords;
    private String desc;
    private Integer pid;
    private String iconUrl;
    private String picUrl;
    private String level;
    private Boolean sortOrder;
    private Date addTime;
    private Date updateTime;
    private Boolean deleted;
    private List<ShoppingCategory> childList;
}
