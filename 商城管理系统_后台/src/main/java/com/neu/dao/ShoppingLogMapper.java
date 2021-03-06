package com.neu.dao;

import com.neu.domain.ShoppingLog;
import com.neu.domain.ShoppingLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 操作日志
 * @author dell
 *
 */
@Repository
public interface ShoppingLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    long countByExample(ShoppingLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int deleteByExample(ShoppingLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int insert(ShoppingLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int insertSelective(ShoppingLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    ShoppingLog selectOneByExample(ShoppingLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    ShoppingLog selectOneByExampleSelective(@Param("example") ShoppingLogExample example, @Param("selective") ShoppingLog.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    List<ShoppingLog> selectByExampleSelective(@Param("example") ShoppingLogExample example, @Param("selective") ShoppingLog.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    List<ShoppingLog> selectByExample(ShoppingLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    ShoppingLog selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") ShoppingLog.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    ShoppingLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    ShoppingLog selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ShoppingLog record, @Param("example") ShoppingLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ShoppingLog record, @Param("example") ShoppingLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ShoppingLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ShoppingLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") ShoppingLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shopping_log
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}