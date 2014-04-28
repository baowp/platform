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

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcUser;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.CategoryService;
import com.abbcc.service.NewsService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;

import freemarker.template.TemplateException;

/**
 **NewsManageAction.java
 **/
@SuppressWarnings("serial")
public class NewsManageAction extends FileUploadAction<AbcNews> {
	private String LISTNEWS = "listnews";
	private NewsService newsService;
	private String entId;
	private String[] newsIds;
	private String entName;
	private String searchKey;
	private String isTopnews;
	private String isRollingnews;
	private String isImagenews;
	public String frontTime;
	public String backTime;
	public String dealState;
	public String dealType;
	public String checkboxs;
	public int stepType;
	public String sourceCate;
	public int sourceSort;
	public String targetCate;
	public int targetSort;
	public String newsType;
	public String picPath;
	public String category;
	private String newsState = CommonConst.STATEINIT;
	private AttachmentService attachmentService;
	private CategoryService categoryService;
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

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	private FreemarkerHelper freemarkerHelper = new FreemarkerHelper();
	Map data = new HashMap();

	public void setFreemarkerHelper(FreemarkerHelper freemarkerHelper) {
		this.freemarkerHelper = freemarkerHelper;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String[] getNewsIds() {
		return newsIds;
	}

	public void setNewsIds(String[] newsIds) {
		this.newsIds = newsIds;
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

	public String getNewsState() {
		return newsState;
	}

	public void setNewsState(String newsState) {
		this.newsState = newsState;
	}

	/**
	 * 查看审核
	 * 
	 * @return
	 */
	public String viewAudit() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.eq("state", newsState));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPEUSER));
		if (StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("title", searchKey,
					MatchMode.ANYWHERE));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = newsService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return INPUT;
	}

	/**
	 * 编辑审核
	 * 
	 * @return
	 */
	public String editAudit() {
		for (String newsId : newsIds) {
			String[] newsIdAndState = StringUtil.splitBySemicolon(newsId);
			AbcNews news = newsService.findById(newsIdAndState[0]);
			news.setState(newsIdAndState[1]);
			if (newsIdAndState[1].equals(CommonConst.STATENORMAL)) {
				news.setAuditId(this.adminId());
				news.setAuditTime(new Date());
			}
			newsService.saveOrUpdate(news);
			this.savaAdminLog("新闻审核", news.getTitle(), CommonConst.LOGUPDATE);
		}
		result = "审核成功！";
		return VIEW;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public String list() {
		this.getSortMap();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPEUSER));
		if (StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
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
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		for (String newsId : newsIds) {
			String[] newsIdAndState = StringUtil.splitBySemicolon(newsId);
			AbcNews news = newsService.findById(newsIdAndState[0]);
			news.setState(newsIdAndState[1]);
			newsService.delete(news);
			this.savaAdminLog("新闻", news.getTitle(), CommonConst.LOGDEL);
		}
		result = "删除成功！";
		return LISTNEWS;
	}

	/**
	 * 查看详细
	 */
	public String view() {
		return DETAIL;
	}

	public String listSys() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPESYS));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("title", searchKey,
					MatchMode.ANYWHERE));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = newsService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return "listSys";
	}

	public String addSys() throws Exception {
		AbcAdmin admin = this.getCurrentAdmin();
		Calendar cal = Calendar.getInstance();
		if (isImagenews.equals("true"))
			entity.setImagenews(CommonConst.NEWSIMAGENEWS);
		else
			entity.setImagenews(CommonConst.NEWSCOMMON);
		if (isTopnews.equals("true"))
			entity.setTopnews(CommonConst.NEWSTOPNEWS);
		if (isRollingnews.equals("true"))
			entity.setRollingnews(CommonConst.NEWSROLLINGNEWS);
		entity.setAdduserId(admin.getAdminId());
		entity.setAddTime(cal.getTime());
		entity.setNewsType(CommonConst.NEWSTYPESYS);
		entity.setDisplay(CommonConst.STATENORMAL);
		entity.setSort(this.newSort());
		entity.setDomain(domain);
		if (entity.getCategory() == null) {
			entity.setCategory("999");
		}
		if (CommonConst.NEWSAUDIT)
			entity.setState(CommonConst.STATEINIT);
		else
			entity.setState(CommonConst.STATENORMAL);
		newsService.save(entity);
		this.savaAdminLog("新闻", entity.getTitle(), CommonConst.LOGSAVE);
		AbcNews an = newsService.findById(entity.getNewsId());
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String username = this.getCurrentAdmin().getUsername();
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
				.split(
						"<div style=\"page-break-after: always;\">\r\n\t<span style=\"display: none;\">&nbsp;</span></div>\r\n");
		// ie6传过来的分页符
		if (contents.length == 1) {
			contents = an
					.getContent()
					.split(
							"<div style=\"page-break-after: always\">\r\n\t<span style=\"display: none\">&nbsp;</span></div>\r\n");
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
		}

		if (upload!=null) {
			uploadImage();
			InetAddress localhost = InetAddress.getLocalHost();
			String ip = localhost.getHostAddress();
			AbcAttachment att = new AbcAttachment();
			att.setBelongType(ModelType.BC);
			att.setUserId(this.getCurrentAdmin().getAdminId());
			att.setBelongId(entity.getNewsId());
			att.setType(CommonConst.NEWSTITLEPICTURE);
			att.setServerPath(uploadFilePath);
			att.setUploadTime(new Date());
			att.setUserId(this.getCurrentAdmin().getAdminId());
			att.setServerIp(ip);
			att.setState(CommonConst.STATENORMAL);
			attachmentService.save(att);
		}
		this.setTempAttachment(entity.getNewsId(), ModelType.BC);
		result = StringUtil.encode(CommonConst.ADDSUCCESS);
		return "returnShow";
	}
	
	public String addPage() {
		this.getSortMap();
		return "showAddPage";
	}
	
	public String del() {
		newsService.delete(entity);
		if (entity.getStaticpath() != null)
			deleteFile(entity.getStaticpath());
		this.savaAdminLog("新闻", entity.getTitle(), CommonConst.LOGDEL);
		result = StringUtil.encode(CommonConst.ADDSUCCESS);
		return "returnShow";
	}
	public String updatePage() throws IOException, TemplateException {
		AbcNews news = newsService.findById(entity.getNewsId());
		getRequest().setAttribute("news", news);
		this.getSortMap();
		return "showUpdatePage";
	}
	public String updateNews(){

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
		entity.setState(CommonConst.STATEINIT);
		newsService.saveOrUpdate(entity);
		if (upload != null) {
			AbcAttachment oldAtt = new AbcAttachment();
			oldAtt.setType(CommonConst.NEWSTITLEPICTURE);
			oldAtt.setBelongType(ModelType.BC);
			oldAtt.setBelongId(entity.getNewsId());
			List<AbcAttachment> attList = attachmentService
					.findByExample(oldAtt);
			uploadImage();
			if (attList.size() == 0) {
				// 上传到attachment表里
				InetAddress localhost = null;
				try {
					localhost = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String ip = localhost.getHostAddress();
				AbcAttachment att1 = new AbcAttachment();
				att1.setBelongType(ModelType.BC);
				att1.setBelongId(entity.getNewsId());
				att1.setUserId(this.getCurrentAdmin().getAdminId());
				att1.setType(CommonConst.NEWSTITLEPICTURE);
				att1.setServerPath(uploadFilePath);
				att1.setUploadTime(new Date());
				att1.setFilename(getUploadFileName().get(0));
				att1.setServerIp(ip);
				attachmentService.save(att1);
			} else {
				AbcAttachment att = attList.get(0);
				removeAll(att.getServerPath());
				att.setAttId(attList.get(0).getAttId());
				att.setServerPath(uploadFilePath);
				att.setFilename(getUploadFileName().get(0));
				attachmentService.saveOrUpdate(att);
			}
		}
		this.savaAdminLog("新闻", entity.getTitle(), CommonConst.LOGUPDATE);
		AbcNews an = newsService.findById(entity.getNewsId());
		deleteFile(an.getStaticpath());
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String username = this.getCurrentAdmin().getUsername();
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
				.split(
						"<div style=\"page-break-after: always;\">\r\n\t<span style=\"display: none;\">&nbsp;</span></div>\r\n");
		// ie6传过来的分页符
		if (contents.length == 1) {
			contents = an
					.getContent()
					.split(
							"<div style=\"page-break-after: always\">\r\n\t<span style=\"display: none\">&nbsp;</span></div>\r\n");
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
		}
		result = StringUtil.encode(CommonConst.EDITSUCCESS);
		this.setTempAttachment(entity.getNewsId(), ModelType.BC);
		return "returnShow";
	}

	public String search() {
		this.getSortMap();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.CATEGORYTYPEADMINNEWS));
		detachedCriteria.addOrder(Order.desc("state"));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.addOrder(Order.desc("imagenews"));
		if (entity.getCategory() != null) {
			detachedCriteria.add(Restrictions.eq("category", entity
					.getCategory()));
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
				detachedCriteria.add(Restrictions.eq("display", dealState
						.equals("DA") ? "01" : "02"));
			else
				detachedCriteria.add(Restrictions.eq("state", dealState
						.equals("DC") ? "00" : "01"));
		if (StringUtil.isNotBlank(dealType)) {
			if (dealType.equals("DA") || dealType.equals("DB")) {
				detachedCriteria.add(Restrictions.eq("imagenews", dealType
						.equals("DA") ? "01" : "02"));
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
			savaAdminLog("新闻", entity.getTitle(), CommonConst.LOGUPDATE);
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
			savaAdminLog("新闻", entity.getTitle(), CommonConst.LOGDEL);
		}
		result = StringUtil.encode(CommonConst.DELSUCCESS);
		return "returnShow";
	}

	public String allNotDisplay() {
		String[] newsIds = checkboxs.split(",");
		for (int i = 0; i < newsIds.length; i++) {
			AbcNews news = newsService.findById(newsIds[i]);
			news.setNewsId(newsIds[i]);
			news.setDisplay(CommonConst.STATEDENY);
			newsService.saveOrUpdate(news);
			savaAdminLog("新闻", entity.getTitle(), CommonConst.LOGUPDATE);
		}
		return "json";
	}
	public boolean deleteFile(String sPath) {

		Boolean flag = false;
		String filePath="";
		String htmlName="";
		try{
			filePath = sPath.substring(0, sPath.lastIndexOf("/"));
			htmlName = sPath.substring(sPath.lastIndexOf("/") + 1, sPath
					.length());
		}catch(Exception e){
			filePath=sPath;
			htmlName=sPath;
		}
		sPath = CommonConst.REALPATH + StringUtil.pathReplace(sPath);
		File file = new File(CommonConst.REALPATH
				+ StringUtil.pathReplace(filePath));
		File[] files = file.listFiles();
		if(files!=null){
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String name=htmlName;
			try{
				name = htmlName.substring(0, htmlName.lastIndexOf("_"));
			}catch(Exception e){
				e.printStackTrace();
				name=htmlName;
			}
			if (fileName.indexOf(name) == 0) {
				File filedel = new File(files[i].getPath());
				if (filedel.isFile() && filedel.exists()) {
					filedel.delete();
				}
			}
		}}
		return flag;
	}

	private Integer newSort() {
		String hql = "select max(sort) from AbcNews";

		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) newsService.find(hql);
		Integer sort=null;
		if(list.size()>0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}
	public String getSortMap() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCategory.class);
		detachedCriteria.add(Restrictions.eq("type",
				CommonConst.CATEGORYTYPEADMINNEWS));
		detachedCriteria.add(Restrictions.and(Restrictions.ne("state",
				CommonConst.STATEDEL), Restrictions.eq("isroot",
				CommonConst.CATEGORYISROOT)));
		detachedCriteria.add(Restrictions.eq("isdisplay",
				CommonConst.CATEGORYISDISPLAY));
		List<AbcCategory> rootList = categoryService
				.findAllByCriteria(detachedCriteria);
		String firstId="";
		if(rootList.size()!=0)
			firstId = rootList.get(0).getCategoryId();
		// 构造结果集利用循环嵌套取出子集
		Map<String, String> sortMap = new LinkedHashMap<String, String>();
		for (AbcCategory rl : rootList) {
			sortMap.put(rl.getCategoryId(), "|-" + rl.getName());
			DetachedCriteria detachedCriteria1 = DetachedCriteria
					.forClass(AbcCategory.class);
			detachedCriteria1.add(Restrictions.eq("type", CommonConst.CATEGORYTYPEADMINNEWS));
			detachedCriteria1.add(Restrictions.and(Restrictions.ne("state",
					CommonConst.STATEDEL), Restrictions.eq("grade", "01")));
			detachedCriteria1.add(Restrictions.eq("belongId", rl
					.getCategoryId()));
			detachedCriteria1.add(Restrictions.eq("isdisplay",
					CommonConst.CATEGORYISDISPLAY));
			List<AbcCategory> sonList = categoryService
					.findAllByCriteria(detachedCriteria1);
			for (AbcCategory sl : sonList)
				sortMap.put(sl.getCategoryId(), "|   |-" + sl.getName());

			rl.setSonCate(sonList);
		}
		//deposit("srotMap",sortMap);
		getSession().setAttribute("sortMap", sortMap);
		return firstId;
	}
	/**
	 * // * 新闻布局
	 * 
	 * @param categoryService
	 */

	public String layOut() {
		String firstId = this.getSortMap();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPESYS));
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
		return "showLayout";
	}

	public String imageSort() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPESYS));
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
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPESYS));
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
		if (entity.getCategory() != null)
			detachedCriteria.add(Restrictions.eq("category", entity
					.getCategory()));
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
	/**
	 * 把公司新闻升级为系统新闻
	 */
	public String upgorad(){
		AbcNews an = newsService.findById(entity.getNewsId());
		an.setAddTime(new Date());
		an.setAdduserId(this.getCurrentAdmin().getAdminId());
		an.setCategory(category);
		an.setAuthor(entity.getAuthor());
		an.setOrigin(entity.getOrigin());
		an.setNewsType(CommonConst.NEWSTYPESYS);
		an.setDomain(domain);
		newsService.save(an);
		this.result=StringUtil.encode("已经添加到系统新闻");
		return "returnList";
		
	}
	//修改新闻时删除老的新闻图片
	private void removeAll(String srcPath){
		String to1 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_3.jpg";
		String to2 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_4.jpg";
		String to3 = srcPath.substring(0, srcPath.lastIndexOf(".")) + "_5.jpg";
		deletePic(srcPath);
		deletePic(to1);
		deletePic(to2);
		deletePic(to3);

	}
	 public boolean deletePic(String sPath) {  
	     Boolean flag = false;  
	     sPath = ConfConst.FILE_UPLOAD_DIR+StringUtil.pathReplace(sPath);
	     File file = new File(sPath);  
	     // 路径为文件且不为空则进行删除  
	     if (file.isFile() && file.exists()) {  
	         file.delete();  
	         flag = true;  
	     }  
	     return flag;  
	} 
}
