/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DistrictManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-16           yixiugg                      initial
 **/

package com.abbcc.module.syscode;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.LogService;
import com.abbcc.service.SyscodeService;

/**
 * *DistrictManageAction.java
 */
@SuppressWarnings("serial")
public class IndustryManageAction extends BaseAction<AbcSyscode> {
	private SyscodeService syscodeService;
	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String view() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcSyscode.class);
		detachedCriteria.add(Restrictions.eq("type",
				CommonConst.SYSCODETYPEINDUSTY));
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		this.startIndex = (this.page - 1) * pageSize;
		pageList = syscodeService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return INPUT;
	}

	public String add() {
		syscodeService.save(entity);
		logService.savaAdminLog("行业",entity.getName(),CommonConst.LOGSAVE);
		result = "添加行业成功！";
		return LIST;
	}

	public String delete() {
		entity.setState(CommonConst.STATEDEL);
		syscodeService.delete(entity);
		logService.savaAdminLog("行业",entity.getName(),CommonConst.LOGSAVE);
		result = "删除行业成功！";
		return LIST;
	}

	public SyscodeService getSyscodeService() {
		return syscodeService;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}
}
