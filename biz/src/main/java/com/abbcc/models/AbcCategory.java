package com.abbcc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.transform.Transformers;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.service.CategoryService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

/**
 * AbcCategory entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_CATEGORY")
public class AbcCategory implements java.io.Serializable {

	// Fields

	private String categoryId;
	private String enterpriseId;
	private String type;
	private String adduserId;
	private String belongId;
	private String name;
	private String cdesc;
	private String grade;
	private String isroot;
	private Date addTime;
	private String isdisplay;
	private String state;
	private Integer sort;

	private String image;

	@Column(name = "image", length = 150)
	public String getImage() {
		return image;
	}

	@Transient
	public String getImage2() {
		if (!StringUtil.isBlank(image)) {
			return "<img src=" + ConfConst.FILE_SVR + image + " />";
		} else {
			return null;
		}
	}

	public void setImage(String image) {
		this.image = image;
	}

	private List<AbcCategory> sonCate;
	private boolean ifLeaf;

	// Constructors

	/** default constructor */
	public AbcCategory() {
	}

	/** minimal constructor */
	public AbcCategory(String categoryId) {
		this.categoryId = categoryId;
	}

	/** full constructor */
	public AbcCategory(String categoryId, String enterpriseId, String type,
			String adduserId, String belongId, String name, String cdesc,
			String grade, String isroot, Date addTime, String isdisplay,
			String state) {
		this.categoryId = categoryId;
		this.enterpriseId = enterpriseId;
		this.type = type;
		this.adduserId = adduserId;
		this.belongId = belongId;
		this.name = name;
		this.cdesc = cdesc;
		this.grade = grade;
		this.isroot = isroot;
		this.addTime = addTime;
		this.isdisplay = isdisplay;
		this.state = state;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Category", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Category"),
			@Parameter(name = "prefix", value = "Category") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Category")
	@Column(name = "CATEGORY_ID", unique = true, nullable = false, length = 64)
	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "TYPE", length = 16)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ADDUSER_ID", length = 64)
	public String getAdduserId() {
		return this.adduserId;
	}

	public void setAdduserId(String adduserId) {
		this.adduserId = adduserId;
	}

	@Column(name = "BELONG_ID", length = 64)
	public String getBelongId() {
		return this.belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CDESC", length = 400)
	public String getCdesc() {
		return this.cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	@Column(name = "GRADE", length = 2)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "ISROOT", length = 2)
	public String getIsroot() {
		return this.isroot;
	}

	public void setIsroot(String isroot) {
		this.isroot = isroot;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "ISDISPLAY", length = 2)
	public String getIsdisplay() {
		return this.isdisplay;
	}

	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}

	@Column(name = "STATE", length = 4)
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

	public String rootCategory() {
		if (this.getBelongId() == null || this.getBelongId() == "") {
			return "top";
		}
		CategoryService categoryService = (CategoryService) BeansFactory
				.get("categoryService");
		return categoryService.findById(this.getBelongId()).getName();
	}

	@Transient
	public List<AbcCategory> getSonCate() {
		return sonCate;
	}

	public void setSonCate(List<AbcCategory> sonCate) {
		this.sonCate = sonCate;
	}

	public AbcEnterprise enterprise() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		return enterpriseService.findById(this.getEnterpriseId());
	}

	public List<AbcCategory> subCategory() {
		return subCategory(Property.forName("sort").asc());
	}

	public List<AbcCategory> subCategory2() {
		return subCategory(Property.forName("sort").desc());
	}

	public List<AbcCategory> subCategory3() {
		return subCategory(Property.forName("sort").asc(), "categoryId",
				("name"), "image", "type");
	}

	public List<AbcCategory> subCategory4() {
		return subCategory(Property.forName("sort").desc(), ("categoryId"),
				("name"), "type");
	}

	@SuppressWarnings("unchecked")
	private List<AbcCategory> subCategory(Order order, String... properties) {
		CategoryService categoryService = (CategoryService) BeansFactory
				.get("categoryService");
		AbcCategory c = new AbcCategory();
		c.setState(CommonConst.STATENORMAL);
		c.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		c.setBelongId(this.getCategoryId());
		DetachedCriteria dc = DetachedCriteria.forClass(AbcCategory.class);
		dc.add(Example.create(c)).addOrder(order);
		if (properties.length > 0) {
			ProjectionList projectionList = Projections.projectionList();
			for (String p : properties)
				projectionList.add(Projections.property(p), p);
			dc.setProjection(projectionList);
			dc.setResultTransformer(Transformers.aliasToBean(getClass()));
		}
		return categoryService.findAllByCriteria(dc);
	}

	public String addTimeString() {
		return DateUtil.formatDate(this.getAddTime(), "yyyy-MM-dd");
	}

	public AbcUser addUser() {
		UserService userService = (UserService) BeansFactory.get("userService");
		return userService.findById(this.getAdduserId());
	}

	@Transient
	public boolean isIfLeaf() {
		return ifLeaf;
	}

	public void setIfLeaf(boolean ifLeaf) {
		this.ifLeaf = ifLeaf;
	}

}