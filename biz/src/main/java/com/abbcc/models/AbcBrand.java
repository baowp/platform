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

import com.abbcc.util.IDUtil;

/**
 * AbcBrand entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_BRAND")
public class AbcBrand implements java.io.Serializable {

	// Fields

	private String brandId;
	private String enterpriseId;
	private String adduserId;
	private String name;
	private String industry;
	private String state;

	// Constructors

	/** default constructor */
	public AbcBrand() {
	}

	/** minimal constructor */
	public AbcBrand(String brandId) {
		this.brandId = brandId;
	}

	/** full constructor */
	public AbcBrand(String brandId, String enterpriseId, String adduserId,
			String name, String industry, String state) {
		this.brandId = brandId;
		this.enterpriseId = enterpriseId;
		this.adduserId = adduserId;
		this.name = name;
		this.industry = industry;
		this.state = state;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Brand", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Brand"),
			@Parameter(name = "prefix", value = "Brand") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Brand")
	@Column(name = "BRAND_ID", unique = true, nullable = false, length = 64)
	public String getBrandId() {
		return this.brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "ADDUSER_ID", length = 64)
	public String getAdduserId() {
		return this.adduserId;
	}

	public void setAdduserId(String adduserId) {
		this.adduserId = adduserId;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "INDUSTRY", length = 64)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "STATE", length = 64)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}