package com.artisan.product.service.impl;

import com.artisan.product.domain.Product;
import com.artisan.product.enums.ProductStatusEnum;
import com.artisan.product.repository.ProductRepository;
import com.artisan.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllUpProduct() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
