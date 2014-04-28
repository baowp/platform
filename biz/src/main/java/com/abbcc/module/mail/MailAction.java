/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MailAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-30           wangjin                      initial
*/

package com.abbcc.module.mail;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcMail;
import com.abbcc.models.AbcUser;
import com.abbcc.module.jms.MessageSender;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.LogService;
import com.abbcc.service.MailService;
import com.abbcc.service.MailsendService;
import com.abbcc.service.UserService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings("serial")
public class MailAction extends FileUploadAction<AbcMail> {
	public String userId;
	private UserService userService;
	private MessageSender messageSender;
	//private UserService userService;
	private MailService mailService;
	private MailsendService mailsendService;
	private String cc;
	private String bcc;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String send() throws UnknownHostException{
		String filePath="";
		String fileName="";
		AbcUser user = getCurrentUser();
		if(upload!=null){
			doUpload();
		// 
		InetAddress localhost = InetAddress.getLocalHost();
		String ip = localhost.getHostAddress();
		fileName = getUploadFileName().get(0);
		filePath = uploadFilePath;

		AbcAttachment att = new AbcAttachment();
		att.setBelongType(ModelType.AX);
		att.setBelongId(entity.getMailId());
		att.setUserId(user.getUserId());
		att.setType(CommonConst.MAILATTACHMENT);
		att.setServerPath(uploadFilePath);
		att.setUploadTime(new Date());
		att.setFilename(fileName);
		att.setServerIp(ip);
		attachmentService.save(att);
		}
		//格式：CommonConst.JMS_MAIL,userId[**]接收人[**]抄送[**]密送[**]标题[**]内容[**]复件名字[**]复件路径
		AbcMail mail = new AbcMail();
//		mail.setAdminId(null);
		mail.setContent(entity.getContent());
		mail.setAddTime(Calendar.getInstance().getTime());
		mail.setTitle(entity.getTitle());
		mail.setReceiver(entity.getReceiver());
		mail.setType(CommonConst.MAIL_TYPE_NORMAL_MAIL);
		mail.setState(CommonConst.STATEINIT);
		mailService.save(mail);
		logService.savaLog("邮件",entity.getTitle(), CommonConst.LOGSAVE);
		try {
			messageSender.sendString(CommonConst.JMS_MAIL,entity.getReceiver()+"[**]"+cc+"[**]"+bcc+"[**]"+entity.getTitle()+"[**]"+entity.getContent()+"[**]"+fileName+"[**]"+ConfConst.FILE_UPLOAD_DIR+StringUtil.pathReplace(filePath));
			mail.setState(CommonConst.STATENORMAL);
			mailService.saveOrUpdate(mail);} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sendSuccess";
	}
	
	/**
	 * 用户注册重发邮件
	 * @return
	 */	public String replaceSendMail(){
		AbcUser au = userService.findById(userId);
		if(au!=null){
			userService.sendMail(au.getName(),au.getUsername(), au.getEmail(), au.getVericode());
		}
		return JSON;
	}

	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	public void setMailsendService(MailsendService mailsendService) {
		this.mailsendService = mailsendService;
	}
	public MessageSender getMessageSender() {
		return messageSender;
	}
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}

