package com.hjy.shop.service;

import com.hjy.shop.entity.AdminUser;

/**
 * Created by sheeran on 2017/3/13.
 */
public interface AdminService {
    AdminUser loginValidate(AdminUser adminUser);
}
