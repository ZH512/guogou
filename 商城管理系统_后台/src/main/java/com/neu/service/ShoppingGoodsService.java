package com.neu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.common.GoodsAllinone;
import com.neu.dao.ShoppingGoodsMapper;
import com.neu.domain.*;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingBrand;
import com.neu.vo.ShoppingCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

@Transactional(rollbackFor=Exception.class)
@Service
public class ShoppingGoodsService  {

    private Integer goodid;

    @Resource
    private ShoppingGoodsMapper goodsMapper;

    public Object list(String goodsSn, String name, Integer page, Integer limit) {
        try {
            PageHelper.startPage(page,limit);
            List<ShoppingGoods> shoppingGoodsList=querySelective(goodsSn,name);
            for (ShoppingGoods goods:shoppingGoodsList){
                String s=goodsMapper.selectGralleryById(goods.getId());
                String[] gallery=stringTo(s);
                goods.setGallery(gallery);
            }
            PageInfo pageInfo=new PageInfo(shoppingGoodsList);
            return BaseResponse.generateOKResponseEntity(pageInfo);
        }catch (Exception e) {
            throw e;
        }
    }

    public Object list2() {
        try {
            List<ShoppingBrand> shoppingBrands=all();
            List<ShoppingCategory> shoppingCategories=queryL1();
            for (ShoppingCategory shoppingCategory:shoppingCategories){
                List<ShoppingCategory> shoppingCategorysChild=queryByPid(shoppingCategory.getId());
                shoppingCategory.setChildList(shoppingCategorysChild);
            }
            Map<String,Object> data=new HashMap<>();
            data.put("categoryList",shoppingCategories);
            data.put("brandList",shoppingBrands);
            return BaseResponse.generateOKResponseEntity(data);
        }catch (Exception e) {
            throw e;
        }
    }

    public Object delete(ShoppingGoods goods) {
        try {
            int resultRow=goodsMapper.goodsDeleteByGoodsSn(goods.getGoodsSn());
            if (resultRow!=0){
                return BaseResponse.generateOKResponseEntity("删除成功！");
            }
        }catch (Exception e) {
            throw e;
        }
        return BaseResponse.serious();
    }

    public Object detail(Integer id) {
        try {
            List<ShoppingGoods> shoppingGoods=goodsMapper.selectGoodsById(id);
            for (ShoppingGoods goods:shoppingGoods){
                String s=goodsMapper.selectGralleryById(id);
                String[] gallery=stringTo(s);
                goods.setGallery(gallery);
            }
            if (shoppingGoods.size()>0){
                String goodid=String.valueOf(id);
                List<ShoppingGoodsProduct> shoppingGoodsProducts=goodsMapper.selectGoodsProductsById(goodid);
                List<ShoppingGoodsAttribute> goodsAttributes=goodsMapper.selectgoodsAttributesById(goodid);
                List<ShoppingGoodsSpecification> goodsSpecifications=goodsMapper.selectgoodsSpecificationsById(goodid);

                for (ShoppingGoodsProduct goodsProduct:shoppingGoodsProducts){
                    String s=goodsMapper.selectSpecificationsById(goodsProduct.getId());
                    String[] specifications=stringTo(s);
                    goodsProduct.setSpecifications(specifications);
                }

                Map<String,Object> data=new HashMap<>();
                data.put("goods",shoppingGoods);
                data.put("specifications",goodsSpecifications);
                data.put("products",shoppingGoodsProducts);
                data.put("attributes",goodsAttributes);
                return BaseResponse.generateOKResponseEntity(data);
            }
        }catch (Exception e) {
            throw e;
        }
        return BaseResponse.serious();
    }

