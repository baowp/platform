/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "WebServerManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-3           yixiugg                      initial
 **/

package com.abbcc.module.soa;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.SoaWebserver;
import com.abbcc.service.SoaWebserverService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

/**
 * *WebServerManageAction.java
 */
@SuppressWarnings("serial")
public class WebServerManageAction extends BaseAction<SoaWebserver> {
	private SoaWebserverService soaWebserverService;

	public SoaWebserverService getSoaWebserverService() {
		return soaWebserverService;
	}

	public void setSoaWebserverService(SoaWebserverService soaWebserverService) {
		this.soaWebserverService = soaWebserverService;
	}

	public String list() {
		resultList = soaWebserverService.findAll(SoaWebserver.class);
		if (resultList.size() == 0)
			result = "没有网站服务器，请添加！";
		return LIST;
	}
	@InputConfig(methodName = "view")
	public String update() {
		soaWebserverService.saveOrUpdate(entity);
		result = "网站服务器更新成功！";
		return list();
	}

	public String add() {
		soaWebserverService.save(entity);
		result = "网站服务器添加成功！";
		return list();
	}

	public String stop() {
		entity.setState(CommonConst.STATEDENY);
		soaWebserverService.saveOrUpdate(entity);
		result = "网站服务器停用成功！";
		return list();
	}

	public String open() {
		entity.setState(CommonConst.STATENORMAL);
		soaWebserverService.saveOrUpdate(entity);
		result = "网站服务器开启成功！";
		return list();
	}

	public String delete() {
		entity.setState(CommonConst.STATEDEL);
		soaWebserverService.saveOrUpdate(entity);
		result = "网站服务器删除成功！";
		return list();
	}

}
