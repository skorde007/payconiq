package com.pq.payconiq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * NOTE: 
 * To Access Swagger UI for testing API endpoints: http://localhost:9999/payconiq/swagger-ui.html
 * 
 * To Access H2 DB for checking records : http://localhost:9999/payconiq/h2-console
 * 
 * To Build Docker Image: docker build -f Dockerfile -t payconiq .
 * 
 * To Run Docker Container: docker run -p 9999:9999 payconiq
 * 
 * @author skorde
 *
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Stock API", version = "1.0", description = "Payconiq Assignment"))
public class PayconiqApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayconiqApplication.class, args);
	}

}