    public Object create(GoodsAllinone goodsAllinone) {
        try {

            ShoppingGoods shoppingGoods=goodsAllinone.getGoods();
            ShoppingGoodsAttribute[] goodsAttributes=goodsAllinone.getAttributes();
            ShoppingGoodsProduct[] goodsProducts=goodsAllinone.getProducts();
            ShoppingGoodsSpecification[] goodsSpecifications=goodsAllinone.getSpecifications();


            List<ShoppingGoods> shoppingGoodsListByDB=checkExistByNameAndGoodsSn(shoppingGoods.getGoodsSn(),shoppingGoods.getName(),null);
            if (shoppingGoodsListByDB.size()>0){
                return BaseResponse.generateBadResponseEntity("商品名或编号重复","");
            }
            shoppingGoods.setId(Integer.parseInt(shoppingGoods.getGoodsSn()));
            shoppingGoods.setDeleted(false);
            String[] strings=shoppingGoods.getGallery();
            String strG=arraysToString(strings);
            shoppingGoods.setStrGallery(strG);
            shoppingGoods.setAddTime(LocalDateTime.now());
            shoppingGoods.setUpdateTime(LocalDateTime.now());
            int resultRow=goodsMapper.addShoppingGoods(goodsAllinone.getGoods());
            if (resultRow==1){
                int goodsSn=goodsMapper.selectGoodsId(shoppingGoods.getGoodsSn());
                goodsMapper.updateGoodsSn(String.valueOf(goodsSn),goodsSn);
                goodid=goodsSn;
                resultRow=addShoppingGoodsInfo(goodsAttributes,goodsProducts,goodsSpecifications,resultRow);
                if (resultRow > 0) {
                    return BaseResponse.generateOKResponseEntity("商品添加成功");
                }
            }
        }catch (Exception e) {
            throw e;
        }
        return BaseResponse.serious();
    }

    public List<ShoppingBrand> all(){
        try {
            return goodsMapper.selectShoppingBrands();
        }catch (Exception e) {
            throw e;
        }
    }

    public List<ShoppingCategory> queryL1(){
        try {
            return goodsMapper.selectShoppingCategory();
        }catch (Exception e) {
            throw e;
        }
    }

    public List<ShoppingCategory> queryByPid(int pid){
        try {
            return goodsMapper.selectShoppingCategoryByPid(pid);
        }catch (Exception e) {
            throw e;
        }
    }

    public List<ShoppingGoods> querySelective(String goodsSn,String name){
        try {
            return goodsMapper.selectByGoodsSnWithName(goodsSn,name);
        }catch (Exception e) {
            throw e;
        }
    }

    public int addShoppingGoodsSpecification(ShoppingGoodsSpecification[] goodsSpecifications) {
        try {
            int i=0;
            for (ShoppingGoodsSpecification shoppingGoodsSpecification:goodsSpecifications){
                shoppingGoodsSpecification.setDeleted(false);
                if (null==shoppingGoodsSpecification.getGoodsId()) {
                    shoppingGoodsSpecification.setGoodsId(goodid);
                }
                shoppingGoodsSpecification.setAddTime(LocalDateTime.now());
                shoppingGoodsSpecification.setUpdateTime(LocalDateTime.now());
                goodsMapper.addShoppingGoodsSpecification(shoppingGoodsSpecification);
                i++;
            }
            return i;
        }catch (Exception e) {
            throw e;
        }
    }

    public int addShoppingGoodsProduct(ShoppingGoodsProduct[] goodsProducts) {
        try {
            int i=0;
            for (ShoppingGoodsProduct shoppingGoodsProduct:goodsProducts){
                shoppingGoodsProduct.setDeleted(false);
                if (null==shoppingGoodsProduct.getGoodsId()) {
                    shoppingGoodsProduct.setGoodsId(goodid);
                }
                String[] strings=shoppingGoodsProduct.getSpecifications();
                String strG=arraysToString(strings);
                shoppingGoodsProduct.setStrSpecifications(strG);
                shoppingGoodsProduct.setAddTime(LocalDateTime.now());
                shoppingGoodsProduct.setUpdateTime(LocalDateTime.now());
                goodsMapper.addShoppingGoodsProduct(shoppingGoodsProduct);
                i++;
            }
            return i;
        }catch (Exception e) {
            throw e;
        }
    }

