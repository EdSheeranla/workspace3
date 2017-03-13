package com.hjy.shop.action.admin;

import com.hjy.shop.entity.Category;
import com.hjy.shop.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sheeran on 2017/3/13.
 */
@Component
@Scope("prototype")
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
    private Category category=new Category();
    private CategoryService categoryService;
    @Resource
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    public String list(){
        List<Category> categoryList=categoryService.queryAll();
        ServletActionContext.getRequest().getSession().setAttribute("categoryList",categoryList);
        return  "Categorylist";
    }
    public String edit(){
        Category editingCategory=categoryService.queryByCid(category.getCid());
        ActionContext.getContext().getValueStack().set("editingCategory",editingCategory);
        return "Categoryedit";
    }
    public String update(){
        Category currentCategory=categoryService.queryByCid(category.getCid());
        currentCategory.setCname(category.getCname());
        categoryService.update(currentCategory);
        return "back";
    }
    public String del(){
        categoryService.deleteByCid(category.getCid());
        return "back";
    }
    public String add(){
        categoryService.add(category);
        return "back";
    }
    @Override
    public Category getModel() {
        return category;
    }
}
