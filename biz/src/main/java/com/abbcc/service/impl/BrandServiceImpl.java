/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "BrandServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.BrandDAO;
import com.abbcc.models.AbcBrand;
import com.abbcc.service.BrandService;

public class BrandServiceImpl extends BaseServiceImpl implements BrandService {

	private BrandDAO brandDAO;

	public void setBrandDAO(BrandDAO brandDAO) {
		this.brandDAO = brandDAO;
		setBaseDAO(brandDAO);
	}

	public void save(AbcBrand transientInstance) {
		brandDAO.save(transientInstance);
	}

	public void delete(AbcBrand persistentInstance) {

	}

	public AbcBrand findById(String id) {
		return brandDAO.findById(id);
	}

	public List<AbcBrand> findByExample(AbcBrand instance) {
		return brandDAO.findByExample(instance);

	}

	public List<AbcBrand> findAll() {
		return brandDAO.findAll();

	}

	public void saveOrUpdate(AbcBrand instance) {
		brandDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return brandDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return brandDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return brandDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return brandDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return brandDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		brandDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return brandDAO.getCallProcedureResult(procString, params);
	}

}
