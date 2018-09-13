package com.example.monitor.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="ORDER_LINES")
public class OrderLine  implements Serializable{
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

/*
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
*/	
	@Column(name="LOCN_NBR")
	Integer locnNbr;

	@Column(name="ORDER_ID")
	Integer orderId;

	@Column(name="ITEM_BRCD")
	String itemBrcd;

	@Column(name="ORIG_ORDER_QTY")
	Integer origOrderQty;

	@Column(name="ORDER_QTY")
	Integer orderQty;

	@Column(name="CANCELLED_QTY")
	Integer cancelledQty;

	@Column(name="SHORT_QTY")
	Integer shortQty;

	@Column(name="PICKED_QTY")
	Integer pickedQty;

	@Column(name="PACKED_QTY")
	Integer packedQty;

	@Column(name="SHIPPED_QTY")
	Integer shippedQty;

	@Column(name="STAT_CODE")
	Integer statCode;

	@Column(name="OLPN")
	String olpn;
	
	@Column(name="SOURCE")
	String source;

	@Column(name="TRANSACTION_NAME")
	String transactionName;

	@Column(name="REF_FIELD_1")
	String refField1;

	@Column(name="REF_FIELD_2")
	String refField2;

	@Column(name="HOST_NAME")
	String hostName;

	@Column(name="CREATED_DTTM")
	Date createdDttm;
	
	@Column(name="UPDATED_DTTM")
	Date updatedDttm;
	
	@Column(name="CREATED_BY")
	String createdBy;

	@Column(name="UPDATED_BY")
	String updatedBy;

	public OrderLine(Long id, Integer locnNbr, Integer orderId, String itemBrcd, Integer origOrderQty,
			Integer orderQty, Integer cancelledQty, Integer shortQty, Integer pickedQty, Integer packedQty,
			Integer shippedQty, Integer statCode, String olpn, String source, String transactionName,
			String refField1, String refField2, Date updatedDttm, String updatedBy) {
		this.id = id;
		this.locnNbr = locnNbr;
		this.orderId = orderId;
		this.itemBrcd = itemBrcd;
		this.origOrderQty = origOrderQty;
		this.orderQty = orderQty;
		this.cancelledQty = cancelledQty;
		this.shortQty = shortQty;
		this.pickedQty = pickedQty;
		this.packedQty = packedQty;
		this.shippedQty = shippedQty;
		this.statCode = statCode;
		this.olpn = olpn;
		this.source = source;
		this.transactionName = transactionName;
		this.refField1 = refField1;
		this.refField2 = refField2;
		this.updatedDttm = updatedDttm;
		this.updatedBy = updatedBy;
	}
}