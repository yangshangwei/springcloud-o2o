package com.artisan.product.service.impl;

import com.artisan.product.common.DecreaseStockInput;
import com.artisan.product.common.ProductOutput;
import com.artisan.product.domain.Product;
import com.artisan.product.enums.ProductStatusEnum;
import com.artisan.product.enums.ResultEnum;
import com.artisan.product.exceptions.ProductException;
import com.artisan.product.repository.ProductRepository;
import com.artisan.product.service.ProductService;
import com.artisan.product.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<Product> getAllUpProduct() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductOutput> getProductList(List<String> productIdList) {
        return productRepository.findByProductIdIn(productIdList).stream()
                .map(e -> {
                    ProductOutput productOutput = new ProductOutput();
                    BeanUtils.copyProperties(e, productOutput);
                    return productOutput;
                }).collect(Collectors.toList());
    }

    @Override
    public void decreaseProduct(List<DecreaseStockInput> decreaseStockInputList) {

        List<Product> productList = operateProducts(decreaseStockInputList);


        List<ProductOutput> productOutputList = productList.stream().map(e -> {
            ProductOutput productOutput = new ProductOutput();
            BeanUtils.copyProperties(e, productOutput);
            return productOutput;
        }).collect(Collectors.toList());

        // 发送消息队列
        amqpTemplate.convertAndSend("productOutput", JsonUtil.toJson(productOutputList));
        log.info("发送消息到MQ,内容为:{}", JsonUtil.toJson(productOutputList));
    }

    // 因为是对List操作，所以加个事务控制
    @Transactional
    public List<Product> operateProducts(List<DecreaseStockInput> decreaseStockInputList) {

        List<Product> productList = new ArrayList<>();

        // 遍历DecreaseStockInput
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            // 根据productId查询Product
            Optional<Product> productOptional = productRepository.findById(decreaseStockInput.getProductId());

            // 商品是否存在
            if (!productOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 是否库存充足

            Product product = productOptional.get();
            int leftStock = product.getProductStock() - decreaseStockInput.getProductQuantity();

            if (leftStock < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            // 将剩余库存设置到product,并更新数据库
            product.setProductStock(leftStock);
            productRepository.save(product);

            productList.add(product);

        }
        return productList;
    }
}
