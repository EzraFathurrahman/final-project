package com.phincon.ezra.gatewaytest1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger Demo Bootcamp",version="1.0",description = "Documentation APIs v1.0"))
public class GatewayTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(GatewayTest1Application.class, args);
	}

}
