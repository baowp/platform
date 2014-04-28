/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsClass.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-6-10           baowp                      initial
 */

package com.abbcc.news.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.news.enums.DefaultClass;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.api.Domain;
import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index = "news")
@Entity
@Table(name = "NEWS_CLASS")
public class NewsClass implements Domain {

	private String classId;
	private String sign;
	private String name;
	private Integer sort;
	private Visibility display;
	private String domain;
	
	private DefaultClass defaultClass;
	private List<NewsGenus> genusList;

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_NEWS_CLASS", parameters = {
			@Parameter(name = "sequence", value = "SEQ_NEWS_CLASS"),
			@Parameter(name = "prefix", value = "class") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_NEWS_CLASS")
	@Column(name = "class_id", unique = true, nullable = false, length = 64)
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name!=null)
			name=name.replaceAll("\\s", "");
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Enumerated(EnumType.STRING)
	public Visibility getDisplay() {
		return display;
	}

	public void setDisplay(Visibility display) {
		this.display = display;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Transient
	public List<NewsGenus> getGenusList() {
		return genusList;
	}

	public void setGenusList(List<NewsGenus> genusList) {
		this.genusList = genusList;
	}

	@Transient
	public DefaultClass getDefaultClass() {
		return defaultClass;
	}

	public void setDefaultClass(DefaultClass defaultClass) {
		this.defaultClass = defaultClass;
	}
}
