package com.artisan.order.client;

import com.artisan.order.domain.Product;
import com.artisan.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// name为注册在注册中心上的名称
@FeignClient(name="ARTISAN-PRODUCT")
public interface ProductClient {

    // ARTISAN-PRODUCT微服务接口的访问路径
    @GetMapping("/product/serverMsg")
    String getServerInfo();


    @PostMapping("/product/productListForOrder")
    List<Product> getProductForOrder(List<String> productIdList);


    @PostMapping("/product/decreseProduct")
    void decreseProduct(List<CartDTO> CartDTOList);


}
