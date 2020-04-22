package com.neu.service;

import com.neu.dao.ShoppingPermissionMapper;
import com.neu.dao.ShoppingRoleMapper;
import com.neu.domain.ShoppingPermission;
import com.neu.domain.ShoppingPermissionExample;
import com.neu.domain.ShoppingRole;
import com.neu.domain.ShoppingRoleExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShoppingPermissionService {
    @Resource
    private ShoppingPermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        ShoppingPermissionExample example = new ShoppingPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<ShoppingPermission> permissionList = permissionMapper.selectByExample(example);

        for(ShoppingPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        ShoppingPermissionExample example = new ShoppingPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<ShoppingPermission> permissionList = permissionMapper.selectByExample(example);

        for(ShoppingPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        ShoppingPermissionExample example = new ShoppingPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        ShoppingPermissionExample example = new ShoppingPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(ShoppingPermission ShoppingPermission) {
        ShoppingPermission.setAddTime(LocalDateTime.now());
        ShoppingPermission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(ShoppingPermission);
    }
}
