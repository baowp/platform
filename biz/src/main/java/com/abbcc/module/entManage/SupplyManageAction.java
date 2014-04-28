/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SupplyManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-11           yixiugg                      initial
 **/

package com.abbcc.module.entManage;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcSupply;
import com.abbcc.service.SupplyService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.SupplyType;

/**
 * *SupplyManageAction.java
 */
@SuppressWarnings("serial")
public class SupplyManageAction extends BaseAction<AbcSupply> {
	private SupplyService supplyService;
	private String entId;
	private String[] supplyIds;
	private String entName;
	private String searchKey;
	private String supplyState = CommonConst.STATEINIT;
	private String SEARCH = "search";
	private String LISTSEARCH = "listsearch";
	private SupplyType supplyType = SupplyType.SE;

	/**
	 * 查看审核
	 * 
	 * @return
	 */
	public String viewAudit() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcSupply.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain",getCurrentAdmin().getDomain()));
		}
		detachedCriteria.add(Restrictions.eq("state", supplyState));
		detachedCriteria.addOrder(Order.desc("addTime"));
		if (StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("title", searchKey,
					MatchMode.ANYWHERE));
		// detachedCriteria.add(Restrictions.eq("type", supplyType));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = supplyService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return INPUT;
	}

	public String info() {
		return "info";
	}

	/**
	 * 编辑审核
	 * 
	 * @return
	 */
	public String editAudit() {
		for (String supplyId : supplyIds) {
			String[] supplyIdAndState = StringUtil.splitBySemicolon(supplyId);
			AbcSupply supply = supplyService.findById(supplyIdAndState[0]);
			supply.setState(supplyIdAndState[1]);
			supplyService.saveOrUpdate(supply);
			this.savaAdminLog("供求审核", supply.getTitle(), CommonConst.LOGUPDATE);
		}
		result = "审核成功！";
		return VIEW;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public String search() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcSupply.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain",getCurrentAdmin().getDomain()));
		}
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		if (StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("title", searchKey,
					MatchMode.ANYWHERE));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = supplyService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return SEARCH;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		for (String supplyId : supplyIds) {
			String[] supplyIdAndState = StringUtil.splitBySemicolon(supplyId);
			AbcSupply supply = supplyService.findById(supplyIdAndState[0]);
			supplyService.delete(supply);
			this.savaAdminLog("供求审核", supply.getTitle(), CommonConst.LOGDEL);
		}
		result = "删除成功！";
		return LISTSEARCH;

	}

	/**
	 * 查看详细
	 */
	public String view() {
		return DETAIL;
	}

	public SupplyService getSupplyService() {
		return supplyService;
	}

	public void setSupplyService(SupplyService supplyService) {
		this.supplyService = supplyService;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSupplyState() {
		return supplyState;
	}

	public void setSupplyState(String supplyState) {
		this.supplyState = supplyState;
	}

	public SupplyType getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(SupplyType supplyType) {
		this.supplyType = supplyType;
	}

	public String[] getSupplyIds() {
		return supplyIds;
	}

	public void setSupplyIds(String[] supplyIds) {
		this.supplyIds = supplyIds;
	}

}
