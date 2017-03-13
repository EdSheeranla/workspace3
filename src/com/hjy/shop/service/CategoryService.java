package com.hjy.shop.service;

import com.hjy.shop.entity.Category;

import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */

public interface CategoryService {
    List<Category> queryAll();

    Category queryByCid(Integer cid);

    void update(Category currentCategory);

    void deleteByCid(Integer cid);

    void add(Category category);
}
