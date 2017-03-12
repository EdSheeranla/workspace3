package com.hjy.shop.action;

import com.hjy.shop.entity.Category;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.CategorySecondService;
import com.hjy.shop.service.CategoryService;
import com.hjy.shop.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/3/8.
 */
@Component("indexAction")
@Scope("prototype")
public class IndexAction extends ActionSupport {
    private CategorySecondService categorySecondService;
    private ProductService productService;
    @Resource
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Resource
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    private CategoryService categoryService;
    @Resource
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String execute(){
        List<Category> categoryList = categoryService.queryAll();
        ServletActionContext.getRequest().getSession().setAttribute("categoryList",categoryList);
        List<Product> hotproductList=productService.queryHotProduct();
        //这里向其中valuestack中添加了热门商品的信息，
        ActionContext.getContext().getValueStack().set("hotproductList",hotproductList);
        //查询最新商品的信息
        List<Product> lastproductList=productService.queryLastProdict();
        ActionContext.getContext().getValueStack().set("lastproductList",lastproductList);
        return SUCCESS;
    }
}
