package com.hjy.shop.service.impl;

import com.hjy.shop.dao.CategorySecondDao;
import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.CategorySecondService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Component("categorySecondServiceImpl")
@Transactional
public class CategorySecondServiceImpl implements CategorySecondService {
    private CategorySecondDao categorySecondDao;
    @Resource
    public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
        this.categorySecondDao = categorySecondDao;
    }

    @Override
    public List<CategorySecond> queryAllProductByCid(Integer cid) {
        return categorySecondDao.queryAllByCid(cid);
    }

    @Override
    public PageBean<CategorySecond> queryAllByPage(Integer pageNow) {
        PageBean<CategorySecond> pageBean=new PageBean<CategorySecond>();
        Integer pageCount = 0;
        Integer pageSize = 20 ;
        Integer columnCount = 0;
        columnCount = categorySecondDao.queryColumnCount();
        pageCount = (columnCount-1)/pageSize+1;
        List<CategorySecond> list=categorySecondDao.queryAllByPage(pageNow,pageSize);

        pageBean.setPageNow(pageNow);
        pageBean.setPageCount(pageCount);
        pageBean.setPageSize(pageSize);
        pageBean.setColumnCount(columnCount);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public CategorySecond queryByCsid(Integer csid) {
        return categorySecondDao.queryByCsid(csid);
    }

    @Override
    public void update(CategorySecond currCategorySecond) {
        categorySecondDao.update(currCategorySecond);
    }

    @Override
    public void delete(CategorySecond categorySecond) {
        categorySecondDao.delete(categorySecond);
    }

    @Override
    public void add(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);
    }

    @Override
    public List<CategorySecond> queryAll() {
        return categorySecondDao.queryAll();
    }
}
