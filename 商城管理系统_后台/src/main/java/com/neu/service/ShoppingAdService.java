package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dao.ShoppingAdMapper;
import com.neu.domain.ShoppingAd;
import com.neu.util.response.BaseResponse;
import com.neu.vo.EmptyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingAdService{
    @Autowired
    ShoppingAdMapper shoppingAdMapper;


    /**
      *
      * description: 分页查询
      * Param: [shopping]
      * return: org.springframework.http.ResponseEntity<com.neu.util.response.BaseResponse<com.neu.vo.EmptyVo>>
      * Author: zouhuailiang
      * Date: 2019/11/28 上午 8:46
      */
    public ResponseEntity<BaseResponse<EmptyVo>> querySelective(ShoppingAd shopping) {
        PageHelper.startPage(shopping.getPageNum(), shopping.getPageSize());
        List<ShoppingAd> list=shoppingAdMapper.selectByExample(shopping);
        PageInfo<ShoppingAd> pageInfo =new PageInfo<>(list);
        return BaseResponse.generateOKResponseEntity(pageInfo);
    }


    /**
      *
      * description: 删除
      * Param: [shoppingAd]
      * return: void
      * Author: zouhuailiang
      * Date: 2019/11/28 上午 8:46
      */
    public void deleteById(ShoppingAd shoppingAd) {
        int count=shoppingAdMapper.logicalDeleteByPrimaryKey(shoppingAd);
    }


    /**
      *
      * description: 添加
      * Param: [shoppingAd]
      * return: void
      * Author: zouhuailiang
      * Date: 2019/11/28 上午 8:47
     * @return
     */
    public int add(ShoppingAd shoppingAd) {
       int count= shoppingAdMapper.insertSelective(shoppingAd);
        return count;
    }

    /**
      *
      * description: 更新
      * Param: [shoppingAd]
      * return: int
      * Author: zouhuailiang
      * Date: 2019/11/28 上午 9:22
      */
    public int updateById(ShoppingAd shoppingAd) {
        int count =shoppingAdMapper.updateByPrimaryKeySelective(shoppingAd);
        return  count;
    }
}
