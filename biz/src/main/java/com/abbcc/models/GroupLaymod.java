package com.abbcc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.group.GroupPosition;



/**
 * The persistent class for the GROUP_LAYMOD database table.
 * 
 */
@Entity
@Table(name="GROUP_LAYMOD")
public class GroupLaymod implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String laymodId;
	private String name;
	private String page;
	private Integer pieceOrder;

	private GroupPosition position;
	
	private GroupLayout layout;
	private GroupModule module;

	public GroupLaymod() {
		super();
	}

	@Id
	@GenericGenerator(name = "SEQ_GroupLaymod", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GroupLaymod"),
			@Parameter(name = "prefix", value = "GroupMod") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GroupLaymod")
	@Column(name = "LAYMOD_ID", unique = true, nullable = false, length = 64)
	public String getLaymodId() {
		return this.laymodId;
	}
	public void setLaymodId(String laymodId) {
		this.laymodId = laymodId;
	}
	
	@Column(name="NAME")
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	@Column(name="PIECE_ORDER")
	public Integer getPieceOrder() {
		return this.pieceOrder;
	}
	public void setPieceOrder(Integer pieceOrder) {
		this.pieceOrder = pieceOrder;
	}
	
	@ManyToOne
	@JoinColumn(name = "LAYOUT_ID")
	public GroupLayout getLayout() {
		return layout;
	}
	public void setLayout(GroupLayout layout) {
		this.layout = layout;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "POSITION")
	public GroupPosition getPosition() {
		return position;
	}

	public void setPosition(GroupPosition position) {
		this.position = position;
	}
	
	@Column(name="PAGE")
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	
	/**
	 * 必须有外键
	 * @return
	 */
	@ManyToOne(optional = false)   
	@JoinColumn(name = "MODULE_ID")
	public GroupModule getModule() {
		return module;
	}
	public void setModule(GroupModule module) {
		this.module = module;
	}

}