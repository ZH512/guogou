package com.neu.service;

import com.neu.dao.ShoppingIssueMapper;
import com.neu.vo.ShoppingIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/19
 * @Time 9:22
 **/
@Service
@Transactional
public class ShoppingIssueService {
    @Autowired
    private ShoppingIssueMapper issueMapper;
    /**
    　　* @description: 获取常见问题
    　　* @author ll
    　　* @date 2019/11/19 9:43
    　　*/
    public List<ShoppingIssue> querySelective(String question, Integer page, Integer limit, String sort, String order) {
        List<ShoppingIssue> list = issueMapper.selectByExample(question,page,limit,sort,order);
        return list;
    }
    /**
    　　* @description: 新增常见问题
    　　* @author ll
    　　* @date 2019/11/19 9:43
    　　*/
    public int add(ShoppingIssue issue) {
        return issueMapper.insertSelective(issue);
    }
    /**
    　　* @description: 删除常见问题
    　　* @author ll
    　　* @date 2019/11/19 9:43
    　　*/
    public int deleteById(Integer id) {
        return issueMapper.logicalDeleteByPrimaryKey(id);
    }
    /**
    　　* @description: 修改常见问题
    　　* @author ll
    　　* @date 2019/11/19 9:43
    　　*/
    public int updateById(ShoppingIssue issue) {
        return issueMapper.updateByPrimaryKeySelective(issue);
    }
}
