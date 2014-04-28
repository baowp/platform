package com.abbcc.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.common.CommonConst;
import com.abbcc.service.SyscodeService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

/**
 * AbcSyscode entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_SYSCODE")
public class AbcSyscode implements java.io.Serializable {

	// Fields

	private String syscodeId;
	private String type;
	private String class_;
	private String fatherdict;
	private String name;
	private String sdesc;
	private String sign;
	private String state;
	private String content;
	private List sonSyscode;

	// Constructors
	//国家代号
	@Transient
	public String getCode() {
		if(type.equals(CommonConst.SYSCODENCOMPANY)){
			if(sign!=null)
				return sign.split(",")[0];
		}
		return "";
	}
	@Transient
	public String getCountrynum() {
		if(type.equals(CommonConst.SYSCODENCOMPANY)){
			if(sign!=null&&sign.split(",").length>1)
				return sign.split(",")[1];
		}
		return "";
	}
	@Transient
	public AbcSyscode country(String code){
		SyscodeService syscodeService = (SyscodeService)BeansFactory.get("syscodeService");
		DetachedCriteria dc = DetachedCriteria.forClass(AbcSyscode.class);
		dc.add(Restrictions.eq("type",CommonConst.SYSCODENCOMPANY));
		dc.add(Restrictions.like("sign",code,MatchMode.ANYWHERE));
		return (AbcSyscode) syscodeService.findAllByCriteria(dc).get(0);
	}

	/** default constructor */
	public AbcSyscode() {
	}

	/** minimal constructor */
	public AbcSyscode(String syscodeId) {
		this.syscodeId = syscodeId;
	}

	/** full constructor */
	public AbcSyscode(String syscodeId, String type, String class_,
			String fatherdict, String name, String sdesc, String sign,
			String state, String content) {
		this.syscodeId = syscodeId;
		this.type = type;
		this.class_ = class_;
		this.fatherdict = fatherdict;
		this.name = name;
		this.sdesc = sdesc;
		this.sign = sign;
		this.state = state;
		this.content = content;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Syscode", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Syscode"),
			@Parameter(name = "prefix", value = "Syscode") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Syscode")
	@Column(name = "SYSCODE_ID", unique = true, nullable = false, length = 64)
	public String getSyscodeId() {
		return this.syscodeId;
	}

	public void setSyscodeId(String syscodeId) {
		this.syscodeId = syscodeId;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "CLASS", length = 2)
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@Column(name = "FATHERDICT", length = 64)
	public String getFatherdict() {
		return this.fatherdict;
	}

	public void setFatherdict(String fatherdict) {
		this.fatherdict = fatherdict;
	}

	@Column(name = "NAME", length = 400)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SDESC", length = 800)
	public String getSdesc() {
		return this.sdesc;
	}

	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}

	@Column(name = "SIGN", length = 100)
	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Transient
	public List getSonSyscode() {
		return subSyscodes();
	}

	public void setSonSyscode(List sonSyscode) {
		this.sonSyscode = sonSyscode;
	}	
	public List<AbcSyscode>subSyscodes(){
		SyscodeService syscodeService = (SyscodeService)BeansFactory.get("syscodeService");
		return syscodeService.getSubSyscodesByFather(syscodeId,CommonConst.STATENORMAL);
	}

}