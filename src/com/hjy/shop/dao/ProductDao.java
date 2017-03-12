package com.hjy.shop.dao;

import com.hjy.shop.entity.Product;
import com.hjy.shop.util.HibernateCallBackUtil;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
@Transactional
@Component("productDao")
public class ProductDao {
    private HibernateTemplate hibernateTemplate;
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public List<Product> queryHotProductByPage(){
        DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
        criteria.add(Restrictions.eq("is_hot",1));
        criteria.addOrder(Order.desc("pdate"));
        List<Product> hotProductList=(List<Product>)hibernateTemplate.findByCriteria(criteria,0,10);
        return hotProductList;
    }
    public void queryByName(){
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Product where pid = 1 ");
        Product producte= (Product) query.uniqueResult();
        System.out.println(producte.getImage());

    }

    public List<Product> queryLastProductByPage() {
        DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
        criteria.addOrder(Order.desc("pdate"));
        List<Product> lastProductList=(List<Product>)hibernateTemplate.findByCriteria(criteria,0,10);
        return lastProductList;
    }

    public Product queryById(Integer pid) {
        Product product = hibernateTemplate.get(Product.class,pid);
        System.out.println(product.getPname()+product.getPid());
        return product;
    }

    public int queryPageCount(Integer cid) {
        int columnCount=0;
        String hql="select count(*) from Product p where  p.categorySecond.category.cid = ?" ;
        List list=hibernateTemplate.find(hql,cid);
        if(list.size()==0||list==null){
            columnCount=0;
        }else {
            Number number= (Number) list.get(0);
            columnCount=number.intValue();
        }
        return columnCount;
    }

    public List<Product> queryProductByPage(Integer pageNow,Integer pageSize,Integer cid) {
        String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
        int start  =  (pageNow-1)*pageSize;
        int end = pageNow*pageSize;
        String[] parameters={cid+""};
        List<Product> list=hibernateTemplate.execute(new HibernateCallBackUtil<Product>(start,end,hql,parameters));
        return list;
    }
}
