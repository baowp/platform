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
 * 2010-6-12           baowp                      initial
 */

package com.abbcc.news.action.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.merchants.models.Merchants;
import com.abbcc.merchants.service.MerchantsService;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.news.enums.DefaultClass;
import com.abbcc.news.enums.DefaultGenus;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.NewsClass;
import com.abbcc.news.models.NewsGenus;
import com.abbcc.news.models.NewsNews;
import com.abbcc.news.service.NewsClassService;
import com.abbcc.news.service.NewsGenusService;
import com.abbcc.news.service.NewsNewsService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.LogService;
import com.abbcc.service.NewsService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings( { "serial", "unchecked" })
public class NewsAction extends FileUploadAction<NewsNews> {
	public String notAudit;
	private NewsClassService newsClassService;
	private NewsGenusService newsGenusService;
	private NewsNewsService newsNewsService;
	private LogService logService;
	public List<NewsClass> classList;
	public List<NewsGenus> genusList;
	private FreemarkerHelper freemarkerHelper;
	private Map<String, Object> data = new HashMap<String, Object>();
	private NewsService newsService;
	private EnterpriseService enterpriseService;
	private MerchantsService merchantsService;
	public void setMerchantsService(MerchantsService merchantsService) {
		this.merchantsService = merchantsService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public String list() {
		NewsNews example = new NewsNews();
		if (!StringUtil.isBlank(entity.getClassSign()))
			example.setClassSign(entity.getClassSign());
		if (!StringUtil.isBlank(entity.getGenusSign()))
			example.setGenusSign(entity.getGenusSign());
		if(notAudit!=null)
			example.setDisplay(Visibility.hidden);
		example.setDomain(domain);
		DetachedCriteria dc = DetachedCriteria.forClass(NewsNews.class);
		if (!StringUtil.isBlank(entity.getKey()))
			dc.add(Restrictions.like("key","%"+entity.getKey()+"%"));
		dc.add(Example.create(example));
		dc.addOrder(Order.desc("addTime"));
		pageList = newsNewsService.findPageByCriteria(dc, pageSize,
				startIndex);
		return LIST;
	}

	public String save() {

		if (entity.getType().equals("true"))
			entity.setType("picture");
		else
			entity.setType("normal");
		if (entity.getPriority().equals("true"))
			entity.setPriority("top");
		else
			entity.setPriority("normal");
		if (entity.getNewsId() == null)
			entity.setAddTime(new Date());
		entity.setDisplay(Visibility.display);

		// 生成静态HTML
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String username = "admin";
		String newsName = StringUtil.getDateString(f, new Date()) + "temp.html";
		String rootPath = "html" + CommonConst.SEP + newsName;
		String staticpath=rootPath.substring(0, rootPath.lastIndexOf("."))
		+ "_0.html";
		entity.setStaticpath(staticpath);
		entity.setSort(newSort());
		newsNewsService.saveOrUpdate(entity);
		setUploadState(entity.getContent());
		uploadPic();
		// 以下功能主要是为了生成静态html，分页
		String targetHtmlPath = servletContext.getRealPath(staticpath);
		
		
			data.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, entity);
			
			freemarkerHelper = new FreemarkerHelper(servletContext
					.getRealPath("home/ftl"));
			

			// -------------------------------------------------------------
			// -----------------最近更新-----------------------------
			String sql = "from NewsNews where classSign='"
					+ entity.getClassSign() + "' order by 'newsId' desc";
			List<NewsNews> list = (List<NewsNews>) newsNewsService.find(sql);
			data.put("newsList", list);
			// -----------------阅读排行----------------------------
			String sql1 = "from NewsNews where classSign='"
					+ entity.getClassSign() + "' order by 'viewTimes' desc";
			List<NewsNews> list1 = (List<NewsNews>) newsNewsService.find(sql1);
			data.put("newsList1", list1);
			//---------------相关文章----------------------------
			String sql2="from NewsNews where classSign='"+entity.getClassSign()+"' order by 'addTime' desc";
			List<NewsNews> list2 = (List<NewsNews>) newsNewsService.find(sql2);
			data.put("newsList2",list2);
			//--------------风云人物------------------------------
			String sql3 = "from NewsNews where classSign='hyfyrw' or genusSign='hyfyrw' order by 'viewTimes' desc";
			List<NewsNews> list3 = (List<NewsNews>) newsNewsService.find(sql3);
			data.put("newsList3",list3);
			// data.put("div0",div0);
			freemarkerHelper.createHTML(data, "info.ftl", targetHtmlPath);
		return SUCCESS;
	}

