/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "HtmlHelper.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-22           yixiugg                      initial
 **/

package com.abbcc.module.soa;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;

import freemarker.template.TemplateException;

/**
 * *HtmlHelper.java
 */
public class HtmlHelper {
	// 数据
	private Map data;
	private String username;
	private FreemarkerHelper freemarkerHelper;
	private SyncHelper syncHelper;

	public SyncHelper getSyncHelper() {
		return syncHelper;
	}

	public void setSyncHelper(SyncHelper syncHelper) {
		this.syncHelper = syncHelper;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

	public FreemarkerHelper getFreemarkerHelper() {
		return freemarkerHelper;
	}

	public void setFreemarkerHelper(FreemarkerHelper freemarkerHelper) {
		this.freemarkerHelper = freemarkerHelper;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 生成首页的html
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createSYHTML(String templatePath) throws IOException, TemplateException {
		String targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
				+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
				+ FileUploadAction.getUserfolder(username) + CommonConst.SEP
				+ username + CommonConst.SEP + CommonConst.FOLDER_HTML
				+ CommonConst.SEP + CommonConst.TEMPLATE_HTML_INDEX;
		freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
	}

	/**
	 * 生成产品类别的html
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createCPLBHTML(String templatePath) throws IOException, TemplateException {
		// 生成产品列表页面
		String targetHtmlPath;
		PaginationSupport products = (PaginationSupport) data
				.get(CommonConst.TEMPLATE_DATA_PRODUCT_LIST);
		// 生成所有产品列表
		for (int i = 0; i < products.getPageCount(); i++) {
			if (i == 0) {
				targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
						+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
						+ FileUploadAction.getUserfolder(username)
						+ CommonConst.SEP + username + CommonConst.SEP
						+ CommonConst.FOLDER_HTML + CommonConst.SEP
						+ CommonConst.TEMPLATE_HTML_PRODUCT;
				freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
			} else {
				data.put(CommonConst.TEMPLATE_DATA_PRODUCT_LIST, syncHelper
						.getProducts(i + 1));
				targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
						+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
						+ FileUploadAction.getUserfolder(username)
						+ CommonConst.SEP + username + CommonConst.SEP
						+ CommonConst.FOLDER_HTML + CommonConst.SEP + i + "_"
						+ CommonConst.TEMPLATE_HTML_PRODUCT;
				freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
			}
		}
		// 生成各个分类的产品列表页面
		List<AbcCategory> categorys = (List<AbcCategory>) data
				.get(CommonConst.TEMPLATE_DATA_PRODUCT_CATEGORY);
		for (AbcCategory category : categorys) {
			data.put(CommonConst.TEMPLATE_DATA_PRODUCT_CURRENT_CATEGORY,
					category);
			products = this.syncHelper.getProductsByCategory(1, category
					.getCategoryId());
			data.put(CommonConst.TEMPLATE_DATA_PRODUCT_LIST, products);
			for (int i = 0; i < products.getPageCount(); i++) {
				if (i == 0) {
					targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
							+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
							+ FileUploadAction.getUserfolder(username)
							+ CommonConst.SEP + username + CommonConst.SEP
							+ CommonConst.FOLDER_HTML + CommonConst.SEP
							+ category.getCategoryId() + ".htm";
					freemarkerHelper.createSiteHTML(data, templatePath,
							targetHtmlPath);
				} else {
					data.put(CommonConst.TEMPLATE_DATA_PRODUCT_LIST, syncHelper
							.getProducts(i + 1));
					targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
							+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
							+ FileUploadAction.getUserfolder(username)
							+ CommonConst.SEP + username + CommonConst.SEP
							+ CommonConst.FOLDER_HTML + CommonConst.SEP
							+ category.getCategoryId() + "_" + i +".htm";
					freemarkerHelper.createSiteHTML(data, templatePath,
							targetHtmlPath);
				}
			}
		}
	}

	/**
	 * 生成产品内容的html
	 * 
	 * @param templatePath
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createCPNRHTML(String templatePath) throws IOException, TemplateException {
		String targetHtmlPath;
		PaginationSupport products = (PaginationSupport) data
				.get(CommonConst.TEMPLATE_DATA_PRODUCT_LIST);
		for (int i = 0; i < products.getPageCount(); i++) {
			if (i != 0)
				products = syncHelper.getProducts(i + 1);
			for (int k = 0; k < products.getItems().size(); k++) {
				AbcProduct product = (AbcProduct) products.getItems().get(k);
				data.put(CommonConst.TEMPLATE_DATA_PRODUCT_DETAIL, product);
				targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
						+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
						+ FileUploadAction.getUserfolder(username)
						+ CommonConst.SEP + username + CommonConst.SEP
						+ CommonConst.FOLDER_HTML + CommonConst.SEP
						+ syncHelper.parseDateToFolder(product.getAddTime())
						+ product.getProductId() + ".html";
				freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
			}
		}
	}

	/**
	 * 生成新闻内容
	 * 
	 * @param templatePath
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createXWNRHTML(String templatePath) throws IOException, TemplateException {
		String targetHtmlPath;
		PaginationSupport pagenews = (PaginationSupport) data
				.get(CommonConst.TEMPLATE_DATA_NEWS_LIST);
		for (int i = 0; i < pagenews.getPageCount(); i++) {
			if (i != 0)
				pagenews = syncHelper.getNews(i + 1);
			for (int k = 0; k < pagenews.getItems().size(); k++) {
				AbcNews news = (AbcNews) pagenews.getItems().get(k);
				data.put(CommonConst.TEMPLATE_DATA_NEWS_DETAIL, news);
				targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
						+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
						+ FileUploadAction.getUserfolder(username)
						+ CommonConst.SEP + username + CommonConst.SEP
						+ CommonConst.FOLDER_HTML + CommonConst.SEP
						+ syncHelper.parseDateToFolder(news.getAddTime())
						+ news.getNewsId() + ".html";
				freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
			}
		}
	}

	/**
	 * 生成新闻列表
	 * 
	 * @param templatePath
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createXWLBHTML(String templatePath) throws IOException, TemplateException {
		// 生成产品列表页面
		String targetHtmlPath;
		PaginationSupport pagenews = (PaginationSupport) data
				.get(CommonConst.TEMPLATE_DATA_NEWS_LIST);
		for (int i = 0; i < pagenews.getPageCount(); i++) {
			if (i == 0) {
				targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
						+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
						+ FileUploadAction.getUserfolder(username)
						+ CommonConst.SEP + username + CommonConst.SEP
						+ CommonConst.FOLDER_HTML + CommonConst.SEP
						+ CommonConst.TEMPLATE_HTML_NEWS;
				freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
			} else {
				data.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, syncHelper
						.getNews(i + 1));
				targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
						+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
						+ FileUploadAction.getUserfolder(username)
						+ CommonConst.SEP + username + CommonConst.SEP
						+ CommonConst.FOLDER_HTML + CommonConst.SEP + i + "_"
						+ CommonConst.TEMPLATE_HTML_NEWS;
				freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
			}
		}
	}

	/**
	 * 招聘
	 * 
	 * @param templatePath
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createZPHTML(String templatePath) throws IOException, TemplateException {
		String targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
				+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
				+ FileUploadAction.getUserfolder(username) + CommonConst.SEP
				+ username + CommonConst.SEP + CommonConst.FOLDER_HTML
				+ CommonConst.SEP + CommonConst.TEMPLATE_HTML_JOB;
		freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
	}

	/**
	 * 供求
	 * 
	 * @param templatePath
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createGQHTML(String templatePath) throws IOException, TemplateException {
		String targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
				+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
				+ FileUploadAction.getUserfolder(username) + CommonConst.SEP
				+ username + CommonConst.SEP + CommonConst.FOLDER_HTML
				+ CommonConst.SEP + CommonConst.TEMPLATE_HTML_SUPPLY;
		freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
	}

	/**
	 * 联系方式
	 * 
	 * @param templatePath
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createLXHTML(String templatePath) throws IOException, TemplateException {
		String targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
				+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
				+ FileUploadAction.getUserfolder(username) + CommonConst.SEP
				+ username + CommonConst.SEP + CommonConst.FOLDER_HTML
				+ CommonConst.SEP + CommonConst.TEMPLATE_HTML_CONTACT;
		freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
	}

	/**
	 * 公司介绍
	 * 
	 * @param templatePath
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createJSHTML(String templatePath) throws IOException, TemplateException {
		String targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
				+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
				+ FileUploadAction.getUserfolder(username) + CommonConst.SEP
				+ username + CommonConst.SEP + CommonConst.FOLDER_HTML
				+ CommonConst.SEP + CommonConst.TEMPLATE_HTML_ABOUTUS;
		freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
	}

	/**
	 * 证书资质
	 * 
	 * @param templatePath
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	public void createZSHTML(String templatePath) throws IOException, TemplateException {
		String targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
				+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
				+ FileUploadAction.getUserfolder(username) + CommonConst.SEP
				+ username + CommonConst.SEP + CommonConst.FOLDER_HTML
				+ CommonConst.SEP + CommonConst.TEMPLATE_HTML_CERT;
		freemarkerHelper.createSiteHTML(data, templatePath, targetHtmlPath);
	}
}
