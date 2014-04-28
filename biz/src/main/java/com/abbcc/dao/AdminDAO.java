/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminDAOImol.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-5           yixiugg                      initial
 **/

package com.abbcc.dao;

import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAdmin;

/**
 * *AdminDAOImol.java
 */
public interface AdminDAO {
	public void save(AbcAdmin transientInstance);

	public void delete(AbcAdmin persistentInstance);

	public AbcAdmin findById(String id);
	public void loadById(AbcAdmin abcAdmin,String id);
	public List<AbcAdmin> findByExample(AbcAdmin instance);

	public List<AbcAdmin> findAll();

	public void saveOrUpdate(AbcAdmin instance);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex);

	public List findAllByCriteria(final DetachedCriteria detachedCriteria);

	public int getCountByCriteria(final DetachedCriteria detachedCriteria);

	public void callProcedure(String procString, List<Object> params)
			throws Exception;

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception;
	
	public List  getAdminByPaoding() throws ParseException;
}
