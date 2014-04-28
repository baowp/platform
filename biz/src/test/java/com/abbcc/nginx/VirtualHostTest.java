/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "VirtualHost.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-8-2           baowp                      initial
 */

package com.abbcc.nginx;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.helper.FreemarkerHelper;

public class VirtualHostTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	String username;
	String nginxPath;
	String confPath;
	String userDomain;
	String address;

	@Before
	public void before() {
		CommonConst.CLASSPATH = "F:/workspace/Abbcc/build/classes/";
		CommonConst.SEP = "\\";
		ConfConst.FILE_UPLOAD_DIR = "E:\\Abbcc\\";
		userDomain = "eee.com";
		username = "ggggfj";
		if (address == null)
			address = "." + userDomain;
	}

	@Test
	public void t() throws IOException, InterruptedException {
		String osName = System.getProperty("os.name"); // 操作系统名称
		String osArch = System.getProperty("os.arch"); // 操作系统构架
		String osVersion = System.getProperty("os.version"); // 操作系统版本
		log.info(osName);
		log.info(osArch);
		log.info(osVersion);
		if (osName.toLowerCase().contains("windows")) {
			nginxPath = "G:/Program Files/nginx-0.8.36/";
			confPath = nginxPath + "conf/";
			write();
			winReload();
		} else {
			nginxPath = "/usr/local/nginx/";
			confPath = nginxPath + "conf/";
			write();
			reload();
		}
	}

	private void write() {
		FreemarkerHelper freemarkerHelper = new FreemarkerHelper();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("address", address);
		root.put("uploadDir", ConfConst.FILE_UPLOAD_DIR.replaceAll("\\\\", "/"));
		root.put("username", username);
		String ftl = "vhost.ftl";
		String targetPath = confPath + "vhost" + CommonConst.SEP + username
				+ ".conf";
		freemarkerHelper.createHTML(root, ftl, targetPath);
	}

	private void reload() throws IOException, InterruptedException {
		String command = nginxPath + "sbin/nginx -s reload";
		String ls_1;
		log.info(command);
		Process p = Runtime.getRuntime().exec(command);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(p.getInputStream()));
		while ((ls_1 = bufferedReader.readLine()) != null)
			log.info(ls_1);
		log.info("p.waitFor()----------->" + p.waitFor());
	}

	private void winReload() throws IOException, InterruptedException {
		String basePath = "d:/";
		File bat = new File(basePath + "nginx_reload.bat");
		if (bat.exists())
			bat.delete();
		bat.createNewFile();
		PrintWriter out = new PrintWriter(bat);
		out.println("cd /d " + nginxPath);
		out.println("nginx -s reload");
		out.flush();
		out.close();

		String cmd = "cmd /c " + bat.getAbsolutePath();
		String ls_1;
		log.info(cmd);
		Process p = Runtime.getRuntime().exec(cmd);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(p.getInputStream()));
		while ((ls_1 = bufferedReader.readLine()) != null)
			log.info(ls_1);
		log.info("p.waitFor()----------->" + p.waitFor());

		bat.delete();
	}
}