    private String arraysToString(String[] strings) {
        try {
            String strG=Arrays.toString(strings);
            strG=strG.replace(",","\",\"");
            strG=strG.replace("[","[\"");
            strG=strG.replace("]","\"]");
            return strG;
        } catch (Exception e) {
            throw e;
        }
    }

    public int addShoppingGoodsAttribute(ShoppingGoodsAttribute[] goodsAttributes) {
        try {
            int i=0;
            for (ShoppingGoodsAttribute shoppingGoodsAttribute:goodsAttributes){
                shoppingGoodsAttribute.setDeleted(false);
                if (null==shoppingGoodsAttribute.getGoodsId()) {
                    shoppingGoodsAttribute.setGoodsId(goodid);
                }
                shoppingGoodsAttribute.setAddTime(LocalDateTime.now());
                shoppingGoodsAttribute.setUpdateTime(LocalDateTime.now());
                goodsMapper.addShoppingGoodsAttribute(shoppingGoodsAttribute);
                i++;
            }
            return i;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<ShoppingGoods> checkExistByNameAndGoodsSn(String goodsSn, String name,Integer id) {
        try {
            return goodsMapper.selectGoodsByName(goodsSn,name,id);
        }catch (Exception e) {
            throw e;
        }
    }

    public String[] stringTo(String s) {
        s=s.replace("[","");
        s=s.replace("]","");
        s=s.replace("\"","");
        s=s.replace(" ","");
        return s.split(",");
    }

    public Object update(GoodsAllinone goodsAllinone) {
        try {
            ShoppingGoods shoppingGoods = goodsAllinone.getGoods();
            ShoppingGoodsAttribute[] goodsAttributes = goodsAllinone.getAttributes();
            ShoppingGoodsProduct[] goodsProducts = goodsAllinone.getProducts();
            ShoppingGoodsSpecification[] goodsSpecifications = goodsAllinone.getSpecifications();


            List<ShoppingGoods> shoppingGoodsListByDB = checkExistByNameAndGoodsSn(shoppingGoods.getGoodsSn(), shoppingGoods.getName(), shoppingGoods.getId());
            if (shoppingGoodsListByDB.size() > 0) {
                return BaseResponse.generateBadResponseEntity("商品名或编号重复", "");
            }

            shoppingGoods.setStrGallery(arraysToString(shoppingGoods.getGallery()));
            shoppingGoods.setUpdateTime(LocalDateTime.now());
            int resultRow = goodsMapper.updateByIdSelective(shoppingGoods);
            if (resultRow > 0) {
                //逻辑删除
                resultRow = goodsMapper.deleteByGoodId(String.valueOf(shoppingGoods.getId()));
                if (resultRow > 0) {
                    resultRow=addShoppingGoodsInfo(goodsAttributes,goodsProducts,goodsSpecifications,resultRow);
                    if (resultRow > 0) {
                        return BaseResponse.generateOKResponseEntity("商品修改成功");
                    }
                }
            }
        }catch (Exception e) {
            throw e;
        }
        return null;
    }

    private Integer addShoppingGoodsInfo(ShoppingGoodsAttribute[] goodsAttributes, ShoppingGoodsProduct[] goodsProducts, ShoppingGoodsSpecification[] goodsSpecifications, int resultRow) {
        try {
            resultRow = addShoppingGoodsAttribute(goodsAttributes);
            if (resultRow > 0) {
                resultRow = addShoppingGoodsProduct(goodsProducts);
                if (resultRow > 0) {
                    resultRow = addShoppingGoodsSpecification(goodsSpecifications);
                    return resultRow;
                }
            }
        }catch (Exception e){
            throw e;
        }
        return null;
    }
  /**
     * 根据数组查询商品列表
     * @param ids
     * @return
     */
    public List<ShoppingGoods> queryByIds(Integer[] ids){
        List<ShoppingGoods> list = goodsMapper.selectByExampleSelective(ids);
        return list;
    }

    public  int count() {
        ShoppingGoodsAttribute attribute=new ShoppingGoodsAttribute();
        attribute.andLogicalDeleted(false);
        return (int) goodsMapper.countByExample(attribute);
    }
}

