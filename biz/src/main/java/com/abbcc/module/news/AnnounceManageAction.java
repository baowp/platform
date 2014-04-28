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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcSupply;
import com.abbcc.service.CategoryService;
import com.abbcc.service.NewsService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;

/**
 * *NewsManageAction.java
 */
@SuppressWarnings("serial")
public class AnnounceManageAction extends BaseAction<AbcNews> {
	private CategoryService categoryService;
	private String LISTANNOUNCE = "listannounce";
	private String categoryValue = "";
	private NewsService newsService;
	private String[] newsIds;
	private String searchKey;
	private List<AbcCategory> categorys;
	private String newsState = CommonConst.STATEINIT;
	Map data = new HashMap();
	private FreemarkerHelper freemarkerHelper = new FreemarkerHelper();
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public String[] getNewsIds() {
		return newsIds;
	}

	public void setNewsIds(String[] newsIds) {
		this.newsIds = newsIds;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getNewsState() {
		return newsState;
	}

	public void setNewsState(String newsState) {
		this.newsState = newsState;
	}

	/**
	 * 编辑审核
	 * 
	 * @return
	 */
	public String updateState() {
		for (String newsId : newsIds) {
			String[] newsIdAndState = StringUtil.splitBySemicolon(newsId);
			AbcNews news = newsService.findById(newsIdAndState[0]);
			news.setState(newsIdAndState[1]);
			// 发布新闻
			if (newsIdAndState[1].equals(CommonConst.STATENORMAL)) {
				news.setAuditId(this.adminId());
				news.setAuditTime(new Date());
			}
			newsService.saveOrUpdate(news);
			this.savaAdminLog("编辑审核",news.getTitle(),CommonConst.LOGUPDATE);
		}
		result = "操作成功！";
		return LISTANNOUNCE;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public String list() {
		categorys = (List<AbcCategory>) this.getSession().getAttribute(
				CommonConst.SESSIONADMINNEWSCATEGORY);
		if (categorys == null || categorys.size() == 0) {
			AbcCategory category = new AbcCategory();
			category.setType(CommonConst.CATEGORYTYPEADMINNEWS);
			categorys = categoryService.findByExample(category);
			this.getSession().setAttribute(
					CommonConst.SESSIONADMINNEWSCATEGORY, categorys);
		}
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.eq("state", newsState));
		if (StringUtil.isNotBlank(categoryValue))
			detachedCriteria.add(Restrictions.eq("category", categoryValue));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSANNOUNCE));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("title", searchKey,
					MatchMode.ANYWHERE));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = newsService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return LIST;
	}

	/**
	 * 修改
	 */
	public String edit() {
		newsService.saveOrUpdate(entity);
		this.savaAdminLog("新闻",entity.getTitle(),CommonConst.LOGUPDATE);
		result = StringUtil.encode("操作成功！");
		return LISTANNOUNCE;
	}

	/**
	 * 修改
	 */
	public String add() {
		Calendar cal = Calendar.getInstance();
		String targetHtmlPath = "";
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String newsName = StringUtil.getDateString(f, new Date()) + "temp.html";
		String rootPath = "html/" + newsName;
		entity.setAdduserId(this.adminId());
		entity.setNewsType(CommonConst.NEWSANNOUNCE);
		entity.setAddTime(cal.getTime());
		entity.setStaticpath(rootPath);
		entity.setState(CommonConst.STATEINIT);
		newsService.save(entity);

		data.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, entity);
		targetHtmlPath = servletContext.getRealPath(rootPath);
		freemarkerHelper.createHTML(data, "announce.ftl", targetHtmlPath);
		this.savaAdminLog("公告",entity.getTitle(),CommonConst.LOGSAVE);
		result = StringUtil.encode("操作成功！");
		return LISTANNOUNCE;
	}
	public String del(){
		newsService.delete(entity);
		this.result=StringUtil.encode(CommonConst.DELSUCCESS);
		this.savaAdminLog("公告",entity.getTitle(), CommonConst.LOGDEL);
		return LISTANNOUNCE;
	}

	/**
	 * 查看修改
	 */
	public String viewEdit() {
		return VIEW;
	}

	/**
	 * 查看详细
	 */
	public String view() {
		return DETAIL;
	}
	/**
	 * 公告显示
	 * @return
	 */
	public String show(){
		return "show";
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public List<AbcCategory> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<AbcCategory> categorys) {
		this.categorys = categorys;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
}
