/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "PayType.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-11           baowp                      initial
 */

package com.abbcc.util.constant;

public enum PayType {

	PA("银行汇款"), PB("网银划账"), PC("支付宝即时到账");

	private String value;

	private PayType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
