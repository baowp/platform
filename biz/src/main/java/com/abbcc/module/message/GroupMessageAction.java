/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "GroupMessageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-15           yixiugg                      initial
 **/

package com.abbcc.module.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.interceptor.ApplicationAware;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcMessage;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcUsergroup;
import com.abbcc.service.MessageService;
import com.abbcc.service.UserService;
import com.abbcc.service.UsergroupService;
import com.abbcc.util.StringUtil;

/**
 * *GroupMessageAction.java
 */
@SuppressWarnings("serial")
public class GroupMessageAction extends BaseAction<AbcMessage> {
	private MessageService messageService;
	private List<AbcUsergroup> usergroups;
	private UsergroupService usergroupService;
	private String isAllUser="0";
	private String recvName;
	private String recvId;
	private String[] groupIds;
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UsergroupService getUsergroupService() {
		return usergroupService;
	}

	public void setUsergroupService(UsergroupService usergroupService) {
		this.usergroupService = usergroupService;
	}

	public List<AbcUsergroup> getUsergroups() {
		return usergroups;
	}

	public void setUsergroups(List<AbcUsergroup> usergroups) {
		this.usergroups = usergroups;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	public String viewAdd() {
		String addAdmin = this.adminId();
		AbcUsergroup group = new AbcUsergroup();
		group.setState(CommonConst.STATENORMAL);
		group.setAddAdmin(addAdmin);
		usergroups = usergroupService.findByExample(group);
		this.getSession().setAttribute("usergroups", usergroups);
		return INPUT;
	}

	/**
	 * 发送留言
	 * 
	 * @return
	 */
	public String send() {
		List<AbcUser> allUsers = new ArrayList<AbcUser>();
		// 所有成员
		if (isAllUser.equals("0")) {
			allUsers = userService.findAll();
			for (AbcUser user : allUsers) {
				entity.setRecvUser(user.getUserId());
				entity.setAddTime(new Date());
				entity.setAddUser(this.adminId());
				entity.setType(CommonConst.MESSAGESYS);
				entity.setRecvState(CommonConst.STATEINIT);
				entity.setSendState(CommonConst.STATENORMAL);
				messageService.save(entity);
				this.savaAdminLog("信息",entity.getTitle(),CommonConst.LOGSAVE);
			}
		}
		// 单个用户
		if (isAllUser.equals("1")) {
			if (StringUtil.isNotBlank(recvId)) {
				entity.setRecvUser(recvId);
				entity.setAddTime(new Date());
				entity.setAddUser(this.adminId());
				entity.setType(CommonConst.MESSAGESYS);
				entity.setRecvState(CommonConst.STATEINIT);
				entity.setSendState(CommonConst.STATENORMAL);
				messageService.save(entity);
				this.savaAdminLog("信息",entity.getTitle(),CommonConst.LOGSAVE);

			}
		}
		// 用户群组
		if (isAllUser.equals("2")) {
			for (String groupId : groupIds) {
				List<AbcUser> users = userService.getUsersByGroupId(groupId);
				allUsers.addAll(users);
			}
			for (AbcUser user : allUsers) {
				entity.setRecvUser(user.getUserId());
				entity.setAddTime(new Date());
				entity.setAddUser(this.adminId());
				entity.setType(CommonConst.MESSAGESYS);
				entity.setRecvState(CommonConst.STATEINIT);
				entity.setSendState(CommonConst.STATENORMAL);
				messageService.save(entity);
				this.savaAdminLog("信息",user.getUsername(),CommonConst.LOGSAVE);
			}
		}
		this.getApplication().setAttribute("name","aaaaaaaaaaaaaaaaaaa"); 

		result = "消息发送成功！";
		return INPUT;
	}

	public String getIsAllUser() {
		return isAllUser;
	}

	public void setIsAllUser(String isAllUser) {
		this.isAllUser = isAllUser;
	}

	public String getRecvId() {
		return recvId;
	}

	public void setRecvId(String recvId) {
		this.recvId = recvId;
	}

	public String[] getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String[] groupIds) {
		this.groupIds = groupIds;
	}

	public String getRecvName() {
		return recvName;
	}

	public void setRecvName(String recvName) {
		this.recvName = recvName;
	}
}
