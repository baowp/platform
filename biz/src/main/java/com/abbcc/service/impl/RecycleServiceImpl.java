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

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.RecycleDAO;
import com.abbcc.models.AbcRecycle;
import com.abbcc.service.RecycleService;

public class RecycleServiceImpl extends BaseServiceImpl implements
		RecycleService {

	private RecycleDAO recycleDAO;

	public void setRecycleDAO(RecycleDAO recycleDAO) {
		this.recycleDAO = recycleDAO;
		setBaseDAO(recycleDAO);
	}

	public void save(AbcRecycle transientInstance) {
		recycleDAO.save(transientInstance);
	}

	public void delete(AbcRecycle persistentInstance) {
		recycleDAO.delete(persistentInstance);
	}

	public AbcRecycle findById(String id) {
		return recycleDAO.findById(id);
	}

	public List<AbcRecycle> findByExample(AbcRecycle instance) {
		return recycleDAO.findByExample(instance);

	}

	public List<AbcRecycle> findAll() {
		return recycleDAO.findAll();

	}

	public void saveOrUpdate(AbcRecycle instance) {
		recycleDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return recycleDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return recycleDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return recycleDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return recycleDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return recycleDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		recycleDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return recycleDAO.getCallProcedureResult(procString, params);
	}

}
