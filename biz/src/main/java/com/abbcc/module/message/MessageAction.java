/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MessageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-6           wangjin                      initial
 */

package com.abbcc.module.message;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcMessage;
import com.abbcc.models.AbcUser;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.LogService;
import com.abbcc.service.MessageService;
import com.abbcc.service.UserService;
import com.abbcc.util.StringUtil;

public class MessageAction extends BaseAction<AbcMessage> {
	private MessageService messageService;
	private UserService userService;
	private String username;
	private String pageType;
	private String send;
	public String userType;
	public String allId;
	public String recvMessageId;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public AbcUser getUser() {
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		return user;
	}

	public DetachedCriteria getDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		return detachedCriteria;
	}

	public void getEntryRequest() {
		getRequest().setAttribute("recvCount", this.findRecv().getTotalCount());
		getRequest().setAttribute("sendCount", this.findSend().getTotalCount());
		getRequest().setAttribute("notReadCount",
				this.findNotRead().getTotalCount());
	}

	public void getSysEntryRequest() {
		getRequest().setAttribute("recvCount",
				this.findSysRecv().getTotalCount());
		getRequest().setAttribute("sendCount",
				this.findSysSend().getTotalCount());
		getRequest().setAttribute("notReadCount",
				this.findSysNotRead().getTotalCount());
	}

	public PaginationSupport findRecv() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		if (StringUtil.isNotBlank(entity.getTitle()))
			detachedCriteria.add(Restrictions.like("title",
					"%" + entity.getTitle() + "%"));
		detachedCriteria
				.add(Restrictions.eq("recvUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions
				.ne("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("messageId"));
		// 查询所有接收的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findSend() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria.add(Restrictions.eq("addUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions.eq("sendState",
				CommonConst.STATENORMAL));
		detachedCriteria.add(Restrictions
				.ne("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria.addOrder(Order.desc("messageId"));
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("type", CommonConst.MESSAGESYS),
				Restrictions.ne("sendState", CommonConst.STATEDEL)));
		// 查询所有发送的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (send != null && send.equals("01"))
			getRequest().setAttribute("send", "01");
		return pageList;
	}

	public PaginationSupport findNotRead() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria
				.add(Restrictions.eq("recvUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions
				.ne("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria.add(Restrictions
				.eq("recvState", CommonConst.STATEINIT));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("messageId"));
		// 查询所有未读的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findSysRecv() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria
				.add(Restrictions.eq("recvUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions.eq("type", CommonConst.MESSAGESYS));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("messageId"));
		// 查询所有接收的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findSysSend() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria.add(Restrictions.eq("addUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions.eq("type", CommonConst.MESSAGESYS));
		detachedCriteria.add(Restrictions.eq("sendState",
				CommonConst.STATENORMAL));
		detachedCriteria
				.add(Restrictions.ne("sendState", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("messageId"));
		// 查询所有发送的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findSysNotRead() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria
				.add(Restrictions.eq("recvUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions.eq("type", CommonConst.MESSAGESYS));
		detachedCriteria.add(Restrictions
				.eq("recvState", CommonConst.STATEINIT));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("messageId"));
		// 查询所有未读的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public String entry() {
		this.getEntryRequest();
		this.findRecv();
		getRequest().setAttribute("pageType", "recv");
		return "messageEntry";
	}

	public String sysEntry() {
		this.getSysEntryRequest();
		this.findSysRecv();
		getRequest().setAttribute("pageType", "recv");
		return "messageSysEntry";
	}

	public String showCheck() {
		// 显示自己企业的联系人
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getCurrentUser().getEnterpriseId()), Restrictions.ne(
				"type", CommonConst.CONTACTNAME)));
		detachedCriteria.add(Restrictions.ne("state", CommonConst.STATEDEL));
		pageList = userService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "messageAddUser";
	}

	public String searchRecv() {
		this.getEntryRequest();
		this.findRecv();
		getRequest().setAttribute("pageType", "recv");
		return "messageShow";
	}

	public String searchSend() {
		this.getEntryRequest();
		this.findSend();
		getRequest().setAttribute("pageType", "send");
		return "messageShow";
	}

	public String searchNotRead() {
		this.getEntryRequest();
		this.findNotRead();
		getRequest().setAttribute("pageType", "notRead");
		return "messageShow";
	}

	public String searchSysRecv() {
		this.getSysEntryRequest();
		this.findSysRecv();
		getRequest().setAttribute("pageType", "recv");
		return "messageSysEntry";
	}

	public String searchSysSend() {
		this.getSysEntryRequest();
		this.findSysSend();
		getRequest().setAttribute("pageType", "send");
		return "messageSysEntry";
	}

	public String searchSysNotRead() {
		this.getSysEntryRequest();
		this.findSysNotRead();
		getRequest().setAttribute("pageType", "notRead");
		return "messageSysEntry";
	}

	public String sendPage() {
		return "messageSendPage";

	}

	public String showInfo() {
		entity.setRecvState(CommonConst.MESSAGEISREAD);
		messageService.update(entity);
		getRequest().setAttribute("message",
				messageService.findById(entity.getMessageId()));
		if (StringUtil.isNotBlank(entity.getAddUser())) {
			AbcUser user = new AbcUser();
			baseService.load(user, entity.getAddUser());
			deposit("user", user);
		}
		getRequest().setAttribute("pageType", pageType);
		return "messageInfoPage";
	}

	public String send() {
		String[] userList = username.split(",");
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < userList.length; i++) {
			DetachedCriteria d = this.getDetachedCriteria();
			d.add(Restrictions.and(
					Restrictions.eq("username", userList[i].trim()),
					Restrictions.ne("type", CommonConst.SUBMEMBER)));
			List<AbcUser> u = userService.findAllByCriteria(d);
			if (u.size() == 0) {
				this.addFieldError("username", userList[i] + ",无此用户!");
				return INPUT;
			}
			entity.setRecvUser(u.get(0).getUserId());
			entity.setAddTime(cal.getTime());
			entity.setAddUser(this.getLoginUserId());
			entity.setAddEnt(this.getUser().getEnterpriseId());
			entity.setType(CommonConst.MESSAGEUSER);
			entity.setRecvState(CommonConst.STATEINIT);
			entity.setSendState(CommonConst.STATENORMAL);
			messageService.save(entity);
			logService.savaLog("消息", entity.getTitle(), CommonConst.LOGSAVE);
		}
		return "sendSuccess";

	}

	// 默认是name='name'

	@Recycle(name = "title", resume = "recvState")
	public String recvdel() {
		entity.setRecvState(CommonConst.STATEDEL);
		messageService.update(entity);
		logService.savaLog("消息", entity.getTitle(), CommonConst.LOGDEL);
		result = CommonConst.DELSUCCESS;
		if (userType.equals("visitor") && pageType.equals("recv"))
			return "returnvisitor";
		if (userType.equals("visitor") && pageType.equals("notRead"))
			return "returnvisitorNotRead";

		if (pageType.equals("recv"))
			return "returnMessageShow";
		if (pageType.equals("notRead"))
			return "returnMessageNotReadShow";
		return "returnMessageShow";

	}

	@Recycle(name = "title", resume = "sendState")
	public String senddel() {
		entity.setSendState(CommonConst.STATEDEL);
		messageService.update(entity);
		logService.savaLog("消息", entity.getTitle(), CommonConst.LOGDEL);
		result = CommonConst.DELSUCCESS;
		if (pageType.equals("send"))
			return "returnMessageSendShow";
		return "returnMessageShow";

	}

	public String senddelAll() {
		String[] mId = allId.split(",");
		for (int i = 0; i < mId.length; i++) {
			messageService.delete(messageService.findById(mId[i]));
		}
		if (pageType.equals("send"))
			return "returnMessageSendShow";
		return "returnMessageShow";
	}

	public String recvdelAll() {
		String[] mId = allId.split(",");
		for (int i = 0; i < mId.length; i++) {
			messageService.delete(messageService.findById(mId[i]));
		}
		if (userType.equals("visitor") && pageType.equals("recv"))
			return "returnvisitor";
		if (userType.equals("visitor") && pageType.equals("notRead"))
			return "returnvisitorNotRead";

		if (pageType.equals("recv"))
			return "returnMessageShow";
		if (pageType.equals("notRead"))
			return "returnMessageNotReadShow";
		result = StringUtil.encode(CommonConst.DELSUCCESS);
		return "returnMessageShow";
	}

	// 访问者留言
	public void getVisitorEntryRequest() {
		getRequest().setAttribute("recvCount",
				this.findVisitorRecv().getTotalCount());
		getRequest().setAttribute("notReadCount",
				this.findVisitorNotRead().getTotalCount());
	}

	public PaginationSupport findVisitorRecv() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		if (StringUtil.isNotBlank(entity.getTitle()))
			detachedCriteria.add(Restrictions.eq("title",
					"%" + entity.getTitle() + "%"));
		detachedCriteria.add(Restrictions.eq("recvEnt", this.getCurrentUser()
				.getEnterpriseId()));
		detachedCriteria.add(Restrictions
				.eq("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria.add(Restrictions.eq("recvState",
				CommonConst.STATENORMAL));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		// 查询所有接收的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findVisitorNotRead() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria.add(Restrictions.eq("recvEnt", this.getCurrentUser()
				.getEnterpriseId()));
		detachedCriteria.add(Restrictions
				.eq("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria.add(Restrictions
				.eq("recvState", CommonConst.STATEINIT));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		// 查询所有未读的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public String searchVisitorRecv() {
		this.getVisitorEntryRequest();
		this.findVisitorRecv();
		getRequest().setAttribute("pageType", "recv");
		getRequest().setAttribute("userType", "visitor");
		return "messageSysEntry";
	}

	public String searchVisitorNotRead() {
		this.getVisitorEntryRequest();
		this.findVisitorNotRead();
		getRequest().setAttribute("pageType", "notRead");
		getRequest().setAttribute("userType", "visitor");
		return "messageSysEntry";
	}

	public String visitor() {
		this.getVisitorEntryRequest();
		this.findVisitorRecv();
		getRequest().setAttribute("pageType", "recv");
		getRequest().setAttribute("userType", "visitor");
		return "messageSysEntry";
	}

	// 用户回复留言
	public String recv() {
		String[] userList = username.split(",");
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < userList.length; i++) {
			DetachedCriteria d = this.getDetachedCriteria();
			d.add(Restrictions.and(
					Restrictions.eq("username", userList[i].trim()),
					Restrictions.ne("type", CommonConst.SUBMEMBER)));
			List<AbcUser> u = userService.findAllByCriteria(d);
			entity.setRecvUser(u.get(0).getUserId());
			entity.setAddTime(cal.getTime());
			entity.setAddUser(this.getLoginUserId());
			entity.setAddEnt(this.getUser().getEnterpriseId());
			entity.setType(CommonConst.MESSAGEUSER);
			entity.setRecvState(CommonConst.STATEINIT);
			entity.setSendState(CommonConst.STATENORMAL);
			messageService.save(entity);
			logService.savaLog("消息", entity.getTitle(), CommonConst.LOGSAVE);
		}
		this.result = StringUtil.encode(CommonConst.SENDSUCCESS);
		return JSON;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
