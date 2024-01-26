package com.phincon.bootcamp.springquiz4ezra.service;


import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.model.Response;
import com.phincon.bootcamp.springquiz4ezra.model.Notif;
import com.phincon.bootcamp.springquiz4ezra.model.Trx;
import com.phincon.bootcamp.springquiz4ezra.repository.OrderRepository;
import com.phincon.bootcamp.springquiz4ezra.repository.TrxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

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


    @JmsListener(destination = "queue.notif")
    public void saveUser(Message<Order> object){
        try {
            Notif notif= new Notif();

            notif.setId(object.getPayload().getId());
            log.info("notid id: {}",notif.getId());
            notif.setStatus("failed");
            log.info("notid status: {}",notif.getStatus());

            Trx trxOrder = new Trx();
            trxOrder.setId(object.getPayload().getTrxid());
            trxOrder.setAction(object.getPayload().getAction());
            trxOrder.setStep("queue.notif");
            log.info("trx id : {}",trxOrder.getId());

            Response response =new Response();
            orderRepository.save(notif).doOnSuccess(savedOrder -> {

                        response.setResponse("failed");
                        jmsTemplate.convertAndSend("queue.response.orches",response);
                        trxOrder.setStatus("failed");
                        trxRepository.savenew(trxOrder.getId(),trxOrder.getAction(),trxOrder.getStep(),trxOrder.getStatus()).subscribe();
                        log.info("response is : {}", response.getResponse());
                    })
                    .doOnError(error -> {
                        response.setResponse("failed");
                        jmsTemplate.convertAndSend("queue.response",response);
                        trxOrder.setStatus("failed");
                        trxRepository.savenew(trxOrder.getId(),trxOrder.getAction(),trxOrder.getStep(),trxOrder.getStatus()).subscribe();
                    })
                    .subscribe();

            log.info("notif message {}",notif);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error receive in activemq : {}", e.getMessage());
        }
    }

    }




