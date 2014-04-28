package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.ModelType;

/**
 * AbcRecycle entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_RECYCLE")
public class AbcRecycle implements java.io.Serializable {

	// Fields

	private String recycleId;
	private String userId;
	private Date delTime;
	private String state;
	private ModelType belongType;
	private String belongId;
	private String content;
	private String category;
	private String resume;

	// Constructors

	/** default constructor */
	public AbcRecycle() {
	}

	/** minimal constructor */
	public AbcRecycle(String recycleId) {
		this.recycleId = recycleId;
	}

	/** full constructor */
	public AbcRecycle(String recycleId, String userId, Date delTime,
			String state, ModelType belongType, String belongId,
			String content, String category) {
		this.recycleId = recycleId;
		this.userId = userId;
		this.delTime = delTime;
		this.state = state;
		this.belongType = belongType;
		this.belongId = belongId;
		this.content = content;
		this.category = category;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Recycle", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Recycle"),
			@Parameter(name = "prefix", value = "Recycle") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Recycle")
	@Column(name = "RECYCLE_ID", unique = true, nullable = false, length = 64)
	public String getRecycleId() {
		return this.recycleId;
	}

	public void setRecycleId(String recycleId) {
		this.recycleId = recycleId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "DEL_TIME")
	public Date getDelTime() {
		return this.delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BELONG_TYPE", length = 2)
	public ModelType getBelongType() {
		return this.belongType;
	}

	public void setBelongType(ModelType belongType) {
		this.belongType = belongType;
	}

	@Column(name = "BELONG_ID", length = 64)
	public String getBelongId() {
		return this.belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	@Column(name = "CONTENT", length = 400)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CATEGORY", length = 64)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

}