package com.study.yeseul.product.service;


import com.study.yeseul.product.domain.Product;
import com.study.yeseul.product.vo.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void 상품등록_테스트() {
        ProductDto.ProductCreateDto productCreateDto = new ProductDto.ProductCreateDto("aaa", 100, "a cop.");
        Product product = productService.createProduct(productCreateDto);
        System.out.println(">>>>> : " + product);

        assertEquals(product.getPrice(), 100);
    }

    @Test
    public void 상품등록_할인_테스트() {
        ProductDto.ProductCreateDto productCreateDto = new ProductDto.ProductCreateDto("iPhone", 100, "a cop.");
        Product product = productService.createProduct(productCreateDto);
        System.out.println(">>>>> : " + product);

        assertEquals(product.getPrice(), 100);
    }

    @Test
    public void 상품목록조회_테스트() {
        List<ProductDto.ProductDetailDto> productList = productService.getProductList();
        System.out.println(productList);
    }

}