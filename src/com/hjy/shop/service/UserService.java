package com.hjy.shop.service;

import com.hjy.shop.entity.User;

/**
 * Created by admin on 2017/3/8.
 */
public interface UserService {
    public void register(User user);
    public User findByUserName(String username);
    public void active(String code);
    public User login(User loginuser);
}
