package com.hjy.shop.dao;

import com.hjy.shop.entity.Category;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Component("categoryDao")
public class CategoryDao {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public List<Category> queryAll(){
        List<Category> categoryList= (List<Category>) hibernateTemplate.find("from Category");
        return categoryList;
    }

    public Category queryByCid(Integer cid) {
        return hibernateTemplate.load(Category.class,cid);
    }

    public void save(Category currentCategory) {
        hibernateTemplate.save(currentCategory);
    }

    public void delete(Category category) {
        hibernateTemplate.delete(category);
    }
}
