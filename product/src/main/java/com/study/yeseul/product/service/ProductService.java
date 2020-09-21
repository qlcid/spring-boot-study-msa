package com.study.yeseul.product.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.study.yeseul.product.dao.ProductRepository;
import com.study.yeseul.product.domain.Product;
import com.study.yeseul.product.exception.ProductNotFoundException;
import com.study.yeseul.product.vo.OrderDto;
import com.study.yeseul.product.vo.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RestTemplate orderRestTemplate;


    public List<ProductDto.ProductDetailDto> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productList.stream()
                .map(ProductDto.ProductDetailDto::valueOf)
                .collect(Collectors.toList());
    }

    public Product createProduct(final ProductDto.ProductCreateDto createDto) {
        if ("iPhone".equals(createDto.getName())) {
            createDto.setPrice((int) (createDto.getPrice() * 0.5));
        }

        return productRepository.save(Product.valueOf(createDto));
    }

    public ProductDto.ProductDetailDto getProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductDto.ProductDetailDto.valueOf(product);
    }

    public void deleteProduct(final long id) {
        ProductDto.ProductDetailDto productDetailDto = getProduct(id);
        productRepository.deleteById(productDetailDto.getId());
    }

    public ProductDto.ProductDetailDto updateProduct(final long id, final ProductDto.ProductUpdateDto updateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        product.setPrice(updateDto.getPrice());
        productRepository.save(product);

        return ProductDto.ProductDetailDto.valueOf(product);
    }

    @HystrixCommand(fallbackMethod = "createOrdersFallback")
    public ProductDto.ProductOrderRes createProductOrders(final long id, final ProductDto.ProductOrderReq productOrderReq) {
        log.info(">>> 2. Product Service");
        // 1. 상품이 존재하는지 확인한다.
        ProductDto.ProductDetailDto productDetailDto = getProduct(id);

        // todo : 2. 사용자가 쿠폰을 가지고 있는지 확인한다.

        // 3. 주문 API를 호출한다.createOrdersFallback
        OrderDto.OrderDetailDto orderDetailDto = createOrders(id, productOrderReq);
        if (orderDetailDto == null) {
            log.error(">>>>> Order API 호출 Error!!!");
            return null;
        }

        // 4. 결과값을 파싱 후 반환한다.
        ProductDto.ProductOrderRes productOrderRes = ProductDto.ProductOrderRes.valueOf(productOrderReq.getAddress(), orderDetailDto);
        return productOrderRes;
    }


    public OrderDto.OrderDetailDto createOrders(final long id, final ProductDto.ProductOrderReq productOrderReq) {
        log.info(">>> 3. Order 상품주문 API 호출");
        OrderDto.OrderCreateReq orderCreateReq = new OrderDto.OrderCreateReq(id, "yeseul", productOrderReq.getCount());
        ResponseEntity<OrderDto.OrderDetailDto> response = orderRestTemplate.postForEntity("/orders", orderCreateReq, OrderDto.OrderDetailDto.class);

        return response.getBody();
    }

    public ProductDto.ProductOrderRes createOrdersFallback(final long id, final ProductDto.ProductOrderReq productOrderReq, Throwable t) {
        throw new RuntimeException("서킷브레이커 에러!!");
    }

}
