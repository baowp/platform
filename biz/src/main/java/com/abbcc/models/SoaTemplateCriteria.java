/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SoaTemplateCriteria.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-4-23           baowp                      initial
 */

package com.abbcc.models;

import java.io.Serializable;

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

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.TemplateDataType;

@SuppressWarnings("serial")
@Indexed(index = "soa")
@Entity
@Table(name = "SOA_TEMPLATE_CRITERIA")
public class SoaTemplateCriteria implements Serializable {

	private String criteriaId;
	private String templateId;
	@Enumerated(EnumType.STRING)
	private TemplateDataType dataType;
	private String pageType;
	private Integer pageSize;
	private String content;
	private String name;
	private String description;
	private String ordercontent;
	public String getOrdercontent() {
		return ordercontent;
	}

	public void setOrdercontent(String ordercontent) {
		this.ordercontent = ordercontent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_SOA_TEMPLATE", parameters = {
			@Parameter(name = "sequence", value = "seq_criteria"),
			@Parameter(name = "prefix", value = "criteria") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_SOA_TEMPLATE")
	@Column(name = "criteria_id", unique = true, nullable = false, length = 64)
	public String getCriteriaId() {
		return criteriaId;
	}

	public void setCriteriaId(String criteriaId) {
		this.criteriaId = criteriaId;
	}

	@Column(name = "template_id")
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "data_type")
	public TemplateDataType getDataType() {
		return dataType;
	}

	public void setDataType(TemplateDataType dataType) {
		this.dataType = dataType;
	}

	@Column(name = "page_type")
	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	@Column(name = "page_size")
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
