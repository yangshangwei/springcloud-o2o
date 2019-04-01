package com.artisan.order.controller;

import com.artisan.order.domain.Product;
import com.artisan.order.dto.CartDTO;
import com.artisan.product.client.ProductClient;
import com.artisan.product.common.DecreaseStockInput;
import com.artisan.product.common.ProductOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
public class FeginClientController {

    @Autowired
    private ProductClient productClient;

//    @GetMapping("/getServerInfoByFeign")
//    public String requestServer() {
//
//        String msg = productClient.getServerInfo();
//
//        log.info("msg from server : {}", msg);
//        return msg;
//    }

    @GetMapping("/getProductList")
    public String getProductList() {

        List<ProductOutput> productList = productClient.getProductForOrder
                (Arrays.asList("1", "2"));

        log.info("productList : {}", productList);
        return "获取成功，详见日志";
    }



    @GetMapping("/decreseProduct")
    public String decreseProduct() {

        DecreaseStockInput decreaseStockInput = new DecreaseStockInput();
        decreaseStockInput.setProductId("3");
        decreaseStockInput.setProductQuantity(2);

        productClient.decreseProduct(Arrays.asList(decreaseStockInput));

        return "扣减成功，查询日志和库表数据";
    }

}
