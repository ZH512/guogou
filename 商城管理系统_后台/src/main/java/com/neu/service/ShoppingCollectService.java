package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingCollectMapper;
import com.neu.vo.ShoppingCollect;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoppingCollectService {

    @Resource
    private ShoppingCollectMapper shoppingCollectMapper;
    private final Log log = LogFactory.getLog(getClass());

    public List<ShoppingCollect> querySelective(String limit, String page, String order, String sort, String userId, String valueId) {
        try {
            //当前显示的是第几页
            int pageIndex = Integer.parseInt(page);
            //每页显示多少条数据
            int pageSizeInt = Integer.parseInt(limit);
            PageHelper.startPage(pageIndex,pageSizeInt);
            return shoppingCollectMapper.selectByExample(limit,page,order,sort,userId,valueId);
        }catch (Exception e){
            log.debug("Service=======>ShoppingCollectService=======>querySelective",e);
        }
        return null;
    }
}
