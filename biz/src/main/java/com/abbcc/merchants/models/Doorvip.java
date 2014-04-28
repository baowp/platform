package com.abbcc.merchants.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.common.CommonConst;
import com.abbcc.service.AdminService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

/**
 * AbcLog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "DOORVIP")
public class Doorvip implements java.io.Serializable {

	// Fields

	@SuppressWarnings("unused")
	private String userId;
	private String username;
	private String password;
	private String question;
	private String answer;
	private String email;
	private String sex;
	private String companyName;
	private String address;
	private String postcode;
	private String receiver;
	private String phone;
	private String mobile;
	private String fax;
	private String usertype;
	private Date regDate;	
	private String loginIP;
	private Integer logins;
	private String homepage;
	private Date lastLoginTime;
	private String lockUser;
	private String qixing;
	

	

	

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_DOORVIP", parameters = {
			@Parameter(name = "sequence", value = "SEQ_DOORVIP"),
			@Parameter(name = "prefix", value = "vip") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_DOORVIP")
	@Column(name = "USERID", unique = true, nullable = false, length = 64)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public Integer getLogins() {
		return logins;
	}

	public void setLogins(Integer logins) {
		this.logins = logins;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLockUser() {
		return lockUser;
	}

	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}

	public String getQixing() {
		return qixing;
	}

	public void setQixing(String qixing) {
		this.qixing = qixing;
	}
	public String stateName1(){
		if(this.getLockUser().trim().equalsIgnoreCase("TRUE"))
			return "正常使用";
		else if(this.getLockUser().trim().equalsIgnoreCase("FALSE"))
			return "用户锁定";
		else return "";
	}
	

}