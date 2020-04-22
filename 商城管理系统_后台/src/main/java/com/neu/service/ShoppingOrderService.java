package com.neu.service;

import com.neu.dao.ShoppingOrderMapper;
import com.neu.domain.ShoppingGoodsSpecification;
import com.neu.domain.ShoppingPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShoppingOrderService {
    @Resource
    private ShoppingOrderMapper orderMapper;
    public  int count() {
        ShoppingPermission permission=new ShoppingPermission();
        permission.andLogicalDeleted(false);
        return (int) orderMapper.countByExample(permission);
    }
}
