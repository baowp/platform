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

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.UpgradelogDAO;
import com.abbcc.models.AbcUpgradelog;
import com.abbcc.service.UpgradelogService;

public class UpgradelogServiceImpl implements UpgradelogService {

	private UpgradelogDAO upgradelogDAO;

	public void setUpgradelogDAO(UpgradelogDAO upgradelogDAO) {
		this.upgradelogDAO = upgradelogDAO;
	}

	public void save(AbcUpgradelog transientInstance) {
		upgradelogDAO.save(transientInstance);
	}

	public void delete(AbcUpgradelog persistentInstance) {

	}

	public AbcUpgradelog findById(String id) {
		return upgradelogDAO.findById(id);
	}

	public List<AbcUpgradelog> findByExample(AbcUpgradelog instance) {
		return upgradelogDAO.findByExample(instance);

	}

	public List<AbcUpgradelog> findAll() {
		return upgradelogDAO.findAll();

	}

	public void saveOrUpdate(AbcUpgradelog instance) {
		upgradelogDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return upgradelogDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return upgradelogDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return upgradelogDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return upgradelogDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return upgradelogDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		upgradelogDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return upgradelogDAO.getCallProcedureResult(procString, params);
	}
}

