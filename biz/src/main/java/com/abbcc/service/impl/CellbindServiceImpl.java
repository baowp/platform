/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CellbindServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.CellbindDAO;
import com.abbcc.models.AbcCellbind;
import com.abbcc.service.BaseService;
import com.abbcc.service.CellbindService;

public class CellbindServiceImpl extends BaseServiceImpl implements CellbindService {

	private CellbindDAO cellbindDAO;

	public void setCellbindDAO(CellbindDAO cellbindDAO) {
		this.cellbindDAO = cellbindDAO;
	}

	public void save(AbcCellbind transientInstance) {
		cellbindDAO.save(transientInstance);
	}

	public void delete(AbcCellbind persistentInstance) {

	}

	public AbcCellbind findById(String id) {
		return cellbindDAO.findById(id);
	}

	public List<AbcCellbind> findByExample(AbcCellbind instance) {
		return cellbindDAO.findByExample(instance);

	}

	public List<AbcCellbind> findAll() {
		return cellbindDAO.findAll();

	}

	public void saveOrUpdate(AbcCellbind instance) {
		cellbindDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return cellbindDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return cellbindDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return cellbindDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return cellbindDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return cellbindDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		cellbindDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return cellbindDAO.getCallProcedureResult(procString, params);
	}

}

