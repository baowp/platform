package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.service.EnterpriseService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.PayType;

/**
 * AbcPaylog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_PAYLOG")
public class AbcPaylog implements java.io.Serializable {

	// Fields

	private String paylogId;
	private String userId;
	private String amount;
	private String bank;
	private String bankAccount;
	private Date payTime;
	private Date endTime;
	private Date startTime;
	private String payuserId;
	private String state;
	private String type;
	private String documentId;
	private String memo;

	private String isExpired;
	@Transient
	public String getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}

	// Constructors
	@Column(name = "DOCUMENT_ID", length = 32)
	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	@Column(name = "MEMO", length = 200)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE", length = 2)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/** default constructor */
	public AbcPaylog() {
	}

	/** minimal constructor */
	public AbcPaylog(String paylogId) {
		this.paylogId = paylogId;
	}

	/** full constructor */
	public AbcPaylog(String paylogId, String userId, String amount,
			String bank, String bankAccount, Date payTime, Date endTime,
			Date startTime, String payuserId) {
		this.paylogId = paylogId;
		this.userId = userId;
		this.amount = amount;
		this.bank = bank;
		this.bankAccount = bankAccount;
		this.payTime = payTime;
		this.endTime = endTime;
		this.startTime = startTime;
		this.payuserId = payuserId;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Paylog", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Paylog"),
			@Parameter(name = "prefix", value = "Paylog") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Paylog")
	@Column(name = "PAYLOG_ID", unique = true, nullable = false, length = 64)
	public String getPaylogId() {
		return this.paylogId;
	}

	public void setPaylogId(String paylogId) {
		this.paylogId = paylogId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "AMOUNT", length = 32)
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "BANK", length = 2)
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "BANK_ACCOUNT", length = 64)
	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Column(name = "PAY_TIME")
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "END_TIME")
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "START_TIME")
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "PAYUSER_ID", length = 64)
	public String getPayuserId() {
		return this.payuserId;
	}

	public void setPayuserId(String payuserId) {
		this.payuserId = payuserId;
	}

	public String payEnd() {
		if (this.getEndTime() != null)
			return DateUtil.formatDate(this.getEndTime(), "yyyy-MM-dd");
		else
			return "";
	}

	public String payStart() {
		if (this.getStartTime() != null)
			return DateUtil.formatDate(this.getStartTime(), "yyyy-MM-dd");
		else
			return "";
	}

	public String payDate() {
		if (this.getPayTime() != null)
			return DateUtil.formatDate(this.getPayTime(), "yyyy-MM-dd");
		else
			return "";
	}

	public AbcUser user() {
		UserService userService = (UserService) BeansFactory.get("userService");
		return userService.findById(this.getUserId());
	}
	public AbcEnterprise ent() {
		UserService userService = (UserService) BeansFactory.get("userService");
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory.get("enterpriseService");
		return enterpriseService.findById(userService.findById(this.getUserId()).getEnterpriseId());
	}
}