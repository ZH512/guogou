package com.neu.dao;

import com.neu.vo.ShoppingTopic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ll
 * @Date 2019/11/19
 * @Time 14:58
 **/
@Repository
public interface ShoppingTopicMapper {
    List<ShoppingTopic> selectByExampleWithBLOBs(@Param("title") String title,
                                                 @Param("subtitle") String subtitle,
                                                 @Param("page") Integer page,
                                                 @Param("limit") Integer limit,
                                                 @Param("sort") String sort,
                                                 @Param("order") String order);

    ShoppingTopic findById(@Param("id")Integer id);

    int insertSelective(ShoppingTopic topic);

    int updateByExampleSelective(ShoppingTopic topic);

    int logicalDeleteByExample(@Param("id") Integer id);
}
