package com.neu.service;

import com.neu.dao.ShoppingBrandMapper;
import com.neu.vo.ShoppingBrand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yinjian by 2019/11/19
 * 查询品牌商信息 尹健
 */
@Service
@Transactional
public class ShoppingBrandService  {

    @Autowired
    private ShoppingBrandMapper shoppingBrandMapper;

    //商品制造商
    public List<ShoppingBrand> querySelective(ShoppingBrand shoppingBrand) {
        shoppingBrand.setPagenum((shoppingBrand.getPagenum()-1)*shoppingBrand.getPagesize());
        List<ShoppingBrand> list = shoppingBrandMapper.selectByExample(shoppingBrand);
        return list;
    }

    //新增新增品牌商信息
    public int add(ShoppingBrand shoppingBrand) {
        return shoppingBrandMapper.insertselective(shoppingBrand);
    }

    //修改品牌商信息
    public int updateById(ShoppingBrand shoppingBrand) {
        return shoppingBrandMapper.updateByPrimaryKeySelective(shoppingBrand);
    }

    //删除品牌商信息
    public int deleteById(ShoppingBrand shoppingBrand) {
        return shoppingBrandMapper.logicalDeleteByPrimaryKey(shoppingBrand);
    }

    //获取总数
    public Integer gettotal() {
        return shoppingBrandMapper.gettotal();
    }

}
