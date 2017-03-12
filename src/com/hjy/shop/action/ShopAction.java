package com.hjy.shop.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/3/8.
 */
@Component("shopAction")
@Scope("prototype")
public class ShopAction extends ActionSupport {
    public String cartPage(){
        return "cartPage";
    }
}
