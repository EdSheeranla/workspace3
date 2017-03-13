package com.hjy.shop.action.admin;

import com.hjy.shop.dto.PageBean;
import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.entity.Product;
import com.hjy.shop.service.CategorySecondService;
import com.hjy.shop.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by sheeran on 2017/3/13.
 */
@Component
@Scope("prototype")
public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {
    private ProductService productService;
    private Integer pageNow;
    private Product product = new Product();
    private CategorySecondService categorySecondService;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private Integer csid;

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    @Resource
    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    @Resource
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public String list() {
        PageBean<Product> pageBean = productService.queryAllProductByPage(pageNow);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "Productlist";
    }

    public String edit() {
        Product editingProduct = productService.queryById(product.getPid());
        int cid = editingProduct.getCategorySecond().getCategory().getCid();
        List<CategorySecond> categorySecondList = categorySecondService.queryAllProductByCid(cid);
        ActionContext.getContext().getValueStack().set("editingProduct", editingProduct);
        ActionContext.getContext().getValueStack().set("categorySecondList", categorySecondList);
        return "Productedit";
    }

    /**
     * 这里要进行文件的上传操作
     */
    public String update() throws IOException {
        upload();
        productService.update(product);
        return "backProduct";
    }

    private void upload() throws IOException {
        String folder = "/products/1";
        String path = ServletActionContext.getServletContext().getRealPath(folder);
        if (uploadFileName != null) {
            StringBuffer image = new StringBuffer(folder);
            image.append("/").append(uploadFileName);
            product.setImage(image.toString());
        }
        if (upload != null) {
            File savefile = new File(new File(path), uploadFileName);
            if (!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
            FileUtils.copyFile(upload, savefile);
        }
    }

    public void validateUpdate() {
        if (uploadContentType != null) {
            System.out.println(uploadContentType);
            if (!uploadContentType.equals("image/jpeg") && !uploadContentType.equals("image/png") && !uploadContentType.equals("image/gif")) {
                this.addFieldError("type", "只能接受jpg,gif格式的图片");
            }
        }
    }
    public void validateSave() {
        if (uploadContentType != null) {
            System.out.println(uploadContentType);
            if (!uploadContentType.equals("image/jpeg") && !uploadContentType.equals("image/png") && !uploadContentType.equals("image/gif")) {
                this.addFieldError("type", "只能接受jpg,gif格式的图片");
            }
        }
    }

    public String add() {
        List<CategorySecond> categorySecondList = categorySecondService.queryAll();
        ActionContext.getContext().getValueStack().set("categorySecondList", categorySecondList);
        return "Productadd";
    }

    public String save() throws IOException {
        CategorySecond categorySecond = categorySecondService.queryByCsid(csid);
        upload();
        product.setCategorySecond(categorySecond);
        productService.save(product);
        return "backProduct";
    }

    /**
     * 这里要注意orderitem表和product表之间的外键映射关系
     */
    public String del() throws SQLException {
        productService.delete(product);
        return "backProduct";
    }

    @Override
    public Product getModel() {
        return product;
    }
}
