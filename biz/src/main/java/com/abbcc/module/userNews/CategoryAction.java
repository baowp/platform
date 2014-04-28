/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CategoryAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-10           baowp                      initial
 */

package com.abbcc.module.userNews;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcUser;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.CategoryService;
import com.abbcc.service.LogService;
import com.abbcc.service.NewsService;
import com.abbcc.service.UserService;
import com.abbcc.util.checkKey.CheckKey;

@SuppressWarnings("serial")
public class CategoryAction extends BaseAction<AbcCategory> {
	private int affectRows;
	private CategoryService categoryService;
	private UserService userService;
	private String hasCategoryId;
	private String parentName;

	public String sourceCate;
	public int sourceSort;
	public String targetCate;
	public int targetSort;
	private LogService logService;
	private NewsService newsService;
public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

public void setLogService(LogService logService) {
		this.logService = logService;
	}

/**
 * 根据数据库里的sort获得最大值，并生成新的sort
 * @return
 */
	private Integer newSort() {
		String hql = "";
		if (entity.getCategoryId() != null) {
			hql = "select max(sort) from AbcCategory where enterpriseId='"
					+ this.getUser().getEnterpriseId() + "'and type='"
					+ CommonConst.CATEGORYTYPENEWS + "' and grade='" + "01"
					+ "'";
		} else {
			hql = "select max(sort) from AbcCategory where enterpriseId='"
					+ this.getUser().getEnterpriseId() + "'and type='"
					+ CommonConst.CATEGORYTYPENEWS + "' and grade='" + "00"
					+ "'";
		}
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) categoryService.find(hql);
		Integer sort=null;
		if(list.size()>0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}

	public AbcUser getUser() {
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		return user;
	}

	@SuppressWarnings( { "unchecked" })
	public String show() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCategory.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("type",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.and(Restrictions.ne("state",
				CommonConst.STATEDEL), Restrictions.eq("isroot",
				CommonConst.CATEGORYISROOT)));
		detachedCriteria.addOrder(Order.desc("sort"));
		resultList = categoryService.findAllByCriteria(detachedCriteria);
		pageList = categoryService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		// 构造结果集在同级的排序

		int i = 1;
		Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		for (AbcCategory cate : resultList)
			sortMap.put(cate.getCategoryId() + "," + cate.getSort(), i++);
		getRequest().setAttribute("sortMap", sortMap);

		return "show";
	}

	public String add() {
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		hasCategoryId = entity.getCategoryId();
		Calendar cal = Calendar.getInstance();
		if (hasCategoryId != null) {
			entity.setBelongId(entity.getCategoryId());
			entity.setGrade("01");
			entity.setIsroot(CommonConst.CATEGORYNOTROOT);
		} else {
			entity.setIsroot(CommonConst.CATEGORYISROOT);
			entity.setGrade("00");
		}
		entity.setSort(newSort());
		entity.setAddTime(cal.getTime());
		entity.setAdduserId(this.getUser().getUserId());
		entity.setEnterpriseId(this.getUser().getEnterpriseId());
		entity.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		entity.setState(CommonConst.STATENORMAL);
		entity.setType(CommonConst.CATEGORYTYPENEWS);
		categoryService.save(entity);
		result = CommonConst.ADDSUCCESS;
		logService.savaLog("新闻分类",entity.getName(), CommonConst.LOGSAVE);
		if (hasCategoryId != null) {
			return "returnsonShow";
		}

		return "returnshow";
	}

	public String isdisplay() {
		categoryService.saveOrUpdate(entity);
		affectRows = 1;
		return "json";
	}

	public String update() {
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		categoryService.saveOrUpdate(entity);
		
		affectRows = 1;
		return "json";
	}

	@Recycle(module = "新闻分类")
	public String del() {
		categoryService.delete(entity);
		result = CommonConst.DELSUCCESS;
		logService.savaLog("新闻分类",entity.getName(), CommonConst.LOGDEL);
		if (parentName.equals("top")) {
			return "returnshow";
		}
		return "returnsonShow";

	}

	// 显示子分类
	public String son() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCategory.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("type",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.and(Restrictions.ne("state",
				CommonConst.STATEDEL), Restrictions.eq("grade",
				CommonConst.CATEGORYISROOT)));
		detachedCriteria.add(Restrictions
				.eq("belongId", entity.getCategoryId()));
		detachedCriteria.addOrder(Order.desc("sort"));
		resultList = categoryService.findAllByCriteria(detachedCriteria);
		pageList = categoryService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		int i = 1;
		Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		for (AbcCategory cate : resultList)
			sortMap.put(cate.getCategoryId() + "," + cate.getSort(), i++);
		getRequest().setAttribute("sortMap", sortMap);
		getRequest().setAttribute("cId", entity.getCategoryId());
		return "sonShow";

	}

	public String change() {
		changeSort(sourceCate, sourceSort, targetCate, targetSort);
		if (entity.getCategoryId() != null) {
			return "returnsonShow";
		}

		return "returnshow";
	}
	
	public String isRoot(){
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcCategory.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("type",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.and(Restrictions.ne("state",
				CommonConst.STATEDEL), Restrictions.eq("grade",
				CommonConst.CATEGORYISROOT)));
		detachedCriteria.add(Restrictions
				.eq("belongId", entity.getCategoryId()));
		List<AbcCategory> list = categoryService.findAllByCriteria(detachedCriteria);
		if(list.size()!=0)
			affectRows=1;
		else
			affectRows=2;
		return JSON;
	}
	public String sonNews(){
		AbcNews an = new AbcNews();
		an.setCategory(entity.getCategoryId());
		DetachedCriteria dc = DetachedCriteria
		.forClass(AbcNews.class);
		dc.add(Example.create(an));
		List<AbcNews> list = newsService.findAllByCriteria(dc);
		if(list.size()!=0)
			result="true";
		else
			result="false";
		return "json2";
		
	}

	private int[] changeSort(String sourceCate, int sourceSort,
			String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
				|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update abc_category set sort=" + targetSort
				+ " where category_id='" + sourceCate + "'";
		sql[1] = "update abc_category set sort=" + sourceSort
				+ " where category_id='" + targetCate + "'";
		return categoryService.batchUpdate(sql);
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getAffectRows() {
		return affectRows;
	}

	public void setAffectRows(int affectRows) {
		this.affectRows = affectRows;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
