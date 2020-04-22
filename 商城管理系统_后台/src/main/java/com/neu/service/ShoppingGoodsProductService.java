package com.neu.service;

import com.neu.dao.ShoppingGoodsProductMapper;
import com.neu.domain.ShoppingGoodsSpecification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ShoppingGoodsProductService {
    @Resource
    private ShoppingGoodsProductMapper goodsProductMapper;
    public  int count() {
        ShoppingGoodsSpecification specification=new ShoppingGoodsSpecification();
        specification.andLogicalDeleted(false);
        return (int) goodsProductMapper.countByExample(specification);
    }
}
