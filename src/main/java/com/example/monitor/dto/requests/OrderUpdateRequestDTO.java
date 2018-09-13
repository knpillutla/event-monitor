package com.example.monitor.dto.requests;

import java.util.Date;

import lombok.Data;

@Data
public class OrderUpdateRequestDTO {
	Long id;
	String busName;
	String locnNbr;
	String orderNbr;
	String company;
	String division;
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
}
