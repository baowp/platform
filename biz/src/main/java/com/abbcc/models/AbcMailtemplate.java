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
 * AbcMailtemplate entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_MAILTEMPLATE")
public class AbcMailtemplate implements java.io.Serializable {

	// Fields

	private String mailtemplateId;
	private String name;
	private String mdesc;
	private String userId;
	private Date addTime;
	private String content;
	private String state;

	// Constructors

	/** default constructor */
	public AbcMailtemplate() {
	}

	/** minimal constructor */
	public AbcMailtemplate(String mailtemplateId) {
		this.mailtemplateId = mailtemplateId;
	}

	/** full constructor */
	public AbcMailtemplate(String mailtemplateId, String name, String mdesc,
			String userId, Date addTime, String content, String state) {
		this.mailtemplateId = mailtemplateId;
		this.name = name;
		this.mdesc = mdesc;
		this.userId = userId;
		this.addTime = addTime;
		this.content = content;
		this.state = state;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Mailtemplate", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Mailtemplate"),
			@Parameter(name = "prefix", value = "Mailtem") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Mailtemplate")
	@Column(name = "MAILTEMPLATE_ID", unique = true, nullable = false, length = 64)
	public String getMailtemplateId() {
		return this.mailtemplateId;
	}

	public void setMailtemplateId(String mailtemplateId) {
		this.mailtemplateId = mailtemplateId;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MDESC", length = 400)
	public String getMdesc() {
		return this.mdesc;
	}

	public void setMdesc(String mdesc) {
		this.mdesc = mdesc;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}