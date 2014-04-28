/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsBaseAction.java is the copyrighted,
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

package com.abbcc.news.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.news.models.api.Domain;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
public class NewsBaseAction<T> extends BaseAction<T> {

	protected HttpSession session;
	protected HttpServletRequest request;
	public final String ROOT = "/WEB-INF/classes/ftls/news";

	public void setServletContext(ServletContext context) {
		super.setServletContext(context);
		request = getRequest();
		session = getSession();
	}

	protected void prepareModelInner(T model) {
		if (model instanceof Domain) {
			Domain entity = (Domain) model;
			if (entity.getDomain() == null) {
				entity.setDomain(domain);
			}
		}
	}
	
	public String remove() {
		baseService.delete(entity);
		return REMOVE;
	}

}
