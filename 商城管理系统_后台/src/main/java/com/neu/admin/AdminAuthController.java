package com.neu.admin;

import com.neu.util.*;
import com.neu.util.response.BaseResponse;
import com.neu.vo.EmptyVo;
import com.neu.vo.UserVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.neu.domain.ShoppingAdmin;
import com.neu.service.LogHelper;
import com.neu.service.ShoppingAdminService;
import com.neu.service.ShoppingPermissionService;
import com.neu.service.ShoppingRoleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

import static com.neu.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

@RestController
@RequestMapping("/admin/auth")
@Validated
public class AdminAuthController {
    private final Log logger = LogFactory.getLog(AdminAuthController.class);

    @Autowired
    private ShoppingAdminService adminService;
    @Autowired
    private ShoppingRoleService roleService;
    @Autowired
    private ShoppingPermissionService permissionService;
    @Autowired
    private LogHelper logHelper;

    /*
     *  { username : value, password : value }
     */

    @ApiOperation(value="用户登录", notes="检验用户名密码是否正确")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名字", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
    })
    @PostMapping("/login")
    public ResponseEntity<UserVo> login(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            // return ResponseUtil.badArgument();
            return BaseResponse.badArgument();
        }

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            logHelper.logAuthFail("登录", "用户帐号或密码不正确");
            return BaseResponse.generateBadResponseEntity(ADMIN_INVALID_ACCOUNT,"用户帐号或密码不正确","");

        } catch (LockedAccountException lae) {
            logHelper.logAuthFail("登录", "用户帐号已锁定不可用");
            return BaseResponse.generateBadResponseEntity(ADMIN_INVALID_ACCOUNT,"用户帐号已锁定不可用","");


        } catch (AuthenticationException ae) {
            logHelper.logAuthFail("登录", "认证失败");
            return BaseResponse.generateBadResponseEntity(ADMIN_INVALID_ACCOUNT,"认证失败","");

        }

        currentUser = SecurityUtils.getSubject();
        ShoppingAdmin admin = (ShoppingAdmin) currentUser.getPrincipal();
        admin.setLastLoginIp(IpUtil.getIpAddr(request));
        admin.setLastLoginTime(LocalDateTime.now());
        adminService.updateById(admin);

        logHelper.logAuthSucceed("登录");

        // userInfo
        Map<String, Object> adminInfo = new HashMap<String, Object>();
        adminInfo.put("nickName", admin.getUsername());
        adminInfo.put("avatar", admin.getAvatar());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", currentUser.getSession().getId());
        result.put("adminInfo", adminInfo);

        return BaseResponse.generateOKResponseEntity(result);
    }

    /*
     *
     */

    @ApiOperation(value="退出登录", notes="用户推出登录")
    @RequiresAuthentication
    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<EmptyVo>> logout() {
        Subject currentUser = SecurityUtils.getSubject();

        logHelper.logAuthSucceed("退出");
        currentUser.logout();
        return BaseResponse.generateOKResponseEntity();
    }


    @ApiOperation(value="用户信息", notes="获取用户信息")
    @RequiresAuthentication
    @GetMapping("/info")
    public ResponseEntity<BaseResponse<UserVo>> info() {
        Subject currentUser = SecurityUtils.getSubject();
        ShoppingAdmin admin = (ShoppingAdmin) currentUser.getPrincipal();

        Map<String, Object> data = new HashMap<>();
        data.put("name", admin.getUsername());
        data.put("avatar", admin.getAvatar());

        Integer[] roleIds = admin.getRoleIds();
        Set<String> roles = roleService.queryByIds(roleIds);
        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
        data.put("roles", roles);
        // NOTE
        // 这里需要转换perms结构，因为对于前端而已API形式的权限更容易理解
        data.put("perms", toApi(permissions));
        return BaseResponse.generateOKResponseEntity(data);

    }

    @Autowired
    private ApplicationContext context;
    private HashMap<String, String> systemPermissionsMap = null;

    private Collection<String> toApi(Set<String> permissions) {
        if (systemPermissionsMap == null) {
            systemPermissionsMap = new HashMap<>();
            final String basicPackage = "com.neu";
            List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
            for (Permission permission : systemPermissions) {
                String perm = permission.getRequiresPermissions().value()[0];
                String api = permission.getApi();
                systemPermissionsMap.put(perm, api);
            }
        }

        Collection<String> apis = new HashSet<>();
        for (String perm : permissions) {
            String api = systemPermissionsMap.get(perm);
            apis.add(api);

            if (perm.equals("*")) {
                apis.clear();
                apis.add("*");
                return apis;
                //                return systemPermissionsMap.values();

            }
        }
        return apis;
    }

    @ApiIgnore//使用该注解忽略这个API
    @GetMapping("/401")
    public ResponseEntity page401() {
        // return ResponseUtil.unlogin();
        return BaseResponse.unlogin();
    }

    @ApiIgnore//使用该注解忽略这个API
    @GetMapping("/index")
    public ResponseEntity pageIndex() {
        // return ResponseUtil.ok();
        return BaseResponse.generateOKResponseEntity();
    }

    @ApiIgnore//使用该注解忽略这个API
    @GetMapping("/403")
    public ResponseEntity page403() {
        //return ResponseUtil.unauthz();
        return BaseResponse.unauthz();
    }
}
