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
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.abbcc.service.SoaWebserverService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index = "soa")
@Entity
@Table(name = "SOA_USER")
public class SoaUser implements Serializable {

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_SOA_USER", parameters = {
			@Parameter(name = "sequence", value = "SEQ_SOA_USER"),
			@Parameter(name = "prefix", value = "soauser") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_SOA_USER")
	@Column(name = "usersite_id", unique = true, nullable = false, length = 64)
	private String usersiteId;
	@Field(index=Index.TOKENIZED, store=Store.NO) 
	private String username;
	@Field(index=Index.TOKENIZED, store=Store.NO) 
	private String domain;
	@Column(name = "server_id")
	private String serverId;
	private String folder;

	public String getUsersiteId() {
		return usersiteId;
	}

	public void setUsersiteId(String usersiteId) {
		this.usersiteId = usersiteId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public SoaWebserver webServer() {
		SoaWebserverService soaWebserverService = (SoaWebserverService) BeansFactory
				.get("soaWebserverService");
		return (SoaWebserver)soaWebserverService.findById(SoaWebserver.class, serverId);
	}

}
