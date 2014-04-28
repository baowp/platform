/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ModuleAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-14           yixiugg                      initial
**/

package com.abbcc.module.site;

import java.util.List;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.Module;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.LogService;
import com.abbcc.service.SyscodeService;

/**
 **ModuleAction.java
 **/
@SuppressWarnings("serial")
public class AuditManageAction extends BaseAction<AbcSyscode>{
	private SyscodeService syscodeService;
	private List<AbcSyscode> auditInfos;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public List<AbcSyscode> getAuditInfos() {
		return auditInfos;
	}

	public void setAuditInfos(List<AbcSyscode> auditInfos) {
		this.auditInfos = auditInfos;
	}
	/**
	 * 查看网站审核信息
	 * @return
	 */
	public String view(){
		AbcSyscode abcSyscode = new AbcSyscode();
		abcSyscode.setType(CommonConst.SYSCODETYPEAUDIT);
		auditInfos = syscodeService.findByExample(abcSyscode);
		return INPUT;
	}
	/**
	 * 修改网站审核信息
	 * @return
	 */
	public String edit(){
		for(AbcSyscode syscode:auditInfos){
			if(syscode.getSyscodeId()!=null)
			syscodeService.saveOrUpdate(syscode);
			if(syscode.getSign().equals(CommonConst.PRODUCTAUDITSIGN)){
				if(syscode.getState().equals(CommonConst.STATEINIT))
				CommonConst.PRODUCTAUDIT=false;
				if(syscode.getState().equals(CommonConst.STATENORMAL))
					CommonConst.PRODUCTAUDIT=true;
			}
			if(syscode.getSign().equals(CommonConst.NEWSAUDITSIGN)){
				if(syscode.getState().equals(CommonConst.STATEINIT))
				CommonConst.NEWSAUDIT=false;
				if(syscode.getState().equals(CommonConst.STATENORMAL))
					CommonConst.NEWSAUDIT=true;
			}
			if(syscode.getSign().equals(CommonConst.JOBAUDITSIGN)){
				if(syscode.getState().equals(CommonConst.STATEINIT))
				CommonConst.JOBAUDIT=false;
				if(syscode.getState().equals(CommonConst.STATENORMAL))
					CommonConst.JOBAUDIT=true;
			}
			if(syscode.getSign().equals(CommonConst.NEEDSAUDITSIGN)){
				if(syscode.getState().equals(CommonConst.STATEINIT))
				CommonConst.NEEDSAUDIT=false;
				if(syscode.getState().equals(CommonConst.STATENORMAL))
					CommonConst.NEEDSAUDIT=true;
			}
		}
		result="修改网站审核信息成功！";
		logService.savaAdminLog("系统", "审核", CommonConst.LOGUPDATE);
		return INPUT;
	}

	public SyscodeService getSyscodeService() {
		return syscodeService;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}

}

