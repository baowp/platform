/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminAccountAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-13           yixiugg                      initial
**/

package com.abbcc.module.admin;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.service.AdminService;
import com.abbcc.service.LogService;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.StringUtil;

/**
 **AdminAccountAction.java
 **/
@SuppressWarnings("serial")
public class AdminAccountAction  extends BaseAction<AbcAdmin>{
	private AdminService adminService;
	private String password1;
	private String password2;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	/**
	 * 修改账号信息
	 * @return
	 */
	public String updateMyAccountInfo(){
		adminService.saveOrUpdate(entity);
		logService.savaAdminLog("帐号", entity.getUsername(), CommonConst.LOGUPDATE);
		getSession().setAttribute(CommonConst.SESSIONADMIN, entity);
		result="修改信息成功！";
		return INPUT;
	}
	@SkipValidation
	/**
	 * 修改密码
	 */
	public String updateMyPassword(){
		AbcAdmin admin = (AbcAdmin)this.getSession().getAttribute(CommonConst.SESSIONADMIN);
		if(StringUtil.isBlank(entity.getPassword())){
			result= "请输入原密码！";
			return INPUT;
		}
		if(!MD5EncryptUtil.md5Encry(entity.getPassword()).equals(admin.getPassword())){
			result= "原密码错误！";
			return INPUT;
		}
		if(!password1.equals(password2)){
			result= "两次密码不一致！";
			return INPUT;
		}
		if(StringUtil.isBlank(password1)){
			result= "请输入新密码！";
			return INPUT;
		}
		entity.setPassword(MD5EncryptUtil.md5Encry(password1));
		adminService.saveOrUpdate(entity);
		logService.savaAdminLog("密码", "", CommonConst.LOGUPDATE);
		getSession().setAttribute(CommonConst.SESSIONADMIN, entity);
		result="修改密码成功！";
		return INPUT;
	}

}

