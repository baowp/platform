/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsGenus.java is the copyrighted,
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

import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.api.Domain;
import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index = "news")
@Entity
@Table(name = "NEWS_GENUS")
public class NewsGenus implements Domain {

	private String genusId;
	private String sign;
	private String name;
	private Integer sort;
	private Visibility display;
	private String classId;
	private String classSign;
	private String domain;

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_NEWS_GENUS", parameters = {
			@Parameter(name = "sequence", value = "SEQ_NEWS_GENUS"),
			@Parameter(name = "prefix", value = "genus") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_NEWS_GENUS")
	@Column(name = "GENUS_id", unique = true, nullable = false, length = 64)
	public String getGenusId() {
		return genusId;
	}

	public void setGenusId(String genusId) {
		this.genusId = genusId;
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

	@Column(name = "class_sign")
	public String getClassSign() {
		return classSign;
	}

	public void setClassSign(String classSign) {
		this.classSign = classSign;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "class_id")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}
}
