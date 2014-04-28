/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SendGroupMailAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-7           yixiugg                      initial
 **/

package com.abbcc.module.groupmail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.helper.JavaMailHelper;
import com.abbcc.models.AbcMail;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcUsergroup;
import com.abbcc.module.jms.MessageSender;
import com.abbcc.service.MailService;
import com.abbcc.service.MailsendService;
import com.abbcc.service.UserService;
import com.abbcc.service.UsergroupService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;

/**
 * *SendGroupMailAction.java
 */
@SuppressWarnings("serial")
public class SendGroupMailAction extends BaseAction<AbcUsergroup> {
	private UsergroupService usergroupService;
	private MailsendService mailsendService;
	private MailService mailService;
	private UserService userService;
	private List<AbcUsergroup> usergroups;
	private String title;
	private String content;
	private String[] groupIds;
	private MessageSender messageSender;

	public String[] getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String[] groupIds) {
		this.groupIds = groupIds;
	}

	/**
	 * 查看邮件内容
	 */
	public String add() {
		String addAdmin = this.adminId();
		AbcUsergroup group = new AbcUsergroup();
		group.setState(CommonConst.STATENORMAL);
		group.setAddAdmin(addAdmin);
		usergroups = usergroupService.findByExample(group);
		this.getSession().setAttribute("usergroups", usergroups);
		return INPUT;
	}

	/**
	 * 发送邮件
	 * 
	 * @return
	 */
	public String send() {
		String userIds = "";
		String userEmails = "";
		List<AbcUser> allUsers = new ArrayList<AbcUser>();
		for (String groupId : groupIds) {
			List<AbcUser> users = userService.getUsersByGroupId(groupId);
			allUsers.addAll(users);
		}
		for (AbcUser user : allUsers) {
			if (StringUtil.isBlank(userIds)) {
				userIds = user.getUserId();
				userEmails = user.getEmail();
			} else {
				if (userIds.indexOf(user.getUserId()) == -1) {
					userIds += ";" + user.getUserId();
					userEmails += ";" + user.getEmail();
				}
			}
		}
		AbcMail mail = new AbcMail();
		mail.setAdminId(this.getCurrentAdmin().getAdminId());
		mail.setContent(content);
		mail.setAddTime(Calendar.getInstance().getTime());
		mail.setTitle(title);
		mail.setReceiver(userEmails);
		mail.setTitle(title);
		mail.setType(CommonConst.MAIL_TYPE_ADMIN_GROUP_MAIL);
		mail.setState(CommonConst.STATEINIT);
		mailService.save(mail);
		this.savaAdminLog("邮件",mail.getTitle(),CommonConst.LOGSAVE);
		this.setTempAttachment(mail.getMailId(),ModelType.AX);
		try {
			//格式：CommonConst.JMS_MAIL,userId[**]接收人[**]抄送[**]密送[**]标题[**]内容[**]复件名字[**]复件路径
			messageSender.sendString(CommonConst.JMS_MAIL, "" + "[**]"
					+ userEmails + "[**]" + "" + "[**]" + "" + "[**]" + title
					+ "[**]" + content + "[**]" + "" + "[**]" + "");
			mail.setState(CommonConst.STATENORMAL);
			mailService.saveOrUpdate(mail);
			result = "邮件发送成功！";
		} catch (Exception e) {
			result="邮件发送失败，原因："+e.toString();
			// TODO: handle exception
		}
		//		JavaMailHelper jmail = new JavaMailHelper();
//		jmail.sendMail(userEmails, "", "", title, content, "", "");
//		String addAdmin = this.adminId();
//		mailsendService.sendMail(addAdmin, title, content, userIds, userEmails,
//				CommonConst.MAILGROUPMAIL, null);
		return INPUT;
	}

	public List<AbcUsergroup> getUsergroups() {
		return usergroups;
	}

	public void setUsergroups(List<AbcUsergroup> usergroups) {
		this.usergroups = usergroups;
	}

	public UsergroupService getUsergroupService() {
		return usergroupService;
	}

	public void setUsergroupService(UsergroupService usergroupService) {
		this.usergroupService = usergroupService;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MailsendService getMailsendService() {
		return mailsendService;
	}

	public void setMailsendService(MailsendService mailsendService) {
		this.mailsendService = mailsendService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

}
