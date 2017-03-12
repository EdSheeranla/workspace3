package com.hjy.shop.dao;

import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.entity.Product;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Component("categorySecondDao")
public class CategorySecondDao {
    private HibernateTemplate hibernateTemplate;
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public List<CategorySecond> queryAllByCid(Integer cid) {
        String hql="from CategorySecond where cid=?";
        List<CategorySecond> categorySecondList= (List<CategorySecond>) hibernateTemplate.find(hql,cid);
        return categorySecondList;
    }
}
