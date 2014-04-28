/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "PasswordUpdateAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-17           Wangjin                      initial
 */

package com.abbcc.module.account;

import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcMail;
import com.abbcc.models.AbcUser;
import com.abbcc.module.jms.MessageSender;
import com.abbcc.service.LogService;
import com.abbcc.service.MailService;
import com.abbcc.service.UserService;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
public class PasswordAction extends BaseAction<AbcUser> {
	private UserService userService;
	private String password1;
	private String password2;
	private String pwdAnswer1;
	private String pwdAnswer2;
	private String newPwdQuestion;
	private String pageType;
	private MessageSender messageSender;
	private MailService mailService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getNewPwdQuestion() {
		return newPwdQuestion;
	}

	public void setNewPwdQuestion(String newPwdQuestion) {
		this.newPwdQuestion = newPwdQuestion;
	}

	public String getPwdAnswer1() {
		return pwdAnswer1;
	}

	public void setPwdAnswer1(String pwdAnswer1) {
		this.pwdAnswer1 = pwdAnswer1;
	}

	public String getPwdAnswer2() {
		return pwdAnswer2;
	}

	public void setPwdAnswer2(String pwdAnswer2) {
		this.pwdAnswer2 = pwdAnswer2;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String showUpdatePassword() {

		return "showUpdatePassword";

	}

	public String udatePassword() {
		if (entity.getPassword().equals("")) {
			this.addFieldError("password", "密码不能为空！");
			return INPUT;
		}
		if (password1.equals("")) {
			this.addFieldError("password1", "密码不能为空！");
			return INPUT;
		}
		if (password2.equals("")) {
			this.addFieldError("password2", "密码不能为空！");
			return INPUT;
		}
		if (password2.length()<6) {
			this.addFieldError("password2", "密码不能不能小于6位！");
			return INPUT;
		}
		AbcUser user=null;
		AbcUser isSubAccountUser = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONSUBACCOUNT);
		if (isSubAccountUser == null) {
			user = (AbcUser) getSession().getAttribute(CommonConst.SESSIONUSER);
		} else {
			user = isSubAccountUser;

		}
		String password = entity.getPassword();
		password = MD5EncryptUtil.md5Encry(password);
		if (password.equals(user.getPassword())) {
			if (!(password1.equals(password2))) {
				this.addFieldError("password1", "两次密码输入不一致！");
				return INPUT;
			}
			user.setUserId(user.getUserId());
			user.setPassword(MD5EncryptUtil.md5Encry(password1));
			userService.saveOrUpdate(user);
			logService.savaLog("密码",entity.getName(), CommonConst.LOGUPDATE);

		} else {
			this.addFieldError("password", "旧密码输入错误，请重新输入！");
			return INPUT;
		}
		result = CommonConst.EDITSUCCESS;
		return "udatePassword";
	}

	public String showPasswordProtection() {
		userService.load(entity,this.getCurrentUser().getUserId());
		return "showPasswordProtection";

	}

	public String savePasswordProtection() {
		if (StringUtil.isBlank(newPwdQuestion)) {
			this.addFieldError("newPwdQuestion", "密码保护问题不能为空！");
			return INPUT;
		}
		if (StringUtil.isBlank(pwdAnswer1)) {
			this.addFieldError("pwdAnswer1", "密码保护答案不能为空！");
			return INPUT;
		}
		if (!pwdAnswer2.equals(pwdAnswer1)) {
			this.addFieldError("pwdAnswer2", "两次的输入不一样！");
			return INPUT;
		}
		entity.setPwdQuestion(newPwdQuestion);
		entity.setPwdAnswer(pwdAnswer1);
		userService.saveOrUpdate(entity);
		logService.savaLog("密码保护","", CommonConst.LOGSAVE);
		result = CommonConst.ADDSUCCESS;
		return "savePasswordProtection";

	}

	public String updatePasswordProtection() {

		AbcUser user = (AbcUser) getSession().getAttribute(CommonConst.SESSIONUSER);

		if (!(pwdAnswer1.equals(pwdAnswer2))) {

			this.addFieldError("pwdAnswer2", "两次输入不一样！");
			return INPUT;
		}
		if (!(entity.getPwdAnswer().equals(user.getPwdAnswer()))) {
			this.addFieldError("pwdAnswer", "原密码答案错误！");
			return INPUT;
		}
		user.setPwdQuestion(getNewPwdQuestion());
		user.setPwdAnswer(this.getPwdAnswer1());
		user.setUserId(user.getUserId());
		userService.saveOrUpdate(user);
		logService.savaLog("密码保护","", CommonConst.LOGUPDATE);
		result = CommonConst.EDITSUCCESS;
		return "updatePasswordProtection";

	}

