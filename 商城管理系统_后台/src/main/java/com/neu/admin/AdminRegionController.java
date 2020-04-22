package com.neu.admin;


import com.neu.service.ShoppingregionService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.EmptyVo;
import com.neu.vo.ShoppingRegion;
import com.neu.vo.ShoppingRegiontree;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/admin/region")
@Validated
public class AdminRegionController {
    @Autowired
    private ShoppingregionService shoppingregionService;

    /* *
     * 作用:查询行政区域信息
     * yinjian on 2019/11/19
     * @param
     **/
    @ApiOperation(value = "查询行政区域信息")
    @PostMapping("/list")
    public ResponseEntity<BaseResponse<EmptyVo>> list() {
        List<ShoppingRegion> list = shoppingregionService.queryByPid();
        ShoppingRegiontree shop = new ShoppingRegiontree();
        this.recursion(list,shop,0);
        return BaseResponse.generateOKResponseEntity(shop.getChildren());
    }

    public void menu(ShoppingRegiontree menutree,ShoppingRegion next){
        //菜单ID
        menutree.setId(next.getId());
        //菜单名称
        menutree.setName(next.getName());
        //菜单类型
        menutree.setType(next.getType());
        //行政区域编码
        menutree.setCode(next.getCode());
        //菜单对象
        menutree.setShoppingRegion(next);
    }

    //递归菜单
    public void recursion(List<ShoppingRegion> menulist, ShoppingRegiontree menutree, Integer seifid){
        Iterator<ShoppingRegion> lterator = menulist.iterator();
        while (lterator.hasNext()){
            //取集合的数据
            ShoppingRegion next = lterator.next();

            //初始化自身菜单
            if(next.getId().equals(seifid)){
//                menu(menutree,next);
            }
            //初始化菜单
            else if(next.getPid().equals(seifid)){
                //定义新的对象
                ShoppingRegiontree menutree1 = new ShoppingRegiontree();
                //给菜单赋值
                this.menu(menutree1,next);
                //将子菜单加到父级菜单上
                menutree.getChildren().add(menutree1);
                this.recursion(menulist,menutree1,next.getId());
            }
        }
    }
}