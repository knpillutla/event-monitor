package com.example.monitor.dto.events;

import com.example.monitor.dto.requests.OrderAllocationRequestDTO;
import com.example.monitor.dto.responses.OrderDTO;

import lombok.Data;

@Data
public class OrderLineCancelledEvent{
	public OrderAllocationRequestDTO pickReq;
	public String errorMsg;
	
	public OrderLineCancelledEvent(OrderAllocationRequestDTO pickReq, String errorMsg) {
		this.pickReq = pickReq;
		this.errorMsg = errorMsg;
	}

}
