/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DecodeUtil.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-12-24           baowp                      initial
 */

package com.abbcc.util;

import org.junit.Test;

public class DecodeUtil {
	@Test
	public void decodeGBKtoUTF8() {
		String s = ("涓ラ噸:");
		System.out.println(s);
		s = StringUtil.decode(s, "gbk", "utf8");
		System.out.println(s);
	}
}
