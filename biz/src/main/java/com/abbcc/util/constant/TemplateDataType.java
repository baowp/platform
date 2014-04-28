/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "TemplateDataType.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-4-23           yixiugg                      initial
 **/

package com.abbcc.util.constant;

import com.abbcc.models.*;

/**
 * *TemplateDataType.java
 */
public enum TemplateDataType {
	/* 新闻栏目 */
	XC("新闻栏目",AbcCategory.class),
	/* 新闻列表 */
	XW("新闻列表",AbcNews.class),
	/* 新闻内容 */
	XD("新闻内容",AbcNews.class),
	/* 产品分类 */
	PC("产品分类",AbcCategory.class),
	/* 产品列表 */
	PD("产品列表",AbcProduct.class), 
	/* 产品内容 */
	PT("产品内容",AbcProduct.class),
	/* 招聘信息 */
	ZP("招聘列表",AbcJob.class),
	/* 招聘信息 */
	ZPXX("招聘详细",AbcJob.class),
	/* 公司介绍 */
	JS("公司介绍",AbcEnterprise.class),
	/* 公司证书 */
	ZS("公司证书",AbcCertificate.class),
	/* 供求信息 */
	GQ("供求列表",AbcSupply.class),
	/* 供求信息 */
	GQXX("供求详细",AbcSupply.class),
	/* 联系方式 */
	LX("联系方式",AbcEnterpcontact.class),
	/* 公司信息 */
	GS("公司信息",AbcEnterprise.class);

	private String value;
	private Class cls;

	private TemplateDataType(String value) {
		this.value = value;
	}
	private TemplateDataType(String value,Class cls) {
		this.value = value;
		this.cls=cls;
	}
	public Class DataClass(){
		return this.cls;
	}
	public String toString() {
		return value;
	}
}
