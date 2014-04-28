package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.abbcc.common.ConstHelper;
import com.abbcc.util.IDUtil;

/**
 * AbcAdmin entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_ADMIN")
public class AbcAdmin implements java.io.Serializable {

	// Fields

	private String adminId;
	@Field(name = "username", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String username;
	private String password;
	private String type;
	private String state;
	private Date addTime;
	private String name;
	private String email;
	private String gender;
	private String cellphone;
	private String adesc;
	private String domain;

	// Constructors



	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	/** default constructor */
	public AbcAdmin() {
	}

	/** minimal constructor */
	public AbcAdmin(String adminId) {
		this.adminId = adminId;
	}

	/** full constructor */
	public AbcAdmin(String adminId, String username, String password,
			String type, String state, Date addTime, String name,
			String email, String gender,String cellphone, String adesc) {
		this.adminId = adminId;
		this.username = username;
		this.password = password;
		this.type = type;
		this.state = state;
		this.addTime = addTime;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.cellphone = cellphone;
		this.adesc = adesc;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_ADMIN", parameters = {
			@Parameter(name = "sequence", value = "seq_admin"),
			@Parameter(name = "prefix", value = "Admin") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_ADMIN")
	@Column(name = "ADMIN_ID", unique = true, nullable = false, length = 64)
	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	@Column(name = "USERNAME", length = 40)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "GENDER", length = 2)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "CELLPHONE", length = 22)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "ADESC", length = 200)
	public String getAdesc() {
		return this.adesc;
	}

	public void setAdesc(String adesc) {
		this.adesc = adesc;
	}

	public String genderName(){
		return ConstHelper.parseGender(this.getGender());
	}
	
	public String adminTypename(){
		return ConstHelper.parseAdmintype(this.getType());
	}
	public String adminStatename(){
		return ConstHelper.parseStatetype(this.getState());
	}

}