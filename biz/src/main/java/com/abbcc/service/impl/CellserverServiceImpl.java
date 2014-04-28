/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CellserverServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.CellserverDAO;
import com.abbcc.models.AbcCellserver;
import com.abbcc.service.BaseService;
import com.abbcc.service.CellserverService;

public class CellserverServiceImpl extends BaseServiceImpl implements CellserverService {

	private CellserverDAO cellserverDAO;

	public void setCellserverDAO(CellserverDAO cellserverDAO) {
		this.cellserverDAO = cellserverDAO;
	}

	public void save(AbcCellserver transientInstance) {
		cellserverDAO.save(transientInstance);
	}

	public void delete(AbcCellserver persistentInstance) {

	}

	public AbcCellserver findById(String id) {
		return cellserverDAO.findById(id);
	}

	public List<AbcCellserver> findByExample(AbcCellserver instance) {
		return cellserverDAO.findByExample(instance);

	}

	public List<AbcCellserver> findAll() {
		return cellserverDAO.findAll();

	}

	public void saveOrUpdate(AbcCellserver instance) {
		cellserverDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return cellserverDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return cellserverDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return cellserverDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return cellserverDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return cellserverDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		cellserverDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return cellserverDAO.getCallProcedureResult(procString, params);
	}

}

