/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "BindServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-8-5           baowp                      initial
 */

package com.abbcc.service.impl;

import com.abbcc.dao.BaseDAO;
import com.abbcc.service.BindService;

public class BindServiceImpl extends BaseServiceImpl implements BindService {

	public void setBaseDAO(BaseDAO dao) {
	}

	public void setBindDAO(BaseDAO dao) {
		super.setBaseDAO(dao);
	}
}
