package com.study.yeseul.product.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

public class OrderDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderCreateReq {
        private long productId;
        private String userName;
        private int count;
    }

    @Getter
    public static class OrderDetailDto {
        private long id;
        private long productId;
        private ZonedDateTime cratedAt;
        private String userName;
        private int count;
    }

}
