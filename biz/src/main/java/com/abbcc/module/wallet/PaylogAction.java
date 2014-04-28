/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "PaylogAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-8           baowp                      initial
 */

package com.abbcc.module.wallet;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcUser;
import com.abbcc.service.PaylogService;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings("serial")
public class PaylogAction extends FileUploadAction<AbcPaylog> {

	private PaylogService paylogService;

	public AbcAttachment attach;

	public String records() {
		AbcUser user = getCurrentUser();
		entity.setUserId(user.getUserId());
		entity.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.addOrder(Property.forName("payTime").desc());
		pageList = paylogService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "records";
	}

	public String applying() {
		AbcUser user = getCurrentUser();
		entity.setUserId(user.getUserId());
		entity.setState(CommonConst.STATEINIT);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.addOrder(Property.forName("payTime").desc());
		pageList = paylogService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "applying";
	}

	public String renew() {
		return "renew";
	}

	public String apply() {
		AbcUser user = getCurrentUser();
		entity.setUserId(user.getUserId());
		entity.setState(CommonConst.STATEINIT);
		paylogService.save(entity);
		return "applied";
	}

	public String dialogRecords() {
		AbcUser user = getCurrentUser();
		getModel().setUserId(user.getUserId());
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.addOrder(Property.forName("payTime").desc());
		DetachedCriteria dc = DetachedCriteria.forClass(AbcAttachment.class)
				.add(Restrictions.eq("state", CommonConst.STATENORMAL))
				.setProjection(Projections.property("belongId"));
		detachedCriteria.add(Property.forName("paylogId").notIn(dc));
		pageList = paylogService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return "dialogRecords";
	}

	public String cert() {
		return "certificate";
	}

	// 凭证上传
	public String upload() {
		uploadImage();
		AbcUser user = getCurrentUser();
		attach.setUserId(user.getUserId());
		attach.setBelongType(ModelType.BE);
		attach.setState(CommonConst.STATENORMAL);
		attach.setServerIp(getRequest().getHeader("host"));
		attach.setServerPath(relativeFolder);
		attach.setFilename(uploadFileName.get(0));
		attach.setUploadTime(new Date());
		paylogService.save(attach);
		return "uploaded";
	}

	@SuppressWarnings("unchecked")
	public String balance() {
		AbcUser user = getCurrentUser();
		entity.setUserId(user.getUserId());
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.add(
				Property.forName("state").in(
						new Object[] { CommonConst.STATEINIT,
								CommonConst.STATENORMAL })).add(
				Property.forName("startTime").gt(new Date()));
		detachedCriteria.setProjection(Projections.sum("amount"));
		List list = paylogService.findAllByCriteria(detachedCriteria);
		getRequest().setAttribute("balance", list);
		return "balance";
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}

	public void setAttach(AbcAttachment attach) {
		this.attach = attach;
	}
}
