/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "StaticDetail.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-5-22           baowp                      initial
 */

package com.abbcc.util.constant.layout;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;

public enum StaticDetail {

	PRODUCT(AbcProduct.class, "{state:'" + CommonConst.STATENORMAL
			+ "',isdisplay:'" + CommonConst.PRODUCTDISPLAY + "'}", "productId",
			"PRODUCT_DETAIL"),

	PHOTO(AbcAlbum.class, "{state:'" + CommonConst.STATENORMAL + "'}",
			"albumId", "PHOTO_DETAIL"),

	SUPPLY(AbcSupply.class, "{state:'" + CommonConst.STATENORMAL + "'}",
			"supplyId", "SUPPLY_DETAIL"),

	NEWS(AbcNews.class, "{state:'" + CommonConst.STATENORMAL + "',display:'"
			+ CommonConst.NEWSDISPLAY + "'}", "newsId", "NEWS_DETAIL");

	private Class<?> clazz;
	private String example;
	private String id;
	private String belong;

	private StaticDetail(Class<?> clazz, String example, String id,
			String belong) {
		this.clazz = clazz;
		this.example = example;
		this.id = id;
		this.belong = belong;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getExample() {
		return example;
	}

	public String getId() {
		return id;
	}

	public BelongPage getBelong() {
		return BelongPage.valueOf(belong);
	}
}
