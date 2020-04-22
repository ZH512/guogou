package com.neu.admin;

import com.neu.service.ShoppingGoodsProductService;
import com.neu.service.ShoppingGoodsService;
import com.neu.service.ShoppingOrderService;
import com.neu.service.ShoppingUserService;
import com.neu.util.ResponseUtil;
import com.neu.util.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页
 * author:陈子晴
 * 2019.11.19
 */
@RestController
@RequestMapping("/admin/dashboard")
@Validated
public class AdminDashbordController {

    @Autowired
    private ShoppingUserService userService;
    @Autowired
    private ShoppingGoodsService goodsService;
    @Autowired
    private ShoppingGoodsProductService productService;
    @Autowired
    private ShoppingOrderService orderService;

    @GetMapping("/info")
    public ResponseEntity info() {
        int userTotal = userService.count();
        int goodsTotal = goodsService.count();
        int productTotal = productService.count();
        int orderTotal = orderService.count();
        Map<String, Integer> data = new HashMap<>();
        data.put("userTotal", userTotal);
        data.put("goodsTotal", goodsTotal);
        data.put("productTotal", productTotal);
        data.put("orderTotal", orderTotal);
        return BaseResponse.generateOKResponseEntity(data);
    }
}
