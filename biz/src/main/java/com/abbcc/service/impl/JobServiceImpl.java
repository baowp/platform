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
import com.abbcc.dao.JobDAO;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcJob;
import com.abbcc.service.JobService;

public class JobServiceImpl implements JobService {

	private JobDAO jobDAO;

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	public void save(AbcJob transientInstance) {
		jobDAO.save(transientInstance);
	}

	public void delete(AbcJob persistentInstance) {
		jobDAO.delete(persistentInstance);
	}

	public AbcJob findById(String id) {
		return jobDAO.findById(id);
	}

	public List<AbcJob> findByExample(AbcJob instance) {
		return jobDAO.findByExample(instance);

	}

	public List<AbcJob> findAll() {
		return jobDAO.findAll();

	}

	public void saveOrUpdate(AbcJob instance) {
		jobDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return jobDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return jobDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return jobDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return jobDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return jobDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		jobDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return jobDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public List<AbcJob> findByEntId(String entId) {
		// TODO Auto-generated method stub
		AbcJob job = new AbcJob();
		job.setEnterpriseId(entId);
		List<AbcJob> list = jobDAO.findByExample(job);
		if (list == null || list.size() == 0)
			return null;
		else
			return list;
	}

	@Override
	public List<AbcJob> findByHql(String hql) {
		// TODO Auto-generated method stub
		return jobDAO.findByHql(hql);

	}

}

