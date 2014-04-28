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
 * 2009-12-6           yixiugg                      initial
 **/

package com.abbcc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAdmin;

/**
 * *AdminService.java
 */
public interface AdminService extends BaseService{
	public void save(AbcAdmin transientInstance);

	public void delete(AbcAdmin persistentInstance);

	public AbcAdmin findById(String id);
	public void loadById(AbcAdmin abcAdmin,String id);
	public List<AbcAdmin> findByExample(AbcAdmin instance);

	public List<AbcAdmin> findAll();

	public void saveOrUpdate(AbcAdmin instance);

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
	public List<AbcAdmin> getAdminByUsername(String username);
	public List<AbcAdmin> getAdminByEmail(String email);
}
