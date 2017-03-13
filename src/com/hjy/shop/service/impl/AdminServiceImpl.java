package com.hjy.shop.service.impl;

import com.hjy.shop.dao.AdminDao;
import com.hjy.shop.entity.AdminUser;
import com.hjy.shop.service.AdminService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by sheeran on 2017/3/13.
 */
@Component("adminServiceImpl")
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;
    @Resource
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public AdminUser loginValidate(AdminUser adminUser) {

        return adminDao.check(adminUser);
    }
}
