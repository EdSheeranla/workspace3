package com.hjy.shop.action;

import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.Category;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.CategorySecondService;
import com.hjy.shop.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/3/11.
 */
@Component
@Scope("prototype")
public class CategorySecondAction extends ActionSupport implements ModelDriven {
    private CategorySecondService categorySecondService;
    private Category category = new Category();
    private ProductService productService;
    private int csid;
    private Integer pageNow;

    public void setCsid(int csid) {
        this.csid = csid;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    @Resource
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Resource
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    public String execute() {
        PageBean<Product> pageBean = productService.queryProductByPage(pageNow, category.getCid());
        ActionContext.getContext().getValueStack().set("cid", category.getCid());
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return SUCCESS;
    }

    public String findByCsid() {
        System.out.println(pageNow);
        PageBean<Product> pageBean = productService.queryProductByPageCsid(pageNow, csid);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return category;
    }
}
