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
import com.abbcc.dao.EnterpcontactDAO;
import com.abbcc.models.AbcEnterpcontact;
import com.abbcc.service.EnterpcontactService;

public class EnterpcontactServiceImpl extends BaseServiceImpl implements EnterpcontactService {

	private EnterpcontactDAO enterpcontactDAO;

	public void setEnterpcontactDAO(EnterpcontactDAO enterpcontactDAO) {
		this.enterpcontactDAO = enterpcontactDAO;
	}

	public void save(AbcEnterpcontact transientInstance) {
		enterpcontactDAO.save(transientInstance);
	}

	public void delete(AbcEnterpcontact persistentInstance) {
		enterpcontactDAO.delete(persistentInstance);
	}

	public AbcEnterpcontact findById(String id) {
		return enterpcontactDAO.findById(id);
	}

	public List<AbcEnterpcontact> findByExample(AbcEnterpcontact instance) {
		return enterpcontactDAO.findByExample(instance);

	}

	public List<AbcEnterpcontact> findAll() {
		return enterpcontactDAO.findAll();

	}

	public void saveOrUpdate(AbcEnterpcontact instance) {
		enterpcontactDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return enterpcontactDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return enterpcontactDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return enterpcontactDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return enterpcontactDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return enterpcontactDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		enterpcontactDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return enterpcontactDAO.getCallProcedureResult(procString, params);
	}

}

