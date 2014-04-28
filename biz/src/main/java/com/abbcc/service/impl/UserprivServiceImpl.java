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
import com.abbcc.dao.UserprivDAO;
import com.abbcc.models.AbcUserpriv;
import com.abbcc.models.AbcUserprivilege;
import com.abbcc.service.UserprivService;

public class UserprivServiceImpl implements UserprivService {
	private UserprivDAO userprivDAO;



	public void save(AbcUserpriv transientInstance) {
		userprivDAO.save(transientInstance);
	}

	public void delete(AbcUserpriv persistentInstance) {
		userprivDAO.delete(persistentInstance);
	}

	public AbcUserpriv findById(String id) {
		return userprivDAO.findById(id);
	}

	public List<AbcUserpriv> findByExample(AbcUserpriv instance) {
		return userprivDAO.findByExample(instance);

	}

	public List<AbcUserpriv> findAll() {
		return userprivDAO.findAll();

	}

	public void saveOrUpdate(AbcUserpriv instance) {
		userprivDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return userprivDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return userprivDAO
				.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return userprivDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return userprivDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return userprivDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		userprivDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return userprivDAO.getCallProcedureResult(procString, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AbcUserprivilege> getAdminprivilegeByAdminId(String adminId) {
		String hql = " from AbcUserprivilege a where a.adminprivilegeId in ("
				+ "select b.adminprivilegeId from AbcUserpriv b where b.adminId='"
				+ adminId + "')";
		// TODO Auto-generated method stub
		return userprivDAO.findByHql(hql);
	}

	@Override
	public void addAdminPrivs(List<String> privIds, String userId,
			String addAdminId) {
		for (String privId : privIds) {
			AbcUserpriv instance = new AbcUserpriv();
			instance.setuserId(userId);
			instance.setuserprivilegeId(privId);
			instance.setAdduserId(addAdminId);
			instance.setState(CommonConst.STATENORMAL);
			instance.setAddTime(new Date());
			save(instance);

		}
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAdminPrivs(List<String> privIds, String userId) {
		for (String privId : privIds) {
			AbcUserpriv instance = new AbcUserpriv();
			instance.setuserId(userId);
			instance.setuserprivilegeId(privId);
			List<AbcUserpriv> l = findByExample(instance);
			for (AbcUserpriv userpriv : l) {
				delete(userpriv);
			}
		}
	}

	public void setUserprivDAO(UserprivDAO userprivDAO) {
		this.userprivDAO = userprivDAO;
	}

	@Override
	public int updateUserPopedom(String getuserprivId, String name, String state) {
		// TODO Auto-generated method stub
		return userprivDAO.updateUserPopedom(getuserprivId, name, state);

	}

}
