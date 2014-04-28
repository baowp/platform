package com.abbcc.module.help;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.models.AbcNews;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.NewsService;
import com.abbcc.service.PayuserService;
import com.abbcc.util.StringUtil;

public class HelpCenterAction extends BaseAction<AbcNews> {
	@Autowired
	private NewsService newsService;
	@Autowired
	private PayuserService payuserService;
	private FreemarkerHelper freemarkerHelper = new FreemarkerHelper();
	List<AbcNews> list;
	public List<AbcNews> getList() {
		return list;
	}

	public void setFreemarkerHelper(FreemarkerHelper freemarkerHelper) {
		this.freemarkerHelper = freemarkerHelper;
	}

	Map data = new HashMap();

	public String show() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("newsType", CommonConst.HELPCENTER)));
		detachedCriteria.addOrder(Order.desc("sort"));
		list = newsService.findAllByCriteria(detachedCriteria);
		getRequest().setAttribute("helpList", list);
		return "show";
	}

	public String adminView() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("newsType", CommonConst.HELPCENTER)));
		detachedCriteria.addOrder(Order.desc("addTime"));
		pageList = newsService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "view";
	}

	public String add() {
		entity.setNewsType(CommonConst.HELPCENTER);
		entity.setAdduserId(this.adminId());
		entity.setAddTime(new Date());
		entity.setState(CommonConst.STATENORMAL);
		newsService.save(entity);
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		String newsName = StringUtil.getDateString(f, new Date()) + "temp.html";
		String rootPath = "help/" + newsName;
		entity.setStaticpath(rootPath);
		newsService.saveOrUpdate(entity);
		data.put("helps", entity);
		String targetHtmlPath = servletContext.getRealPath(rootPath);
		freemarkerHelper.createHTML(data, "help.ftl", targetHtmlPath);
		this.savaAdminLog("帮助", entity.getTitle(), CommonConst.LOGSAVE);
		return "returnView";
	}

	public String detail() {
		return "detail";
	}

	@Recycle(id = "dh", name = "title", module = "帮助中心")
	public String del() {
		String[] helpIds = getRequest().getParameterValues("dh");
		for (int i = 0; i < helpIds.length; i++) {
			AbcNews news = newsService.findById(helpIds[i]);
			if(entity.getStaticpath()!=null){
				deleteFile(news.getStaticpath());
			}
			newsService.delete(news);
		}
		return "returnView";
	}

	public String tree() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("newsType", CommonConst.HELPCENTER)));
		detachedCriteria.addOrder(Order.desc("sort"));
		list = newsService.findAllByCriteria(detachedCriteria);
		return "tree";
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
}
