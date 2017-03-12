package com.hjy.shop.dto;
import java.util.*;

/**
 * Created by admin on 2017/3/12.
 * 封装一个购物车类用于存储用户的购物信息
 */
public class ShoppingCart {
    private LinkedHashMap<Integer,CartItem> cart=new LinkedHashMap<Integer,CartItem>();
    private Double totalPrice= Double.valueOf(0);
    private Collection<CartItem> cartItems;

    public Double getTotalPrice() {
        return totalPrice;
    }

    //将map中cartItem转换为一个collection单列集合
    public Collection<CartItem> getCartItems(){
        return cart.values();
    }
    //添加
    //这里注意添加的是一个item 而不是一个product ，这里有些细节没有想到
    public void add(CartItem item){
        Integer pid = item.getProduct().getPid();
        if(cart.containsKey(pid)){
            CartItem localItem=cart.get(pid);
            localItem.setCount(localItem.getCount()+item.getCount());
        }else {
            cart.put(pid, item);
        }
        totalPrice = totalPrice+item.getSubtotal();

    }


    //清空
    public void clear (){
        cart.clear();
        totalPrice = Double.valueOf(0);
    }

    //删除
    public void remove(Integer pid){
        CartItem cartItem=cart.remove(pid);
        totalPrice = totalPrice-cartItem.getSubtotal();
    }

}
