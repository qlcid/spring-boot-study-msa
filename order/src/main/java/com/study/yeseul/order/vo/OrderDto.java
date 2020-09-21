package com.study.yeseul.order.vo;

import com.study.yeseul.order.domain.Order;
import lombok.*;

import java.time.ZonedDateTime;

public class OrderDto {

    @Getter
    @ToString
    public static class OrderCreateReq {
        private long productId;
        private String userName;
        private int count;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderCreateRes {
        private long id;
        private long productId;
        private ZonedDateTime cratedAt;
        private String userName;
        private int count;

        public static OrderCreateRes valueOf(Order order) {
            return OrderCreateRes.builder()
                    .id(order.getId())
                    .productId(order.getProductId())
                    .cratedAt(order.getCratedAt())
                    .userName(order.getUserName())
                    .count(order.getCount())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class OrderDetailDto {
        private long id;
        private long productId;
        private ZonedDateTime createdAt;
        private String userName;
        private int count;

        public static OrderDetailDto valueOf(Order order) {
            return new OrderDetailDto(
                    order.getId(),
                    order.getProductId(),
                    order.getCratedAt(),
                    order.getUserName(),
                    order.getCount()
            );
        }
    }
}
