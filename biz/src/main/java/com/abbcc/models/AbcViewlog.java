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

import com.abbcc.util.IDUtil;

/**
 * AbcViewlog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_VIEWLOG")
public class AbcViewlog implements java.io.Serializable {

	// Fields

	private String viewlogId;
	private String beviewId;
	private String userId;
	private Date viewTime;
	private String ip;
	private String viewtype;
	private String username;
	private String location;

	// Constructors

	/** default constructor */
	public AbcViewlog() {
	}

	/** minimal constructor */
	public AbcViewlog(String viewlogId) {
		this.viewlogId = viewlogId;
	}

	/** full constructor */
	public AbcViewlog(String viewlogId, String beviewId, String userId,
			Date viewTime, String ip, String viewtype, String username, String location) {
		this.viewlogId = viewlogId;
		this.beviewId = beviewId;
		this.userId = userId;
		this.viewTime = viewTime;
		this.ip = ip;
		this.viewtype = viewtype;
		this.username = username;
		this.location = location;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Viewlog", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Viewlog"),
			@Parameter(name = "prefix", value = "Viewlog") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Viewlog")
	@Column(name = "VIEWLOG_ID", unique = true, nullable = false, length = 64)
	public String getViewlogId() {
		return this.viewlogId;
	}

	public void setViewlogId(String viewlogId) {
		this.viewlogId = viewlogId;
	}

	@Column(name = "BEVIEW_ID", length = 64)
	public String getBeviewId() {
		return this.beviewId;
	}

	public void setBeviewId(String beviewId) {
		this.beviewId = beviewId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "VIEW_TIME")
	public Date getViewTime() {
		return this.viewTime;
	}

	public void setViewTime(Date viewTime) {
		this.viewTime = viewTime;
	}

	@Column(name = "IP", length = 64)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "VIEWTYPE", length = 2)
	public String getViewtype() {
		return this.viewtype;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	@Column(name = "USERNAME", length = 64)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "LOCATION", length = 64)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


}