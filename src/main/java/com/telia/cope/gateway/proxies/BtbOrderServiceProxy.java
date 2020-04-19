package com.telia.cope.gateway.proxies;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.telia.cope.gateway.orders.OrderDestinations;

import reactor.core.publisher.Mono;

@Service
public class BtbOrderServiceProxy {

	  private WebClient client;

	  public BtbOrderServiceProxy(OrderDestinations orderDestinations, WebClient client) {
	    this.client = client;
	  }

	  public Mono<BtbOrderInfo> findBtbOrderById(String orderId) {
	    Mono<ClientResponse> response = client
	            .get()
	            .uri("http://localhost:8082/btborderapi/", orderId)
	            .exchange();
	    return response.flatMap(resp -> {
	      switch (resp.statusCode()) {
	        case OK:
	          return resp.bodyToMono(BtbOrderInfo.class);
	        case NOT_FOUND:
	          return Mono.error(new OrderNotFoundException());
	        default:
	          return Mono.error(new RuntimeException("Unknown" + resp.statusCode()));
	      }
	    });
	  }
}
