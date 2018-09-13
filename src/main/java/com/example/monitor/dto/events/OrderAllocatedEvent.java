package com.example.monitor.dto.events;

import java.util.List;

import com.example.monitor.dto.requests.OrderAllocationRequestDTO;
import com.example.monitor.dto.responses.OrderDTO;

import lombok.Data;

@Data
public class OrderAllocatedEvent{
	List<OrderDTO> inventoryDTOList;
	
	public OrderAllocatedEvent(List<OrderDTO> inventoryDTOList) {
		this.inventoryDTOList = inventoryDTOList;
	}	
}
