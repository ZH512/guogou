package com.neu.dao;

import com.neu.domain.ShoppingAd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingAdMapper {

    List<ShoppingAd> selectByExample(ShoppingAd shopping);

    int logicalDeleteByPrimaryKey(ShoppingAd shoppingAd);

    int insertSelective(ShoppingAd shoppingAd);

    int updateByPrimaryKeySelective(ShoppingAd shoppingAd);
}
