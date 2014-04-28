package com.abbcc.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.common.CommonConst;
import com.abbcc.common.Module;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.UserprivService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcUserprivilege entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_USERPRIVILEGE")
public class AbcUserprivilege implements java.io.Serializable {

	// Fields

	private String userprivilegeId;
	private String pname;
	private String sign;
	private String type;
	private String state;
	private String moduleId;
	private String url;

	// Constructors

	/** default constructor */
	public AbcUserprivilege() {
	}

	/** minimal constructor */
	public AbcUserprivilege(String userprivilegeId) {
		this.userprivilegeId = userprivilegeId;
	}

	/** full constructor */
	public AbcUserprivilege(String userprivilegeId, String pname, String sign,
			String type, String state, String moduleId, String url) {
		this.userprivilegeId = userprivilegeId;
		this.pname = pname;
		this.sign = sign;
		this.type = type;
		this.state = state;
		this.moduleId = moduleId;
		this.url = url;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Userprivilege", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Userprivilege"),
			@Parameter(name = "prefix", value = "Userpri") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Userprivilege")
	@Column(name = "USERPRIVILEGE_ID", unique = true, nullable = false, length = 64)
	public String getUserprivilegeId() {
		return this.userprivilegeId;
	}

	public void setUserprivilegeId(String userprivilegeId) {
		this.userprivilegeId = userprivilegeId;
	}

	@Column(name = "PNAME", length = 40)
	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Column(name = "SIGN", length = 40)
	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "MODULE_ID", length = 40)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "URL", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String moduleName() {
		String modulename = "";
		for (Module module : CommonConst.MODULES) {
			if (module.moduleid.equals(this.moduleId)) {
				modulename = module.name;
				break;
			}
		}
		return modulename;
	}

	public String popedomState() {
		String userId = ServletActionContext.getRequest().getParameter("index");
		UserprivService userprivService = (UserprivService) BeansFactory
				.get("userprivService");
		AbcUserpriv auser = new AbcUserpriv();
		auser.setuserprivilegeId(this.getUserprivilegeId());
		auser.setuserId(userId);
		List list = userprivService.findByExample(auser);
		if (list == null || list.size() == 0) {
			return "00";
		}
		AbcUserpriv au = (AbcUserpriv) list.iterator().next();
		return au.getState();
	}

	public String userId() {
		String userId = ServletActionContext.getRequest().getParameter("index");
		return userId;
	}

}