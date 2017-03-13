package com.hjy.shop.util;

import com.hjy.shop.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import java.util.List;

/**
 * Created by admin on 2017/3/11.
 */
public class HibernateCallBackUtil<T> implements HibernateCallback<List<T>> {
    private String hql;
    private Integer start;
    private Integer end;
    private String[] parameters;

    public HibernateCallBackUtil(Integer start, Integer end, String hql, String[] parameters) {
        this.hql = hql;
        this.start = start;
        this.end = end;
        this.parameters = parameters;
    }

    public List<T> doInHibernate(Session session) throws HibernateException {
        Query query = session.createQuery(hql);
        query.setMaxResults(end);
        query.setFirstResult(start);
        if(parameters!=null){
            for (int i = 0; i < parameters.length; i++) {
                query.setString(i, parameters[i]);
            }
        }
        return query.list();
    }
}

