package com.study.yeseul.order.controller;

import com.study.yeseul.order.domain.Order;
import com.study.yeseul.order.service.OrderService;
import com.study.yeseul.order.vo.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
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
    public Order createOrder(@RequestBody final OrderDto.OrderCreateDto createDto) {
        return orderService.createOrder(createDto);
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
