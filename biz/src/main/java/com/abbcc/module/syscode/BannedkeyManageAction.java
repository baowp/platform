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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.LogService;
import com.abbcc.service.SyscodeService;
import com.abbcc.util.ObjectUtil;

/**
 * *DistrictManageAction.java
 */
@SuppressWarnings("serial")
public class BannedkeyManageAction extends BaseAction<AbcSyscode> {
	private SyscodeService syscodeService;
	private LogService logService;
	private int affectRows=0;
	public String keyValue;
	public String keyValues;

	public int getAffectRows() {
		return affectRows;
	}

	public void setAffectRows(int affectRows) {
		this.affectRows = affectRows;
	}
	public void setLogService(LogService logService) {
		this.logService = logService;
	}


	public String view() {
		entity.setType(CommonConst.SYSCODETYPEBANNEDKEY);
		entity.setState(CommonConst.STATENORMAL);
		List<AbcSyscode> list = syscodeService.findByExample(entity);
		if(list.size()==0)
			syscodeService.save(entity);
		list = syscodeService.findByExample(entity);
		ObjectUtil.copy(list.get(0), entity);
		return INPUT;
	}


	public String edit() {
		syscodeService.saveOrUpdate(entity);
		logService.savaAdminLog("关键字", entity.getName(), CommonConst.LOGUPDATE);
		result = "修改关键词成功！";
		return INPUT;
	}
	
	public String checkValue(){
		entity.setType(CommonConst.SYSCODETYPEBANNEDKEY);
		entity.setState(CommonConst.STATENORMAL);
		List<AbcSyscode> list = syscodeService.findByExample(entity);
		if(list.size()!=0){
			String[] key=list.get(0).getContent().split(" ");
			for(int i=0;i<key.length;i++){
				if(keyValue.trim().indexOf(key[i])>-1){
					affectRows = -1;
					break;
				}
			}
		}
		return JSON;
	}
	//检查关键字数组
	public String checkKeys(){
		entity.setType(CommonConst.SYSCODETYPEBANNEDKEY);
		entity.setState(CommonConst.STATENORMAL);
		List<AbcSyscode> list = syscodeService.findByExample(entity);
		String[] pageKey=keyValues.split(",");
		if(list.size()!=0){
			String[] key=list.get(0).getContent().split(" ");
			for(int i=0;i<key.length;i++){
				for(int j=0;j<pageKey.length;j++){
					if(pageKey[j].trim().indexOf(key[i])>-1){
						result = pageKey[j];
						return "json1";
					}
				}
			}
		}
		return "json1";
	}

	public SyscodeService getSyscodeService() {
		return syscodeService;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}
}
