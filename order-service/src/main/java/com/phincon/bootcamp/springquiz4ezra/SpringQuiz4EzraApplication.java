package com.phincon.bootcamp.springquiz4ezra;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger Demo Bootcamp",version="1.0",description = "Documentation APIs v1.0"))
@EnableJms
@EnableScheduling
public class SpringQuiz4EzraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringQuiz4EzraApplication.class, args);
	}

}
