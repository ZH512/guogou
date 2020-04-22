package com.neu.admin;

import com.neu.service.SystemConfigService;
import com.neu.util.JacksonUtil;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingExpress;
import com.neu.vo.ShoppingMall;
import com.neu.vo.ShoppingWx;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("admin/config")
@Api(description = "配置管理模块")
@Validated
public class AdminConfigController {
    @Autowired
    SystemConfigService systemConfigService;

    @ApiOperation(value = "获取运费配置信息")
    @GetMapping("/express")
    public ResponseEntity listExpress() {
        Map<String, String> data = systemConfigService.listExpress();
        return BaseResponse.generateOKResponseEntity(data);
    }

    @ApiOperation(value = "修改运费配置信息")
    @PostMapping("/express")
    public ResponseEntity updateExpress(@RequestBody ShoppingExpress shoppingExpress) {
        Map<String, String> data = JacksonUtil.object2Map(shoppingExpress);
        int row=systemConfigService.updateConfig(data);
        if (row!=data.size()){
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }
        return BaseResponse.generateOKResponseEntity();
    }

    @ApiOperation(value = "获取商城首页配置信息")
    @GetMapping("/wx")
    public ResponseEntity listWx() {
        Map<String, String> data = systemConfigService.listWx();
        return BaseResponse.generateOKResponseEntity(data);
    }

    @ApiOperation(value = "修改商城首页配置信息")
    @PostMapping("/wx")
    public ResponseEntity updateWx(@RequestBody ShoppingWx shoppingWx) {
        Map<String, String> data = JacksonUtil.object2Map(shoppingWx);
        int row=systemConfigService.updateConfig(data);
        if (row!=data.size()){
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }
        return BaseResponse.generateOKResponseEntity();
    }

    @ApiOperation(value = "获取商场配置信息数据 ")
    @GetMapping("/mall")
    public ResponseEntity listMail() {
        Map<String, String> data = systemConfigService.listMail();
        return BaseResponse.generateOKResponseEntity(data);
    }

    @ApiOperation(value = "修改商场配置信息数据 ")
    @PostMapping("/mall")
    public ResponseEntity updateMall(@RequestBody ShoppingMall shoppingMall) {
        Map<String, String> data = JacksonUtil.object2Map(shoppingMall);
        int row=systemConfigService.updateConfig(data);
        if (row!=data.size()){
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }
        return BaseResponse.generateOKResponseEntity();
    }
//    @Autowired
//    private ShoppingSystemConfigService systemConfigService;
//
//    @RequiresPermissions("admin:config:mall:list")
//    @RequiresPermissionsDesc(menu = {"配置管理", "商场配置"}, button = "详情")
//    @GetMapping("/mall")
//    public ResponseEntity listMall(@RequestBody ShoppingSystem shoppingSystem) {
//        Map<String, String> data = systemConfigService.listMail(shoppingSystem);
//        return BaseResponse.generateOKResponseEntity(data);
//    }
//
////    @RequiresPermissions("admin:config:mall:updateConfigs")
////    @RequiresPermissionsDesc(menu={"配置管理" , "商场配置"}, button="编辑")
////    @PostMapping("/mall")
////    public ResponseEntity updateMall(@RequestParam(value ="list")  List<ShoppingSystem> list) {
////        systemConfigService.updateConfig(list);
////        return BaseResponse.generateOKResponseEntity("编辑成功");
////    }
//
//    @RequiresPermissions("admin:config:express:list")
//    @RequiresPermissionsDesc(menu = {"配置管理", "运费配置"}, button = "详情")
//    @GetMapping("/express")
//    public ResponseEntity listExpress(@RequestBody ShoppingSystem shoppingSystem) {
//        Map<String, String> data = systemConfigService.listExpress(shoppingSystem);
//        return BaseResponse.generateOKResponseEntity(data);
//    }
//
////    @RequiresPermissions("admin:config:express:updateConfigs")
////    @RequiresPermissionsDesc(menu={"配置管理" , "运费配置"}, button="编辑")
////    @PostMapping("/express")
////    public Object updateExpress(@RequestBody String body) {
////        Map<String, String> data = JacksonUtil.toMap(body);
////        systemConfigService.updateConfig(data);
////        SystemConfig.updateConfigs(data);
////        return BaseResponse.generateOKResponseEntity("编辑成功");
////    }
//
//    @RequiresPermissions("admin:config:express:list")
//    @RequiresPermissionsDesc(menu = {"配置管理", "商城首页配置"}, button = "详情")
//    @GetMapping("/wx")
//    public ResponseEntity listWx(@RequestBody ShoppingSystem shoppingSystem) {
//        Map<String, String> data = systemConfigService.listWx(shoppingSystem);
//        return BaseResponse.generateOKResponseEntity(data);
//    }
//
//    @RequiresPermissions("admin:config:express:list")
//    @RequiresPermissionsDesc(menu = {"配置管理", "商城首页配置"}, button = "详情")
//    @PostMapping("wx")
//    public ResponseEntity<BaseResponse<System>> updateWx(@RequestBody Map<String, Object> map) {
//        int flag = systemConfigService.updateConfig(map);
//        if (flag == 1) {
//            return BaseResponse.generateOKResponseEntity();
//        } else {
//            return BaseResponse.generateBadResponseEntity();
//        }
//    }
}
