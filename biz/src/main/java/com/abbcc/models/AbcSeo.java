package com.abbcc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.layout.BelongPage;

@SuppressWarnings("serial")
@Entity
@Table(name = "ABC_SEO")
public class AbcSeo implements Serializable {

	private String seoId;
	private String enterpriseId;
	private BelongPage belongPage;
	private String title;
	private String keywords;
	private String description;

	public AbcSeo() {
	}

	public AbcSeo(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public AbcSeo(String enterpriseId, BelongPage page) {
		this.enterpriseId = enterpriseId;
		belongPage = page;
	}

	public AbcSeo(String enterpriseId, BelongPage page, String title) {
		this.enterpriseId = enterpriseId;
		belongPage = page;
		this.title = title;
	}

	@Id
	@GenericGenerator(name = "SEQ_Seo", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Seo"),
			@Parameter(name = "prefix", value = "Seo") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Seo")
	@Column(name = "seo_ID", unique = true, nullable = false, length = 64)
	public String getSeoId() {
		return seoId;
	}

	public void setSeoId(String seoId) {
		this.seoId = seoId;
	}

	@Column(name = "enterprise_id")
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "belong_page")
	public BelongPage getBelongPage() {
		return belongPage;
	}

	public void setBelongPage(BelongPage belongPage) {
		this.belongPage = belongPage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
