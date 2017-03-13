package com.hjy.shop.dao;

import com.hjy.shop.entity.AdminUser;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sheeran on 2017/3/13.
 */
@Component("adminDao")
public class AdminDao {
    private HibernateTemplate hibernateTemplate;
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public AdminUser check(AdminUser adminUser) {
    String hql="from AdminUser where name=? and password=?";
    List<AdminUser> adminUserList= (List<AdminUser>) hibernateTemplate.find(hql,adminUser.getName(),adminUser.getPassword());
    if(adminUserList.size()==0||adminUserList==null){
        return null;
    }else{
        return adminUserList.get(0);
    }
    }
}
