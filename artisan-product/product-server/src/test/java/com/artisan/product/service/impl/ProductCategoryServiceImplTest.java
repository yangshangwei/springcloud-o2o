package com.artisan.product.service.impl;

import com.artisan.product.ArtisanProductApplicationTests;
import com.artisan.product.domain.ProductCategory;
import com.artisan.product.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductCategoryServiceImplTest extends ArtisanProductApplicationTests {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryTypeIn() {

       List<ProductCategory> list =  productCategoryRepository.findByCategoryTypeIn(Arrays.asList(99,98,97));
        Assert.assertEquals(3,list.size());

    }
}