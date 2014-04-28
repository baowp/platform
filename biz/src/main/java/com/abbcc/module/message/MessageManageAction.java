/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MessageManageAction.java is the copyrighted,
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

package com.abbcc.module.message;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcMessage;
import com.abbcc.service.MessageService;
import com.abbcc.util.StringUtil;

/**
 * *MessageManageAction.java
 */
@SuppressWarnings("serial")
public class MessageManageAction extends BaseAction<AbcMessage> {
	private MessageService messageService;
	private String sendId;
	private String sendName;
	private String recvId;
	private String recvName;
	private String entId;
	private String entName;
	private String sendState;
	private String recvState;
	private String searchKey;
	private String[] messageIds;
	private String LISTMESSAGE = "listmessage";

	public String[] getMessageIds() {
		return messageIds;
	}

	public void setMessageIds(String[] messageIds) {
		this.messageIds = messageIds;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getRecvId() {
		return recvId;
	}

	public void setRecvId(String recvId) {
		this.recvId = recvId;
	}

	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	public String getRecvState() {
		return recvState;
	}

	public void setRecvState(String recvState) {
		this.recvState = recvState;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * 
	 */
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		if (StringUtil.isNotBlank(sendId))
			detachedCriteria.add(Restrictions.eq("addUser", sendId));
		if (StringUtil.isNotBlank(recvId))
			detachedCriteria.add(Restrictions.eq("recvUser", recvId));
		if (StringUtil.isNotBlank(sendState))
			detachedCriteria.add(Restrictions.eq("sendState", sendState));
		if (StringUtil.isNotBlank(recvState))
			detachedCriteria.add(Restrictions.eq("recvState", recvState));
		if (StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("recvEnt", entId));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.or(Restrictions.like("title",
					searchKey, MatchMode.ANYWHERE), Restrictions.like(
					"content", searchKey, MatchMode.ANYWHERE)));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.eq("domain",domain));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return LIST;
	}
	/**
	 * 显示系统已发留言
	 * @return
	 */
	public String send(){
		DetachedCriteria dc = DetachedCriteria
		.forClass(entity.getClass());
		dc.add(Restrictions.eq("type",CommonConst.MESSAGESYS));
		pageList = baseService.findPageByCriteria(dc,pageSize,startIndex);
		return "send";
	}
	
	public String browser(){
		entity.setType(CommonConst.MESSAGEBROWSER);
		entity.setDomain(domain);
		DetachedCriteria dc = DetachedCriteria
		.forClass(entity.getClass());
		dc.add(Example.create(entity));
		dc.addOrder(Order.desc("addTime"));
		this.pageList = messageService.findPageByCriteria(dc,
				100, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return LIST;
	}

	/**
	 * 修改状态
	 */
	public String editState() {
		for (String messageId : messageIds) {
			String[] messageIdAndState = StringUtil.splitBySemicolon(messageId);
			AbcMessage message = messageService.findById(messageIdAndState[0]);
			message.setSendState(messageIdAndState[1]);
			message.setRecvState(messageIdAndState[1]);
			messageService.saveOrUpdate(message);
		}
		result = "操作成功！";
		return LISTMESSAGE;
	}
	/**
	 * 查看message
	 * @return
	 */
	public String view(){
		return DETAIL;
	}
	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getRecvName() {
		return recvName;
	}

	public void setRecvName(String recvName) {
		this.recvName = recvName;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}
}
