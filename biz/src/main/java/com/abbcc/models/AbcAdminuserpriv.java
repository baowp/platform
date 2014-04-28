package com.abbcc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.service.AdminuserprivService;
import com.abbcc.service.UserprivService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcAdminuserpriv entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_ADMINUSERPRIV")
public class AbcAdminuserpriv implements java.io.Serializable {

	// Fields

	private String adminuserprivId;
	private String adminId;
	private String adminprivilegeId;
	private Date addTime;
	private String addAdminId;
	private String state;

	// Constructors

	/** default constructor */
	public AbcAdminuserpriv() {
	}

	/** minimal constructor */
	public AbcAdminuserpriv(String adminuserprivId) {
		this.adminuserprivId = adminuserprivId;
	}

	/** full constructor */
	public AbcAdminuserpriv(String adminuserprivId, String adminId,
			String adminprivilegeId, Date addTime, String addAdminId,
			String state) {
		this.adminuserprivId = adminuserprivId;
		this.adminId = adminId;
		this.adminprivilegeId = adminprivilegeId;
		this.addTime = addTime;
		this.addAdminId = addAdminId;
		this.state = state;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Adminuserpriv", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Adminuserpriv"),
			@Parameter(name = "prefix", value = "Adminuse") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Adminuserpriv")
	@Column(name = "ADMINUSERPRIV_ID", unique = true, nullable = false, length = 64)
	public String getAdminuserprivId() {
		return this.adminuserprivId;
	}

	public void setAdminuserprivId(String adminuserprivId) {
		this.adminuserprivId = adminuserprivId;
	}

	@Column(name = "ADMIN_ID", length = 64)
	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	@Column(name = "ADMINPRIVILEGE_ID", length = 64)
	public String getAdminprivilegeId() {
		return this.adminprivilegeId;
	}

	public void setAdminprivilegeId(String adminprivilegeId) {
		this.adminprivilegeId = adminprivilegeId;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "ADD_ADMIN_ID", length = 64)
	public String getAddAdminId() {
		return this.addAdminId;
	}

	public void setAddAdminId(String addAdminId) {
		this.addAdminId = addAdminId;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String moudelName(String modelId){
		if(modelId.equals("01"))
			return "个人设置";
		if(modelId.equals("02"))
			return "系统设置";
		if(modelId.equals("03"))
			return "会员管理";
		if(modelId.equals("04"))
			return "新闻留言";
		if(modelId.equals("05"))
			return "企业管理";
		if(modelId.equals("06"))
			return "统计日志";
		if(modelId.equals("07"))
			return "帮助中心";
		return "";
		
	}
	public String isCheck(String userId, String privId) {
		AdminuserprivService adminuserprivService = (AdminuserprivService) BeansFactory
				.get("adminuserprivService");
		AbcAdminuserpriv au = new AbcAdminuserpriv();
		au.setAdminId(userId);
		au.setAdminprivilegeId(privId);
		List<AbcAdminuserpriv> list = adminuserprivService.findByExample(au);
		if (list.size() != 0)
			return list.get(0).getState();
		return "";
	}

}