package com.abbcc.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;

/**
 * The persistent class for the GROUP_LAYMOD database table.
 * 
 */
@Entity
@Table(name = "GROUP_WIDTH")
public class GroupWidth implements Serializable {

	private static final long serialVersionUID = 1L;
	private String widthId;
	private String page;
	private Integer one;
	private Integer two;
	private Integer three;

	private String layoutId;

	public GroupWidth() {
		super();
	}

	@Id
	@GenericGenerator(name = "SEQ_GroupWidth", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GroupWidth"),
			@Parameter(name = "prefix", value = "GroupWidth") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GroupWidth")
	@Column(name = "WIDTH_ID", unique = true, nullable = false, length = 64)
	public String getWidthId() {
		return widthId;
	}

	public void setWidthId(String widthId) {
		this.widthId = widthId;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getOne() {
		return one;
	}

	public void setOne(Integer one) {
		this.one = one;
	}

	public Integer getTwo() {
		return two;
	}

	public void setTwo(Integer two) {
		this.two = two;
	}

	public Integer getThree() {
		return three;
	}

	public void setThree(Integer three) {
		this.three = three;
	}

	@Column(name = "LAYOUT_ID")
	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}
}