package com.neu.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinjian by 2019/11/19
 * 行政区域信息 尹健
 */
public class ShoppingRegiontree {
    private Integer id;
    private Integer pid;
    private String name;
    private Integer type;
    private Integer code;
    private ShoppingRegion shoppingRegion;
    private List<ShoppingRegiontree> children = new ArrayList<>();

    public ShoppingRegion getShoppingRegion() {
        return shoppingRegion;
    }

    public void setShoppingRegion(ShoppingRegion shoppingRegion) {
        this.shoppingRegion = shoppingRegion;
    }

    public List<ShoppingRegiontree> getChildren() {
        return children;
    }

    public void setChildren(List<ShoppingRegiontree> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ShoppingRegiontree{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", code=" + code +
                ", shoppingRegion=" + shoppingRegion +
                ", children=" + children +
                '}';
    }
}
