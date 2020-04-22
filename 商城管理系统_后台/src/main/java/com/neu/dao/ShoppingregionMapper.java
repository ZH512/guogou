package com.neu.dao;

import com.neu.vo.ShoppingRegion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yinjian by 2019/11/19
 *  行政区域信息 尹健
 */
@Repository
public interface ShoppingregionMapper {

    //查询行政区域信息
    List<ShoppingRegion> selectByExample();

}
