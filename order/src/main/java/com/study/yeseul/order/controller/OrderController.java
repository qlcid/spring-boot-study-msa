package com.study.yeseul.order.controller;

import com.study.yeseul.order.service.OrderService;
import com.study.yeseul.order.vo.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 주문 목록 조회
    @GetMapping("")
    public List<OrderDto.OrderDetailDto> getOrderList() {
        return orderService.getOrderList();
    }

    // 주문 생성
    @PostMapping("")
    public OrderDto.OrderCreateRes createOrder(@RequestBody final OrderDto.OrderCreateReq createDto) {
        log.info(">>> 입력값 : {}", createDto);
        OrderDto.OrderCreateRes orderCreateRes = orderService.createOrder(createDto);
        return orderCreateRes;
    }

    // 주문 상세 조회
    @GetMapping("/{id}")
    public OrderDto.OrderDetailDto getOrder(@PathVariable("id") final long id) {
        return orderService.getOrder(id);
    }

    // 주문 삭제
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") final long id) {
        orderService.deleteOrder(id);
    }
}
