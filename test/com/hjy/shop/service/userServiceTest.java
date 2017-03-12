package com.hjy.shop.service;

import com.hjy.shop.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by admin on 2017/3/8.
 */
public class userServiceTest {
    ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    @Test
    public void TestRigister(){
        User user=new User();
        user.setName("aa");
        user.setPassword("bb");
        user.setEmail("bb");
        UserService userService= (UserService) ac.getBean("userServiceImpl");
        userService.register(user);

    }
    @Test
    public void TestFindByName() throws IOException {
        UserService userService= (UserService) ac.getBean("userServiceImpl");
        System.out.println(userService.findByUserName("aaa"));
    }

}
