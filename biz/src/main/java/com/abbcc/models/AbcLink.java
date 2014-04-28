package com.abbcc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotBlank;

import com.abbcc.util.IDUtil;

/**
 * AbcLink entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_LINK")
public class AbcLink implements java.io.Serializable {

	// Fields

	private String linkId;
	private String enterpriseId;
	@NotBlank(message="请输入名称!")
	private String name;
	@NotBlank(message="请输入地址!")
	private String url;
	private String state;
	private Integer lorder;
	private String userId;

	// Constructors

	/** default constructor */
	public AbcLink() {
	}

	/** minimal constructor */
	public AbcLink(String linkId) {
		this.linkId = linkId;
	}

	/** full constructor */
	public AbcLink(String linkId, String enterpriseId, String name, String urr,
			String state, Integer lorder, String userId) {
		this.linkId = linkId;
		this.enterpriseId = enterpriseId;
		this.name = name;
		this.url = urr;
		this.state = state;
		this.lorder = lorder;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Link", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Link"),
			@Parameter(name = "prefix", value = "Link") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Link")
	@Column(name = "LINK_ID", unique = true, nullable = false, length = 64)
	public String getLinkId() {
		return this.linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "URL", length = 400)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String urr) {
		this.url = urr;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "LORDER")
	public Integer getLorder() {
		return this.lorder;
	}

	public void setLorder(Integer lorder) {
		this.lorder = lorder;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}