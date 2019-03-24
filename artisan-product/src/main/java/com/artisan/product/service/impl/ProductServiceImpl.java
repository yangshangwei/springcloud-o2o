package com.artisan.product.service.impl;

import com.artisan.product.domain.Product;
import com.artisan.product.dto.CartDTO;
import com.artisan.product.enums.ProductStatusEnum;
import com.artisan.product.enums.ResultEnum;
import com.artisan.product.exceptions.ProductException;
import com.artisan.product.repository.ProductRepository;
import com.artisan.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllUpProduct() {
        return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<Product> getProductList(List<String> productIdList) {
        return productRepository.findByProductIdIn(productIdList);
    }

    @Override
    // 因为是对List操作，所以加个事务控制
    @Transactional
    public void decreaseProduct(List<CartDTO> cartDTOList) {

        // 遍历CartDTO
        for (CartDTO cartDTO : cartDTOList) {
            // 根据productId查询Product
            Optional<Product> productOptional = productRepository.findById(cartDTO.getProductId());

            // 商品是否存在
            if (!productOptional.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 是否库存充足

            Product product = productOptional.get();
            int leftStock = product.getProductStock() - cartDTO.getProductQuantity();

            if (leftStock < 0 ){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            // 将剩余库存设置到product,并更新数据库
            product.setProductStock(leftStock);
            productRepository.save(product);
        }

    }
}
