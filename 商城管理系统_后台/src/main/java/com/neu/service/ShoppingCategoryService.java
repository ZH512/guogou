package com.neu.service;

import com.neu.dao.ShoppingCategoryMapper;
import com.neu.vo.ShoppingCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品类目
 */
@Service
@Transactional
public class ShoppingCategoryService {
    @Autowired
    ShoppingCategoryMapper shoppingCategoryMapper;

    /**
     * 商品类目新增
     * @param category
     */
    public int add(ShoppingCategory category) {
        return shoppingCategoryMapper.insertSelective(category);
    }

    /**
     * 商品类目查询
     * @param pid
     * @return
     */
    public List<ShoppingCategory> queryByPid(Integer pid) {
        return shoppingCategoryMapper.selectByExample(pid);
    }

    /**
     * 商品类目修改
     * @param category
     * @return
     */
    public int updateById(ShoppingCategory category) {
        return shoppingCategoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * 商品类目删除
     * @param id
     * @return
     */
    public void deleteById(Integer id) {
        shoppingCategoryMapper.logicalDeleteByPrimaryKey(id);
    }

    /**
     * 获取一级商品类目列表
     * @param category
     * @return
     */
    public List<ShoppingCategory> l1List(ShoppingCategory category) {
        return shoppingCategoryMapper.l1List(category);
    }
}
