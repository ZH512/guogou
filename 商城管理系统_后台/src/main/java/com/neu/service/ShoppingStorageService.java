package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingStorageMapper;
import com.neu.domain.ShoppingStorage;
import com.neu.domain.ShoppingStorageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingStorageService {
    @Autowired
    private ShoppingStorageMapper storageMapper;

    public void deleteByKey(String key) {
        ShoppingStorageExample example = new ShoppingStorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);
    }

    public void add(ShoppingStorage storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper.insertSelective(storageInfo);
    }

    public ShoppingStorage findByKey(String key) {
        ShoppingStorageExample example = new ShoppingStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return storageMapper.selectOneByExample(example);
    }

    public int update(ShoppingStorage storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public ShoppingStorage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<ShoppingStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        ShoppingStorageExample example = new ShoppingStorageExample();
        ShoppingStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);
    }
}
