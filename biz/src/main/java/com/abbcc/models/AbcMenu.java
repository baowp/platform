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

import com.abbcc.common.ConstHelper;
import com.abbcc.util.IDUtil;

/**
 * AbcMenu entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_MENU")
public class AbcMenu implements java.io.Serializable {

	// Fields

	private String menuId;
	private String mname;
	private String state;
	private Integer sort;
	private String action;
	private String moduleName;
	// Constructors

	/** default constructor */
	public AbcMenu() {
	}

	/** minimal constructor */
	public AbcMenu(String menuId) {
		this.menuId = menuId;
	}

	/** full constructor */
	public AbcMenu(String menuId, String mname,
			String state, Integer sort,String moduleName
			) {
		this.menuId = menuId;
		this.mname = mname;
		this.sort = sort;
		this.state = state;
		this.action=action;
		this.moduleName=moduleName;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_MENU", parameters = {
			@Parameter(name = "sequence", value = "seq_menu"),
			@Parameter(name = "prefix", value = "Menu") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_MENU")
	@Column(name = "MENU_ID", unique = true, nullable = false, length = 64)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "MNAME", length = 20)
	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}


	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name = "ACTION", length = 200)
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	@Column(name = "MODULE_NAME", length = 40)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}