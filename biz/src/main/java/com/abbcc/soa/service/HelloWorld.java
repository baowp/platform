/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "HelloWorld.java is the copyrighted,
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

package com.abbcc.soa.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.abbcc.models.AbcUser;

/**
 * *HelloWorld.java
 */
@WebService
public interface HelloWorld {

	public String sayHello(String name);

	AbcUser user(String name);

	Map<String, String> map(AbcUser user);

	List<String> list();
}
