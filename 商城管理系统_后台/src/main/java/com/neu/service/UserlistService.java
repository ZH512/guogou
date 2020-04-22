package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingUserlistMapper;
import com.neu.vo.ShoppingUserList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserlistService {
    @Resource
    private ShoppingUserlistMapper shoppingUserlistMapper;
    public  List<ShoppingUserList> querySelective(String username, String mobile, String limit, String page, String order, String sort) {
        try{
            int pageIndex = Integer.parseInt(page);
            int pageSizeInt =Integer.parseInt(limit);
            PageHelper.startPage(pageIndex,pageSizeInt);
            List<ShoppingUserList> userlistVoList= shoppingUserlistMapper.selectByExampleWithBLOBs(username,mobile,order,sort);
            return userlistVoList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
