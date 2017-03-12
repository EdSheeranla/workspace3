package com.hjy.shop.service;

import com.hjy.shop.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by sheeran on 2017/3/12.
 */
public class OrderServiceTest {
    @Test
    public void testOrderId(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderServiceImpl orderServiceImpl= (OrderServiceImpl) ac.getBean("orderServiceImpl");
        orderServiceImpl.showOrderByPage(1,1);
    }
}
