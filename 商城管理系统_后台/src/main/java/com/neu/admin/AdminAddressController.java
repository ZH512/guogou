package com.neu.admin;

import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.service.ShoppingAddressService;
import com.neu.util.ResponseUtil;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingAddress;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/address")
public class AdminAddressController {
    @Autowired
    private ShoppingAddressService shoppingAddressService;

    @GetMapping("/list")
    public Object list(String userId, String name,

                       @RequestParam(defaultValue = "1") String page,

                       @RequestParam(defaultValue = "10") String limit,

                       @Sort @RequestParam(defaultValue = "add_time") String sort,

                       @Order @RequestParam(defaultValue = "desc") String order) {



try{
        List<ShoppingAddress> addressList = shoppingAddressService.querySelective(userId, name, page, limit, sort, order);
        if (addressList!=null) {
             return BaseResponse.generateOKListResponseEntity(addressList);
        }else {
        return BaseResponse.generateBadResponseEntity(500,"没有找到信息","");
        }}catch (Exception e){
    e.printStackTrace();
}
return null;
    }

}
