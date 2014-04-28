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
@Table(name = "SOA_WEBSERVER")
public class SoaWebserver implements Serializable {

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_SOA_WEBSERVER", parameters = {
			@Parameter(name = "sequence", value = "SEQ_SOA_WEBSERVER"),
			@Parameter(name = "prefix", value = "webserver") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_SOA_WEBSERVER")
	@Column(name = "server_ID", unique = true, nullable = false, length = 64)
	private String serverId;
	private String name;
	private String ip;
	@Column(name = "ftp_username")
	private String ftpUsername;
	@Column(name = "ftp_password")
	private String ftpPassword;
	private String state;
	private String webservice;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebservice() {
		return webservice;
	}

	public void setWebservice(String webservice) {
		this.webservice = webservice;
	}
}
