package com.phincon.bootcamp.springquiz4ezra.service;


import com.phincon.bootcamp.springquiz4ezra.model.Order;
import com.phincon.bootcamp.springquiz4ezra.model.Response;
import com.phincon.bootcamp.springquiz4ezra.model.Trx;
import com.phincon.bootcamp.springquiz4ezra.repository.TrxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {



    @Autowired
    JmsTemplate jmsTemplate ;
    @Autowired
    TrxRepository trxRepository;


    @JmsListener(destination = "queue.complete")
    public void returnComplete(Message<Order> object){
        try {
            Response response =new Response();
            response.setResponse("success");
            jmsTemplate.convertAndSend("queue.response.orches",response);
            Trx trxOrder = new Trx();
            trxOrder.setId(object.getPayload().getTrxid());
            trxOrder.setAction(object.getPayload().getAction());
            trxOrder.setStep("queue.complete");
            trxOrder.setStatus("success");
            trxRepository.savenew(trxOrder.getId(),trxOrder.getAction(),trxOrder.getStep(),trxOrder.getStatus()).subscribe();


        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error receive in activemq : {}", e.getMessage());
        }
    }

    }




