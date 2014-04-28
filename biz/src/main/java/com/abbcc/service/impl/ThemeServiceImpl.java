/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ThemeServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-9           baowp                      initial
 */

package com.abbcc.service.impl;

import com.abbcc.dao.ThemeDAO;
import com.abbcc.service.ThemeService;

public class ThemeServiceImpl extends BaseServiceImpl implements ThemeService {
	private ThemeDAO themeDAO;

	public void setThemeDAO(ThemeDAO dao) {
		themeDAO = dao;
		setBaseDAO(dao);
	}
}
