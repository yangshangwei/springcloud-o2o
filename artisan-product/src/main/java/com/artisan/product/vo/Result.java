package com.artisan.product.vo;

import lombok.Getter;

@Getter
public class Result<T> {

    private Integer code ;
    private String msg ;
    private T data;

    /**
     * 成功时候的调用
     * */
    public static <T> Result<T> success(T data){
        return new  Result<T>(data);
    }



    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }


    /**
     * 失败时候的调用
     * */
    public static <T> Result<T> error(ErrorCodeMsg cm){
        return new  Result<T>(cm);
    }


    private Result(ErrorCodeMsg cm) {
        if(cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }
}
