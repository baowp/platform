/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SearchTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-7-21           baowp                      initial
 */

package com.abbcc.data;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abbcc.news.models.NewsNews;
import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;

public class SearchTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	BaseService service;

	@Before
	public void before() {
		service = (BaseService) BeansFactory.get("baseService");
	}

	@Test
	public void test() throws Exception {
		String[] field = new String[] { "content" };
		String qs = "江 浙";
		List list = service.findUserByParams(field, qs, NewsNews.class);
		log.info(list);
		log.info(list.size());
	}
	//@Test
	public void test2(){
		NewsNews news=(NewsNews) service.findById(NewsNews.class, "news_000000000000000000000000075");
		service.delete(news);
		service.save(news);
	}
}
