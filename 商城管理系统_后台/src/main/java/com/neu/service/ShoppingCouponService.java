package com.neu.service;

import com.neu.dao.ShoppingCouponMapper;
import com.neu.domain.ShoppingCoupon;
import com.neu.domain.ShoppingCouponUser;
import com.neu.util.CharUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *
 * @Author long
 * @Date 2019/11/19
 * @Param
 * @Return
 */
@Service
public class ShoppingCouponService {
    @Autowired
    private ShoppingCouponMapper couponMapper;
    /*
     *分页查询
     * @Author long
     * @Date 2019/11/19
     * @Param [name, type, status, page, limit, sort, order]
     * @Return java.util.List<com.neu.domain.ShoppingCoupon>
     */
    public List<ShoppingCoupon> querySelective(String name, Short type, Short status, Integer page, Integer limit, String sort, String order) {
        int pageNum=(page-1)*limit;
        List<ShoppingCoupon> list=couponMapper.selectByExample(name,type,status,pageNum,limit,sort,order);
        return list;
    }
/*
 * 获取优惠券详情信息
 * @Author long
 * @Date 2019/11/20
 * @Param [id]
 * @Return com.neu.domain.ShoppingCoupon
 */
    public ShoppingCoupon findById(Integer id) {
        return couponMapper.selectByPrimaryKey(id);
    }
/*
 *修改优惠券信息
 * @Author long
 * @Date 2019/11/20
 * @Param [coupon]
 * @Return int
 */
    public int updateById(ShoppingCoupon coupon) {
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }
/*
 * 删除优惠券
 * @Author long
 * @Date 2019/11/20
 * @Param [id]
 * @Return void
 */
    public int deleteById(Integer id) {
        return couponMapper.logicalDeleteByPrimaryKey(id);
    }
/*
 *获取优惠券领取用户信息
 * @Author long
 * @Date 2019/11/20
 * @Param [userId, couponId, status, page, limit, sort, order]
 * @Return java.util.List<com.neu.domain.ShoppingCouponUser>
 */
    public List<ShoppingCouponUser> queryList(Integer userId, Integer couponId, Short status, Integer page, Integer limit, String sort, String order) {
        int pageNum=(page-1)*limit;
        List<ShoppingCouponUser> list=couponMapper.selectUserByExample(userId,couponId,status,pageNum,limit,sort,order);
        return list;
    }
/*
 * 生成一个兑换码
 * @Author long
 * @Date 2019/12/5
 * @Param []
 * @Return java.lang.String
 */
    public String generateCode() {
        String code= CharUtil.getRandomString(8);
        return code;
    }
/*
 * 添加优惠劵
 * @Author long
 * @Date 2019/12/5
 * @Param [coupon]
 * @Return void
 */
    public void add(ShoppingCoupon coupon) {
         couponMapper.insertSelective(coupon);
    }
/*
 * 获得优惠卷总条数
 * @Author long
 * @Date 2019/12/5
 * @Param [name, type, status]
 * @Return java.lang.String
 */
    public String getTotal(String name, Short type, Short status) {
        return couponMapper.getTotal(name, type, status)+"";
    }
/*
 * 获得用户总条数
 * @Author long
 * @Date 2019/12/5
 * @Param [userId, couponId, status]
 * @Return java.lang.String
 */
    public String getUserTotal(Integer userId, Integer couponId, Short status) {
        return couponMapper.getUserTotal(userId, couponId, status)+"";
    }
}
