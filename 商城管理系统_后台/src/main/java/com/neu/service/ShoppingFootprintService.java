package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingFootprintMapper;
import com.neu.vo.ShoppingCollect;
import com.neu.vo.ShoppingFootprint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoppingFootprintService {

    @Resource
    private ShoppingFootprintMapper shoppingFootprintMapper;
    private final Log log = LogFactory.getLog(getClass());

    public List<ShoppingFootprint> querySelective(String limit, String page, String order, String sort, String userId, String goodsId) {
        try {
            //当前显示的是第几页
            int pageIndex = Integer.parseInt(page);
            //每页显示多少条数据
            int pageSizeInt = Integer.parseInt(limit);
            PageHelper.startPage(pageIndex,pageSizeInt);
            return shoppingFootprintMapper.selectByExample(limit,page,order,sort,userId,goodsId);
        }catch (Exception e){
            log.debug("Service=======>ShoppingFootprintService=======>querySelective",e);
        }
        return null;
    }
}
