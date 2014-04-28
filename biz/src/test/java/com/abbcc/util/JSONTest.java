/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "JSONTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-22           baowp                      initial
 */

package com.abbcc.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.junit.Test;

import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.models.AbcUser;

public class JSONTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	@Test
	public void jsonToBean() {
		JSONArray jsonArray = JSONArray
				.fromObject("[{name:'sdf'},{userId:'001'}]");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		jsonConfig.setRootClass(AbcUser.class);

		AbcUser[] users = (AbcUser[]) JSONSerializer.toJava(jsonArray,
				jsonConfig);
		log.info(users.length);
	}

	@Test
	public void jsonToLayoutmodules() {
		JSONArray jsonArray = JSONArray
				.fromObject("[{module:{moduleId:'HEAD_LOGO'},block:'HOME',blockOrder:1,state:'01'},{module:{moduleId:'HEAD_LOGO'},block:'HOME',blockOrder:2,state:'01'}]");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		jsonConfig.setRootClass(AbcLayoutmodule.class);
		AbcLayoutmodule[] lays = (AbcLayoutmodule[]) JSONSerializer.toJava(
				jsonArray, jsonConfig);
		log.info(lays);
	}

	@Test
	public void beanToJson() {
		AbcCategory ac = new AbcCategory();
		log.info(JSONSerializer.toJSON(ac));
	}

	@Test
	public void t() {
		JSONObject jsonObject = JSONObject.fromObject("{y:{z:'a\\''},y2:[1]}");
		log.info(jsonObject);
	}
	
	@Test
	public void readFile() throws Exception {
		String strFileName = "conf/deploy/layoutmodules";
		/*StringBuffer buf = null;    
		BufferedReader breader = null;
		String strFileName = "conf/deploy/layoutmodules";
		breader = new BufferedReader(new InputStreamReader(
				 new FileInputStream((strFileName)),Charset.forName("utf-8")));   
		buf   =   new   StringBuffer();     
		while(breader.ready())       
		  buf.append((char)breader.read());     
	   breader.close();  
	  // String fileContent = buf.toString();*/
	   JSONArray jsonArray = JSONArray
		.fromObject( StringUtil.readFromFile(strFileName));
	   JsonConfig jsonConfig = new JsonConfig();
	   jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
	   jsonConfig.setRootClass(AbcLayoutmodule.class);
	   AbcLayoutmodule[] lays = (AbcLayoutmodule[]) JSONSerializer.toJava(
				jsonArray, jsonConfig);
	   for(AbcLayoutmodule module : lays) {
		   System.out.println(module.getBlock());
	   }
	}
}
