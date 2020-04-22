package com.neu.admin;


import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.domain.ShoppingOrder;
import com.neu.domain.Shoppingg;
import com.neu.service.AdminOrderService;
import com.neu.util.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/order")
@Api(description   ="订单管理模块")
@Validated
public class AdminOrderController {
    @Autowired
    AdminOrderService adminOrderService;
    private final Log log= LogFactory.getLog(getClass());
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    @ResponseBody
    public Object list(Integer userId, String orderSn,

                       @RequestParam(required = false) List<Short> orderStatusArray,

                       @RequestParam(defaultValue = "1") String page,

                       @RequestParam(defaultValue = "10") String limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,

                       @Order @RequestParam(defaultValue = "desc") String order) {

        try {
            List<ShoppingOrder> shoppingOrderList=adminOrderService.selectByExample(userId,orderSn,orderStatusArray,page,limit,sort,order);
            if (shoppingOrderList != null){
                return BaseResponse.generateOKListResponseEntity(shoppingOrderList);
            }
            return BaseResponse.generateBadResponseEntity(500,"查询失败","");

        }catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }

    }
    @ApiOperation(value = "订单退款")
    @ResponseBody
    @PostMapping("/refund")

    public Object refund(@RequestBody ShoppingOrder shoppingOrder) {

        try {
            int flag=adminOrderService.updateAddByExample(shoppingOrder);
            if (flag == 1){
                Map<String,Object> obj= new HashMap<>();
                obj.put("Success","退单成功");
                return BaseResponse.generateOKResponseEntity(obj);
            }
            else {
                return BaseResponse.generateBadResponseEntity(500,"退单失败","");
            }
        }catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    @ApiOperation(value = "id信息列表")
    @GetMapping("/detail")
    @ResponseBody
    public Object detail(Integer id, @RequestParam(defaultValue = "1") String page,

                       @RequestParam(defaultValue = "10") String limit) {

        try {
            List<ShoppingOrder> shoppingOrderList=adminOrderService.detail1(id,page,limit);
            if (shoppingOrderList != null){
                return BaseResponse.generateOKListResponseEntity(shoppingOrderList);
            }
            return BaseResponse.generateBadResponseEntity(500,"查询失败","");
        }catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }

    }
    @ApiOperation(value = "id商品列表")
    @GetMapping("/detai2")
    @ResponseBody
    public Object detai2(Integer id, @RequestParam(defaultValue = "1") String page,

                       @RequestParam(defaultValue = "10") String limit) {

        try {
            List<ShoppingOrder> shoppingOrderList=adminOrderService.detail2(id,page,limit);
            if (shoppingOrderList != null){
                return BaseResponse.generateOKListResponseEntity(shoppingOrderList);
            }
            return BaseResponse.generateBadResponseEntity(500,"查询失败","");
        }catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }

    }
    @ApiOperation(value = "商家发货")
    @ResponseBody
    @PostMapping("/ship")

    public Object ship(@RequestBody ShoppingOrder shoppingOrder, Shoppingg shoppingg) {


        try {
            int flag=adminOrderService.updateShipByExample(shoppingOrder);
            if (flag == 1){
                Map<String,Object> obj= new HashMap<>();
                obj.put("Success","发货成功");
                adminOrderService.updateShipExample(shoppingOrder);
                return BaseResponse.generateOKResponseEntity(obj);
            }
            else {
                return BaseResponse.generateBadResponseEntity(500,"发货失败","");
            }
        }catch (Exception e){
            log.debug("UserServiceImpl-->>getUserByName",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
    }

