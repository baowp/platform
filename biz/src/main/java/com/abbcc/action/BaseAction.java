/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewBaseAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-7           yixiugg                      initial
 **/

package com.abbcc.action;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.service.AdminService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.BaseService;
import com.abbcc.service.LogService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.FileUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * *NewBaseAction.java
 */
@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,
		Preparable, ServletContextAware {
	public static final int DEFAULT_PAGESIZE = 20;

	protected static final String VIEW = "view";

	protected static final String LIST = "list";

	protected static final String QUERY = "query";

	protected static final String EDIT = "edit";

	protected static final String ADD = "add";

	protected static final String JSON = "json";

	protected static final String REMOVE = "remove";

	protected static final String DETAIL = "detail";

	private String targetModule;

	protected ServletContext servletContext;
	@Autowired
	protected BaseService baseService;

	LogService logService = (LogService) BeansFactory.get("logService");;

	protected String domain;
	public String redirectDomain;

	protected final org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(this.getClass());

	UserService userService = (UserService) BeansFactory.get("userService");

	protected Class<T> entityClass;
	protected T entity;
	protected String id;
	protected int page = 1;
	protected int startIndex = 0;
	protected int pageSize = DEFAULT_PAGESIZE;

	protected PaginationSupport pageList;
	protected PaginationSupport<T> pageSupport;

	public List<T> resultList;

	protected String result;

	public String callback;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			log.info("未能获取泛型类");
		}
	}

	public T getModel() {
		if (entity == null) {
			try {
				entity = (T) entityClass.newInstance();
			} catch (Exception e) {
				log.info("无法创建模型实例");
				return null;
			}
		}
		return entity;
	}

	public HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected ServletContext getApplication() {
		ServletContext app = ServletActionContext.getServletContext();
		return app;
	}

	protected void output(Object s) throws IOException {
		getResponse().setContentType("text/html;charset=utf-8");
		getResponse().getWriter().print(s);
	}

	@SuppressWarnings("unchecked")
	public void prepare() throws Exception {
		if (this.id != null && id.length() > 0) {
			if (entityClass != null)
				entity = (T) baseService.findById(entityClass, id);
		} else {
			entity = (T) entityClass.newInstance();
			prepareModelInner(entity);
		}
	}

	/**
	 * @param model
	 */
	protected void prepareModelInner(T model) {

	}

	protected void setRedirectDomain(String redirect) {
		if (redirect != null) {
			redirectDomain = redirect;
			return;
		}
		Cookie[] cookie = getRequest().getCookies();
		for (Cookie c : cookie) {
			if (c.getName().equals("redirectDomain"))
				redirectDomain = c.getValue();
		}
	}

	protected void deposit(String key, Object o) {
		getRequest().setAttribute(key, o);
	}

	public Serializable save() {
		return SUCCESS;
	}

	@SkipValidation
	public String view() {
		return VIEW;
	}

	public String edit() {
		return EDIT;
	}

	public String list() {
		return LIST;
	}

	public String query() {
		return QUERY;
	}

	public String add() {
		return ADD;
	}

	public String remove() {
		return REMOVE;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setServletContext(ServletContext context) {
		this.servletContext = context;
		domain = getRequest().getServerName();
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PaginationSupport<?> getPageList() {
		return pageList;
	}

	public void setPageList(PaginationSupport pageList) {
		this.pageList = pageList;
	}

	public PaginationSupport<T> getPageSupport() {
		return pageSupport;
	}

	public void setPageSupport(PaginationSupport<T> pageSupport) {
		this.pageSupport = pageSupport;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public String getTargetModule() {
		return targetModule;
	}

	public void setTargetModule(String targetModule) {
		this.targetModule = targetModule;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		if (page > 0)
			startIndex = (page - 1) * pageSize;
	}

	public boolean isBlank(String str) {
		return StringUtil.isBlank(str);
	}

	public String adminId() {
		String addAdmin = ((AbcAdmin) this.getSession().getAttribute(
				CommonConst.SESSIONADMIN)).getAdminId();
		return addAdmin;
	}

	public AbcAdmin getCurrentAdmin() {
		return (AbcAdmin) this.getSession().getAttribute(
				CommonConst.SESSIONADMIN);
	}

	public AbcUser getCurrentUser() {
		return (AbcUser) getSession().getAttribute(CommonConst.SESSIONUSER);
	}

	public AbcEnterprise getCurrentEnt() {
		return (AbcEnterprise) getSession()
				.getAttribute(CommonConst.SESSIONENT);
	}

	public String getLoginUserId() {
		String userId = (String) getSession().getAttribute(
				CommonConst.SESSIONLOGINUSERID);
		return userId;
	}

	@SuppressWarnings("unchecked")
	public void setTempAttachment(String belongId, ModelType belongType) {
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		List<AbcAttachment> tempAttachments = (List<AbcAttachment>) this
				.getSession().getAttribute(CommonConst.SESSIONATTACHMENTS);
		if (tempAttachments != null && tempAttachments.size() > 0) {
			for (AbcAttachment att : tempAttachments) {
				att.setBelongId(belongId);
				att.setBelongType(belongType);
				attachmentService.saveOrUpdate(att);
			}
			this.getSession().setAttribute(CommonConst.SESSIONATTACHMENTS,
					new ArrayList());
		}
	}

	public void savaLog(String name, String desc, String logType) {
		logService.savaLog(name, desc, logType);
	}

	public void savaAdminLog(String name, String desc, String logType) {
		logService.savaAdminLog(name, desc, logType);
	}

	protected String[] getAdminIdByOrigi(String type) {
		AdminService adminService = (AdminService) BeansFactory
				.get("adminService");
		String hql = "select adminId from AbcAdmin where domain='" + type + "'";
		List<String> ulist = adminService.findByHql(hql);
		String uarray[] = new String[ulist.size()];
		for (int i = 0; i < ulist.size(); i++) {
			uarray[i] = ulist.get(i);
		}
		return uarray;
	}

	protected List getEnterpriseIdsByDomain() {
		DetachedCriteria eIdList = DetachedCriteria.forClass(AbcUser.class);
		eIdList.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()))
				.add(Restrictions.ne("state", CommonConst.STATEINIT))
				.add(Restrictions.isNotNull("type"))
				.add(Restrictions.isNotNull("grade"))
				.setProjection(Projections.property("enterpriseId"));
		List eList = baseService.findAllByCriteria(eIdList);
		return eList;
	}

	protected List getUserIdsByDomain() {
		DetachedCriteria userIdList = DetachedCriteria.forClass(AbcUser.class);
		userIdList
				.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()))
				.add(Restrictions.ne("state", CommonConst.STATEINIT))
				.add(Restrictions.isNotNull("type"))
				.add(Restrictions.isNotNull("grade"))
				.setProjection(Projections.property("userId"));
		List uList = baseService.findAllByCriteria(userIdList);
		return uList;
	}

	public void reloadSession(String SessionKey, Object obj) {
		getSession().setAttribute(SessionKey, obj);
	}

	// 把内容里的图片对照存在attachment表里的记录，存在状态改成01,不存在改成00
	public void setUploadState(String content) {
		AbcAttachment att = new AbcAttachment();
		if (getCurrentUser() != null)
			att.setUserId(getCurrentUser().getUserId());
		else if (getCurrentAdmin() != null)
			att.setUserId(getCurrentAdmin().getAdminId());
		att.setBelongType(ModelType.AD);
		att.setFiledesc("xheditor");
		att.setState(CommonConst.STATEINIT);
		List<AbcAttachment> list = baseService.findByExample(att);
		for (AbcAttachment at : list) {
			if (content.indexOf(at.getServerPath()) > -1) {
				at.setState(CommonConst.STATENORMAL);
				baseService.update(at);
			}
		}
	}

	public void delNoUpload() {
		AbcAttachment att = new AbcAttachment();
		att.setUserId(getCurrentUser().getUserId());
		att.setBelongType(ModelType.AD);
		att.setFiledesc("xheditor");
		att.setState(CommonConst.STATEINIT);
		List<AbcAttachment> list = baseService.findByExample(att);
		for (AbcAttachment at : list) {
			FileUtil.removeAll(at.getServerPath());
			baseService.delete(at);
		}
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public boolean bitAnd(Integer i, Integer j) {
		return (i & j) == 0 ? false : true;
	}
}
