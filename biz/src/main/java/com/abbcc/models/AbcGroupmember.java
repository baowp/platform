/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AbcGroupmember.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-4           baowp                      initial
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

import com.abbcc.service.UserService;
import com.abbcc.service.UsergroupService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_GROUPMEMBER")
public class AbcGroupmember implements Serializable {

	private String groupmemberId;
	private String usergroupId;
	private String userId;
	private String state;

	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_groupmember", parameters = {
			@Parameter(name = "sequence", value = "SEQ_groupmember"),
			@Parameter(name = "prefix", value = "Groupmem") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_groupmember")
	@Column(name = "groupmember_ID", unique = true, nullable = false, length = 64)
	public String getGroupmemberId() {
		return groupmemberId;
	}

	public void setGroupmemberId(String groupmemberId) {
		this.groupmemberId = groupmemberId;
	}

	@Column(name = "usergroup_id")
	public String getUsergroupId() {
		return usergroupId;
	}

	public void setUsergroupId(String usergroupId) {
		this.usergroupId = usergroupId;
	}

	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public AbcUser user(){
		UserService userService = (UserService)BeansFactory.get("userService");
		return userService.findById(this.getUserId());
	}
	public AbcUsergroup group(){
		UsergroupService usergroupService = (UsergroupService)BeansFactory.get("usergroupService");
		return usergroupService.findById(this.getUsergroupId());
	}
}
