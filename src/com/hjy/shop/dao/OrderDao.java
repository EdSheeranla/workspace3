package com.hjy.shop.dao;

import com.hjy.shop.entity.Order;
import com.hjy.shop.entity.OrderItem;
import com.hjy.shop.util.HibernateCallBackUtil;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sheeran on 2017/3/12.
 */
@Component("orderDao")
public class OrderDao  {
    private HibernateTemplate hibernateTemplate;
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void add(Order order) {
        hibernateTemplate.save(order);

    }

    public int queryPageCount(String hql, int id) {
        int columnCount=0;
        List list=hibernateTemplate.find(hql,id);
        if(list.size()==0||list==null){
            columnCount=0;
        }else {
            Number number= (Number) list.get(0);
            columnCount=number.intValue();
        }
        return columnCount;
    }

    public List<Order> queryOrderByPage(String hql, Integer pageNow, int pageSize, Integer userid) {
        int start  =  (pageNow-1)*pageSize;
        int end = pageSize;
        String[] parameters={userid+""};
        List<Order> list = hibernateTemplate.execute(new HibernateCallBackUtil<Order>(start,end,hql,parameters));
        return list;

    }

    public Order queryByOid(Integer oid) {
        return hibernateTemplate.load(Order.class,oid);
    }

    public void update(Order currentOrder) {
        hibernateTemplate.update(currentOrder);
    }
}
