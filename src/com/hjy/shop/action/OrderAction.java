package com.hjy.shop.action;

import com.hjy.shop.dto.CartItem;
import com.hjy.shop.dto.PageBean;
import com.hjy.shop.dto.ShoppingCart;
import com.hjy.shop.entity.Order;
import com.hjy.shop.entity.OrderItem;
import com.hjy.shop.entity.User;
import com.hjy.shop.service.OrderService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Date;

import static org.apache.struts2.ServletActionContext.*;

/**
 * Created by sheeran on 2017/3/12.
 * 创建一个action用来处理各种订单操作
 */
@Component
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
    private OrderService orderService;
    private Order order=new Order();
    private Integer pageNow;

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    @Resource
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 这里需要进行一个用户是否登陆的判断
     */
    public String goOrderPage() {
        User user = (User) getRequest().getSession().getAttribute("loginUser");
        if(user==null){
            this.addActionError("请先登陆");
            return "goLoginPage";
        }
        createOrder();
        orderService.save(order);
        ActionContext.getContext().getValueStack().set("order",order);
        return "goOrderPage";
    }

    /**
     * 这里进行提交订单的操作
     */
    public String submit(){

        return "submit";
    }

    /**
     * 显示我的订单
     */
    public String showMyOrder(){
        User user = (User) getRequest().getSession().getAttribute("loginUser");
        PageBean<Order> pageBean=orderService.showOrderByPage(pageNow,user.getUserid());
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        for (Order order:pageBean.getList()) {
            System.out.println(order.getOid());
        }
        return "showMyOrder";
    }

    /**
     * 封装一个订单对象
     */
    private void createOrder(){
        ShoppingCart shoppingCart= (ShoppingCart) ServletActionContext.getRequest().getSession().getAttribute("shoppingCart");
        if(isEmpty(shoppingCart)){
            this.addActionError("购物车中商品为空!");
        }
        User user = (User) getRequest().getSession().getAttribute("loginUser");
        order.setName(user.getUsername());
        order.setTelnum(user.getTelnum());
        order.setAddress(user.getAddress());
        order.setOrdertime(new Date());
        order.setUser(user);
        order.setState(1);
        order.setTotal(shoppingCart.getTotalPrice());
        for (CartItem cartItem:shoppingCart.getCartItems()) {
            OrderItem orderItem= new OrderItem();
            orderItem.setCount(cartItem.getCount());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setOrder(order);

            order.getOrderItemSet().add(orderItem);
        }

    }

    private boolean isEmpty(ShoppingCart shoppingCart){
        if(shoppingCart==null||shoppingCart.getCartItems().size()==0){
            return true;
        }
        return false;
    }

    @Override
    public Order getModel() {
        return order;
    }
}
