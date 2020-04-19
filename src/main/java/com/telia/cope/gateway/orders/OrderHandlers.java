package com.telia.cope.gateway.orders;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.telia.cope.gateway.proxies.BtbOrderInfo;
import com.telia.cope.gateway.proxies.BtbOrderServiceProxy;
import com.telia.cope.gateway.proxies.OrderInfo;
import com.telia.cope.gateway.proxies.OrderNotFoundException;
import com.telia.cope.gateway.proxies.OrderServiceProxy;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;


import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class OrderHandlers {

  private OrderServiceProxy orderService;
  private BtbOrderServiceProxy btbOrderService;

  public OrderHandlers(OrderServiceProxy orderService,
                       BtbOrderServiceProxy btbOrderService) {
    this.orderService = orderService;
    this.btbOrderService = btbOrderService;

  }

  public Mono<ServerResponse> getOrderDetails(ServerRequest serverRequest) {
    String orderId = serverRequest.pathVariable("orderId");

    Mono<OrderInfo> orderInfo = orderService.findOrderById(orderId);
    Mono<BtbOrderInfo> btbOrderInfo = btbOrderService.findBtbOrderById(orderId);


    Mono<Tuple2<OrderInfo, BtbOrderInfo>> combined =
            Mono.zip(orderInfo, btbOrderInfo);

    Mono<OrderDetails> orderDetails = combined.map(OrderDetails::makeOrderDetails);

    return orderDetails.flatMap(od -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(fromObject(od)))
            .onErrorResume(OrderNotFoundException.class, e -> ServerResponse.notFound().build());
  }


}
