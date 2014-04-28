/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DomainbindServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-9           Wangjin                      initial
 */

package com.abbcc.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.MailDAO;
import com.abbcc.dao.MailsendDAO;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcMail;
import com.abbcc.models.AbcMailsend;
import com.abbcc.service.MailsendService;
import com.abbcc.util.StringUtil;

public class MailsendServiceImpl implements MailsendService {

	private MailsendDAO mailsendDAO;
	private MailDAO mailDAO;

	public void setMailsendDAO(MailsendDAO mailsendDAO) {
		this.mailsendDAO = mailsendDAO;
	}

	public void save(AbcMailsend transientInstance) {
		mailsendDAO.save(transientInstance);
	}

	public void delete(AbcMailsend persistentInstance) {

	}

	public AbcMailsend findById(String id) {
		return mailsendDAO.findById(id);
	}

	public List<AbcMailsend> findByExample(AbcMailsend instance) {
		return mailsendDAO.findByExample(instance);

	}

	public List<AbcMailsend> findAll() {
		return mailsendDAO.findAll();

	}

	public void saveOrUpdate(AbcMailsend instance) {
		mailsendDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return mailsendDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return mailsendDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return mailsendDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return mailsendDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return mailsendDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		mailsendDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return mailsendDAO.getCallProcedureResult(procString, params);
	}

	public void sendMail(String sender, String title, String content,
			String receiver, String receiverMail, String mailType,String filePath) {
		AbcMail mail = new AbcMail();
		mail.setAddTime(new Date());
		mail.setAdminId(sender);
		mail.setContent(content);

//		mail.setReceiver(receiverMail);

		mail.setState(CommonConst.STATENORMAL);
		mail.setTitle(title);
		mail.setType(mailType);
		mailDAO.save(mail);
		String[] receivers = StringUtil.splitBySemicolon(receiver);
		String[] receiverMails = StringUtil.splitBySemicolon(receiverMail);
		for (int i = 0; i < receivers.length; i++) {
			String rece = receivers[i];
			String receMail = receiverMails[i];
			AbcMailsend mailsend = new AbcMailsend();
			mailsend.setMailId(mail.getMailId());
			// mailsend.setReadTime(readTime);
			mailsend.setReceEmail(receMail);
			mailsend.setReceiver(rece);
			// mailsend.setReceState(receState);
			mailsend.setSendTime(new Date());
			mailsend.setState(CommonConst.STATENORMAL);
			mailsend.setType(mailType);
			save(mailsend);
		}
	}

	public MailDAO getMailDAO() {
		return mailDAO;
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}

	public MailsendDAO getMailsendDAO() {
		return mailsendDAO;
	}

}
