package com.neu.dao;

import com.neu.domain.ShoppingCoupon;
import com.neu.domain.ShoppingCouponUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/* 
 * 
 * @Author long
 * @Date 2019/11/19 
 * @Param   
 * @Return   
 */
@Mapper
public interface ShoppingCouponMapper {
    /*
     *分页查询
     * @Author long
     * @Date 2019/11/19
     * @Param []
     * @Return java.util.List<com.neu.domain.ShoppingCoupon>
     */
    List<ShoppingCoupon> selectByExample(@Param("name") String name,@Param("type") Short type,@Param("status") Short status,@Param("page") Integer page,@Param("limit") Integer limit,@Param("sort") String sort,@Param("order") String order);
/*
 * 获取优惠券详情信息
 * @Author long
 * @Date 2019/11/20
 * @Param [id]
 * @Return com.neu.domain.ShoppingCoupon
 */
    ShoppingCoupon selectByPrimaryKey(@Param("id") Integer id);
/*
 *修改优惠券信息
 * @Author long
 * @Date 2019/11/20
 * @Param [id]
 * @Return int
 */
    int updateByPrimaryKeySelective(@Param("coupon") ShoppingCoupon coupon);
/*
 * 删除优惠券
 * @Author long
 * @Date 2019/11/20
 * @Param [id]
 * @Return int
 */
    int logicalDeleteByPrimaryKey(@Param("id") Integer id);
/*
 *获取优惠券领取用户信息
 * @Author long
 * @Date 2019/11/20
 * @Param [userId, couponId, status, pageNum, limit, sort, order]
 * @Return java.util.List<com.neu.domain.ShoppingCouponUser>
 */
    List<ShoppingCouponUser> selectUserByExample(@Param("userId") Integer userId,@Param("couponId") Integer couponId, @Param("status") Short status,@Param("page") Integer page,@Param("limit") Integer limit,@Param("sort") String sort,@Param("order") String order);
/*
 * 添加优惠券
 * @Author long
 * @Date 2019/11/21
 * @Param [coupon]
 * @Return int
 */
    int insertSelective(@Param("coupon") ShoppingCoupon coupon);
//获得优惠卷列表总条数
    int getTotal(@Param("name") String name,@Param("type") Short type,@Param("status") Short status);
//获得领取优惠卷用户列表总条数
    int getUserTotal(@Param("userId") Integer userId,@Param("couponId") Integer couponId, @Param("status") Short status);
}
