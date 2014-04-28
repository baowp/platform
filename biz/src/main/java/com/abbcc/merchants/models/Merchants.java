/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "NewsNews.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-6-12           wj                      initial
 */

package com.abbcc.merchants.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.springframework.transaction.annotation.Transactional;

import com.abbcc.models.AbcAttachment;
import com.abbcc.news.enums.NewsType;
import com.abbcc.news.enums.Priority;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.api.Domain;
import com.abbcc.service.AttachmentService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
@Indexed(index = "merchants")
@Entity
@Table(name = "MERCHANTS")
public class Merchants implements Domain {

	private String merchantsId;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String content;
	private Date addTime;
	private String type;
	private String state;
	private String domain;
	private Integer sort;

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_MERCHANTS", parameters = {
			@Parameter(name = "sequence", value = "SEQ_MERCHANTS"),
			@Parameter(name = "prefix", value = "nerchants") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_MERCHANTS")
	@Column(name = "merchants_id", unique = true, nullable = false, length = 64)
	public String getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(String merchantsId) {
		this.merchantsId = merchantsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String stateName(){
		if(this.getState().equals("00"))
			return "未处理";
		if(this.getState().equals("01"))
			return "审核通过";
		return "";
	}
	public String subTextEdesc(int i,String text){
		String s = StringUtil.parseHTMLtoText(text);
		if(s.length()>i){
			return s.substring(0, i-1);
		}
		return  s;
	}
}
