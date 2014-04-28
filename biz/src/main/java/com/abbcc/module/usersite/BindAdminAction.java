/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "BindAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-8-3           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.models.AbcBind;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.BindType;

@SuppressWarnings("serial")
public class BindAdminAction extends BindAction<AbcBind> {

	private String nginxPath;
	private String confPath;
	private String vhost;
	private String vhostPath;

	protected void before() {
		nginxPath = ConfConst.NGINX_DIR;
		confPath = nginxPath + "conf/";
		vhost = ConfConst.NGINX_VHOST;
		vhostPath = confPath + vhost + CommonConst.SEP;
	}

	public String audit() {
		entity.setState(CommonConst.STATEINIT);
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		condition(dc);
		pageList = bindService.findPageByCriteria(dc, startIndex);
		return "audit";
	}

	public String approved() {
		entity.setState(CommonConst.STATENORMAL);
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		condition(dc);
		pageList = bindService.findPageByCriteria(dc, startIndex);
		return "approved";
	}

	public String unapproved() {
		entity.setState(CommonConst.STATEDENY);
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		condition(dc);
		pageList = bindService.findPageByCriteria(dc, startIndex);
		return "unapproved";
	}

	private void condition(DetachedCriteria dc) {
		if (StringUtil.isBlank(entity.getUsername()))
			entity.setUsername(null);
		if (StringUtil.isBlank(entity.getAddress()))
			entity.setAddress(null);

		dc.add(Example.create(entity).enableLike(MatchMode.ANYWHERE)).addOrder(
				Property.forName("applyTime").desc());
	}

	public String deny() {
		entity.setState(CommonConst.STATEDENY);
		bindService.update(entity);
		return JSON;
	}

	public String approvedToDeny() throws IOException, InterruptedException {
		before();
		File file = new File(vhostPath + entity.getFileName());
		if (file.exists())
			file.delete();
		entity.setState(CommonConst.STATEDENY);
		bindService.update(entity);
		compatibleReload();
		return JSON;
	}

	public String approve() throws IOException, InterruptedException {
		bind();
		entity.setDenyReason(null);
		entity.setApproveTime(new Date());
		entity.setState(CommonConst.STATENORMAL);
		bindService.update(entity);
		return JSON;
	}

	private void bind() throws IOException, InterruptedException {
		before();
		write();
		compatibleReload();
	}

	private void write() {
		FreemarkerHelper freemarkerHelper = new FreemarkerHelper();
		Map<String, Object> root = new HashMap<String, Object>();
		String address = entity.getAddress();
		if (entity.getType() == BindType.D)
			address = "." + entity.getAddress();
		root.put("address", address);
		root.put("username", entity.getUsername());
		root.put("uploadDir", ConfConst.FILE_UPLOAD_DIR.replaceAll("\\\\", "/"));
		String ftl = "vhost.ftl";
		String targetPath = vhostPath + entity.getFileName();
		freemarkerHelper.createHTML(root, ftl, targetPath);
	}

	private void compatibleReload() throws IOException, InterruptedException {
		String osName = System.getProperty("os.name"); // 操作系统名称
		log.info(osName);
		if (osName.toLowerCase().contains("windows")) {
			winReload();
		} else {
			reload();
		}
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
		String batPath = servletContext.getRealPath("nginx_reload.bat");
		File bat = new File(batPath);
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
