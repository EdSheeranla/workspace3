package com.hjy.shop.entity;

import java.util.Date;

/**
 * Created by sheeran on 2017/3/12.
 * 创建一个表单细节类
 */
public class OrderItem {
   private Integer itemid;
   private Integer count;
   private Double subtotal;
   //创建一个many-to-one 外键映射product表
   private Product product;
   //创建一个many-to-one 外键映射oreder表
   private Order order;

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
