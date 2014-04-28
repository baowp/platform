/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CategoryServiceImpl.java is the copyrighted,
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

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.CustomDAO;
import com.abbcc.models.AbcCustom;
import com.abbcc.service.CustomService;

public class CustomServiceImpl extends BaseServiceImpl implements CustomService {

	private CustomDAO customDAO;

	public void setCustomDAO(CustomDAO customDAO) {
		this.customDAO = customDAO;
	}

	public void save(AbcCustom transientInstance) {
		customDAO.save(transientInstance);
	}

	public void delete(AbcCustom persistentInstance) {
		customDAO.delete(persistentInstance);
	}

	public AbcCustom findById(String id) {
		return customDAO.findById(id);
	}

	public List<AbcCustom> findByExample(AbcCustom instance) {
		return customDAO.findByExample(instance);

	}

	public List<AbcCustom> findAll() {
		return customDAO.findAll();

	}

	public void saveOrUpdate(AbcCustom instance) {
		customDAO.saveOrUpdate(instance);

	}

	public void update(AbcCustom instance) {
		customDAO.update(instance);

	}

	public void load(AbcCustom entity, Serializable id) {
		customDAO.load(entity, id);
	}

	public int updateColumn(Serializable id, String column, String value) {
		return customDAO.updateColumn(id, column, value);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return customDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return customDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return customDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List<?> findAllByCriteria(DetachedCriteria detachedCriteria) {
		return customDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return customDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		customDAO.callProcedure(procString, params);
	}

	public List<?> getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return customDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public List<AbcCustom> findByHql(String hql) {
		// TODO Auto-generated method stub
		return customDAO.findByHql(hql);
	}

}
