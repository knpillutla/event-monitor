package com.example.monitor.dto.events;

import com.example.monitor.dto.responses.OrderDTO;

import lombok.Data;

@Data
public class OrderCreatedEvent extends Event{
	public OrderDTO orderDTO;
	public OrderCreatedEvent(OrderDTO orderDTO) {
		super("OrderCreatedEvent");
		this.orderDTO = orderDTO;
		this.addHeader("busName",orderDTO.getBusName());
		this.addHeader("locnNbr",orderDTO.getLocnNbr());
		this.addHeader("busUnit", orderDTO.getBusUnit());
		this.addHeader("company", orderDTO.getCompany());
		this.addHeader("division", orderDTO.getDivision());
	}	
}
