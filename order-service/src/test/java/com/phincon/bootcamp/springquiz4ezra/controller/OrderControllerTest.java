package com.phincon.bootcamp.springquiz4ezra.controller;

import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.service.OrderService;
import com.phincon.bootcamp.springquiz4ezra.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderServiceImpl orderService;

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private OrderController controller;

    WebTestClient testClient;

    @Test
    void testPost() {

        Order order = new Order("1", "Some product", "10.99", "PENDING", "Registration-1", null,null);
        when(orderService.publish(any())).thenReturn(Mono.just(order));

        Mono<Order> result = controller.post(order);

        StepVerifier.create(result)
                .expectNext(order)
                .verifyComplete();
    }

    @Test
    void testGetOrders() {

        Order order1 = new Order("1", "Product1", "10.99", "PENDING", "Registration-1", null, null);
        Order order2 = new Order("2", "Product2", "20.99", "PENDING", "Registration-2", null, null);
        List<Order> sampleOrders = Arrays.asList(order1, order2);

        when(orderService.getOrder()).thenReturn(Flux.fromIterable(sampleOrders));


        Flux<Order> result = controller.getOrders();

        verify(orderService).getOrder();

        StepVerifier.create(result)
                .expectNextSequence(sampleOrders)
                .verifyComplete();
    }



}


