/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "LaythemeAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-15           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.IOException;
import java.util.Date;

import com.abbcc.models.AbcLaytheme;
import com.abbcc.service.LaythemeService;
import com.abbcc.util.constant.layout.LaythemeState;

@SuppressWarnings("serial")
public class LaythemeAction extends SiteBaseAction<AbcLaytheme> {

	private LaythemeService laythemeService;

	public String save() {
		return save(LaythemeState.B);
	}

	public String saveAs() {
		return save(LaythemeState.A);
	}

	private String save(LaythemeState ls) {
		entity.setState(ls);
		entity.setAddTime(new Date());
		laythemeService.saveOrUpdate(entity);
		return JSON;
	}

	public String refresh() {
		laythemeService.refresh(entity);
		return JSON;
	}

	public void delete() throws IOException {
		laythemeService.delete(entity);
		output(SUCCESS);
	}

	public void setLaythemeService(LaythemeService laythemeService) {
		this.laythemeService = laythemeService;
	}
}
