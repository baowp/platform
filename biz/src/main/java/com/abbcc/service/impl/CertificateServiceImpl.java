/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CertificateServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.CertificateDAO;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.service.BaseService;
import com.abbcc.service.CertificateService;

public class CertificateServiceImpl extends BaseServiceImpl implements CertificateService{
	private CertificateDAO certificateDAO;

	public void setCertificateDAO(CertificateDAO certificateDAO) {
		this.certificateDAO = certificateDAO;
	}

	public void save(AbcCertificate transientInstance) {
		certificateDAO.save(transientInstance);
	}

	public void delete(AbcCertificate persistentInstance) {
		certificateDAO.delete(persistentInstance);
	}

	public AbcCertificate findById(String id) {
		return certificateDAO.findById(id);
	}

	public List<AbcCertificate> findByExample(AbcCertificate instance) {
		return certificateDAO.findByExample(instance);

	}

	public List<AbcCertificate> findAll() {
		return certificateDAO.findAll();

	}

	public void saveOrUpdate(AbcCertificate instance) {
		certificateDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return certificateDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return certificateDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return certificateDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return certificateDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return certificateDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		certificateDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return certificateDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public List<AbcCertificate> findByEntId(String EntId) {
		// TODO Auto-generated method stub
		AbcCertificate certificate = new AbcCertificate();
		certificate.setEnterpriseId(EntId);
		List<AbcCertificate> list = certificateDAO.findByExample(certificate);
		if (list == null || list.size() == 0)
			return null;
		else
			return list;
	}

	@Override
	public List<AbcCertificate> findByHql(String hql) {
		// TODO Auto-generated method stub
		return certificateDAO.findByHql(hql);
	}


}

