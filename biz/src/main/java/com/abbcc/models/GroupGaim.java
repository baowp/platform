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

import com.abbcc.models.annotation.SortProperty;
import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.group.GroupGaimType;

/**
 * GroupGaim entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GROUP_GAIM")
public class GroupGaim implements Serializable {

	// fields

	private String gaimId;
	private GroupGaimType type;
	private String enterpriseId;
	@SortProperty
	private Integer sort;
	private String display;
	@NotBlank(message = "请输入账号!")
	private String account;
	private String name;
	private String code;

	@Id
	@GenericGenerator(name = "SEQ_GROUP_GAIM", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GROUP_GAIM"),
			@Parameter(name = "prefix", value = "gaim") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GROUP_GAIM")
	@Column(name = "GAIM_ID", unique = true, nullable = false, length = 64)
	public String getGaimId() {
		return gaimId;
	}

	public void setGaimId(String gaimId) {
		this.gaimId = gaimId;
	}
	@Column(name="TYPE")
	@Enumerated(value = EnumType.STRING)
	public GroupGaimType getType() {
		return type;
	}
	public void setType(GroupGaimType type) {
		this.type = type;
	}


	@Column(name = "enterprise_id")
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
