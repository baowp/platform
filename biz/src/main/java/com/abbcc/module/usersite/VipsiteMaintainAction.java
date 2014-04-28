/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "VipsiteManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-6           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import com.abbcc.models.AbcLaytheme;
import com.abbcc.models.AbcTheme;
import com.abbcc.models.AbcUser;
import com.abbcc.util.constant.layout.LaythemeState;

@SuppressWarnings("serial")
public class VipsiteMaintainAction extends VipsiteAction<AbcUser> {

	public VipsiteMaintainAction() {
		layts = LaythemeState.B;
		maintainable = true;
	}

	protected boolean beforeAction() {
		if (super.beforeAction()) {
			{// 没有相应权限跳转页面
				if (forbid(entity.getEnterpriseId())) {
					try {
						getResponse().sendRedirect(
								"http://" + entity.getUsername()
										+ ".vip.51archetype.com");
					} catch (IOException e) {
						log.error(e);
					}
				}
			}
			if(this.getSession().getAttribute("memberState")!=null&&this.getSession().getAttribute("memberState").equals("03")){
				try {
					getResponse().sendRedirect(
							"h51archetype.comcc.net/404.html");
				} catch (IOException e) {
					log.error(e);
				}
			}

			getRequest().setAttribute("themeList",
					themeService.findAll(AbcTheme.class));
			if (layout.getLayoutId() != null) {
				DetachedCriteria dc = DetachedCriteria
						.forClass(AbcLaytheme.class)
						.add(Property.forName("layoutId").eq(
								layout.getLayoutId()))
						.add(Property.forName("state").eq(LaythemeState.A))
						.addOrder(Property.forName("addTime").asc());
				getRequest().setAttribute("mythemeList",
						laythemeService.findAllByCriteria(dc));
			}

			prepareTool();
			return true;
		}
		return false;
	}

	private void prepareTool() {
		Set<String> bannerSet = new HashSet<String>();
		getRequest().setAttribute("bannerSet", bannerSet);
		Set<String> navBgSet = new HashSet<String>();
		getRequest().setAttribute("navBgSet", navBgSet);
		Set<String> titleBgSet = new HashSet<String>();
		getRequest().setAttribute("titleBgSet", titleBgSet);
		Set<String> inBgSet = new HashSet<String>();
		Set<String> outBgSet = new HashSet<String>();
		Set<String> bgSet = new HashSet<String>();

		String path = "/user/vipsite/theme";
		String realPath = servletContext.getRealPath(path);
		File themeFolder = new File(realPath);
		for (File theme : themeFolder.listFiles()) {
			if (theme.isDirectory()) {
				File imageFolder = new File(theme, "images");
				if (imageFolder.exists()) {
					File banner = fetchPic(imageFolder, "banner.min");
					if (banner.exists())
						bannerSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), banner.getName()));

					File navBg = fetchPic(imageFolder, "navBg");
					if (navBg.exists())
						navBgSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), navBg.getName()));

					File titleBg = fetchPic(imageFolder, "titleBg");
					if (titleBg.exists())
						titleBgSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), titleBg.getName()));

					File inBg = fetchPic(imageFolder, "inBg");
					if (inBg.exists())
						inBgSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), inBg.getName()));

					File outBg = fetchPic(imageFolder, "outBg");
					if (outBg.exists())
						outBgSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), outBg.getName()));
				}
			}
		}

		bgSet.addAll(inBgSet);
		bgSet.addAll(outBgSet);
		getRequest().setAttribute("bgSet", bgSet);
	}

	private File fetchPic(File folder, String name) {
		File file = new File(folder, name + ".gif");
		if (!file.exists())
			file = new File(folder, name + ".jpg");
		return file;
	}

	private String buildPath(String... str) {
		String string = servletContext.getContextPath();
		for (String s : str)
			string += s + "/";
		return string.replaceAll("/$", "");
	}
}
