/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SoaUserServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-29           baowp                      initial
 */

package com.abbcc.service.impl;

import com.abbcc.dao.SoaUserDAO;
import com.abbcc.service.SoaUserService;

public class SoaUserServiceImpl extends BaseServiceImpl implements
		SoaUserService {

	private SoaUserDAO soaUserDAO;

	public void setSoaUserDAO(SoaUserDAO dao) {
		soaUserDAO = dao;
		setBaseDAO(dao);
	}
}
