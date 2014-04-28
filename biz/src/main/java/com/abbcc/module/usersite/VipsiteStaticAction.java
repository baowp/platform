/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "VipsiteStaticAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-5-13           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.helper.XmlHelper;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSeo;
import com.abbcc.models.AbcUser;
import com.abbcc.models.interfaces.EnterpriseId;
import com.abbcc.service.PaylogService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.AlbumType;
import com.abbcc.util.constant.layout.Piece;
import com.abbcc.util.constant.layout.StaticDetail;
import com.abbcc.util.constant.layout.StaticRely;

import freemarker.ext.beans.BeansWrapper;

@SuppressWarnings("serial")
public class VipsiteStaticAction extends VipsiteAction<AbcUser> {

	private Map<String, Object> root;
	private FreemarkerHelper freemarkerHelper;
	private Piece piece;
	private String targetHtmlDir;
	private String targetHtmlPath;
	private String ftl;
	private PaylogService paylogService;

	public VipsiteStaticAction() {
		root = new HashMap<String, Object>();
	}

	protected boolean beforeAction() {
		return true;
	}

	public String search() {
		targetHtmlDir = ConfConst.FILE_UPLOAD_DIR + "vipsite" + CommonConst.SEP
				+ entity.getUsername() + CommonConst.SEP
				+ CommonConst.FOLDER_HTML + CommonConst.SEP;
		if (super.beforeAction()) {
			freemarkerHelper = new FreemarkerHelper(
					servletContext.getRealPath("user/viplate"));
			deposit("action", this);
			deposit("enums", BeansWrapper.getDefaultInstance().getEnumModels());
			deposit("statics", BeansWrapper.getDefaultInstance()
					.getStaticModels());
			deposit("lan", lan);
			ftl = "search.ftl";
			keywords = keywords.replace('/', '-');
			targetHtmlPath = targetHtmlDir + "search" + CommonConst.SEP
					+ keywords + "," + priceStart + "," + priceEnd + ".html";
			layout.clearMainmoduleList();
			super.search();
			freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
			String html = StringUtil.readFromFile(targetHtmlPath, "utf8");
			try {
				output(html);
			} catch (IOException e) {
				log.error(e);
			}
		}
		return NONE;
	}

	public void staticize() {
		targetHtmlDir = ConfConst.FILE_UPLOAD_DIR + "vipsite" + CommonConst.SEP
				+ entity.getUsername() + CommonConst.SEP
				+ CommonConst.FOLDER_HTML + CommonConst.SEP;
		if (super.beforeAction()) {
			freemarkerHelper = new FreemarkerHelper(
					servletContext.getRealPath("user/viplate"));
			deposit("action", this);
			deposit("enums", BeansWrapper.getDefaultInstance().getEnumModels());
			deposit("statics", BeansWrapper.getDefaultInstance()
					.getStaticModels());
			deposit("lan", lan);
			staticFlash();
			for (AbcLayoutmodule navmodule : layout.getNavmoduleList()) {
				staticize(navmodule.getModule().getModuleId());
			}
			staticRely();
			try {
				FileUtils.deleteDirectory(new File(targetHtmlDir + "search"));
			} catch (IOException e) {
				log.error(e);
			}
		}
	}

	private void staticFlash() {
		targetHtmlPath = targetHtmlDir + "index.htm";
		if (home().equals("flash")) {
			ftl = "flash.ftl";
			freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
		} else {
			new File(targetHtmlPath).delete();
		}
	}

