package com.abbcc.models;

import java.io.Serializable;

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

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.group.GroupPieceKind;
import com.abbcc.util.constant.group.GroupPieceType;

@Entity
@Table(name = "GROUP_MODULE")
public class GroupModule implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private String moduleId;
	private String msign;
	private String name;

	private GroupPieceType type;
	private GroupPieceKind kind;

	public GroupModule() {
	}

	@Id
	@GenericGenerator(name = "SEQ_GroupModule", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GroupModule"),
			@Parameter(name = "prefix", value = "GroupMod") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GroupModule")
	@Column(name = "MODULE_ID", unique = true, nullable = false)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "MSIGN")
	public String getMsign() {
		return this.msign;
	}

	public void setMsign(String msign) {
		this.msign = msign;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "KIND")
	public GroupPieceKind getKind() {
		return kind;
	}

	public void setKind(GroupPieceKind kind) {
		this.kind = kind;
	}

	@Column(name = "TYPE")
	public GroupPieceType getType() {
		return type;
	}

	public void setType(GroupPieceType type) {
		this.type = type;
	}

	public GroupModule clone() {
		GroupModule module = null;
		try {
			module = (GroupModule) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return module;
	}

	@Transient
	public String getMsignJsp() {
		return this.msign + ".jsp";
	}

	@Transient
	public String getMsignFtl() {
		return this.msign + ".ftl";
	}
}