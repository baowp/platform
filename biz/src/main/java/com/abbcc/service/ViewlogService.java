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
 * 
 * 2010-04-17			raystone					add fun findByExampleByDate
 **/

package com.abbcc.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcViewlog;

/**
 * *AdminService.java
 */
public interface ViewlogService {
	public void save(AbcViewlog transientInstance);

	public void delete(AbcViewlog persistentInstance);

	public AbcViewlog findById(String id);

	public List<AbcViewlog> findByExample(AbcViewlog instance);

	public List<AbcViewlog> findAll();

	public void saveOrUpdate(AbcViewlog instance);

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
	
	public List findByExampleByDate(AbcViewlog instance, DetachedCriteria detachedCriteria, Date startCalendar, Date endCalendar); 
}
