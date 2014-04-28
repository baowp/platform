/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "EnterpriseServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-8           Wangjin                      initial
 */

package com.abbcc.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.EnterpriseDAO;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.service.EnterpriseService;

public class EnterpriseServiceImpl extends BaseServiceImpl implements
		EnterpriseService {

	private EnterpriseDAO enterpriseDAO;

	public void setEnterpriseDAO(EnterpriseDAO enterpriseDAO) {
		this.enterpriseDAO = enterpriseDAO;
		setBaseDAO(enterpriseDAO);
	}

	public void save(AbcEnterprise transientInstance) {
		enterpriseDAO.save(transientInstance);
	}

	public void delete(AbcEnterprise persistentInstance) {

	}

	public AbcEnterprise findById(String id) {
		return enterpriseDAO.findById(id);
	}

	public List<AbcEnterprise> findByExample(AbcEnterprise instance) {
		return enterpriseDAO.findByExample(instance);

	}

	public List<AbcEnterprise> findAll() {
		return enterpriseDAO.findAll();

	}

	public void saveOrUpdate(AbcEnterprise instance) {
		enterpriseDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return enterpriseDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return enterpriseDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return enterpriseDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return enterpriseDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return enterpriseDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		enterpriseDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return enterpriseDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public AbcEnterprise findByUserId(String userId) {
		// TODO Auto-generated method stub
		AbcEnterprise enterprise = new AbcEnterprise();
		enterprise.setUserId(userId);
		List<AbcEnterprise> list = enterpriseDAO.findByExample(enterprise);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public PaginationSupport getEnterprisesByIndustry(String industry,int pageSize,int startIndex) {
		AbcEnterprise entity = new AbcEnterprise();
		entity.setIndustry(industry);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity.getClass());
		detachedCriteria.add(Example.create(entity));
		PaginationSupport enterpriseItems = findPageByCriteria(detachedCriteria,pageSize,startIndex);
		return enterpriseItems;
	}

}
