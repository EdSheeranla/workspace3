package com.hjy.shop.service;

import com.hjy.shop.dao.ProductDao;
import com.hjy.shop.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
public class ProductSericeTest {
    @Test
    public void testQuery(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductService productService= (ProductService) ac.getBean("productServiceImpl");
        List<Product> productList=productService.queryHotProduct();
        System.out.println(productList.get(2).getImage());
        Assert.assertEquals(10,productList.size());

    }
    @Test
    public void testQueryone(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductDao productDao= (ProductDao) ac.getBean("productDao");
        productDao.queryByName();

    }
    @Test
    public void testQueryById(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductDao productDao= (ProductDao) ac.getBean("productDao");
        Product p=productDao.queryById(70);
        System.out.println(p.getPname());
    }
    @Test
    public void testQueryCount(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductDao productDao= (ProductDao) ac.getBean("productDao");
        System.out.println(productDao.queryPageCount(1));
    }
}
