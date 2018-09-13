package com.example.monitor.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.monitor.db.Order;
import com.example.monitor.db.OrderRepository;
import com.example.monitor.dto.converter.OrderDTOConverter;
import com.example.monitor.dto.events.OrderCreatedEvent;
import com.example.monitor.dto.events.OrderCreationFailedEvent;
import com.example.monitor.dto.events.OrderUpdateFailedEvent;
import com.example.monitor.dto.requests.OrderCreationRequestDTO;
import com.example.monitor.dto.requests.OrderDeleteRequestDTO;
import com.example.monitor.dto.requests.OrderUpdateRequestDTO;
import com.example.monitor.dto.responses.OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderDAO;

	@Autowired
	EventPublisher eventPublisher;

	@Autowired
	OrderDTOConverter orderDTOConverter;

	public enum OrderStatus {
		CREATED(100), READY(110), ALLOCATED(120), PARTIALLY_ALLOCATED(121), PICKED(130), PACKED(140), SHIPPED(150),
		SHORTED(160), CANCELLED(199);
		OrderStatus(Integer statCode) {
			this.statCode = statCode;
		}

		private Integer statCode;

		public Integer getStatCode() {
			return statCode;
		}
	}

	@Override
	@Transactional
	public OrderDTO updateOrder(OrderUpdateRequestDTO orderUpdateRequestDTO) throws Exception {
		OrderDTO orderDTO = null;
		try {
			Optional<Order> orderOptional = orderDAO.findById(orderUpdateRequestDTO.getId());
			if (!orderOptional.isPresent()) {
				throw new Exception("Order Update Failed. Order Not found to update");
			}
			Order orderEntity = orderOptional.get();
			orderDTOConverter.updateOrderEntity(orderEntity, orderUpdateRequestDTO);
			orderDTO = orderDTOConverter.getOrderDTO(orderDAO.save(orderEntity));
		} catch (Exception ex) {
			log.error("Created Order Error:" + ex.getMessage(), ex);
			eventPublisher.publish(
					new OrderUpdateFailedEvent(orderUpdateRequestDTO, "Update Order Error:" + ex.getMessage()));
			throw ex;
		}
		return orderDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	@Override
	@Transactional
	public OrderDTO createOrder(OrderCreationRequestDTO orderCreationRequestDTO) throws Exception {
		OrderDTO orderResponseDTO = null;
		try {
			Order order = orderDTOConverter.getOrderEntity(orderCreationRequestDTO);
			Date orderCreationDate = new Date();
			order.setCreatedDttm(orderCreationDate);
			order.setUpdatedDttm(orderCreationDate);
			order.setStatCode(OrderStatus.READY.getStatCode());
			Order savedOrderObj = orderDAO.save(order);
			orderResponseDTO = orderDTOConverter.getOrderDTO(savedOrderObj);
			eventPublisher.publish(new OrderCreatedEvent(orderResponseDTO));
		} catch (Exception ex) {
			log.error("Created Order Error:" + ex.getMessage(), ex);
			eventPublisher.publish(
					new OrderCreationFailedEvent(orderCreationRequestDTO, "Created Order Error:" + ex.getMessage()));
			throw ex;
		}
		return orderResponseDTO;
	}

	@Override
	public OrderDTO findById(String busName, Long Id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO purgeOrder(OrderDeleteRequestDTO orderDelRequest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
