/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DomainbindServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.LayoutmoduleDAO;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.service.LayoutmoduleService;

public class LayoutmoduleServiceImpl extends BaseServiceImpl implements LayoutmoduleService {

	private LayoutmoduleDAO layoutmoduleDAO;

	public void setLayoutmoduleDAO(LayoutmoduleDAO layoutmoduleDAO) {
		this.layoutmoduleDAO = layoutmoduleDAO;
		setBaseDAO(layoutmoduleDAO);
	}

	public void save(AbcLayoutmodule transientInstance) {
		layoutmoduleDAO.save(transientInstance);
	}

	public void delete(AbcLayoutmodule persistentInstance) {

	}

	public AbcLayoutmodule findById(String id) {
		return layoutmoduleDAO.findById(id);
	}

	public List<AbcLayoutmodule> findByExample(AbcLayoutmodule instance) {
		return layoutmoduleDAO.findByExample(instance);

	}

	public List<AbcLayoutmodule> findAll() {
		return layoutmoduleDAO.findAll();

	}

	public void saveOrUpdate(AbcLayoutmodule instance) {
		layoutmoduleDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return layoutmoduleDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return layoutmoduleDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return layoutmoduleDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return layoutmoduleDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return layoutmoduleDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		layoutmoduleDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return layoutmoduleDAO.getCallProcedureResult(procString, params);
	}

}

