package com.phincon.bootcamp.springquiz4ezra.service;

import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.model.Response;
import com.phincon.bootcamp.springquiz4ezra.repository.TrxRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class ConsumerServiceTest {
    @Mock
    private TrxRepository trxRepository;

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private ConsumerService consumerService;

    @Test
    public void testReturnComplete_Success() {
        // Mock data
        Order mockOrder = new Order();
        mockOrder.setTrxid("456");
        mockOrder.setAction("SomeAction");

        Message<Order> mockMessage = mock(Message.class);
        when(mockMessage.getPayload()).thenReturn(mockOrder);

        // Mock repository behavior
        when(trxRepository.savenew(anyString(), anyString(), anyString(), anyString())).thenReturn(Mono.empty());

        // Test the service method
        consumerService.returnComplete(mockMessage);

        // Verify that the expected methods were called
        verify(jmsTemplate, times(1)).convertAndSend(eq("queue.response.orches"), any(Response.class));
        verify(trxRepository, times(1)).savenew(anyString(), anyString(), anyString(), anyString());
    }
}