package com.artisan.order.service.impl;

import com.artisan.order.domain.Order;
import com.artisan.order.dto.OrderDTO;
import com.artisan.order.enums.OrderStatusEnum;
import com.artisan.order.enums.PayStatusEnum;
import com.artisan.order.repository.OrderDetailRepository;
import com.artisan.order.repository.OrderRepository;
import com.artisan.order.service.OrderService;
import com.artisan.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        // TODO 查询商品信息（调用商品微服务）
        // TODO  计算订单总价
        // TODO  扣减库存（调用商品微服务）


        //订单入库
        Order order = new Order();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        // 复制属性
        BeanUtils.copyProperties(orderDTO, order);
        // 设置其他属性
        order.setOrderAmount(new BigDecimal("100")); // TODO 后需要修改
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderRepository.save(order);

        return orderDTO;
    }
}
