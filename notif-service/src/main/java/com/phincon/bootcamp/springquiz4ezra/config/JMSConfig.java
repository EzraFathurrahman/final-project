package com.phincon.bootcamp.springquiz4ezra.config;

import jakarta.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JMSConfig {
    @Value("${spring.activemq.broker-url:tcp://localhost:61616}")
    private String defaultBrokerUrl;
    @Bean
    public JmsListenerContainerFactory<?> MyFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer){ DefaultJmsListenerContainerFactory factory=new DefaultJmsListenerContainerFactory();
    configurer.configure(factory,connectionFactory);
    return factory;    }
    @Bean
    public MessageConverter jsonConverter(){
        MappingJackson2MessageConverter converter=new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    @Bean
    public ActiveMQConnectionFactory jmsConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(defaultBrokerUrl);
        connectionFactory.setTrustedPackages(List.of("com.phincon"));
        return connectionFactory;
    }




}
