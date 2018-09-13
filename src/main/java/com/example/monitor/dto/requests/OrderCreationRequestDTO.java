package com.example.monitor.dto.requests;

import java.util.Date;

import lombok.Data;

@Data
public class OrderCreationRequestDTO {
	String busName;
	Integer locnNbr;
	String company;
	String division;
	String busUnit;
	String externalBatchNbr;
	String orderNbr;
	Date orderDttm;
	Date shipByDttm;
	Date expectedDeliveryDttm;
	String deliveryType;
	boolean isGift;
	String giftMsg;
	String source;
	String transactionName;
	String refField1;
	String refField2;
	String userId;
	
	
	public OrderCreationRequestDTO(String busName, Integer locnNbr, String company, String division, String busUnit,
			String externalBatchNbr, String orderNbr, Date orderDttm, Date shipByDttm, Date expectedDeliveryDttm,
			String deliveryType, boolean isGift, String giftMsg, String source, String transactionName,
			String refField1, String refField2, String userId) {
		super();
		this.busName = busName;
		this.locnNbr = locnNbr;
		this.company = company;
		this.division = division;
		this.busUnit = busUnit;
		this.externalBatchNbr = externalBatchNbr;
		this.orderNbr = orderNbr;
		this.orderDttm = orderDttm;
		this.shipByDttm = shipByDttm;
		this.expectedDeliveryDttm = expectedDeliveryDttm;
		this.deliveryType = deliveryType;
		this.isGift = isGift;
		this.giftMsg = giftMsg;
		this.source = source;
		this.transactionName = transactionName;
		this.refField1 = refField1;
		this.refField2 = refField2;
		this.userId = userId;
	}
}

