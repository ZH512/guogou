package com.neu.dao;


import com.neu.domain.*;
import com.neu.vo.ShoppingBrand;
import com.neu.vo.ShoppingCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingGoodsMapper {

    List<ShoppingGoods> selectByGoodsSnWithName(@Param("goodsSn") String goodsSn, @Param("name") String name);

    int goodsDeleteByGoodsSn(@Param("goodsSn") String goodsSn);

    List<ShoppingBrand> selectShoppingBrands();

    List<ShoppingCategory> selectShoppingCategory();

    List<ShoppingCategory> selectShoppingCategoryByPid(@Param("pid") Integer pid);

    List<ShoppingGoods> selectGoodsById(@Param("id") Integer id);

    String selectGralleryById(@Param("id") Integer id);

    int addShoppingGoods(ShoppingGoods goods);

    List<ShoppingGoods> selectGoodsByName(@Param("goodsSn") String goodsSn, @Param("name") String name, @Param("id") Integer id);

    void addShoppingGoodsAttribute(ShoppingGoodsAttribute goodsAttributes);

    void addShoppingGoodsProduct(ShoppingGoodsProduct shoppingGoodsProduct);

    void addShoppingGoodsSpecification(ShoppingGoodsSpecification shoppingGoodsSpecification);

    int selectGoodsId(@Param("goodsSn") String goodsSn);

    void updateGoodsSn(@Param("goodsSn") String goodsSn, @Param("id") int id);

    int updateByIdSelective(ShoppingGoods shoppingGoods);

    int deleteByGoodId(@Param("id") String id);

    List<ShoppingGoodsProduct> selectGoodsProductsById(@Param("goodid") String goodid);

    List<ShoppingGoodsAttribute> selectgoodsAttributesById(@Param("goodid") String goodid);

    List<ShoppingGoodsSpecification> selectgoodsSpecificationsById(@Param("goodid") String goodid);

    String selectSpecificationsById(@Param("id") Integer id);

    List<ShoppingGoods> selectByExampleSelective(@Param("ids") Integer[] ids);

    long countByExample(ShoppingGoodsAttribute example);
}