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

package com.abbcc.news.action.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import com.abbcc.news.enums.DefaultClass;
import com.abbcc.news.enums.DefaultGenus;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.NewsClass;
import com.abbcc.news.models.NewsGenus;
import com.abbcc.util.StringUtil;

@SuppressWarnings({ "serial", "unchecked" })
public class GenusAction extends
		com.abbcc.news.action.main.GenusAction<NewsGenus> {

	public List<NewsGenus> entities;

	public String list() {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsClass.class);
		dc.add(Property.forName("domain").eq(domain)).addOrder(
				Property.forName("sort").asc());
		classResults = newsClassService.findAllByCriteria(dc);
		if (classResults.isEmpty()) {
			for (DefaultClass defaultClass : DefaultClass.values()) {
				NewsClass newsClass = new NewsClass();
				newsClass.setDefaultClass(defaultClass);
				newsClass.setName(defaultClass.toString());
				newsClass.setSign(defaultClass.name());
				newsClass.setSort(defaultClass.ordinal() + 1);
				newsClass.setDisplay(Visibility.display);
				newsClass.setDomain(domain);
				classResults.add(newsClass);
			}
			newsClassService.saveOrUpdateAll(classResults);
		}
		for (NewsClass nc : classResults) {
			dc = DetachedCriteria.forClass(NewsGenus.class)
					.add(Property.forName("classSign").eq(nc.getSign()))
					.add(Property.forName("domain").eq(domain))
					.addOrder(Property.forName("sort").asc());
			List<NewsGenus> genusList = newsGenusService.findAllByCriteria(dc);

			if (nc.getDefaultClass() != null || genusList.isEmpty()) {
				genusList = new ArrayList<NewsGenus>();
				int i = 1;
				for (DefaultGenus dg : DefaultGenus.values()) {
					if (nc.getSign().equals(dg.getDefaultClass().name())) {
						NewsGenus newsGenus = new NewsGenus();
						newsGenus.setName(dg.toString());
						newsGenus.setSign(dg.name());
						newsGenus.setSort(i++);
						newsGenus.setDisplay(Visibility.display);
						newsGenus.setClassId(nc.getClassId());
						newsGenus.setClassSign(nc.getSign());
						newsGenus.setDomain(domain);
						genusList.add(newsGenus);
					}
				}
				newsGenusService.saveOrUpdateAll(genusList);
			}
			nc.setGenusList(genusList);

		}
		return LIST;
	}
	public String addPage() {
		getRequest().setAttribute("classId",entity.getClassId());
		return "addPage";
	}
	public String add() {
		entity.setDisplay(Visibility.display);
		entity.setSort(newSort());
		newsGenusService.save(entity);
		return "json2";
	}
	public String saveAll() {
		for (NewsGenus nc : entities) {
			nc.setDomain(domain);
		}
		newsClassService.saveOrUpdateAll(entities);
		result = "success";
		return JSON;
	}

	public String remove() {
		newsGenusService.delete(entity);
		return JSON;
	}

	public List<NewsGenus> getEntities() {
		return entities;
	}

	public void setEntities(List<NewsGenus> entities) {
		this.entities = entities;
	}
	private Integer newSort() {
		String hql = "select max(sort) from NewsGenus where domain=?";

		List<Integer> list = (List<Integer>) newsGenusService.find(hql, domain);
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
