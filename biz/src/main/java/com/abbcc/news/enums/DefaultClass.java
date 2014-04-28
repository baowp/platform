/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsClass.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-6-11           baowp                      initial
 */

package com.abbcc.news.enums;

public enum DefaultClass {

	index("新闻首页"), internal("国内"), world("国际"), military("军事"), finance("财经"),

	internet("互联网"), house("房产"), car("汽车"), sports("体育"), yule("娱乐"),

	technic("科技"), society("社会");

	private String name;

	private DefaultClass(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
