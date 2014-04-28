/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AbcOrder.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-28           baowp                      initial
 */

package com.abbcc.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.OrderDealState;

@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_ORDER")
public class AbcOrder implements Serializable {

	private String orderId;
	private AbcEnterprise orderEnt;
	private AbcEnterprise gerorderEnt;
	private AbcProduct product;
	private Integer orderSum;
	private Date orderTime;
	private String state;
	private String reply;
	private String odesc;
	private OrderDealState dealState;
	private Double amount;
	private String content;
	private AbcUser orderUser;
	
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_order", parameters = {
			@Parameter(name = "sequence", value = "SEQ_order"),
			@Parameter(name = "prefix", value = "Order") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_order")
	@Column(name = "ORDER_ID", unique = true, nullable = false, length = 64)
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	public AbcProduct getProduct() {
		return product;
	}

	public void setProduct(AbcProduct product) {
		this.product = product;
	}

	@Column(name = "order_sum")
	public Integer getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(Integer orderSum) {
		this.orderSum = orderSum;
	}

	@Column(name = "order_time")
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getOdesc() {
		return odesc;
	}

	public void setOdesc(String odesc) {
		this.odesc = odesc;
	}

	@Enumerated(value = EnumType.STRING)
	public OrderDealState getDealState() {
		return dealState;
	}

	public void setDealState(OrderDealState dealState) {
		this.dealState = dealState;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_ent_id")
	public AbcEnterprise getOrderEnt() {
		return orderEnt;
	}

	public void setOrderEnt(AbcEnterprise orderEnt) {
		this.orderEnt = orderEnt;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "gerorder_ent_id")
	public AbcEnterprise getGerorderEnt() {
		return gerorderEnt;
	}

	public void setGerorderEnt(AbcEnterprise gerorderEnt) {
		this.gerorderEnt = gerorderEnt;
	}

	@Transient
	@Formula(value="product2_.price*order_sum")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "order_user")
	public AbcUser getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(AbcUser orderUser) {
		this.orderUser = orderUser;
	}

}
