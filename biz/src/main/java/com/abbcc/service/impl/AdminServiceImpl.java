/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-6           yixiugg                      initial
 **/

package com.abbcc.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.AdminDAO;
import com.abbcc.models.AbcAdmin;
import com.abbcc.service.AdminService;

/**
 * *AdminServiceImpl.java
 */
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {
	private AdminDAO adminDAO;

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	public void save(AbcAdmin transientInstance) {
		adminDAO.save(transientInstance);
	}

	public void delete(AbcAdmin persistentInstance) {

	}

	public AbcAdmin findById(String id) {
		return adminDAO.findById(id);
	}
	public void loadById(AbcAdmin abcAdmin,String id){
		adminDAO.loadById(abcAdmin, id);
	}
	public List<AbcAdmin> findByExample(AbcAdmin instance) {
		return adminDAO.findByExample(instance);

	}

	public List<AbcAdmin> findAll() {
		return adminDAO.findAll();

	}

	public void saveOrUpdate(AbcAdmin instance) {
		adminDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return adminDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return adminDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return adminDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return adminDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return adminDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		adminDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return adminDAO.getCallProcedureResult(procString, params);
	}

	public List<AbcAdmin> getAdminByUsername(String username) {
		AbcAdmin admin = new AbcAdmin();
		admin.setUsername(username);
		return adminDAO.findByExample(admin);
		// TODO Auto-generated method stub
	}
	public List<AbcAdmin> getAdminByEmail(String email) {
		AbcAdmin admin = new AbcAdmin();
		admin.setEmail(email);
		return adminDAO.findByExample(admin);
		// TODO Auto-generated method stub
	}
}
