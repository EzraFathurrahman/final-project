package com.phincon.bootcamp.springquiz4ezra.service;

import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.model.Response;
import com.phincon.bootcamp.springquiz4ezra.repository.OrderRepository;
import com.phincon.bootcamp.springquiz4ezra.repository.TrxRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringBootTest
class ConsumerServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private TrxRepository trxRepository;

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private ConsumerService consumerService;

    @Test
    public void testSaveUser_Success() {
        // Mock data
        Order mockOrder = new Order();
        mockOrder.setId("123");
        mockOrder.setProduct("MockProduct");
        mockOrder.setPrice("100");
        mockOrder.setStatus("success");
        mockOrder.setTrxid("456");

        Message<Order> mockMessage = mock(Message.class);
        when(mockMessage.getPayload()).thenReturn(mockOrder);


        when(orderRepository.save(any(Order.class))).thenReturn(Mono.just(mockOrder));
        when(trxRepository.savenew(anyString(), anyString(), anyString(), anyString())).thenReturn(Mono.empty());


        consumerService.saveUser(mockMessage);

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(jmsTemplate, times(1)).convertAndSend(eq("queue.response.orches"), any(Response.class));
    }
}