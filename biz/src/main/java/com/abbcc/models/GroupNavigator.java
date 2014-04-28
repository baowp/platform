package com.abbcc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONArray;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import com.abbcc.springrest.constraints.UserdefinedPage;
import com.abbcc.util.IDUtil;

/**
 * GroupNavigator entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GROUP_NAVIGATOR")
public class GroupNavigator implements Serializable {

	// Fields

	private String navigatorId;
	private String enterpriseId;
	@NotBlank(message = "请输入导航名称!")
	private String name;
	@UserdefinedPage
	private String page;
	private Integer grade;
	private String parentId;
	private Integer sort;
	private String display;
	private JSONArray subJson;
	
	private Integer childSize;


	@Id
	@GenericGenerator(name = "SEQ_GROUP_NAVIGATOR", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GROUP_NAVIGATOR"),
			@Parameter(name = "prefix", value = "navigator") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GROUP_NAVIGATOR")
	@Column(name = "NAVIGATOR_ID", unique = true, nullable = false, length = 64)
	public String getNavigatorId() {
		return navigatorId;
	}

	public void setNavigatorId(String navigatorId) {
		this.navigatorId = navigatorId;
	}

	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Column(name = "PARENT_ID")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Transient
	public JSONArray getSubJson() {
		return subJson;
	}
	
	public void setSubJson(JSONArray subJson) {
		this.subJson = subJson;
	}
	
	@Transient
	public Integer getChildSize() {
		return childSize;
	}
	
	public void setChildSize(Integer childSize) {
		this.childSize = childSize;
	}
}
