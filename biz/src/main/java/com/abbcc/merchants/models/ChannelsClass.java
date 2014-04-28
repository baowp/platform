package com.abbcc.merchants.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.util.IDUtil;

@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "CHANNELS_CLASS")
public class ChannelsClass {
	private String channelsId;
	private String name;
	private Integer sort;
	private String domain;
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_CHANNELS_CLASS", parameters = {
			@Parameter(name = "sequence", value = "SEQ_CHANNELS_CLASS"),
			@Parameter(name = "prefix", value = "class") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_CHANNELS_CLASS")
	@Column(name = "channels_id")
	public String getChannelsId() {
		return channelsId;
	}
	public void setChannelsId(String channelsId) {
		this.channelsId = channelsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String cN(String type){
		if(type.equals("1")){
			if(this.getName().equals("钢木门"))
				return "28";
			if(this.getName().equals("复合门"))
				return "34";
			if(this.getName().equals("仿铜门"))
				return "35";
			if(this.getName().equals("免漆门"))
				return "36";
			if(this.getName().equals("防火门"))
				return "37";
			if(this.getName().equals("非标门"))
				return "31";
			if(this.getName().equals("防盗门"))
				return "29";
			if(this.getName().equals("室内门"))
				return "30";
			if(this.getName().equals("木门"))
				return "32";
			
			if(this.getName().equals("不锈钢门"))
				return "33";
			if(this.getName().equals("钢木室内门"))
				return "38";
			if(this.getName().equals("生态门"))
				return "39";
			if(this.getName().equals("安全门/钢质门"))
				return "40";
			if(this.getName().equals("强化木门"))
				return "41";
		}else{
			if(this.getName().equals("钢木门"))
				return "/home/images/top/aaa_28.jpg";
			if(this.getName().equals("复合门"))
				return "/home/images/top/aaa_34.jpg";
			if(this.getName().equals("仿铜门"))
				return "/home/images/top/aaa_35.jpg";
			if(this.getName().equals("免漆门"))
				return "/home/images/top/aaa_36.jpg";
			if(this.getName().equals("防火门"))
				return "/home/images/top/aaa_37.jpg";
			if(this.getName().equals("非标门"))
				return "/home/images/top/aaa_31.jpg";
			if(this.getName().equals("防盗门"))
				return "/home/images/top/aaa_29.jpg";
			if(this.getName().equals("室内门"))
				return "/home/images/top/aaa_30.jpg";
			if(this.getName().equals("木门"))
				return "/home/images/top/aaa_32.jpg";
			
			if(this.getName().equals("不锈钢门"))
				return "/home/images/top/aaa_33.jpg";
			if(this.getName().equals("钢木室内门"))
				return "/home/images/top/aaa_38.jpg";
			if(this.getName().equals("生态门"))
				return "/home/images/top/aaa_39.jpg";
			if(this.getName().equals("安全门/钢质门"))
				return "/home/images/top/aaa_40.jpg";
			if(this.getName().equals("强化木门"))
				return "/home/images/top/aaa_41.jpg";
		}
		return "";
	}
}
