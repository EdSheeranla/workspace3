package com.hjy.shop.service.impl;

import com.hjy.shop.dao.ProductDao;
import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Transactional
@org.springframework.stereotype.Component("productServiceImpl")
public class ProductServiceImpl implements ProductService {
    private PageBean<Product> pageBean=new PageBean<Product>();
    private int pageSize=12;
    private int pageCount = 0;
    private int columnCout = 0;
    private String hql="";

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

    /**
     * 根据一级分类id分页查询商品
     */
    @Override
    public PageBean<Product> queryProductByPage(Integer pageNow,Integer cid) {
        hql="select count(*) from Product p where  p.categorySecond.category.cid = ?" ;

        setPageBean(pageNow,cid);

        hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
        List<Product> productList=productDao.queryProductByPage(hql,pageNow,pageSize,cid);
        pageBean.setList(productList);
        return pageBean;
    }

    /**
     * 根据二级分类id分页查询商品
     */
    @Override
    public PageBean<Product> queryProductByPageCsid(int pageNow,int csid) {
        hql="select count(*) from Product p where  p.categorySecond.csid = ?" ;
        setPageBean(pageNow,csid);
        hql="select p from Product p join p.categorySecond cs where cs.csid= ?";
        List<Product> productList=productDao.queryProductByPage(hql,pageNow,pageSize,csid);
        pageBean.setList(productList);
        return pageBean;
    }

    private void setPageBean(int pageNow,int id){
        pageBean.setPageNow(pageNow);
        columnCout = productDao.queryPageCount(hql,id);
        pageCount = (columnCout - 1)/pageSize+1;
        pageBean.setColumnCount(columnCout);
        pageBean.setPageCount(pageCount);
    }

}
