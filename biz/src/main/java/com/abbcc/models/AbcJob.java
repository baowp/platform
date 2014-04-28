package com.abbcc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.abbcc.service.CategoryService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

/**
 * AbcJob entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_JOB")
public class AbcJob implements java.io.Serializable {

	// Fields

	private String jobId;
	private String type;
	private String enterpriseId;
	private String addUser;
	private Date addTime;
	@Field(name = "title", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String title;
	@Field(name = "content", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String content;
	private Date endTime;
	private String state;
	private Date auditTime;
	private String auditAdmin;
	private String industory;
	private String sum;
	private String duty;
	private String category;
	private String domain;
	private String address;
	// Constructors


	/** default constructor */
	public AbcJob() {
	}

	/** minimal constructor */
	public AbcJob(String jobId) {
		this.jobId = jobId;
	}

	/** full constructor */
	public AbcJob(String jobId, String type, String enterpriseId,
			String addUser, Date addTime, String title, String content,
			Date endTime, String state, Date auditTime, String auditAdmin,
			String industory, String sum, String duty) {
		this.jobId = jobId;
		this.type = type;
		this.enterpriseId = enterpriseId;
		this.addUser = addUser;
		this.addTime = addTime;
		this.title = title;
		this.content = content;
		this.endTime = endTime;
		this.state = state;
		this.auditTime = auditTime;
		this.auditAdmin = auditAdmin;
		this.industory = industory;
		this.sum = sum;
		this.duty = duty;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Job", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Job"),
			@Parameter(name = "prefix", value = "Job") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Job")
	@Column(name = "JOB_ID", unique = true, nullable = false, length = 64)
	public String getJobId() {
		return this.jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "ADD_USER", length = 64)
	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "TITLE", length = 400)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "END_TIME")
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	@Column(name = "AUDIT_ADMIN", length = 64)
	public String getAuditAdmin() {
		return this.auditAdmin;
	}

	public void setAuditAdmin(String auditAdmin) {
		this.auditAdmin = auditAdmin;
	}

	@Column(name = "INDUSTORY", length = 64)
	public String getIndustory() {
		return this.industory;
	}

	public void setIndustory(String industory) {
		this.industory = industory;
	}

	@Column(name = "SUM", length = 16)
	public String getSum() {
		return this.sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	@Column(name = "DUTY", length = 200)
	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String endTimeString() {
		if (this.getEndTime() == null)
			return "";
		return DateUtil.formatDate(this.getEndTime(), "yyyy-MM-dd");

	}

	public AbcEnterprise enterprise() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		return enterpriseService.findById(this.getEnterpriseId());
	}

	public String addTimeString() {
		return DateUtil.formatDate(this.getAddTime(), "yyyy-MM-dd");
	}
	public String htmlPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		return sdf.format(this.getAddTime()) + this.getJobId() + ".html";
	}
	public String categoryName(){
		CategoryService categoryService = (CategoryService) BeansFactory
		.get("categoryService");
		if(StringUtil.isNotBlank(this.getCategory())){
			return categoryService.findById(this.getCategory()).getName();
		}
		return "";
	}

}