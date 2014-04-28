package com.abbcc.merchants.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.service.EnterpriseService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "CHANNELS_INFO")
public class ChannelsInfo {
	private String infoId;
	private String enterpriseId;
	private String channelsId;
	private String userId;
	private Integer sort;
	private Date addTime;
	private String domain;
	private String esbablished;
	private String storm;
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_CHANNELS_INFO", parameters = {
			@Parameter(name = "sequence", value = "SEQ_CHANNELS_INFO"),
			@Parameter(name = "prefix", value = "info") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_CHANNELS_INFO")
	@Column(name = "info_id")
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	@Column(name = "enterprise_id")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	@Column(name = "channels_id")
	public String getChannelsId() {
		return channelsId;
	}
	public void setChannelsId(String channelsId) {
		this.channelsId = channelsId;
	}
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name = "add_time")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getEsbablished() {
		return esbablished;
	}
	public void setEsbablished(String esbablished) {
		this.esbablished = esbablished;
	}
	public String getStorm() {
		return storm;
	}
	public void setStorm(String storm) {
		this.storm = storm;
	}
	public String entName(){
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
		.get("enterpriseService");
		AbcEnterprise ae = (AbcEnterprise)enterpriseService.findById(this.getEnterpriseId());
		if(ae!=null)
			return ae.getName();
		return "";
	}
	public String sstorm(){
		if(this.getStorm().equals(CommonConst.STATEINIT))
			return "";
		else
			return "checked";
	}
	public String estate(){
		if(this.getEsbablished().equals(CommonConst.STATEINIT))
			return "";
		else
			return "checked";
	}

}
