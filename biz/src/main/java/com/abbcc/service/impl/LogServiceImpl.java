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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.dao.LogDAO;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcLog;
import com.abbcc.models.AbcStat;
import com.abbcc.models.AbcUser;
import com.abbcc.service.LogService;
import com.abbcc.service.StatService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.ipUtil.IPSeeker;

public class LogServiceImpl extends BaseServiceImpl implements LogService {

	private LogDAO logDAO;

	public void setLogDAO(LogDAO logDAO) {
		this.logDAO = logDAO;
	}

	@Override
	public void savaLog(String name, String desc, String logType) {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest().getSession();
		AbcUser user = (AbcUser) session.getAttribute(CommonConst.SESSIONUSER);
		AbcAdmin admin = (AbcAdmin) session
				.getAttribute(CommonConst.SESSIONADMIN);
		     String ip = ServletActionContext.getRequest().getHeader("X-Real-IP");  
		//String ip = ServletActionContext.getRequest().getRemoteAddr();
		AbcLog al = new AbcLog();
		al.setAddTime(new Date());
		al.setIp(ip);
		if (user != null) {
			String loginUserId = (String) session
					.getAttribute(CommonConst.SESSIONLOGINUSERID);
			al.setIsadmin(CommonConst.LOGUSER);
			al.setUserId(loginUserId);
			al.setEnterpriseId(user.getEnterpriseId());
		} else if (admin != null) {
			al.setIsadmin(CommonConst.LOGADMIN);
			al.setUserId(admin.getAdminId());
		}
		al.setName(name);
		al.setType(logType);
		al.setLdesc(desc);
		al.setLocation(IPSeeker.getInstance().getAddress(ip));
		al.setDomain(ServletActionContext.getRequest().getServerName());
		logDAO.save(al);
		int loginNumber = 0,jobnumber=0,productnumber=0,certnumber=0,newsnumber=0,supplynumber=0;
		if (name.equals("产品")&&logType.equals(CommonConst.LOGSAVE))
			productnumber = 1;
		if (name.equals("供求")&&logType.equals(CommonConst.LOGSAVE))
			supplynumber = 1;
		if (name.equals("新闻")&&logType.equals(CommonConst.LOGSAVE))
			newsnumber = 1;
		if (name.equals("招聘")&&logType.equals(CommonConst.LOGSAVE))
			jobnumber = 1;
		if (name.equals("证书")&&logType.equals(CommonConst.LOGSAVE))
			certnumber = 1;
		if (logType.equals(CommonConst.LOGLOGIN))
			loginNumber = 1;
		if(!(loginNumber == 0&&jobnumber==0&&productnumber==0&&certnumber==0&&newsnumber==0&&supplynumber==0)){
			StatService statService = (StatService) BeansFactory
					.get("statService");
			DetachedCriteria detachedCriteria = DetachedCriteria
			.forClass(AbcStat.class);
			detachedCriteria.add(Restrictions.eq("enterpriseId", al.getEnterpriseId()));
			List astat=statService.findAllByCriteria(detachedCriteria);
			AbcStat as = new AbcStat();
			if (astat.size() != 0) {
				as = (AbcStat)astat.get(0);
				as.setCertfrequency(as.getCertfrequency()+certnumber);
				as.setJobfrequency(as.getJobfrequency()+jobnumber);
				as.setLoginfrequency(as.getLoginfrequency()+loginNumber);
				as.setNewsfrequency(as.getNewsfrequency()+newsnumber);
				as.setProductfrequency(as.getProductfrequency()+productnumber);
				as.setSupplyfrequency(as.getSupplyfrequency()+supplynumber);
				as.setLastAddTime(new Date());
			} else {
				as.setEnterpriseId(al.getEnterpriseId());
				as.setCertfrequency(certnumber);
				as.setJobfrequency(jobnumber);
				as.setLoginfrequency(loginNumber);
				as.setNewsfrequency(newsnumber);
				as.setProductfrequency(productnumber);
				as.setSupplyfrequency(supplynumber);
				as.setLastAddTime(new Date());
			}
			statService.saveOrUpdate(as);
		}
		
	}

	public void savaAdminLog(String name, String desc, String logType) {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest().getSession();
		AbcAdmin admin = (AbcAdmin) session
				.getAttribute(CommonConst.SESSIONADMIN);
		//String ip = ServletActionContext.getRequest().getRemoteAddr();
		String ip = ServletActionContext.getRequest().getHeader("X-Real-IP");
		AbcLog al = new AbcLog();
		al.setAddTime(new Date());
		if (admin != null) {
			al.setIsadmin(CommonConst.LOGADMIN);
			al.setUserId(admin.getAdminId());
		}
		al.setName(name);
		al.setType(logType);
		al.setLdesc(desc);
		al.setIp(ip);
		al.setLocation(IPSeeker.getInstance().getAddress(ip));
		al.setDomain(ServletActionContext.getRequest().getServerName());
		logDAO.save(al);
	}

}
