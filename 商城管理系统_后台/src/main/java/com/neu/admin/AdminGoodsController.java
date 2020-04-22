package com.neu.admin;

import com.neu.common.GoodsAllinone;
import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.domain.ShoppingGoods;
import com.neu.service.ShoppingGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * filename: GoodsController
 * date:     2019/11/19 8:57
 */
@RestController
@RequestMapping("/admin/goods")
@Validated
public class AdminGoodsController {

    @Autowired
    private ShoppingGoodsService goodsService;



    @ApiOperation(value="获取商品分类和品牌商列表", notes="用户获取商品分类和品牌商列表")
    @GetMapping("/catAndBrand")
    public Object list2() {
        return goodsService.list2();
    }

    @ApiOperation(value="获取商品信息", notes="获取商品信息")
    @GetMapping("/list")
    public Object list(String goodsSn, String name,

                       @RequestParam(defaultValue = "1") Integer page,

                       @RequestParam(defaultValue = "10") Integer limit,

                       @Sort @RequestParam(defaultValue = "add_time") String sort,

                       @Order @RequestParam(defaultValue = "desc") String order) {

        return goodsService.list(goodsSn, name, page, limit);

    }

    @ApiOperation(value= "删除商品信息", notes="删除商品信息")
    @PostMapping("/delete")
    public Object delete(@RequestBody ShoppingGoods goods) {

        return goodsService.delete(goods);

    }

    @ApiOperation(value= "商品详情", notes="商品详情")
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return goodsService.detail(id);
    }


    @ApiOperation(value= "新增商品信息", notes="新增商品信息")
    @PostMapping("/create")
    public Object create(@RequestBody GoodsAllinone goodsAllinone) {
        return goodsService.create(goodsAllinone);
    }


    @ApiOperation(value= "更新商品信息", notes="更新商品信息")
    @PostMapping("/update")
    public Object update(@RequestBody GoodsAllinone goodsAllinone) {

        return goodsService.update(goodsAllinone);

    }
}
