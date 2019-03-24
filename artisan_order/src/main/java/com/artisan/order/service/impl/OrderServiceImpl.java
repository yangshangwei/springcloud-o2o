package com.artisan.order.service.impl;

import com.artisan.order.client.ProductClient;
import com.artisan.order.domain.Order;
import com.artisan.order.domain.OrderDetail;
import com.artisan.order.domain.Product;
import com.artisan.order.dto.CartDTO;
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
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        //  查询商品信息（调用商品微服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<Product> productList = productClient.getProductForOrder(productIdList);


        //   计算订单总价

        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            for (Product product: productList) {
                if (product.getProductId().equals(orderDetail.getProductId())) {
                    //单价*数量
                    orderAmout = product.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(product, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣减库存（调用商品微服务）

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreseProduct(cartDTOList);

        //订单入库
        Order order = new Order();
        orderDTO.setOrderId(orderId);
        // 复制属性
        BeanUtils.copyProperties(orderDTO, order);
        // 设置其他属性
        order.setOrderAmount(orderAmout);
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderRepository.save(order);

        return orderDTO;
    }
}
