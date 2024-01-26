package com.phincon.bootcamp.springquiz4ezra.service;

import com.phincon.bootcamp.springquiz4ezra.model.Notif;
import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.model.Response;
import com.phincon.bootcamp.springquiz4ezra.repository.OrderRepository;
import com.phincon.bootcamp.springquiz4ezra.repository.TrxRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
        Notif notif = new Notif();
        notif.setId("123");
        notif.setStatus("success");

        Order order = new Order();
        order.setId("123");
        order.setStatus("success");

        Message<Order> mockMessage = mock(Message.class);
        when(mockMessage.getPayload()).thenReturn(order);


        when(orderRepository.save(any(Notif.class))).thenReturn(Mono.just(notif));
        when(trxRepository.savenew(anyString(), anyString(), anyString(), anyString())).thenReturn(Mono.empty());


        consumerService.saveUser(mockMessage);

        verify(orderRepository, times(1)).save(any(Notif.class));
        verify(jmsTemplate, times(1)).convertAndSend(eq("queue.response.orches"), any(Response.class));
    }
}