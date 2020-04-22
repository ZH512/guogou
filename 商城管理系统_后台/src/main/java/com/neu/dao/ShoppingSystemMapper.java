package com.neu.dao;

import com.neu.vo.ShoppingSystem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 获取商城首页配置信息数据
 * author:陈子晴
 * 2019.11.20
 */
@Repository
public interface ShoppingSystemMapper {
//    //查询商场配置信息列表
//    List<ShoppingSystem> selectByExample(ShoppingSystem shoppingSystem);
//    //查询运费配置信息
//    List<ShoppingSystem> selectByExample1(ShoppingSystem shoppingSystem);
//    //商城首页配置信息
//    List<ShoppingSystem> selectByExample2(ShoppingSystem shoppingSystem);
//    //修改当前运费配置信息
//    int updateByExampleSelective(@Param("key") String key, @Param("val") String val);
List<ShoppingSystem> selectByExample();

    int updateByExampleSelective(@Param("key_name") String key_name,@Param("key_value")String key_value);
}
