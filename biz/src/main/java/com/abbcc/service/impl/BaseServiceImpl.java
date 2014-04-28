/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-6           yixiugg                      initial
 **/

package com.abbcc.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.BaseDAO;
import com.abbcc.service.BaseService;

/**
 * *AdminServiceImpl.java
 */
public class BaseServiceImpl implements BaseService {
	protected BaseDAO baseDAO;

	protected final org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(this.getClass());

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void save(Object transientInstance) {
		baseDAO.save(transientInstance);
	}

	public void delete(Object persistentInstance) {
		baseDAO.delete(persistentInstance);
	}

	public void deleteAll(Collection entities) {
		baseDAO.deleteAll(entities);
	}

	@SuppressWarnings("unchecked")
	public Object findById(Class clazz, String id) {
		return baseDAO.findById(clazz, id);
	}

	public void load(Object obj, Serializable id) {
		baseDAO.loadById(obj, id);
	}

	public void refresh(Object entity) {
		baseDAO.refresh(entity);
	}

	public List findByExample(Object instance) {
		return baseDAO.findByExample(instance);
	}

	@SuppressWarnings("unchecked")
	public List findAll(Class clazz) {
		return baseDAO.findAll(clazz);

	}

	public void saveOrUpdate(Object instance) {
		baseDAO.saveOrUpdate(instance);

	}
	public void saveOrUpdateAll(Collection entities){
		baseDAO.saveOrUpdateAll(entities);
	}

	public void update(Object instance) {
		baseDAO.update(instance);
	}

	public List findByHql(String hql) {
		return baseDAO.findByHql(hql);
	}

	public List<?> find(String hql, Object... values) {
		return baseDAO.find(hql, values);
	}

	public int bulkUpdate(String hql, Object... values) {
		return baseDAO.bulkUpdate(hql, values);
	}

	public List<?> query(String sql, RowMapper rowMapper) {
		return baseDAO.query(sql, rowMapper);
	}
	
	public Object query(String sql,ResultSetExtractor rse){
		return baseDAO.query(sql, rse);
	}

	public int[] batchUpdate(String[] sql) {
		return baseDAO.batchUpdate(sql);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return baseDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return baseDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return baseDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex,
			ResultTransformer resultTransformer) {
		return baseDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex, resultTransformer);
	}

	@SuppressWarnings("unchecked")
	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return baseDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return baseDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		baseDAO.callProcedure(procString, params);
	}

	@SuppressWarnings("unchecked")
	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return baseDAO.getCallProcedureResult(procString, params);
	}

	public List findUserByParams(String[] fields, String key, Class clazz)
			throws Exception {
		return baseDAO.findUserByParams(fields, key, clazz);
	}

	@Override
	public List findUserByParamsByPage(String[] fields, String key,
			Class clazz, int page, int pageSize) throws ParseException {
		// TODO Auto-generated method stub
		return baseDAO.findUserByParamsByPage(fields, key, clazz, page,
				pageSize);
	}

	public List findByCriteria(DetachedCriteria detachedCriteria) {
		return baseDAO.findByCriteria(detachedCriteria);
	}

	@Override
	public int getStatBySql(String sql) {
		// TODO Auto-generated method stub
		return baseDAO.getStatBySql(sql);
	}

	@Override
	public PaginationSupport findPaginationUserByParams(String[] fields,
			String key, Class clazz, int page, int pageSize) throws ParseException {
		// TODO Auto-generated method stub
		return baseDAO.findPaginationUserByParams(fields, key, clazz, page, pageSize);
	}
}
