package com.neu.admin;

import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.domain.ShoppingCoupon;
import com.neu.domain.ShoppingCouponUser;
import com.neu.service.ShoppingCouponService;
import com.neu.util.CouponConstant;
import com.neu.util.ResponseUtil;
import com.neu.util.response.BaseResponse;
import com.neu.util.response.ListVoUntil;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
 * @Author long
 * @Date 2019/11/19 
 * @Param   
 * @Return   
 */ 
@RestController
@RequestMapping("/admin/coupon")
@Validated
public class AdminCouponController {
    @Autowired
    private ShoppingCouponService couponService;
    @PostMapping("/create")
/*
 * 添加优惠券
 * @Author long
 * @Date 2019/11/21
 * @Param [coupon]
 * @Return java.lang.Object
 */
    public Object create(@RequestBody ShoppingCoupon coupon) {
        // 如果是兑换码类型，则这里需要生成一个兑换码
        if (coupon.getType()==CouponConstant.TYPE_CODE) {
            String code = couponService.generateCode();
            coupon.setCode(code);
        }
        couponService.add(coupon);
        return BaseResponse.generateOKResponseEntity("新增成功");
    }
    /*
     * 对前台返回优惠券信息列表
     * @Author long
     * @Date 2019/11/19
     * @Param [name, type, status, page, limit, sort, order]
     * @Return java.lang.Object
     */
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShoppingCoupon>> list(String name, Short type, Short status,
                                                             @RequestParam(defaultValue = "1") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer limit,
                                                             @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                             @Order @RequestParam(defaultValue = "desc") String order) {
        List<ShoppingCoupon> couponList = couponService.querySelective(name, type, status, page, limit, sort, order);
        ListVoUntil listVoUntil=new ListVoUntil();
        String total=couponService.getTotal(name, type, status);
        listVoUntil.setTotal(total);
        listVoUntil.setList(couponList);
        return BaseResponse.generateOKResponseEntity(listVoUntil);
    }
    /*
     *获取优惠券详情信息
     * @Author long
     * @Date 2019/11/20
     * @Param
     * @Return
     */
    @GetMapping("/read")

    public ResponseEntity<BaseResponse<ShoppingCoupon>> read(@RequestParam("id") Integer id) {
        ShoppingCoupon coupon=couponService.findById(id);
        if(coupon!=null) {
            return BaseResponse.generateOKResponseEntity(coupon);
        }else{
            return BaseResponse.generateBadResponseEntity(500,"没有查询到该数据","");
        }
    }
    /*
     *修改优惠券信息
     * @Author long
     * @Date 2019/11/20
     * @Param [coupon]
     * @Return java.lang.Object
     */
    @PostMapping("/update")
    public Object update(@RequestBody ShoppingCoupon coupon) {
        // 如果是兑换码类型，则这里需要生成一个兑换码
        if (coupon.getType()==CouponConstant.TYPE_CODE) {
            String code = couponService.generateCode();
            coupon.setCode(code);
        }else{
            coupon.setCode("");
        }
        if (couponService.updateById(coupon) == 0) {
            return BaseResponse.generateBadResponseEntity(500,"修改失败","");
        }
        return BaseResponse.generateOKResponseEntity("修改成功");
    }
    /*
     *删除优惠券
     * @Author long
     * @Date 2019/11/20
     * @Param [coupon]
     * @Return java.lang.Object
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody ShoppingCoupon coupon) {
        if (couponService.deleteById(coupon.getId()) == 0) {
            return BaseResponse.generateBadResponseEntity(500,"删除失败","");
        }
        return BaseResponse.generateOKResponseEntity("删除成功");
    }
    /*
     *获取优惠券领取用户信息
     * @Author long
     * @Date 2019/11/20
     * @Param [userId, couponId, status, page, limit, sort, order]
     * @Return java.lang.Object
     */
    @GetMapping("/listuser")
    public ResponseEntity<BaseResponse<ShoppingCouponUser>> listuser(Integer userId, Integer couponId, Short status,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer limit,
                           @Sort @RequestParam(defaultValue = "add_time") String sort,
                           @Order @RequestParam(defaultValue = "desc") String order) {
        List<ShoppingCouponUser> couponList = couponService.queryList(userId, couponId, status, page, limit, sort, order);
        ListVoUntil listVoUntil=new ListVoUntil();
        String total=couponService.getUserTotal(userId, couponId, status);
        listVoUntil.setTotal(total);
        listVoUntil.setList(couponList);
        return BaseResponse.generateOKResponseEntity(listVoUntil);
    }
}
