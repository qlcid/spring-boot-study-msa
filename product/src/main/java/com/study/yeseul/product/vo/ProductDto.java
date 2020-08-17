package com.study.yeseul.product.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.yeseul.product.domain.Product;
import lombok.*;

import java.time.ZonedDateTime;

public class ProductDto {

    @Getter
    @Setter
    public static class ProductCreateDto {
        private String name;
        private int price;
        private String vender;

        public ProductCreateDto() {
        }

        public ProductCreateDto(String name, int price, String vender) {
            this.name = name;
            this.price = price;
            this.vender = vender;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProductDetailDto {
        private long id;
        private String name;
        private int price;
        private String vender;

        public static ProductDetailDto valueOf(Product product) {
            return new ProductDetailDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getVender()
            );
        }

        public ProductDetailDto(long id, String name, int price, String vender) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.vender = vender;
        }
    }

    @Getter
    @Setter
    public static class ProductUpdateDto {
        private int price;
    }

    @Getter
    public static class ProductOrderReq {
        private int count;
        private String address;
        private String couponId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductOrderRes {
        private long orderId;
        private long id;
        private String address;
        @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
        private ZonedDateTime cratedAt;
        private String userName;
        private int count;

        public static ProductOrderRes valueOf(String address, OrderDto.OrderDetailDto orderDetailDto) {
            return ProductOrderRes.builder()
                    .address(address)
                    .id(orderDetailDto.getProductId())
                    .orderId(orderDetailDto.getId())
                    .cratedAt(orderDetailDto.getCratedAt())
                    .userName(orderDetailDto.getUserName())
                    .count(orderDetailDto.getCount())
                    .build();
        }
    }

}
