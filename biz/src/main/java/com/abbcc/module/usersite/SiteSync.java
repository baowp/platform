/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SiteSync.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-4           yixiugg                      initial
 **/

package com.abbcc.module.usersite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abbcc.action.BaseAction;
import com.abbcc.exception.DataAccessException;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.module.soa.CustomizeSiteService;
import com.abbcc.module.soa.SyncHelper;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.ObjectUtil;

/**
 * *SiteSync.java
 */
@SuppressWarnings("serial")
public class SiteSync extends BaseAction<SoaUser> {
	private UserService userService;
	private SoaUserService soaUserService;
	private AbcEnterprise abcEnterprise;
	private SyncHelper syncHelper;
	private CustomizeSiteService customizeSiteService;
	
	public CustomizeSiteService getCustomizeSiteService() {
		return customizeSiteService;
	}

	public void setCustomizeSiteService(CustomizeSiteService customizeSiteService) {
		this.customizeSiteService = customizeSiteService;
	}

	/**
	 * 查看网站
	 */
	public String view() {
		AbcUser abcUser = this.getCurrentUser();
		abcEnterprise = abcUser.enterprise();
		syncHelper.setUser(abcUser);
		syncHelper.setEnterprise(abcEnterprise);
		SoaUser soaUser = new SoaUser();
		soaUser.setUsername(abcUser.getUsername());
		List<SoaUser> list = soaUserService.findByExample(soaUser);
		if (list.size() == 0) {
			this.result = "您还没有个性化网站信息！请等待管理员添加您的个性化网站信息。";
			return VIEW;
		}
		soaUser = list.get(0);
		ObjectUtil.copy(soaUser, entity);
		return VIEW;
	}

	/**
	 * 同步
	 * 
	 * @return
	 */
	public String sync() {
		AbcUser abcUser = this.getCurrentUser();
		abcEnterprise = abcUser.enterprise();
		try {
			customizeSiteService.syncUserSite(entity, abcEnterprise);
		} catch (DataAccessException e) {
			e.printStackTrace();
			this.result = "模板文件有问题，"+e.toString();
			return LIST;
			// TODO Auto-generated catch block
		} 
		this.result = "同步成功";
		return VIEW;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SoaUserService getSoaUserService() {
		return soaUserService;
	}

	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}

	public AbcEnterprise getAbcEnterprise() {
		return abcEnterprise;
	}

	public void setAbcEnterprise(AbcEnterprise abcEnterprise) {
		this.abcEnterprise = abcEnterprise;
	}

	public SyncHelper getSyncHelper() {
		return syncHelper;
	}

	public void setSyncHelper(SyncHelper syncHelper) {
		this.syncHelper = syncHelper;
	}

}
