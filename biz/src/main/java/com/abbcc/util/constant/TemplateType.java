/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "TemplateType.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-5           yixiugg                      initial
 **/

package com.abbcc.util.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * *TemplateType.java
 */
public enum TemplateType {
	///////用户自定义网站模板
	SY("首页（index.html）"), XWLB("新闻列表（news.html）"), XWNR(
			"新闻内容（news_detail.html）"), CPLB("产品列表（product.html）"),
			XCP("新产品（new_product.html）"), CPNR(
			"产品内容（product_detail.html）"), ZP("招聘信息（job.html）"), GQ(
			"供求信息（supply.html）"), LX("联系方式（contact.html）"), JS(
			"关于我们（aboutus.html）"), ZS("证书资质（cert.html）"),
	//旺铺
	WPSY("首页（index.html）",1), WPXWLB("新闻列表（news.html）",1), WPXWNR(
			"新闻内容（news_detail.html）",1), WPCPLB("产品列表（product.html）",1), WPCPNR(
			"产品内容（product_detail.html）",1), WPZP("招聘信息（job.html）",1), WPGQ(
			"供求信息（supply.html）",1), WPLX("联系方式（contact.html）",1), WPJS(
			"关于我们（aboutus.html）",1), WPZS("证书资质（cert.html）",1);

	private String value;
	private int type;

	private TemplateType(String value) {
		this.value = value;
	}

	/**
	 * 返回对应类型的模板枚举
	 * 
	 * @param type
	 * @return
	 */
	public static List<TemplateType> values(int type) {
		List<TemplateType> templateTypes = new ArrayList<TemplateType>();
		for (TemplateType templateType : values()) {
			if (templateType.type == type)
				templateTypes.add(templateType);
		}
		return templateTypes;
	}

	private TemplateType(String value, int type) {
		this.value = value;
		this.type = type;
	}

	public String toString() {
		return value;
	}
}
