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
 * 2010-1-7           wangjin                      initial
 */

package com.abbcc.module.userNews;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcUser;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.CategoryService;
import com.abbcc.service.LogService;
import com.abbcc.service.NewsService;
import com.abbcc.service.UserService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;
import com.abbcc.util.constant.ModelType;

import freemarker.template.TemplateException;

public class NewsAction extends FileUploadAction<AbcNews> {
	private CategoryService categoryService;
	private UserService userService;
	private NewsService newsService;
	private String isTopnews;
	private String isRollingnews;
	private String isImagenews;
	private int affectRows;
	private AttachmentService attachmentService;
	private String frontTime;
	private String backTime;
	private String dealState;
	private String dealType;
	private String checkboxs;
	public int stepType;
	public String sourceCate;
	public int sourceSort;
	public String targetCate;
	public int targetSort;
	public String newsType;
	public String picText;
	public String picPath;
	private FreemarkerHelper freemarkerHelper = new FreemarkerHelper();
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	Map data = new HashMap();

	public void setFreemarkerHelper(FreemarkerHelper freemarkerHelper) {
		this.freemarkerHelper = freemarkerHelper;
	}

	public String getCheckboxs() {
		return checkboxs;
	}

	public void setCheckboxs(String checkboxs) {
		this.checkboxs = checkboxs;
	}

	public String getFrontTime() {
		return frontTime;
	}

	public void setFrontTime(String frontTime) {
		this.frontTime = frontTime;
	}

