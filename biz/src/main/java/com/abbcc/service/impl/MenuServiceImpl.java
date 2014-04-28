/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CategoryServiceImpl.java is the copyrighted,
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

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.MenuDAO;
import com.abbcc.models.AbcMenu;
import com.abbcc.service.MenuService;

public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

	private MenuDAO menuDAO;

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public void save(AbcMenu transientInstance) {
		menuDAO.save(transientInstance);
	}

	public void delete(AbcMenu persistentInstance) {
		menuDAO.delete(persistentInstance);
	}

	public AbcMenu findById(String id) {
		return menuDAO.findById(id);
	}

	public List<AbcMenu> findByExample(AbcMenu instance) {
		return menuDAO.findByExample(instance);

	}

	public List<AbcMenu> findAll() {
		return menuDAO.findAll();

	}

	public void saveOrUpdate(AbcMenu instance) {
		menuDAO.saveOrUpdate(instance);

	}

	public void update(AbcMenu instance) {
		menuDAO.update(instance);

	}

	public void load(AbcMenu entity, Serializable id) {
		menuDAO.load(entity, id);
	}

	public int updateColumn(Serializable id, String column, String value) {
		return menuDAO.updateColumn(id, column, value);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return menuDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return menuDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return menuDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List<?> findAllByCriteria(DetachedCriteria detachedCriteria) {
		return menuDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return menuDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		menuDAO.callProcedure(procString, params);
	}

	public List<?> getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return menuDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public List<AbcMenu> findByHql(String hql) {
		// TODO Auto-generated method stub
		return menuDAO.findByHql(hql);
	}

}
