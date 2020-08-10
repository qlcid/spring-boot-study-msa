package com.study.yeseul.order.exception;

import com.study.yeseul.error.exception.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {

    public OrderNotFoundException(Long id) {
        super(id + " is not found");
    }
}
