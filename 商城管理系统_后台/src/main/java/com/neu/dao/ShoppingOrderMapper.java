package com.neu.dao;



import com.neu.domain.ShoppingOrder;
import com.neu.domain.ShoppingPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingOrderMapper {
    long countByExample(ShoppingPermission example);

    List<ShoppingOrder> selectByExample(@Param("userId") Integer userId, @Param("orderSn") String orderSn, @Param("orderStatus") List<Short> orderStatusArray);


    int updateAddByExample(ShoppingOrder shoppingOrder);

    List<ShoppingOrder> detail(Integer id);

    List<ShoppingOrder> detail2(Integer id);

    int updateShipByExample(ShoppingOrder ShoppingOrder);

    int updateShipExample(ShoppingOrder ShoppingOrder);
}
