/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DealState.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-30           baowp                      initial
 */

package com.abbcc.util.constant;

public enum OrderDealState {
	/**
	 * 下单未付款
	 */
	DA("下单"),
	/**
	 * 付款未发货
	 */
	DB("已付款"),
	/**
	 * 发货未确认收货
	 */
	DC("已发货"),
	/**
	 * 确认收货未评价
	 */
	DD("收货确认"),
	/**
	 * 成功交易并已评价
	 */
	DE("已评价");

	private String description;

	private OrderDealState(String description) {
		this.description = description;
	}

	public String toString() {
		return description;
	}
}
