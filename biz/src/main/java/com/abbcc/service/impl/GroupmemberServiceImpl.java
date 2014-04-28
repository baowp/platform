/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "GroupmemberServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.GroupmemberDAO;
import com.abbcc.models.AbcGroupmember;
import com.abbcc.service.GroupmemberService;

public class GroupmemberServiceImpl extends BaseServiceImpl implements GroupmemberService {

	private GroupmemberDAO groupmemberDAO;

	public void setGroupmemberDAO(GroupmemberDAO groupmemberDAO) {
		this.groupmemberDAO = groupmemberDAO;
	}

	public void save(AbcGroupmember transientInstance) {
		groupmemberDAO.save(transientInstance);
	}

	public void delete(AbcGroupmember persistentInstance) {

	}

	public AbcGroupmember findById(String id) {
		return groupmemberDAO.findById(id);
	}

	public List<AbcGroupmember> findByExample(AbcGroupmember instance) {
		return groupmemberDAO.findByExample(instance);

	}

	public List<AbcGroupmember> findAll() {
		return groupmemberDAO.findAll();

	}

	public void saveOrUpdate(AbcGroupmember instance) {
		groupmemberDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return groupmemberDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return groupmemberDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return groupmemberDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return groupmemberDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return groupmemberDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		groupmemberDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return groupmemberDAO.getCallProcedureResult(procString, params);
	}

}
