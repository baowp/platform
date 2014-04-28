/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "BindAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-8-3           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.util.Date;
import java.util.List;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcBind;
import com.abbcc.service.BindService;
import com.abbcc.util.ObjectUtil;

@SuppressWarnings("serial")
public class BindAction<T> extends SiteBaseAction<AbcBind> {

	protected BindService bindService;
	protected String username;

	protected void before() {
		username = getCurrentUser().getUsername();
	}

	public String apply() {
		before();
		AbcBind example = new AbcBind();
		example.setUsername(username);
		@SuppressWarnings("unchecked")
		List<AbcBind> list = bindService.findByExample(example);
		if (!list.isEmpty()) {
			AbcBind bind = list.iterator().next();
			ObjectUtil.extend(entity, bind);
		}
		return "apply";
	}

	public String save() {
		before();
		entity.setApplyTime(new Date());
		entity.setUsername(username);
		entity.setFileName(username + ".conf");
		entity.setState(CommonConst.STATEINIT);
		bindService.saveOrUpdate(entity);
		return "applied";
	}

	public void setBindService(BindService bindService) {
		this.bindService = bindService;
	}
}
