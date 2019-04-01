package com.artisan.product.repository;

import com.artisan.product.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void findByProductStatus() {

       List<Product> list =  productRepository.findByProductStatus(0);

        Assert.assertEquals(3,list.size());
    }

    @Test
    public void findByProductIdIn() {
        List<Product> list =  productRepository.findByProductIdIn(Arrays.asList("1","2"));

        Assert.assertEquals(2,list.size());

    }
}