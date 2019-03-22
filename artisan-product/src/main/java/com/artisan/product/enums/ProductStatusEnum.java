package com.artisan.product.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {

    UP(0,"上架"),
    DOWN(1,"下架");

    private int code ;
    private String msg;

    ProductStatusEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
