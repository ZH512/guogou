package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.neu.dao.ShoppingLogMapper;
import com.neu.domain.ShoppingAd;
import com.neu.domain.ShoppingLog;
import com.neu.domain.ShoppingLogExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingLogService {
    @Resource
    private ShoppingLogMapper logMapper;

    public void deleteById(Integer id) {
        logMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(ShoppingLog log) {
        log.setAddTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        logMapper.insertSelective(log);
    }

    public List<ShoppingLog> querySelective(String name, Integer page, Integer size, String sort, String order) {
        ShoppingLogExample example = new ShoppingLogExample();
        ShoppingLogExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andAdminLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return logMapper.selectByExample(example);
    }
}
