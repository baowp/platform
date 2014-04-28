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

import com.abbcc.news.models.NewsNews;
import com.abbcc.news.service.NewsNewsService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcComment entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_COMMENT")
public class AbcComment implements java.io.Serializable {

	// Fields

	private String commentId;
	private String belongType;
	private String belongId;
	private String adduserId;
	private String title;
	private String content;
	private Date addTime;
	private String state;
	private String auditId;
	private Date auditTime;

	// Constructors

	/** default constructor */
	public AbcComment() {
	}

	/** minimal constructor */
	public AbcComment(String commentId) {
		this.commentId = commentId;
	}

	/** full constructor */
	public AbcComment(String commentId, String belongType, String belongId,
			String adduserId, String title, String content, Date addTime,
			String state, String auditId, Date auditTime) {
		this.commentId = commentId;
		this.belongType = belongType;
		this.belongId = belongId;
		this.adduserId = adduserId;
		this.title = title;
		this.content = content;
		this.addTime = addTime;
		this.state = state;
		this.auditId = auditId;
		this.auditTime = auditTime;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Comment", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Comment"),
			@Parameter(name = "prefix", value = "Comment") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Comment")
	@Column(name = "COMMENT_ID", unique = true, nullable = false, length = 64)
	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@Column(name = "BELONG_TYPE", length = 2)
	public String getBelongType() {
		return this.belongType;
	}

	public void setBelongType(String belongType) {
		this.belongType = belongType;
	}

	@Column(name = "BELONG_ID", length = 64)
	public String getBelongId() {
		return this.belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	@Column(name = "ADDUSER_ID", length = 64)
	public String getAdduserId() {
		return this.adduserId;
	}

	public void setAdduserId(String adduserId) {
		this.adduserId = adduserId;
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

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "AUDIT_ID", length = 64)
	public String getAuditId() {
		return this.auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	@Column(name = "AUDIT_TIME")
	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String newsTitle(String id){
		NewsNewsService newsNewsService = (NewsNewsService)BeansFactory
		.get("newsNewsService");
		NewsNews nn = (NewsNews) newsNewsService.findById(NewsNews.class, id);
		return nn.getTitle();
	}

}