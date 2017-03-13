package com.hjy.shop.service.impl;

import com.hjy.shop.dao.CategoryDao;
import com.hjy.shop.entity.Category;
import com.hjy.shop.service.CategoryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Component("categoryImpl")
@Transactional
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

    @Override
    public Category queryByCid(Integer cid) {
        return categoryDao.queryByCid(cid);
    }

    @Override
    public void update(Category currentCategory) {
        categoryDao.save(currentCategory);
    }

    @Override
    public void deleteByCid(Integer cid) {
        Category category=categoryDao.queryByCid(cid);
        categoryDao.delete(category);
    }

    @Override
    public void add(Category category) {
        categoryDao.save(category);
    }
}
