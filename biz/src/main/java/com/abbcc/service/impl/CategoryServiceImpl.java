/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CategoryServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-9           Wangjin                      initial
 */

package com.abbcc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Property;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.CategoryDAO;
import com.abbcc.models.AbcCategory;
import com.abbcc.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements
		CategoryService {

	private CategoryDAO categoryDAO;

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
		setBaseDAO(categoryDAO);
	}

	public void save(AbcCategory transientInstance) {
		categoryDAO.save(transientInstance);
	}

	public void delete(AbcCategory persistentInstance) {
		categoryDAO.delete(persistentInstance);
	}

	public AbcCategory findById(String id) {
		return categoryDAO.findById(id);
	}

	public List<AbcCategory> findByExample(AbcCategory instance) {
		return categoryDAO.findByExample(instance);

	}

	public List<AbcCategory> findAll() {
		return categoryDAO.findAll();

	}

	public void saveOrUpdate(AbcCategory instance) {
		categoryDAO.saveOrUpdate(instance);

	}

	public void update(AbcCategory instance) {
		categoryDAO.update(instance);

	}

	public void load(AbcCategory entity, Serializable id) {
		categoryDAO.load(entity, id);
	}

	public int updateColumn(Serializable id, String column, String value) {
		return categoryDAO.updateColumn(id, column, value);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return categoryDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return categoryDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return categoryDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List<?> findAllByCriteria(DetachedCriteria detachedCriteria) {
		return categoryDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return categoryDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		categoryDAO.callProcedure(procString, params);
	}

	public List<?> getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return categoryDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public List<AbcCategory> findByHql(String hql) {
		// TODO Auto-generated method stub
		return categoryDAO.findByHql(hql);
	}
	
	public void changeSort(AbcCategory category1, AbcCategory category2) {
		int sort = category1.getSort();
		category1.setSort(category2.getSort());
		category2.setSort(sort);
		categoryDAO.update(category1);
		categoryDAO.update(category2);
	}
	
	public AbcCategory cascadeVisibleCategory(String categoryId) {
		AbcCategory category = findById(categoryId);
		return cascadeVisibleCategory(category);
	}

	public AbcCategory cascadeVisibleCategory(AbcCategory category) {
		AbcCategory example = new AbcCategory();
		example.setBelongId(category.getCategoryId());
		example.setState(CommonConst.STATENORMAL);
		example.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		DetachedCriteria dc = DetachedCriteria.forClass(AbcCategory.class);
		dc.add(Example.create(example))
				.addOrder(Property.forName("sort").asc());

		@SuppressWarnings("unchecked")
		List<AbcCategory> categoryList = (List<AbcCategory>) findAllByCriteria(dc);
		category.setSonCate(categoryList);
		for (AbcCategory cate : categoryList) {
			cascadeVisibleCategory(cate);
		}
		return category;
	}
	
	public List<String> cascadeVisibleIds(String categoryId) {
		AbcCategory category = cascadeVisibleCategory(categoryId);
		List<String> list = new ArrayList<String>();
		cascadeVisibleIds(list, category);
		return list;
	}

	private void cascadeVisibleIds(List<String> idList, AbcCategory category) {
		idList.add(category.getCategoryId());
		for (AbcCategory cate : category.getSonCate()) {
			cascadeVisibleIds(idList, cate);
		}
	}
}
