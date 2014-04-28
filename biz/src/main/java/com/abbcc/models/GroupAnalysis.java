package com.abbcc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.group.GroupAnalysisType;

/**
 * GroupAnalysis entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GROUP_ANALYSIS")
public class GroupAnalysis implements Serializable {

	//FIELDS
	
	private String analysisId;
	private GroupAnalysisType type;
	private String enterpriseId;
	private String code;
	private String display;
	
	
	@Id
	@GenericGenerator(name = "SEQ_GROUP_ANALYSIS", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GROUP_ANALYSIS"),
			@Parameter(name = "prefix", value = "analysis") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GROUP_ANALYSIS")
	@Column(name = "ANALYSIS_ID", unique = true, nullable = false, length = 64)
	public String getAnalysisId() {
		return analysisId;
	}
	public void setAnalysisId(String analysisId) {
		this.analysisId = analysisId;
	}
	
	@Column(name="TYPE")
	@Enumerated(value = EnumType.STRING)
	public GroupAnalysisType getType() {
		return type;
	}
	public void setType(GroupAnalysisType type) {
		this.type = type;
	}
	@Column(name="ENTERPRISE_ID" )
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	
	
}
