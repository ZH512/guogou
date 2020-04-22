package com.neu.dao;

import com.neu.vo.ShoppingCollect;
import com.neu.vo.ShoppingFootprint;

import java.util.List;

public interface ShoppingFootprintMapper {
    List<ShoppingFootprint> selectByExample(String limit, String page, String order, String sort, String userId, String goodsId);
}
