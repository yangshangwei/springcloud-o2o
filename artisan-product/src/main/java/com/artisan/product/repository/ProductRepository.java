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
}
