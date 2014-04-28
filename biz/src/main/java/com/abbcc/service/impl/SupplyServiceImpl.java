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

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.SupplyDAO;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcSupply;
import com.abbcc.service.SupplyService;

public class SupplyServiceImpl extends BaseServiceImpl implements SupplyService {

	private SupplyDAO supplyDAO;

	public void setSupplyDAO(SupplyDAO supplyDAO) {
		this.supplyDAO = supplyDAO;
		setBaseDAO(supplyDAO);
	}

	public void save(AbcSupply transientInstance) {
		supplyDAO.save(transientInstance);
	}

	public void delete(AbcSupply persistentInstance) {
		supplyDAO.delete(persistentInstance);
	}

	public AbcSupply findById(String id) {
		return supplyDAO.findById(id);
	}

	public List<AbcSupply> findByExample(AbcSupply instance) {
		return supplyDAO.findByExample(instance);

	}

	public List<AbcSupply> findAll() {
		return supplyDAO.findAll();

	}

	public void saveOrUpdate(AbcSupply instance) {
		supplyDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return supplyDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return supplyDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return supplyDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return supplyDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return supplyDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		supplyDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return supplyDAO.getCallProcedureResult(procString, params);
	}

	public PaginationSupport getPublished(AbcEnterprise enterprise,
			int pageSize, int startIndex) {
		AbcSupply entity=new AbcSupply();
		entity.setEnterpriseId(enterprise.getEnterpriseId());
		entity.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.add(Restrictions.gt("endTime", new Date()));
		detachedCriteria.addOrder(Order.desc("addTime"));
		PaginationSupport pageItems = findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageItems;
	}
}
