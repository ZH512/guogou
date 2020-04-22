package com.neu.dao;

import com.neu.vo.ShoppingBrand;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yinjian by 2019/11/19
 * 查询品牌商信息 尹健
 */
@Repository
public interface ShoppingBrandMapper {
    //商品制造商
    List<ShoppingBrand> selectByExample(ShoppingBrand shoppingBrand);

    //新增新增品牌商信息
    int insertselective(ShoppingBrand shoppingBrand);

    //修改品牌商信息
    int updateByPrimaryKeySelective(ShoppingBrand shoppingBrand);

    //删除品牌商信息
    int logicalDeleteByPrimaryKey(ShoppingBrand shoppingBrand);

    //获取总数
    Integer gettotal();
}
