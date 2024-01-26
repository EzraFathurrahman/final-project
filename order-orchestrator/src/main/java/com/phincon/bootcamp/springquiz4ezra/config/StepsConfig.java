package com.phincon.bootcamp.springquiz4ezra.config;


import com.phincon.bootcamp.springquiz4ezra.model.Steps;
import com.phincon.bootcamp.springquiz4ezra.repository.StepConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class StepsConfig {

    @Autowired
    StepConfigRepository stepConfigRepository;

     public List<String> getStep(String id){
         Flux<Steps> stepData=stepConfigRepository.findStep(id);
         Flux<String> stepFlux = stepData.map(Steps::getStep);
         List<String> stepList = stepFlux.collectList().block();
         return stepList;
     }
     public String getAction(String id){
        Flux<Steps> actionData=stepConfigRepository.findStep(id);
        Flux<String> actionFlux = actionData.map(Steps::getAction);
         List<String> actionList = actionFlux.collectList().block();
        return actionList.get(0);
    }


}
