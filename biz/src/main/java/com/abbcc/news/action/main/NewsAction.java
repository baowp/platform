/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-6-22           baowp                      initial
 */

package com.abbcc.news.action.main;

import com.abbcc.news.action.NewsBaseAction;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.NewsNews;

@SuppressWarnings("serial")
public class NewsAction extends NewsBaseAction<NewsNews> {

	public String index() {
		return "index";
	}
	
	public String n(){
		
		return "index";
	}

	protected void prepareModelInner(NewsNews model) {
		super.prepareModelInner(model);
		model.setClassSign(request.getParameter("class"));
		model.setGenusSign(request.getParameter("genus"));
		model.setDisplay(Visibility.display);
	}
	
}
