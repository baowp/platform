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
import com.abbcc.dao.UsergroupDAO;
import com.abbcc.models.AbcUsergroup;
import com.abbcc.service.UsergroupService;

public class UsergroupServiceImpl implements UsergroupService {

	private UsergroupDAO usergroupDAO;

	public void setUsergroupDAO(UsergroupDAO usergroupDAO) {
		this.usergroupDAO = usergroupDAO;
	}

	public void save(AbcUsergroup transientInstance) {
		usergroupDAO.save(transientInstance);
	}

	public void delete(AbcUsergroup persistentInstance) {
		usergroupDAO.delete(persistentInstance);
	}

	public AbcUsergroup findById(String id) {
		return usergroupDAO.findById(id);
	}
	public AbcUsergroup loadById(AbcUsergroup persistentInstance,String id) {
		return usergroupDAO.loadById( persistentInstance, id);
	}

	public List<AbcUsergroup> findByExample(AbcUsergroup instance) {
		return usergroupDAO.findByExample(instance);

	}

	public List<AbcUsergroup> findAll() {
		return usergroupDAO.findAll();

	}

	public void saveOrUpdate(AbcUsergroup instance) {
		usergroupDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return usergroupDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return usergroupDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return usergroupDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return usergroupDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return usergroupDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		usergroupDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return usergroupDAO.getCallProcedureResult(procString, params);
	}

}

