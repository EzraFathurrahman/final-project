package com.phincon.bootcamp.springquiz4ezra.service;


import com.phincon.bootcamp.springquiz4ezra.config.StepsConfig;
import com.phincon.bootcamp.springquiz4ezra.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


import java.time.Duration;
import java.util.*;

@Service
@Slf4j
public class OrderOrchestratorService  {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    StepsConfig stepsConfig;

    private Order order;
    private String responseStatus = "Idle";
    private String currentQueue="Idle";

// MAIN LISTENER ====================================
    @JmsListener(destination = "queue.order")
    public void getOrder(Order order) {
        try{
            // inputting order

            this.order=order;
            // setting trxid dan actionnya
            order.setTrxid(UUID.randomUUID().toString());
            String action=stepsConfig.getAction(order.getId());
            this.order.setAction(action);
            List<String> stepArray=stepsConfig.getStep(order.getId());

            this.responseStatus = "Init";

            orchestSteps(stepArray);

            this.responseStatus="Idle";
        }catch(Exception e){
            log.info("Failed to get order from queue.order : {}", e.getMessage());
        }
    }

    public void orchestSteps(List<String> stringList) {
        Flux.fromIterable(stringList)
                .delayElements(Duration.ofSeconds(1))
                .takeWhile(queueName -> !Objects.equals(responseStatus, "failed"))
                .doOnNext(queueName -> {
                    this.currentQueue = queueName;

                    jmsTemplate.convertAndSend(queueName, order);
                    this.responseStatus = "Pending";

                    log.info("Message sent to: {}", queueName);
                    log.info("Status current Order : {}", responseStatus);
                })
                .subscribe();
    }


    @JmsListener(destination = "queue.response.orches")
    public void getResponse(Message<Response> message) {
        this.responseStatus = message.getPayload().getResponse();
        log.info("Status from response listener: {}", responseStatus);
        updateResponse();
    }
    @Scheduled(cron = "* * * * * *")
    public void updateResponse() {
        log.info("Status current Order : {} in Queue : {}", responseStatus,currentQueue);
    }

}

