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

package com.abbcc.module.userNews;

public enum DealState {
	/**
	 * 显示
	 */
	DA("显示"),
	/**
	 * 隐藏
	 */
	DB("隐藏"),
	/**
	 * 审核中
	 */
	DC("审核中"),
	/**
	 * 通过审核
	 */
	DD("通过审核");
	
	private String description;

	private DealState(String description) {
		this.description = description;
	}

	public String toString() {
		return description;
	}
}
