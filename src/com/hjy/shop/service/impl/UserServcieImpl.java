package com.hjy.shop.service.impl;

import com.hjy.shop.dao.UserDao;
import com.hjy.shop.entity.User;
import com.hjy.shop.service.UserService;
import com.hjy.shop.util.MailUtil;
import com.hjy.shop.util.UUIDutil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/3/8.
 */
@Component("userServiceImpl")
@Transactional
public class UserServcieImpl implements UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }
    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void register(User user) {
        user.setState(0);
        user.setCode(UUIDutil.getUUID()+UUIDutil.getUUID());
        /**
         * 这里发送邮件
         */
        MailUtil.sendMail(user.getEmail(),user.getCode());
        userDao.add(user);
    }

    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    public void active(String code) {
        User user=userDao.findByUUID(code);
        user.setState(1);
        user.setCode(null);
        userDao.update(user);
    }

    public User login(User loginuser) {
        User user=userDao.query(loginuser);
        if(user!=null){
            return user;
        }
        return null;
    }
}
