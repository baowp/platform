/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SoaTemplateServiceImpl.java is the copyrighted,
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

import com.abbcc.dao.SoaTemplateDAO;
import com.abbcc.service.SoaTemplateService;

public class SoaTemplateServiceImpl extends BaseServiceImpl implements
		SoaTemplateService {

	private SoaTemplateDAO soaTemplateDAO;

	public void setSoaTemplateDAO(SoaTemplateDAO dao) {
		soaTemplateDAO = dao;
		setBaseDAO(dao);
	}
}
