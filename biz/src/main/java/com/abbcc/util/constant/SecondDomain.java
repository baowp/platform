/**
 * 为系统保留的二级域名
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SecondDomain.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-5-31           baowp                      initial
 */

package com.abbcc.util.constant;

public enum SecondDomain {

	MAIL, POP3, STMP, SEARCH, BLOG, ZHIDAO, HELP, NEWS, VIDEO, IMAGE, MUSIC, SHOP, FINANCE, ADMIN, USER, DATA, DATE;

	public static boolean defined(String name) {
		try {
			SecondDomain.valueOf(name.toUpperCase());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
