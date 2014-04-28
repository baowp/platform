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
import com.abbcc.dao.DomainbindDAO;
import com.abbcc.models.AbcDomainbind;
import com.abbcc.service.DomainbindService;

public class DomainbindServiceImpl implements DomainbindService {

	private DomainbindDAO domainbindDAO;

	public void setDomainbindDAO(DomainbindDAO domainbindDAO) {
		this.domainbindDAO = domainbindDAO;
	}

	public void save(AbcDomainbind transientInstance) {
		domainbindDAO.save(transientInstance);
	}

	public void delete(AbcDomainbind persistentInstance) {

	}

	public AbcDomainbind findById(String id) {
		return domainbindDAO.findById(id);
	}

	public List<AbcDomainbind> findByExample(AbcDomainbind instance) {
		return domainbindDAO.findByExample(instance);

	}

	public List<AbcDomainbind> findAll() {
		return domainbindDAO.findAll();

	}

	public void saveOrUpdate(AbcDomainbind instance) {
		domainbindDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return domainbindDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return domainbindDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return domainbindDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return domainbindDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return domainbindDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		domainbindDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return domainbindDAO.getCallProcedureResult(procString, params);
	}

}

