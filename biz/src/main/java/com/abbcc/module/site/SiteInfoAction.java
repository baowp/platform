/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SiteInfoAction.java is the copyrighted,
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

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.SiteInfo;
import com.abbcc.helper.XmlHelper;
import com.abbcc.service.LogService;
import com.abbcc.util.ObjectUtil;

/**
 **SiteInfoAction.java
 **/
@SuppressWarnings("serial")
public class SiteInfoAction  extends BaseAction<SiteInfo>{
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	/**
	 * 查看
	 */
	@SkipValidation
	public String view(){
		ObjectUtil.copy(CommonConst.SITEINFO, entity);
		return INPUT;
	}
	/**
	 * 修改
	 */
	public String edit(){
		XmlHelper.setSiteInfoXml(entity, CommonConst.SITEINFOFILEPATH);
		logService.savaAdminLog("系统", "网站基本信息", CommonConst.LOGUPDATE);
		result="修改站点信息成功！";
		CommonConst.SITEINFO=entity;
		return INPUT;
	}
	public String editCount(){
		XmlHelper.setSiteInfoXml(entity, CommonConst.SITEINFOFILEPATH);
		return JSON;
	}
}

