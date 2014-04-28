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
 * AbcMailsend entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_MAILSEND")
public class AbcMailsend implements java.io.Serializable {

	// Fields

	private String mailsendId;
	private Date sendTime;
	private String receiver;
	private String receEmail;
	private String receState;
	private String mailId;
	private Date readTime;
	private String state;
	private String type;

	// Constructors

	/** default constructor */
	public AbcMailsend() {
	}

	/** minimal constructor */
	public AbcMailsend(String mailsendId) {
		this.mailsendId = mailsendId;
	}

	/** full constructor */
	public AbcMailsend(String mailsendId, Date sendTime, String receiver,
			String receEmail, String receState, String mailId, Date readTime,
			String state, String type) {
		this.mailsendId = mailsendId;
		this.sendTime = sendTime;
		this.receiver = receiver;
		this.receEmail = receEmail;
		this.receState = receState;
		this.mailId = mailId;
		this.readTime = readTime;
		this.state = state;
		this.type = type;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Mailsend", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Mailsend"),
			@Parameter(name = "prefix", value = "Mailsend") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Mailsend")
	@Column(name = "MAILSEND_ID", unique = true, nullable = false, length = 64)
	public String getMailsendId() {
		return this.mailsendId;
	}

	public void setMailsendId(String mailsendId) {
		this.mailsendId = mailsendId;
	}

	@Column(name = "SEND_TIME")
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "RECEIVER", length = 64)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "RECE_EMAIL", length = 100)
	public String getReceEmail() {
		return this.receEmail;
	}

	public void setReceEmail(String receEmail) {
		this.receEmail = receEmail;
	}

	@Column(name = "RECE_STATE", length = 2)
	public String getReceState() {
		return this.receState;
	}

	public void setReceState(String receState) {
		this.receState = receState;
	}

	@Column(name = "MAIL_ID", length = 64)
	public String getMailId() {
		return this.mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Column(name = "READ_TIME")
	public Date getReadTime() {
		return this.readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
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

}