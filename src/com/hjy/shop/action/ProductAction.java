package com.hjy.shop.action;

import com.hjy.shop.entity.Category;
import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.CategoryService;
import com.hjy.shop.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/3/11.
 */
@Component("productAction")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
    private Product product=new Product();
    private ProductService productService;
    private Integer cid;
    private CategoryService categoryService;
    @Resource
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductService getProductService() {
        return productService;
    }
    @Resource
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public String showProduct(){
        Product queryproduct = productService.queryById(product.getPid());
        ActionContext.getContext().getValueStack().set("product",queryproduct);
        return "showProduct";
    }
    public String findAllByCid(){
        return "findAllByCid";
    }
    public Product getModel() {
        return product;
    }
}
