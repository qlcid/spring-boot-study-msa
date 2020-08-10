package com.study.yeseul.order.vo;

import com.study.yeseul.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

public class OrderDto {

    @Getter
    public static class OrderCreateDto {
        private long productId;
        private String userName;
        private int count;
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
