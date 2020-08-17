package com.study.yeseul.order.service;

import com.study.yeseul.order.dao.OrderRepository;
import com.study.yeseul.order.domain.Order;
import com.study.yeseul.order.exception.OrderNotFoundException;
import com.study.yeseul.order.vo.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDto.OrderDetailDto> getOrderList() {
        List<Order> orderList = orderRepository.findAll();

        return orderList.stream()
                .map(OrderDto.OrderDetailDto::valueOf)
                .collect(Collectors.toList());
    }

    public OrderDto.OrderCreateRes createOrder(final OrderDto.OrderCreateReq createDto) {
        Order order = orderRepository.save(Order.valueOf(createDto));
        return OrderDto.OrderCreateRes.valueOf(order);
    }

    public OrderDto.OrderDetailDto getOrder(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return OrderDto.OrderDetailDto.valueOf(order);
    }

    public void deleteOrder(final long id) {
        orderRepository.deleteById(id);
    }
}
