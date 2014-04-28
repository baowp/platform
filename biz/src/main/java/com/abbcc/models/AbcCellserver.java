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
 * AbcCellserver entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_CELLSERVER")
public class AbcCellserver implements java.io.Serializable {

	// Fields

	private String cellserverId;
	private String cellbindId;
	private String cellphone;
	private String type;
	private Date addTime;

	// Constructors

	/** default constructor */
	public AbcCellserver() {
	}

	/** minimal constructor */
	public AbcCellserver(String cellserverId) {
		this.cellserverId = cellserverId;
	}

	/** full constructor */
	public AbcCellserver(String cellserverId, String cellbindId,
			String cellphone, String type, Date addTime) {
		this.cellserverId = cellserverId;
		this.cellbindId = cellbindId;
		this.cellphone = cellphone;
		this.type = type;
		this.addTime = addTime;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Cellserver", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Cellserver"),
			@Parameter(name = "prefix", value = "Cellserver") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Cellserver")
	@Column(name = "CELLSERVER_ID", unique = true, nullable = false, length = 64)
	public String getCellserverId() {
		return this.cellserverId;
	}

	public void setCellserverId(String cellserverId) {
		this.cellserverId = cellserverId;
	}

	@Column(name = "CELLBIND_ID", length = 64)
	public String getCellbindId() {
		return this.cellbindId;
	}

	public void setCellbindId(String cellbindId) {
		this.cellbindId = cellbindId;
	}

	@Column(name = "CELLPHONE", length = 24)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}