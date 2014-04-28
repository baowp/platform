/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "EnterpriseService.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-8           Wangjin                      initial
*/

package com.abbcc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcEnterprise;

public interface EnterpriseService extends BaseService{
	public void save(AbcEnterprise transientInstance);

	public void delete(AbcEnterprise persistentInstance);

	public AbcEnterprise findById(String id);
	
	public AbcEnterprise findByUserId(String userId);

	public List<AbcEnterprise> findByExample(AbcEnterprise instance);

	public List<AbcEnterprise> findAll();

	public void saveOrUpdate(AbcEnterprise instance);

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
	

	@SuppressWarnings("unchecked")
	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception;
	
	public PaginationSupport getEnterprisesByIndustry(String industry,int pageSize,int startIndex);

}

