/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "FreemarkerResult.java is the copyrighted,
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

package com.abbcc.news.result;

import java.io.IOException;

import com.opensymphony.xwork2.ActionInvocation;

import freemarker.template.TemplateException;

public class FreemarkerResult extends
		org.apache.struts2.views.freemarker.FreemarkerResult {

	private static final long serialVersionUID = 8690608214030432330L;
	private final String ROOT = "/WEB-INF/classes/ftls/news";

	public void doExecute(String locationArg, ActionInvocation invocation) throws IOException, TemplateException {
		if (locationArg.startsWith("/")) {
			locationArg = ROOT + locationArg;
		}
		super.doExecute(locationArg, invocation);
	}

}
