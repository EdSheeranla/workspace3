package com.hjy.shop.dao;

import com.hjy.shop.entity.Category;
import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.util.HibernateCallBackUtil;
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

    public List<CategorySecond> queryAllByPage(Integer pageNow, Integer pageSize) {
        String hql = "from CategorySecond order by csid";
        int start = (pageNow-1)*pageSize;
        List<CategorySecond> list=hibernateTemplate.execute(new HibernateCallBackUtil<CategorySecond>(start,pageSize,hql,null));
        return list;
    }

    public Integer queryColumnCount() {
        String hql="select count(*) from CategorySecond";
        List list=hibernateTemplate.find(hql);
        if(list.size()==0||list==null){
            return 0;
        }else{
            Number number= (Number) list.get(0);
            return number.intValue();
        }
    }

    public CategorySecond queryByCsid(Integer csid) {
        return hibernateTemplate.load(CategorySecond.class,csid);
    }

    public void update(CategorySecond currCategorySecond) {
        hibernateTemplate.update(currCategorySecond);
    }

    public void delete(CategorySecond categorySecond) {
        hibernateTemplate.delete(categorySecond);
    }

    public void save(CategorySecond categorySecond) {
        hibernateTemplate.save(categorySecond);
    }

    public List<CategorySecond> queryAll() {
        String hql="from CategorySecond order by csid";
        List<CategorySecond> list= (List<CategorySecond>) hibernateTemplate.find(hql);
        return list;
    }
}
