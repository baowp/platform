package com.abbcc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.layout.PieceType;

/**
 * AbcModule entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_MODULE")
public class AbcModule implements java.io.Serializable, Comparable<AbcModule> {

	// Fields

	private String moduleId;
	private String name;
	private String msign;
	private PieceType type;

	private int order = 1000;// 用于输出显示上的排序

	// Constructors

	/** default constructor */
	public AbcModule() {
	}

	/** minimal constructor */
	public AbcModule(String moduleId) {
		this.moduleId = moduleId;
	}

	/** full constructor */
	public AbcModule(String moduleId, String name, String msign, PieceType type) {
		this.moduleId = moduleId;
		this.name = name;
		this.msign = msign;
		this.type = type;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Module", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Module"),
			@Parameter(name = "prefix", value = "Module") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Module")
	@Column(name = "MODULE_ID", unique = true, nullable = false, length = 64)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "NAME", length = 400)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MSIGN", length = 40)
	public String getMsign() {
		return this.msign;
	}

	public void setMsign(String msign) {
		this.msign = msign;
	}

	@Transient
	public String getMsign2() {
		return msign.replaceAll("jsp$", "ftl");
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE", length = 2)
	public PieceType getType() {
		return this.type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int compareTo(AbcModule another) {
		int i = this.getType().compareTo(another.getType());

		if (i == 0) {
			i = this.order - another.order;
		}
		return i == 0 ? 1 : i;
	}

}