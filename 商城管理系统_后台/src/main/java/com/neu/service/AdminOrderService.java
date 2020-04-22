package com.neu.service;


import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingOrderMapper;
import com.neu.domain.ShoppingOrder;
import com.neu.domain.Shoppingg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminOrderService {
    @Resource
    private ShoppingOrderMapper shoppingOrderMapper;
    private final Log log= LogFactory.getLog(getClass());

    public List<ShoppingOrder> selectByExample(Integer userId, String orderSn, List<Short> orderStatusArray, String page, String limit, String sort, String order1) {
        try {
            int pageIndex=Integer.parseInt(page);
            int pageSizeInt=Integer.parseInt(limit);
            PageHelper.startPage(pageIndex,pageSizeInt);
            return shoppingOrderMapper.selectByExample(userId,orderSn,orderStatusArray);

        } catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
        }
        return null;
    }


    public int updateAddByExample(ShoppingOrder shoppingOrder) {
        try {
            return shoppingOrderMapper.updateAddByExample(shoppingOrder);
        }catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
        }
        return 0;
    }


    public List<ShoppingOrder> detail1(Integer id,String page,String limit) {
        try {
            int pageIndex=Integer.parseInt(page);
            int pageSizeInt=Integer.parseInt(limit);
            PageHelper.startPage(pageIndex,pageSizeInt);
            return shoppingOrderMapper.detail(id);

        } catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
        }
        return null;
    }

    public List<ShoppingOrder> detail2(Integer id,String page,String limit) {
        try {
            int pageIndex=Integer.parseInt(page);
            int pageSizeInt=Integer.parseInt(limit);
            PageHelper.startPage(pageIndex,pageSizeInt);
            return shoppingOrderMapper.detail2(id);

        } catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
        }
        return null;
    }

    public int updateShipByExample(ShoppingOrder shoppingOrder) {
        try {
            return shoppingOrderMapper.updateShipByExample(shoppingOrder);
        }catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
        }
        return 0;
    }

    public void updateShipExample(ShoppingOrder shoppingOrder) {
        try {
            shoppingOrderMapper.updateShipExample(shoppingOrder);
        }catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
        }
        return;
    }
}


