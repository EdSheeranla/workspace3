package com.hjy.shop.dto;

import com.hjy.shop.entity.Product;

import java.util.HashMap;

/**
 * Created by admin on 2017/3/12.
 * 封装一个购物车类用于存储用户的购物信息
 */
public class ShoppingCart {
    private HashMap<Integer,Product> cartItem=new HashMap<Integer,Product>();
    private Double totalPrice;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
