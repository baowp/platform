package com.abbcc.module.message;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcMessage;
import com.abbcc.service.MessageService;
import com.abbcc.util.StringUtil;

public class JqueryMessageAction extends BaseAction<AbcMessage>{
	private MessageService messageService;
	public String SecurityCode;
	public String content;
	public String phone;
	public String email;
	public String phonehide;
	public String contenthide;
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public String list(){
		entity.setType(CommonConst.MESSAGEBROWSER);
		entity.setDomain(domain);
		DetachedCriteria dc = DetachedCriteria
		.forClass(entity.getClass());
		dc.add(Example.create(entity));
		dc.addOrder(Order.desc("addTime"));
		this.pageList = messageService.findPageByCriteria(dc,
				pageSize, startIndex);
		System.out.println("pageSize--------------------------------"+pageSize);
		System.out.println("startIndex--------------------------------"+startIndex);
		return LIST;
	}
	public String save(){
		HttpSession session = this.getSession();
		if (!SecurityCode.equalsIgnoreCase((String) session.getAttribute(CommonConst.VERICODE))) {
			result= "图片验证码错误！";
			return JSON;
		}
		if(!phone.matches("^(13|15|18)\\d{9}$")){
			result= "手机号码不对！";
			return JSON;
		}
		entity.setType(CommonConst.MESSAGEBROWSER);
		entity.setAddTime(new Date());
		entity.setRecvState(CommonConst.STATEINIT);
		entity.setTitle(phone);    //表里无EMAIL,PHONE字段，暂存
		entity.setAddUser(email);  //表里无EMAIL,PHONE字段，暂存
		entity.setDomain(domain);
		messageService.save(entity);
		result="留言成功";
		return JSON;
	}
	public void saveBd(){
		entity.setType(CommonConst.MESSAGEBROWSER);
		entity.setAddTime(new Date());
		entity.setRecvState(CommonConst.STATEINIT);
		entity.setTitle(phonehide);    //表里无EMAIL,PHONE字段，暂存
		entity.setDomain(domain);
		entity.setContent(contenthide);
		messageService.save(entity);
	}
	public String helpMessage(){
		entity.setType(CommonConst.MESSAGEBROWSER);
		entity.setAddTime(new Date());
		entity.setRecvState(CommonConst.STATEINIT);
		entity.setContent(entity.getTitle());
		entity.setDomain(domain);
		messageService.save(entity);
		result="提交成功!";
		return JSON;
	}
	public String remove(){
		messageService.delete(entity);
		return SUCCESS;
	}
	}
