/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminService.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-9           wangjin                      initial
 **/

package com.abbcc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcUser;

/**
 * *AdminService.java
 */
public interface NewsService extends BaseService{
	public void save(AbcNews transientInstance);
	

	public void delete(AbcNews persistentInstance);

	public AbcNews findById(String id);

	public void loadById(AbcNews news,String id);

	public List<AbcNews> findByExample(AbcNews instance);

	public List<AbcNews> findAll();

	public void saveOrUpdate(AbcNews instance);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex);

	public List findAllByCriteria(DetachedCriteria detachedCriteria);

	public int getCountByCriteria(DetachedCriteria detachedCriteria);

	public void callProcedure(String procString, List<Object> params)
			throws Exception;

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception;
	
	public PaginationSupport getNewsByEnterprise(AbcEnterprise enterprise,
			int pageSize, int startIndex);
}
