package com.neu.admin;

import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.domain.ShoppingAdmin;
import com.neu.service.ShoppingAdminService;
import com.neu.util.bcrypt.BCryptPasswordEncoder;
import com.neu.util.response.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/admin")
public class AdminAdminController {
    private final Log logger = LogFactory.getLog(AdminAuthController.class);
    @Autowired
    private ShoppingAdminService adminService;

    @ApiOperation(value="分页查询", notes="管理员分页查询")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShoppingAdmin>> list(String username,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit,
                               @Sort @RequestParam(defaultValue = "add_time") String sort,
                               @Order @RequestParam(defaultValue = "desc") String order) {
        List<ShoppingAdmin> adminList = adminService.querySelective(username, page, limit, sort, order);
        return BaseResponse.generateOKListResponseEntity(adminList);
    }
    @ApiOperation(value="增加管理员", notes="增加一个管理员")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ShoppingAdmin>> create(@RequestBody ShoppingAdmin admin){
        String username = admin.getUsername();
        List<ShoppingAdmin> adminList = adminService.findAdmin(username);
        if (adminList.size() > 0) {
            return BaseResponse.generateBadResponseEntity(500,"管理员已存在","");
        }
        //密码加密
        String rawPassword = admin.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        admin.setPassword(encodedPassword);
        adminService.add(admin);
        return BaseResponse.generateOKResponseEntity("新增成功");
    }
    @ApiOperation(value="编辑管理员", notes="编辑一个管理员")
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<ShoppingAdmin>> update(@RequestBody ShoppingAdmin admin){
        Integer anotherAdminId = admin.getId();
        if (anotherAdminId == null) {
            return BaseResponse.generateBadResponseEntity(500,"该管理员不存在","");
        }
        // 不允许管理员通过编辑接口修改密码
        admin.setPassword(null);
        if (adminService.updateById(admin) == 0) {
            return BaseResponse.generateBadResponseEntity(500,"编辑失败","");
        }
        return BaseResponse.generateOKResponseEntity("编辑成功");
    }
    @ApiOperation(value="删除管理员", notes="删除一个管理员")
    @PostMapping("/delete")
    public ResponseEntity<BaseResponse<ShoppingAdmin>> delete(@RequestBody ShoppingAdmin admin) {
        Integer anotherAdminId = admin.getId();
        if (anotherAdminId == null) {
            return BaseResponse.generateBadResponseEntity(500, "该管理员不存在", "");
        }
        // 管理员不能删除自身账号
        //获得当前正在执行的subject
        Subject currentUser = SecurityUtils.getSubject();
        ShoppingAdmin currentAdmin = (ShoppingAdmin) currentUser.getPrincipal();
        if (currentAdmin.getId().equals(anotherAdminId)) {
            return BaseResponse.generateBadResponseEntity(500, "管理员不能删除自身账号", "");
        }
        adminService.deleteById(anotherAdminId);
        return BaseResponse.generateOKResponseEntity("删除成功");
    }
}
