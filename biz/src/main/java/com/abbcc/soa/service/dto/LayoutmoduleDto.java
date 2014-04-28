package com.abbcc.soa.service.dto;

import java.io.Serializable;

import com.abbcc.models.AbcModule;
import com.abbcc.util.constant.layout.Position;

@SuppressWarnings("serial")
public class LayoutmoduleDto implements Serializable {
	private String layoutmoduleId;
	private AbcModule module;
	private String block;
	private Integer blockOrder;
	private String name;
	private String state;
	private String layout;
	private Position position;

	public String getLayoutmoduleId() {
		return layoutmoduleId;
	}

	public void setLayoutmoduleId(String layoutmoduleId) {
		this.layoutmoduleId = layoutmoduleId;
	}

	public AbcModule getModule() {
		return module;
	}

	public void setModule(AbcModule module) {
		this.module = module;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public Integer getBlockOrder() {
		return blockOrder;
	}

	public void setBlockOrder(Integer blockOrder) {
		this.blockOrder = blockOrder;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
