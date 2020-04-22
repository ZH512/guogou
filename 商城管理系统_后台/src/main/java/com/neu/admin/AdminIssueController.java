package com.neu.admin;

import com.neu.common.RequiresPermissionsDesc;
import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.service.ShoppingIssueService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingIssue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author ll
 * @Date 2019/11/19
 * @Time 9:19
 **/
@RestController
@Api(description = "常见问题")
@RequestMapping("/admin/issue")
public class AdminIssueController {
    @Autowired
    private ShoppingIssueService issueService;
    /**
    　　* @description: 获取通用问题信息
    　　* @author ll
    　　* @date 2019/11/19 9:27
    　　*/
    @RequiresPermissions("admin:issue:list")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题"}, button = "查询")
    @ApiOperation(value="对前台返回通用问题列表", notes="获取常见问题")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShoppingIssue>> list(String question, @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<ShoppingIssue> issueList = issueService.querySelective(question, page, limit, sort, order);
        return BaseResponse.generateOKListResponseEntity(issueList);
    }/**
    　　* @description: 新增常见问题
    　　* @author ll
    　　* @date 2019/11/19 9:42
    　　*/
    @RequiresPermissions("admin:issue:create")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题"}, button = "新增")
    @ApiOperation(value="新增常见问题", notes="新增常见问题")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ShoppingIssue>> create(@RequestBody ShoppingIssue issue) {
        int count = issueService.add(issue);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("新增成功");
        }else {
            return BaseResponse.generateBadResponseEntity(500,"新增失败","");
        }
    }
    /**
    　　* @description: 删除常见问题
    　　* @author ll
    　　* @date 2019/11/19 9:42
    　　*/
    @RequiresPermissions("admin:issue:delete")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题"}, button = "删除")
    @ApiOperation(value="删除常见问题", notes="删除常见问题")
    @PostMapping("/delete")
    public ResponseEntity<BaseResponse<ShoppingIssue>> delete(@RequestBody ShoppingIssue issue) {
        int count = issueService.deleteById(issue.getId());
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("删除成功");
        }else {
            return BaseResponse.generateBadResponseEntity(500,"删除失败","");
        }
    }
    /**
    　　* @description: 修改常见问题
    　　* @author ll
    　　* @date 2019/11/19 9:42
    　　*/
    @RequiresPermissions("admin:issue:list")
    @RequiresPermissionsDesc(menu = {"商城管理", "常见问题"}, button = "修改")
    @ApiOperation(value="修改常见问题", notes="修改常见问题")
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<ShoppingIssue>> update(@RequestBody ShoppingIssue issue) {
        if (issueService.updateById(issue) == 0) {
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }
        return BaseResponse.generateOKResponseEntity("修改成功");
    }
}
