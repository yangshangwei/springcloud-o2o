package com.artisan.product.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Table(name = "product_category")
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String categoryId;

    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;


}
