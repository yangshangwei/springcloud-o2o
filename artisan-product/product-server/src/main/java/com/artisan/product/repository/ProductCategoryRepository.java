package com.artisan.product.repository;

import com.artisan.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
