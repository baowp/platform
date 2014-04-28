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
 * AbcDomainbind entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_DOMAINBIND")
public class AbcDomainbind implements java.io.Serializable {

	// Fields

	private String domainbindId;
	private String enterpriseId;
	private String domain;
	private String ip;
	private String state;

	// Constructors

	/** default constructor */
	public AbcDomainbind() {
	}

	/** minimal constructor */
	public AbcDomainbind(String domainbindId) {
		this.domainbindId = domainbindId;
	}

	/** full constructor */
	public AbcDomainbind(String domainbindId, String enterpriseId,
			String domain, String ip, String state) {
		this.domainbindId = domainbindId;
		this.enterpriseId = enterpriseId;
		this.domain = domain;
		this.ip = ip;
		this.state = state;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Domainbind", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Domainbind"),
			@Parameter(name = "prefix", value = "Domainbind") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Domainbind")
	@Column(name = "DOMAINBIND_ID", unique = true, nullable = false, length = 64)
	public String getDomainbindId() {
		return this.domainbindId;
	}

	public void setDomainbindId(String domainbindId) {
		this.domainbindId = domainbindId;
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

	@Column(name = "IP", length = 30)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}