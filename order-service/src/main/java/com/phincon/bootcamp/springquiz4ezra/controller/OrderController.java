package com.phincon.bootcamp.springquiz4ezra.controller;

import com.phincon.bootcamp.springquiz4ezra.model.Order;

import com.phincon.bootcamp.springquiz4ezra.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    public Mono<Order> post(@RequestBody Order order)
    {
        return orderService.publish(order);
    }

    @GetMapping("/order/getOrder")
    public Flux<Order> getOrders()
    {
        return orderService.getOrder();
    }
}
