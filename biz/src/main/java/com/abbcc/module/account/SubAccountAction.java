/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SubAccountAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-21           wangjin                      initial
 */

package com.abbcc.module.account;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcUserpriv;
import com.abbcc.models.AbcUserprivilege;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.LogService;
import com.abbcc.service.UserService;
import com.abbcc.service.UserprivService;
import com.abbcc.service.UserprivilegeService;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.StringUtil;

public class SubAccountAction extends BaseAction<AbcUser> {
	protected int pageSize = 10;
	private UserService userService;
	private UserprivService userprivService;
	private EnterpriseService enterpriseService;
	private UserprivilegeService userprivilegeService;
	private String password1;
	private int affectRows;
	public String userprivilegeIds;
	public String unCheckId;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public EnterpriseService getEnterpriseService() {
		return enterpriseService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public void setUserprivilegeService(
			UserprivilegeService userprivilegeService) {
		this.userprivilegeService = userprivilegeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String showSubAccount() {
		// 加入分页
		DetachedCriteria dc = DetachedCriteria
				.forClass(AbcUser.class);
		if(StringUtil.isNotBlank(entity.getUsername()))
			dc.add(Restrictions.like("username","%"+entity.getUsername()+"%"));
		if(StringUtil.isNotBlank(entity.getName()))
			dc.add(Restrictions.like("name","%"+entity.getName()+"%"));
		dc.add(Restrictions.and(Restrictions.eq("type",
				CommonConst.SUBACCOUNT), Restrictions.eq("enterpriseId", getCurrentUser().getEnterpriseId())));
		dc.add(Restrictions.ne("state", "03"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = userService.findPageByCriteria(dc,
				pageSize, startIndex);

		return "showSubAccount";
	}

	public String addSubAccount() {
		if (!(entity.getPassword().equals(password1))) {
			this.addFieldError("password", "您两次输入的密码不一致！");
			return INPUT;
		}
		AbcUser user1 = new AbcUser();
		user1.setUsername(entity.getUsername());
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
		user2.setType(CommonConst.SUBACCOUNT);
		List list2 = userService.findByExample(user2);
		if (list2.size() != 0) {
			this.addFieldError("email", "对不起，您输入的EMAIL已经被使用！");
			return INPUT;
		}
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);

		// AbcEnterprise ent =
		// (AbcEnterprise)getSession().getAttribute(CommonConst.SESSIONENT);

		String password = entity.getPassword();
		password = MD5EncryptUtil.md5Encry(password);
		Calendar cal = Calendar.getInstance();
		entity.setAddTime(cal.getTime());
		entity.setType(CommonConst.SUBACCOUNT);
		entity.setState(CommonConst.STATENORMAL);
		entity.setPassword(password);
		entity.setEnterpriseId(user.getEnterpriseId());
		userService.save(entity);
		logService.savaLog("二级账号",entity.getUsername(), CommonConst.LOGSAVE);
		result = StringUtil.encode(CommonConst.ADDSUCCESS);
		return "returnShowSubAccount";

	}

	public String showUpdateSubAccount() {
		return "showUpdateSubAccount";
	}

	public String updateSubAccount() {
		AbcUser user = (AbcUser) userService.findById(entity.getUserId());
		user.setUserId(entity.getUserId());
		user.setAddress(entity.getAddress());
		user.setCellphone(entity.getCellphone());
		user.setPhone(entity.getPhone());
		user.setName(entity.getName());
		user.setQq(entity.getQq());
		user.setEmail(entity.getEmail());
		userService.saveOrUpdate(user);
		logService.savaLog("二级账号",entity.getUsername(), CommonConst.LOGUPDATE);
		result = StringUtil.encode(CommonConst.EDITSUCCESS);
		return "returnShowSubAccount";
	}

	public String delSubAccount() {
		userService.delete(entity);
		logService.savaLog("二级账号",entity.getUsername(), CommonConst.LOGDEL);
		result = StringUtil.encode(CommonConst.DELSUCCESS);
		return "returnShowSubAccount";
	}

	// 通过JSON来修改用户状态
	public String updateSubState() {
		String userId = entity.getUserId();
		String state = entity.getState();
		affectRows = userService.updateUser(userId, "state", state);
		logService.savaLog("二级账号",entity.getUsername(), CommonConst.LOGUPDATE);
		return "json";
	}

	public int getAffectRows() {
		return affectRows;
	}

	// 显示用户权限设置窗口
	public String popedomShow() {

		// 加入分页
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUserprivilege.class);

		// detachedCriteria.add(Restrictions.ne("state", "01"));
		detachedCriteria.addOrder(Order.asc("moduleId"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = userprivilegeService.findPageByCriteria(detachedCriteria, 50,
				startIndex);
		return "popedomShow";
	}

	// 通过JSON来修改用户权限状态
	public String subAccountPopedomState() {
		String[] privIds = userprivilegeIds.split(",");
		String[] uncheck = unCheckId.split(",");
		// String hql = "update abc_userpriv set state='"+
		// CommonConst.STATEINIT+"' where userprivId in ";
		String userId = entity.getUserId();
		String state = entity.getState();
		for (int i = 0; i < privIds.length; i++) {
			if (!("".equals(privIds[i]))) {
				AbcUserpriv auser = new AbcUserpriv();
				auser.setuserId(userId);
				auser.setuserprivilegeId(privIds[i]);
				List privList = userprivService.findByExample(auser);
				if (privList.size() == 0 || privList == null) {
					auser.setState(CommonConst.STATENORMAL);
					auser.setAdduserId(getCurrentUser().getUserId());
					auser.setuserId(userId);
					userprivService.save(auser);
					affectRows = 1;
				} else {
					AbcUserpriv auser1 = (AbcUserpriv) privList.iterator()
							.next();
					affectRows = userprivService.updateUserPopedom(auser1
							.getuserprivId(), "state", CommonConst.STATENORMAL);
				}
			}
		}
		for (int i = 0; i < uncheck.length; i++) {
			if (!("".equals(uncheck[i]))) {
				AbcUserpriv auser = new AbcUserpriv();
				auser.setuserId(userId);
				auser.setuserprivilegeId(uncheck[i]);
				List privList = userprivService.findByExample(auser);
				if (privList.size() == 0 || privList == null) {
					auser.setState(CommonConst.STATEINIT);
					auser.setAdduserId(getCurrentUser().getUserId());
					auser.setuserId(userId);
					userprivService.save(auser);
					affectRows = 1;
				} else {
					AbcUserpriv auser1 = (AbcUserpriv) privList.iterator()
							.next();
					affectRows = userprivService.updateUserPopedom(auser1
							.getuserprivId(), "state", CommonConst.STATEINIT);
				}
			}
		}
		logService.savaLog("用户","权限", CommonConst.LOGUPDATE);
		return "json";
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setUserprivService(UserprivService userprivService) {
		this.userprivService = userprivService;
	}

}
