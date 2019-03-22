package com.artisan.product.service;

import com.artisan.product.ArtisanProductApplicationTests;
import com.artisan.product.domain.Product;
import com.artisan.product.enums.ProductStatusEnum;
import com.artisan.product.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;


@Component
public class ProductServiceTest extends ArtisanProductApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getAllUpProduct() {
        List<Product> list =  productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        Assert.assertEquals(3,list.size());
    }
}