package com.abbcc.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.abbcc.service.EnterpriseService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcPayuser entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_PAYUSER")
public class AbcPayuser implements java.io.Serializable {

	// Fields

	private String payuserId;
	private String userId;
	private String grade;
	private Date applyTime;
	private String auditState;
	private Date auditTime;
	private String auditAdmin;
	private String applyContent;
	private String applyMemo;
	private String paytype;
	private String payway;
	private String state;
	private String sum;
	private String year;

	// Constructors

	/** default constructor */
	public AbcPayuser() {
	}

	/** minimal constructor */
	public AbcPayuser(String payuserId) {
		this.payuserId = payuserId;
	}

	/** full constructor */
	public AbcPayuser(String payuserId, String userId, String grade,
			Date applyTime, String auditState, Date auditTime,
			String auditAdmin, String applyContent, String applyMemo,
			String paytype, String payway, String state, String sum) {
		this.payuserId = payuserId;
		this.userId = userId;
		this.grade = grade;
		this.applyTime = applyTime;
		this.auditState = auditState;
		this.auditTime = auditTime;
		this.auditAdmin = auditAdmin;
		this.applyContent = applyContent;
		this.applyMemo = applyMemo;
		this.paytype = paytype;
		this.payway = payway;
		this.state = state;
		this.sum = sum;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Payuser", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Payuser"),
			@Parameter(name = "prefix", value = "Payuser") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Payuser")
	@Column(name = "PAYUSER_ID", unique = true, nullable = false, length = 64)
	public String getPayuserId() {
		return this.payuserId;
	}

	public void setPayuserId(String payuserId) {
		this.payuserId = payuserId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "GRADE", length = 2)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "APPLY_TIME")
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "AUDIT_STATE", length = 2)
	public String getAuditState() {
		return this.auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	@Column(name = "AUDIT_TIME")
	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "AUDIT_ADMIN", length = 64)
	public String getAuditAdmin() {
		return this.auditAdmin;
	}

	public void setAuditAdmin(String auditAdmin) {
		this.auditAdmin = auditAdmin;
	}

	@Column(name = "APPLY_CONTENT")
	public String getApplyContent() {
		return this.applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	@Column(name = "APPLY_MEMO", length = 2)
	public String getApplyMemo() {
		return this.applyMemo;
	}

	public void setApplyMemo(String applyMemo) {
		this.applyMemo = applyMemo;
	}

	@Column(name = "PAYTYPE", length = 2)
	public String getPaytype() {
		return this.paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	@Column(name = "PAYWAY", length = 2)
	public String getPayway() {
		return this.payway;
	}

	public void setPayway(String payway) {
		this.payway = payway;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "YEAR", length = 2)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "SUM", length = 32)
	public String getSum() {
		return this.sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public AbcUser user() {
		UserService userService = (UserService) BeansFactory.get("userService");
		AbcUser au = userService.findById(this.getUserId());
		if (au == null) {
			au.setUsername("");
			au.setCellphone("");
			return au;
		}
		return au;
	}

	public String gradeName() {
		if(getGrade()==null)
			return "免费版";
		if (getGrade().equals("01")) {
			return "企业版";
		} else if (getGrade().equals("02")) {
			return "集团版";
		} else {
			return "免费版";
		}
	}
	public String currentGradeName() {
		AbcUser user = new AbcUser();
		UserService userService = (UserService) BeansFactory
				.get("userService");
		user = userService.findById(getUserId());
		
		if(user.getGrade()==null)
			return "免费版";
		if (user.getGrade().equals("01")) {
			return "企业版";
		} else if (user.getGrade().equals("02")) {
			return "集团版";
		} else {
			return "免费版";
		}
		
	}
	public String yearName() {
		if (getYear().equals("01"))
			return "一年";
		if (getYear().equals("02"))
			return "两年";
		return "";
	}

	public AbcEnterprise enterprise() {
		UserService userService = (UserService) BeansFactory.get("userService");
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		AbcEnterprise au = enterpriseService.findById(userService
				.findById(this.getUserId()).getEnterpriseId());
		if(au==null){
			AbcEnterprise ae = new AbcEnterprise();
			ae.setName("未定义");
			return ae;
		}
		return au;
	}

	public String endTime() {
		List<AbcPaylog> plogList = new ArrayList();
		PaylogService paylogService = (PaylogService) BeansFactory
				.get("paylogService");
		String hql = "from AbcPaylog where userId='" + getUserId()
				+ "' order by endTime desc";
		plogList = paylogService.findByHql(hql);
		if (plogList.size() != 0) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String endTime = df.format(plogList.get(0).getEndTime());
			String startTime = df.format(plogList.get(0).getStartTime());
			return startTime + "到" + endTime;
		} else {
			return null;
		}
	}
}