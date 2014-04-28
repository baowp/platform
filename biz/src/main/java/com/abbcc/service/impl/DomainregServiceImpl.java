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
import com.abbcc.dao.DomainregDAO;
import com.abbcc.models.AbcDomainreg;
import com.abbcc.service.DomainregService;

public class DomainregServiceImpl implements DomainregService {
	private DomainregDAO domainregDAO;

	public void setDomainregDAO(DomainregDAO domainregDAO) {
		this.domainregDAO = domainregDAO;
	}

	public void save(AbcDomainreg transientInstance) {
		domainregDAO.save(transientInstance);
	}

	public void delete(AbcDomainreg persistentInstance) {

	}

	public AbcDomainreg findById(String id) {
		return domainregDAO.findById(id);
	}

	public List<AbcDomainreg> findByExample(AbcDomainreg instance) {
		return domainregDAO.findByExample(instance);

	}

	public List<AbcDomainreg> findAll() {
		return domainregDAO.findAll();

	}

	public void saveOrUpdate(AbcDomainreg instance) {
		domainregDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return domainregDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return domainregDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return domainregDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return domainregDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return domainregDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		domainregDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return domainregDAO.getCallProcedureResult(procString, params);
	}

}

