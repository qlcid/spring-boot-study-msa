package com.study.yeseul.product.exception;

import com.study.yeseul.error.exception.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(Long id) { super(id + " is not found"); }
}
