package com.neu.dao;

import com.neu.vo.ShoppingUserList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShoppingUserlistMapper {


    List<ShoppingUserList> selectByExampleWithBLOBs(@Param("username") String username,
                                                    @Param("mobile") String mobile,
                                                    @Param("order") String order,
                                                    @Param("sort") String sort);

//limit,page,order,sort
}