/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "OrderServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-28           baowp                      initial
 */

package com.abbcc.service.impl;

import com.abbcc.dao.BaseDAO;
import com.abbcc.dao.OrderDAO;
import com.abbcc.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	private OrderDAO orderDAO;

	public void setOrderDAO(BaseDAO dao) {
		orderDAO = (OrderDAO) dao;
		setBaseDAO(dao);
	}

}
