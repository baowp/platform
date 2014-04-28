/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MassageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-22           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.helper.JavaMailHelper;
import com.abbcc.models.AbcMessage;
import com.abbcc.service.MessageService;
import com.abbcc.service.UserService;

@SuppressWarnings("serial")
public class MessageAction extends SiteBaseAction<AbcMessage> {

	private MessageService messageService;
	public String email;
	public String recvUser;
	@Autowired
	private UserService userService;

	protected boolean checkValiCode() {
		return true;
	}

	public void send() throws IOException {
			if(entity.getFromName().trim().isEmpty())
			entity.setFromName("anonymous");
			entity.setType(CommonConst.MESSAGEUSER);
			entity.setAddTime(new Date());
			entity.setRecvState(CommonConst.STATEINIT);
			entity.setSendState(CommonConst.STATENORMAL);
			messageService.save(entity);
			result = "messageSuccess()";
			output(result);
	}

	public void leave() throws IOException {
		if (checkValiCode()) {
			if (entity.getContent().length() > 5)
				entity.setTitle(entity.getContent().substring(0, 5) + "...");
			else
				entity.setTitle(entity.getContent());
			entity.setType(CommonConst.MESSAGEBROWSER);
			entity.setAddTime(new Date());
			entity.setRecvState(CommonConst.STATEINIT);
			entity.setSendState(CommonConst.STATENORMAL);
			entity.setDomain(domain);
			messageService.save(entity);
			new JavaMailHelper().sendMail(userService.findById(recvUser).getEmail(),"","","尊敬的东方五金会员有人在您的网站上留言了，赶快去看看吧",entity.getContent(),"","");
			result = "leaveSuccess()";
		} else {
			result = "leaveFailure()";
		}
		output(result);
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
