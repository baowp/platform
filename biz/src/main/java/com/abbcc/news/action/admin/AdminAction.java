/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-6-10           baowp                      initial
 */

package com.abbcc.news.action.admin;

import java.util.List;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.news.action.NewsBaseAction;
import com.abbcc.service.AdminService;
import com.abbcc.util.MD5EncryptUtil;

@SuppressWarnings("serial")
public class AdminAction extends NewsBaseAction<AbcAdmin> {

	private AdminService adminService;

	public String login() {
		return INPUT;
	}

	public String enter() {
		entity.setPassword(MD5EncryptUtil.md5Encry(entity.getPassword()));
		// entity.setState(CommonConst.STATENORMAL);
		List<AbcAdmin> l = adminService.findByExample(entity);
		if (l.size() == 0) {
			this.result = CommonConst.LOGINERROR;
			return INPUT;
		}
		if (l.get(0).getState().equals(CommonConst.STATEDEL)
				|| l.get(0).getState().equals(CommonConst.STATEDENY)) {
			this.result = CommonConst.USERSTATEERROR;
			return INPUT;
		}
		this.getSession().setAttribute(CommonConst.SESSIONADMIN, l.get(0));
		savaAdminLog("帐号", l.get(0).getUsername(), CommonConst.LOGLOGIN);
		return index();
	}

	public String index() {
		if (this.getSession().getAttribute(CommonConst.SESSIONADMIN) == null) {
			return INPUT;
		}
		return "index";
	}
	public String logout(){
		this.getSession().invalidate();
		return INPUT;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}
