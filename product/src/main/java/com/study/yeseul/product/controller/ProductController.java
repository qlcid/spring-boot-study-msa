package com.study.yeseul.product.controller;

import com.study.yeseul.product.domain.Product;
import com.study.yeseul.product.service.ProductService;
import com.study.yeseul.product.vo.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api(tags = "상품 APIs")
public class ProductController {

    // spring 생성자 주입하는 방법이
    @Autowired
    private ProductService productService;


    @ApiOperation(value = "상품목록 조회", notes = "상품목록 조회 API입니다.")
    @GetMapping("")
    public List<ProductDto.ProductDetailDto> getProductList() {
        return productService.getProductList();
    }


    @ApiOperation(value = "상품등록", notes = "상품등록 API입니다. name에 iPhone을 입력하면 반값되는건 안비밀 ^^")
    @PostMapping("")
    public Product createProduct(@RequestBody final ProductDto.ProductCreateDto createDto) {
        return productService.createProduct(createDto);
    }


    @ApiOperation(value = "상품 조회")
    @GetMapping("/{id}")
    public ProductDto.ProductDetailDto getProduct(@PathVariable("id") final long id) {
        return productService.getProduct(id);
    }


    @ApiOperation(value = "상품 삭제")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") final long id) {
        productService.deleteProduct(id);
    }


    @ApiOperation(value = "상품 수정")
    @PutMapping("/{id}")
    public ProductDto.ProductDetailDto updateProduct(@PathVariable("id") final long id,
                                                     @RequestBody final ProductDto.ProductUpdateDto updateDto
    ) {
        return productService.updateProduct(id, updateDto);
    }

    @ApiOperation(value = "상품 주문")
    @PostMapping("/{id}/orders")
    public ProductDto.ProductOrderRes createProductOrders(@PathVariable("id") final long id,
                                                          @RequestBody ProductDto.ProductOrderReq productOrderReq) {
        return productService.createProductOrders(id, productOrderReq);
    }

}
