package com.hjy.shop.service.impl;

import com.hjy.shop.dao.CategorySecondDao;
import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.CategorySecondService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Component("categorySecondServiceImpl")
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
}
