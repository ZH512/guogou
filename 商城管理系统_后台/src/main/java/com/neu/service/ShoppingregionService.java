package com.neu.service;

import com.neu.dao.ShoppingregionMapper;
import com.neu.vo.ShoppingRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yinjian by 2019/11/19
 * 行政区域信息 尹健
 */
@Service
@Transactional
public class ShoppingregionService {
    @Autowired
    private ShoppingregionMapper shoppingregionMapper;

    //查询行政区域信息
    public List<ShoppingRegion> queryByPid() {
        return shoppingregionMapper.selectByExample();
    }
}
