/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminprivilegeServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-9           Wangjin                      initial
*/

package com.abbcc.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.NewsDAO;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcUser;
import com.abbcc.service.BaseService;
import com.abbcc.service.NewsService;
import com.abbcc.util.StringUtil;

public class NewsServiceImpl extends BaseServiceImpl implements NewsService {

	private NewsDAO newsDAO;

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	public void save(AbcNews transientInstance) {
		newsDAO.save(transientInstance);
	}

	public void delete(AbcNews persistentInstance) {
		newsDAO.delete(persistentInstance);
	}

	public AbcNews findById(String id) {
		return newsDAO.findById(id);
	}

	public void loadById(AbcNews news,String id){
		newsDAO.loadById(news, id);
	}
	public List<AbcNews> findByExample(AbcNews instance) {
		return newsDAO.findByExample(instance);

	}

	public List<AbcNews> findAll() {
		return newsDAO.findAll();

	}

	public void saveOrUpdate(AbcNews instance) {
		newsDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return newsDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return newsDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return newsDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return newsDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return newsDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		newsDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return newsDAO.getCallProcedureResult(procString, params);
	}
	
	public PaginationSupport getNewsByEnterprise(AbcEnterprise enterprise, 
			int pageSize, int startIndex) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPEUSER));
		detachedCriteria.add(Restrictions.eq("enterpriseId", enterprise
				.getEnterpriseId()));
		PaginationSupport pageItems = findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageItems;
	}

}

