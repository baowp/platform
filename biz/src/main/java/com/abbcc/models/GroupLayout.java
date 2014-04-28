package com.abbcc.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

@Entity
@Table(name = "GROUP_LAYOUT")
public class GroupLayout implements Serializable {
	private static final long serialVersionUID = 1L;

	private String layoutId;
	private Date createTime;
	private String enterpriseId;
	private String signContent;
	private String state;
	private Date updateTime;
	private String userId;

	private JSONObject jsonSign;

	public GroupLayout() {
		super();
	}

	public GroupLayout(String layoutId, Date createTime, String enterpriseId,
			String signContent, String state, Date updateTime, String userId) {
		super();
		this.layoutId = layoutId;
		this.createTime = createTime;
		this.enterpriseId = enterpriseId;
		this.signContent = signContent;
		this.state = state;
		this.updateTime = updateTime;
		this.userId = userId;
	}

	@Id
	@GenericGenerator(name = "SEQ_GroupLayout", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GroupLayout"),
			@Parameter(name = "prefix", value = "GroupLay") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GroupLayout")
	@Column(name = "LAYOUT_ID", unique = true, nullable = false, length = 64)
	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "SIGN_CONTENT")
	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
		if (StringUtil.isBlank(signContent)) {
			this.signContent = "{}";
		}

		jsonSign = JSONObject.fromObject(this.signContent);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Transient
	public JSONObject getJsonSign() {
		if (jsonSign == null)
			setSignContent(null);
		return jsonSign;
	}

}