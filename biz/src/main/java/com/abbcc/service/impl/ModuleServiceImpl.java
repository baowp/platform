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
import com.abbcc.dao.AdminDAO;
import com.abbcc.dao.ModuleDAO;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcModule;
import com.abbcc.service.DomainbindService;
import com.abbcc.service.ModuleService;

public class ModuleServiceImpl implements ModuleService {

	private ModuleDAO moduleDAO;

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	public void save(AbcModule transientInstance) {
		moduleDAO.save(transientInstance);
	}

	public void delete(AbcModule persistentInstance) {

	}

	public AbcModule findById(String id) {
		return moduleDAO.findById(id);
	}

	public List<AbcModule> findByExample(AbcModule instance) {
		return moduleDAO.findByExample(instance);

	}

	public List<AbcModule> findAll() {
		return moduleDAO.findAll();

	}

	public void saveOrUpdate(AbcModule instance) {
		moduleDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return moduleDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return moduleDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return moduleDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return moduleDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return moduleDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		moduleDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return moduleDAO.getCallProcedureResult(procString, params);
	}

}

