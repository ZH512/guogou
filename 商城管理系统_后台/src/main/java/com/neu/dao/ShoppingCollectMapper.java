package com.neu.dao;

import com.neu.vo.ShoppingCollect;

import java.util.List;

public interface ShoppingCollectMapper {
    List<ShoppingCollect> selectByExample(String limit, String page, String order, String sort, String userId, String valueId);
}
