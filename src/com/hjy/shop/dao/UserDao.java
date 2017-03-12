package com.hjy.shop.dao;

import com.hjy.shop.entity.User;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/8.
 */
@Component("userDao")
public class UserDao {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void add(User user){
        hibernateTemplate.save(user);
    }
    public User findByUserName(String username){
         List<User> list= (List<User>) hibernateTemplate.find("from User where username= ?",username);
         if(list.size()!=0){
            return list.get(0);
        }else{
            return null;
        }
    }
    public User finByEmail(String email){
        List<User> list= (List<User>) hibernateTemplate.find("from User where email= ?",email);
        if(list.size()!=0){
            return list.get(0);
        }else{
            return null;
        }
    }
    public User findByUUID(String code){
        List<User> list= (List<User>) hibernateTemplate.find("from User where code= ?",code);
        if(list.size()!=0){
            return list.get(0);
        }else{
            return null;
        }
    }
    //更新用户
    public void update(User user){
        hibernateTemplate.update(user);
    }
    public User query(User user){
        List<User> list= (List<User>) hibernateTemplate.find("from User where username = ? and password = ?",user.getUsername(),user.getPassword());
        if(list.size()!=0){
            return list.get(0);
        }else{
            list=(List<User>) hibernateTemplate.find("from User where email = ? and password = ?",user.getUsername(),user.getPassword());
            if(list.size()!=0){
                return list.get(0);
            }
            return null;
        }
    }
}
