package com.abbcc.models;

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
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.layout.BelongPage;
import com.abbcc.util.constant.layout.Position;

/**
 * AbcLayoutmodule entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_LAYOUTMODULE")
public class AbcLayoutmodule implements java.io.Serializable {

	// Fields

	private String layoutmoduleId;
	private AbcModule module;
	private BelongPage block;
	private Integer blockOrder;
	private String name;
	private String state;
	private AbcLayout layout;
	private Position position;

	// Constructors

	/** default constructor */
	public AbcLayoutmodule() {
	}

	/** minimal constructor */
	public AbcLayoutmodule(String layoutmoduleId) {
		this.layoutmoduleId = layoutmoduleId;
	}
	
	public AbcLayoutmodule(AbcModule module,AbcLayout layout){
		this.module=module;
		this.layout=layout;
	}

	/** full constructor */
	public AbcLayoutmodule(String layoutmoduleId, AbcModule module,
			BelongPage block, Integer blockOrder, String name, String state,Position position) {
		this.layoutmoduleId = layoutmoduleId;
		this.module = module;
		this.block = block;
		this.blockOrder = blockOrder;
		this.name = name;
		this.state = state;
		this.position = position;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Layoutmodule", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Layoutmodule"),
			@Parameter(name = "prefix", value = "Layoutmod") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Layoutmodule")
	@Column(name = "LAYOUTMODULE_ID", unique = true, nullable = false, length = 64)
	public String getLayoutmoduleId() {
		return this.layoutmoduleId;
	}

	public void setLayoutmoduleId(String layoutmoduleId) {
		this.layoutmoduleId = layoutmoduleId;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "module_id")
	public AbcModule getModule() {
		return this.module;
	}

	public void setModule(AbcModule module) {
		this.module = module;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BLOCK", length = 40)
	public BelongPage getBlock() {
		return this.block;
	}

	public void setBlock(BelongPage block) {
		this.block = block;
	}

	@Column(name = "Block_ORDER", length = 2)
	public Integer getBlockOrder() {
		return this.blockOrder;
	}

	public void setBlockOrder(Integer blockOrder) {
		this.blockOrder = blockOrder;
	}

	@Column(name = "NAME", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne
	@JoinColumn(name = "layout_id")
	public AbcLayout getLayout() {
		return layout;
	}

	public void setLayout(AbcLayout layout) {
		this.layout = layout;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "POSITION")
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}