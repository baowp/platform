/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "PayendRemind.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-30           yixiugg                      initial
**/

package com.abbcc.module.pay;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.JavaMailHelper;
import com.abbcc.models.AbcUser;
import com.abbcc.module.jms.MessageSender;
import com.abbcc.service.MailService;
import com.abbcc.service.MailsendService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.UserService;


/**
 **PayendRemind.java
 **/
@SuppressWarnings("serial")
public class PayendRemindAction extends BaseAction<AbcUser>{
	public String payuserId;
	private MailService mailService;
	private MailsendService mailsendService;
	private String payendRemind;
	private PayuserService payuserService;
	private UserService userService;
	private MessageSender messageSender;
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPayuserService(PayuserService payuserService) {
		this.payuserService = payuserService;
	}

	public String getPayendRemind() {
		return payendRemind;
	}

	public void setPayendRemind(String payendRemind) {
		this.payendRemind = payendRemind;
	}
	public String viewSendPayendRemind(){
		userService.load(entity, entity.getUserId());
		this.payendRemind="亲爱的Abbcc会员"+entity.getName()+"先生/女士：<br/><p></p>";
		return VIEW;
	}
	public String sendPayendRemind(){
		JavaMailHelper jmail = new JavaMailHelper();
		messageSender.sendString(CommonConst.JMS_MAIL,entity.getEmail()+"[**]"+"[**]"+"[**]"+CommonConst.MAILTITLEPAYENDREMIND+"[**]"+payendRemind+"[**]"+"[**]");
		//jmail.sendMail(entity.getEmail(), "", "", CommonConst.MAILTITLEPAYENDREMIND, payendRemind, "", "");
		String addAdmin = this.adminId();
//		messageSender.sendString(CommonConst.JMS_MAIL,entity.getEmail()+"[**]"+"[**]"+"[**]"+CommonConst.MAILTITLEPAYENDREMIND+"[**]"+payendRemind+"[**]"+"[**]");
		mailsendService.sendMail(addAdmin, CommonConst.MAILTITLEPAYENDREMIND, payendRemind, entity.getUserId(), entity.getEmail(), CommonConst.MAILPAYENDREMIND,null);
		result="邮件发送成功！";
		return INPUT;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public MailsendService getMailsendService() {
		return mailsendService;
	}

	public void setMailsendService(MailsendService mailsendService) {
		this.mailsendService = mailsendService;
	}
}

