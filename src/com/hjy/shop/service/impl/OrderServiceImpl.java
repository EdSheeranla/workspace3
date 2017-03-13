package com.hjy.shop.service.impl;

import com.hjy.shop.dao.OrderDao;
import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.Order;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sheeran on 2017/3/12.
 */
@Component("orderServiceImpl")
@Transactional
public class OrderServiceImpl implements OrderService{
    private PageBean<Order> pageBean=new PageBean<Order>();
    private int pageSize=3;
    private int pageCount = 0;
    private int columnCout = 0;
    private String hql="";

    private OrderDao orderDao;
    @Resource
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void save(Order order) {
        orderDao.add(order);
    }

    //根据用户id分页查询订单
    @Override
    public PageBean<Order> showOrderByPage(Integer pageNow, Integer userid) {
        hql="select count(*) from Order o where o.user.userid=?";
        setPageBean(pageNow,userid);
        hql="from Order o  where o.user.userid = ? order by ordertime desc";
        List<Order> orderList=orderDao.queryOrderByPage(hql,pageNow,pageSize,userid);
        pageBean.setList(orderList);
        return pageBean;
    }

    @Override
    public Order queryByOid(Integer oid) {
        return orderDao.queryByOid(oid);
    }

    @Override
    public Order update(Order order) {
        Order currentOrder=orderDao.queryByOid(order.getOid());
        currentOrder.setName(order.getName());
        currentOrder.setTelnum(order.getTelnum());
        currentOrder.setAddress(order.getAddress());
        orderDao.update(currentOrder);
        return currentOrder;
    }

    private void setPageBean(int pageNow,int id){
        pageBean.setPageNow(pageNow);
        columnCout = orderDao.queryPageCount(hql,id);
        pageCount = (columnCout - 1)/pageSize+1;
        pageBean.setColumnCount(columnCout);
        pageBean.setPageCount(pageCount);
    }
}
