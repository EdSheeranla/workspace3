package com.hjy.shop.entity;

/**
 * Created by sheeran on 2017/3/13.
 */
public class AdminUser {
    private Integer adminid;
    private String name;
    private String password;

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
