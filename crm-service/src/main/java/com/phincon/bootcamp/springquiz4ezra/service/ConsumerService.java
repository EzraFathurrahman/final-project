package com.phincon.bootcamp.springquiz4ezra.service;


import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.model.Response;
import com.phincon.bootcamp.springquiz4ezra.model.Trx;
import com.phincon.bootcamp.springquiz4ezra.repository.OrderRepository;
import com.phincon.bootcamp.springquiz4ezra.repository.TrxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class ConsumerService {


    @Autowired
    OrderRepository orderRepository;
    @Autowired
    TrxRepository trxRepository;
    @Autowired
    JmsTemplate jmsTemplate ;
    Response response = new Response();

    @JmsListener(destination = "queue.crm.register")
    public void saveUser(Message<Order> object){
        try {
            Order order=new Order();
            order.setId(object.getPayload().getId());
            order.setProduct(object.getPayload().getProduct());
            order.setPrice(object.getPayload().getPrice());
            order.setStatus("success");
            order.setTrxid(object.getPayload().getTrxid());

            Trx trxOrder = new Trx();
            trxOrder.setId(object.getPayload().getTrxid());
            trxOrder.setAction(object.getPayload().getAction());
            trxOrder.setStep("queue.crm.register");

            orderRepository.save(order).doOnSuccess(savedOrder -> {

                        response.setResponse("success");
                        jmsTemplate.convertAndSend("queue.response.orches",response);
                        log.info("response is : {}", response.getResponse());
                        trxOrder.setStatus("success");
                        trxRepository.savenew(trxOrder.getId(),trxOrder.getAction(),trxOrder.getStep(),trxOrder.getStatus()).subscribe();
                        log.info("message sent to transaction : {}", trxOrder.getId());
                        }

                    )
                    .doOnError(error -> {
                        response.setResponse("failed");
                        jmsTemplate.convertAndSend("queue.response",response);
                        trxOrder.setStatus("failed");
                        trxRepository.savenew(trxOrder.getId(),trxOrder.getAction(),trxOrder.getStep(),trxOrder.getStatus()).subscribe();
                        log.info("response is : {}", response.getResponse());
                    })
                    .subscribe();
            log.info("order message {}",object);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error receive in activemq : {}", e.getMessage());
        }
    }


    }




