package com.telia.cope.gateway.proxies;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.telia.cope.gateway.orders.OrderDestinations;

import reactor.core.publisher.Mono;

@Service
public class OrderServiceProxy {



  private WebClient client;
  

  public OrderServiceProxy(OrderDestinations orderDestinations, WebClient client) {
    this.client = client;
  }

  public Mono<OrderInfo> findOrderById(String orderId) {
    Mono<ClientResponse> response = client
            .get()
            .uri("http://localhost:8081/btcorderapi/", orderId)
            .exchange();
    return response.flatMap(resp -> {
      switch (resp.statusCode()) {
        case OK:
          return resp.bodyToMono(OrderInfo.class);
        case NOT_FOUND:
          return Mono.error(new OrderNotFoundException());
        default:
          return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
      }
    });
  }


}
