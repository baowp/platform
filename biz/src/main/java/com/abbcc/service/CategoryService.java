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

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcCategory;

/**
 * *AdminService.java
 */
public interface CategoryService extends BaseService {
	public void save(AbcCategory transientInstance);

	public List<AbcCategory> findByHql(String hql);

	public void delete(AbcCategory persistentInstance);

	public AbcCategory findById(String id);

	public List<AbcCategory> findByExample(AbcCategory instance);

	public List<AbcCategory> findAll();

	public void saveOrUpdate(AbcCategory instance);

	public void update(AbcCategory instance);

	public void load(AbcCategory entity, Serializable id);

	public int updateColumn(Serializable id, String column, String value);

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
	
	public void changeSort(AbcCategory category1, AbcCategory category2);
	
	public AbcCategory cascadeVisibleCategory(String categoryId);
	
	public AbcCategory cascadeVisibleCategory(AbcCategory category);
	
	public List<String> cascadeVisibleIds(String categoryId);
}
