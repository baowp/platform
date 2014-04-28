/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserprivilegeServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.UserprivilegeDAO;
import com.abbcc.models.AbcUserprivilege;
import com.abbcc.service.UserprivilegeService;

public class UserprivilegeServiceImpl implements UserprivilegeService {
	private UserprivilegeDAO userprivilegeDAO;

	public void setUserprivilegeDAO(UserprivilegeDAO userprivilegeDAO) {
		this.userprivilegeDAO = userprivilegeDAO;
	}

	public void save(AbcUserprivilege transientInstance) {
		userprivilegeDAO.save(transientInstance);
	}

	public void delete(AbcUserprivilege persistentInstance) {

	}

	public AbcUserprivilege findById(String id) {
		return userprivilegeDAO.findById(id);
	}

	public List<AbcUserprivilege> findByExample(AbcUserprivilege instance) {
		return userprivilegeDAO.findByExample(instance);

	}

	public List<AbcUserprivilege> findAll() {
		return userprivilegeDAO.findAll();

	}

	public void saveOrUpdate(AbcUserprivilege instance) {
		userprivilegeDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return userprivilegeDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return userprivilegeDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return userprivilegeDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return userprivilegeDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return userprivilegeDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		userprivilegeDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return userprivilegeDAO.getCallProcedureResult(procString, params);
	}





}

