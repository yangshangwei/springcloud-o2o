package com.artisan.order.domain;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

// lombok
@Data
// @Table指定这个实体对应数据库的表名 ,符合驼峰命名的 可以省略不写
@Table(name = "product")
// @Entity表示这个类是一个实体类
@Entity
public class Product {

    // @Id标识主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;

    private String productName;
    private Integer productStock;
    private BigDecimal productPrice;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;


}
