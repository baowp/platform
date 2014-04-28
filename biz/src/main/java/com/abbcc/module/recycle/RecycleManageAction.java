/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "RecycleManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-17           yixiugg                      initial
 **/

package com.abbcc.module.recycle;

import com.abbcc.action.BaseAction;
import com.abbcc.models.AbcRecycle;
import com.abbcc.service.RecycleService;

/**
 * *RecycleManageAction.java
 */
@SuppressWarnings("serial")
public class RecycleManageAction extends BaseAction<AbcRecycle> {
	private RecycleService recycleService;

	/**
	 * 查看
	 */
	public String viewSearch() {
		return LIST;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	public String search() {
		return LIST;
	}

	/**
	 * 清空
	 * 
	 * @return
	 */
	public String viewClear() {
		return LIST;
	}
	/**
	 * 回复
	 * 
	 * @return
	 */
	public String viewRestore() {
		return LIST;
	}
	public RecycleService getRecycleService() {
		return recycleService;
	}

	public void setRecycleService(RecycleService recycleService) {
		this.recycleService = recycleService;
	}
}
