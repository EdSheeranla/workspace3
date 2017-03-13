package com.hjy.shop.action.admin;

import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.Category;
import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.service.CategorySecondService;
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
public class AdminCategorySecondAction extends ActionSupport  implements ModelDriven<CategorySecond>{
    private CategorySecondService categorySecondService;
    private CategorySecond categorySecond=new CategorySecond();
    private Integer pageNow;
    private Integer cid;

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    private CategoryService categoryService;
    @Resource
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Resource
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public String list(){
        PageBean<CategorySecond> pageBean=categorySecondService.queryAllByPage(pageNow);
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "CategorySecondlist";
    }
    public String edit(){
        List<Category> categoryList= (List<Category>) ServletActionContext.getRequest().getSession().getAttribute("");
        if(categoryList==null||categoryList.size()==0){
            categoryList=categoryService.queryAll();
            ServletActionContext.getRequest().getSession().setAttribute("categoryList",categoryList);
        }
        CategorySecond editingCategorySecond=categorySecondService.queryByCsid(categorySecond.getCsid());
        ActionContext.getContext().getValueStack().set("editingCategorySecond",editingCategorySecond);
        return "CategorySecondedit";
    }
    public String update(){
        Category querycategory=categoryService.queryByCid(cid);

        CategorySecond currCategorySecond=categorySecondService.queryByCsid(categorySecond.getCsid());
        currCategorySecond.setCsname(categorySecond.getCsname());
        currCategorySecond.setCategory(querycategory);
        categorySecondService.update(currCategorySecond);
        return "backCategorySecond";
    }

    public String del(){
        categorySecondService.delete(categorySecond);
        return "backCategorySecond";
    }
    public String add(){
        List<Category> categoryList= (List<Category>) ServletActionContext.getRequest().getSession().getAttribute("");
        if(categoryList==null||categoryList.size()==0){
            categoryList=categoryService.queryAll();
            ServletActionContext.getRequest().getSession().setAttribute("categoryList",categoryList);
        }
        return "CategorySecondadd";
    }
    public String save(){
        Category querycategory = categoryService.queryByCid(cid);
        categorySecond.setCategory(querycategory);
        categorySecondService.add(categorySecond);
        return "backCategorySecond";
    }
    @Override
    public CategorySecond getModel() {
        return categorySecond;
    }
}
