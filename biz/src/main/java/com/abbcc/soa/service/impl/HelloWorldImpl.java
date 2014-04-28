/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "HelloWorldImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-29           yixiugg                      initial
 **/

package com.abbcc.soa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.abbcc.models.AbcUser;

/**
 ** HelloWorldImpl.java
 **/
@WebService(endpointInterface = "com.abbcc.soa.service.HelloWorld")
public class HelloWorldImpl {

	public String sayHello(String name) {
		return "sayhello" + name;
	}

	public AbcUser user(String name) {
		AbcUser user = new AbcUser();
		user.setName(name);
		user.setPhone("189");
		return user;
	}

	public Map<String, String> map(AbcUser user) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", user.getName());
		map.put("phone", user.getPhone());
		return map;
	}

	public List<String> list() {
		List<String> list = new ArrayList<String>();
		list.add("e");
		return list;
	}
}
