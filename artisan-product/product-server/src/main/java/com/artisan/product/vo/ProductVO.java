package com.artisan.product.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    //  @JsonProperty注解用于属性上，作用是把该属性的名称序列化为另外一个名称，
    // 如把categoryName属性序列化为name
    // 【这里约定给前台返回的节点名为name, 但是为了方便理解这个name到底是什么的name,在vo中定义了方便理解的属性名】
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    // 因为这个节点下可能返回多个ProductInfoVO，因此定义一个List集合
    @JsonProperty("products")
    private List<ProductInfoVO> productInfoVOList ;

}
