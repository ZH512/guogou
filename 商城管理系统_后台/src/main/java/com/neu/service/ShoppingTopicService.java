package com.neu.service;

import com.neu.dao.ShoppingTopicMapper;
import com.neu.vo.ShoppingTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/19
 * @Time 14:56
 **/
@Service
public class ShoppingTopicService {
    @Autowired
    private ShoppingTopicMapper shoppingTopicMapper;
    /**
    　　* @description: 查询专题信息
    　　* @author ll
    　　* @date 2019/11/20 13:57
    　　*/
    public List<ShoppingTopic> querySelective(String title, String subtitle, Integer page, Integer limit, String sort, String order) {
        return shoppingTopicMapper.selectByExampleWithBLOBs(title,subtitle,page,limit,sort,order);
    }
    /**
    　　* @description: 查询专题详情
    　　* @author ll
    　　* @date 2019/11/20 13:55
    　　*/
    public ShoppingTopic findById(Integer id) {
        return shoppingTopicMapper.findById(id);
    }
    /**
    　　* @description: 添加专题管理
    　　* @author ll
    　　* @date 2019/11/20 13:57
    　　*/
    public int add(ShoppingTopic topic) {
        return shoppingTopicMapper.insertSelective(topic);
    }
    /**
    　　* @description: 编辑专题管理
    　　* @author ll
    　　* @date 2019/11/20 13:57
    　　*/
    public int updateById(ShoppingTopic topic) {
        return shoppingTopicMapper.updateByExampleSelective(topic);
    }
    /**
    　　* @description: 删除专题管理
    　　* @author ll
    　　* @date 2019/11/20 13:58
    　　*/
    public int deleteById(Integer id) {
        return shoppingTopicMapper.logicalDeleteByExample(id);
    }
}
