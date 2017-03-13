package com.hjy.shop.action;

import com.hjy.shop.dto.CartItem;
import com.hjy.shop.dto.PageBean;
import com.hjy.shop.dto.ShoppingCart;
import com.hjy.shop.entity.Order;
import com.hjy.shop.entity.OrderItem;
import com.hjy.shop.entity.User;
import com.hjy.shop.service.OrderService;
import com.hjy.shop.util.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.io.IOException;
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
    private String pd_FrpId;

    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }

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
    public String goPay(){
        Order payOrder=orderService.queryByOid(order.getOid());
        ActionContext.getContext().getValueStack().set("order",payOrder);
        return "goPay";
    }
    /**
     * 这里进行订单付款的操作
     */
    public String submit() throws IOException {
        //先将订单中信息进行修改
        Order currentOrder=orderService.update(order);
        String p0_Cmd="Buy";
        String p1_MerId="10001126856";
        String p2_Order=currentOrder.getOid().toString();
        String p3_Amt="0.01";
        String p4_Cur="CNY";
        String p5_Pid="";
        String p6_Pcat="";
        String p7_Pdesc="";
        String p8_Url="http://localhost:8080/shop1/order_callback.action";
        String p9_SAF="";
        String pa_MP="";
        String pr_NeedResponse="1";
        String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac=PaymentUtil.buildHmac(p0_Cmd,p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,p8_Url,p9_SAF,pa_MP,pd_FrpId,pr_NeedResponse,keyValue);


        StringBuffer sb=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);

        ServletActionContext.getResponse().sendRedirect(sb.toString());
        return NONE;
    }

    /**
     * 显示我的订单
     */
    public String showMyOrder(){
        User user = (User) getRequest().getSession().getAttribute("loginUser");
        PageBean<Order> pageBean=orderService.showOrderByPage(pageNow,user.getUserid());
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
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
