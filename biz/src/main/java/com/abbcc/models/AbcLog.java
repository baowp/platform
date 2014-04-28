package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.common.CommonConst;
import com.abbcc.service.AdminService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcLog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_LOG")
public class AbcLog implements java.io.Serializable {

	// Fields

	private String logId;
	private String type;
	private String isadmin;
	private String class_;
	private String userId;
	private String enterpriseId;
	private String name;
	private String ldesc;
	private Date addTime;
	private String state;
	private String ip;
	private String location;
	private String domain;
	// Constructors




	/** default constructor */
	public AbcLog() {
	}

	/** minimal constructor */
	public AbcLog(String logId) {
		this.logId = logId;
	}

	/** full constructor */
	public AbcLog(String logId, String type, String isadmin, String class_,
			String userId, String name, String ldesc, Date addTime,
			String state, String ip,String enterpriseId) {
		this.logId = logId;
		this.type = type;
		this.isadmin = isadmin;
		this.class_ = class_;
		this.userId = userId;
		this.name = name;
		this.ldesc = ldesc;
		this.addTime = addTime;
		this.state = state;
		this.ip = ip;
		this.enterpriseId = enterpriseId;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Log", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Log"),
			@Parameter(name = "prefix", value = "Log") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Log")
	@Column(name = "LOG_ID", unique = true, nullable = false, length = 64)
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ISADMIN", length = 2)
	public String getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

	@Column(name = "CLASS", length = 2)
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "NAME", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LDESC", length = 800)
	public String getLdesc() {
		return this.ldesc;
	}

	public void setLdesc(String ldesc) {
		this.ldesc = ldesc;
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

	@Column(name = "IP", length = 32)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String userName(){
	UserService userService = (UserService)BeansFactory.get("userService");
	AbcUser user = userService.findById(this.getUserId());
	if(user!=null)
		return user.getName();
	else{
		AdminService adminService = (AdminService)BeansFactory.get("adminService");
		AbcAdmin admin = adminService.findById(this.getUserId());
		return admin.getName();
	}
	}
	public String logType(){
		if(this.getType().equals(CommonConst.LOGDEL))
			return "删除";
		if(this.getType().equals(CommonConst.LOGSAVE))
			return "添加";
		if(this.getType().equals(CommonConst.LOGUPDATE))
			return "修改";
		if(this.getType().equals(CommonConst.LOGLOGIN))
			return "登陆系统";
		if(this.getType().equals(CommonConst.LOGLOGOUT))
			return "退出系统";
		if(this.getType().equals(CommonConst.LOGRESEND))
			return "重发";
		return "";
	}


}