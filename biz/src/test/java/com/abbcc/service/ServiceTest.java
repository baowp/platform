/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ServiceTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-10           yixiugg                      initial
**/

package com.abbcc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcUser;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;

/**
 **ServiceTest.java
 **/
public class ServiceTest {
	private AdminService adminService;
	private UserService userService;
	private SyscodeService syscodeService;
	@Autowired
	private SeoService seoService;
	@Before
	public void before(){
		adminService = (AdminService)BeansFactory.get("adminService");
		userService  = (UserService)BeansFactory.get("userService");
		syscodeService = (SyscodeService)BeansFactory.get("syscodeService");
	}
	@Test
	public void testAdminService(){
		
//		DetachedCriteria detachedCriteria = DetachedCriteria
//		.forClass(AbcAdmin.class);
////	detachedCriteria.add(Restrictions.or(Restrictions.like("username",
////			"", MatchMode.ANYWHERE), Restrictions.like("name",
////			this.name, MatchMode.ANYWHERE)));
//detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
//PaginationSupport l = adminService.findPageByCriteria(detachedCriteria,20, 0);
//		AbcUser user = userService.findById("User_000000000000000000000000035");
//		userService.saveOrUpdate(user);
		//userService.getUserByDomain("bluish.com");
		System.out.println(seoService);
	}

}

