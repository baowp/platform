package com.abbcc.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.group.GroupLaythemeType;

@Entity
@Table(name = "GROUP_LAYTHEME")
public class GroupLaytheme implements Serializable {

	private static final long serialVersionUID = 1L;
	private String laythemeId;
	private Date addTime;
	private String layoutId;
	private String style;
	private GroupLaythemeType type;

	private GroupTheme theme;
	
	private JSONObject jsonStyle;

	public GroupLaytheme() {
	}

	@Id
	@GenericGenerator(name = "SEQ_GroupLaytheme", parameters = {
			@Parameter(name = "sequence", value = "SEQ_GroupLaytheme"),
			@Parameter(name = "prefix", value = "LayThe") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_GroupLaytheme")
	@Column(name = "LAYTHEME_ID", unique = true, nullable = false, length = 6)
	public String getLaythemeId() {
		return this.laythemeId;
	}

	public void setLaythemeId(String laythemeId) {
		this.laythemeId = laythemeId;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "LAYOUT_ID")
	public String getLayoutId() {
		return this.layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	@Transient
	public JSONObject getJsonStyle() {
		return jsonStyle;
	}
	public void setJsonStyle(JSONObject jsonStyle) {
		this.jsonStyle = jsonStyle;
	}
	
	@Column(name = "STYLE")
	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
		if (StringUtil.isBlank(style)) {
			this.style="{}";
		}
		jsonStyle=JSONObject.fromObject(this.style);
	}

	@Enumerated(EnumType.STRING)
	public GroupLaythemeType getType() {
		return this.type;
	}

	public void setType(GroupLaythemeType type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name = "THEME_ID")
	public GroupTheme getTheme() {
		return theme;
	}

	public void setTheme(GroupTheme theme) {
		this.theme = theme;
	}
}