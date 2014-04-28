/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CustomizeSiteService.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-4-24           yixiugg                      initial
 **/

package com.abbcc.module.soa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.exception.DataAccessException;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.helper.FtpHelper;
import com.abbcc.helper.ZipHelper;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.SoaTemplate;
import com.abbcc.models.SoaUser;
import com.abbcc.soa.client.WebServiceClient;
import com.abbcc.util.ThreeDesUtil;
import com.abbcc.util.UploadUtil;

import freemarker.template.TemplateException;

/**
 * *CustomizeSiteService.java
 */
public class CustomizeSiteService {
	private Log log = LogFactory.getLog(this.getClass());
	// /同步数据service
	private SyncDataService syncDataService;

	public SyncDataService getSyncDataService() {
		return syncDataService;
	}

	public void setSyncDataService(SyncDataService syncDataService) {
		this.syncDataService = syncDataService;
	}

	/**
	 * 同步用户的网站
	 * 
	 * @param user
	 * @param enterprise
	 * @throws TemplateException
	 * @throws IOException
	 * @throws DataAccessException
	 */
	@SuppressWarnings( { "unchecked", "deprecation" })
	public void syncUserSite(SoaUser soaUser, AbcEnterprise enterprise)
			throws DataAccessException {
		// step1.得到用户的模板
		// step2.得到用户数据
		// step3.写html
		// step4.打包文件
		// step5.传文件
		// step6.回调webservice解压缩文件
		List<SoaTemplate> templates = syncDataService
				.getUsersiteTemplate(soaUser);
		for (SoaTemplate template : templates) {
			String targetHtmlPath = "";
			// 得到数据
			Map dataMap = syncDataService.getTemplateMapdata(template,
					enterprise.getEnterpriseId());
			dataMap.put("news_num", 0);
			dataMap.put("categoryId", "0");
			dataMap.put("category", null);
			dataMap.put("category_num", 0);
			FreemarkerHelper freemarkerHelper = new FreemarkerHelper();
			// 列表页面
			if (template.getContentType().equals("00")) {
				// 生成也卖弄
				targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
						+ ConfConst.FOLDER_UPLOAD + CommonConst.SEP
						+ FileUploadAction.getUserfolder(soaUser.getUsername())
						+ CommonConst.SEP + soaUser.getUsername()
						+ CommonConst.SEP + CommonConst.FOLDER_HTML
						+ CommonConst.SEP + template.getPageName();
				freemarkerHelper.createSiteHTML(dataMap, template,
						targetHtmlPath);
				// /对于产品，新闻等，拥有分类信息的，需要单独处理
				// 如果是有分页的，先得到页数
				Integer pages = (Integer) dataMap.get("pages");
				if (pages != null) {
					for (int i = 0; i < pages; i++) {
						// 得到各页数据
						PaginationSupport ps = (PaginationSupport) dataMap
								.get("page" + i);
						// 得到分页数据的名称
						String pageParamName = (String) dataMap
								.get("pageParamName");
						// 设置分页的数据
						dataMap.put(pageParamName, ps);
						// 生成页面
						targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
								+ ConfConst.FOLDER_UPLOAD
								+ CommonConst.SEP
								+ FileUploadAction.getUserfolder(soaUser
										.getUsername()) + CommonConst.SEP
								+ soaUser.getUsername() + CommonConst.SEP
								+ CommonConst.FOLDER_HTML + CommonConst.SEP + i
								+ "_" + template.getPageName();
						freemarkerHelper.createSiteHTML(dataMap, template,
								targetHtmlPath);
					}
				}
				// /分类产品的展示页面，如果有产品分类的
				Integer productCategory = (Integer) dataMap
						.get("productCategory");
				if (productCategory != null) {
					List<AbcCategory> productCategorys = (List<AbcCategory>) dataMap
							.get("productCategorys");
					// 得到分类
					for (int i = 0; i < productCategory; i++) {
						AbcCategory category = productCategorys.get(i);
						// 得到分类的产品的页数
						Integer categoryPages = (Integer) dataMap
								.get("categoryPages" + i);
						for (int k = 0; k < categoryPages; k++) {
							// 得到分类分页的产品
							PaginationSupport ps = (PaginationSupport) dataMap
									.get("pageProduct_" + i + "_" + k);
							String pageParamName = (String) dataMap
									.get("pageParamName");
							// 设置分页的数据
							dataMap.put("category_num", (i + 1));
							dataMap.put("categoryId", category.getCategoryId());
							dataMap.put("category", category);
							dataMap.put(pageParamName, ps);
							// 生成页面，格式为 c_i_k_***
							targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
									+ ConfConst.FOLDER_UPLOAD
									+ CommonConst.SEP
									+ FileUploadAction.getUserfolder(soaUser
											.getUsername()) + CommonConst.SEP
									+ soaUser.getUsername() + CommonConst.SEP
									+ CommonConst.FOLDER_HTML + CommonConst.SEP
									+ category.getCategoryId() + "_" + (k + 1)
									+ "_" + template.getPageName();
							freemarkerHelper.createSiteHTML(dataMap, template,
									targetHtmlPath);

						}
					}
				}
				// /分类新闻的展示页面，如果有新闻分类的
				Integer newsCategory = (Integer) dataMap.get("newsCategory");
				if (newsCategory != null) {
					List<AbcCategory> newsCategorys = (List<AbcCategory>) dataMap
							.get("newsCategorys");
					// 得到分类
					for (int i = 0; i < newsCategory; i++) {
						// 得到分类的新闻的页数
						AbcCategory category = newsCategorys.get(i);
						Integer categoryPages = (Integer) dataMap
								.get("categoryPages" + i);
						for (int k = 0; k < categoryPages; k++) {
							// 得到分类分页的产品
							PaginationSupport ps = (PaginationSupport) dataMap
									.get("pageNews_" + i + "_" + k);
							String pageParamName = (String) dataMap
									.get("pageParamName");
							// 设置分页的数据
							dataMap.put("news_num", i);
							dataMap.put("categoryId", category.getCategoryId());
							dataMap.put("category", category);
							dataMap.put(pageParamName, ps);
							// 生成页面，格式为 n_i_k_***
							targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
									+ ConfConst.FOLDER_UPLOAD
									+ CommonConst.SEP
									+ FileUploadAction.getUserfolder(soaUser
											.getUsername()) + CommonConst.SEP
									+ soaUser.getUsername() + CommonConst.SEP
									+ CommonConst.FOLDER_HTML + CommonConst.SEP
									+ category.getCategoryId() + "_" + k + "_"
									+ template.getPageName();
							freemarkerHelper.createSiteHTML(dataMap, template,
									targetHtmlPath);

						}
					}
				}
			}
			// 详细页面
			else {

				List<AbcProduct> allProducts = (List<AbcProduct>) dataMap
						.get("allProducts");
				List<AbcNews> allNews = (List<AbcNews>) dataMap.get("allNews");
				List<AbcJob> allJobs = (List<AbcJob>) dataMap.get("allJobs");
				List<AbcSupply> allSupplys = (List<AbcSupply>) dataMap
						.get("allSupplys");
				// 生成所有产品细节的页面
				Boolean hasProductDetail = (Boolean) dataMap
						.get("hasProductDetail");
				Boolean hasNewsDetail = (Boolean) dataMap.get("hasNewsDetail");
				Boolean hasJobDetail = (Boolean) dataMap.get("hasJobDetail");
				Boolean hasSupplyDetail = (Boolean) dataMap
						.get("hasSupplyDetail");
				// Boolean hasJobDetail = (Boolean) dataMap.get("hasJobDetail");
				if (allProducts != null && hasProductDetail != null) {
					for (AbcProduct product : allProducts) {
						String detailName = (String) dataMap
								.get("productDetailName");
						dataMap.put(detailName, product);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy"
								+ CommonConst.SEP + "MM" + CommonConst.SEP
								+ "dd");
						targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
								+ ConfConst.FOLDER_UPLOAD
								+ CommonConst.SEP
								+ FileUploadAction.getUserfolder(soaUser
										.getUsername()) + CommonConst.SEP
								+ soaUser.getUsername() + CommonConst.SEP
								+ CommonConst.FOLDER_HTML + CommonConst.SEP
								+ sdf.format(product.getAddTime())
								+ CommonConst.SEP + product.getProductId()
								+ ".html";
						freemarkerHelper.createSiteHTML(dataMap, template,
								targetHtmlPath);
					}
				}
				// 生成所有新闻细节的页面
				if (allNews != null && hasNewsDetail != null) {
					for (AbcNews news : allNews) {
						String detailName = (String) dataMap
								.get("newsDetailName");
						dataMap.put(detailName, news);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy"
								+ CommonConst.SEP + "MM" + CommonConst.SEP
								+ "dd");
						targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
								+ ConfConst.FOLDER_UPLOAD
								+ CommonConst.SEP
								+ FileUploadAction.getUserfolder(soaUser
										.getUsername()) + CommonConst.SEP
								+ soaUser.getUsername() + CommonConst.SEP
								+ CommonConst.FOLDER_HTML + CommonConst.SEP
								+ sdf.format(news.getAddTime())
								+ CommonConst.SEP + news.getNewsId() + ".html";
						freemarkerHelper.createSiteHTML(dataMap, template,
								targetHtmlPath);
					}
				}
				// 生成所有招聘的页面
				if (allSupplys != null && hasSupplyDetail != null) {
					for (AbcSupply supply : allSupplys) {
						String detailName = (String) dataMap
								.get("supplyDetailName");
						dataMap.put(detailName, supply);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy"
								+ CommonConst.SEP + "MM" + CommonConst.SEP
								+ "dd");
						targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
								+ ConfConst.FOLDER_UPLOAD
								+ CommonConst.SEP
								+ FileUploadAction.getUserfolder(soaUser
										.getUsername()) + CommonConst.SEP
								+ soaUser.getUsername() + CommonConst.SEP
								+ CommonConst.FOLDER_HTML + CommonConst.SEP
								+ sdf.format(supply.getAddTime())
								+ CommonConst.SEP + supply.getSupplyId()
								+ ".html";
						freemarkerHelper.createSiteHTML(dataMap, template,
								targetHtmlPath);
					}
				}
				// 生成所有供求的页面

