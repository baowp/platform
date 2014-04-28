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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.SyscodeDAO;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.SyscodeService;

public class SyscodeServiceImpl extends BaseServiceImpl implements SyscodeService {

	private SyscodeDAO syscodeDAO;

	public void setSyscodeDAO(SyscodeDAO syscodeDAO) {
		this.syscodeDAO = syscodeDAO;
	}

	public void save(AbcSyscode transientInstance) {
		syscodeDAO.save(transientInstance);
	}

	public void delete(AbcSyscode persistentInstance) {
		syscodeDAO.delete(persistentInstance);
	}

	public AbcSyscode findById(String id) {
		return syscodeDAO.findById(id);
	}

	public List<AbcSyscode> findByExample(AbcSyscode instance) {
		return syscodeDAO.findByExample(instance);

	}

	public List<AbcSyscode> findAll() {
		return syscodeDAO.findAll();

	}

	public void saveOrUpdate(AbcSyscode instance) {
		syscodeDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return syscodeDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return syscodeDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return syscodeDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return syscodeDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return syscodeDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		syscodeDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return syscodeDAO.getCallProcedureResult(procString, params);
	}
	
	public List<AbcSyscode> getSyscodesByType(String type,String state){
		AbcSyscode syscode = new AbcSyscode();
		syscode.setType(type);
		syscode.setState(state);
		return findByExample(syscode);
	}
	public List<AbcSyscode> getSubSyscodesByFather(String syscodeId,String state){
		AbcSyscode syscode = new AbcSyscode();
		syscode.setFatherdict(syscodeId);
		syscode.setState(state);
		return findByExample(syscode);
	}
	public Map<String,String>getBankMaps(){
		List<AbcSyscode> banks = getSyscodesByType(CommonConst.SYSCODEBANK,CommonConst.STATENORMAL);
		Map bankMaps = new HashMap();
		for(AbcSyscode bank:banks){
			bankMaps.put(bank.getSdesc(), bank.getName());
		}
		return bankMaps;
	}
}

