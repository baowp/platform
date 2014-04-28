/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AbcTheme.java is the copyrighted,
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "abc_theme")
public class AbcTheme implements Serializable {

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_ABC_THEME", parameters = {
			@Parameter(name = "sequence", value = "SEQ_THEME"),
			@Parameter(name = "prefix", value = "theme") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_ABC_THEME")
	@Column(name = "theme_id", unique = true, nullable = false, length = 64)
	private String themeId;
	private String name;
	private String folder;

	public String getThemeId() {
		return themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
}
