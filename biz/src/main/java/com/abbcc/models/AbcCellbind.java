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
 * AbcCellbind entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_CELLBIND")
public class AbcCellbind implements java.io.Serializable {

	// Fields

	private String cellbindId;
	private String userId;
	private String cellphone;
	private String state;
	private Date bindTime;
	private Date limitTime;
	private String verifyCode;
	private String aduitState;
	private String aduitAdmin;
	private Date aduitTime;

	// Constructors

	/** default constructor */
	public AbcCellbind() {
	}

	/** minimal constructor */
	public AbcCellbind(String cellbindId) {
		this.cellbindId = cellbindId;
	}

	/** full constructor */
	public AbcCellbind(String cellbindId, String userId, String cellphone,
			String state, Date bindTime, Date limitTime, String verifyCode,
			String aduitState, String aduitAdmin, Date aduitTime) {
		this.cellbindId = cellbindId;
		this.userId = userId;
		this.cellphone = cellphone;
		this.state = state;
		this.bindTime = bindTime;
		this.limitTime = limitTime;
		this.verifyCode = verifyCode;
		this.aduitState = aduitState;
		this.aduitAdmin = aduitAdmin;
		this.aduitTime = aduitTime;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Cellbind", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Cellbind"),
			@Parameter(name = "prefix", value = "Cellbind") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Cellbind")
	@Column(name = "CELLBIND_ID", unique = true, nullable = false, length = 64)
	public String getCellbindId() {
		return this.cellbindId;
	}

	public void setCellbindId(String cellbindId) {
		this.cellbindId = cellbindId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "CELLPHONE", length = 24)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "BIND_TIME")
	public Date getBindTime() {
		return this.bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	@Column(name = "LIMIT_TIME")
	public Date getLimitTime() {
		return this.limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}

	@Column(name = "VERIFY_CODE", length = 16)
	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Column(name = "ADUIT_STATE", length = 2)
	public String getAduitState() {
		return this.aduitState;
	}

	public void setAduitState(String aduitState) {
		this.aduitState = aduitState;
	}

	@Column(name = "ADUIT_ADMIN", length = 64)
	public String getAduitAdmin() {
		return this.aduitAdmin;
	}

	public void setAduitAdmin(String aduitAdmin) {
		this.aduitAdmin = aduitAdmin;
	}

	@Column(name = "ADUIT_TIME")
	public Date getAduitTime() {
		return this.aduitTime;
	}

	public void setAduitTime(Date aduitTime) {
		this.aduitTime = aduitTime;
	}

}