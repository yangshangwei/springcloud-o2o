package com.artisan.product.service;

import com.artisan.product.common.DecreaseStockInput;
import com.artisan.product.common.ProductOutput;
import com.artisan.product.domain.Product;

import java.util.List;

public interface ProductService {

    // 查询上架商品
    List<Product>   getAllUpProduct();

    // 根据Id查询商品信息
    List<ProductOutput> getProductList(List<String> productIdList);

    // 扣减库存
    void decreaseProduct(List<DecreaseStockInput> decreaseStockInputList);

}
