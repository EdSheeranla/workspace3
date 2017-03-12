package com.hjy.shop.service.impl;

import com.hjy.shop.dao.ProductDao;
import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.ProductService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@org.springframework.stereotype.Component("productServiceImpl")
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;
    @Resource
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> queryHotProduct() {
        return productDao.queryHotProductByPage();
    }

    public List<Product> queryLastProdict() {
        return productDao.queryLastProductByPage();
    }

    public Product queryById(Integer pid) {
        return productDao.queryById(pid);
    }

    @Override
    public PageBean<Product> queryProductByPage(Integer pageNow,Integer cid) {
        PageBean<Product> pageBean=new PageBean<Product>();
        pageBean.setPageNow(pageNow);
        int pageSize=12;
        int pageCount = 0;
        int columnCout = 0;
        columnCout = productDao.queryPageCount(cid);
        pageCount = (columnCout - 1)/pageSize+1;
        pageBean.setColumnCount(columnCout);
        pageBean.setPageCount(pageCount);
        List<Product> productList=productDao.queryProductByPage(pageNow,pageSize,cid);
        pageBean.setList(productList);
        System.out.println(productList.size());
        return pageBean;
    }
}
