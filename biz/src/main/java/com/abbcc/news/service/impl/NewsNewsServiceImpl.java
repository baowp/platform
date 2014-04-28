/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsNewsServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-6-12           baowp                      initial
 */

package com.abbcc.news.service.impl;


import com.abbcc.dao.BaseDAO;
import com.abbcc.news.service.NewsNewsService;
import com.abbcc.service.impl.BaseServiceImpl;
public class NewsNewsServiceImpl extends BaseServiceImpl implements
		NewsNewsService {
	
	public void setBaseDAO(BaseDAO dao) {
	}

	public void setNewsNewsDAO(BaseDAO dao) {
		super.setBaseDAO(dao);
	}
}
