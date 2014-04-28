/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AttachmentServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.AttachmentDAO;
import com.abbcc.models.AbcAttachment;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.BaseService;

public class AttachmentServiceImpl extends BaseServiceImpl implements AttachmentService {

	private AttachmentDAO attachmentDAO;

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public void save(AbcAttachment transientInstance) {
		attachmentDAO.save(transientInstance);
	}

	public void delete(AbcAttachment persistentInstance) {
		attachmentDAO.delete(persistentInstance);
	}

	public AbcAttachment findById(String id) {
		return attachmentDAO.findById(id);
	}

	public List<AbcAttachment> findByExample(AbcAttachment instance) {
		return attachmentDAO.findByExample(instance);

	}

	public List<AbcAttachment> findAll() {
		return attachmentDAO.findAll();

	}

	public void saveOrUpdate(AbcAttachment instance) {
		attachmentDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return attachmentDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return attachmentDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return attachmentDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return attachmentDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return attachmentDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		attachmentDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return attachmentDAO.getCallProcedureResult(procString, params);
	}


}

