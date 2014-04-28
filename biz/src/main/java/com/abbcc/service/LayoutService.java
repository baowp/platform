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

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcLayout;
import com.abbcc.models.AbcLaytheme;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;

/**
 * *AdminService.java
 */
public interface LayoutService extends BaseService {
	public void save(AbcLayout transientInstance);

	public void delete(AbcLayout persistentInstance);

	public AbcLayout findById(String id);

	public List<AbcLayout> findByExample(AbcLayout instance);

	public List<AbcLayout> findAll();

	public void saveOrUpdate(AbcLayout instance);

	public AbcLayout merge(AbcLayout entity);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex);

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex);

	public List findAllByCriteria(DetachedCriteria detachedCriteria);

	public int getCountByCriteria(DetachedCriteria detachedCriteria);

	public void callProcedure(String procString, List<Object> params)
			throws Exception;

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception;

	public void cascadeSave(AbcLayout entity);

	public void revertLayout(AbcUser user);

	public void initialize(AbcUser user);

	/**
	 * 覆盖布局信息
	 * 
	 * @param layout
	 * @param themeList
	 */
	public void layover(AbcLayout layout, List<AbcLaytheme> themeList);

	/**
	 * 覆盖分类与产品信息
	 * 
	 * @param categoryList
	 */
	public void layoverCategoryAndProduct(List<AbcCategory> categoryList,
			List<AbcProduct> productList);
}