	/**
	 * 根据用户名或邮箱找回用户名或密码
	 * 
	 * @return
	 */
	public String showForgetUsernameOrPassword() {

		AbcUser user = new AbcUser();
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcUser.class);
		if (pageType.equals("1")) {
			if(entity.getEmail()==null){
				this.addFieldError("email", "邮箱不能为空！");
				return INPUT;
			}
			user.setEmail(entity.getEmail());
			detachedCriteria.add(Example.create(user));
			detachedCriteria.add(Restrictions.ne("type", CommonConst.SUBMEMBER));
			List list = userService.findAllByCriteria(detachedCriteria);
			if (list.size() == 0) {
				this.addFieldError("email", "无此邮箱，请检查后重新输入！");
				return INPUT;
			}
			AbcUser userRequest = (AbcUser) list.iterator().next();

			getRequest().setAttribute("userRequest", userRequest);
			return "showForgetUsernameOrPassword";

		} else if (pageType.equals("2")) {
			user.setUsername(entity.getUsername());
			detachedCriteria.add(Example.create(user));
			detachedCriteria.add(Restrictions.ne("type", CommonConst.SUBMEMBER));
			List list = userService.findAllByCriteria(detachedCriteria);
			if (list.size() == 0) {
				this.addFieldError("username", "无此用户，请检查后重新输入！");
				return INPUT;
			}
			AbcUser userRequest = (AbcUser) list.iterator().next();

			getRequest().setAttribute("userRequest", userRequest);
			return "showForgetUsernameOrPassword";
		} else {
			return ERROR;
		}
	}

	/**
	 * 找回用户名或密码
	 * 
	 * @return
	 */
	public String forgetUsernameOrPassword() {
		@SuppressWarnings("unused")
		AbcUser user = new AbcUser();
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcUser.class);
		if (pageType.equals("1")) {//判断页面传过来的是找回用户名时
			user.setPwdAnswer(entity.getPwdAnswer());
			user.setEmail(entity.getEmail());
			detachedCriteria.add(Example.create(user));
			detachedCriteria.add(Restrictions.ne("type", CommonConst.SUBMEMBER));
			List list = userService.findAllByCriteria(detachedCriteria);
			if (list.size() == 0|| list==null) {
				this.addFieldError("pwdAnswer", "对不起您输入的密码问题答案不对！");
				user.setPwdQuestion(entity.getPwdQuestion());
				getRequest().setAttribute("userRequest", user);
				return INPUT;
			}
			AbcUser userRequest = (AbcUser) list.iterator().next();
			String content = "尊敬的" + userRequest.getName() + ",您的登陆名是:"
					+ userRequest.getUsername();
			String title = "尊敬的ABBCC会员，您的会员名已经找回！";
			AbcMail mail = new AbcMail();
			mail.setContent(content);
			mail.setAddTime(Calendar.getInstance().getTime());
			mail.setTitle(title);
			mail.setReceiver(userRequest.getEmail());
			mail.setTitle(title);
			mail.setType(CommonConst.MAIL_TYPE_USERNAME_PASSWORD);
			mail.setState(CommonConst.STATEINIT);
			mailService.save(mail);
			//格式：CommonConst.JMS_MAIL,userId[**]接收人[**]抄送[**]密送[**]标题[**]内容[**]复件名字[**]复件路径
			try {
				messageSender.sendString(CommonConst.JMS_MAIL,userRequest.getEmail()+"[**]"+"[**]"+"[**]"+title+"[**]"+content+"[**]"+"[**]");
				mailService.saveOrUpdate(mail);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result="邮件发送失败，原因："+e.toString();
			}
			return "forgetUsernameOrPasswordSuccess";

		} else if (pageType.equals("2")) {		//判断页面传过来的是找回密码时
			user.setUsername(entity.getUsername());
			user.setPwdAnswer(entity.getPwdAnswer());
			detachedCriteria.add(Example.create(user));
			detachedCriteria.add(Restrictions.ne("type", CommonConst.SUBMEMBER));
			List list = userService.findAllByCriteria(detachedCriteria);
			if (list.size() == 0||list == null) {
				this.addFieldError("pwdAnswer", "对不起您输入的密码问题答案不对！");
				return INPUT;
			}
			AbcUser userRequest = (AbcUser) list.iterator().next();
			StringUtil su = new StringUtil();
			String randomString32 = su.getRndString(32);
			userRequest.setUserId(userRequest.getUserId());
			userRequest.setZipcode(randomString32);
			userService.saveOrUpdate(userRequest);
			String content = "尊敬的"
					+ userRequest.getName()
					+ ",您的登陆名是:"
					+ userRequest.getUsername()
					+ "<br/>,请点击这里<a href='"
					+ CommonConst.SITEINFO.url
					+ "/user/account/password/forgetPassword?username="
					+ userRequest.getUsername() + "&&zipcode=" + randomString32
					+ "'>修改你的新密码</a>";
			String title = "尊敬的ABBCC会员，您的密码已经找回，请尽快修改！";
			//格式：CommonConst.JMS_MAIL,userId[**]接收人[**]抄送[**]密送[**]标题[**]内容[**]复件名字[**]复件路径
			try {
				messageSender.sendString(CommonConst.JMS_MAIL,userRequest.getEmail()+"[**]"+"[**]"+"[**]"+title+"[**]"+content+"[**]"+"[**]");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result="邮件发送失败，原因："+e.toString();
			}
			return "forgetUsernameOrPasswordSuccess";

		} else {
			return ERROR;
		}

	}

	public String forgetPassword() {
		AbcUser user = new AbcUser();
		user.setUsername(entity.getUsername());
		user.setZipcode(entity.getZipcode());
		List list = userService.findByExample(user);
		if (list.size() != 0) {
			AbcUser userList = (AbcUser) list.iterator().next();
			getRequest().setAttribute("userList", userList);
			return "showForgotPassword";

		}

		return ERROR;
	}

	public String updatePasswordForId() {
		if (password1.equals(password2)) {
			AbcUser user = userService.findById(entity.getUserId());
			if (user != null) {
				String password = MD5EncryptUtil.md5Encry(password1);
				user.setPassword(password);
				user.setUserId(entity.getUserId());
				userService.saveOrUpdate(user);

			} else {
				return ERROR;
			}

		} else {
			AbcUser user = userService.findById(entity.getUserId());
			getRequest().setAttribute("userList", user);
			this.addFieldError("password2", "两次输入的不一样！请重新输入");
			return INPUT;
		}
		return SUCCESS;

	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

}
