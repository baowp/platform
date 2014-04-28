/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminuserprivServiceImpl.java is the copyrighted,
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

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.AdminuserprivDAO;
import com.abbcc.models.AbcAdminprivilege;
import com.abbcc.models.AbcAdminuserpriv;
import com.abbcc.service.AdminuserprivService;

public class AdminuserprivServiceImpl implements AdminuserprivService {
	private AdminuserprivDAO adminuserprivDAO;

	public void setAdminuserprivDAO(AdminuserprivDAO adminuserprivDAO) {
		this.adminuserprivDAO = adminuserprivDAO;
	}

	public void save(AbcAdminuserpriv transientInstance) {
		adminuserprivDAO.save(transientInstance);
	}

	public void delete(AbcAdminuserpriv persistentInstance) {
		adminuserprivDAO.delete(persistentInstance);
	}

	public AbcAdminuserpriv findById(String id) {
		return adminuserprivDAO.findById(id);
	}

	public List<AbcAdminuserpriv> findByExample(AbcAdminuserpriv instance) {
		return adminuserprivDAO.findByExample(instance);

	}

	public List<AbcAdminuserpriv> findAll() {
		return adminuserprivDAO.findAll();

	}

	public void saveOrUpdate(AbcAdminuserpriv instance) {
		adminuserprivDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return adminuserprivDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return adminuserprivDAO
				.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return adminuserprivDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return adminuserprivDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return adminuserprivDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		adminuserprivDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return adminuserprivDAO.getCallProcedureResult(procString, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbcAdminprivilege> getAdminprivilegeByAdminId(String adminId) {
		String hql = " from AbcAdminprivilege a where a.adminprivilegeId in ("
				+ "select b.adminprivilegeId from AbcAdminuserpriv b where b.adminId='"
				+ adminId + "')";
		// TODO Auto-generated method stub
		return adminuserprivDAO.findByHql(hql);
	}

	@Override
	public void addAdminPrivs(List<String> privIds, String adminId,
			String addAdminId) {
		for (String privId : privIds) {
			AbcAdminuserpriv instance = new AbcAdminuserpriv();
			instance.setAdminId(adminId);
			instance.setAdminprivilegeId(privId);
			instance.setAddAdminId(addAdminId);
			instance.setState(CommonConst.STATENORMAL);
			instance.setAddTime(new Date());
			save(instance);

		}
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAdminPrivs(List<String> privIds, String adminId) {
		for (String privId : privIds) {
			AbcAdminuserpriv instance = new AbcAdminuserpriv();
			instance.setAdminId(adminId);
			instance.setAdminprivilegeId(privId);
			List<AbcAdminuserpriv> l = findByExample(instance);
			for (AbcAdminuserpriv userpriv : l) {
				delete(userpriv);
			}
		}
	}
	public int updateUserPopedom(String getuserprivId, String name, String state) {
		// TODO Auto-generated method stub
		return adminuserprivDAO.updateUserPopedom(getuserprivId, name, state);

	}

}
