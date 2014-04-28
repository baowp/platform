/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SyncDataHelper.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-21           yixiugg                      initial
 **/

package com.abbcc.module.soa;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.exception.DataAccessException;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.helper.FtpHelper;
import com.abbcc.helper.ZipHelper;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcEnterpcontact;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaTemplate;
import com.abbcc.models.SoaUser;
import com.abbcc.models.SoaWebserver;
import com.abbcc.service.CategoryService;
import com.abbcc.service.CertificateService;
import com.abbcc.service.EnterpcontactService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.JobService;
import com.abbcc.service.NewsService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SoaTemplateService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.SoaWebserverService;
import com.abbcc.service.SupplyService;
import com.abbcc.service.UserService;
import com.abbcc.soa.client.WebServiceClient;
import com.abbcc.util.ThreeDesUtil;
import com.abbcc.util.UploadUtil;
import com.abbcc.util.constant.TemplateType;

/**
 * *SyncDataHelper.java
 */
public class SyncHelper {
	private AbcEnterprise enterprise;
	private AbcUser user;
	private SoaUser soaUser;
	private SoaTemplateService soaTemplateService;
	private EnterpriseService enterpriseService;
	private NewsService newsService;
	private UserService userService;
	private CategoryService categoryService;
	private ProductService productService;
	private CertificateService certificateService;
	private EnterpcontactService enterpcontactService;
	private JobService jobService;
	private SupplyService supplyService;
	private SoaUserService soaUserService;
	private SoaWebserverService soaWebserverService;
	private int pageSize = PaginationSupport.PAGESIZE;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public AbcEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(AbcEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public AbcUser getUser() {
		return user;
	}

	public void setUser(AbcUser user) {
		this.user = user;
	}

	public SoaUser getSoaUser() {
		return soaUser;
	}

	public void setSoaUser(SoaUser soaUser) {
		this.soaUser = soaUser;
	}

	public SoaTemplateService getSoaTemplateService() {
		return soaTemplateService;
	}

	public void setSoaTemplateService(SoaTemplateService soaTemplateService) {
		this.soaTemplateService = soaTemplateService;
	}

	public EnterpriseService getEnterpriseService() {
		return enterpriseService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public CertificateService getCertificateService() {
		return certificateService;
	}

	public void setCertificateService(CertificateService certificateService) {
		this.certificateService = certificateService;
	}

	public EnterpcontactService getEnterpcontactService() {
		return enterpcontactService;
	}

	public void setEnterpcontactService(
			EnterpcontactService enterpcontactService) {
		this.enterpcontactService = enterpcontactService;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public SupplyService getSupplyService() {
		return supplyService;
	}

	public void setSupplyService(SupplyService supplyService) {
		this.supplyService = supplyService;
	}

	public SoaUserService getSoaUserService() {
		return soaUserService;
	}

	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}

	/**
	 * 得到新闻
	 * 
	 * @param page
	 * @return
	 */
	public PaginationSupport getNews(int page) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this.enterprise
				.getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		int startIndex = (page - 1) * PaginationSupport.PAGESIZE;
		PaginationSupport ps = newsService.findPageByCriteria(detachedCriteria,
				PaginationSupport.PAGESIZE, startIndex);
		List<AbcNews> list = ps.getItems();
		for (AbcNews news : list) {
			news.setContent(replaceUserFolder(news.getContent()));
		}
		return ps;
	}

	/**
	 * 查找特殊显示的新闻，1：图片新闻，2：顶置新闻，3：滚动新闻
	 * 
	 * @param displayType
	 * @return
	 */
	public List<AbcNews> getNewsByDisplay(int displayType) {
		AbcNews news = new AbcNews();
		news.setState(CommonConst.STATENORMAL);
		news.setEnterpriseId(this.enterprise.getEnterpriseId());
		if (displayType == 1)
			news.setImagenews(CommonConst.STATENORMAL);
		if (displayType == 2)
			news.setTopnews(CommonConst.STATENORMAL);
		if (displayType == 3)
			news.setRollingnews(CommonConst.STATENORMAL);
		return newsService.findByExample(news);
	}

	/**
	 * 得到产品
	 * 
	 * @param page
	 * @return
	 */
	public PaginationSupport getProducts(int page) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this.enterprise
				.getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		int startIndex = (page - 1) * pageSize;
		PaginationSupport ps = productService.findPageByCriteria(
				detachedCriteria, pageSize, startIndex);
		List<AbcProduct> list = ps.getItems();
		for (AbcProduct product : list) {
			product.setProddesc(replaceUserFolder(product.getProddesc()));
		}
		return ps;
	}
	
