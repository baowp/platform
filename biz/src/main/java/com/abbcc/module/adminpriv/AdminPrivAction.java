/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminPrivAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-12           yixiugg                      initial
 **/

package com.abbcc.module.adminpriv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.Module;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAdminprivilege;
import com.abbcc.models.AbcAdminuserpriv;
import com.abbcc.models.AbcUserpriv;
import com.abbcc.models.AbcUserprivilege;
import com.abbcc.service.AdminService;
import com.abbcc.service.AdminprivilegeService;
import com.abbcc.service.AdminuserprivService;
import com.abbcc.service.LogService;

/**
 * *AdminPrivAction.java
 */
@SuppressWarnings("serial")
public class AdminPrivAction extends BaseAction<AbcAdminuserpriv> {
	private AdminprivilegeService adminprivilegeService;
	private AdminuserprivService adminuserprivService;
	private AdminService adminService;
	private String adminId;
	private List<String> adminprivId;
	private List<AbcAdminprivilege> allPrivs = CommonConst.ADMINPRIVILEGES;
	private List<AbcAdminprivilege> adminprivs;
	private List<Module> modules = CommonConst.MODULES;
	private Map modulePrivs = new HashMap();
	private AbcAdmin admin;
	public String userprivilegeIds;
	public String unCheckId;
	private LogService logService;
	private int affectRows;

	public int getAffectRows() {
		return affectRows;
	}

	public void setAffectRows(int affectRows) {
		this.affectRows = affectRows;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public List<AbcAdminprivilege> getAdminprivs() {
		return adminprivs;
	}

	public void setAdminprivs(List<AbcAdminprivilege> adminprivs) {
		this.adminprivs = adminprivs;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	/**
	 * 查看用户权限
	 */
	public String view() {
		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAdminprivilege.class);

		// detachedCriteria.add(Restrictions.ne("state", "01"));
		detachedCriteria.addOrder(Order.asc("moduleId"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = adminprivilegeService.findPageByCriteria(
				detachedCriteria, 100, startIndex);
		getRequest().setAttribute("adminId",
				getRequest().getParameter("adminId"));
		return "view";
	}

	public String add() {
		return "view";
	}

	/**
	 * 修改管理员权限
	 */
	public String edit() {
		// 通过JSON来修改用户权限状态
		String[] privIds = userprivilegeIds.split(",");
		String[] uncheck = unCheckId.split(",");
		// String hql = "update abc_userpriv set state='"+
		// CommonConst.STATEINIT+"' where userprivId in ";
		String userId = adminId;
		AbcAdminuserpriv auser = new AbcAdminuserpriv();
		auser.setAdminId(userId);
		List<AbcAdminuserpriv> privList = adminuserprivService.findByExample(auser);
		for(AbcAdminuserpriv aa:privList){
			baseService.delete(aa);
		}
		for (int i = 0; i < privIds.length; i++) {
			auser.setState(CommonConst.STATENORMAL);
			auser.setAddAdminId(this.getCurrentAdmin().getAdminId());
			auser.setAdminId(userId);
			auser.setAdminprivilegeId(privIds[i]);
			adminuserprivService.save(auser);
			affectRows = 1;
		}

		return "json";
	}

	public AdminprivilegeService getAdminprivilegeService() {
		return adminprivilegeService;
	}

	public void setAdminprivilegeService(
			AdminprivilegeService adminprivilegeService) {
		this.adminprivilegeService = adminprivilegeService;
	}

	public AdminuserprivService getAdminuserprivService() {
		return adminuserprivService;
	}

	public void setAdminuserprivService(
			AdminuserprivService adminuserprivService) {
		this.adminuserprivService = adminuserprivService;
	}

	public List<AbcAdminprivilege> getAllPrivs() {
		return allPrivs;
	}

	public void setAllPrivs(List<AbcAdminprivilege> allPrivs) {
		this.allPrivs = allPrivs;
	}

	public Map getModulePrivs() {
		return modulePrivs;
	}

	public void setModulePrivs(Map modulePrivs) {
		this.modulePrivs = modulePrivs;
	}

	public AbcAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(AbcAdmin admin) {
		this.admin = admin;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public List<String> getAdminprivId() {
		return adminprivId;
	}

	public void setAdminprivId(List<String> adminprivId) {
		this.adminprivId = adminprivId;
	}
}
