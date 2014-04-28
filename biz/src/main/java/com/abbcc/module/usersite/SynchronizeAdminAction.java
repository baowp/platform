/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SynchronizeAdminAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-7-26           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.IOException;

import com.abbcc.exception.DataAccessException;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.service.UserService;

@SuppressWarnings("serial")
public class SynchronizeAdminAction extends SynchronizeAction<SoaUser> {

	private UserService userService;

	public AbcUser getCurrentUser() {
		AbcUser user = userService.getUserByUsername(entity.getUsername());
		return user;
	}

	public String syncsite() throws IOException, DataAccessException {
		sync();
		result = "用户信息已同步";
		return LIST;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
