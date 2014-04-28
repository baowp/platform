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
 * AbcMail entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_MAIL")
public class AbcMail implements java.io.Serializable {

	// Fields

	private String mailId;
	private String title;
	private String content;
	private Date addTime;
	private String adminId;
	private String mkey;
	private String state;
	private String type;
	private String receiver;

	// Constructors

	/** default constructor */
	public AbcMail() {
	}

	/** minimal constructor */
	public AbcMail(String mailId) {
		this.mailId = mailId;
	}

	/** full constructor */
	public AbcMail(String mailId, String title, String content, Date addTime,
			String adminId, String mkey, String state, String type,
			String receiver) {
		this.mailId = mailId;
		this.title = title;
		this.content = content;
		this.addTime = addTime;
		this.adminId = adminId;
		this.mkey = mkey;
		this.state = state;
		this.type = type;
		this.receiver = receiver;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Mail", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Mail"),
			@Parameter(name = "prefix", value = "Mail") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Mail")
	@Column(name = "MAIL_ID", unique = true, nullable = false, length = 64)
	public String getMailId() {
		return this.mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Column(name = "TITLE", length = 400)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "ADMIN_ID", length = 64)
	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	@Column(name = "MKEY", length = 200)
	public String getMkey() {
		return this.mkey;
	}

	public void setMkey(String mkey) {
		this.mkey = mkey;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "RECEIVER", length = 40)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}