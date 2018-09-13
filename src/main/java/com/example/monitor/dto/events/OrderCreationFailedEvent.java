package com.example.monitor.dto.events;

import com.example.monitor.dto.requests.OrderAllocationRequestDTO;
import com.example.monitor.dto.requests.OrderCreationRequestDTO;
import com.example.monitor.dto.responses.OrderDTO;

import lombok.Data;

@Data
public class OrderCreationFailedEvent extends Event{
	public OrderCreationRequestDTO orderCreationReq;
	public String errorMsg;
	
	public OrderCreationFailedEvent(OrderCreationRequestDTO pickReq, String errorMsg) {
		this.orderCreationReq = pickReq;
		this.errorMsg = errorMsg;
	}

}
