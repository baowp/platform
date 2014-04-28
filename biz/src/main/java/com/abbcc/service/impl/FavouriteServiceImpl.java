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
import com.abbcc.dao.FavouriteDAO;
import com.abbcc.models.AbcFavourite;
import com.abbcc.service.FavouriteService;

public class FavouriteServiceImpl extends BaseServiceImpl implements FavouriteService {

	private FavouriteDAO favouriteDAO;

	public void setFavouriteDAO(FavouriteDAO favouriteDAO) {
		this.favouriteDAO = favouriteDAO;
	}

	public void save(AbcFavourite transientInstance) {
		favouriteDAO.save(transientInstance);
	}

	public void delete(AbcFavourite persistentInstance) {
		favouriteDAO.delete(persistentInstance);
	}

	public AbcFavourite findById(String id) {
		return favouriteDAO.findById(id);
	}

	public List<AbcFavourite> findByExample(AbcFavourite instance) {
		return favouriteDAO.findByExample(instance);

	}

	public List<AbcFavourite> findAll() {
		return favouriteDAO.findAll();

	}

	public void saveOrUpdate(AbcFavourite instance) {
		favouriteDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return favouriteDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return favouriteDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return favouriteDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return favouriteDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return favouriteDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		favouriteDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return favouriteDAO.getCallProcedureResult(procString, params);
	}

}

