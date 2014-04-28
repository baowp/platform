/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MemberManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-29           wangjin                      initial
*/

package com.abbcc.module.subMember;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.LogService;
import com.abbcc.service.UserService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.StringUtil;

public class MemberManageAction extends BaseAction<AbcUser> {
	private UserService userService;
	private EnterpriseService enterpriseService;
	private int affectRows;
	private String password1;
	private String receiver;
	private String frontTime;
	private String backTime;
	private String dealState;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String show(){
		AbcUser user = (AbcUser) getSession().getAttribute(CommonConst.SESSIONUSER);

		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);

		detachedCriteria.add(Restrictions.and(Restrictions.eq("type", CommonConst.SUBMEMBER),
				Restrictions.eq("enterpriseId", user.getEnterpriseId())));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return "subMemberShow";
	}
	public String search(){
		AbcUser user = (AbcUser) getSession().getAttribute(CommonConst.SESSIONUSER);

		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);

		detachedCriteria.add(Restrictions.and(Restrictions.eq("type", CommonConst.SUBMEMBER),
				Restrictions.eq("enterpriseId", user.getEnterpriseId())));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		if (StringUtil.isNotBlank(entity.getUsername()))
			detachedCriteria.add(Restrictions.eq("username",entity.getUsername()));
		if (StringUtil.isNotBlank(entity.getName()))
			detachedCriteria.add(Restrictions.eq("name",entity.getName()));
		if (StringUtil.isNotBlank(frontTime))
			detachedCriteria.add(Property.forName("addTime").ge(
					DateUtil.strToDate(frontTime)));
		if (StringUtil.isNotBlank(backTime))
			detachedCriteria.add(Property.forName("addTime").le(
					DateUtil.strToDate(backTime)));
		if(StringUtil.isNotBlank(dealState))
			detachedCriteria.add(Restrictions.eq("state",dealState.equals("DA")?"01":"02"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return "subMemberShow";

	}
	public String del(){
		userService.delete(entity);
		logService.savaLog("客户",entity.getName(), CommonConst.LOGDEL);
		this.result=StringUtil.encode(CommonConst.DELSUCCESS);
		return "returnSubMemberShow";
	}
	public String updateState(){
		String userId = entity.getUserId();
		String state = entity.getState();
		affectRows = userService.updateUser(userId, "state", state);
		logService.savaLog("客户",entity.getName(), CommonConst.LOGUPDATE);
		return "json";
	}
	public String add(){
		if (!(entity.getPassword().equals(password1))) {
			this.addFieldError("password", "您两次输入的密码不一致！");
			return INPUT;
		}
		AbcUser user1 = new AbcUser();
		user1.setUsername(entity.getUsername());
		user1.setType(CommonConst.SUBMEMBER);
		List list1 = userService.findByExample(user1);
		if (list1.size() != 0) {
			this.addFieldError("username", "对不起，您输入的账号已经被使用！");
			return INPUT;
		}
		if (StringUtil.isChinese(entity.getUsername())) {
			addFieldError("username", "用户名不能为中文！");
			return INPUT;
		}
		AbcUser user2 = new AbcUser();
		user2.setEmail(entity.getEmail());
		user2.setType(CommonConst.SUBMEMBER);
		List list2 = userService.findByExample(user2);
		if (list2.size() != 0) {
			this.addFieldError("email", "对不起，您输入的EMAIL已经被使用！");
			return INPUT;
		}
		AbcUser user = (AbcUser) getSession().getAttribute(CommonConst.SESSIONUSER);

		String password = entity.getPassword();
		password = MD5EncryptUtil.md5Encry(password);
		Calendar cal = Calendar.getInstance();
		entity.setAddTime(cal.getTime());
		entity.setState(CommonConst.STATENORMAL);
		entity.setPassword(password);
		entity.setType(CommonConst.SUBMEMBER);
		entity.setEnterpriseId(user.getEnterpriseId());
		userService.save(entity);
		logService.savaLog("客户",entity.getName(), CommonConst.LOGSAVE);
		result=StringUtil.encode(CommonConst.ADDSUCCESS);
		return "returnSubMemberShow";
	}
	public String sendEmail(){
		AbcUser user = (AbcUser) getSession().getAttribute(CommonConst.SESSIONUSER);

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);

		detachedCriteria.add(Restrictions.and(Restrictions.eq("type", CommonConst.SUBMEMBER),
				Restrictions.eq("enterpriseId", user.getEnterpriseId())));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		List<AbcUser> userList = userService.findAllByCriteria(detachedCriteria);
		String userEmail = "";
		for(AbcUser users:userList){
			userEmail=userEmail+users.getEmail()+";";
		}
		getRequest().setAttribute("emailList",userEmail);
		return "emailShow";
	}
//对单个会员进行Email发送
	public String sendEmail1(){
		getRequest().setAttribute("emailList",receiver);
		return "emailShow";
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}
	public int getAffectRows() {
		return affectRows;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

}

