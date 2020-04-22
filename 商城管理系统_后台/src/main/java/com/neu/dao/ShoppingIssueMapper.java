package com.neu.dao;

import com.neu.vo.ShoppingIssue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingIssueMapper {
    List<ShoppingIssue> selectByExample(@Param(value = "question")String question,
                                        @Param(value = "page") Integer page,
                                        @Param(value = "limit") Integer limit,
                                        @Param(value = "sort") String sort,
                                        @Param(value = "order") String order);

    int insertSelective(ShoppingIssue issue);

    int logicalDeleteByPrimaryKey(@Param(value = "id") Integer id);

    int updateByPrimaryKeySelective(ShoppingIssue issue);
}
