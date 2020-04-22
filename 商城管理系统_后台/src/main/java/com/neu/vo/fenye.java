package com.neu.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by yinjian by 2019/11/19
 * 分页 尹健
 */

public class fenye {
    private Integer pagenum;
    private Integer pagesize;
    private Integer total;
    private List<?> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

}
