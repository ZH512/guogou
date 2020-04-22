package com.neu.admin;

import com.neu.service.ShoppingBrandService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.EmptyVo;
import com.neu.vo.ShoppingBrand;
import com.neu.vo.fenye;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yinjian by 2019/11/20
 */
@RestController
@RequestMapping("/admin/brand")
public class AdminbrandController {
    @Autowired
    private ShoppingBrandService shoppingBrandService;

    /* *
     * 作用:商品制造商
      {
	"pagenum":1,
	"pagesize":2,
	"id":1001010,
	"name":"星"
    }
     * yinjian on 2019/11/19
     * @param
     **/
    @ApiOperation(value = "查询商品制造商")
    @PostMapping("/list")
    private ResponseEntity<BaseResponse<EmptyVo>> list(@RequestBody ShoppingBrand shoppingBrand){
        if (null == shoppingBrand.getPagenum() || "".equals(shoppingBrand.getPagenum())){
            return BaseResponse.badArgument();
        }
        if (null == shoppingBrand.getPagesize() || "".equals(shoppingBrand.getPagesize())){
            return BaseResponse.badArgument();
        }
        List<ShoppingBrand> list =  shoppingBrandService.querySelective(shoppingBrand);
        fenye fenye = new fenye();
        fenye.setList(list);
        //获取总数
        if (null == shoppingBrand.getId() && null == shoppingBrand.getName()){
            fenye.setTotal(shoppingBrandService.gettotal());
        }else {
            fenye.setTotal(list.size());
        }
        return BaseResponse.generateOKResponseEntity(fenye);
    }

    /* *
     * 作用:新增新增品牌商信息
      {
        "name":"123",
        "desc":"这是介绍",
        "floorPrice":12.8
      }
     * yinjian on 2019/11/20
     * @param shoppingBrand
     **/
    @ApiOperation(value = "新增品牌商信息")
    @PostMapping("/create")
    private ResponseEntity<BaseResponse<EmptyVo>> create(@RequestBody ShoppingBrand shoppingBrand){
        if (null == shoppingBrand.getName() || "".equals(shoppingBrand.getName())){
            return BaseResponse.badArgument();
        }
        if (null == shoppingBrand.getDesc() || "".equals(shoppingBrand.getDesc())){
            return BaseResponse.badArgument();
        }
        if (null == shoppingBrand.getFloorPrice() || "".equals(shoppingBrand.getFloorPrice())){
            return BaseResponse.badArgument();
        }
        int code = shoppingBrandService.add(shoppingBrand);
        if (code != 1){
            return BaseResponse.serious();
        }
        return BaseResponse.generateOKResponseEntity("新增成功");
    }

    /* *
     * 作用:修改品牌商信息
        {
                "id":1046002,
                "name":"22",
                "desc":"介绍",
                "floorPrice":22.8
        }
     * yinjian on 2019/11/20
     * @param shoppingBrand
     **/
    @ApiOperation(value = "修改品牌商信息")
    @PostMapping("/update")
    private ResponseEntity<BaseResponse<EmptyVo>> update(@RequestBody ShoppingBrand shoppingBrand){
        if (null == shoppingBrand.getName() || "".equals(shoppingBrand.getName())){
            return BaseResponse.badArgument();
        }
        if (null == shoppingBrand.getDesc() || "".equals(shoppingBrand.getDesc())){
            return BaseResponse.badArgument();
        }
        if (null == shoppingBrand.getFloorPrice() || "".equals(shoppingBrand.getFloorPrice())){
            return BaseResponse.badArgument();
        }
        int code = shoppingBrandService.updateById(shoppingBrand);
        if (code != 1){
            return BaseResponse.serious();
        }
        return BaseResponse.generateOKResponseEntity("修改成功");
    }

    /* *
     * 作用:删除品牌商信息
        {
                "id":1046002
        }
     * yinjian on 2019/11/20
     * @param shoppingBrand
     **/
    @ApiOperation(value = "删除品牌商信息")
    @PostMapping("/delete")
    private ResponseEntity<BaseResponse<EmptyVo>> delete(@RequestBody ShoppingBrand shoppingBrand){
        if (null == shoppingBrand.getId() || "".equals(shoppingBrand.getId())){
            return BaseResponse.badArgument();
        }
        int code = shoppingBrandService.deleteById(shoppingBrand);
        if (code != 1){
            return BaseResponse.serious();
        }
        return BaseResponse.generateOKResponseEntity("删除成功");
    }

}
