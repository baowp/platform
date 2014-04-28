/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "FetchSyscodeAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-22           baowp                      initial
 */

package com.abbcc.module.product;

import java.util.List;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.SyscodeService;

@SuppressWarnings("serial")
public class FetchSyscodeAction extends BaseAction<AbcSyscode> {

	private SyscodeService syscodeService;

	public List<AbcSyscode> syscodeList;

	public String syspath;

	public String subSyscodes() {
		entity.setState(CommonConst.STATENORMAL);
		syscodeList = syscodeService.findByExample(entity);
		return "json";
	}

	public void rootIndustry() {
		entity.setType("20");
		entity.setState(CommonConst.STATENORMAL);
		syscodeList = syscodeService.findByExample(entity);
	}

	public void validateRootIndustry() {
		clearFieldErrors();
	}

	public void syscodePath() {
		syspath = syscodePath("", entity.getSyscodeId());
	}

	private String syscodePath(String syspath, String syscodeId) {
		AbcSyscode as = syscodeService.findById(syscodeId);
		syspath = as.getName() + " > " + syspath;
		String fatherdict = as.getFatherdict();
		if (fatherdict != null && fatherdict.length() > 0) {
			return syscodePath(syspath, fatherdict);
		}
		return syspath;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}

	public List<AbcSyscode> getSyscodeList() {
		return syscodeList;
	}
}
