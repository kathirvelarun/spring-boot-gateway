package com.telia.cope.gateway;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.telia.cope.gateway.orders.OrderConfiguration;

@SpringBootApplication
@ComponentScan
@Import(OrderConfiguration.class)
public class CoPEGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoPEGatewayApplication.class, args);
	}

}
