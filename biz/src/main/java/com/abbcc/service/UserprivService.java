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
import com.abbcc.models.AbcUserpriv;
import com.abbcc.models.AbcUserprivilege;

/**
 * *AdminService.java
 */
public interface UserprivService {
	public void save(AbcUserpriv transientInstance);

	public void delete(AbcUserpriv persistentInstance);

	public AbcUserpriv findById(String id);

	public List<AbcUserpriv> findByExample(AbcUserpriv instance);

	public List<AbcUserpriv> findAll();

	public void saveOrUpdate(AbcUserpriv instance);
	
	public int updateUserPopedom(String getuserprivId, String name, String state);

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

	public List<AbcUserprivilege> getAdminprivilegeByAdminId(String userId);

	public void deleteAdminPrivs(List<String> privIds, String userId);

	public void addAdminPrivs(List<String> privIds, String userId,
			String addAdminId);
}