	public void categoryList() {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsClass.class);
		NewsClass example = new NewsClass();
		example.setDomain(domain);
		example.setDisplay(Visibility.display);
		dc.add(Example.create(example))
				.addOrder(Property.forName("sort").asc());
		classList = newsClassService.findAllByCriteria(dc);
		if (classList.isEmpty()) {
			for (DefaultClass defaultClass : DefaultClass.values()) {
				NewsClass newsClass = new NewsClass();
				newsClass.setName(defaultClass.toString());
				newsClass.setSign(defaultClass.name());
				classList.add(newsClass);
			}
		}
		String classSign = entity.getClassSign();
		if (classSign == null && !classList.isEmpty())
			classSign = classList.get(0).getSign();
		if (classSign != null) {
			fetchGenus(classSign);
		}
	}

	public String fetchGenus() {
		fetchGenus(entity.getClassSign());
		return JSON;
	}

	private void fetchGenus(String classSign) {
		NewsGenus example = new NewsGenus();
		example.setDisplay(Visibility.display);
		example.setClassSign(classSign);
		example.setDomain(domain);
		DetachedCriteria dc = DetachedCriteria.forClass(NewsGenus.class);
		dc.add(Example.create(example))
				.addOrder(Property.forName("sort").asc());
		genusList = newsGenusService.findAllByCriteria(dc);
		if (genusList.isEmpty()) {
			for (DefaultGenus dg : DefaultGenus.values()) {
				if (dg.getDefaultClass().name().equals(classSign)) {
					NewsGenus newsGenus = new NewsGenus();
					newsGenus.setName(dg.toString());
					newsGenus.setSign(dg.name());
					genusList.add(newsGenus);
				}
			}
		}
	}

	public String edit() {
		String picPath = "";
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(entity.getNewsId());
		List<AbcAttachment> list = attachmentService.findByExample(attachment);
		if (list.size() != 0)
			picPath = list.get(0).getServerPath();
		getRequest().setAttribute("picPath", picPath);
		return EDIT;
	}

	public void uploadPic() {
		uploadImage();
		if (upload != null) {
			for (int i = 0; i < upload.size(); i++) {
				//attachment.setServerPath(serverPath);
				attachment.setBelongType(ModelType.BQ);
				attachment.setBelongId(entity.getNewsId());
				attachment.setType(CommonConst.PICTURE);
				attachment.setUploadTime(new Date());
				attachment.setFilename(getUploadFileName().get(i));
				attachment.setState(CommonConst.STATENORMAL);
				attachmentService.saveOrUpdate(attachment);
				logService.savaLog("图片", attachment.getFilename(),
						CommonConst.LOGSAVE);
			}
		}
	}
	public String staticPage(){
		return "staticPage";
	}

	// 生成静态首页的HTML
	public String indexStatic() {
		String rootPath = servletContext.getRealPath("index.html");
		data.put("swjxsList", this.getNewsList("swjxsdlm"));
		data.put("hyfyrwList",getNewsList("hyfyrw"));
		data.put("mykxList", this.getNewsList("mykx"));
		data.put("topNews", this.getTopNews("mykx"));
		data.put("newsPicPath", this.getTopPic(getTopNews("mykx").getNewsId()));

		data.put("cjywList", this.getNewsList("cjyw"));
		data.put("zsdhList", this.getNewsList("zsdh"));
		data.put("zsdl", this.getMerchants());
		//data.put("hydtList", this.getNewsList("hydt"));
		//data.put("topNewsDt", this.getTopNews("hydt"));
		//data.put("newsPicPathDt", this.getTopPic(getTopNews("hydt").getNewsId()));

		data.put("tjqyList", this.getNewsList("tjqy"));
		
		//data.put("zxjrList", getNewEnt());
		
		//data.put("zthdList",getNewsList("zthd"));
		
		data.put("xpssList", getNewsList("xpss"));

		freemarkerHelper = new FreemarkerHelper(servletContext
				.getRealPath("home/ftl"));
		freemarkerHelper.createHTML(data, "index.ftl", rootPath);
		return JSON;
	}
	public List getMerchants(){
		DetachedCriteria dc = DetachedCriteria
		.forClass(Merchants.class);
		if(entity.getType()!=null)
			dc.add(Restrictions.eq("type",entity.getType()));
		dc.add(Restrictions.and(Restrictions.eq("domain",domain),Restrictions.eq("state", CommonConst.STATENORMAL)));
		dc.addOrder(Order.desc("addTime"));
		List<Merchants> mList = merchantsService.findAllByCriteria(dc);

		return  mList;
	}

	public List getNewsList(String newsType) {
		String sql = "from NewsNews where genusSign='" + newsType
				+ "' or classSign='" + newsType + "' order by addTime desc";
		List<NewsNews> list = (List<NewsNews>) newsNewsService.find(sql);
		List list1=new ArrayList();
		for(NewsNews ns:list){
			NewsNews nn = ns;
			nn.setContent(subTextEdesc(78, ns.getContent()));
			nn.setNewsPicPath(this.picPath(ns.getNewsId()));
			list1.add(nn);
		}
		return list1;
	}

	public NewsNews getTopNews(String newsType) {
		
		String sql = "from NewsNews where priority='top' and genusSign='"
				+ newsType + "' or classSign='" + newsType
				+ "' order by addTime desc";
		List<NewsNews> list = (List<NewsNews>) newsNewsService.find(sql);
		NewsNews nn = list.get(0);
		nn.setContent(subTextEdesc(78, nn.getContent()));
		return nn;
	}

	public String getTopPic(String newsId) {
		String picPath = "http://img.51archetype.com/";
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(newsId);
		List<AbcAttachment> list = attachmentService.findByExample(attachment);
		if (list.size() != 0)
			picPath = picPath + list.get(0).getServerPath();
		return picPath;
	}

	// 最新厂家要闻
	public List zxcjyw() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		List<List> l = ObjectUtil.getInDataByList(getEnterpriseIdsByDomain());
		for(int i=0;i<l.size();i++)
			detachedCriteria.add(Restrictions.in("enterpriseId",l.get(i)));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPEUSER));
		List list = newsService.findAllByCriteria(detachedCriteria);
		return list;

	}

	// 最新加入企业
	public List getNewEnt() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcEnterprise.class);
		List<List> l = ObjectUtil.getInDataByList(getEnterpriseIdsByDomain());
		for(int i=0;i<l.size();i++)
			detachedCriteria.add(Restrictions.in("enterpriseId",l.get(i)));
		detachedCriteria.addOrder(Order.desc("enterpriseId"));
		List<AbcEnterprise> list= enterpriseService.findAllByCriteria(detachedCriteria);
		List list1=new ArrayList();
		for(AbcEnterprise ap:list){
			ap.setUrl(getEntUrl(ap.getEnterpriseId()));
			list1.add(ap);
		}
		return list1;
	}
	public String getEntUrl(String entId){
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory.get("soaUserService");
		AbcEnterprise ent = enterpriseService.findById(entId);
		AbcUser userIds = userService.findById(ent.getUserId());
		SoaUser su = new SoaUser();
		su.setUsername(userIds.getUsername());
		List<SoaUser> sou = (List)soaUserService.findByExample(su);
		if(sou.size()!=0){
			String url = "http://"+sou.get(0).getDomain();
			return "?url="+StringUtil.encode(url);
		}
		
		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		String []types={"00","01","02","03"};
		detachedCriteria.add(Restrictions.in("type", types));
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				entId), Restrictions.ne("state",
				CommonConst.STATEDEL)));
		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if(userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)){
				return "?url=/site/"+path;
			}
		}
		return "?url=http://"+path+".51archetype.com";
	}
	
	public String picPath(String newsId){
		String picPath="http://img.51archetype.com/";
		AttachmentService attachmentService = (AttachmentService)BeansFactory.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(newsId);
		List<AbcAttachment> list = attachmentService.findByExample(attachment);
		if(list.size()!=0)
			picPath=picPath+list.get(0).getServerPath();
		return picPath;
	}
	
	public String remove() {
		baseService.delete(entity);
		if(entity.getStaticpath()!=null){
			deleteFile(entity.getStaticpath());
		}
		return REMOVE;
	}
	public boolean deleteFile(String sPath) {

		Boolean flag = false;
		String filePath = "";
		String htmlName = "";
		try {
			filePath = sPath.substring(0, sPath.lastIndexOf("/"));
			htmlName = sPath.substring(sPath.lastIndexOf("/") + 1, sPath
					.length());
		} catch (Exception e) {
			filePath = sPath;
			htmlName = sPath;
		}

		sPath = CommonConst.REALPATH + StringUtil.pathReplace(sPath);
		File file = new File(CommonConst.REALPATH
				+ StringUtil.pathReplace(filePath));
		if(file!=null)
			file.delete();
		return flag;
	}
	
	public String allStatic() throws IOException{
		NewsNews example = new NewsNews();
		example.setDomain(domain);
		DetachedCriteria dc = DetachedCriteria.forClass(NewsNews.class);
		dc.add(Example.create(example));
		List<NewsNews> listNews = newsNewsService.findAllByCriteria(dc);
		int i=0;
		for(NewsNews nn:listNews){
			i++;
			// 生成静态HTML
			String staticpath=nn.getStaticpath();
			// 以下功能主要是为了生成静态html，分页
			String targetHtmlPath = servletContext.getRealPath(staticpath);
			
			
				data.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, nn);
				
				freemarkerHelper = new FreemarkerHelper(servletContext
						.getRealPath("home/ftl"));
				

				// -------------------------------------------------------------
				// -----------------最近更新-----------------------------
				String sql = "from NewsNews where classSign='"
						+ nn.getClassSign() + "' order by 'newsId' desc";
				List<NewsNews> list = (List<NewsNews>) newsNewsService.find(sql);
				data.put("newsList", list);
				// -----------------阅读排行----------------------------
				String sql1 = "from NewsNews where classSign='"
						+ nn.getClassSign() + "' order by 'viewTimes' desc";
				List<NewsNews> list1 = (List<NewsNews>) newsNewsService.find(sql1);
				data.put("newsList1", list1);
				//---------------相关文章----------------------------
				String sql2="from NewsNews where classSign='"+nn.getClassSign()+"' order by 'addTime' desc";
				List<NewsNews> list2 = (List<NewsNews>) newsNewsService.find(sql);
				data.put("newsList2",list2);
				//--------------风云人物------------------------------
				String sql3 = "from NewsNews where classSign='hyfyrw' or genusSign='hyfyrw' order by 'viewTimes' desc";
				List<NewsNews> list3 = (List<NewsNews>) newsNewsService.find(sql3);
				data.put("newsList3",list3);
				// data.put("div0",div0);
				freemarkerHelper.createHTML(data, "info.ftl", targetHtmlPath);
				getSession().setAttribute("progressIndex",i);
		}
		result = Integer.toString(listNews.size());
		return JSON;
	}
	public String progress(){
		result = (String)getSession().getAttribute("progressIndex");
		return JSON;
	}
	

	public void validateCategoryList() {
		clearFieldErrors();
	}

	public void setNewsClassService(NewsClassService newsClassService) {
		this.newsClassService = newsClassService;
	}

	public void setNewsGenusService(NewsGenusService newsGenusService) {
		this.newsGenusService = newsGenusService;
	}

	public void setNewsNewsService(NewsNewsService newsNewsService) {
		this.newsNewsService = newsNewsService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String subTextEdesc(int i, String text) {
		String s = StringUtil.parseHTMLtoText(text);
		if (s.length() > i) {
			return s.substring(0, i - 1);
		}
		return s;
	}
	private Integer newSort() {
		String hql = "";

			hql = "select max(sort) from NewsNews";
		
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) newsNewsService.find(hql);
		Integer sort=null;
		if(list.size()>0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}
}
