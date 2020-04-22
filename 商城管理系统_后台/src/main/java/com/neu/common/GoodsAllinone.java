package com.neu.common;

import com.neu.domain.ShoppingGoods;
import com.neu.domain.ShoppingGoodsAttribute;
import com.neu.domain.ShoppingGoodsProduct;
import com.neu.domain.ShoppingGoodsSpecification;

public class GoodsAllinone {
    ShoppingGoods goods;
    ShoppingGoodsSpecification[] specifications;
    ShoppingGoodsAttribute[] attributes;
    ShoppingGoodsProduct[] products;

    public ShoppingGoods getGoods() {
        return goods;
    }

    public void setGoods(ShoppingGoods goods) {
        this.goods = goods;
    }

    public ShoppingGoodsProduct[] getProducts() {
        return products;
    }

    public void setProducts(ShoppingGoodsProduct[] products) {
        this.products = products;
    }

    public ShoppingGoodsSpecification[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(ShoppingGoodsSpecification[] specifications) {
        this.specifications = specifications;
    }

    public ShoppingGoodsAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(ShoppingGoodsAttribute[] attributes) {
        this.attributes = attributes;
    }

}
