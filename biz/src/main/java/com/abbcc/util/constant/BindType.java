/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "BindType.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-8-5           baowp                      initial
 */

package com.abbcc.util.constant;

public enum BindType {

	/** domain */
	D("域名"),
	/** address */
	A("地址");

	private String value;

	private BindType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
