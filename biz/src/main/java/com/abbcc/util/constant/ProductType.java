/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ProductType.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-4-19           baowp                      initial
 */

package com.abbcc.util.constant;

public enum ProductType {

	/** normal */
	NM("普通"),
	/** new */
	NW("新品"),
	/** competitive */
	CP("精品");

	private String value;

	ProductType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
