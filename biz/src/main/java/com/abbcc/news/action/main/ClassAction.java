/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ClassAction.java is the copyrighted,
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

package com.abbcc.news.action.main;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Property;

import com.abbcc.news.action.NewsBaseAction;
import com.abbcc.news.enums.DefaultClass;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.NewsClass;
import com.abbcc.news.service.NewsClassService;

@SuppressWarnings( { "serial", "unchecked" })
public class ClassAction<T> extends NewsBaseAction<NewsClass> {

	protected NewsClassService newsClassService;
	public List<NewsClass> classResults;

	public void lis() {
		entity.setDisplay(Visibility.display);
		DetachedCriteria dc = DetachedCriteria.forClass(NewsClass.class);
		dc.add(Example.create(entity)).addOrder(Property.forName("sort").asc());
		classResults = newsClassService.findAllByCriteria(dc);
		if (classResults.isEmpty()) {
			for (DefaultClass defaultClass : DefaultClass.values()) {
				NewsClass newsClass = new NewsClass();
				newsClass.setName(defaultClass.toString());
				newsClass.setSign(defaultClass.name());
				newsClass.setDomain(domain);
				classResults.add(newsClass);
			}
		}
	}

	public void setNewsClassService(NewsClassService newsClassService) {
		this.newsClassService = newsClassService;
	}

}
