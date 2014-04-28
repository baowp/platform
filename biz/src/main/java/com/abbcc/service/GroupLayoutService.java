/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminService.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-9           wangjin                      initial
 **/

package com.abbcc.service;

import java.util.List;

import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.models.GroupLayout;
import com.abbcc.models.GroupLaytheme;
import com.abbcc.springrest.controller.user.site.LayoutController;

public interface GroupLayoutService extends Service<GroupLayout> {

	/**
	 * 覆盖布局信息
	 * 
	 * @param layout
	 * @param themeList
	 */
	public void layover(GroupLayout layout, List<GroupLaytheme> themeList);

	/**
	 * 覆盖分类与产品信息
	 * 
	 * @param categoryList
	 */
	public void layoverCategoryAndProduct(List<AbcCategory> categoryList,
			List<AbcProduct> productList);

	public void revertLayout(AbcUser user);

	public void initLay(AbcUser user); // 初始化布局

	public void initNav(AbcUser user); // 初始化导航菜单
	
	public void initUser(AbcUser user);	// 初始化用户(布局,导航菜单)

	void cascadeSave(LayoutController handle);
}
