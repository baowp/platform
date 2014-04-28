package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.common.CommonConst;
import com.abbcc.service.AdminService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcLog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_STAT")
public class AbcStat implements java.io.Serializable {

	// Fields

	@SuppressWarnings("unused")
	private String statId;
	private String enterpriseId;
	@SuppressWarnings("unused")
	private int loginfrequency;
	private int newsfrequency;
	private int productfrequency;
	private int jobfrequency;
	private int certfrequency;
	private int supplyfrequency;
	

	private Date lastAddTime;

	// Constructors

	/** default constructor */
	public AbcStat() {
	}

	/** minimal constructor */
	public AbcStat(String statId) {
		this.statId = statId;
	}

	/** full constructor */
	public AbcStat(String statId,  int loginfrequency,int newsfrequency,int productfrequency,int jobfrequency,int certfrequency,int supplyfrequency, 
			 Date lastAddTime,
			String enterpriseId) {
		this.statId = statId;
		this.loginfrequency = loginfrequency;
		this.newsfrequency = newsfrequency;
		this.productfrequency = productfrequency;
		this.jobfrequency = jobfrequency;
		this.certfrequency = certfrequency;
		this.supplyfrequency = supplyfrequency;
		this.lastAddTime = lastAddTime;
		this.enterpriseId = enterpriseId;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Stat", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Stat"),
			@Parameter(name = "prefix", value = "stat") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Stat")
	@Column(name = "STAT_ID", unique = true, nullable = false, length = 64)
	public String getStatId() {
		return this.statId;
	}

	public void setStatId(String statId) {
		this.statId = statId;
	}
	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "LAST_ADD_TIME")
	public Date getLastAddTime() {
		return lastAddTime;
	}

	public void setLastAddTime(Date lastAddTime) {
		this.lastAddTime = lastAddTime;
	}
	@Column(name = "LOGINFREQUENCY")
	public int getLoginfrequency() {
		return loginfrequency;
	}

	public void setLoginfrequency(int loginfrequency) {
		this.loginfrequency = loginfrequency;
	}
	@Column(name = "NEWSFREQUENCY")
	public int getNewsfrequency() {
		return newsfrequency;
	}

	public void setNewsfrequency(int newsfrequency) {
		this.newsfrequency = newsfrequency;
	}
	@Column(name = "PRODUCTFREQUENCY")
	public int getProductfrequency() {
		return productfrequency;
	}

	public void setProductfrequency(int productfrequency) {
		this.productfrequency = productfrequency;
	}
	@Column(name = "JOBFREQUENCY")
	public int getJobfrequency() {
		return jobfrequency;
	}

	public void setJobfrequency(int jobfrequency) {
		this.jobfrequency = jobfrequency;
	}
	@Column(name = "CERTFREQUENCY")
	public int getCertfrequency() {
		return certfrequency;
	}

	public void setCertfrequency(int certfrequency) {
		this.certfrequency = certfrequency;
	}
	@Column(name = "SUPPLYFREQUENCY")
	public int getSupplyfrequency() {
		return supplyfrequency;
	}

	public void setSupplyfrequency(int supplyfrequency) {
		this.supplyfrequency = supplyfrequency;
	}
	

}