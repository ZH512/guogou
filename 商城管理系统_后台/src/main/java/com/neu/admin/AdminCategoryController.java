package com.neu.admin;

import com.neu.common.RequiresPermissionsDesc;
import com.neu.service.ShoppingCategoryService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.CategoryVo;
import com.neu.vo.ShoppingCategory;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品类目
 */
@Controller
@Api(description = "商品类目")
@RequestMapping("/admin/category")
@Validated
public class AdminCategoryController {
    @Autowired
    ShoppingCategoryService shoppingCategoryService;

    @RequiresPermissions("admin:storage:create")
    @RequiresPermissionsDesc(menu = {"系统管理","商品类目"}, button = "新增")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ShoppingCategory>> create(@RequestBody ShoppingCategory category) {
        int count =shoppingCategoryService.add(category);
        if (count>0) {
            return BaseResponse.generateOKResponseEntity("新增成功");
        }
        return BaseResponse.generateBadResponseEntity(500,"新增失败","");
    }

    @RequiresPermissions("admin:category:list")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "查询")
    @GetMapping("/list")
    public ResponseEntity list() {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        List<ShoppingCategory> categoryList = shoppingCategoryService.queryByPid(0);
        for (ShoppingCategory category : categoryList) {
            CategoryVo categoryVO = new CategoryVo();
            categoryVO.setId(category.getId());
            categoryVO.setDesc(category.getDesc());
            categoryVO.setPid(category.getPid());
            categoryVO.setIconUrl(category.getIconUrl());
            categoryVO.setPicUrl(category.getPicUrl());
            categoryVO.setKeywords(category.getKeywords());
            categoryVO.setName(category.getName());
            categoryVO.setLevel(category.getLevel());
            List<CategoryVo> children = new ArrayList<>();
            List<ShoppingCategory> subCategoryList = shoppingCategoryService.queryByPid(category.getId());
            for (ShoppingCategory subCategory : subCategoryList) {
                CategoryVo subCategoryVo = new CategoryVo();
                subCategoryVo.setId(subCategory.getId());
                subCategoryVo.setDesc(subCategory.getDesc());
                subCategoryVo.setPid(subCategory.getPid());
                subCategoryVo.setIconUrl(subCategory.getIconUrl());
                subCategoryVo.setPicUrl(subCategory.getPicUrl());
                subCategoryVo.setKeywords(subCategory.getKeywords());
                subCategoryVo.setName(subCategory.getName());
                subCategoryVo.setLevel(subCategory.getLevel());
                children.add(subCategoryVo);
            }
            categoryVO.setChildren(children);
            categoryVoList.add(categoryVO);
        }
        return BaseResponse.generateOKListResponseEntity(categoryVoList);
    }

    @RequiresPermissions("admin:category:update")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "修改")
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<ShoppingCategory>> update(@RequestBody ShoppingCategory category) {
        int count = shoppingCategoryService.updateById(category);
        if (count >0) {
            return BaseResponse.generateOKResponseEntity("修改成功");
        }
        return BaseResponse.generateBadResponseEntity(500,"修改失败","");
    }

    @RequiresPermissions("admin:category:delete")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "删除")
    @PostMapping("/delete")
    public ResponseEntity<BaseResponse<ShoppingCategory>> delete(@RequestBody ShoppingCategory category) {
        Integer id = category.getId();
        if (id == null) {
            return BaseResponse.generateBadResponseEntity(500,"id不能为空","");
        }
        shoppingCategoryService.deleteById(id);
        return BaseResponse.generateOKResponseEntity("查询成功");
    }

    @RequiresPermissions("admin:category:l1")
    @RequiresPermissionsDesc(menu = {"商场管理", "类目管理"}, button = "获取一级商品类目列表")
    @PostMapping("/l1")
    public ResponseEntity l1(@RequestBody ShoppingCategory category) {
        List<ShoppingCategory> subCategoryList = shoppingCategoryService.l1List(category);
        return BaseResponse.generateOKResponseEntity(subCategoryList);
    }
}
