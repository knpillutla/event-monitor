package com.example.monitor.service;

import com.example.monitor.dto.requests.OrderCreationRequestDTO;
import com.example.monitor.dto.requests.OrderDeleteRequestDTO;
import com.example.monitor.dto.requests.OrderUpdateRequestDTO;
import com.example.monitor.dto.responses.OrderDTO;

public interface OrderService {
	public OrderDTO findById(String busName, Long Id) throws Exception;
	public OrderDTO createOrder(OrderCreationRequestDTO orderCreationReq) throws Exception;
	public OrderDTO updateOrder(OrderUpdateRequestDTO orderUpdRequest) throws Exception;
	public OrderDTO purgeOrder(OrderDeleteRequestDTO orderDelRequest) throws Exception;
}