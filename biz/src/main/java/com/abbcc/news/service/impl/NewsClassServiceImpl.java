/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsClassServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-6-10           baowp                      initial
 */

package com.abbcc.news.service.impl;

import java.util.List;

import com.abbcc.dao.BaseDAO;
import com.abbcc.news.models.NewsClass;
import com.abbcc.news.models.NewsGenus;
import com.abbcc.news.service.NewsClassService;
import com.abbcc.service.impl.BaseServiceImpl;

public class NewsClassServiceImpl extends BaseServiceImpl implements
		NewsClassService {

	public void setBaseDAO(BaseDAO dao) {
	}

	public void setNewsClassDAO(BaseDAO dao) {
		super.setBaseDAO(dao);
	}

	public void delete(NewsClass entity) {
		NewsGenus example = new NewsGenus();
		example.setClassSign(entity.getSign());
		example.setDomain(entity.getDomain());
		@SuppressWarnings("unchecked")
		List<NewsGenus> list = baseDAO.findByExample(example);
		baseDAO.deleteAll(list);
		baseDAO.delete(entity);
	}
}
