/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SoaTemplate.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-29           baowp                      initial
 */

package com.abbcc.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.common.CommonConst;
import com.abbcc.service.SoaUserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.TemplateType;

@SuppressWarnings("serial")
@Indexed(index = "soa")
@Entity
@Table(name = "SOA_TEMPLATE")
public class SoaTemplate implements Serializable {

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_SOA_TEMPLATE", parameters = {
			@Parameter(name = "sequence", value = "SEQ_SOA_TEMPLATE"),
			@Parameter(name = "prefix", value = "template") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_SOA_TEMPLATE")
	@Column(name = "template_id", unique = true, nullable = false, length = 64)
	private String templateId;
	@Column(name = "usersite_id")
	private String usersiteId;
	@Enumerated(EnumType.STRING)
	private TemplateType type;
	private String path;
	private String content;
	private String name;
	@Column(name = "page_name")
	private String pageName;
	@Column(name = "content_type")
	private String contentType;
	@Column(name = "add_time")
	private Date addTime;
	private String userdisplay;
	public String getUserdisplay() {
		return userdisplay;
	}

	public void setUserdisplay(String userdisplay) {
		this.userdisplay = userdisplay;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUsersiteId() {
		return usersiteId;
	}

	public void setUsersiteId(String usersiteId) {
		this.usersiteId = usersiteId;
	}

	public TemplateType getType() {
		return type;
	}

	public void setType(TemplateType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public SoaUser soaUser() {
		SoaUserService soaUserService = (SoaUserService) BeansFactory
				.get("soaUserService");
		return (SoaUser) soaUserService.findById(SoaUser.class, usersiteId);
	}
	public String addTimeString() {
		if (this.getAddTime() == null)
			return "";
		return DateUtil.formatDate(this.getAddTime(), "yyyy-MM-dd");

	}
	
	public String physicalPath(){
		String s = CommonConst.REALPATH+ this.getPath().replaceAll("/", CommonConst.SEP+ CommonConst.SEP);
		return s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
