package com.hjy.shop.service;

import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.Product;

import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
public interface ProductService {
    List<Product> queryHotProduct();

    List<Product> queryLastProdict();

    Product queryById(Integer pid);

    PageBean<Product> queryProductByPage(Integer pageNow,Integer cid);

    PageBean<Product> queryProductByPageCsid(int pageNow,int csid);
}
