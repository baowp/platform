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
 * 2010-6-11           baowp                      initial
 */

package com.abbcc.news.enums;

import static com.abbcc.news.enums.DefaultClass.index;
import static com.abbcc.news.enums.DefaultClass.internal;
import static com.abbcc.news.enums.DefaultClass.world;
import static com.abbcc.news.enums.DefaultClass.military;
import static com.abbcc.news.enums.DefaultClass.finance;
import static com.abbcc.news.enums.DefaultClass.internet;
import static com.abbcc.news.enums.DefaultClass.house;
import static com.abbcc.news.enums.DefaultClass.car;
import static com.abbcc.news.enums.DefaultClass.sports;
import static com.abbcc.news.enums.DefaultClass.yule;
import static com.abbcc.news.enums.DefaultClass.technic;
import static com.abbcc.news.enums.DefaultClass.society;

public enum DefaultGenus {
	focus("焦点新闻", index),

	politics("时政要闻", internal), gangaotai("港澳台", internal),

	hqsy("环球视野", world), hqrw("环球人物", world),

	cnmil("中国军情", military), thjj("台海聚焦", military), worldmil("国际军情", military),

	stock("股票", finance), money("理财", finance), hongguan("宏观经济", finance), chanye(
			"产业经济", finance),

	rwdt("人物动态", internet), gsdt("公司动态", internet), search("搜索引擎", internet), commerce(
			"电子商务", internet),

	zcfx("政策风向", house),

	newcar("新车", car), gfhq("各地行情", car), weixiu("维修养护", car),

	nba("NBA", sports), cba("CBA", sports), worldsoccer("国际足球", sports), cnsoccer(
			"国内足球", sports),

	star("明星", yule), movie("电影", yule), tv("电视", yule), music("音乐", yule), twhh(
			"港台", yule), rihan("日韩", yule), oumei("欧美", yule),

	mobile("手机", technic), digit("数码", technic), computer("电脑", technic), discovery(
			"科普", technic),

	shwx("社会万象", society), zqck("真情时刻", society), qwys("奇闻异事", society);

	private String name;
	private DefaultClass defaultClass;

	private DefaultGenus(String name, DefaultClass defaultClass) {
		this.name = name;
		this.defaultClass = defaultClass;
	}

	public String toString() {
		return name;
	}

	public DefaultClass getDefaultClass() {
		return defaultClass;
	}
}
