/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SoaAdminServiceImpl.java is the copyrighted,
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

import com.abbcc.dao.SoaWebserverDAO;
import com.abbcc.service.SoaWebserverService;

public class SoaWebserverServiceImpl extends BaseServiceImpl implements
		SoaWebserverService {

	private SoaWebserverDAO soaWebserverDAO;

	public void setSoaWebserverDAO(SoaWebserverDAO soaWebserverDAO) {
		this.soaWebserverDAO = soaWebserverDAO;
		setBaseDAO(soaWebserverDAO);
	}
}
