/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SoaTemplateCriteriaServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-4-23           baowp                      initial
 */

package com.abbcc.service.impl;

import com.abbcc.dao.SoaTemplateCriteriaDAO;
import com.abbcc.service.SoaTemplateCriteriaService;

public class SoaTemplateCriteriaServiceImpl extends BaseServiceImpl implements
		SoaTemplateCriteriaService {

	private SoaTemplateCriteriaDAO soaTemplateCriteriaDAO;

	public void setSoaTemplateCriteriaDAO(SoaTemplateCriteriaDAO dao) {
		soaTemplateCriteriaDAO = dao;
		setBaseDAO(dao);
	}
}
