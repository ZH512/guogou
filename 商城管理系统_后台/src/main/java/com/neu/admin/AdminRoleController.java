package com.neu.admin;

import com.alibaba.druid.util.StringUtils;
import com.neu.common.RequiresPermissionsDesc;
import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.domain.ShoppingAdmin;
import com.neu.domain.ShoppingPermission;
import com.neu.domain.ShoppingRole;
import com.neu.service.ShoppingAdminService;
import com.neu.service.ShoppingPermissionService;
import com.neu.service.ShoppingRoleService;
import com.neu.util.JacksonUtil;
import com.neu.util.Permission;
import com.neu.util.PermissionUtil;
import com.neu.util.response.BaseResponse;
import com.neu.vo.PermVo;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {
        @Autowired
     private ShoppingRoleService roleService;
        @Autowired
     private ShoppingAdminService adminService;
        @Autowired
     private ShoppingPermissionService permissionService;

    @ApiOperation(value="获取角色分页数据", notes="角色分页查询")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShoppingRole>> list(String username,
                                                            @RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer limit,
                                                            @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                            @Order @RequestParam(defaultValue = "desc") String order) {
        List<ShoppingRole> roleList = roleService.querySelective(username, page, limit, sort, order);
        return BaseResponse.generateOKListResponseEntity(roleList);
    }
    @ApiOperation(value="增加角色", notes="增加一个角色")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ShoppingRole>> create(@RequestBody ShoppingRole role){
        if (roleService.checkExist(role.getName())) {
            return BaseResponse.generateBadResponseEntity(500,"角色已经存在","");
        }
        roleService.add(role);
        return BaseResponse.generateOKResponseEntity("新增成功");
    }
    @ApiOperation(value="编辑角色", notes="编辑一个角色")
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<ShoppingRole>> update(@RequestBody ShoppingRole role){
        Integer id = role.getId();
        if (id == null) {
            return BaseResponse.generateOKResponseEntity("id不能为空");
        }
        roleService.updateById(role);
        return BaseResponse.generateOKResponseEntity("编辑成功");
    }
    @ApiOperation(value="删除角色", notes="删除一个角色")
    @PostMapping("/delete")
    public ResponseEntity<BaseResponse<ShoppingRole>> delete(@RequestBody ShoppingRole role){
        Integer id = role.getId();
        if (id == null) {
            return BaseResponse.generateOKResponseEntity("id不能为空");
        }
        // 如果当前角色所对应管理员仍存在，则拒绝删除角色。
        List<ShoppingAdmin> adminList = adminService.all();
        for (ShoppingAdmin admin : adminList) {
            Integer[] roleIds = admin.getRoleIds();
            for (Integer roleId : roleIds) {
                if (id.equals(roleId)) {
                    return BaseResponse.generateBadResponseEntity(500,"当前角色存在管理员，不能删除","");
                }
            }
        }
        roleService.deleteById(id);
        return BaseResponse.generateOKResponseEntity("删除成功");
    }
    @ApiOperation(value="获取角色列表", notes="获取角色列表")
    @GetMapping("/options")
    public ResponseEntity<BaseResponse<ShoppingRole>> options() {
        List<ShoppingRole> roleList = roleService.queryAll();
        List<Map<String, Object>> options = new ArrayList<>(roleList.size());
        for (ShoppingRole role : roleList) {
            Map<String, Object> option = new HashMap<>(2);
            option.put("value", role.getId());
            option.put("label", role.getName());
            options.add(option);
        }
        return BaseResponse.generateOKListResponseEntity(options);
    }

    @RequiresPermissions("admin:role:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色详情")
    @GetMapping("/read")
    //@NotNull 被注释的元素不能为null
    public ResponseEntity<BaseResponse<ShoppingRole>> read(@NotNull Integer id) {
        ShoppingRole role = roleService.findById(id);
        return BaseResponse.generateOKResponseEntity(role);
    }


    private ResponseEntity<BaseResponse<ShoppingRole>> validate(ShoppingRole role) {
        String name = role.getName();
        if (StringUtils.isEmpty(name)) {
            return BaseResponse.badArgument();
        }

        return null;
    }
    @Autowired
    private ApplicationContext context;
    private List<PermVo> systemPermissions = null;
    private Set<String> systemPermissionsString = null;

    private List<PermVo> getSystemPermissions() {
        final String basicPackage = "com.neu";
        if (systemPermissions == null) {
            List<Permission> permissions = PermissionUtil.listPermission(context, basicPackage);
            systemPermissions = PermissionUtil.listPermVo(permissions);
            systemPermissionsString = PermissionUtil.listPermissionString(permissions);
        }
        return systemPermissions;
    }
    private Set<String> getAssignedPermissions(Integer roleId) {
        // 这里需要注意的是，如果存在超级权限*，那么这里需要转化成当前所有系统权限。
        // 之所以这么做，是因为前端不能识别超级权限，所以这里需要转换一下。
        Set<String> assignedPermissions = null;
        if (permissionService.checkSuperPermission(roleId)) {
            getSystemPermissions();
            assignedPermissions = systemPermissionsString;
        } else {
            assignedPermissions = permissionService.queryByRoleId(roleId);
        }
        return assignedPermissions;
    }
    /**
     * 管理员的权限情况
     *
     * @return 系统所有权限列表和管理员已分配权限
     */
    @RequiresPermissions("admin:role:permission:get")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "权限详情")
    @GetMapping("/permissions")
    public ResponseEntity<BaseResponse<Object>> getPermissions(Integer roleId) {
        List<PermVo> systemPermissions = getSystemPermissions();
        Set<String> assignedPermissions = getAssignedPermissions(roleId);
        Map<String, Object> data = new HashMap<>();
        data.put("systemPermissions", systemPermissions);
        data.put("assignedPermissions", assignedPermissions);
        return BaseResponse.generateOKResponseEntity(data);
    }

    @PostMapping("/permissions")
    public ResponseEntity<BaseResponse<ShoppingRole>> updatePermissions(@RequestBody String body) {
        Integer roleId = JacksonUtil.parseInteger(body, "roleId");
        List<String> permissions = JacksonUtil.parseStringList(body, "permissions");
        if (roleId == null || permissions == null) {
            return BaseResponse.generateBadResponseEntity();
        }
        // 如果修改的角色是超级权限，则拒绝修改。
        if (permissionService.checkSuperPermission(roleId)) {
            return BaseResponse.generateBadResponseEntity(500,"当前角色的超级权限不能变更","");
        }
        // 先删除旧的权限，再更新新的权限
        permissionService.deleteByRoleId(roleId);
        for (String permission : permissions) {
            ShoppingPermission ShoppingPermission = new ShoppingPermission();
            ShoppingPermission.setRoleId(roleId);
            ShoppingPermission.setPermission(permission);
            permissionService.add(ShoppingPermission);
        }
        return BaseResponse.generateOKResponseEntity();
    }
}
