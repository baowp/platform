/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SupplyType.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-25           baowp                      initial
 */

package com.abbcc.util.constant;

public enum SupplyType {
	/* SELL */
	SE("供应"),
	/* BUY */
	BU("需求"),
	/* AGENT */
	AG("代理"),
	/* COOPERATE */
	CO("合作");

	private String value;

	private SupplyType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
