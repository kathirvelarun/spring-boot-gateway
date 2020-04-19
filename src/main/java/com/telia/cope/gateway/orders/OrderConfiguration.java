package com.telia.cope.gateway.orders;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.telia.cope.gateway.proxies.BtbOrderServiceProxy;
import com.telia.cope.gateway.proxies.OrderServiceProxy;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
@EnableConfigurationProperties(OrderDestinations.class)
public class OrderConfiguration {

	/*
	 * @Bean public RouteLocator orderProxyRouting(RouteLocatorBuilder builder,
	 * OrderDestinations orderDestinations) { return builder.routes() //.route(r ->
	 * r.path("/orders/").and().method("GET").uri(
	 * "http://localhost:8081/btcorderapi/")) .route(r ->
	 * r.path("/btcorder/").uri("http://localhost:8081/btcorderapi/").id("btcModule"
	 * )) .route(r ->
	 * r.path("/btborder/").uri("http://localhost:8082/btcorderapi/").id("btbModule"
	 * )) .build(); }
	 */

  @Bean
  public RouterFunction<ServerResponse> orderHandlerRouting(OrderHandlers orderHandlers) {
	System.out.println("invoke order handling..... ") ;
    return RouterFunctions.route(GET("/orders/{orderId}"), orderHandlers::getOrderDetails);
  }
  
  @Bean
  public RouterFunction<ServerResponse> btbOrderHandlerRouting(OrderHandlers orderHandlers) {
	System.out.println("invoke order handling..... ") ;
    return RouterFunctions.route(GET("/btborders/{orderId}"), orderHandlers::getOrderDetails);
  }

  @Bean
  public OrderHandlers orderHandlers(OrderServiceProxy orderService, BtbOrderServiceProxy btbOrderService
                                     ) {
    return new OrderHandlers(orderService, btbOrderService);
  }

  @Bean
  public WebClient webClient() {
    return WebClient.create();
  }

}
