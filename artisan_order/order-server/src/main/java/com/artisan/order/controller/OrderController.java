package com.artisan.order.controller;

import com.artisan.order.converter.OrderForm2OrderDTOConverter;
import com.artisan.order.dto.OrderDTO;
import com.artisan.order.enums.ResultEnum;
import com.artisan.order.exception.OrderException;
import com.artisan.order.form.OrderForm;
import com.artisan.order.service.OrderService;
import com.artisan.order.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public Result create(@Valid  OrderForm orderForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            log.error("【Create Order】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【Create Order】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.createOrder(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return Result.success(map);
    }

}
