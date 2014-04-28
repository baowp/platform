package com.abbcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abbcc.util.IDUtil;

@Entity
@Table(name = "Abc_WATERMARK")
public class AbcWatermark {
	private String watermarkId;
	private String font;
	private String fontSize;
	private String fontColor;
	private String elocation;
	private String dlocation;
	private String enterpriseId;
	private String addUser;
	private Date addTime;
	

	@Id
	@GenericGenerator(name = "SEQ_WaterMark", parameters = {
			@Parameter(name = "sequence", value = "SEQ_WaterMark"),
			@Parameter(name = "prefix", value = "watermark") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_WaterMark")
	@Column(name = "WATERMARK_ID", unique = true, nullable = false, length = 64)
	public String getWatermarkId() {
		return watermarkId;
	}
	public void setWatermarkId(String watermarkId) {
		this.watermarkId = watermarkId;
	}
	public String getFont() {
		return font;
	}
	public void setFont(String font) {
		this.font = font;
	}
	@Column(name = "FONT_SIZE")
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	@Column(name = "FONT_COLOR")
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	
	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	@Column(name = "ADD_USER")
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getElocation() {
		return elocation;
	}
	public void setElocation(String elocation) {
		this.elocation = elocation;
	}
	public String getDlocation() {
		return dlocation;
	}
	public void setDlocation(String dlocation) {
		this.dlocation = dlocation;
	}
}
