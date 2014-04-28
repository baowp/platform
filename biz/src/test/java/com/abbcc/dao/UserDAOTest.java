/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminDAOTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-5           yixiugg                      initial
 **/

package com.abbcc.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.abbcc.models.AbcUser;
import com.abbcc.util.BeansFactory;


/**
 * *AdminDAOTest.java
 */
public class UserDAOTest{
	UserDAO userDAO;
	AbcUser user;
	@Before
	public void before() {
		userDAO=(UserDAO)BeansFactory.get("userDAO");
		user = new AbcUser();
		user.setUsername("abc");
		user.setState("1");
		user.setPassword("123456");
		user.setAddress("瓦斯的");

	}

	@Test
	public void testAdd() {

		userDAO.save(user);
		
	}
}