	@SuppressWarnings("unchecked")
	private void staticize(String moduleId) {
		piece = Piece.valueOf(moduleId);
		ftl = piece.getAction() + ".ftl";
		targetHtmlPath = targetHtmlDir + piece.getAction() + ".html";
		layout.clearMainmoduleList();
		setPage(1);
		try {
			Method method = getClass().getMethod(piece.getAction());
			method.invoke(this);
			freemarkerHelper.createHTML(root, ftl, targetHtmlPath);

			if (piece.getKey() != null) {
				PaginationSupport ps = (PaginationSupport) root.get(piece
						.getKey());
				if (ps != null) {
					for (int i = 1; i <= ps.getPageCount(); i++) {
						setPage(i);
						targetHtmlPath = targetHtmlDir + piece.getAction()
								+ CommonConst.SEP + i + ".html";
						freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
					}
					if (piece.getAction().equals("product")) {
						eachCategoryProducts((List<AbcCategory>) root
								.get("categoryList"));
					}
					if (piece.getAction().equals("news")) {
						eachCategoryNews((List<AbcCategory>) root
								.get("newsRoots"));
					}
					if (piece.getAction().equals("photo")) {
						eachPhotos((List<AbcAlbum>) root.get("albumList"));
					}
				}
			}
			if (piece.getDetail() != null) {
				layout.clearMainmoduleList();
				StaticDetail detail = piece.getDetail();
				JSONObject json = JSONObject.fromObject(detail.getExample());
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
				jsonConfig.setRootClass(detail.getClazz());
				if (detail.name().equals("PHOTO")) {
					staticPhotoForDetail(detail,
							JSONSerializer.toJava(json, jsonConfig));
				} else {
					EnterpriseId example = (EnterpriseId) JSONSerializer
							.toJava(json, jsonConfig);
					example.setEnterpriseId(entity.getEnterpriseId());

					List<?> list = baseService.findByExample(example);
					if (example instanceof AbcProduct)
						productPic((AbcProduct[]) list
								.toArray(new AbcProduct[0]));
					layout.setBelongPage(detail.getBelong());
					ftl = detail.getBelong().name().toLowerCase() + ".ftl";
					for (Iterator<?> it = list.iterator(); it.hasNext();) {
						Object entity = it.next();
						deposit(detail.name().toLowerCase(), entity);
						method = detail.getClazz().getMethod(
								ObjectUtil.getMethodName(detail.getId()));
						String name = (String) method.invoke(entity);
						targetHtmlPath = targetHtmlDir + piece.getAction()
								+ CommonConst.SEP + "detail" + CommonConst.SEP
								+ name.replaceFirst("^[^1-9]+", "") + ".html";
						freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private void staticPhotoForDetail(StaticDetail detail, Object obj)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		DetachedCriteria dc = DetachedCriteria.forClass(AbcAlbum.class);
		dc.add(Restrictions.eq("state", CommonConst.STATENORMAL))
				.add(Restrictions.eq("blongType", AlbumType.AP.name()))
				.add(Restrictions.eq("belongId", entity.getEnterpriseId()));

		List<AbcAlbum> list = albumService.findAllByCriteria(dc);
		layout.setBelongPage(detail.getBelong());
		ftl = detail.getBelong().name().toLowerCase() + ".ftl";
		for (Iterator<?> it = list.iterator(); it.hasNext();) {
			Object entity = it.next();
			deposit(detail.name().toLowerCase(), entity);
			Method method = detail.getClazz().getMethod(
					ObjectUtil.getMethodName(detail.getId()));
			String name = (String) method.invoke(entity);
			eachPic(name);
			allAlbumPage(list);
			targetHtmlPath = targetHtmlDir + piece.getAction()
					+ CommonConst.SEP + "detail" + CommonConst.SEP
					+ name.replaceFirst("^[^1-9]+", "") + ".html";
			freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
		}
	}

	private void allAlbumPage(List<AbcAlbum> list) {
		Map albumPage = new HashMap();
		for (AbcAlbum aa : list) {
			albumPage.put(
					"photo-detail-"
							+ aa.getAlbumId().replaceFirst("^[^1-9]+", "")
							+ ".html", aa.getName());
		}
		deposit("albumSelect", albumPage);
	}

	private void eachPic(String albumId) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcAttachment.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("belongId", albumId)));
		detachedCriteria.addOrder(Order.desc("uploadTime"));
		deposit("pictrues",
				attachmentService.findAllByCriteria(detachedCriteria));
	}

	private void eachPhotos(List<AbcAlbum> photoList) {
		for (AbcAlbum album : photoList) {
			albumCategory = album.getAlbumId();
			String cateFolder = albumCategory.replaceFirst("^[^1-9]+", "");
			piecePhotoList();
			PaginationSupport ps = (PaginationSupport) root.get(piece.getKey());
			int pageCount = ps.getPageCount() > 0 ? ps.getPageCount() : 1;
			for (int i = 1; i <= pageCount; i++) {
				setPage(i);
				targetHtmlPath = targetHtmlDir + piece.getAction()
						+ CommonConst.SEP + cateFolder + CommonConst.SEP + i
						+ ".html";
				freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
			}
		}
	}

	private void eachCategoryProducts(List<AbcCategory> categoryList) {
		if (categoryList != null) {
			for (AbcCategory category : categoryList) {
				categoryId = category.getCategoryId();
				String cateFolder = categoryId.replaceFirst("^[^1-9]+", "");
				pieceProducts();
				PaginationSupport ps = (PaginationSupport) root.get(piece
						.getKey());
				int pageCount = ps.getPageCount() > 0 ? ps.getPageCount() : 1;
				for (int i = 1; i <= pageCount; i++) {
					setPage(i);
					targetHtmlPath = targetHtmlDir + piece.getAction()
							+ CommonConst.SEP + cateFolder + CommonConst.SEP
							+ i + ".html";
					freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
				}
				categoryId = null;
				eachCategoryProducts(category.getSonCate());
			}
		}
	}

	private void eachCategoryNews(List<AbcCategory> categoryList) {
		if (categoryList != null) {
			for (AbcCategory category : categoryList) {
				newsCategory = category.getCategoryId();
				String cateFolder = newsCategory.replaceFirst("^[^1-9]+", "");
				commonNews();
				PaginationSupport ps = (PaginationSupport) root.get(piece
						.getKey());
				int pageCount = ps.getPageCount() > 0 ? ps.getPageCount() : 1;
				for (int i = 1; i <= pageCount; i++) {
					setPage(i);
					targetHtmlPath = targetHtmlDir + piece.getAction()
							+ CommonConst.SEP + cateFolder + CommonConst.SEP
							+ i + ".html";
					freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
				}
				newsCategory = null;
				eachCategoryNews(category.getSonCate());
			}
		}
	}

	private void staticRely() {
		for (StaticRely rely : StaticRely.values()) {
			String resDir = rely.getResourceDir();
			String buildDir = rely.getBuild();
			for (String name : rely.getResource().split(",")) {
				if (name.endsWith("*")) {
					File folder = new File(servletContext.getRealPath(resDir
							+ "/" + (name = name.replace("*", ""))));
					if (folder.exists())
						try {
							FileUtils.copyDirectory(folder, new File(
									targetHtmlDir + buildDir + "/" + name));
						} catch (IOException e) {
							log.error(e);
						}
				} else {
					File file = new File(servletContext.getRealPath(resDir
							+ "/" + name));
					if (file.exists())
						try {
							FileUtils.copyFile(file, new File(targetHtmlDir
									+ buildDir + "/" + name));
						} catch (IOException e) {
							log.error(e);
						}
				}
			}
		}
		File folder = new File(servletContext.getRealPath("user/vipsite/theme/"
				+ pageTheme.getFolder()));
		if (folder.exists())
			try {
				File destDir = new File(targetHtmlDir + "theme");
				for (File file : folder.listFiles()) {
					if (file.isDirectory())
						FileUtils.copyDirectoryToDirectory(file, destDir);
					else if (file.isFile())
						FileUtils.copyFileToDirectory(file, destDir);
				}
			} catch (IOException e) {
				log.error(e);
			}
	}

	protected void laytheme() {
		Pattern p = Pattern.compile("url\\((?!http)[^)]+\\)");
		Matcher m = p.matcher(layout.getLaytheme().getStyle());
		while (m.find()) {
			String uri = m.group().replaceAll("^url\\(|\\)$", "");
			File srcFile = new File(servletContext.getRealPath(uri));
			if (srcFile.exists()) {
				File destFile = new File(targetHtmlDir
						+ uri.substring(uri.indexOf("/theme/") + 1));
				try {
					FileUtils.copyFile(srcFile, destFile);
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		layout.getLaytheme()
				.setStyle(
						layout.getLaytheme()
								.getStyle()
								.replaceAll("url\\((?!http)[^)]*/theme/",
										"url(theme/"));
	}

	protected void setupCategory(AbcCategory... categories) {
		for (AbcCategory cate : categories) {
			if (CommonConst.CATEGORYTYPEPRODUCT.equals(cate.getType())) {
				cate.setSonCate(cate.subCategory3());
			} else if (CommonConst.CATEGORYTYPENEWS.equals(cate.getType())) {
				cate.setSonCate(cate.subCategory4());
			}
			setupCategory(cate.getSonCate().toArray(new AbcCategory[0]));
		}
	}

	public void allUserStatic() {
		String quartzString = CommonConst.SITEINFO.staticNumber;
		if (quartzString.equals("0")) {
			DetachedCriteria dc = DetachedCriteria.forClass(AbcUser.class);
			dc.add(Restrictions.or(
					Restrictions.eq("grade", CommonConst.USERGRADETWO),
					Restrictions.eq("grade", CommonConst.USERGRADEONE)))
					.add(Restrictions.eq("state", CommonConst.STATENORMAL))
					.add(Restrictions.ne("type", CommonConst.SUBACCOUNT))
					.add(Restrictions.eq("domain", domain));
			List<AbcUser> au = userService.findAllByCriteria(dc);
			for (AbcUser user : au) {
				if (hasExpired(user)) {
					ObjectUtil.extend(entity, user);
					// userService.load(entity, user.getUserId());
					staticize();
				}
			}
			CommonConst.SITEINFO.staticNumber = "1";
			XmlHelper.setSiteInfoXml(CommonConst.SITEINFO,
					CommonConst.SITEINFOFILEPATH);
		} else {
			CommonConst.SITEINFO.staticNumber = "0";
			XmlHelper.setSiteInfoXml(CommonConst.SITEINFO,
					CommonConst.SITEINFOFILEPATH);
		}

	}

	protected boolean hasExpired(AbcUser au) {
		DetachedCriteria dc1 = DetachedCriteria.forClass(AbcPaylog.class);
		dc1.add(Restrictions.and(Restrictions.eq("userId", au.getUserId()),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		dc1.addOrder(Order.desc("endTime"));
		List<AbcPaylog> aplist = paylogService.findAllByCriteria(dc1);
		if (aplist.size() > 0) {
			if (!aplist.get(0).getEndTime().after(new Date()))
				return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	protected List<AbcSeo> fetchSeoList() {
		String key = "meta";
		if (take(key) != null)
			return (List<AbcSeo>) take(key);
		else {
			AbcSeo seo = new AbcSeo(entity.getEnterpriseId());
			List<AbcSeo> list = seoService.findByExample(seo);
			deposit(key, list);
			return list;
		}
	}

	protected void deposit(String key, Object value) {
		root.put(key, value);
	}

	protected Object take(String key) {
		return root.get(key);
	}

	public Map<String, Object> getRoot() {
		return root;
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}
}
