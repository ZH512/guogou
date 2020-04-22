package com.neu.admin;

import com.neu.service.ShoppingCollectService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingCollect;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/collect")
@Validated
public class AdminCollectController {
    @Autowired
    private ShoppingCollectService adminCollectService;
    private final Log log = LogFactory.getLog(getClass());
    @ApiOperation(value="获取用户收藏信息分页数据", notes="获取用户收藏信息分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "每页多少条数据", defaultValue = "20", required = true, dataType = "string"),
            @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1", required = true, dataType = "string"),
            @ApiImplicitParam(name = "order", value = "排序规则", defaultValue = "desc", required = true, dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "排序字段", defaultValue = "add_time", required = true, dataType = "string"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "string"),
            @ApiImplicitParam(name = "valueId", value = "商品id", required = false, dataType = "string")
    })
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShoppingCollect>> list(String limit, String page, String order, String sort, String userId, String valueId) {
        try {
            List<ShoppingCollect> collectVoList = adminCollectService.querySelective(limit,page,order,sort,userId,valueId);
            return BaseResponse.generateOKListResponseEntity(collectVoList);
        }catch (Exception e){
            log.debug("Controller=======>AdminCollectController=======>list",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
}
