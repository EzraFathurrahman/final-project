package com.phincon.bootcamp.springquiz4ezra.service;


import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    OrderRepository orderRepository;

    String jmsDestination="queue.order";
    @Override
    public Mono<Order> publish(Order order) {

        jmsTemplate.convertAndSend(jmsDestination,order);

        log.info("order data {}", order);

        return Mono.just(order) ;
    }

    @Override
    public Flux<Order> getOrder() {
        return orderRepository.findAll();
    }

}
