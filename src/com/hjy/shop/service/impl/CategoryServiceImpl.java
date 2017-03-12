package com.hjy.shop.service.impl;

import com.hjy.shop.dao.CategoryDao;
import com.hjy.shop.entity.Category;
import com.hjy.shop.service.CategoryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Component("categoryImpl")
public class CategoryServiceImpl implements CategoryService{
    private CategoryDao categoryDao;

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
    @Resource
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> queryAll() {

        return categoryDao.queryAll();
    }
}
