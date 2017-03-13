package com.hjy.shop.action;

import com.hjy.shop.entity.AdminUser;
import com.hjy.shop.service.AdminService;
import com.hjy.shop.util.Mymd5;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by sheeran on 2017/3/13.
 */
@Component
@Scope("prototype")
public class AdminAction extends ActionSupport implements ModelDriven<AdminUser>{
    private AdminUser adminUser=new AdminUser();
    private AdminService adminService;
    @Resource
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public String loginPage(){
        return "loginPage";
    }
    public String login(){
        adminUser.setPassword(Mymd5.doMD5(adminUser.getPassword()));
        AdminUser loginAdminUser=adminService.loginValidate(adminUser);
        if(loginAdminUser==null){
            this.addActionError("用户名或者密码错误!!");
            return "loginfail";
        }
        ServletActionContext.getRequest().getSession().setAttribute("loginAdminUser",loginAdminUser);
        return "loginsuccess";
    }
    @Override
    public AdminUser getModel() {
        return adminUser;
    }
}
