package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingHistoryMapper;
import com.neu.vo.ShoppingSearchHistory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoppingHistoryService {

    @Resource
    private ShoppingHistoryMapper shoppingHistoryMapper;
    private final Log log = LogFactory.getLog(getClass());

    public List<ShoppingSearchHistory> querySelective(String limit, String page, String order, String sort, String userId, String keyword) {
        try {
            //当前显示的是第几页
            int pageIndex = Integer.parseInt(page);
            //每页显示多少条数据
            int pageSizeInt = Integer.parseInt(limit);
            PageHelper.startPage(pageIndex, pageSizeInt);
            return shoppingHistoryMapper.selectByExample(limit, page, order, sort, userId, keyword);
        } catch (Exception e) {
            log.debug("Service=======>ShoppingHistoryService=======>querySelective", e);
        }
        return null;
    }
}