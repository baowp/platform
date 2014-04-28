/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CustomizeSiteSyncAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-4-24           yixiugg                      initial
**/

package com.abbcc.module.soa;

import com.abbcc.action.BaseAction;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.SoaUser;
import com.abbcc.service.SoaTemplateCriteriaService;
import com.abbcc.service.SoaTemplateService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;

/**
 **CustomizeSiteSyncAction.java
 **/
@SuppressWarnings("serial")
public class CustomizeSiteSyncAction  extends BaseAction<SoaUser> {
	private AbcEnterprise abcEnterprise;
	private UserService userService;
	private SoaTemplateService soaTemplateService;
	private SoaUserService soaUserService;
	private SoaTemplateCriteriaService soaTemplateCriteriaService;
	public AbcEnterprise getAbcEnterprise() {
		return abcEnterprise;
	}
	public void setAbcEnterprise(AbcEnterprise abcEnterprise) {
		this.abcEnterprise = abcEnterprise;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public SoaTemplateService getSoaTemplateService() {
		return soaTemplateService;
	}
	public void setSoaTemplateService(SoaTemplateService soaTemplateService) {
		this.soaTemplateService = soaTemplateService;
	}
	public SoaUserService getSoaUserService() {
		return soaUserService;
	}
	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}
	public SoaTemplateCriteriaService getSoaTemplateCriteriaService() {
		return soaTemplateCriteriaService;
	}
	public void setSoaTemplateCriteriaService(
			SoaTemplateCriteriaService soaTemplateCriteriaService) {
		this.soaTemplateCriteriaService = soaTemplateCriteriaService;
	}
	/**
	 * 
	 * @return
	 */
	public String sync(){
		
		this.result="同步成功！";
		return LIST;
	}

}

