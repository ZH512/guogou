package com.neu.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingRoleMapper;
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
public class ShoppingRoleService {
    @Resource
    private ShoppingRoleMapper roleMapper;


    public Set<String> queryByIds(Integer[] roleIds) {
        Set<String> roles = new HashSet<String>();
        if(roleIds.length == 0){
            return roles;
        }

        ShoppingRoleExample example = new ShoppingRoleExample();
        example.or().andIdIn(Arrays.asList(roleIds)).andEnabledEqualTo(true).andDeletedEqualTo(false);
        List<ShoppingRole> roleList = roleMapper.selectByExample(example);

        for(ShoppingRole role : roleList){
            roles.add(role.getName());
        }

        return roles;

    }

    public List<ShoppingRole> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        ShoppingRoleExample example = new ShoppingRoleExample();
        ShoppingRoleExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return roleMapper.selectByExample(example);
    }

    public ShoppingRole findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public void add(ShoppingRole role) {
        role.setAddTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insertSelective(role);
    }

    public void deleteById(Integer id) {
        roleMapper.logicalDeleteByPrimaryKey(id);
    }

    public void updateById(ShoppingRole role) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    public boolean checkExist(String name) {
        ShoppingRoleExample example = new ShoppingRoleExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return roleMapper.countByExample(example) != 0;
    }

    public List<ShoppingRole> queryAll() {
        ShoppingRoleExample example = new ShoppingRoleExample();
        example.or().andDeletedEqualTo(false);
        return roleMapper.selectByExample(example);
    }
}
