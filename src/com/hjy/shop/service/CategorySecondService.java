package com.hjy.shop.service;

import com.hjy.shop.entity.CategorySecond;
import com.hjy.shop.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2017/3/9.
 */
public interface CategorySecondService {
    List<CategorySecond> queryAllProductByCid(Integer cid);
}
