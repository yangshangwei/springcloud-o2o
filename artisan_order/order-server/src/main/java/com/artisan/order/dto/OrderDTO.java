package com.artisan.order.dto;

import com.artisan.order.domain.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class OrderDTO {

    /**
     * 订单id.
     */
    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;

    /**
     * 买家手机号.
     */
    private String buyerPhone;

    /**
     * 买家地址.
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus;


    /**
     * 一个订单中可能有多个OrderDetail ，所以在DTO中利用集合
     */

    private List<OrderDetail> orderDetailList;
}