	public String getBackTime() {
		return backTime;
	}

	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}

	public String getDealState() {
		return dealState;
	}

	public void setDealState(String dealState) {
		this.dealState = dealState;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public int getAffectRows() {
		return affectRows;
	}

	public String getIsTopnews() {
		return isTopnews;
	}

	public void setIsTopnews(String isTopnews) {
		this.isTopnews = isTopnews;
	}

	public String getIsRollingnews() {
		return isRollingnews;
	}

	public void setIsRollingnews(String isRollingnews) {
		this.isRollingnews = isRollingnews;
	}

	public String getIsImagenews() {
		return isImagenews;
	}

	public void setIsImagenews(String isImagenews) {
		this.isImagenews = isImagenews;
	}

	public AbcUser getUser() {
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		return user;
	}

	public String getSortMap() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCategory.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("type",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("isroot", CommonConst.CATEGORYISROOT)));
		detachedCriteria.add(Restrictions.eq("isdisplay",
				CommonConst.CATEGORYISDISPLAY));
		List<AbcCategory> rootList = categoryService
				.findAllByCriteria(detachedCriteria);
		String firstId = "";
		if (rootList.size() > 0)
			firstId = rootList.get(0).getCategoryId();
		// 构造结果集利用循环嵌套取出子集
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		for (AbcCategory rl : rootList) {
			sortMap.put(rl.getCategoryId(), "|-" + rl.getName());
			DetachedCriteria detachedCriteria1 = DetachedCriteria
					.forClass(AbcCategory.class);
			detachedCriteria1.add(Restrictions.and(Restrictions.eq(
					"enterpriseId", this.getUser().getEnterpriseId()),
					Restrictions.eq("type", CommonConst.CATEGORYTYPENEWS)));
			detachedCriteria1.add(Restrictions.and(
					Restrictions.ne("state", CommonConst.STATEDEL),
					Restrictions.eq("grade", "01")));
			detachedCriteria1.add(Restrictions.eq("belongId",
					rl.getCategoryId()));
			detachedCriteria1.add(Restrictions.eq("isdisplay",
					CommonConst.CATEGORYISDISPLAY));
			List<AbcCategory> sonList = categoryService
					.findAllByCriteria(detachedCriteria1);
			for (AbcCategory sl : sonList)
				sortMap.put(sl.getCategoryId(), "|   |-" + sl.getName());

			rl.setSonCate(sonList);
		}
		deposit("sortMap",sortMap);
		//getSession().setAttribute("sortMap", sortMap);
		return firstId;
	}

	private Integer newSort() {
		String hql = "select max(sort) from AbcNews";

		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) newsService.find(hql);
		Integer sort = null;
		if (list.size() > 0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}

	public String show() {
		String firstId = getSortMap();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		if(StringUtil.isNotBlank(entity.getTitle()))
			detachedCriteria.add(Restrictions.like("title",entity.getTitle(),MatchMode.ANYWHERE));
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("newsType",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		if (entity.getCategory() != null)
			detachedCriteria.add(Restrictions.eq("category",
					entity.getCategory()));
		/*else
			detachedCriteria.add(Restrictions.eq("category", firstId));*/
		detachedCriteria.addOrder(Order.asc("state"));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.addOrder(Order.desc("imagenews"));
		pageList = newsService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "show";
	}

	public String addNews() throws Exception {
		/*if (nValidate())
			return INPUT;*/
		AbcUser user = this.getUser();
		Calendar cal = Calendar.getInstance();
		if (isImagenews.equals("true"))
			entity.setImagenews(CommonConst.NEWSIMAGENEWS);
		else
			entity.setImagenews(CommonConst.NEWSCOMMON);
		if (isTopnews.equals("true"))
			entity.setTopnews(CommonConst.NEWSTOPNEWS);
		if (isRollingnews.equals("true"))
			entity.setRollingnews(CommonConst.NEWSROLLINGNEWS);
		entity.setEnterpriseId(user.getEnterpriseId());
		entity.setAdduserId(user.getUserId());
		entity.setAddTime(cal.getTime());
		entity.setNewsType(CommonConst.NEWSTYPEUSER);
		entity.setDisplay(CommonConst.STATENORMAL);
		entity.setSort(this.newSort());
		if (entity.getCategory() == null) {
			entity.setCategory("999");
		}
		if (CommonConst.NEWSAUDIT)
			entity.setState(CommonConst.STATEINIT);
		else
			entity.setState(CommonConst.STATENORMAL);
		newsService.save(entity);
		setUploadState(entity.getContent());
		logService.savaLog("新闻", entity.getTitle(), CommonConst.LOGSAVE);
		AbcNews an = newsService.findById(entity.getNewsId());
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String username = this.getCurrentUser().getUsername();
		String newsName = StringUtil.getDateString(f, new Date()) + "temp.html";
		String rootPath = "html/" + newsName;
		an.setStaticpath(rootPath.substring(0, rootPath.lastIndexOf("."))
				+ "_0.html");
		newsService.saveOrUpdate(an);
		// 以下功能主要是为了生成静态html，分页
		String[] contents = null;
		String targetHtmlPath = "";
		String con = "<div style=\"page-break-after: always;\">\r\n\t<span style=\"display: none;\">&nbsp;</span></div>\r\n";
		// firfox,ie6传过来的分页符FF多一个分号
		contents = an
				.getContent()
				.split("<div style=\"page-break-after: always;?\">\r\n\t<span style=\"display: none;?\">&nbsp;</span></div>\r\n");
		/*
		 * // ie6传过来的分页符 if (contents.length == 1) { contents = an .getContent()
		 * .split(
		 * "<div style=\"page-break-after: always\">\r\n\t<span style=\"display: none\">&nbsp;</span></div>\r\n"
		 * ); }
		 */
		String[] newsPage = new String[contents.length];
		for (int i = 0; i < contents.length; i++) {
			newsPage[i] = rootPath.substring(0, rootPath.lastIndexOf("."))
					+ "_" + i + ".html";
			an.setContent(contents[i]);
			data.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, an);
			String backPage = "";
			String nexPage = "";
			if (i == 0) {
				backPage = "上一页";
			} else {
				backPage = "<a href=\""
						+ newsName.substring(0, newsName.lastIndexOf("."))
						+ "_" + (i - 1) + ".html\">上一页</a>";
			}
			if (i == contents.length - 1) {
				nexPage = "下一页";
			} else {
				nexPage = "<a href=\""
						+ newsName.substring(0, newsName.lastIndexOf("."))
						+ "_" + (i + 1) + ".html\">下一页</a>";
			}
			targetHtmlPath = servletContext.getRealPath(newsPage[i]);
			String paging = "<table><tr><td>" + backPage + "&nbsp&nbsp"
					+ nexPage + "&nbsp&nbsp本文共有" + contents.length + "页，本页第"
					+ (i + 1) + "页</td></tr></table>";
			data.put("page", paging);
			freemarkerHelper.createHTML(data, "news.ftl", targetHtmlPath);
		}

		if (!("".equals(picPath))) {
			InetAddress localhost = InetAddress.getLocalHost();
			String ip = localhost.getHostAddress();
			AbcAttachment att = new AbcAttachment();
			att.setBelongType(ModelType.BC);
			att.setUserId(this.getCurrentUser().getUserId());
			att.setBelongId(entity.getNewsId());
			att.setType(CommonConst.NEWSTITLEPICTURE);
			att.setServerPath(picPath);
			att.setUploadTime(new Date());
			att.setUserId(this.getCurrentUser().getUserId());
			att.setServerIp(ip);
			att.setState(CommonConst.STATENORMAL);
			attachmentService.save(att);
		}
		this.setTempAttachment(entity.getNewsId(), ModelType.BC);
		result = StringUtil.encode(CommonConst.ADDSUCCESS);
		return "returnShow";
	}

	/**
	 * 验证未通过的时候执行
	 */
	public void validateAdd() {
		addPage();
	}

	public String addPage() {
		this.getSortMap();
		return "showAddPage";
	}

	/**
	 * 通过JSON修改状态
	 * 
	 * @param categoryService
	 */
	public String updateDisplay() {
		newsService.saveOrUpdate(entity);
		logService.savaLog("新闻", entity.getTitle(), CommonConst.LOGUPDATE);
		affectRows = 1;
		return "json";
	}

	public String updateImagenews() {
		newsService.saveOrUpdate(entity);
		logService.savaLog("新闻", entity.getTitle(), CommonConst.LOGUPDATE);
		affectRows = 1;
		return "json";
	}

	@Recycle(name = "title", module = "新闻分类")
	public String del() {
		newsService.delete(entity);
		if (entity.getStaticpath() != null)
			deleteFile(entity.getStaticpath());
		logService.savaLog("新闻", entity.getTitle(), CommonConst.LOGDEL);
		result = CommonConst.DELSUCCESS;
		return "returnShow";
	}

	public String updatePage() throws IOException, TemplateException {
		AbcNews news = newsService.findById(entity.getNewsId());
		getRequest().setAttribute("news", news);
		this.getSortMap();
		return "showUpdatePage";
	}

	public String updateNews() throws IOException, TemplateException {
		if (nValidate())
			return INPUT;
		if (upload != null) {
			AbcAttachment oldAtt = new AbcAttachment();
			oldAtt.setType(CommonConst.NEWSTITLEPICTURE);
			oldAtt.setBelongType(ModelType.BC);
			oldAtt.setBelongId(entity.getNewsId());
			List<AbcAttachment> attList = attachmentService
					.findByExample(oldAtt);
			uploadImage();
			if (attList.size() > 0) {
				AbcAttachment att = attList.get(0);
				if (attList.size() == 0) {
					// 上传到attachment表里
					InetAddress localhost = InetAddress.getLocalHost();
					String ip = localhost.getHostAddress();
					AbcAttachment att1 = new AbcAttachment();
					att1.setBelongType(ModelType.BC);
					att1.setBelongId(entity.getNewsId());
					att1.setUserId(this.getCurrentUser().getUserId());
					att1.setType(CommonConst.NEWSTITLEPICTURE);
					att1.setServerPath(uploadFilePath);
					att1.setUploadTime(new Date());
					att1.setFilename(getUploadFileName().get(0));
					att1.setServerIp(ip);
					attachmentService.save(att1);
				} else {
					att.setAttId(att.getAttId());
					att.setServerPath(uploadFilePath);
					att.setFilename(getUploadFileName().get(0));
					attachmentService.saveOrUpdate(att);
				}
			}
		}
		if (isImagenews.equals("true")) {
			entity.setImagenews(CommonConst.NEWSIMAGENEWS);
		} else
			entity.setImagenews(CommonConst.NEWSCOMMON);
		if (isTopnews.equals("true")) {
			entity.setTopnews(CommonConst.NEWSTOPNEWS);
		} else
			entity.setTopnews("");
		if (isRollingnews.equals("true")) {
			entity.setRollingnews(CommonConst.NEWSROLLINGNEWS);
		} else
			entity.setRollingnews("");
		newsService.saveOrUpdate(entity);
		logService.savaLog("新闻", entity.getTitle(), CommonConst.LOGUPDATE);
		AbcNews an = newsService.findById(entity.getNewsId());
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String username = this.getCurrentUser().getUsername();
		String newsName = StringUtil.getDateString(f, new Date()) + "temp.html";
		String rootPath = "html/" + newsName;
		an.setStaticpath(rootPath.substring(0, rootPath.lastIndexOf("."))
				+ "_0.html");
		newsService.saveOrUpdate(an);
		// 以下功能主要是为了生成静态html，分页
		String[] contents = null;
		String targetHtmlPath = "";
		String con = "<div style=\"page-break-after: always;\">\r\n\t<span style=\"display: none;\">&nbsp;</span></div>\r\n";
		// firfox传过来的分页符
		contents = an
				.getContent()
				.split("<div style=\"page-break-after: always;\">\r\n\t<span style=\"display: none;\">&nbsp;</span></div>\r\n");
		// ie6传过来的分页符
		if (contents.length == 1) {
			contents = an
					.getContent()
					.split("<div style=\"page-break-after: always\">\r\n\t<span style=\"display: none\">&nbsp;</span></div>\r\n");
		}
		String[] newsPage = new String[contents.length];
		for (int i = 0; i < contents.length; i++) {
			newsPage[i] = rootPath.substring(0, rootPath.lastIndexOf("."))
					+ "_" + i + ".html";
			an.setContent(contents[i]);
			data.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, an);
			String backPage = "";
			String nexPage = "";
			if (i == 0) {
				backPage = "上一页";
			} else {
				backPage = "<a href=\""
						+ newsName.substring(0, newsName.lastIndexOf("."))
						+ "_" + (i - 1) + ".html\">上一页</a>";
			}
			if (i == contents.length - 1) {
				nexPage = "下一页";
			} else {
				nexPage = "<a href=\""
						+ newsName.substring(0, newsName.lastIndexOf("."))
						+ "_" + (i + 1) + ".html\">下一页</a>";
			}
			targetHtmlPath = servletContext.getRealPath(newsPage[i]);
			String paging = "<table><tr><td>" + backPage + "&nbsp&nbsp"
					+ nexPage + "&nbsp&nbsp本文共有" + contents.length + "页，本页第"
					+ (i + 1) + "页</td></tr></table>";
			data.put("page", paging);
			freemarkerHelper.createHTML(data, "news.ftl", targetHtmlPath);
			result = CommonConst.EDITSUCCESS;
			this.setTempAttachment(entity.getNewsId(), ModelType.BC);
		}
		return "returnShow";
	}

	public String search() {
		this.getSortMap();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("newsType",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("state"));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.addOrder(Order.desc("imagenews"));
		if (entity.getCategory() != null) {
			detachedCriteria.add(Restrictions.eq("category",
					entity.getCategory()));
			getRequest().setAttribute("categoryId", entity.getCategory());
		}
		if (StringUtil.isNotBlank(entity.getTitle()))
			detachedCriteria.add(Restrictions.eq("title", entity.getTitle()));
		if (StringUtil.isNotBlank(entity.getNkey()))
			detachedCriteria.add(Restrictions.eq("nkey", entity.getNkey()));
		if (StringUtil.isNotBlank(frontTime))
			detachedCriteria.add(Property.forName("addTime").ge(
					DateUtil.strToDate(frontTime)));
		if (StringUtil.isNotBlank(backTime))
			detachedCriteria.add(Property.forName("addTime").le(
					DateUtil.strToDate(backTime)));
		if (StringUtil.isNotBlank(dealState))
			if (dealState.equals("DA") || dealState.equals("DB"))
				detachedCriteria.add(Restrictions.eq("display",
						dealState.equals("DA") ? "01" : "02"));
			else
				detachedCriteria.add(Restrictions.eq("state",
						dealState.equals("DC") ? "00" : "01"));
		if (StringUtil.isNotBlank(dealType)) {
			if (dealType.equals("DA") || dealType.equals("DB")) {
				detachedCriteria.add(Restrictions.eq("imagenews",
						dealType.equals("DA") ? "01" : "02"));
			} else {
				if (dealType.equals("DC"))
					detachedCriteria.add(Restrictions.eq("topnews",
							CommonConst.NEWSTOPNEWS));
				else if (dealType.equals("DD"))
					detachedCriteria.add(Restrictions.eq("rollingnews",
							CommonConst.NEWSROLLINGNEWS));
			}
		}

		pageList = newsService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "show";
	}

	public String allDisplay() {
		String[] newsIds = checkboxs.split(",");
		for (int i = 0; i < newsIds.length; i++) {
			AbcNews news = newsService.findById(newsIds[i]);
			news.setNewsId(newsIds[i]);
			news.setDisplay(CommonConst.STATENORMAL);
			newsService.saveOrUpdate(news);
			logService.savaLog("新闻", entity.getTitle(), CommonConst.LOGUPDATE);
		}
		return "json";
	}

	@Recycle(id = "ch", name = "title", module = "新闻分类")
	public String allDel() {
		String[] newsIds = getRequest().getParameterValues("ch");
		for (int i = 0; i < newsIds.length; i++) {
			AbcNews news = newsService.findById(newsIds[i]);
			newsService.delete(news);
			if (news.getStaticpath() != null)
				deleteFile(news.getStaticpath());
			logService.savaLog("新闻", entity.getTitle(), CommonConst.LOGDEL);
		}
		result = CommonConst.DELSUCCESS;
		return "returnShow";
	}

	public String allNotDisplay() {
		String[] newsIds = checkboxs.split(",");
		for (int i = 0; i < newsIds.length; i++) {
			AbcNews news = newsService.findById(newsIds[i]);
			news.setNewsId(newsIds[i]);
			news.setDisplay(CommonConst.STATEDENY);
			newsService.saveOrUpdate(news);
			logService.savaLog("新闻", entity.getTitle(), CommonConst.LOGUPDATE);
		}
		return "json";
	}

	/**
	 * // * 新闻布局
	 * 
	 * @param categoryService
	 */

	public String layout() {
		String firstId = this.getSortMap();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("newsType",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		detachedCriteria.add(Restrictions.eq("category", firstId));
		detachedCriteria.addOrder(Order.desc("sort"));
		pageList = newsService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		resultList = newsService.findAllByCriteria(detachedCriteria);
		int i = 1;
		Map<String, Integer> sortList = new LinkedHashMap<String, Integer>();
		for (AbcNews news : resultList)
			sortList.put(news.getNewsId() + "," + news.getSort(), i++);
		getRequest().setAttribute("sortList", sortList);
		getRequest().setAttribute("firstId", firstId);
		return "showLayout";
	}

	public String imageSort() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("newsType",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		detachedCriteria.add(Restrictions.eq("imagenews",
				CommonConst.NEWSIMAGENEWS));
		detachedCriteria.addOrder(Order.desc("sort"));
		pageList = newsService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		resultList = newsService.findAllByCriteria(detachedCriteria);
		int i = 1;
		Map<String, Integer> sortList = new LinkedHashMap<String, Integer>();
		for (AbcNews news : resultList)
			sortList.put(news.getNewsId() + "," + news.getSort(), i++);
		getRequest().setAttribute("sortList", sortList);
		getRequest().setAttribute("newsType", "image");
		return "showLayout";
	}

	public String searchByCategory() {
		getSortMap();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getUser().getEnterpriseId()), Restrictions.eq("newsType",
				CommonConst.CATEGORYTYPENEWS)));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		detachedCriteria.add(Restrictions.eq("category", entity.getCategory()));
		getRequest().setAttribute("categoryId", entity.getCategory());
		detachedCriteria.addOrder(Order.desc("sort"));
		pageList = newsService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		resultList = newsService.findAllByCriteria(detachedCriteria);
		int i = 1;
		Map<String, Integer> sortList = new LinkedHashMap<String, Integer>();
		for (AbcNews news : resultList)
			sortList.put(news.getNewsId() + "," + news.getSort(), i++);
		getRequest().setAttribute("sortList", sortList);
		return "showLayout";
	}

	public String change() {
		changeSort(sourceCate, sourceSort, targetCate, targetSort);
		if (newsType != null && newsType.equals("image"))
			return "returnImage";
		return "returnshowLayout";
	}

	private int[] changeSort(String sourceCate, int sourceSort,
			String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
				|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update abc_news set sort=" + targetSort + " where news_id='"
				+ sourceCate + "'";
		sql[1] = "update abc_news set sort=" + sourceSort + " where news_id='"
				+ targetCate + "'";
		return newsService.batchUpdate(sql);
	}

	@SuppressWarnings("unchecked")
	public String step() {
		AbcUser user = getCurrentUser();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				user.getEnterpriseId()));
		if (entity.getCategory() != null)
			detachedCriteria.add(Restrictions.eq("category",
					entity.getCategory()));
		if (entity.getCategory() == null)
			detachedCriteria.add(Restrictions.eq("imagenews",
					CommonConst.NEWSIMAGENEWS));
		if (stepType == 1) {
			detachedCriteria.addOrder(Order.desc("sort"));
			detachedCriteria.add(Restrictions.lt("sort", sourceSort));
		} else {
			detachedCriteria.addOrder(Order.asc("sort"));
			detachedCriteria.add(Restrictions.gt("sort", sourceSort));
		}
		pageList = newsService.findPageByCriteria(detachedCriteria, 1, 0);
		for (AbcNews ac : (List<AbcNews>) pageList.getItems()) {
			changeSort(sourceCate, sourceSort, ac.getNewsId(), ac.getSort());
		}
		if (newsType != null && newsType.equals("image"))
			return "returnImage";
		return "returnshowLayout";
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public boolean deleteFile(String sPath) {

		Boolean flag = false;
		String filePath = "";
		String htmlName = "";
		try {
			filePath = sPath.substring(0, sPath.lastIndexOf("/"));
			htmlName = sPath.substring(sPath.lastIndexOf("/") + 1,
					sPath.length());
		} catch (Exception e) {
			filePath = sPath;
			htmlName = sPath;
		}

		sPath = CommonConst.REALPATH + StringUtil.pathReplace(sPath);
		File file = new File(CommonConst.REALPATH
				+ StringUtil.pathReplace(filePath));
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				String name = htmlName;
				try {
					name = htmlName.substring(0, htmlName.lastIndexOf("_"));
				} catch (Exception e) {
					e.printStackTrace();
					name = htmlName;
				}

				if (fileName.indexOf(name) == 0) {
					File filedel = new File(files[i].getPath());
					if (filedel.isFile() && filedel.exists()) {
						filedel.delete();
					}
				}
			}
		}
		return flag;
	}

	public boolean nValidate() {
		if (CommonConst.ISCONTROL) {
			if (getSession().getAttribute("addState").equals(
					CommonConst.STATEINIT)) {
				DetachedCriteria dc = DetachedCriteria.forClass(entity
						.getClass());
				dc.add(Restrictions.eq("enterpriseId", getCurrentUser()
						.getEnterpriseId())).setProjection(
						Projections.count("newsId"));
				int i = newsService.getCountByCriteria(dc);
				if (i >= CommonConst.NEWSCOUNT) {
					addFieldError("addState", "对不起，您目前还不是高级会员，新闻的发布数量不能超过"
							+ CommonConst.NEWSCOUNT
							+ "条!<a href=\"/user/upgrade/show\">升级后将不受限制</a>");
					return true;
				}
			}
		}
		if (!CheckKey.checkKey(entity.getTitle())) {
			this.addFieldError("title", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getNkey())) {
			this.addFieldError("nkey", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getOrigin())) {
			this.addFieldError("origin", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getAuthor())) {
			this.addFieldError("author", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getContent())) {
			this.addFieldError("content", "存在非法字符！");
			return true;
		}
		return false;
	}
}
