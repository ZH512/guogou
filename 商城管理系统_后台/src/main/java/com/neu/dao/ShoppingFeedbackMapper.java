package com.neu.dao;

import com.neu.vo.ShoppingFeedback;

import java.util.List;

public interface ShoppingFeedbackMapper {
    List<ShoppingFeedback> selectByExample(Integer userId, String username, Integer page, Integer limit, String sort, String order);
}
