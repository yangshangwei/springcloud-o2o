package com.artisan.order.vo;

import lombok.Getter;

@Getter
public class ErrorCodeMsg {

    private int code;
    private String msg;

    // 异常
    public static ErrorCodeMsg SERVER_ERROR = new ErrorCodeMsg(-1, "服务端异常");


    private ErrorCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
