/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SupplyAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-25           baowp                      initial
 */

package com.abbcc.module.product;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcSupply;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.SupplyService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;
import com.abbcc.util.constant.ModelType;
import com.mchange.v2.codegen.bean.Property;

@SuppressWarnings("serial")
public class SupplyAction extends BaseAction<AbcSupply> {

	private SupplyService supplyService;

	public String productName;
	public String back;

	public String save() {
		/*
		 * if (sValidate()) return INPUT;
		 */
		entity.setEnterpriseId(getCurrentUser().getEnterpriseId());
		entity.setDomain(domain);
		entity.setAddTime(new Date());
		if (CommonConst.NEEDSAUDIT)
			entity.setState(CommonConst.STATEINIT);
		else
			entity.setState(CommonConst.STATENORMAL);
		supplyService.save(entity);
		result = CommonConst.ADDSUCCESS;
		setUploadState(entity.getContact());
		this.setTempAttachment(entity.getSupplyId(), ModelType.BI);
		savaLog("供求", entity.getTitle(), CommonConst.LOGSAVE);
		return SUCCESS;
	}

	public String update() {
		/*
		 * if (sValidate()) return INPUT;
		 */
		entity.setState(CommonConst.STATEINIT);
		supplyService.update(entity);
		result=CommonConst.EDITSUCCESS;
		setUploadState(entity.getContact());
		this.setTempAttachment(entity.getSupplyId(), ModelType.BI);
		savaLog("供求", entity.getTitle(), CommonConst.LOGUPDATE);
		return back;
	}

	@Recycle(name = "title")
	public String remove() {
		supplyService.delete(entity);
		result = CommonConst.DELSUCCESS;
		savaLog("供求", entity.getTitle(), CommonConst.LOGDEL);
		return back;
	}

	public String auditing() {
		entity.setEnterpriseId(getCurrentUser().getEnterpriseId());
		entity.setState(CommonConst.STATEINIT);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.addOrder(Order.desc("addTime"));
		pageList = supplyService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "auditing";
	}

	public String unapprove() {
		entity.setEnterpriseId(getCurrentUser().getEnterpriseId());
		entity.setState(CommonConst.STATEDENY);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.addOrder(Order.desc("addTime"));
		pageList = supplyService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "unapprove";
	}

	public String expired() {
		entity.setEnterpriseId(getCurrentUser().getEnterpriseId());
		entity.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.add(Restrictions.lt("endTime", new Date()));
		detachedCriteria.addOrder(Order.desc("addTime"));
		pageList = supplyService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "expired";
	}

	public String published() {
		entity.setEnterpriseId(getCurrentUser().getEnterpriseId());
		entity.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		if (StringUtil.isNotBlank(entity.getTitle()))
			detachedCriteria.add(Restrictions.like("title", entity.getTitle(),
					MatchMode.ANYWHERE));
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.add(Restrictions.gt("endTime", new Date()));
		detachedCriteria.addOrder(Order.desc("addTime"));
		pageList = supplyService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "published";
	}

	@SuppressWarnings("deprecation")
	public String publishAgain() {
		Date d = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.MONTH, d.getMonth() + 1);

		entity.setEndTime(calendar.getTime());
		supplyService.update(entity);
		result = CommonConst.EDITSUCCESS;
		return "showexpired";
	}

	public boolean sValidate() {
		if (CommonConst.ISCONTROL) {
			if (getSession().getAttribute("addState").equals(
					CommonConst.STATEINIT)) {
				DetachedCriteria dc = DetachedCriteria.forClass(entity
						.getClass());
				dc.add(Restrictions.eq("enterpriseId", getCurrentUser()
						.getEnterpriseId()))
						.add(Restrictions.eq("domain", domain))
						.setProjection(Projections.count("supplyId"));
				int i = supplyService.getCountByCriteria(dc);
				if (i >= CommonConst.SUPPLYCOUNT) {
					addFieldError("addState", "对不起，您目前还不是高级会员，供求的发布数量不能超过"
							+ CommonConst.SUPPLYCOUNT
							+ "条!<a href=\"/user/upgrade/show\">升级后将不受限制</a>");
					return true;
				}
			}
		}
		if (!CheckKey.checkKey(entity.getTitle())) {
			this.addFieldError("title", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getSkey())) {
			this.addFieldError("skey", "存在非法字符！");
			return true;
		}
		if (!CheckKey.checkKey(entity.getContactId())) {
			this.addFieldError("contactId", "存在非法字符！");
			return true;
		}
		return false;
	}

	public void setSupplyService(SupplyService supplyService) {
		this.supplyService = supplyService;
	}
}
