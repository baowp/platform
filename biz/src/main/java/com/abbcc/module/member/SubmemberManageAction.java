/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "Submember.java is the copyrighted,
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

package com.abbcc.module.member;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.helper.JavaMailHelper;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.module.jms.MessageSender;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.MailService;
import com.abbcc.service.MailsendService;
import com.abbcc.service.UserService;
import com.abbcc.util.StringUtil;

/**
 * *SubmemberManageAction.java
 */
@SuppressWarnings("serial")
public class SubmemberManageAction extends BaseAction<AbcUser> {
	private String VIEWENTERPRISE = "viewenterprise";
	private String SENDUPGRADEINVITE = "sendupgradeinvite";
	private AbcEnterprise enterprise;
	private EnterpriseService enterpriseService;
	private UserService userService;
	private String entName;
	private String memberKey;
	private PaginationSupport enterprises;
	private String inviteContent;
	private MailService mailService;
	private MailsendService mailsendService;
	private MessageSender messageSender;
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
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

	/**
	 * 查看企业
	 * 
	 * @return
	 */
	public String viewEnterprises() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcEnterprise.class);
		if (StringUtil.isNotBlank(this.entName))
			detachedCriteria.add(Restrictions.like("name", entName,
					MatchMode.ANYWHERE));
		this.startIndex = (this.page - 1) * pageSize;
		detachedCriteria.addOrder(Order.asc("name"));
		this.pageList = enterpriseService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return VIEWENTERPRISE;
	}

	/**
	 * 查看二级会员
	 * 
	 * @return
	 */
	public String viewSubmembers() {
		//enterprise = enterpriseService.findById(entity.getEnterpriseId());
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain",getCurrentAdmin().getDomain()));
		if (StringUtil.isNotBlank(this.memberKey))
			detachedCriteria.add(Restrictions.like("username", memberKey,
					MatchMode.ANYWHERE));
		this.startIndex = (this.page - 1) * pageSize;
		detachedCriteria.add(Restrictions.eq("type", CommonConst.SUBMEMBER));
		detachedCriteria.addOrder(Order.asc("name"));
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return VIEW;
	}

	/**
	 * 查看升级邀请
	 * 
	 * @return
	 */
	public String viewSendUpgradeInvite() {
		this.inviteContent = "亲爱的Abbcc会员" + entity.getName()
				+ "先生/女士：<br/><p></p>";
		return SENDUPGRADEINVITE;

	}

	/**
	 * 发送升级邀请
	 * 
	 * @return
	 */
	public String sendUpgradeInvite() {
		JavaMailHelper jmail = new JavaMailHelper();
		jmail.sendMail(entity.getEmail(), "", "",
				CommonConst.MAILTITLEUPGRADEINVITE, inviteContent, "", "");
		String addAdmin = this.adminId();
		mailsendService.sendMail(addAdmin, CommonConst.MAILTITLEPAYENDREMIND,
				inviteContent, entity.getUserId(), entity.getEmail(),
				CommonConst.MAILUPGRADEINVITE,null);
//		messageSender.sendString(CommonConst.JMS_MAIL,entity.getEmail()+"[**]"+"[**]"+"[**]"+CommonConst.MAILTITLEPAYENDREMIND+"[**]"+inviteContent+"[**]"+"[**]");
		this.savaAdminLog("升级邀请",entity.getUsername(),CommonConst.LOGSAVE);
		result = "邮件发送成功！";
		return INPUT;
	}

	public EnterpriseService getEnterpriseService() {
		return enterpriseService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public PaginationSupport getEnterprises() {
		return enterprises;
	}

	public void setEnterprises(PaginationSupport enterprises) {
		this.enterprises = enterprises;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getMemberKey() {
		return memberKey;
	}

	public void setMemberKey(String memberKey) {
		this.memberKey = memberKey;
	}

	public AbcEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(AbcEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getInviteContent() {
		return inviteContent;
	}

	public void setInviteContent(String inviteContent) {
		this.inviteContent = inviteContent;
	}
}
