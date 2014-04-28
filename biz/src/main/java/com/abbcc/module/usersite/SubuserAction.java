/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-24           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;
import com.abbcc.service.UserService;
import com.abbcc.util.MD5EncryptUtil;

@SuppressWarnings("serial")
public class SubuserAction extends SiteBaseAction<AbcUser> {

	private UserService userService;

	public SubuserAction() {
		result = "";
	}

	public void register() throws IOException {
		if (!checkDomain())
			return;
		AbcUser user = new AbcUser();
		user.setUsername(entity.getUsername());
		user.setEnterpriseId(entity.getEnterpriseId());
		if (!checkUser(user))
			return;
		entity.setPassword(MD5EncryptUtil.md5Encry(entity.getPassword()));
		entity.setAddTime(new Date());
		entity.setState(CommonConst.STATENORMAL);
		entity.setType(CommonConst.SUBMEMBER);
		userService.save(entity);
		addCookie(entity);
		output(result + "registerSuccess()");
	}

	public void checkUser() throws IOException {
		checkUser(entity);
		output("isUserExist=" + resultList.size());
	}

	private boolean checkUser(AbcUser user) {
		resultList = userService.findByExample(user);
		return resultList.isEmpty();
	}

	public void login() throws IOException {
		if (!checkDomain())
			return;
		AbcUser au = new AbcUser();
		au.setUsername(entity.getUsername());
		au.setPassword(MD5EncryptUtil.md5Encry(entity.getPassword()));
		au.setState(CommonConst.STATENORMAL);
		List<AbcUser> list = userService.findByExample(au);
		Iterator<AbcUser> it = list.iterator();
		if (it.hasNext())
			addCookie(it.next());

		output(result + "loginBack(" + list.size() + ")");
	}

	public void logout() throws IOException {
		if (!checkDomain())
			return;
		getSession().removeAttribute(userDomain);
		output("$.cookie('username',null);$.cookie('userId',null);location.reload()");
	}

	private void addCookie(AbcUser user) {
		getSession().setAttribute(userDomain, user);
		result = "$.cookie('username','" + user.getUsername() + "');"+"$.cookie('userId','" + user.getUserId() + "');";
		// addCookie("username", user.getUsername());
	}

	@SuppressWarnings("unused")
	private void addCookie(String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setDomain(userDomain);
		getResponse().addCookie(cookie);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
