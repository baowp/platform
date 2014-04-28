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

import com.abbcc.service.EnterpriseService;
import com.abbcc.service.SyscodeService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_AUDIT")
public class AbcAudit  implements java.io.Serializable{
	private String auditId;
	private String className;
	private String jsonContent;
	private String enterpriseId;

	private String state;
	private Date addTime;
	
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_AUDIT", parameters = {
			@Parameter(name = "sequence", value = "seq_audit"),
			@Parameter(name = "prefix", value = "audit") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_AUDIT")
	@Column(name = "AUDIT_ID", unique = true, nullable = false, length = 64)
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getJsonContent() {
		return jsonContent;
	}
	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}
	@Column(name="enterprise_id")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="add_time")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public AbcEnterprise getEnterprise(String entId){
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
		.get("enterpriseService");
		return enterpriseService.findById(entId);
	}
	public String businessTypeName(String type) {
		if (type != null) {
			if (type.equals("00"))
				return "生产加工";
			if (type.equals("01"))
				return "经营批发";
			if (type.equals("02"))
				return "招商代理";
			if (type.equals("03"))
				return "商业服务";
		}
		return "还未填写";
	}
	public AbcSyscode industry(String industryId){
		SyscodeService syscodeService = (SyscodeService) BeansFactory
		.get("syscodeService");
		return syscodeService.findById(industryId);
	}
}
