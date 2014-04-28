/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserTemplateManageAction.java is the copyrighted,
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

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.SoaTemplate;
import com.abbcc.models.SoaTemplateCriteria;
import com.abbcc.models.SoaUser;
import com.abbcc.service.SoaTemplateCriteriaService;
import com.abbcc.service.SoaTemplateService;
import com.abbcc.service.SoaUserService;
import com.abbcc.util.FileUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.TemplateDataType;

/**
 * *UserTemplateManageAction.java
 */
@SuppressWarnings("serial")
public class UserTemplateManageAction extends FileUploadAction<SoaTemplate> {
	private SoaTemplateService soaTemplateService;
	private SoaUserService soaUserService;
	private SoaTemplateCriteriaService soaTemplateCriteriaService;
	public SoaTemplateService getSoaTemplateService() {
		return soaTemplateService;
	}

	public void setSoaTemplateService(SoaTemplateService soaTemplateService) {
		this.soaTemplateService = soaTemplateService;
	}

	private String siteId;
	private String searchKey;
	List<SoaTemplateCriteria> criterias;

	public List<SoaTemplateCriteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<SoaTemplateCriteria> criterias) {
		this.criterias = criterias;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	private TemplateDataType dataType;
	private String pageType;
	private String criteriaContent;
	private String orderContent;
	private String description;
	private String criteriaId;
	public  String name;

	/**
	 * 列表
	 */
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(SoaTemplate.class);
		if (StringUtil.isNotBlank(siteId)) {
			detachedCriteria.add(Restrictions.eq("usersiteId", siteId));
			this.startIndex = (this.page - 1) * pageSize;
			this.pageList = soaTemplateService.findPageByCriteria(
					detachedCriteria, pageSize, startIndex);
			if (pageList.getTotalCount() == 0)
				result = CommonConst.NORESULT;
		} else {
			result = "请先查询用户网站！";
		}
		return LIST;
	}

	/**
	 * 上传文件
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String upload() {
		SoaUser user = (SoaUser) soaUserService.findById(SoaUser.class, siteId);
		if (user == null) {
			this.result = "请先查询到用户！";
			return list();
		}
		if (StringUtil.isBlank(entity.getName())
				|| StringUtil.isBlank(entity.getPageName())) {
			this.result = "请输入模板名称和页面url";
			return list();
		}
		if (upload == null) {
			this.result = "请选择文件，只能是ftl,html格式";
			return list();
		}
		Set<String> fileTypes = new HashSet<String>();
		fileTypes.add("ftl");
		fileTypes.add("html");
		fileTypes.add("htm");
		if (!checkUploadFile(fileTypes)) {
			this.result = this.feedback.get(0);
			return list();
		}
		File file = upload.get(0);
		String content = FileUtil.readToString(file);
		entity.setUserdisplay("0");
		entity.setContent(content);
		entity.setUsersiteId(siteId);
		entity.setAddTime(new Date());
		soaTemplateService.save(entity);
		this.result = "模板上传更新成功！";
		return list();
	}

	/**
	 * 删除模板
	 * 
	 * @return
	 */
	public String delete() {
		soaTemplateService.delete(entity);
		this.result = "模板删除成功！";
		return list();
	}

	/**
	 * 修改模板
	 * 
	 * @return
	 */
	public String update() {
		soaTemplateService.saveOrUpdate(entity);
		this.result = ("修改模板成功！");
		if(pageType!=null)
			return "returnList";
		return list();
	}

	/**
	 * 查看模板的条件
	 * 
	 * @return
	 */
	public String viewCriteria() {
		SoaTemplateCriteria criteria = new SoaTemplateCriteria();
		criteria.setTemplateId(id);
		criterias = (List<SoaTemplateCriteria>) soaTemplateCriteriaService
				.findByExample(criteria);
		return "listCriteria";
	}

	/**
	 * 添加模板数据
	 * 
	 * @return
	 */
	public String addCriteria() {
		SoaTemplateCriteria criteria = new SoaTemplateCriteria();
		if (pageType.equals("1")) {
			String countPaginationDataType = "from SoaTemplateCriteria t where t.templateId=?  and t.pageType=1";
			List list = soaTemplateCriteriaService.find(
					countPaginationDataType, id);
			if (list.size() > 0) {
				this.result = "对不起，一个模板中分页的数据类型只能有一个！";
				return viewCriteria();
			}
		}
		criteria.setPageSize(pageSize);
		criteria.setDataType(dataType);
		criteria.setPageType(pageType);
		criteria.setOrdercontent(orderContent);
		criteria.setDescription(description);
		criteria.setName(name);
		criteria.setContent(criteriaContent);
		criteria.setTemplateId(id);
		soaTemplateCriteriaService.save(criteria);
		this.result = "添加模板数据成功！";
		return viewCriteria();
	}

	/**
	 * 修改模板数据
	 * 
	 * @return
	 */
	public String updateCriteria() {
		SoaTemplateCriteria criteria = (SoaTemplateCriteria) soaTemplateCriteriaService
				.findById(SoaTemplateCriteria.class, criteriaId);
		if (pageType.equals("1")) {
			String countPaginationDataType = "from SoaTemplateCriteria t where t.templateId=? and t.pageType=1";
			List list = soaTemplateCriteriaService.find(
					countPaginationDataType, id);
			if (list.size() > 0) {
				SoaTemplateCriteria temp = (SoaTemplateCriteria) list.get(0);
				if (!temp.getCriteriaId().equals(criteriaId)) {
					this.result = "对不起，一个模板中分页的数据类型只能有一个！";
					return viewCriteria();
				}
			}
		}
		criteria.setPageSize(pageSize);
		criteria.setDataType(dataType);
		criteria.setPageType(pageType);
		criteria.setName(name);
		criteria.setOrdercontent(orderContent);
		criteria.setDescription(description);
		criteria.setContent(criteriaContent);
		criteria.setTemplateId(entity.getTemplateId());
		soaTemplateCriteriaService.saveOrUpdate(criteria);
		this.result = "修改模板数据成功！";
		return viewCriteria();
	}

	/**
	 * 删除模板数据
	 * 
	 * @return
	 */
	public String deleteCriteria() {
		SoaTemplateCriteria criteria = (SoaTemplateCriteria) soaTemplateCriteriaService
				.findById(SoaTemplateCriteria.class, criteriaId);
		soaTemplateCriteriaService.delete(criteria);
		this.result = "删除模板数据成功！";
		return viewCriteria();
	}
	//显示自定义网站用户的模版（用户后台）
	public String viewUser(){
		if(this.getCurrentUser().getGrade().equals(CommonConst.USERGRADETWO)){
			SoaUser su = new SoaUser();
			su.setUsername(this.getCurrentUser().getUsername());
			List<SoaUser> list = soaUserService.findByExample(su);
			if(list.size()!=0){
				DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(SoaTemplate.class);
				detachedCriteria.add(Restrictions.and(Restrictions.eq("usersiteId", list.get(0).getUsersiteId()), Restrictions.eq("userdisplay", "1")));
				pageList=soaTemplateService.findPageByCriteria(detachedCriteria,
						pageSize, startIndex);
			}
		}
		return "list";
		
	}

	public SoaUserService getSoaUserService() {
		return soaUserService;
	}

	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public SoaTemplateCriteriaService getSoaTemplateCriteriaService() {
		return soaTemplateCriteriaService;
	}

	public void setSoaTemplateCriteriaService(
			SoaTemplateCriteriaService soaTemplateCriteriaService) {
		this.soaTemplateCriteriaService = soaTemplateCriteriaService;
	}

	public TemplateDataType getDataType() {
		return dataType;
	}

	public void setDataType(TemplateDataType dataType) {
		this.dataType = dataType;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(String criteriaId) {
		this.criteriaId = criteriaId;
	}

	public String getCriteriaContent() {
		return criteriaContent;
	}

	public void setCriteriaContent(String criteriaContent) {
		this.criteriaContent = criteriaContent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
}
