package com.artisan.order.repository;

import com.artisan.order.ArtisanOrderApplicationTests;
import com.artisan.order.domain.Order;
import com.artisan.order.enums.OrderStatusEnum;
import com.artisan.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class OrderRepositoryTest  extends ArtisanOrderApplicationTests {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testSave(){

        Order order = new Order();
        order.setOrderId("1222");
        order.setBuyerName("artisan");
        order.setBuyerPhone("123445664");
        order.setBuyerAddress("Artisan Tech");
        order.setBuyerOpenid("11112233");
        order.setOrderAmount(new BigDecimal(3.9));
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.WAIT.getCode());

        Order result = orderRepository.save(order);
        Assert.assertNotNull(result);
    }
}