	/**
	 * 得到产品
	 * 
	 * @param page
	 * @return
	 */
	public PaginationSupport getNewProducts(int page) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this.enterprise
				.getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		int startIndex = (page - 1) * pageSize;
		PaginationSupport ps = productService.findPageByCriteria(
				detachedCriteria, pageSize, startIndex);
		List<AbcProduct> list = ps.getItems();
		for (AbcProduct product : list) {
			product.setProddesc(replaceUserFolder(product.getProddesc()));
		}
		return ps;
	}

	/**
	 * 得到产品
	 * 
	 * @param page
	 * @return
	 */
	public PaginationSupport getProductsByCategory(int page, String category) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this.enterprise
				.getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("category", category));
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		int startIndex = (page - 1) * pageSize;
		PaginationSupport ps = productService.findPageByCriteria(
				detachedCriteria, pageSize, startIndex);
		List<AbcProduct> list = ps.getItems();
		for (AbcProduct product : list) {
			product.setProddesc(replaceUserFolder(product.getProddesc()));
		}
		return ps;
	}

	/**
	 * 得到分类
	 * 
	 * @param type
	 * @return
	 */
	public List<AbcCategory> getCategorys(String type) {
		AbcCategory category = new AbcCategory();
		category.setEnterpriseId(this.enterprise.getEnterpriseId());
		category.setType(type);
		category.setState(CommonConst.STATENORMAL);
		return categoryService.findByExample(category);
	}

	/**
	 * 得到招聘信息
	 * 
	 * @return
	 */
	public List<AbcJob> getJobs() {
		List<AbcJob> list = jobService.findByEntId(this.enterprise
				.getEnterpriseId());
		if (list != null) {
			for (AbcJob job : list) {
				job.setContent(replaceUserFolder(job.getContent()));
			}
		}
		return list;
	}

	/**
	 * 得到企业联系人
	 * 
	 * @return
	 */
	public List<AbcEnterpcontact> getEnterprisecontacts() {
		AbcEnterpcontact enterpcontact = new AbcEnterpcontact();
		enterpcontact.setState(CommonConst.STATENORMAL);
		enterpcontact.setEnterpriseId(this.enterprise.getEnterpriseId());
		return enterpriseService.findByExample(enterpcontact);
	}

	/**
	 * 得到证书资质
	 * 
	 * @return
	 */
	public List<AbcCertificate> getCerts() {
		List<AbcCertificate> list = certificateService
				.findByEntId(this.enterprise.getEnterpriseId());
		if (list != null) {
			for (AbcCertificate cert : list) {

			}
		}
		return list;

	}

	/**
	 * 得到供求
	 * 
	 * @return
	 */

	public List getSupplies() {
		AbcSupply supply = new AbcSupply();
		supply.setEnterpriseId(this.enterprise.getEnterpriseId());
		supply.setState(CommonConst.STATEALL);
		List<AbcSupply> list = supplyService.findByExample(supply);
		for (AbcSupply s : list) {
			s.setWdesc(replaceUserFolder(s.getWdesc()));
		}
		return list;
	}

	/**
	 * 得到用户模板所在的路径
	 * 
	 * @param username
	 * @return
	 */
	public String getTemplateDir(String username) {
		return CommonConst.REALPATH
				+ ConfConst.FOLDER_UPLOAD
				+ CommonConst.SEP
				+ FileUploadAction.getUserfolder(username).replaceAll("/",
						CommonConst.SEP + CommonConst.SEP) + CommonConst.SEP
				+ username + CommonConst.SEP + CommonConst.FOLDER_TEMPLATE;
	}

	/**
	 * 得到模板的物理路径
	 * 
	 * @param username
	 * @param type
	 * @return
	 * @throws DataAccessException
	 */
	public String getTemplatePath(String username, TemplateType type)
			throws DataAccessException {
		SoaUser user = new SoaUser();
		user.setUsername(username);
		user = (SoaUser) soaUserService.findByExample(user).get(0);
		SoaTemplate template = new SoaTemplate();
		template.setUsersiteId(user.getUsersiteId());
		template.setType(type);
		String s;
		List list = soaTemplateService.findByExample(template);
		if (list.size() > 0) {
			template = (SoaTemplate) soaTemplateService.findByExample(template)
					.get(0);
			s = template.getPath();
			s = s.substring(s.lastIndexOf(CommonConst.FOLDER_TEMPLATE)
					+ CommonConst.FOLDER_TEMPLATE.length());
		} else {
			return null;
		}
		return s;
	}

	/**
	 * 得到模板内容
	 * 
	 * @param username
	 * @param type
	 * @return
	 * @throws DataAccessException
	 */
	public String getTemplateContent(String username, TemplateType type)
			throws DataAccessException {
		SoaUser user = new SoaUser();
		user.setUsername(username);
		user = (SoaUser) soaUserService.findByExample(user).get(0);
		SoaTemplate template = new SoaTemplate();
		template.setUsersiteId(user.getUsersiteId());
		template.setType(type);
		String s;
		List list = soaTemplateService.findByExample(template);
		if (list.size() > 0) {
			template = (SoaTemplate) soaTemplateService.findByExample(template)
					.get(0);
			s = template.getContent();
		} else {
			return null;
		}
		return s;
	}

	/**
	 * zip压缩和ftp传输
	 * 
	 * @param userFolder
	 * @param zipFilePath
	 * @param destPath
	 * @throws DataAccessException
	 */
	public String zipFolderAndUploadToSvr(String userFolder, String zipFilePath,
			String destPath, String includeFolder, String excludeFolder)
			throws DataAccessException {
		
		String s = "";
		ZipHelper zipHelper = new ZipHelper();
		try {
			zipHelper.compress(userFolder, zipFilePath, includeFolder,
					excludeFolder);
		} catch (Exception e) {
			throw new DataAccessException("zip打包失败！" + e.toString());
		}
		FtpHelper ftpHelper = new FtpHelper();
		ftpHelper.setHost(soaUser.webServer().getIp());
		ftpHelper.setPort(21);
		ftpHelper.setUsername(soaUser.webServer().getFtpUsername());
		ftpHelper.setPassword(soaUser.webServer().getFtpPassword());
		ftpHelper.setDestPath(destPath);
		ftpHelper.setSrcFile(zipFilePath);
		try {
			ftpHelper.upload();
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataAccessException("连接ftp失败！" + e.toString());
		}
		WebServiceClient wsc = new WebServiceClient();
		wsc.setAddress(soaUser.webServer().getWebservice());

		String srcZipPath = destPath + "/" + soaUser.getUsername() + ".zip";
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

	/**
	 * 压缩旺铺并且上传
	 * @param userFolder
	 * @param zipFilePath
	 * @param destPath
	 * @param includeFolder
	 * @param excludeFolder
	 * @return
	 * @throws DataAccessException
	 */
	public String zipWangpuFolderAndUploadToSvr(String userFolder, String zipFilePath,
			String destPath, String includeFolder, String excludeFolder,String username)
			throws DataAccessException {
		
		SoaWebserver server = getWangpuServer(); 
		String s = "";
		ZipHelper zipHelper = new ZipHelper();
		try {
			zipHelper.compress(userFolder, zipFilePath, includeFolder,
					excludeFolder);
		} catch (Exception e) {
			throw new DataAccessException("zip打包失败！" + e.toString());
		}
		FtpHelper ftpHelper = new FtpHelper();
		ftpHelper.setHost(server.getIp());
		ftpHelper.setPort(21);
		ftpHelper.setUsername(server.getFtpUsername());
		ftpHelper.setPassword(server.getFtpPassword());
		ftpHelper.setDestPath(destPath);
		ftpHelper.setSrcFile(zipFilePath);
		try {
			ftpHelper.upload();
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataAccessException("连接ftp失败！" + e.toString());
		}
		WebServiceClient wsc = new WebServiceClient();
		wsc.setAddress(server.getWebservice());

		String srcZipPath = destPath + "/" + username + ".zip";
		String encryptUsername = ThreeDesUtil
				.encryptMode(username);
		String encryptSrcZipPath = ThreeDesUtil.encryptMode(srcZipPath);
		//Todo
		String wangpuFolder="";
		String encryptFolder = ThreeDesUtil.encryptMode(wangpuFolder);
		String ic = ThreeDesUtil.encryptMode(encryptUsername
				+ encryptSrcZipPath + encryptFolder
				+ CommonConst.SITEINFO.syncKey);
		try {
			s = wsc.syncSite(encryptUsername, encryptSrcZipPath, encryptFolder,
					ic);
			if(!s.equals("true")){
				throw new DataAccessException("连接webservice失败！" +s);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataAccessException("连接webservice失败！" + e.toString());
		}
		return s;
	}
	/**
	 * 得到数据
	 * 
	 * @param syncType
	 * @return
	 */
	@SuppressWarnings( { "unchecked" })
	public Map getData(String syncType) {
		Map map = new HashMap();
		map.put(CommonConst.TEMPLATE_DATA_ENTERPRISE, this.enterprise);
		// 全部
		if (syncType.equals("ALL")) {
			map.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, getNews(1));
			map.put(CommonConst.TEMPLATE_DATA_NEWS_IMAGE, getNewsByDisplay(1));
			map.put(CommonConst.TEMPLATE_DATA_NEWS_TOP, getNewsByDisplay(2));
			map
					.put(CommonConst.TEMPLATE_DATA_NEWS_ROLLING,
							getNewsByDisplay(3));
			map.put(CommonConst.TEMPLATE_DATA_NEWS_DETAIL, null);
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_LIST, getProducts(1));
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_NEW, getNewProducts(1));
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_CATEGORY,
					getCategorys(CommonConst.CATEGORYTYPEPRODUCT));
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_DETAIL, null);
			map.put(CommonConst.TEMPLATE_DATA_JOB, getJobs());
			map.put(CommonConst.TEMPLATE_DATA_SUPPLY, getSupplies());
			map.put(CommonConst.TEMPLATE_DATA_CONTACT, getEnterprisecontacts());
			map.put(CommonConst.TEMPLATE_DATA_INTRO, enterprise.getEdesc());
			map.put(CommonConst.TEMPLATE_DATA_CERT, getCerts());
		}
		// 首页
		if (syncType.equals(TemplateType.SY.name())) {
			map.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, getNews(1));
			map.put(CommonConst.TEMPLATE_DATA_NEWS_DETAIL, null);
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_LIST, getProducts(1));
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_CATEGORY,
					getCategorys(CommonConst.CATEGORYTYPEPRODUCT));
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_DETAIL, null);
			map.put(CommonConst.TEMPLATE_DATA_JOB, getJobs());
			map.put(CommonConst.TEMPLATE_DATA_SUPPLY, getSupplies());
			map.put(CommonConst.TEMPLATE_DATA_CONTACT, getEnterprisecontacts());
			map.put(CommonConst.TEMPLATE_DATA_INTRO, enterprise.getEdesc());
			map.put(CommonConst.TEMPLATE_DATA_CERT, getCerts());

		}// 新闻列表
		if (syncType.equals(TemplateType.XWLB.name())) {
			map.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, getNews(1));
		}
		// 新闻内容
		if (syncType.equals(TemplateType.XWNR.name())) {
			map.put(CommonConst.TEMPLATE_DATA_NEWS_LIST, getNews(1));
			map.put(CommonConst.TEMPLATE_DATA_NEWS_DETAIL, null);
		}
		// 产品列表
		if (syncType.equals(TemplateType.CPLB.name())) {
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_LIST, getProducts(1));
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_CATEGORY,
					getCategorys(CommonConst.CATEGORYTYPEPRODUCT));
		}
		// 产品内容
		if (syncType.equals(TemplateType.CPNR.name())) {
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_LIST, getProducts(1));
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_CATEGORY,
					getCategorys(CommonConst.CATEGORYTYPEPRODUCT));
			map.put(CommonConst.TEMPLATE_DATA_PRODUCT_DETAIL, null);
		}
		// 招聘
		if (syncType.equals(TemplateType.ZP.name())) {
			map.put(CommonConst.TEMPLATE_DATA_JOB, getJobs());
		}
		// 供求
		if (syncType.equals(TemplateType.GQ.name())) {
			map.put(CommonConst.TEMPLATE_DATA_SUPPLY, getSupplies());
		}
		// 联系方式
		if (syncType.equals(TemplateType.LX.name())) {
			map.put(CommonConst.TEMPLATE_DATA_CONTACT, getEnterprisecontacts());
		}
		// 公司介绍
		if (syncType.equals(TemplateType.JS.name())) {
			map.put(CommonConst.TEMPLATE_DATA_INTRO, enterprise.getEdesc());
		}
		// 证书资质
		if (syncType.equals(TemplateType.ZS.name())) {
			map.put(CommonConst.TEMPLATE_DATA_CERT, getCerts());
		}

		return map;
	}

	/**
	 * 写静态html
	 * 
	 * @param map
	 * @param syncType
	 * @throws DataAccessException
	 */
	@SuppressWarnings( { "unchecked" })
	public void writeHTML(Map map, String syncType) throws Exception {
		FreemarkerHelper freemarkerHelper = new FreemarkerHelper(
				getTemplateDir(soaUser.getUsername()));
		TemplateType templateType = convertTemplateType(syncType);
		String templatePath = getTemplatePath(soaUser.getUsername(),
				templateType);
		HtmlHelper htmlHelper = new HtmlHelper();
		htmlHelper.setData(map);
		htmlHelper.setFreemarkerHelper(freemarkerHelper);
		htmlHelper.setUsername(soaUser.getUsername());
		htmlHelper.setSyncHelper(this);
		// 如果是null，表示是全部同步
		if (templateType == null) {
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.SY);
			if (templatePath != null)
				htmlHelper.createSYHTML(templatePath);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.XWLB);
			if (templatePath != null)
				htmlHelper.createXWLBHTML(templatePath);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.XWNR);
			if (templatePath != null)
				htmlHelper.createXWNRHTML(templatePath);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.CPLB);
			PaginationSupport products = (PaginationSupport) htmlHelper
					.getData().get(CommonConst.TEMPLATE_DATA_PRODUCT_LIST);
			if (templatePath != null)
				htmlHelper.createCPLBHTML(templatePath);
			// 解决生成列表以后产品信息被更新了
			htmlHelper.getData().put(CommonConst.TEMPLATE_DATA_PRODUCT_LIST,
					products);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.CPNR);
			if (templatePath != null)
				htmlHelper.createCPNRHTML(templatePath);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.ZP);
			if (templatePath != null)
				htmlHelper.createZPHTML(templatePath);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.GQ);
			if (templatePath != null)
				htmlHelper.createGQHTML(templatePath);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.LX);
			if (templatePath != null)
				htmlHelper.createLXHTML(templatePath);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.JS);
			if (templatePath != null)
				htmlHelper.createJSHTML(templatePath);
			templatePath = getTemplateContent(soaUser.getUsername(),
					TemplateType.ZS);
			if (templatePath != null)
				htmlHelper.createZSHTML(templatePath);
		} else if (templateType == TemplateType.SY) {// 首页
			if (templatePath != null)
				htmlHelper.createSYHTML(templatePath);
		} else if (templateType == TemplateType.CPLB) {// 产品列表
			if (templatePath != null)
				htmlHelper.createCPLBHTML(templatePath);
		} else if (syncType.equals(TemplateType.CPNR.name())) {// 产品内容
			if (templatePath != null)
				htmlHelper.createCPNRHTML(templatePath);
		} else if (syncType.equals(TemplateType.XWLB.name())) {// 新闻列表
			if (templatePath != null)
				htmlHelper.createXWLBHTML(templatePath);
		} else if (syncType.equals(TemplateType.XWNR.name())) {// 新闻内容
			if (templatePath != null)
				htmlHelper.createXWNRHTML(templatePath);
		} else if (syncType.equals(TemplateType.ZP.name())) {// 招聘
			if (templatePath != null)
				htmlHelper.createZPHTML(templatePath);
		} else if (syncType.equals(TemplateType.GQ.name())) {// 供求
			if (templatePath != null)
				htmlHelper.createGQHTML(templatePath);
		} else if (syncType.equals(TemplateType.LX.name())) {// 联系方式
			if (templatePath != null)
				htmlHelper.createLXHTML(templatePath);
		} else if (syncType.equals(TemplateType.JS.name())) {// 公司介绍
			if (templatePath != null)
				htmlHelper.createJSHTML(templatePath);
		} else if (syncType.equals(TemplateType.ZS.name())) {// 证书资质
			if (templatePath != null)
				htmlHelper.createZSHTML(templatePath);
		}
	}

	/**
	 * 转换模板类型
	 * 
	 * @param syncType
	 * @return
	 */
	public TemplateType convertTemplateType(String syncType) {
		if (syncType.equals(TemplateType.SY.name()))
			return TemplateType.SY;
		else if (syncType.equals(TemplateType.XWLB.name()))
			return TemplateType.XWLB;
		else if (syncType.equals(TemplateType.XWNR.name()))
			return TemplateType.XWNR;
		else if (syncType.equals(TemplateType.CPLB.name()))
			return TemplateType.CPLB;
		else if (syncType.equals(TemplateType.CPNR.name()))
			return TemplateType.CPNR;
		else if (syncType.equals(TemplateType.ZP.name()))
			return TemplateType.ZP;
		else if (syncType.equals(TemplateType.GQ.name()))
			return TemplateType.GQ;
		else if (syncType.equals(TemplateType.LX.name()))
			return TemplateType.LX;
		else if (syncType.equals(TemplateType.JS.name()))
			return TemplateType.JS;
		else if (syncType.equals(TemplateType.LX.name()))
			return TemplateType.ZS;
		else
			return null;
	}

	/**
	 * 打包、上传和解压缩
	 * 
	 * @param syncType
	 * @throws DataAccessException
	 */
	public String uploadAndZip(String syncType) throws Exception {
		String s = "";
		String userFolder = ConfConst.FILE_UPLOAD_DIR
				+ UploadUtil.getRelativeFolder(soaUser.getUsername());
		String zipFilePath = ConfConst.TEMP_ZIP_FOLDER + soaUser.getUsername()
				+ ".zip";
		String destPath = UploadUtil.getDatePath();
		String includeFolder = null;
		String excludeFolder = null;
		if (syncType.equals("ALL")) {
			excludeFolder = "**/" + CommonConst.FOLDER_HTML + "/,**/"
					+ CommonConst.FOLDER_TEMPLATE + "/";
			s = zipFolderAndUploadToSvr(userFolder, zipFilePath, destPath, includeFolder,
					excludeFolder);
			File file = new File(zipFilePath);
			file.deleteOnExit();
			userFolder = userFolder + CommonConst.FOLDER_HTML + CommonConst.SEP;
			excludeFolder = null;
			s = zipFolderAndUploadToSvr(userFolder, zipFilePath, destPath, includeFolder,
					excludeFolder);
			file = new File(zipFilePath);
			file.deleteOnExit();
		} else {
			userFolder = userFolder + CommonConst.FOLDER_HTML + CommonConst.SEP;
			s = zipFolderAndUploadToSvr(userFolder, zipFilePath, destPath, includeFolder,
					excludeFolder);
			File file = new File(zipFilePath);
			file.deleteOnExit();
		}
		return s;
	}

	/**
	 * 打包、上传和解压缩
	 * 
	 * @param syncType
	 * @throws DataAccessException
	 */
	public String uploadAndZipWangPu(String username) throws Exception {
		String s = "";
		String userFolder = ConfConst.FILE_UPLOAD_DIR
				+ UploadUtil.getRelativeFolder(username);
		String zipFilePath = ConfConst.TEMP_ZIP_FOLDER + username + ".zip";
		String destPath = UploadUtil.getDatePath();
		String includeFolder = null;
		String excludeFolder = null;
		//第一次，排除wangpu，html和templates
		excludeFolder = "**/" + CommonConst.FOLDER_HTML_WANGPU + "/,**/"
				+ CommonConst.FOLDER_TEMPLATE + "/"+"**/" + CommonConst.FOLDER_HTML + "/";
		s = zipWangpuFolderAndUploadToSvr(userFolder, zipFilePath, destPath, includeFolder,
				excludeFolder,username);
		File file = new File(zipFilePath);
		file.deleteOnExit();
		//单独包含wangpu
		userFolder = userFolder + CommonConst.FOLDER_HTML_WANGPU + CommonConst.SEP;
		excludeFolder = null;
		s = zipWangpuFolderAndUploadToSvr(userFolder, zipFilePath, destPath, includeFolder,
				excludeFolder, username);
		file = new File(zipFilePath);
		file.deleteOnExit();
		return s;
	}

	/**
	 * 解析文件夹
	 * 
	 * @param date
	 * @return
	 */
	public String parseDateToFolder(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + CommonConst.SEP
				+ "MM" + CommonConst.SEP + "dd" + CommonConst.SEP);
		return sdf.format(date);
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public String replaceUserFolder(String s) {
		if (s == null)
			return s;
		s = s.replaceAll(CommonConst.CONTEXTROOT + "/"
				+ UploadUtil.getWebRelativeFolder(soaUser.getUsername()), "/");
		return s;
	}

	public SoaWebserverService getSoaWebserverService() {
		return soaWebserverService;
	}

	public void setSoaWebserverService(SoaWebserverService soaWebserverService) {
		this.soaWebserverService = soaWebserverService;
	}
	
	/**
	 * 得到旺铺服务器
	 * @return
	 */
	public SoaWebserver getWangpuServer(){
		SoaWebserver server = new SoaWebserver();
		server.setName("旺铺服务器");
		server = (SoaWebserver)getSoaWebserverService().findByExample(server).get(0);
		return server;
	}
}
