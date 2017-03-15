package com.hjy.shop.action;

import com.hjy.shop.entity.User;
import com.hjy.shop.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by admin on 2017/3/8.
 */
@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven {
    private UserService userService;
    private String captcha;

    public void setIsRememberUsername(String isRememberUsername) {
        this.isRememberUsername = isRememberUsername;
    }

    public void setUser(User user) {

        this.user = user;
    }

    private String isRememberUsername;
    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * 这里没有使用DTO而是直接使用了entity对象来进行封装
     */
    private User user = new User();

    public UserService getUserService() {
        return userService;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String registerPage() {
        return "registerPage";
    }

    public String loginPage() {
        return "loginPage";
    }

    /**
     * AJAX进行异步校验
     */
    public String findByName() throws IOException {
        User existUser = userService.findByUserName(user.getUsername());
        ServletResponse response=ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        if (existUser == null) {
            response.getWriter().println("<font color='green'>用户名可以使用</font>");
        }else{
            response.getWriter().println("<font color='red'>用户名已经存在</font>");
        }
        return NONE;
    }

    public String register() {
        try{
            userService.register(user);
        }catch (Exception e){
            return "registerError";
        }
        return "registerOk";
    }

    /**
     * 根据邮箱来激活用户的账号
     */
    public String active(){
        userService.active(user.getCode());
        return SUCCESS;
    }


    /**
     *完成用户登陆操作
     */
    public String login(){
        HttpSession session=ServletActionContext.getRequest().getSession();
        String checkcode= (String) session.getAttribute("checkcode");
        if(!captcha.equalsIgnoreCase(checkcode)){
            this.addActionError("验证码错误");
            return "loginfail";
        }
        Cookie[] cookies=ServletActionContext.getRequest().getCookies();
        for(Cookie cookie: cookies){
            System.out.println(cookie.getName()+"   " + cookie.getValue());
        }
        User loginUser=userService.login(user);
        if(loginUser!=null){
            if(isRememberUsername!=null) {
                if (isRememberUsername.equals(true)) {

                    saveUsername(loginUser);
                }
            }
            session.setAttribute("loginUser",loginUser);
            return "back";
        }else {
            this.addActionError("用户名或密码错误");
            return "loginfail";
        }
    }
    private void saveUsername(User user){
        Cookie nameCookie = new Cookie("username",user.getUsername());
        ServletActionContext.getResponse().addCookie(nameCookie);
    }
    /**
     * 用户退出登陆操作
     */
    public String logout(){
        ServletActionContext.getRequest().getSession().invalidate();

        return "back";
    }
    public Object getModel() {
        return user;
    }
}
