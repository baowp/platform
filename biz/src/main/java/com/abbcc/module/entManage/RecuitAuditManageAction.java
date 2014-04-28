/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "RecuitAuditManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-9           yixiugg                      initial
 **/

package com.abbcc.module.entManage;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcJob;
import com.abbcc.service.JobService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;

/**
 ** RecuitAuditManageAction.java
 **/
@SuppressWarnings("serial")
public class RecuitAuditManageAction extends BaseAction<AbcJob> {
	private JobService jobService;
	private String state = CommonConst.STATEINIT;
	private String[] jobIds;

	public String view() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcJob.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain",getCurrentAdmin().getDomain()));
		}
		detachedCriteria.add(Restrictions.eq("state", state));
		detachedCriteria.addOrder(Order.desc("addTime"));
		entity.setState(state);
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = jobService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return INPUT;
	}

	public String edit() {
		for (String jobId : jobIds) {
			String[] jobAndState = StringUtil.splitBySemicolon(jobId);
			AbcJob job = jobService.findById(jobAndState[0]);
			job.setState(jobAndState[1]);
			jobService.saveOrUpdate(job);
			this.savaAdminLog("招聘审核", job.getTitle(), CommonConst.LOGUPDATE);
		}
		result = "审核成功！";
		return VIEW;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public String[] getJobIds() {
		return jobIds;
	}

	public void setJobIds(String[] jobIds) {
		this.jobIds = jobIds;
	}

}
