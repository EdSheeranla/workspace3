package com.hjy.shop.service;

import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
public interface CategorySecondService {
    List<CategorySecond> queryAllProductByCid(Integer cid);

    PageBean<CategorySecond> queryAllByPage(Integer pageNow);

    CategorySecond queryByCsid(Integer csid);

    void update(CategorySecond currCategorySecond);

    void delete(CategorySecond categorySecond);

    void add(CategorySecond categorySecond);

    List<CategorySecond> queryAll();
}
