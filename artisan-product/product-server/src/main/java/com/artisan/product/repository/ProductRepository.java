package com.artisan.product.repository;

import com.artisan.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JpaRepository<Product, String>  第一个参数为具体的domain对象，第二个参数为主键类型
 */
public interface ProductRepository  extends JpaRepository<Product, String> {

    // 根据产品状态查询产品
    List<Product>  findByProductStatus(Integer productStatus);


    // 根据productId 查询 产品列表
    List<Product> findByProductIdIn(List<String> productIdList);


}
