package com.example.monitor.dto.converter;

import org.springframework.stereotype.Component;

import com.example.monitor.db.Order;
import com.example.monitor.db.OrderLine;
import com.example.monitor.dto.requests.OrderCreationRequestDTO;
import com.example.monitor.dto.requests.OrderUpdateRequestDTO;
import com.example.monitor.dto.responses.OrderDTO;
import com.example.monitor.dto.responses.OrderLineDTO;

@Component
public class OrderDTOConverter {

	public OrderDTO getOrderDTO(Order orderEntity) {
		OrderDTO orderDTO = new OrderDTO(orderEntity.getId(), orderEntity.getBusName(), orderEntity.getLocnNbr(),
				orderEntity.getCompany(), orderEntity.getDivision(), orderEntity.getBusUnit(),
				orderEntity.getExternalBatchNbr(), orderEntity.getBatchNbr(), orderEntity.getOrderNbr(),
				orderEntity.getStatCode(), orderEntity.getOrderDttm(), orderEntity.getShipByDttm(),
				orderEntity.getExpectedDeliveryDttm(), orderEntity.getDeliveryType(), orderEntity.isGift(),
				orderEntity.getGiftMsg(), orderEntity.getSource(), orderEntity.getTransactionName(),
				orderEntity.getRefField1(), orderEntity.getRefField2(), orderEntity.getUpdatedDttm(),
				orderEntity.getUpdatedBy());
		return orderDTO;
	}

	public Order getOrderEntity(OrderCreationRequestDTO orderDTO) {
		Order orderEntity = new Order(orderDTO.getBusName(), orderDTO.getLocnNbr(),
				orderDTO.getCompany(), orderDTO.getDivision(), orderDTO.getBusUnit(), orderDTO.getExternalBatchNbr(),
				orderDTO.getOrderNbr(), orderDTO.getOrderDttm(),
				orderDTO.getShipByDttm(), orderDTO.getExpectedDeliveryDttm(), orderDTO.getDeliveryType(),
				orderDTO.isGift(), orderDTO.getGiftMsg(), orderDTO.getSource(), orderDTO.getTransactionName(),
				orderDTO.getRefField1(), orderDTO.getRefField2(),orderDTO.getUserId());
		return orderEntity;
	}

	public Order updateOrderEntity(Order orderEntity, OrderUpdateRequestDTO orderUpdateReqDTO) {
		orderEntity.setExpectedDeliveryDttm(orderUpdateReqDTO.getExpectedDeliveryDttm());
		orderEntity.setDeliveryType(orderUpdateReqDTO.getDeliveryType());
		orderEntity.setGift(orderUpdateReqDTO.isGift());
		orderEntity.setGiftMsg(orderUpdateReqDTO.getGiftMsg());
		orderEntity.setShipByDttm(orderUpdateReqDTO.getShipByDttm());
		orderEntity.setTransactionName(orderUpdateReqDTO.getTransactionName());
		orderEntity.setUpdatedBy(orderUpdateReqDTO.getUserId());
		orderEntity.setRefField1(orderUpdateReqDTO.getRefField1());
		orderEntity.setRefField2(orderUpdateReqDTO.getRefField2());
		orderEntity.setSource(orderUpdateReqDTO.getSource());
		orderEntity.setUpdatedDttm(new java.util.Date());
		return orderEntity;
	}

	public OrderLineDTO getOrderLineDTO(OrderLine orderLine) {
		OrderLineDTO orderLineDTO = new OrderLineDTO(orderLine.getId(), orderLine.getLocnNbr(), orderLine.getOrderId(),
				orderLine.getItemBrcd(), orderLine.getOrigOrderQty(), orderLine.getOrderQty(),
				orderLine.getCancelledQty(), orderLine.getShortQty(), orderLine.getPickedQty(),
				orderLine.getPackedQty(), orderLine.getShippedQty(), orderLine.getStatCode(), orderLine.getOlpn(),
				orderLine.getSource(), orderLine.getTransactionName(), orderLine.getRefField1(),
				orderLine.getRefField2(), orderLine.getUpdatedDttm(), orderLine.getUpdatedBy());
		return orderLineDTO;
	}

	public OrderLine getOrderLineEntity(OrderLineDTO orderLineDTO) {
		OrderLine orderLine = new OrderLine(orderLineDTO.getId(), orderLineDTO.getLocnNbr(), orderLineDTO.getOrderId(),
				orderLineDTO.getItemBrcd(), orderLineDTO.getOrigOrderQty(), orderLineDTO.getOrderQty(),
				orderLineDTO.getCancelledQty(), orderLineDTO.getShortQty(), orderLineDTO.getPickedQty(),
				orderLineDTO.getPackedQty(), orderLineDTO.getShippedQty(), orderLineDTO.getStatCode(), orderLineDTO.getOlpn(),
				orderLineDTO.getSource(), orderLineDTO.getTransactionName(), orderLineDTO.getRefField1(),
				orderLineDTO.getRefField2(), orderLineDTO.getUpdatedDttm(), orderLineDTO.getUpdatedBy());
		return orderLine;
	}

}
