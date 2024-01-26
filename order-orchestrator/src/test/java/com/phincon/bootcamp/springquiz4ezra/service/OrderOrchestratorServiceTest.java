package com.phincon.bootcamp.springquiz4ezra.service;

import com.phincon.bootcamp.springquiz4ezra.config.StepsConfig;
import com.phincon.bootcamp.springquiz4ezra.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderOrchestratorServiceTest {
    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private StepsConfig stepsConfig;

    @InjectMocks
    private OrderOrchestratorService orderOrchestratorService;

    @Test
    void testGetOrderAndOrchestrateSteps() {

        Order order = new Order();
        order.setId("1");

        List<String> stepArray = Arrays.asList("queue.crm.register", "queue.notif");

        when(stepsConfig.getStep(order.getId())).thenReturn(stepArray);


        orderOrchestratorService.getOrder(order);


        verify(stepsConfig, times(1)).getStep(order.getId());
        verify(jmsTemplate, times(2)).convertAndSend(String.valueOf(any()), eq(order));

    }
}