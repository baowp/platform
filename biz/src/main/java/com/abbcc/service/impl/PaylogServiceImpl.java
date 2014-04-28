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
import com.abbcc.dao.PaylogDAO;
import com.abbcc.models.AbcPaylog;
import com.abbcc.service.PaylogService;

public class PaylogServiceImpl extends BaseServiceImpl implements PaylogService {

	private PaylogDAO paylogDAO;

	public void setPaylogDAO(PaylogDAO paylogDAO) {
		this.paylogDAO = paylogDAO;
		setBaseDAO(paylogDAO);
	}

	public void save(AbcPaylog transientInstance) {
		paylogDAO.save(transientInstance);
	}

	public void delete(AbcPaylog persistentInstance) {

	}

	public AbcPaylog findById(String id) {
		return paylogDAO.findById(id);
	}

	public List<AbcPaylog> findByExample(AbcPaylog instance) {
		return paylogDAO.findByExample(instance);

	}

	public List<AbcPaylog> findAll() {
		return paylogDAO.findAll();

	}

	public void saveOrUpdate(AbcPaylog instance) {
		paylogDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return paylogDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return paylogDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return paylogDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return paylogDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return paylogDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		paylogDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return paylogDAO.getCallProcedureResult(procString, params);
	}
	/**
	 * 得到用户的截止日期最迟的付费记录
	 * @param payuserId
	 * @return
	 */
	public AbcPaylog getUserLastestPayLog(String payuserId){
		AbcPaylog payLog = new AbcPaylog();
		List<AbcPaylog> payLogs = getUserPaylogs(payuserId);
		if(payLogs.size()==0)
			return null;
		payLog = payLogs.get(0);
		for(AbcPaylog temp:payLogs){
			if(temp.getEndTime().getTime()>payLog.getEndTime().getTime())
				payLog = temp;
		}
		return payLog;
	}
	/**
	 * 得到用户付费记录
	 * @param payuserId
	 * @return
	 */
	public List<AbcPaylog> getUserPaylogs(String userId){
		String hql="from AbcPaylog where userId='"+userId+"' order by payTime desc";
		List<AbcPaylog> payLogs = paylogDAO.findByHql(hql);
		return payLogs;
	}

}

