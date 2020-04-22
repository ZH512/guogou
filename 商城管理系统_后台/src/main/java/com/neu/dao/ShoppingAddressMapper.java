package com.neu.dao;

import com.neu.vo.ShoppingAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShoppingAddressMapper {
    List<ShoppingAddress> selectByExample(@Param("userId") String userId, @Param("name") String name, @Param("order") String order, @Param("sort") String sort);
}
