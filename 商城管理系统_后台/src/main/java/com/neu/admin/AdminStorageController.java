package com.neu.admin;

import com.neu.common.RequiresPermissionsDesc;
import com.neu.common.storage.StorageService;
import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.domain.ShoppingStorage;
import com.neu.service.ShoppingStorageService;
import com.neu.util.response.BaseResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/storage")
@Validated
public class AdminStorageController {
    private final Log logger = LogFactory.getLog(AdminStorageController.class);

    @Autowired
    private StorageService storageService;
    @Autowired
    private ShoppingStorageService shoppingStorageService;

    @RequiresPermissions("admin:storage:list")
    @RequiresPermissionsDesc(menu = {"系统管理","对象存储"}, button = "查询")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShoppingStorage>> list(String key, String name,
                                                              @RequestParam(defaultValue = "1") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer limit,
                                                              @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                              @Order @RequestParam(defaultValue = "desc") String order){
        List<ShoppingStorage> storageList = shoppingStorageService.querySelective(key,name,page,limit,sort,order);
        return BaseResponse.generateOKListResponseEntity(storageList);
    }

    @RequiresPermissions("admin:storage:create")
    @RequiresPermissionsDesc(menu = {"系统管理","对象存储"}, button = "上传")
    @PostMapping("/create")
    public ResponseEntity create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        ShoppingStorage shoppingStorage = storageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return BaseResponse.generateOKResponseEntity(shoppingStorage);
    }

    @RequiresPermissions("admin:storage:delete")
    @RequiresPermissionsDesc(menu = {"系统管理","对象存储"}, button = "删除")
    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody ShoppingStorage shoppingStorage)  {
        String key = shoppingStorage.getKey();
        if (StringUtils.isEmpty(key)){
            return BaseResponse.generateBadResponseEntity("删除失败","");
        }
        shoppingStorageService.deleteByKey(key);
        storageService.delete(key);
        return BaseResponse.generateOKResponseEntity();
    }
}
