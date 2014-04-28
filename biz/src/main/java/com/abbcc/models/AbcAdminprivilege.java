package com.abbcc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.abbcc.common.CommonConst;
import com.abbcc.common.Module;
import com.abbcc.util.IDUtil;

/**
 * AbcAdminprivilege entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_ADMINPRIVILEGE")
public class AbcAdminprivilege implements java.io.Serializable {

	// Fields

	private String adminprivilegeId;
	@Field(name = "pname", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String pname;
	private String sign;
	private String type;
	private String state;
	private String moduleId;
	private String url;

	// Constructors

	/** default constructor */
	public AbcAdminprivilege() {
	}

	/** minimal constructor */
	public AbcAdminprivilege(String adminprivilegeId) {
		this.adminprivilegeId = adminprivilegeId;
	}

	/** full constructor */
	public AbcAdminprivilege(String adminprivilegeId, String pname,
			String sign, String type, String state, String moduleId, String url) {
		this.adminprivilegeId = adminprivilegeId;
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
	@GenericGenerator(name = "SEQ_Adminprivilege", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Adminprivilege"),
			@Parameter(name = "prefix", value = "Adminpri") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Adminprivilege")
	@Column(name = "ADMINPRIVILEGE_ID", unique = true, nullable = false, length = 64)
	public String getAdminprivilegeId() {
		return this.adminprivilegeId;
	}

	public void setAdminprivilegeId(String adminprivilegeId) {
		this.adminprivilegeId = adminprivilegeId;
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

	@Column(name = "URL", length = 400)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String moduleName(){
		String modulename="";
		for(Module module:CommonConst.MODULES){
			if(module.moduleid.equals(this.moduleId)){
				modulename=module.name;
				break;
			}
		}
		return modulename;
	}

}