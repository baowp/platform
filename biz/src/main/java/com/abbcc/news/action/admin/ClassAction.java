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

package com.abbcc.news.action.admin;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Property;

import com.abbcc.news.enums.DefaultClass;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.NewsClass;

@SuppressWarnings( { "serial", "unchecked" })
public class ClassAction extends com.abbcc.news.action.main.ClassAction<NewsClass> {

	public List<NewsClass> entities;

	public String list() {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.add(Example.create(entity));
		dc.addOrder(Property.forName("sort").asc());
		classResults = newsClassService.findAllByCriteria(dc);
		if (classResults.isEmpty()) {
			for (DefaultClass defaultClass : DefaultClass.values()) {
				NewsClass newsClass = new NewsClass();
				newsClass.setName(defaultClass.toString());
				newsClass.setSign(defaultClass.name());
				newsClass.setSort(defaultClass.ordinal() + 1);
				newsClass.setDisplay(Visibility.display);
				newsClass.setDomain(domain);
				classResults.add(newsClass);
			}
			newsClassService.saveOrUpdateAll(classResults);
		}
		return LIST;
	}

	public String add() {
		entity.setDisplay(Visibility.display);
		entity.setSort(newSort());
		newsClassService.save(entity);
		result = SUCCESS;
		return SUCCESS;
	}

	public String saveAll() {
		for (NewsClass nc : entities) {
			nc.setDomain(domain);
		}
		newsClassService.saveOrUpdateAll(entities);
		result = SUCCESS;
		return JSON;
	}

	public String remove() {
		newsClassService.delete(entity);
		return JSON;
	}

	public String addPage() {
		return "addPage";
	}

	public List<NewsClass> getEntities() {
		return entities;
	}

	public void setEntities(List<NewsClass> entities) {
		this.entities = entities;
	}

	private Integer newSort() {
		String hql = "select max(sort) from NewsClass where domain=?";

		List<Integer> list = (List<Integer>) newsClassService.find(hql, domain);
		Integer sort = null;
		if (list.size() > 0)
			sort = list.get(0);
		if (sort == null)
			sort = DefaultClass.values().length + 1;
		else
			sort++;
		return sort;
	}
}
