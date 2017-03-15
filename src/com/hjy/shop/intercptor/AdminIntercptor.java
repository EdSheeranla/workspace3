package com.hjy.shop.intercptor;

import com.hjy.shop.entity.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletRequest;

/**
 * Created by sheeran on 2017/3/13.
 * 创建一个拦截器用于对后台进行权限管理
 */
public class AdminIntercptor implements Interceptor{

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
//        AdminUser adminUser= (AdminUser)ServletActionContext.getRequest().getAttribute("loginAdminUser");
        AdminUser adminUser= (AdminUser) ServletActionContext.getServletContext().getAttribute("loginAdminUser");
        System.out.println(adminUser);
        if(adminUser==null&&!ServletActionContext.getRequest().getRequestURI().equals("/shop1/admin_login.action")){
            return "loginfail";
        }else {
            return    actionInvocation.invoke();
        }

    }
}
