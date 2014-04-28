package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.TailorType;

@Entity
@Table(name = "Abc_TAILOR")
public class AbcTailor {
	private String tailorId;
	private String userId;
	private String type;
	private String content;
	private String enterpriseId;
	private Date addTime;
	private String state;

	@Id
	@GenericGenerator(name = "SEQ_Tailor", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Tailor"),
			@Parameter(name = "prefix", value = "tailor") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Tailor")
	@Column(name = "TAILOR_ID", unique = true, nullable = false, length = 64)
	public String getTailorId() {
		return tailorId;
	}

	public void setTailorId(String tailorId) {
		this.tailorId = tailorId;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
