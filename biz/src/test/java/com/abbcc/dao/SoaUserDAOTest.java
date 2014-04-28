/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SoaUserDAOTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-5           yixiugg                      initial
**/

package com.abbcc.dao;

import org.apache.lucene.queryParser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.abbcc.models.SoaUser;
import com.abbcc.util.BeansFactory;

/**
 **SoaUserDAOTest.java
 **/
public class SoaUserDAOTest {
	SoaUserDAO soaUserDAO;
	@Before
	public void before() {
		soaUserDAO=(SoaUserDAO)BeansFactory.get("soaUserDAO");
	}
	@Test
	public void testSoaUser() {
		String[] s = {"domain"};
		try {
			soaUserDAO.findUserByParams(s, "abbcc", SoaUser.class);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

