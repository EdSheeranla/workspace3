package com.hjy.shop.dto;
import java.util.List;

/**
 * Created by admin on 2017/3/11.
 * 这个pagebean对象使用来分页显示
 * 而且这里使用了泛型，便于对所有的对象进行分页操作
 */
public class PageBean<T> {
    private Integer pageNow;
    private Integer pageSize;
    private Integer pageCount;
    private Integer columnCount;
    private List<T> list;

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(Integer columnCount) {
        this.columnCount = columnCount;
    }

    public java.util.List<T> getList() {
        return list;
    }

    public void setList(java.util.List<T> list) {
        this.list = list;
    }
}
