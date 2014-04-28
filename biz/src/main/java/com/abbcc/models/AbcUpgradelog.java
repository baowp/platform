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
 * AbcUpgradelog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_UPGRADELOG")
public class AbcUpgradelog implements java.io.Serializable {

	// Fields

	private String upgradeId;
	private String userId;
	private String type;
	private Date upgradeDate;
	private String state;
	private String aduitAdmin;
	private String udesc;

	// Constructors

	/** default constructor */
	public AbcUpgradelog() {
	}

	/** minimal constructor */
	public AbcUpgradelog(String upgradeId) {
		this.upgradeId = upgradeId;
	}

	/** full constructor */
	public AbcUpgradelog(String upgradeId, String userId, String type,
			Date upgradeDate, String state, String aduitAdmin, String udesc) {
		this.upgradeId = upgradeId;
		this.userId = userId;
		this.type = type;
		this.upgradeDate = upgradeDate;
		this.state = state;
		this.aduitAdmin = aduitAdmin;
		this.udesc = udesc;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Upgradelog", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Upgradelog"),
			@Parameter(name = "prefix", value = "Upgra") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Upgradelog")
	@Column(name = "UPGRADE_ID", unique = true, nullable = false, length = 64)
	public String getUpgradeId() {
		return this.upgradeId;
	}

	public void setUpgradeId(String upgradeId) {
		this.upgradeId = upgradeId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "UPGRADE_DATE")
	public Date getUpgradeDate() {
		return this.upgradeDate;
	}

	public void setUpgradeDate(Date upgradeDate) {
		this.upgradeDate = upgradeDate;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ADUIT_ADMIN", length = 64)
	public String getAduitAdmin() {
		return this.aduitAdmin;
	}

	public void setAduitAdmin(String aduitAdmin) {
		this.aduitAdmin = aduitAdmin;
	}

	@Column(name = "UDESC", length = 800)
	public String getUdesc() {
		return this.udesc;
	}

	public void setUdesc(String udesc) {
		this.udesc = udesc;
	}

}