/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ModelType.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-4           baowp                      initial
 */

package com.abbcc.util.constant;

import com.abbcc.models.*;
import com.abbcc.news.models.NewsNews;

public enum ModelType {
	AA(AbcAdmin.class), AB(AbcAdminprivilege.class), AC(AbcAdminuserpriv.class), AD(
			AbcAlbum.class,"相册管理"), AE(AbcAttachment.class), AF(AbcBrand.class, "品牌信息"), AG(
			AbcCategory.class, "产品分类"), AH(AbcCellbind.class), AI(
			AbcCellserver.class), AJ(AbcCertificate.class,"证书管理"), AL(AbcComment.class), AM(
			AbcDomainbind.class), AN(AbcDomainreg.class), AO(
			AbcEnterpcontact.class), AP(AbcEnterprise.class), AQ(
			AbcFavourite.class, "物品收藏"), AR(AbcGroupmember.class), AS(
			AbcJob.class,"招聘管理"), AT(AbcLayout.class), AU(AbcLayoutmodule.class), AV(
			AbcLink.class), AW(AbcLog.class), AX(AbcMail.class,"邮件管理"), AY(
			AbcMailsend.class), AZ(AbcMailtemplate.class), BA(AbcMessage.class,"留言信息"), BB(
			AbcModule.class), BC(AbcNews.class,"新闻管理"), BD(AbcOrder.class), BE(
			AbcPaylog.class), BF(AbcPayuser.class), BG(AbcProduct.class, "产品信息"), BH(
			AbcRecycle.class), BI(AbcSupply.class, "供求信息"), BJ(AbcSyscode.class), BK(
			AbcUpgradelog.class), BL(AbcUser.class), BM(AbcUsergroup.class), BN(
			AbcUserpriv.class), BO(AbcUserprivilege.class), BP(AbcViewlog.class), BQ(NewsNews.class,"资讯");

	private Class<?> entityClass;

	private String module;

	private ModelType(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	private ModelType(Class<?> entityClass, String module) {
		this.entityClass = entityClass;
		this.module = module;
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public String toString() {
		return module;
	}

	public static ModelType getModelType(Class<?> entityClass) {
		for (ModelType mt : values())
			if (mt.getEntityClass().equals(entityClass))
				return mt;

		return null;
	}
}
