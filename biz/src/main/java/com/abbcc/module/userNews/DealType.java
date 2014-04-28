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

public enum DealType {
	/**
	 * 图片新闻
	 */
	DA("图片新闻"),
	/**
	 * 一般新闻
	 */
	DB("一般新闻"),
	/**
	 * 
	 */
	DC("置顶新闻"),
	DD("滚动新闻");
	
	private String description;

	private DealType(String description) {
		this.description = description;
	}

	public String toString() {
		return description;
	}
}
