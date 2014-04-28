/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "FavouriteAction.java is the copyrighted,
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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcRecycle;
import com.abbcc.models.AbcUser;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.constant.ModelType;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class RecycleInterceptor extends AbstractInterceptor {

	BaseAction<?> action;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String resultCode = invocation.invoke();

		Object action = invocation.getAction();
		String methodName = invocation.getProxy().getMethod();
		Method method = getActionMethod(action.getClass(), methodName);
		Recycle recycle = method.getAnnotation(Recycle.class);
		if (recycle != null) {
			if (action instanceof BaseAction<?>) {
				this.action = (BaseAction<?>) action;
				Map<String, String> recycleMap = new HashMap<String, String>();
				String idProperty = recycle.id();
				String nameProperty = recycle.name();
				Object entity = this.action.getModel();
				if (idProperty.length() == 0) {
					method = getMethodByAnnotation(entity, Id.class);
					if (method == null) {
						Field field = getFieldByAnnotation(entity, Id.class);
						methodName = ObjectUtil.getMethodName(field);
						method = entity.getClass().getMethod(methodName);
					}
					String deletedId = (String) method.invoke(entity);
					// get display name
					methodName = ObjectUtil.getMethodName(nameProperty);
					method = entity.getClass().getMethod(methodName);
					String deletedName = (String) method.invoke(entity);

					recycleMap.put(deletedId, deletedName);
				} else {
					String[] deletedId = this.action.getRequest()
							.getParameterValues(idProperty);
					String entityId = getEntityId(entity);
					DetachedCriteria detachedCriteria = DetachedCriteria
							.forClass(entity.getClass());
					detachedCriteria
							.add(Restrictions.in(entityId, deletedId))
							.setProjection(
									Projections
											.projectionList()
											.add(Projections.property(entityId))
											.add(
													Projections
															.property(nameProperty)));
					@SuppressWarnings("unchecked")
					List<Object[]> list = this.action.getBaseService()
							.findAllByCriteria(detachedCriteria);
					for (Object[] obj : list)
						recycleMap.put((String) obj[0], (String) obj[1]);
				}
				toRecycled(recycle, recycleMap);
			}
		}

		return resultCode;
	}

	protected Method getMethodByAnnotation(Object instance,
			Class<? extends Annotation> clazz) {
		Method method = null;
		for (Method m : instance.getClass().getMethods())
			if (m.getAnnotation(clazz) != null) {
				method = m;
				break;
			}
		return method;
	}

	protected Field getFieldByAnnotation(Object instance,
			Class<? extends Annotation> clazz) {
		Field field = null;
		for (Field f : instance.getClass().getDeclaredFields())
			if (f.getAnnotation(clazz) != null) {
				field = f;
				break;
			}
		return field;
	}

	private void toRecycled(Recycle recycleAnnotation, Map<String, String> map) {
		AbcUser user = (AbcUser) action.getSession().getAttribute(
				CommonConst.SESSIONUSER);
		Date date = new Date();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			AbcRecycle recycleModel = new AbcRecycle();
			recycleModel.setUserId(user.getUserId());
			recycleModel.setState(CommonConst.STATENORMAL);
			recycleModel.setDelTime(date);
			recycleModel.setBelongType(ModelType.getModelType(action
					.getEntityClass()));
			recycleModel.setBelongId(entry.getKey());
			recycleModel.setContent(entry.getValue());
			if (recycleAnnotation.module().length() > 0)
				recycleModel.setCategory(recycleAnnotation.module());
			if (recycleAnnotation.resume().length() > 0)
				recycleModel.setResume(recycleAnnotation.resume());
			action.getBaseService().save(recycleModel);
		}
	}

	private String getEntityId(Object entity) {
		Method method = getMethodByAnnotation(entity, Id.class);
		if (method != null)
			return ObjectUtil.getFieldName(method.getName());
		else
			return getFieldByAnnotation(entity, Id.class).getName();
	}

	/*
	 * FIXME: This is copied from DefaultActionInvocation but should be exposed
	 * through the interface
	 */
	protected Method getActionMethod(Class<?> actionClass, String methodName)
			throws NoSuchMethodException {
		Method method;
		try {
			method = actionClass.getMethod(methodName, new Class[0]);
		} catch (NoSuchMethodException e) {
			// hmm -- OK, try doXxx instead
			try {
				String altMethodName = "do"
						+ methodName.substring(0, 1).toUpperCase()
						+ methodName.substring(1);
				method = actionClass.getMethod(altMethodName, new Class[0]);
			} catch (NoSuchMethodException e1) {
				// throw the original one
				throw e;
			}
		}
		return method;
	}
}
