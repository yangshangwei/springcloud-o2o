package com.artisan.order.service;

import com.artisan.order.dto.OrderDTO;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);
}
