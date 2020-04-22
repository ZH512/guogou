package com.neu.dao;

import com.neu.vo.ShoppingSearchHistory;

import java.util.List;

public interface ShoppingHistoryMapper {
    List<ShoppingSearchHistory> selectByExample(String limit, String page, String order, String sort, String userId, String keyword);
}
