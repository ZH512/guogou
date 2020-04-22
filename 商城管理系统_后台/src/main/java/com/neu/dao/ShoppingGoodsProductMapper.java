package com.neu.dao;

import com.neu.domain.ShoppingGoodsSpecification;

public interface ShoppingGoodsProductMapper {
    long countByExample (ShoppingGoodsSpecification example);
}
