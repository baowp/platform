/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ProductDAOTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-10           baowp                      initial
 */

package com.abbcc.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.constant.SupplyType;

public class ProductDAOTest {

	protected final org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(this.getClass());

	HibernateTemplate hibernateTemplate;
	BaseDAO baseDAO;

	@Before
	public void setHibernateTemplate() {
		SessionFactory sessionFactory = (SessionFactory) BeansFactory
				.get("sessionFactory");
		if (this.hibernateTemplate == null) {
			this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		} else {
			this.hibernateTemplate.setSessionFactory(sessionFactory);
		}
	}

	public void go() {
		String queryString = "from AbcCategory where name=:name";
		String[] paramNames = new String[] { "name" };
		String[] values = new String[] { "在" };
		@SuppressWarnings("unchecked")
		List<AbcCategory> list = hibernateTemplate.findByNamedParam(
				queryString, paramNames, values);
		log.info(list.size());
	}

	public void go2() {
		String queryString = "select count(distinct sort) from AbcCategory where adduserId=? and type=? and state=?";
		String[] values = new String[] { "User_000000000000000000000000013",
				"product", "01" };
		@SuppressWarnings("unchecked")
		List list = hibernateTemplate.find(queryString, values);
		// AbcCategory ac=new AbcCategory();
		// list=hibernateTemplate.findByExample(ac);
		log.info(list.size());
		// AbcCategory ac=new AbcCategory();
		// ObjectUtil.copy(list.get(0), ac);
		queryString = "from AbcSupply where type>?";
		list = hibernateTemplate.find(queryString, SupplyType.AG);
		log.info(list.size());
		DetachedCriteria dc = DetachedCriteria.forClass(AbcSupply.class);
		dc.add(Restrictions.gt("type", SupplyType.AG));
		log.info(baseDAO.findAllByCriteria(dc).size());
	}

	@Test
	public void go3() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class);
		detachedCriteria.add(Property.forName("price").between(null,(float)4));
		PaginationSupport<AbcProduct> products = baseDAO.findPageByCriteria(
				detachedCriteria, 20, 0, CriteriaSpecification.ROOT_ENTITY);
		log.info(products.size());
		for(AbcProduct p:products){
			log.info(p.getPrice());
		}
	}

	@Before
	public void setBaseDAO() {
		this.baseDAO = (BaseDAO) BeansFactory.get("baseDAO");
	}
}
