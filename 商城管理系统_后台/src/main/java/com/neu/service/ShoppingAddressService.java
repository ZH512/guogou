package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingAddressMapper;
import com.neu.vo.ShoppingAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoppingAddressService {
    @Resource
    private ShoppingAddressMapper shoppingAddressMapper;
    public List<ShoppingAddress> querySelective(String userId, String name, String page, String limit, String sort, String order) {
        try{
            int pageIndex = Integer.parseInt(page);
            int pageSizeInt =Integer.parseInt(limit);
            PageHelper.startPage(pageIndex,pageSizeInt);
            List<ShoppingAddress> userlistVoList= shoppingAddressMapper.selectByExample(userId,name,order,sort);
            return userlistVoList;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
