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
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.junit.Before;
import org.junit.Test;

import com.abbcc.models.AbcAdmin;
import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;


/**
 * *AdminDAOTest.java
 */
public class AdminDAOTest{
	AdminDAO adminDAO;
	BaseService baseService;
	AbcAdmin admin;
	@Before
	public void before() {
		adminDAO=(AdminDAO)BeansFactory.get("adminDAO");
//		
//		admin = new AbcAdmin();
//		admin.setUsername("中文");
//		admin.setPassword("啊啊");
//		admin.setAddTime(new Date());
//		baseService=(BaseService)BeansFactory.get("baseService");
	}

//	@Test
//	public void testAdd() {
////		adminDAO=(AdminDAO)BeansFactory.get("adminDAO");
////		admin = new AbcAdmin();
////		admin.setUsername("test");
////		admin.setPassword("test");
////		admin.setAddTime("2008-09-11 11:30:05");
//		adminDAO.save(admin);
//		
//	}
	@Test
	public void testSearch() throws ParseException{
		admin = new  AbcAdmin();
		admin.setName("中华人民共和国");
		adminDAO.save(admin);
		admin = new  AbcAdmin();
		admin.setName("中华明国");
		adminDAO.save(admin);
		admin = new  AbcAdmin();
		admin.setName("中华共和国");
		adminDAO.save(admin);
		admin = new  AbcAdmin();
		admin.setName("人民共和国");
		adminDAO.save(admin);
		List   l = (List<AbcAdmin>)adminDAO.getAdminByPaoding() ;
		for(int i=0;i<l.size();i++){
			admin = (AbcAdmin)l.get(i);
			System.out.println(admin.getUsername());
		}
	}
}
