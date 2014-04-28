/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-8           yixiugg                      initial
 **/

package com.abbcc.module.admin;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.service.AdminService;
import com.abbcc.service.LogService;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.StringUtil;

/**
 * *AdminManageAction.java
 */
@SuppressWarnings("serial")
public class AdminManageAction extends BaseAction<AbcAdmin> {
	private String name;

	private AdminService adminService;
	
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@Override
	public String add() {
		if (adminService.getAdminByUsername(entity.getUsername()).size() > 0) {
			this.addFieldError("username", CommonConst.USERNAMEEXISTEDEROOR);
			return "add_input";
		}
		if (adminService.getAdminByEmail(entity.getEmail()).size() > 0) {
			this.addFieldError("email", CommonConst.EMAILEXISTEDDROOR);
			return "add_input";
		}
		if (entity.getPassword() == null || entity.getPassword().equals("")) {
			entity.setPassword(entity.getUsername());
		}
		entity.setPassword(MD5EncryptUtil.md5Encry(entity.getPassword()));
		adminService.save(entity);
		logService.savaAdminLog("帐号", entity.getUsername(), CommonConst.LOGSAVE);
		this.result = "管理员添加成功！";
		return "listAdmin";
	}

	@Override
	public String edit() {
		List<AbcAdmin> l = adminService
				.getAdminByUsername(entity.getUsername());
		if (l.size() > 0) {
			if (!l.get(0).getAdminId().equals(entity.getAdminId())) {
				this
						.addFieldError("username",
								CommonConst.USERNAMEEXISTEDEROOR);
				return "input";
			}
		}
		l = adminService.getAdminByEmail(entity.getEmail());
		if (l.size() > 0) {
			if (!l.get(0).getAdminId().equals(entity.getAdminId())) {
				this.addFieldError("email", CommonConst.EMAILEXISTEDDROOR);
				return "input";
			}
		}
		if (entity.getPassword().equals("") || entity.getPassword() == null) {
			entity.setPassword(adminService.findById(entity.getAdminId())
					.getPassword());
		}
		adminService.saveOrUpdate(entity);
		logService.savaAdminLog("帐号", entity.getUsername(), CommonConst.LOGUPDATE);
		this.result = "修改管理员信息成功！";
		return "listAdmin";
	}

	@SkipValidation
	public String delete() {
		entity = adminService.findById(id);
		entity.setState(CommonConst.STATEDEL);
		adminService.saveOrUpdate(entity);
		logService.savaAdminLog("帐号", entity.getUsername(), CommonConst.LOGDEL);
		this.result = "admindelsuccess";
		return "listAdmin";
	}

	@SkipValidation
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAdmin.class);
		if (StringUtil.isNotBlank(this.name))
			detachedCriteria.add(Restrictions.or(Restrictions.like("username",
					this.name, MatchMode.ANYWHERE), Restrictions.like("name",
					this.name, MatchMode.ANYWHERE)));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = adminService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if(pageList.getTotalCount()==0)
			result=CommonConst.NORESULT;
		return "list";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
