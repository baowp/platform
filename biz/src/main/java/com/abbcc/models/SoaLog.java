/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SoaWebserver.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-29           baowp                      initial
 */

package com.abbcc.models;

import java.io.Serializable;
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

@SuppressWarnings("serial")
@Indexed(index="soa")
@Entity
@Table(name = "SOA_LOG")
public class SoaLog implements Serializable {

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_SOA_LOG", parameters = {
			@Parameter(name = "sequence", value = "SEQ_SOA_LOG"),
			@Parameter(name = "prefix", value = "log") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_SOA_LOG")
	@Column(name = "LOG_id", unique = true, nullable = false, length = 64)
	private String logId;
	private String type;
	@Column(name = "oper_id")
	private String operId;
	private String name;
	private String ldesc;
	@Column(name = "add_time")
	private Date addTime;
	private String state;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLdesc() {
		return ldesc;
	}

	public void setLdesc(String ldesc) {
		this.ldesc = ldesc;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
