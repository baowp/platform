package com.abbcc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import com.abbcc.models.annotation.SortProperty;
import com.abbcc.util.IDUtil;
/**
 * GroupAnalysis entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GROUP_USERDEFINED")
public class GroupUserdefined implements Serializable {

	
	//FIELDS
	
	private String userdefinedId;
	private String enterpriseId;
	private String moduleId;
	private String content;
	@NotBlank(message="请输入自定义标题")
	private String name;
	@SortProperty
	private Integer serial; 
	
	@Id
	@GenericGenerator(name = "SEQ_GROUP_USERDEFINE", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GROUP_USERDEFINED"),
			@Parameter(name = "prefix", value = "userdefine") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GROUP_USERDEFINE")
	@Column(name = "USERDEFINED_ID", unique = true, nullable = false, length = 64)
	public String getUserdefinedId() {
		return userdefinedId;
	}
	public void setUserdefinedId(String userdefinedId) {
		this.userdefinedId = userdefinedId;
	}
	@Column(name="enterprise_id")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	@Column(name="module_id")
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String Content) {
		this.content = Content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	
	
}
