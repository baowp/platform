/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AlbumServiceImpl.java is the copyrighted,
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
import com.abbcc.dao.AlbumDAO;
import com.abbcc.models.AbcAlbum;
import com.abbcc.service.AlbumService;
import com.abbcc.service.BaseService;

public class AlbumServiceImpl extends BaseServiceImpl implements AlbumService {

	private AlbumDAO albumDAO;

	public void setAlbumDAO(AlbumDAO albumDAO) {
		this.albumDAO = albumDAO;
	}

	public void save(AbcAlbum transientInstance) {
		albumDAO.save(transientInstance);
	}

	public void delete(AbcAlbum persistentInstance) {
		albumDAO.delete(persistentInstance);
	}

	public AbcAlbum findById(String id) {
		return albumDAO.findById(id);
	}

	public List<AbcAlbum> findByExample(AbcAlbum instance) {
		return albumDAO.findByExample(instance);

	}

	public List<AbcAlbum> findAll() {
		return albumDAO.findAll();

	}

	public void saveOrUpdate(AbcAlbum instance) {
		albumDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return albumDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return albumDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return albumDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return albumDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return albumDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		albumDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return albumDAO.getCallProcedureResult(procString, params);
	}

}

