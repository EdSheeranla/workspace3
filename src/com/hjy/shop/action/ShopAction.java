package com.hjy.shop.action;

import com.hjy.shop.dto.CartItem;
import com.hjy.shop.dto.ShoppingCart;
import com.hjy.shop.entity.Product;
import com.hjy.shop.entity.User;
import com.hjy.shop.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by admin on 2017/3/8.
 */
@Component("shopAction")
@Scope("prototype")
public class ShopAction extends ActionSupport {
    //这里调用了peoductdao中方法来对商品进行查询
    private ProductService productService;
    private Integer pid;
    private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    @Resource
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 跳转到购物车界面
     */
    public String cartPage(){
        return "cartPage";
    }

    /**
     * 注意这里应该将购物车和用户进行绑定，避免出现不同用户使用同一个购物车的情况
     * 可以在用户登录的时候给用户分配一个购物车，当用户没有登陆便跳转到登陆界面，
     * 已经登陆了便可以添加购物车
     */
    public String add(){
        ShoppingCart shoppingCart= (ShoppingCart) ServletActionContext.getRequest().getSession().getAttribute("shoppingCart");
        if(shoppingCart==null){
            shoppingCart=new ShoppingCart();
            ServletActionContext.getRequest().getSession().setAttribute("shoppingCart",shoppingCart);
        }
        Product product=productService.queryById(pid);
        CartItem cartItem=new CartItem(product,count);
        shoppingCart.add(cartItem);
        return SUCCESS;
    }
    public String remove(){
        ShoppingCart shoppingCart= (ShoppingCart) ServletActionContext.getRequest().getSession().getAttribute("shoppingCart");
        shoppingCart.remove(pid);
        return SUCCESS;
    }
    public String clear(){
        ShoppingCart shoppingCart= (ShoppingCart) ServletActionContext.getRequest().getSession().getAttribute("shoppingCart");
        shoppingCart.clear();
        return SUCCESS;
    }

}
