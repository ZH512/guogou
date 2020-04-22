package com.neu.admin;

import com.neu.service.ShoppingHistoryService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingSearchHistory;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/history")
@Validated
public class AdminHistoryController {
    @Autowired
    private ShoppingHistoryService shoppingHistoryService;
    private final Log log = LogFactory.getLog(getClass());
    @ApiOperation(value="获取用户足迹信息数据", notes="获取用户足迹信息数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "每页多少条数据", defaultValue = "20", required = true, dataType = "string"),
            @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "1", required = true, dataType = "string"),
            @ApiImplicitParam(name = "order", value = "排序规则", defaultValue = "desc", required = true, dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "排序字段", defaultValue = "add_time", required = true, dataType = "string"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = false, dataType = "string"),
            @ApiImplicitParam(name = "keyword", value = "关键词", required = false, dataType = "string")
    })
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShoppingSearchHistory>> list(String limit, String page, String order, String sort, String userId, String keyword) {
        try {
            List<ShoppingSearchHistory> shoppingCollectList = shoppingHistoryService.querySelective(limit,page,order,sort,userId,keyword);
            return BaseResponse.generateOKListResponseEntity(shoppingCollectList);
        }catch (Exception e){
            log.debug("Controller=======>AdminHistoryController=======>list",e);
            return BaseResponse.generateBadResponseEntity(500,"服务器内部错误","");
        }
    }
}
