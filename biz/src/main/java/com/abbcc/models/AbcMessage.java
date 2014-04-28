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

import com.abbcc.common.CommonConst;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

/**
 * AbcMessage entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_MESSAGE")
public class AbcMessage implements java.io.Serializable {

	// Fields

	private String messageId;
	private String title;
	private String content;
	private String replyId;
	private String addUser;
	private String type;
	private String addEnt;
	private String recvUser;
	private String recvEnt;
	private Date addTime;
	private String sendState;
	private String recvState;
	private String domain;
	private String fromName;
	private String fromEmail;
	private String fromPhone;
	// Constructors

	/** default constructor */
	public AbcMessage() {
	}

	/** minimal constructor */
	public AbcMessage(String messageId) {
		this.messageId = messageId;
	}

	/** full constructor */
	public AbcMessage(String messageId, String title, String content,
			String replyId, String addUser, String type, String addEnt,
			String recvUser, String recvEnt, Date addTime, String sendState,
			String recvState,String readState,String state) {
		this.messageId = messageId;
		this.title = title;
		this.content = content;
		this.replyId = replyId;
		this.addUser = addUser;
		this.type = type;
		this.addEnt = addEnt;
		this.recvUser = recvUser;
		this.recvEnt = recvEnt;
		this.addTime = addTime;
		this.sendState = sendState;
		this.recvState = recvState;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Message", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Message"),
			@Parameter(name = "prefix", value = "Message") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Message")
	@Column(name = "MESSAGE_ID", unique = true, nullable = false, length = 64)
	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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

	@Column(name = "REPLY_ID", length = 64)
	public String getReplyId() {
		return this.replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	@Column(name = "ADD_USER", length = 64)
	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ADD_ENT", length = 64)
	public String getAddEnt() {
		return this.addEnt;
	}

	public void setAddEnt(String addEnt) {
		this.addEnt = addEnt;
	}

	@Column(name = "RECV_USER", length = 64)
	public String getRecvUser() {
		return this.recvUser;
	}

	public void setRecvUser(String recvUser) {
		this.recvUser = recvUser;
	}

	@Column(name = "RECV_ENT", length = 64)
	public String getRecvEnt() {
		return this.recvEnt;
	}

	public void setRecvEnt(String recvEnt) {
		this.recvEnt = recvEnt;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "SEND_STATE", length = 2)
	public String getSendState() {
		return this.sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
	}

	@Column(name = "RECV_STATE", length = 2)
	public String getRecvState() {
		return this.recvState;
	}

	public void setRecvState(String recvState) {
		this.recvState = recvState;
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	@Column(name = "FROM_NAME")
	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	@Column(name = "FROM_EMAIL")
	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	@Column(name = "FROM_PHONE")
	public String getFromPhone() {
		return fromPhone;
	}

	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}

	public AbcUser sender(){
		UserService userService = (UserService) BeansFactory.get("userService");
		return userService.findById(this.getAddUser());
	}
	public AbcUser recver(){
		UserService userService = (UserService) BeansFactory.get("userService");
		return userService.findById(this.getRecvUser());
	}
	public AbcEnterprise enterprise(){
		AbcEnterprise ae = new AbcEnterprise();
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
		.get("enterpriseService");
		try{
		if(StringUtil.isNotBlank(getAddEnt()))
			return enterpriseService.findById(this.getAddEnt());
		else{
			ae.setName("未知");
		}
		}catch(Exception e){
			ae.setName("未知");
		}
		return ae;
	}
	public AbcUser userName(){
		String userId = "";
		UserService userService = (UserService) BeansFactory.get("userService");
		if(StringUtil.isNotBlank(this.getAddUser())){
			userId=this.getAddUser();
		AbcUser lu = userService.findById(userId);

			if(lu.getType()!=null&&lu.getType().equals(CommonConst.MESSAGESYS)){
				lu.setName("管理员");
			}
			else if(lu.getType()!=null&&lu.getType().equals(CommonConst.MESSAGEBROWSER))
				lu.setName("访问者");
				
		
		return lu;
		}
		return new AbcUser();
	}
	public String recvName(){
		UserService userService = (UserService) BeansFactory.get("userService");
		if(this.getRecvUser()!=null){
			AbcUser au = userService.findById(this.getRecvUser());
			if(au!=null)
				return au.getName();
			}
		else{
			if(this.getType().equals(CommonConst.MESSAGESYS))
				return "管理员";
			else if(this.getType().equals(CommonConst.MESSAGEBROWSER))
				return "访问者";
				
		}
		return "未知";
	}
	public String entName(){
		UserService userService = (UserService) BeansFactory.get("userService");
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
		.get("enterpriseService");
		enterpriseService.findById(userService.findById(this.getAddUser()).getEnterpriseId()).getName();
		return 	enterpriseService.findById(userService.findById(this.getAddUser()).getEnterpriseId()).getName();
	}
	public String addTimeString() {
		return DateUtil.formatDate(this.getAddTime(), "yyyy-MM-dd");
	}


}