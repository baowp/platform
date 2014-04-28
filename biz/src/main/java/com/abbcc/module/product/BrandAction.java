/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "BrandAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-26           baowp                      initial
 */

package com.abbcc.module.product;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcBrand;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.BrandService;
import com.abbcc.util.checkKey.CheckKey;

@SuppressWarnings("serial")
public class BrandAction extends BaseAction<AbcBrand> {

	private BrandService brandService;

	public String industryName;

	public String save() {
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		if (entity.getBrandId() == null || entity.getBrandId().length() == 0) {
			entity.setAdduserId(getCurrentUser().getUserId());
			entity.setEnterpriseId(getCurrentUser().getEnterpriseId());
		}
		entity.setState(CommonConst.STATENORMAL);
		brandService.saveOrUpdate(entity);
		savaLog("品牌",entity.getName(),CommonConst.LOGSAVE);
		result=CommonConst.ADDSUCCESS;
		return SUCCESS;
	}

	public String list() {
		AbcBrand example = new AbcBrand();
		example.setEnterpriseId(getCurrentUser().getEnterpriseId());
		example.setState(CommonConst.STATENORMAL);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Example.create(example));
		pageList = brandService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
		return LIST;
	}

	@Recycle
	public String remove() {
		entity.setState(CommonConst.STATEDEL);
		brandService.update(entity);
		savaLog("品牌",entity.getName(),CommonConst.LOGDEL);
		result=CommonConst.DELSUCCESS;
		return SUCCESS;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

}
