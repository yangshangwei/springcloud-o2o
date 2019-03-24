package com.artisan.product.controller;

import com.artisan.product.domain.Product;
import com.artisan.product.domain.ProductCategory;
import com.artisan.product.dto.CartDTO;
import com.artisan.product.service.ProductCategoryService;
import com.artisan.product.service.ProductService;
import com.artisan.product.vo.ProductInfoVO;
import com.artisan.product.vo.ProductVO;
import com.artisan.product.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService categoryService;


    @GetMapping("/list")
    private Result list() {

        //1. 查询所有在架的商品
        List<Product> productInfoList = productService.getAllUpProduct();

        //2. 获取类目type列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(Product::getCategoryType)
                .collect(Collectors.toList());

        //3. 从数据库查询类目
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4. 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            // 设置属性
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            // ProductInfoVO 集合
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (Product product : productInfoList) {
                // 挂到对应的的categoryType下
                if (product.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    // 将属性copy到productInfoVO，避免逐个属性set，更简洁
                    BeanUtils.copyProperties(product, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return Result.success(productVOList);
    }


    /**
     * 根据productIdList 查询商品列表
     * 提供给Order微服务用
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/productListForOrder")
    private List<Product> getProductForOrder(@RequestBody List<String> productIdList) {
        return productService.getProductList(productIdList);
    }


    /**
     * 扣减库存
     * 提供给Order微服务用
     *
     * @param CartDTOList
     * @return
     */
    @PostMapping("/decreseProduct")
    private void  decreseProduct(@RequestBody List<CartDTO> CartDTOList) {
        productService.decreaseProduct(CartDTOList);
    }



}
