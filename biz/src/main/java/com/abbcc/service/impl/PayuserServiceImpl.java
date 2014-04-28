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
import com.abbcc.dao.PayuserDAO;
import com.abbcc.models.AbcPayuser;
import com.abbcc.service.PayuserService;

public class PayuserServiceImpl implements PayuserService {

	private PayuserDAO payuserDAO;

	public void setPayuserDAO(PayuserDAO payuserDAO) {
		this.payuserDAO = payuserDAO;
	}

	public void save(AbcPayuser transientInstance) {
		payuserDAO.save(transientInstance);
	}

	public void delete(AbcPayuser persistentInstance) {

	}

	public AbcPayuser findById(String id) {
		return payuserDAO.findById(id);
	}

	public List<AbcPayuser> findByExample(AbcPayuser instance) {
		return payuserDAO.findByExample(instance);

	}

	public List<AbcPayuser> findAll() {
		return payuserDAO.findAll();

	}

	public void saveOrUpdate(AbcPayuser instance) {
		payuserDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return payuserDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return payuserDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return payuserDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return payuserDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return payuserDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		payuserDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return payuserDAO.getCallProcedureResult(procString, params);
	}
}

