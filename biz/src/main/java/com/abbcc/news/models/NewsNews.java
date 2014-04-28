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
 * 2010-6-12           baowp                      initial
 */

package com.abbcc.news.models;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.news.enums.Visibility;
import com.abbcc.news.models.api.Domain;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
@Indexed(index = "news")
@Entity
@Table(name = "NEWS_NEWS")
public class NewsNews implements Domain {

	private String newsId;
	@Field(name = "title", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String title;
	@Field(name = "content", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String content;
	@Field(name = "key", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String key;
	private Integer sort;
	private Date addTime;
	private String priority;
	private String type;
	private String origin;
	private String author;
	private String classId;
	private String genusId;
	private String classSign;
	private String genusSign;
	private String domain;
	private Visibility display;
	private Integer viewTimes;
	private String staticpath;
	private String newsPicPath;
	private String comentCount;
	private String enterpriseId;

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_NEWS_NEWS", parameters = {
			@Parameter(name = "sequence", value = "SEQ_NEWS_NEWS"),
			@Parameter(name = "prefix", value = "news") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_NEWS_NEWS")
	@Column(name = "NEWS_id", unique = true, nullable = false, length = 64)
	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "add_time")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "class_id")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Column(name = "genus_id")
	public String getGenusId() {
		return genusId;
	}

	public void setGenusId(String genusId) {
		this.genusId = genusId;
	}

	@Column(name = "class_sign")
	public String getClassSign() {
		return classSign;
	}

	public void setClassSign(String classSign) {
		this.classSign = classSign;
	}

	@Column(name = "genus_sign")
	public String getGenusSign() {
		return genusSign;
	}

	public void setGenusSign(String genusSign) {
		this.genusSign = genusSign;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Enumerated(EnumType.STRING)
	public Visibility getDisplay() {
		return display;
	}

	public void setDisplay(Visibility display) {
		this.display = display;
	}

	@Column(name = "view_times")
	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	@Column(name = "key")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "STATICPATH", length = 200)
	public String getStaticpath() {
		return this.staticpath;
	}

	public void setStaticpath(String staticpath) {
		this.staticpath = staticpath;
	}

	@Transient
	public String getNewsPicPath() {
		return newsPicPath;
	}

	public void setNewsPicPath(String newsPicPath) {
		this.newsPicPath = newsPicPath;
	}

	@Transient
	public String getComentCount() {
		return comentCount;
	}

	public void setComentCount(String comentCount) {
		this.comentCount = comentCount;
	}

	public String subTextEdesc(int i, String text) {
		String s = StringUtil.parseHTMLtoText(text);
		if (s.length() > i) {
			String newStr = s.replaceAll("(^[ |　]*|[ |　]*$)", "");
			return newStr.substring(0, i - 1);
		} else {

			return s.replaceAll("(^[ |　]*|[ |　]*$)", "");
		}
	}

	public String picPath(String newsId) {
		String picPath = ConfConst.FILE_SVR;
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(newsId);
		List<AbcAttachment> list = attachmentService.findByExample(attachment);
		if (list.size() != 0)
			picPath = picPath + list.get(0).getServerPath();
		return picPath;
	}

	public String stateName() {
		if (this.getDisplay().name().equals("hidden"))
			return "未审核";
		else
			return "通过审核";
	}

	public String entName() {
		if (this.getEnterpriseId() == null)
			return "上门网";
		else {
			EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
					.get("enterpriseService");
			return enterpriseService.findById(this.getEnterpriseId()).getName();
		}
	}

	public String enterprisePath(String entId) {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory
				.get("soaUserService");
		AbcEnterprise ent = enterpriseService.findById(entId);
		AbcUser userIds = userService.findById(ent.getUserId());
		SoaUser su = new SoaUser();
		su.setUsername(userIds.getUsername());
		List<SoaUser> sou = (List) soaUserService.findByExample(su);
		if (sou.size() != 0) {
			String url = "http://" + sou.get(0).getDomain();
			return "?url=" + StringUtil.encode(url);
		}

		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		String[] types = { "00", "01", "02", "03" };
		detachedCriteria.add(Restrictions.in("type", types));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("enterpriseId", entId),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if (userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)) {
				return "?url=/site/" + path;
			}
		}
		return "?url=http://" + path + ".51archetype.com";
	}

	public static void main(String[] args) {
		String s = "<P><IMG alt=\"\" src=\"http://www.buyang.com/userFiles/IMG_3259.jpg\" width=540 height=333></P><P>sdfgsdfgsdfgasdf</P>";
		new NewsNews().subTextEdesc(18, s);
	}

}
