/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "StringTest.java is the copyrighted,
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

import java.io.IOException;

import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.abbcc.util.constant.group.GroupPage;

/**
 ** StringTest.java
 **/
public class StringTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	@Test
	public void s() throws IOException {
		String s = StringUtil.encode("产品");
		log.info(s);
		s = StringUtil.decode(s);
		log.info(s);
		s = Native2AsciiUtils.ascii2Native("\u8bdd\uff1a");
		log.info(s);
		s = Native2AsciiUtils.native2Ascii("英文版 中文版");
		log.info(s);
		ExpressionParser parser = new SpelExpressionParser();
		String helloWorld = (String) parser.parseExpression("'Hello World'")
				.getValue();
		int maxValue = parser.parseExpression("0x7FFFFFFF").getValue(
				Integer.class);
		log.info(helloWorld);
		log.info(maxValue);
		
		log.info(Native2AsciiUtils.native2Ascii("产品名"));
		log.info(Native2AsciiUtils.native2Ascii("价格"));
		log.info(GroupPage.valueOf("index"));
	}
}
