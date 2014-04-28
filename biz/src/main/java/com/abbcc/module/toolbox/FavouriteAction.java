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
 * 2010-1-6           baowp                      initial
 */

package com.abbcc.module.toolbox;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcFavourite;
import com.abbcc.models.AbcUser;
import com.abbcc.service.FavouriteService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;

@SuppressWarnings("serial")
public class FavouriteAction extends BaseAction<AbcFavourite> {
	public String userId;
	public String enterpriseId;
	public String productId;
	public String pageType;
	private FavouriteService favouriteService;

	public String save() {

		return SUCCESS;
	}

	public String list() {
		if(StringUtil.isBlank(pageType))
			pageType="product";
		if(pageType.equals("product"))
			entity.setBelongType(ModelType.BG);
		else if(pageType.equals("ent"))
			entity.setBelongType(ModelType.AP);
		entity.setUserId(getCurrentUser().getUserId());
		entity.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		detachedCriteria.add(Example.create(entity)).addOrder(Order.desc("favId"));
		pageList = favouriteService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return LIST;
	}

	// result:5(二级会员),2(该对象已收藏),0(用户未登陆)
	public String saveCollectByEnt() {
		saveCollect(enterpriseId, ModelType.AP);
		return JSON;
	}

	public String saveCollectByPro() {
		saveCollect(productId, ModelType.BG);
		return JSON;
	}

	public String moodsByEnt() {
		moodsNumber(enterpriseId, ModelType.AP);
		return JSON;
	}

	public String moodsByPro() {
		moodsNumber(productId, ModelType.BG);
		return JSON;
	}

	public void moodsNumber(String belongId, ModelType belongType) {
		if (StringUtil.isNotBlank(belongId)) {
			AbcFavourite af = new AbcFavourite();
			af.setBelongId(belongId);
			af.setBelongType(belongType);
			af.setState(CommonConst.STATENORMAL);
			List list = baseService.findByExample(af);
			result = String.valueOf(list.size());
		} else
			result = "0";
	}

	public void saveCollect(String belongId, ModelType type) {
		if (StringUtil.isNotBlank(userId) && !userId.equals("null")) {
			AbcUser au = new AbcUser();
			baseService.load(au, userId);
			if (au.getType().equals(CommonConst.SUBMEMBER))
				result = "5";
			else {
				if (!exist(userId, belongId, type)) {
					result = "2";
				} else {
					favourite(userId, belongId, type);
				}

			}
		} else {
			result = "0";
		}
	}

	public void favourite(String userId, String belongId, ModelType belongType) {
		AbcFavourite af = new AbcFavourite();
		af.setAddTime(new Date());
		af.setUserId(userId);
		af.setBelongId(belongId);
		af.setBelongType(belongType);
		af.setState(CommonConst.STATENORMAL);
		baseService.save(af);
	}

	public boolean exist(String userId, String belongId, ModelType belongType) {
		AbcFavourite af = new AbcFavourite();
		af.setUserId(userId);
		af.setBelongId(belongId);
		af.setBelongType(belongType);
		af.setState(CommonConst.STATENORMAL);
		List<AbcFavourite> list = baseService.findByExample(af);
		return list.isEmpty();
	}

	@Recycle(name = "content")
	public String remove() {
		entity.setState(CommonConst.STATEDEL);
		favouriteService.update(entity);
		return SUCCESS;
	}

	public void setFavouriteService(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}
	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

}
