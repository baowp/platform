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
 * 
 * 2010-04-17			raystone					add fun findByExampleByDate
*/

package com.abbcc.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.ViewlogDAO;
import com.abbcc.models.AbcViewlog;
import com.abbcc.service.ViewlogService;

public class ViewlogServiceImpl implements ViewlogService {

	private ViewlogDAO viewlogDAO;

	public void setViewlogDAO(ViewlogDAO viewlogDAO) {
		this.viewlogDAO = viewlogDAO;
	}

	public void save(AbcViewlog transientInstance) {
		viewlogDAO.save(transientInstance);
	}

	public void delete(AbcViewlog persistentInstance) {

	}

	public AbcViewlog findById(String id) {
		return viewlogDAO.findById(id);
	}

	public List<AbcViewlog> findByExample(AbcViewlog instance) {
		return viewlogDAO.findByExample(instance);

	}

	public List<AbcViewlog> findAll() {
		return viewlogDAO.findAll();

	}

	public void saveOrUpdate(AbcViewlog instance) {
		viewlogDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return viewlogDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return viewlogDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return viewlogDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return viewlogDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return viewlogDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		viewlogDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return viewlogDAO.getCallProcedureResult(procString, params);
	}
	
	public List findByExampleByDate(AbcViewlog instance, DetachedCriteria detachedCriteria, Date startCalendar, Date endCalendar){
		return viewlogDAO.findByExampleByDate(instance, detachedCriteria, startCalendar, endCalendar);
	}

}

