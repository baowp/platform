/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "RecycleAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-4           baowp                      initial
 */

package com.abbcc.module.toolbox;

import java.lang.reflect.Method;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
//import org.junit.Test;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcRecycle;
import com.abbcc.models.AbcUser;
import com.abbcc.service.RecycleService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings("serial")
public class RecycleAction extends BaseAction<AbcRecycle> {

	private RecycleService recycleService;

	//@Test
	public void t() {
		ModelType m = ModelType.getModelType(AbcAdmin.class);
		log.info(m);
	}

	public String list() {
		AbcUser user = (AbcUser) getSession().getAttribute(
				CommonConst.SESSIONUSER);
		entity.setUserId(user.getUserId());
		entity.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.addOrder(Order.desc("delTime"));
		pageList = recycleService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return LIST;
	}

	public String resume() throws Exception {
		Object resumeEntity = recycleService.findById(entity.getBelongType()
				.getEntityClass(), entity.getBelongId());

		String resumeProperty = "state", resumeValue = CommonConst.STATENORMAL;
		String resume = entity.getResume();
		if (resume != null) {
			int index = resume.indexOf(':');
			if (index == -1) {
				resumeProperty = resume.trim();
			} else {
				resumeProperty = resume.substring(0, index).trim();
				resumeValue = resume.substring(index + 1).trim();
			}
		}
		String methodName = ObjectUtil.getMethodName("set", resumeProperty);
		Method method = resumeEntity.getClass().getMethod(methodName,
				new Class[] { String.class });
		method.invoke(resumeEntity, resumeValue);
		recycleService.update(resumeEntity);
		recycleService.delete(entity);
		return SUCCESS;
	}

	public String remove() {
		Object targetEntity = recycleService.findById(entity.getBelongType()
				.getEntityClass(), entity.getBelongId());
		recycleService.delete(targetEntity);
		recycleService.delete(entity);
		return SUCCESS;
	}

	public void setRecycleService(RecycleService recycleService) {
		this.recycleService = recycleService;
	}
}
