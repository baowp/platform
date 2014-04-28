/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminLoginAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-5           yixiugg                      initial
**/

package com.abbcc.module.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.abbcc.action.BaseAction;
import com.abbcc.common.ActionConst;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAdminprivilege;
import com.abbcc.models.AbcAdminuserpriv;
import com.abbcc.models.AbcUserpriv;
import com.abbcc.service.AdminService;
import com.abbcc.service.AdminprivilegeService;
import com.abbcc.service.AdminuserprivService;
import com.abbcc.service.LogService;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.StringUtil;

/**
 **AdminLoginAction.java
 **/
@SuppressWarnings("serial")
public class AdminLoginAction extends BaseAction<AbcAdmin>{
	private AdminService adminService;
	private String vericode;
	private LogService logService;
	private AdminprivilegeService adminprivilegeService;
	private AdminuserprivService adminuserprivService;
	public void setAdminuserprivService(AdminuserprivService adminuserprivService) {
		this.adminuserprivService = adminuserprivService;
	}

	public void setAdminprivilegeService(AdminprivilegeService adminprivilegeService) {
		this.adminprivilegeService = adminprivilegeService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getVericode() {
		return vericode;
	}

	public void setVericode(String vericode) {
		this.vericode = vericode;
	}

	/**
	 * 管理员用户登陆
	 * @return
	 */
	public String login(){
		HttpSession session = this.getSession();
		if(!this.vericode.equalsIgnoreCase((String)session.getAttribute(CommonConst.VERICODE))){
			this.addFieldError("vericode", "图片验证码错误！");
			return INPUT;
		}
		entity.setPassword(MD5EncryptUtil.md5Encry(entity.getPassword()));
		if(!(entity.getUsername().equals("admin")))
			entity.setDomain(domain);
		//entity.setDomain(domain);
//		entity.setState(CommonConst.STATENORMAL);
		List<AbcAdmin> l = adminService.findByExample(entity);
		if(l.size()==0){
			this.result=CommonConst.LOGINERROR;
			return INPUT;
		}
		if(l.get(0).getState().equals(CommonConst.STATEDEL)||l.get(0).getState().equals(CommonConst.STATEDENY)){
			this.result=CommonConst.USERSTATEERROR;
			return INPUT;
		}
		if(!(l.get(0).getType().equals(CommonConst.ADMINTYPESUPER))){
			if(!(l.get(0).getDomain().equals(domain))){
				this.result=CommonConst.USERSTATEERROR;
				return INPUT;
			}
			AbcAdminuserpriv aap = new AbcAdminuserpriv();
			aap.setAdminId(l.get(0).getAdminId());
			aap.setState(CommonConst.STATENORMAL);
			List<AbcAdminuserpriv> privList = (List<AbcAdminuserpriv>)adminuserprivService.findByExample(aap);
			session.setAttribute(CommonConst.ADMINPRIVS, privList);
		}
		session.setAttribute(CommonConst.SESSIONADMIN, l.get(0));
		savaAdminLog("帐号", l.get(0).getUsername(), CommonConst.LOGLOGIN);
		return ActionConst.ADMININDEX;
	}
	/**
	 * 管理员注销
	 * @return
	 */
	@SkipValidation
	public String logout(){
		this.getSession().invalidate();
		return ActionConst.ADMINLOGOUT;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}

