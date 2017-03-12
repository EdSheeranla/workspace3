package com.hjy.shop.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 2017/3/9.
 */
@Entity(name = "CategorySecond")
public class CategorySecond {
    private Integer csid;
    private String csname;
    private Category category;
    private Set<Product> productSet=new HashSet<Product>();


    @OneToMany(cascade=CascadeType.ALL,mappedBy = "categorySecond")
    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "increment")
    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "cid")
    public Category getCategory() {
        return category;
    }
}
