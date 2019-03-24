package com.artisan.product.service;

import com.artisan.product.domain.Product;
import com.artisan.product.dto.CartDTO;

import java.util.List;

public interface ProductService {

    // 查询上架商品
    List<Product>   getAllUpProduct();

    // 根据Id查询商品信息
    List<Product> getProductList(List<String> productIdList);

    // 扣减库存
    void decreaseProduct(List<CartDTO> cartDTOList);

}
