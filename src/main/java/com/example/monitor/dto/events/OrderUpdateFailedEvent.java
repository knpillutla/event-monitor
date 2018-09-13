package com.example.monitor.dto.events;

import com.example.monitor.dto.requests.OrderUpdateRequestDTO;

import lombok.Data;

@Data
public class OrderUpdateFailedEvent extends Event{
	public OrderUpdateRequestDTO orderUpdateReq;
	public String errorMsg;
	
	public OrderUpdateFailedEvent(OrderUpdateRequestDTO orderUpdateReq, String errorMsg) {
		this.orderUpdateReq = orderUpdateReq;
		this.errorMsg = errorMsg;
	}

}
