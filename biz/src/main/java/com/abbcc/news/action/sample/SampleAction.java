/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SampleAction.java is the copyrighted,
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

package com.abbcc.news.action.sample;

import com.abbcc.models.AbcBrand;
import com.abbcc.news.action.NewsBaseAction;

@SuppressWarnings("serial")
public class SampleAction extends NewsBaseAction<AbcBrand> {

	public String sample(){
		return "sample";
	}
}
