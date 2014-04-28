package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.service.EnterpriseService;
import com.abbcc.service.InquiryService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SyscodeService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_INQUIRY")
public class AbcInquiry implements java.io.Serializable {
	private String inquiryId;
	private String enterpriseId;
	private String title;
	private String productId;
	private Integer count;
	private Integer price;
	private String state;
	private Date addTime;
	private Date endTime;
	private String toKnow;
	private String added;
	private String userType;
	private String accessories;
	private String name;
	private String email;
	private String companyName;
	private String phone;
	private String recvEnt;
	private String type;

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_INQUIRY", parameters = {
			@Parameter(name = "sequence", value = "seq_inquiry"),
			@Parameter(name = "prefix", value = "inquiry") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_INQUIRY")
	@Column(name = "INQUIRY_ID", unique = true, nullable = false, length = 64)
	public String getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(String inquiryId) {
		this.inquiryId = inquiryId;
	}

	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "PRODUCT_ID")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "RECV_ENT")
	public String getRecvEnt() {
		return recvEnt;
	}

	public void setRecvEnt(String recvEnt) {
		this.recvEnt = recvEnt;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getToKnow() {
		return toKnow;
	}

	public void setToKnow(String toKnow) {
		this.toKnow = toKnow;
	}

	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}

	@Column(name = "USER_TYPE")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public String getInquiryName() {
		if (this.getUserType().equals("02")) {
			if (StringUtil.isNotBlank(this.getName()))
				return this.getName();
			else
				return "匿名";
		} else {
			EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
					.get("enterpriseService");
			UserService userService = (UserService) BeansFactory
					.get("userService");
			return userService.findById(
					enterpriseService.findById(enterpriseId).getUserId())
					.getName();
		}
	}

	public AbcProduct productName(String proId) {
		ProductService productService = (ProductService) BeansFactory
				.get("productService");
		return productService.findById(proId);
	}

	public AbcEnterprise ent() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		return enterpriseService.findById(this.getEnterpriseId());
	}

	public AbcUser user() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		return userService.findById(enterpriseService.findById(enterpriseId)
				.getUserId());
	}
}
