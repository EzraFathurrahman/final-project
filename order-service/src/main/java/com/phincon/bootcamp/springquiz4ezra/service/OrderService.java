package com.phincon.bootcamp.springquiz4ezra.service;

import com.phincon.bootcamp.springquiz4ezra.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {


    Mono<Order> publish(Order order);

    Flux<Order> getOrder();



}
