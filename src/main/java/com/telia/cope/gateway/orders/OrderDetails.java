package com.telia.cope.gateway.orders;

import com.telia.cope.gateway.proxies.BtbOrderInfo;
import com.telia.cope.gateway.proxies.OrderInfo;

import reactor.util.function.Tuple2;


public class OrderDetails {

	private OrderInfo orderInfo;
	private BtbOrderInfo btbOrderInfo;

	public OrderDetails() {
	}


	public OrderDetails(OrderInfo orderInfo,BtbOrderInfo btbOrderInfo) {
		this.orderInfo = orderInfo;
		this.btbOrderInfo = btbOrderInfo;
	}

	public BtbOrderInfo getBtbOrderInfo() {
		return btbOrderInfo;
	}

	public void setBtbOrderInfo(BtbOrderInfo btbOrderInfo) {
		this.btbOrderInfo = btbOrderInfo;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public static OrderDetails makeOrderDetails(
			Tuple2<OrderInfo, BtbOrderInfo> info) {
		return new OrderDetails(info.getT1(), info.getT2());
	}
}
