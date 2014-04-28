package com.abbcc.models;

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

import com.abbcc.service.BaseService;
import com.abbcc.service.MenuService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcMenu entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_CUSTOM")
public class AbcCustom implements java.io.Serializable {

	// Fields

	private String customId;
	private String enterpriseId;
	private String menuId;
	private String name;
	private String state;
	private Integer sort;
	private String display;
	private String action;
	private String moduleName;

	// Constructors

	/** default constructor */
	public AbcCustom() {
	}

	/** minimal constructor */
	public AbcCustom(String customId) {
		this.customId = customId;
	}

	/** full constructor */
	public AbcCustom(String customId, String enterpriseId, String menuId,
			String name, String state, Integer sort, String display,
			String action, String moduleName) {
		this.customId = customId;
		this.enterpriseId = enterpriseId;
		this.sort = sort;
		this.state = state;
		this.menuId = menuId;
		this.name = name;
		this.display = display;
		this.action = action;
		this.moduleName = moduleName;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_CUSTOM", parameters = {
			@Parameter(name = "sequence", value = "SEQ_CUSTOM"),
			@Parameter(name = "prefix", value = "Custom") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_CUSTOM")
	@Column(name = "CUSTOM_ID", unique = true, nullable = false, length = 64)
	public String getCustomId() {
		return this.customId;
	}

	public void setCustomId(String customId) {
		this.customId = customId;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "DISPLAY", length = 2)
	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Column(name = "MENU_ID", length = 64)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public String oldName() {
		MenuService menuService =(MenuService) BeansFactory.get("menuService");
		return menuService.findById(this.getMenuId()).getMname();
	}
	public String isCheck(String entId,String menuId){
		BaseService baseService =(BaseService) BeansFactory
		.get("baseService");
		AbcCustom ac = new AbcCustom();
		ac.setEnterpriseId(entId);
		ac.setMenuId(menuId);
		List<AbcCustom> list = baseService.findByExample(ac);
		if(list.size()!=0)
			return "01";
		return "02";
		
	}
}