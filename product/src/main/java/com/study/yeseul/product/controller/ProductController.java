package com.study.yeseul.product.controller;

import com.study.yeseul.product.domain.Product;
import com.study.yeseul.product.service.ProductService;
import com.study.yeseul.product.vo.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    // spring 생성자 주입하는 방법이
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<ProductDto.ProductDetailDto> getProductList() {
        return productService.getProductList();
    }

    // 등록(생성)
    @PostMapping("")
    public Product createProduct(@RequestBody final ProductDto.ProductCreateDto createDto) {
        return productService.createProduct(createDto);
    }

    // 조회
    @GetMapping("/{id}")
    public ProductDto.ProductDetailDto getProduct(@PathVariable("id") final long id) {
        return productService.getProduct(id);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") final long id) {
        productService.deleteProduct(id);
        System.out.println("삭제 OK!");
    }

    // 업데이트
    @PutMapping("/{id}")
    public ProductDto.ProductDetailDto updateProduct(@PathVariable("id") final long id,
                                                     @RequestBody final ProductDto.ProductUpdateDto updateDto
    ) {
        return productService.updateProduct(id, updateDto);
    }

}
