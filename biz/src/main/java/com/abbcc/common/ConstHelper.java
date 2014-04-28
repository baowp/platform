/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ConstHelper.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-12           yixiugg                      initial
 **/

package com.abbcc.common;

/**
 * *ConstHelper.java
 */
public class ConstHelper {
	public static String parseGender(String gender) {
		if (gender == null)
			return "";
		if (gender.equals("男"))
			return CommonConst.GENDERMALE;
		if (gender.equals("女"))
			return CommonConst.GENDERFEMALE;
		if (gender.equals(CommonConst.GENDERMALE))
			return "男";
		if (gender.equals(CommonConst.GENDERFEMALE))
			return "女";
		return "";
	}

	public static String goodName(String gender) {
		if (gender.equals(CommonConst.GENDERMALE))
			return "先生";
		if (gender.equals(CommonConst.GENDERFEMALE))
			return "女士";
		return "";
	}

	public static String parseAdmintype(String admintype) {
		if (admintype == null)
			return "";
		if (admintype.equals("超级管理员"))
			return CommonConst.ADMINTYPESUPER;
		if (admintype.equals("管理员"))
			return CommonConst.ADMINTYPENORMAL;
		if (admintype.equals("员工"))
			return CommonConst.ADMINTYPESTUFF;
		if (admintype.equals(CommonConst.ADMINTYPESUPER))
			return "超级管理员";
		if (admintype.equals(CommonConst.ADMINTYPENORMAL))
			return "管理员";
		if (admintype.equals(CommonConst.ADMINTYPESTUFF))
			return "员工";
		return "";
	}

	public static String parseStatetype(String statetype) {
		if (statetype == null)
			return "";
		if (statetype.equals("正常"))
			return CommonConst.STATENORMAL;
		if (statetype.equals("封禁"))
			return CommonConst.STATEDENY;
		if (statetype.equals("已删除"))
			return CommonConst.STATEDEL;
		if (statetype.equals("未验证"))
			return CommonConst.STATEINIT;
		if (statetype.equals(CommonConst.STATENORMAL))
			return "正常";
		if (statetype.equals(CommonConst.STATEDENY))
			return "封禁";
		if (statetype.equals(CommonConst.STATEINIT))
			return "初始化";
		if (statetype.equals(CommonConst.STATEDEL))
			return "已删除";
		return "";
	}

	public static String parseUsertype(String userype) {
		if (userype == null)
			return "";
		if (userype.equals("未经工商注册，个人"))
			return CommonConst.USERTYPEPERSON;
		if (userype.equals("企业单位"))
			return CommonConst.USERTYPEENTERPRISE;
		if (userype.equals("事业单位或社会团体"))
			return CommonConst.USERTYPEGROUP;
		if (userype.equals("个体经营"))
			return CommonConst.USERTYPEINDIVI;
		if (userype.equals(CommonConst.USERTYPEPERSON))
			return "未经工商注册，个人";
		if (userype.equals(CommonConst.USERTYPEENTERPRISE))
			return "企业单位";
		if (userype.equals(CommonConst.USERTYPEGROUP))
			return "事业单位或社会团体";
		if (userype.equals(CommonConst.USERTYPEINDIVI))
			return "个体经营";
		return "";
	}

	public static String parseUsergrade(String gradeype) {
		if (gradeype == null)
			return "免费版";
		if (gradeype.equals("免费版"))
			return CommonConst.USERGRADENONE;
		if (gradeype.equals("企业版"))
			return CommonConst.USERGRADEONE;
		if (gradeype.equals("集团版"))
			return CommonConst.USERGRADETWO;

		if (gradeype.equals(CommonConst.USERGRADENONE))
			return "免费版";
		if (gradeype.equals(CommonConst.USERGRADEONE))
			return "企业版";
		if (gradeype.equals(CommonConst.USERGRADETWO))
			return "集团版";

		return "";
	}
}
