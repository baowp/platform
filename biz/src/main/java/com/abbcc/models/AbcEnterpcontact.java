package com.abbcc.models;

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
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcEnterpcontact entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_ENTERPCONTACT")
public class AbcEnterpcontact implements java.io.Serializable {

	// Fields

	private String enterpcontactId;
	private String enterpriseId;
	private String adduserId;
	private String name;
	private String gender;
	private String position;
	private String phone;
	private String fax;
	private String cellphone;
	private String url;
	private String email;
	private String state;
	private String sex;
	private Integer sort;
	
	private String address;
	
	// Constructors



	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/** default constructor */
	public AbcEnterpcontact() {
	}

	/** minimal constructor */
	public AbcEnterpcontact(String enterpcontactId) {
		this.enterpcontactId = enterpcontactId;
	}

	/** full constructor */
	public AbcEnterpcontact(String enterpcontactId, String enterpriseId,
			String adduserId, String name, String gender, String position,
			String phone, String fax, String cellphone, String url, String email) {
		this.enterpcontactId = enterpcontactId;
		this.enterpriseId = enterpriseId;
		this.adduserId = adduserId;
		this.name = name;
		this.gender = gender;
		this.position = position;
		this.phone = phone;
		this.fax = fax;
		this.cellphone = cellphone;
		this.url = url;
		this.email = email;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Enterpcontact", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Enterpcontact"),
			@Parameter(name = "prefix", value = "Enterpco") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Enterpcontact")
	@Column(name = "ENTERPCONTACT_ID", unique = true, nullable = false, length = 64)
	public String getEnterpcontactId() {
		return this.enterpcontactId;
	}

	public void setEnterpcontactId(String enterpcontactId) {
		this.enterpcontactId = enterpcontactId;
	}
	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "ADDUSER_ID", length = 64)
	public String getAdduserId() {
		return this.adduserId;
	}

	public void setAdduserId(String adduserId) {
		this.adduserId = adduserId;
	}

	@Column(name = "NAME", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GENDER", length = 2)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "POSITION", length = 60)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "PHONE", length = 40)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "FAX", length = 40)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "CELLPHONE", length = 24)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "URL", length = 400)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 联系人的企业
	 * @return
	 */
	public AbcEnterprise contactEnterprise(){
		EnterpriseService enterpriseService=(EnterpriseService)BeansFactory.get("enterpriseService");
		return enterpriseService.findById(this.enterpriseId);
	}
	public String sexName(){
		if(this.getSex().equals("00"))
			return "男";
		if(this.getSex().equals("01"))
			return "女";
		return "";
	}

	@Transient
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}