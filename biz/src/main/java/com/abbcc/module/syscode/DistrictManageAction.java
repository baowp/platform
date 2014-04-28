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
import com.abbcc.models.AbcSyscode;
import com.abbcc.service.LogService;
import com.abbcc.service.SyscodeService;

/**
 * *DistrictManageAction.java
 */
@SuppressWarnings("serial")
public class DistrictManageAction extends BaseAction<AbcSyscode> {
	private List<AbcSyscode> provs;
	private SyscodeService syscodeService;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public String view() {
		provs = syscodeService.getSyscodesByType(
				CommonConst.SYSCODETYPEPROVINCE, CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcSyscode.class);
		detachedCriteria.add(Restrictions.eq("type",
				CommonConst.SYSCODETYPEPROVINCE));
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
		result = "添加地区成功！";
		logService.savaAdminLog("地区", entity.getName(), CommonConst.LOGSAVE);
		return LIST;
	}

	public String delete() {
		entity.setState(CommonConst.STATEDEL);
		syscodeService.saveOrUpdate(entity);
		logService.savaAdminLog("地区", entity.getName(), CommonConst.LOGDEL);
		result = "删除地区！";
		return LIST;
	}

	public SyscodeService getSyscodeService() {
		return syscodeService;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}

	public List<AbcSyscode> getProvs() {
		return provs;
	}

	public void setProvs(List<AbcSyscode> provs) {
		this.provs = provs;
	}

}
