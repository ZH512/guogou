package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingFeedbackMapper;
import com.neu.vo.ShoppingFeedback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoppingFeedbackService {

    @Resource
    ShoppingFeedbackMapper shoppingFeedbackMapper;

    public List<ShoppingFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order) {
        //当前显示的是第几页
        int pageIndex = Integer.parseInt(String.valueOf(page));
        //每页显示多少条数据
        int pageSizeInt = Integer.parseInt(String.valueOf(limit));
        PageHelper.startPage(pageIndex,pageSizeInt);
        List<ShoppingFeedback> shoppingFeedbacks=shoppingFeedbackMapper.selectByExample(userId,username,page,limit,sort,order);
        return shoppingFeedbacks;
    }
}
