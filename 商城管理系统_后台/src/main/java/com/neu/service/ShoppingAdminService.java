package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingAdminMapper;
import com.neu.domain.ShoppingAdmin;
import com.neu.domain.ShoppingAdmin.Column;
import com.neu.domain.ShoppingAdminExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingAdminService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};
    @Resource
    private ShoppingAdminMapper adminMapper;

    public List<ShoppingAdmin> findAdmin(String username) {
        ShoppingAdminExample example = new ShoppingAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public ShoppingAdmin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public List<ShoppingAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        ShoppingAdminExample example = new ShoppingAdminExample();
        ShoppingAdminExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adminMapper.selectByExampleSelective(example, result);
    }

    public int updateById(ShoppingAdmin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(ShoppingAdmin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

    public ShoppingAdmin findById(Integer id) {
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }

    public List<ShoppingAdmin> all() {
        ShoppingAdminExample example = new ShoppingAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
