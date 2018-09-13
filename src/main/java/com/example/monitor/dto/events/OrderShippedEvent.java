package com.example.monitor.dto.events;

import java.util.List;

import com.example.monitor.dto.requests.OrderAllocationRequestDTO;
import com.example.monitor.dto.responses.OrderDTO;

import lombok.Data;

@Data
public class OrderShippedEvent{
	List<OrderDTO> inventoryDTOList;
	
	public OrderShippedEvent(List<OrderDTO> inventoryDTOList) {
		this.inventoryDTOList = inventoryDTOList;
	}	
}
