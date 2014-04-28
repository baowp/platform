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
import com.abbcc.dao.AdminprivilegeDAO;
import com.abbcc.models.AbcAdminprivilege;
import com.abbcc.service.AdminprivilegeService;

public class AdminprivilegeServiceImpl implements AdminprivilegeService {
	private AdminprivilegeDAO adminprivilegeDAO;

	public void setAdminprivilegeDAO(AdminprivilegeDAO adminprivilegeDAO) {
		this.adminprivilegeDAO = adminprivilegeDAO;
	}

	public void save(AbcAdminprivilege transientInstance) {
		adminprivilegeDAO.save(transientInstance);
	}

	public void delete(AbcAdminprivilege persistentInstance) {

	}

	public AbcAdminprivilege findById(String id) {
		return adminprivilegeDAO.findById(id);
	}

	public List<AbcAdminprivilege> findByExample(AbcAdminprivilege instance) {
		return adminprivilegeDAO.findByExample(instance);

	}

	public List<AbcAdminprivilege> findAll() {
		return adminprivilegeDAO.findAll();

	}

	public void saveOrUpdate(AbcAdminprivilege instance) {
		adminprivilegeDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return adminprivilegeDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return adminprivilegeDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return adminprivilegeDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return adminprivilegeDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return adminprivilegeDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		adminprivilegeDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return adminprivilegeDAO.getCallProcedureResult(procString, params);
	}

}

