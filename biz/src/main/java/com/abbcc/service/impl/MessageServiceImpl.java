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
import org.hibernate.transform.ResultTransformer;
import org.springframework.jdbc.core.RowMapper;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.MessageDAO;
import com.abbcc.models.AbcMessage;
import com.abbcc.service.BaseService;
import com.abbcc.service.MessageService;

public class MessageServiceImpl extends BaseServiceImpl implements MessageService {

	private MessageDAO messageDAO;

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	public void save(AbcMessage transientInstance) {
		messageDAO.save(transientInstance);
	}

	public void delete(AbcMessage persistentInstance) {
		messageDAO.delete(persistentInstance);
	}

	public AbcMessage findById(String id) {
		return messageDAO.findById(id);
	}

	public List<AbcMessage> findByExample(AbcMessage instance) {
		return messageDAO.findByExample(instance);

	}

	public List<AbcMessage> findAll() {
		return messageDAO.findAll();

	}


	
	public void saveOrUpdate(AbcMessage instance) {
		messageDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return messageDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return messageDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return messageDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return messageDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return messageDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		messageDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return messageDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public int[] batchUpdate(String[] sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bulkUpdate(String hql, Object... values) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Object persistentInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<?> find(String hql, Object... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll(Class clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByExample(Object instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findById(Class clazz, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex,
			ResultTransformer resultTransformer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> query(String sql, RowMapper rowMapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Object transientInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Object instance) {
		// TODO Auto-generated method stub
		
	}



}

