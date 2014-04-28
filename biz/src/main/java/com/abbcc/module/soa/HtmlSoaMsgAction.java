/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserSiteManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-6           RayStone                    initial
 **/

package com.abbcc.module.soa;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Calendar;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcMessage;
import com.abbcc.models.AbcUser;
import com.abbcc.module.usersite.SiteBaseAction;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.MessageService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;

/**
 * HtmlSoaMsgAction
 * @author RayStone
 *
 */
@SuppressWarnings("serial")
public class HtmlSoaMsgAction extends SiteBaseAction<AbcMessage>{
	private UserService userService;
	private MessageService messageService;
	private SoaUserService soaUserService;
	private EnterpriseService enterpriseService;
	public String enterpriseId;
	public String msgContent;

	
	/**
	 * 解码中文参数
	 * @param encodeStr
	 * @return
	 */
	private String decodeStr(String encodeStr) throws IOException{
		return URLDecoder.decode(URLDecoder.decode(encodeStr, "utf-8"), "utf-8");
	}
	
	/**
	 * 产品留言
	 * @return
	 * @throws IOException
	 */
	public void doMsg() throws IOException{
		try {
			AbcUser urlUser = userService.getUserByDomain(userDomain);
			if(urlUser==null){
				output(f+"('"+CommonConst.FAILURE+"', '不存在该公司！');");
			}
			else {
				
				entity.setAddEnt(decodeStr(entity.getAddEnt()));
				entity.setAddUser(decodeStr(entity.getAddUser()));
				entity.setContent(decodeStr(entity.getContent()));
				entity.setTitle(decodeStr(entity.getTitle()));
				entity.setRecvUser(urlUser.getUserId());
				entity.setRecvEnt(urlUser.getEnterpriseId());
				entity.setType(CommonConst.MESSAGEBROWSER);
				entity.setAddTime(Calendar.getInstance().getTime());
				entity.setRecvState(CommonConst.STATEINIT);
				entity.setSendState(CommonConst.STATENORMAL);
				messageService.save(entity);
				output(f+"('"+CommonConst.SUCCESS+"');");
			}
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
	}
	public void domessage() throws IOException{
		try {
			AbcEnterprise urlUser = enterpriseService.findById(enterpriseId);
			if(urlUser==null){
				output(f+"('"+CommonConst.FAILURE+"', '不存在该公司！');");
			}
			else {
				
				entity.setContent(msgContent);
				entity.setTitle(decodeStr(entity.getTitle()));
				entity.setRecvUser(urlUser.getUserId());
				entity.setRecvEnt(urlUser.getEnterpriseId());
				entity.setType(CommonConst.MESSAGEBROWSER);
				entity.setAddTime(Calendar.getInstance().getTime());
				entity.setRecvState(CommonConst.STATEINIT);
				entity.setSendState(CommonConst.STATENORMAL);
				messageService.save(entity);
				output(f+"('留言成功');");
			}
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public SoaUserService getSoaUserService() {
		return soaUserService;
	}
	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}
	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}
	
}
