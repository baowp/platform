/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "EncryptTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-13           yixiugg                      initial
**/

package com.abbcc.util;

import org.junit.Test;

/**
 **EncryptTest.java
 **/
public class EncryptTest {
	@Test
	public void md5Test(){
		System.out.println(MD5EncryptUtil.md5Encry("abbcc"));
	}

}

