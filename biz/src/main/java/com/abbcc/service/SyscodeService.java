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
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcSyscode;

/**
 * *AdminService.java
 */
public interface SyscodeService extends BaseService{
	public void save(AbcSyscode transientInstance);

	public void delete(AbcSyscode persistentInstance);

	public AbcSyscode findById(String id);

	public List<AbcSyscode> findByExample(AbcSyscode instance);

	public List<AbcSyscode> findAll();

	public void saveOrUpdate(AbcSyscode instance);

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
	public List<AbcSyscode> getSyscodesByType(String type,String state);
	public List<AbcSyscode> getSubSyscodesByFather(String syscodeId,String state);
	public Map<String,String>getBankMaps();
}
