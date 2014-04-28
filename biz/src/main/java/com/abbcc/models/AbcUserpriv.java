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
 * AbcUserpriv entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_USERPRIV")
public class AbcUserpriv implements java.io.Serializable {

	// Fields

	private String userprivId;
	private String userId;
	private String userprivilegeId;
	private Date addTime;
	private String adduserId;
	private String state;

	// Constructors

	/** default constructor */
	public AbcUserpriv() {
	}

	/** minimal constructor */
	public AbcUserpriv(String userprivId) {
		this.userprivId = userprivId;
	}

	/** full constructor */
	public AbcUserpriv(String userprivId, String userId,
			String userprivilegeId, Date addTime, String adduserId,
			String state) {
		this.userprivId = userprivId;
		this.userId = userId;
		this.userprivilegeId = userprivilegeId;
		this.addTime = addTime;
		this.adduserId = adduserId;
		this.state = state;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Userpriv", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Userpriv"),
			@Parameter(name = "prefix", value = "Useruse") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Userpriv")
	@Column(name = "USERPRIV_ID", unique = true, nullable = false, length = 64)
	public String getuserprivId() {
		return this.userprivId;
	}

	public void setuserprivId(String userprivId) {
		this.userprivId = userprivId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getuserId() {
		return this.userId;
	}

	public void setuserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USERPRIVILEGE_ID", length = 64)
	public String getuserprivilegeId() {
		return this.userprivilegeId;
	}

	public void setuserprivilegeId(String userprivilegeId) {
		this.userprivilegeId = userprivilegeId;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "ADD_USER_ID", length = 64)
	public String getAdduserId() {
		return this.adduserId;
	}

	public void setAdduserId(String adduserId) {
		this.adduserId = adduserId;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}