				if (allJobs != null && hasJobDetail != null) {
					for (AbcJob job : allJobs) {
						String detailName = (String) dataMap
								.get("jobDetailName");
						dataMap.put(detailName, job);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy"
								+ CommonConst.SEP + "MM" + CommonConst.SEP
								+ "dd");
						targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
								+ ConfConst.FOLDER_UPLOAD
								+ CommonConst.SEP
								+ FileUploadAction.getUserfolder(soaUser
										.getUsername()) + CommonConst.SEP
								+ soaUser.getUsername() + CommonConst.SEP
								+ CommonConst.FOLDER_HTML + CommonConst.SEP
								+ sdf.format(job.getAddTime())
								+ CommonConst.SEP + job.getJobId() + ".html";
						freemarkerHelper.createSiteHTML(dataMap, template,
								targetHtmlPath);
					}
				}
			}
			// // 生成所有招聘细节的页面
			// if (allJobs != null && hasJobDetail!=null) {
			// for (AbcJob job : allJobs) {
			// String detailName = (String) dataMap.get("jobDetailName");
			// dataMap.put(detailName, job);
			// targetHtmlPath = ConfConst.FILE_UPLOAD_DIR
			// + ConfConst.FOLDER_UPLOAD
			// + CommonConst.SEP
			// + FileUploadAction.getUserfolder(soaUser
			// .getUsername()) + CommonConst.SEP
			// + soaUser.getUsername() + CommonConst.SEP
			// + CommonConst.FOLDER_HTML + CommonConst.SEP
			// + job.getAddTime().getYear() + CommonConst.SEP
			// + job.getAddTime().getMonth() + CommonConst.SEP
			// + job.getAddTime().getDate() + CommonConst.SEP
			// + job.getJobId() + ".html";
			// freemarkerHelper.createSiteHTML(dataMap, template
			// .getContent(), targetHtmlPath);
			// }
			// }
			//
		}
		try {
			zipAndUploadUserFolder(soaUser);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 打成zip包
	 * 
	 * @param soaUser
	 * @throws DataAccessException
	 */
	public void zipAndUploadUserFolder(SoaUser soaUser)
			throws DataAccessException {
		String userFolder = ConfConst.FILE_UPLOAD_DIR
				+ UploadUtil.getRelativeFolder(soaUser.getUsername());
		String zipFilePath = ConfConst.TEMP_ZIP_FOLDER + soaUser.getUsername()
				+ ".zip";
		String includeFolder = null;
		String excludeFolder = "**/" + CommonConst.FOLDER_HTML + "/,**/"
				+ CommonConst.FOLDER_TEMPLATE + "/";
		String destPath = UploadUtil.getDatePath();
		ZipHelper zipHelper = new ZipHelper();
		try {
			zipHelper.compress(userFolder, zipFilePath, includeFolder,
					excludeFolder);
		} catch (Exception e) {
			throw new DataAccessException("zip打包失败！" + e.toString());
		}
		String fileName = soaUser.getUsername() + ".zip";
		putFile(soaUser, fileName, zipFilePath, destPath);

		File file = new File(zipFilePath);
		file.deleteOnExit();
		userFolder = userFolder + CommonConst.FOLDER_HTML + CommonConst.SEP;
		excludeFolder = null;
		try {
			zipHelper.compress(userFolder, zipFilePath, includeFolder,
					excludeFolder);
		} catch (Exception e) {
			throw new DataAccessException("zip打包失败！" + e.toString());
		}
		putFile(soaUser, fileName, zipFilePath, destPath);
		file = new File(zipFilePath);
		file.deleteOnExit();
	}

	/**
	 * 发送文件
	 * 
	 * @param address
	 * @param user
	 * @param password
	 * @param fileName
	 * @param dir
	 * @param serverDir
	 * @return
	 * @throws DataAccessException
	 */
	public String putFile(SoaUser soaUser, String fileName, String dir,
			String serverDir) throws DataAccessException {
		String s = "";
		FtpHelper ftpHelper = new FtpHelper();
		ftpHelper.setHost(soaUser.webServer().getIp());
		ftpHelper.setPort(21);
		ftpHelper.setUsername(soaUser.webServer().getFtpUsername());
		ftpHelper.setPassword(soaUser.webServer().getFtpPassword());
		ftpHelper.setDestPath(serverDir);
		ftpHelper.setSrcFile(dir);
		try {
			ftpHelper.upload();
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataAccessException("连接ftp失败！" + e.toString());
		}
		WebServiceClient wsc = new WebServiceClient();
		wsc.setAddress(soaUser.webServer().getWebservice());

		String srcZipPath = serverDir + "/" + soaUser.getUsername() + ".zip";
		String encryptUsername = ThreeDesUtil
				.encryptMode(soaUser.getUsername());
		String encryptSrcZipPath = ThreeDesUtil.encryptMode(srcZipPath);
		String encryptFolder = ThreeDesUtil.encryptMode(soaUser.getFolder());
		String ic = ThreeDesUtil.encryptMode(encryptUsername
				+ encryptSrcZipPath + encryptFolder
				+ CommonConst.SITEINFO.syncKey);
		try {
			s = wsc.syncSite(encryptUsername, encryptSrcZipPath, encryptFolder,
					ic);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataAccessException("连接webservice失败！" + e.toString());
		}
		return s;
	}

}
