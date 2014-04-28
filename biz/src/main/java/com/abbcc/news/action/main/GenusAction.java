/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "GenusAction.java is the copyrighted,
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
import com.abbcc.news.enums.DefaultGenus;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.NewsClass;
import com.abbcc.news.models.NewsGenus;
import com.abbcc.news.service.NewsClassService;
import com.abbcc.news.service.NewsGenusService;

@SuppressWarnings( { "serial", "unchecked" })
public class GenusAction<T> extends NewsBaseAction<NewsGenus> {

	protected NewsClassService newsClassService;
	protected NewsGenusService newsGenusService;
	public List<NewsClass> classResults;
	public List<NewsGenus> genusList;

	public void lis() {
		entity.setDisplay(Visibility.display);
		DetachedCriteria dc = DetachedCriteria.forClass(NewsGenus.class);
		dc.add(Example.create(entity)).addOrder(Property.forName("sort").asc());
		genusList = newsGenusService.findAllByCriteria(dc);
		if (genusList.isEmpty()) {
			for (DefaultGenus dg : DefaultGenus.values()) {
				if (dg.getDefaultClass().name().equals(entity.getClassSign())) {
					NewsGenus newsGenus = new NewsGenus();
					newsGenus.setName(dg.toString());
					newsGenus.setSign(dg.name());
					newsGenus.setClassSign(entity.getClassSign());
					genusList.add(newsGenus);
				}
			}
		}
	}

	public void categories() {
		if (entity.getClassSign() == null)
			entity.setClassSign(DefaultClass.index.name());
		lis();
		if (entity.getSign() != null)
			for (int i = 0; i < genusList.size(); i++) {
				if (!genusList.get(i).getSign().equals(entity.getSign()))
					genusList.remove(i--);
			}
		if (DefaultClass.valueOf(entity.getClassSign()) == DefaultClass.index
				&& entity.getSign() == null) {
			NewsClass example = new NewsClass();
			example.setDisplay(Visibility.display);
			example.setDomain(domain);
			DetachedCriteria dc = DetachedCriteria.forClass(NewsClass.class);
			dc.add(Example.create(example)).add(
					Property.forName("sign").ne(DefaultClass.index.name()))
					.addOrder(Property.forName("sort").asc());
			classResults = newsClassService.findAllByCriteria(dc);
			if (classResults.isEmpty())
				for (DefaultClass defaultClass : DefaultClass.values()) {
					if (defaultClass == DefaultClass.index)
						continue;
					NewsClass newsClass = new NewsClass();
					newsClass.setName(defaultClass.toString());
					newsClass.setSign(defaultClass.name());
					newsClass.setDomain(domain);
					classResults.add(newsClass);
				}
		}
	}

	protected void prepareModelInner(NewsGenus model) {
		super.prepareModelInner(model);
		model.setClassSign(request.getParameter("class"));
		model.setSign(request.getParameter("genus"));
	}

	public void setNewsClassService(NewsClassService newsClassService) {
		this.newsClassService = newsClassService;
	}

	public void setNewsGenusService(NewsGenusService newsGenusService) {
		this.newsGenusService = newsGenusService;
	}

}
