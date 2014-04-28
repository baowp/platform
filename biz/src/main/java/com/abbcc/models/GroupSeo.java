package com.abbcc.models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;

@Entity
@Table(name = "GROUP_SEO")
public class GroupSeo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String description;
	private String enterpriseId;
	private String keywords;
	private String page;
	private String seoId;
	private String title;

	@Id
	@GenericGenerator(name = "SEQ_GROUP_SEO", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GROUP_SEO"),
			@Parameter(name = "prefix", value = "GSeo") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GROUP_SEO")
	@Column(name = "SEO_ID", unique = true, nullable = false, length = 64)
	public String getSeoId() {
		return this.seoId;
	}

	public void setSeoId(String seoId) {
		this.seoId = seoId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}