package com.artisan.product.client;


import com.artisan.product.common.DecreaseStockInput;
import com.artisan.product.common.ProductOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="ARTISAN-PRODUCT")
public interface ProductClient {



    @PostMapping("/product/productListForOrder")
    List<ProductOutput> getProductForOrder(List<String> productIdList);


    /**
     * 这里我们就不用CartDTO了，因为它属于Order工程，我们这里自己在ProductCommon中自己维护一个DTO类
     * DecreaseStockInput
     * @param decreaseStockInputList
     */
    @PostMapping("/product/decreseProduct")
    void decreseProduct(List<DecreaseStockInput> decreaseStockInputList);
}
