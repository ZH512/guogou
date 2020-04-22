package com.neu.dao;

import com.neu.vo.ShoppingCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品类目接口
 */
@Repository
public interface ShoppingCategoryMapper {

    /**
     * 商品类目新增
     * @param category
     */
    int insertSelective(ShoppingCategory category);

    /**
     * 商品类目查询
     * @param pid
     * @return
     */
    List<ShoppingCategory> selectByExample(Integer pid);

    /**
     * 商品类目修改
     * @param category
     * @return
     */
    int updateByPrimaryKeySelective(ShoppingCategory category);

    /**
     * 商品类目删除
     * @param id
     * @return
     */
    int logicalDeleteByPrimaryKey(Integer id);

    /**
     * 获取一级商品类目列表
     * @param category
     * @return
     */
    List<ShoppingCategory> l1List(ShoppingCategory category);
}
