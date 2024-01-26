package com.phincon.bootcamp.springquiz4ezra.service;

import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.repository.OrderRepository;
import jakarta.jms.Destination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceImplTest {
    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    OrderServiceImpl orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPublish() {
        Order order = new Order("1",null,null,null,"Registration-crm",null,null);

        Mono<Order> result = orderService.publish(order);


        verify(jmsTemplate).convertAndSend(anyString(), eq(order));

        result.as(StepVerifier::create)
                .expectNext(order)
                .expectComplete()
                .verify();
    }

    @Test
    public void testGetOrder() {

        Order order1 = new Order("1",null,null,null,"Registration-crm",null,null);
        Order order2 = new Order("1",null,null,null,"Registration-crm",null,null);
        List<Order> orders = Arrays.asList(order1, order2);

        when(orderRepository.findAll()).thenReturn(Flux.fromIterable(orders));

        Flux<Order> result = orderService.getOrder();

        verify(orderRepository).findAll();

        result.as(StepVerifier::create)
                .expectNextSequence(orders)
                .expectComplete()
                .verify();
    }



}