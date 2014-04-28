/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AbcBind.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-8-5           baowp                      initial
 */

package com.abbcc.models;

import java.io.Serializable;
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

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.BindType;

@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_BIND")
public class AbcBind implements Serializable {

	private String bindId;
	private String username;
	private String address;
	private String icp;
	private Date applyTime;
	private Date approveTime;
	private String denyReason;
	private String fileName;
	private String state;
	private BindType type;

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Bind", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Bind"),
			@Parameter(name = "prefix", value = "Bind") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Bind")
	@Column(name = "Bind_ID", unique = true, nullable = false, length = 64)
	public String getBindId() {
		return bindId;
	}

	public void setBindId(String bindId) {
		this.bindId = bindId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	@Column(name = "apply_time")
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "approve_time")
	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@Column(name = "deny_reason")
	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	@Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Enumerated(EnumType.STRING)
	public BindType getType() {
		return type;
	}

	public void setType(BindType type) {
		this.type = type;
	}

}
