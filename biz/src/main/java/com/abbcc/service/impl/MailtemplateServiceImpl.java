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
import com.abbcc.dao.MailtemplateDAO;
import com.abbcc.models.AbcMailtemplate;
import com.abbcc.service.MailtemplateService;

public class MailtemplateServiceImpl implements MailtemplateService {

	private MailtemplateDAO mailtemplateDAO;

	public void setMailtemplateDAO(MailtemplateDAO mailtemplateDAO) {
		this.mailtemplateDAO = mailtemplateDAO;
	}

	public void save(AbcMailtemplate transientInstance) {
		mailtemplateDAO.save(transientInstance);
	}

	public void delete(AbcMailtemplate persistentInstance) {

	}

	public AbcMailtemplate findById(String id) {
		return mailtemplateDAO.findById(id);
	}

	public List<AbcMailtemplate> findByExample(AbcMailtemplate instance) {
		return mailtemplateDAO.findByExample(instance);

	}

	public List<AbcMailtemplate> findAll() {
		return mailtemplateDAO.findAll();

	}

	public void saveOrUpdate(AbcMailtemplate instance) {
		mailtemplateDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return mailtemplateDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return mailtemplateDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return mailtemplateDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return mailtemplateDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return mailtemplateDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		mailtemplateDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return mailtemplateDAO.getCallProcedureResult(procString, params);
	}

}

