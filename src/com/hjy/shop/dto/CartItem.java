package com.hjy.shop.dto;

import com.hjy.shop.entity.Product;

/**
 * Created by admin on 2017/3/12.
 * 代表购物车中的单个商品
 */
public class CartItem {
    private Product product;
    private Integer count;
    private Double subtotal;

    public CartItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return count*product.getShop_price();
    }

}
