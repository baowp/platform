/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserSiteManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-3           yixiugg                      initial
 **/

package com.abbcc.module.soa;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcPayuser;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.models.SoaWebserver;
import com.abbcc.service.PaylogService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.SoaTemplateService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.SoaWebserverService;
import com.abbcc.service.UserService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.StringUtil;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * *UserSiteManageAction.java
 */
@SuppressWarnings("serial")
public class UserSiteManageAction extends BaseAction<SoaUser> {
	private SoaUserService soaUserService;
	private UserService userService;
	private String searchKey;
	private String searchDomain;
	private String searchServerId;
	private SoaWebserverService soaWebserverService;
	private PayuserService payuserService;
	private PaylogService paylogService;


	public void setPayuserService(PayuserService payuserService) {
		this.payuserService = payuserService;
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}

	public SoaWebserverService getSoaWebserverService() {
		return soaWebserverService;
	}

	public void setSoaWebserverService(SoaWebserverService soaWebserverService) {
		this.soaWebserverService = soaWebserverService;
	}

	public SoaUserService getSoaUserService() {
		return soaUserService;
	}

	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchServerId() {
		return searchServerId;
	}

	public void setSearchServerId(String searchServerId) {
		this.searchServerId = searchServerId;
	}

	public String getSearchDomain() {
		return searchDomain;
	}

	public void setSearchDomain(String searchDomain) {
		this.searchDomain = searchDomain;
	}

	/**
	 * 列表
	 */
	public String list() {
		List servers = (List) this.getSession().getAttribute(
				CommonConst.SESSIONWEBSERVERS);
		if (servers == null || servers.size() == 0) {
			servers = soaWebserverService.findAll(SoaWebserver.class);
			this.getSession().setAttribute(CommonConst.SESSIONWEBSERVERS,
					servers);
		}
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(SoaUser.class);
		detachedCriteria.addOrder(Order.desc("usersiteId"));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("username", searchKey,
					MatchMode.ANYWHERE));
		if (StringUtil.isNotBlank(searchDomain))
			detachedCriteria.add(Restrictions.like("domain", searchDomain,
					MatchMode.ANYWHERE));
		if (StringUtil.isNotBlank(searchServerId))
			detachedCriteria.add(Restrictions.eq("serverId", searchServerId));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = soaUserService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return LIST;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	@InputConfig(methodName = "view")
	public String update() {
		soaUserService.saveOrUpdate(entity);
		this.result = "修改成功！";
		return list();
	}
	
	public String remove(){
		soaUserService.delete(entity);
		this.result="删除成功!";
		return list();
	}

	/**
	 * 添加
	 */
	public String add() {
		SoaUser su = new SoaUser();
		su.setUsername(entity.getUsername());
		List list = soaUserService.findByExample(su);
		if(list.size()!=0){
			this.result="添加失败，用户名已经存在!";
			return list();
		}
		soaUserService.save(entity);
		//修改用户等级
		AbcUser user = userService.getUserByUsername(entity.getUsername());
		user.setGrade(CommonConst.USERGRADETWO);
		userService.saveOrUpdate(user);
		//插入到付费用户表和付费记录表里
		AbcPayuser ap = new AbcPayuser();
		ap.setAuditAdmin(this.getCurrentAdmin().getAdminId());
		ap.setPaytype(CommonConst.USERGRADETWO);
		ap.setUserId(user.getUserId());
		ap.setApplyTime(new Date());
		ap.setAuditState(CommonConst.STATENORMAL);
		payuserService.save(ap);
		
		AbcPaylog al = new AbcPaylog();
		al.setPayuserId(ap.getPayuserId());
		al.setState(CommonConst.STATENORMAL);
		al.setPayTime(ap.getApplyTime());
		al.setUserId(user.getUserId());
		al.setType(CommonConst.USERGRADETWO);
		al.setStartTime(ap.getApplyTime());
		al.setEndTime(DateUtil.addYears(ap.getApplyTime(), 1));
		paylogService.save(al);
		this.result = "添加成功！";
		return list();
	}

	/**
	 * ajax查询
	 * 
	 * @return
	 */
	public String search() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(SoaUser.class);
		detachedCriteria.addOrder(Order.desc("usersiteId"));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.or(Restrictions.like("username",
					searchKey, MatchMode.ANYWHERE), (Restrictions.like(
					"domain", searchKey, MatchMode.ANYWHERE))));
		List userSites = soaWebserverService
				.findAllByCriteria(detachedCriteria);
		JSONArray jsonArray = JSONArray.fromObject(userSites);
		this.result = jsonArray.toString();
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
