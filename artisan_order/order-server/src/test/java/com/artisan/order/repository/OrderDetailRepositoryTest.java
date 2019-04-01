package com.artisan.order.repository;

import com.artisan.order.ArtisanOrderApplicationTests;
import com.artisan.order.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class OrderDetailRepositoryTest extends ArtisanOrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1111");
        orderDetail.setOrderId("111111");
        orderDetail.setProductIcon("http://xxx.com");
        orderDetail.setProductId("22222");
        orderDetail.setProductName("拿铁");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(2);

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertTrue(result != null);
    }
}