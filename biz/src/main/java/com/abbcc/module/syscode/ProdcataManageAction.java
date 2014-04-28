/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DistrictManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-16           yixiugg                      initial
 **/

package com.abbcc.module.syscode;

import java.util.List;

import net.sf.json.JSONArray;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.LogService;
import com.abbcc.service.SyscodeService;

/**
 * *DistrictManageAction.java
 */
@SuppressWarnings("serial")
public class ProdcataManageAction extends BaseAction<AbcSyscode> {
	private List<AbcSyscode> industrys;
	private SyscodeService syscodeService;
	private List<AbcSyscode> subsyscodes;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setSubsyscodes(List<AbcSyscode> subsyscodes) {
		this.subsyscodes = subsyscodes;
	}

	public String view() {
		
		industrys = syscodeService.getSyscodesByType(
				CommonConst.SYSCODETYPEINDUSTY, CommonConst.STATENORMAL);
		return INPUT;
	}

	public String add() {
		String[] names = entity.getName().split(",");
		for(int i=0;i<names.length;i++){
			AbcSyscode as = new AbcSyscode();
			as.setName(names[i]);
			as.setFatherdict(entity.getFatherdict());
			as.setType(entity.getType());
			as.setState(entity.getState());
			syscodeService.save(as);
		}
		//syscodeService.save(entity);
		logService.savaAdminLog("行业",entity.getName(),CommonConst.LOGSAVE);
		result = "添加行业成功！";
		return view();
	}
	
	public String addSub() {
		String[] names = entity.getName().split(",");
		for(int i=0;i<names.length;i++){
			AbcSyscode as = new AbcSyscode();
			as.setName(names[i]);
			as.setFatherdict(entity.getFatherdict());
			as.setType(entity.getType());
			as.setState(entity.getState());
			syscodeService.save(as);
		}
		logService.savaAdminLog("行业",entity.getName(),CommonConst.LOGSAVE);
		result = "添加行业成功！";
		return SUCCESS;
	}

	public String delete() {
		syscodeService.delete(entity);
		logService.savaAdminLog("行业",entity.getName(),CommonConst.LOGSAVE);
		result = "删除行业成功！";
		return LIST;
	}

	public List<AbcSyscode> getSubsyscodes() {
		return this.subsyscodes;
	}

	public SyscodeService getSyscodeService() {
		return syscodeService;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}

	public String sub() {
		subsyscodes = entity.subSyscodes();
		JSONArray json = new JSONArray();
		json = JSONArray.fromObject(subsyscodes);
		result = json.toString();
		return SUCCESS;
	}

	public List<AbcSyscode> getIndustrys() {
		return industrys;
	}

	public void setIndustrys(List<AbcSyscode> industrys) {
		this.industrys = industrys;
	}
}
