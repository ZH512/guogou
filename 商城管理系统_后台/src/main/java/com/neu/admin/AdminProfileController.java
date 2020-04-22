package com.neu.admin;

import com.neu.util.response.BaseResponse;
import com.neu.vo.EmptyVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neu.domain.ShoppingAdmin;
import com.neu.service.ShoppingAdminService;
import com.neu.util.JacksonUtil;
import com.neu.util.ResponseUtil;
import com.neu.util.bcrypt.BCryptPasswordEncoder;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import static com.neu.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

@RestController
@RequestMapping("/admin/profile")
@Validated
public class AdminProfileController {
    private final Log logger = LogFactory.getLog(AdminProfileController.class);

    @Autowired
    private ShoppingAdminService adminService;

    
    @ApiOperation(value="修改密码", notes="用户修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "原密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    @RequiresAuthentication
    @PostMapping("/password")
    public ResponseEntity<BaseResponse<EmptyVo>> create(@RequestBody String body) {
        String oldPassword = JacksonUtil.parseString(body, "oldPassword");
        String newPassword = JacksonUtil.parseString(body, "newPassword");
        if (StringUtils.isEmpty(oldPassword)) {
            return BaseResponse.badArgument();
        }
        if (StringUtils.isEmpty(newPassword)) {
            return BaseResponse.badArgument();
        }

        Subject currentUser = SecurityUtils.getSubject();
        ShoppingAdmin admin = (ShoppingAdmin) currentUser.getPrincipal();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(oldPassword, admin.getPassword())) {
            return BaseResponse.generateBadResponseEntity(ADMIN_INVALID_ACCOUNT,"账号密码不正确","");
        }

        String encodedNewPassword = encoder.encode(newPassword);
        admin.setPassword(encodedNewPassword);

        adminService.updateById(admin);
        return BaseResponse.generateOKResponseEntity();
    }

}
