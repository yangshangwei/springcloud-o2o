package com.artisan.product.service;

import com.artisan.product.domain.Product;

import java.util.List;

public interface ProductService {

    // 查询上架商品
    List<Product>   getAllUpProduct();
}
