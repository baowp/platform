/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-10           yixiugg                      initial
**/

package com.abbcc.module.news;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCategory;
import com.abbcc.service.CategoryService;
import com.abbcc.util.StringUtil;

/**
 **NewsCategoryManageAction.java
 **/
@SuppressWarnings("serial")
public class NewsCategoryManageAction extends BaseAction<AbcCategory>{
	private String LISTCATEGORY="listcategory";
	private CategoryService categoryService;
	private String entId;
	private String[] categoryIds;
	private String entName;
	private String searchKey;
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	/**
	 * 查询
	 * 
	 * @return
	 */
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcCategory.class);
			detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		if(StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
		if(StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("name", searchKey,MatchMode.ANYWHERE));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = categoryService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return LIST;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		for(String categoryId:categoryIds){
			String[]categoryIdAndState = StringUtil.splitBySemicolon(categoryId);
			AbcCategory category = categoryService.findById(categoryIdAndState[0]);
			category.setState(categoryIdAndState[1]);
			categoryService.delete(category);
			this.savaAdminLog("新闻分类",category.getName(),CommonConst.LOGDEL);
		}
		result="删除成功！";
		return LISTCATEGORY;
	}
	/**
	 * 查看详细
	 */
	public String view(){
		return DETAIL;
	}
	public String[] getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String[] categoryIds) {
		this.categoryIds = categoryIds;
	}
}

