package com.abbcc.models;

import java.util.Date;

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
 * AbcDomainreg entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_DOMAINREG")
public class AbcDomainreg implements java.io.Serializable {

	// Fields

	private String domainregId;
	private String enterpriseId;
	private String domain;
	private Date applyTime;
	private String isreged;
	private String state;
	private Date auditTime;
	private String payId;

	// Constructors

	/** default constructor */
	public AbcDomainreg() {
	}

	/** minimal constructor */
	public AbcDomainreg(String domainregId) {
		this.domainregId = domainregId;
	}

	/** full constructor */
	public AbcDomainreg(String domainregId, String enterpriseId, String domain,
			Date applyTime, String isreged, String state, Date auditTime,
			String payId) {
		this.domainregId = domainregId;
		this.enterpriseId = enterpriseId;
		this.domain = domain;
		this.applyTime = applyTime;
		this.isreged = isreged;
		this.state = state;
		this.auditTime = auditTime;
		this.payId = payId;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Domainreg", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Domainreg"),
			@Parameter(name = "prefix", value = "Domainreg") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Domainreg")
	@Column(name = "DOMAINREG_ID", unique = true, nullable = false, length = 64)
	public String getDomainregId() {
		return this.domainregId;
	}

	public void setDomainregId(String domainregId) {
		this.domainregId = domainregId;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "DOMAIN", length = 100)
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "APPLY_TIME")
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "ISREGED", length = 1)
	public String getIsreged() {
		return this.isreged;
	}

	public void setIsreged(String isreged) {
		this.isreged = isreged;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "AUDIT_TIME")
	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "PAY_ID", length = 64)
	public String getPayId() {
		return this.payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

}