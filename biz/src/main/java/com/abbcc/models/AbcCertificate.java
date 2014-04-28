package com.abbcc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.service.AttachmentService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;

/**
 * AbcCertificate entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_CERTIFICATE")
public class AbcCertificate implements java.io.Serializable {

	// Fields

	private String certificateId;
	private String enterpriseId;
	private String name;
	private String organize;
	private String attachmentId;
	private String state;
	private String type;
	private Date addTime;
	private Integer sort;
	private String domain;
	// Constructors

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/** default constructor */
	public AbcCertificate() {
	}

	/** minimal constructor */
	public AbcCertificate(String certificateId) {
		this.certificateId = certificateId;
	}

	/** full constructor */
	public AbcCertificate(String certificateId, String enterpriseId,
			String name, String organize, String state, String type,
			String attachmentId,Date addTime) {
		this.certificateId = certificateId;
		this.enterpriseId = enterpriseId;
		this.name = name;
		this.organize = organize;
		this.state = state;
		this.type = type;
		this.attachmentId = attachmentId;
		this.addTime=addTime;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Certificate", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Certificate"),
			@Parameter(name = "prefix", value = "Certificate") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Certificate")
	@Column(name = "CERTIFICATE_ID", unique = true, nullable = false, length = 64)
	public String getCertificateId() {
		return this.certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "NAME", length = 400)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ORGANIZE", length = 400)
	public String getOrganize() {
		return this.organize;
	}

	public void setOrganize(String organize) {
		this.organize = organize;
	}


	@Column(name = "ATTACHMENT_ID", length = 64)
	public String getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String picUrlPath(){
		AttachmentService attachmentService = (AttachmentService)BeansFactory.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(this.getCertificateId());
		List list = attachmentService.findByExample(attachment);
		AbcAttachment att = (AbcAttachment)list.iterator().next();
		return  att.getServerPath();
	}	

	public String picUrl(){
		AttachmentService attachmentService = (AttachmentService)BeansFactory.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(this.getCertificateId());
		List list = attachmentService.findByExample(attachment);
		AbcAttachment att = (AbcAttachment)list.iterator().next();
		return  att.picUrl();
	}
	
	public String picUrl(int i){
		AttachmentService attachmentService = (AttachmentService)BeansFactory.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(this.getCertificateId());
		List list = attachmentService.findByExample(attachment);
		AbcAttachment att = (AbcAttachment)list.iterator().next();
		return  att.picUrl(i);
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public AbcEnterprise enterprise() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		return enterpriseService.findById(this.getEnterpriseId());
	}

	public String addTimeString() {
		return DateUtil.formatDate(this.getAddTime(), "yyyy-MM-dd");
	}

	
}