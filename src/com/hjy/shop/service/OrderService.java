package com.hjy.shop.service;

import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.Order;

/**
 * Created by sheeran on 2017/3/12.
 */
public interface OrderService {

    void save(Order order);

    PageBean<Order> showOrderByPage(Integer pageNow, Integer userid);

    Order queryByOid(Integer oid);

    Order update(Order order);
}

