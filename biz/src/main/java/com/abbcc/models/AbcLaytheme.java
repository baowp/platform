/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AbcLaytheme.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-9           baowp                      initial
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.layout.LaythemeState;

@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "abc_laytheme")
public class AbcLaytheme implements Serializable {

	private String laythemeId;

	private AbcTheme theme;

	private String name;

	private String layoutId;

	private String style;

	private LaythemeState state;

	private Date addTime;

	private JSONObject jsonStyle;

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_ABC_LAYTHEME", parameters = {
			@Parameter(name = "sequence", value = "SEQ_LAYTHEME"),
			@Parameter(name = "prefix", value = "laytheme") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_ABC_LAYTHEME")
	@Column(name = "laytheme_id", unique = true, nullable = false, length = 64)
	public String getLaythemeId() {
		return laythemeId;
	}

	public void setLaythemeId(String laythemeId) {
		this.laythemeId = laythemeId;
	}

	@ManyToOne
	@JoinColumn(name = "theme_id")
	public AbcTheme getTheme() {
		return theme;
	}

	public void setTheme(AbcTheme theme) {
		this.theme = theme;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "layout_id")
	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layout) {
		this.layoutId = layout;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
		if (StringUtil.isBlank(style)) {
			this.style="{}";
		}
		jsonStyle=JSONObject.fromObject(this.style);
	}

	@Transient
	public JSONObject getJsonStyle() {
		return jsonStyle;
	}

	public void setJsonStyle(JSONObject jsonStyle) {
		this.jsonStyle = jsonStyle;
	}

	@Enumerated(EnumType.STRING)
	public LaythemeState getState() {
		return state;
	}

	public void setState(LaythemeState state) {
		this.state = state;
	}

	@Column(name = "add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
