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
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;

/**
 * AbcFavourite entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_FAVOURITE")
public class AbcFavourite implements java.io.Serializable {

	// Fields

	private String favId;
	private String userId;
	private Date addTime;
	private String state;
	private ModelType belongType;
	private String belongId;
	private String content;
	private String category;

	// Constructors

	/** default constructor */
	public AbcFavourite() {
	}

	/** minimal constructor */
	public AbcFavourite(String favId) {
		this.favId = favId;
	}

	/** full constructor */
	public AbcFavourite(String favId, String userId, Date addTime,
			String state, ModelType belongType, String belongId,
			String content, String category) {
		this.favId = favId;
		this.userId = userId;
		this.addTime = addTime;
		this.state = state;
		this.belongType = belongType;
		this.belongId = belongId;
		this.content = content;
		this.category = category;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Favourite", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Favourite"),
			@Parameter(name = "prefix", value = "Favou") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Favourite")
	@Column(name = "FAV_ID", unique = true, nullable = false, length = 64)
	public String getFavId() {
		return this.favId;
	}

	public void setFavId(String favId) {
		this.favId = favId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BELONG_TYPE", length = 2)
	public ModelType getBelongType() {
		return this.belongType;
	}

	public void setBelongType(ModelType belongType) {
		this.belongType = belongType;
	}

	@Column(name = "BELONG_ID", length = 64)
	public String getBelongId() {
		return this.belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	@Column(name = "CONTENT", length = 800)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CATEGORY", length = 64)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public AbcProduct product() {
		try {
			BaseService baseService = (BaseService) BeansFactory
					.get("baseService");
			if (StringUtil.isNotBlank(belongId)) {
				AbcProduct ap = (AbcProduct) baseService.findById(
						AbcProduct.class, belongId);
				return ap;
			}

		} catch (Exception e) {
			AbcProduct a = new AbcProduct();
			a.setName("此产品已删除!");
			return a;
		}
		return null;
	}

	public AbcEnterprise ent() {
		try {
			BaseService baseService = (BaseService) BeansFactory
					.get("baseService");
			if (StringUtil.isNotBlank(belongId)) {
				AbcEnterprise ap = (AbcEnterprise) baseService.findById(
						AbcEnterprise.class, belongId);
				ap.setAddress(new AbcEnterprise().entAddress(ap.getEnterpriseId()));
				return ap;
			}

		} catch (Exception e) {
			AbcEnterprise a = new AbcEnterprise();
			a.setName("此公司已删除!");
			return a;
		}
		return null;
	}

	public AbcUser userByProId() {
		BaseService baseService = (BaseService) BeansFactory.get("baseService");
		if (StringUtil.isNotBlank(belongId)) {
			AbcUser ap = (AbcUser) baseService.findById(AbcUser.class,
					((AbcEnterprise) baseService.findById(AbcEnterprise.class,
							((AbcProduct) baseService.findById(
									AbcProduct.class, belongId))
									.getEnterpriseId())).getUserId());
			return ap;
		}
		return null;
	}
	public AbcEnterprise entByProId() {
		BaseService baseService = (BaseService) BeansFactory.get("baseService");
		if (StringUtil.isNotBlank(belongId)) {
			AbcEnterprise ap = (AbcEnterprise) baseService.findById(AbcEnterprise.class,
							((AbcProduct) baseService.findById(
									AbcProduct.class, belongId)).getEnterpriseId());
			return ap;
		}
		return null;
	}
	public int collectNumber() {
		BaseService baseService = (BaseService) BeansFactory.get("baseService");
		AbcFavourite af = new AbcFavourite();
		af.setBelongId(belongId);
		return baseService.findByExample(af).size();

	}
}