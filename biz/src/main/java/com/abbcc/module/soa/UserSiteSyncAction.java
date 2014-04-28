/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserSiteAsyncAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-3           yixiugg                      initial
 **/

package com.abbcc.module.soa;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.exception.DataAccessException;
import com.abbcc.helper.FreemarkerHelper;
import com.abbcc.helper.FtpHelper;
import com.abbcc.helper.ZipHelper;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcEnterpcontact;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaTemplate;
import com.abbcc.models.SoaUser;
import com.abbcc.service.CategoryService;
import com.abbcc.service.CertificateService;
import com.abbcc.service.EnterpcontactService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.JobService;
import com.abbcc.service.NewsService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SoaTemplateCriteriaService;
import com.abbcc.service.SoaTemplateService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.SupplyService;
import com.abbcc.service.UserService;
import com.abbcc.soa.client.WebServiceClient;
import com.abbcc.util.ThreeDesUtil;
import com.abbcc.util.UploadUtil;
import com.abbcc.util.constant.TemplateType;

import freemarker.template.TemplateException;

/**
 * *UserSiteAsyncAction.java
 */
@SuppressWarnings("serial")
public class UserSiteSyncAction extends BaseAction<SoaUser> {
	private Date startDate;
	private Date endDate;
	private int pageProduct;
	private String syncType;
	private String searchKey;

	private UserService userService;
	private SyncDataService syncDataService;
	private CustomizeSiteService customizeSiteService;
	private AbcUser abcUser;
	private AbcEnterprise abcEnterprise;
	private SyncHelper syncHelper;

	public SyncHelper getSyncHelper() {
		return syncHelper;
	}

	public void setSyncHelper(SyncHelper syncHelper) {
		this.syncHelper = syncHelper;
	}

	/**
	 * 重新同步
	 * 
	 * @return
	 */
	public String redo() {
		abcUser = this.getUser();
		abcEnterprise = abcUser.enterprise();
		syncHelper.setUser(abcUser);
		syncHelper.setEnterprise(abcEnterprise);
		syncHelper.setSoaUser(entity);
		syncHelper.setPageSize(pageProduct);
		Map map = new HashMap();
		try {
			map = syncHelper.getData(syncType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.result = "同步失败，数据有问题！" + e.toString();
			return LIST;
		}
		try {
			syncHelper.writeHTML(map, syncType);
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			this.result = "同步失败，生成html有问题！" + e.toString();
			return LIST;
		}
		try {
			String s = syncHelper.uploadAndZip(syncType);
			if (!s.equalsIgnoreCase("true")) {
				this.result = "同步失败，数据打包上传有问题！" + s;
				return LIST;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			this.result = "同步失败，数据打包上传有问题！" + e.toString();
			return LIST;
		}
		this.result = "同步成功";
		return LIST;
	}

	public String getSyncType() {
		return syncType;
	}

	public void setSyncType(String syncType) {
		this.syncType = syncType;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	// /**
	// * 同步首页
	// */
	// private void syncIndex(){
	//		
	//		
	// }

	private AbcUser getUser() {
		AbcUser user = userService.getUserByUsername(entity.getUsername());
		return user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getPageProduct() {
		return pageProduct;
	}

	public void setPageProduct(int pageProduct) {
		this.pageProduct = pageProduct;
	}

	/**
	 * 同步用户网站
	 * 
	 * @return
	 */
	public String sync() {
		abcUser = this.getUser();
		abcEnterprise = abcUser.enterprise();
		try {
			customizeSiteService.syncUserSite(entity, abcEnterprise);
		} catch (DataAccessException e) {
			e.printStackTrace();
			this.result = "模板文件有问题，"+e.toString();
			return LIST;
			// TODO Auto-generated catch block
		} 
		this.result = "同步成功";
		return LIST;
	}

	public SyncDataService getSyncDataService() {
		return syncDataService;
	}

	public void setSyncDataService(SyncDataService syncDataService) {
		this.syncDataService = syncDataService;
	}

	public CustomizeSiteService getCustomizeSiteService() {
		return customizeSiteService;
	}

	public void setCustomizeSiteService(CustomizeSiteService customizeSiteService) {
		this.customizeSiteService = customizeSiteService;
	}
}
