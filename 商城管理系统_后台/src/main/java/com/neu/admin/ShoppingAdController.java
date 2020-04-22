package com.neu.admin;

import com.neu.domain.ShoppingAd;
import com.neu.service.ShoppingAdService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.EmptyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ASUS
 * @Date: 2019/11/21 10:10
 * @Description:
 */
@RestController
@Api(description = "广告相关")
@RequestMapping("/admin/ad")
public class ShoppingAdController {


    @Autowired
    ShoppingAdService shoppingAdService;

    /**
     * description: 获取分页广告
     * Param: [shopping]
     * return: org.springframework.http.ResponseEntity<com.neu.util.response.BaseResponse<com.neu.vo.EmptyVo>>
     * Author: zouhuailiang
     * Date: 2019/11/21 上午 10:30
     */
    @ApiOperation(value = "获取广告分页数据")
    @PostMapping("/list")
    public ResponseEntity<BaseResponse<EmptyVo>> list(@RequestBody ShoppingAd shopping) {
        ResponseEntity<BaseResponse<EmptyVo>> baseResponse = shoppingAdService.querySelective(shopping);
        return baseResponse;
    }


    /**
      *
      * description: 删除
      * Param: [shoppingAd]
      * return: org.springframework.http.ResponseEntity<com.neu.util.response.BaseResponse<com.neu.vo.EmptyVo>>
      * Author: zouhuailiang
      * Date: 2019/11/21 上午 10:38
      */
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResponseEntity<BaseResponse<EmptyVo>> delete(@RequestBody ShoppingAd shoppingAd) {
        shoppingAdService.deleteById(shoppingAd);
        return BaseResponse.generateOKResponseEntity();
    }


    /**
     *
     * description: 新增
     * Param: [shoppingAd]
     * return: org.springframework.http.ResponseEntity<com.neu.util.response.BaseResponse<com.neu.vo.EmptyVo>>
     * Author: zouhuailiang
     * Date: 2019/11/21 上午 10:38
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<EmptyVo>> create(@RequestBody ShoppingAd shoppingAd) {
      int count=  shoppingAdService.add(shoppingAd);
      if (count<=0){
          return BaseResponse.generateBadResponseEntity();
      }
        return BaseResponse.generateOKResponseEntity();
    }



    /**
     *
     * description: 更新
     * Param: [shoppingAd]
     * return: org.springframework.http.ResponseEntity<com.neu.util.response.BaseResponse<com.neu.vo.EmptyVo>>
     * Author: zouhuailiang
     * Date: 2019/11/21 上午 10:38
     */
    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<EmptyVo>> update(@RequestBody ShoppingAd shoppingAd) {
        int count=  shoppingAdService.updateById(shoppingAd);
        if (count<=0){
            return BaseResponse.generateBadResponseEntity();
        }
        return BaseResponse.generateOKResponseEntity();
    }

}
