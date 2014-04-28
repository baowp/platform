package com.abbcc.soa.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.abbcc.util.constant.layout.BodyType;

@SuppressWarnings("serial")
public class LayoutDto implements Serializable {
	private String layoutId;
	private String enterpriseId;
	private String userId;
	private String type;
	private String bannerType;
	private String bannerContent;
	private BodyType bodyType;
	private String bodyContent;
	private String navModule;
	private String footerType;
	private String footerContent;
	private Date updateTime;
	private String state;
	private String pagestep;
	private String staticable;
	private String signContent;
	private List<LayoutmoduleDto> layoutmoduleList;

	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	public String getBannerContent() {
		return bannerContent;
	}

	public void setBannerContent(String bannerContent) {
		this.bannerContent = bannerContent;
	}

	public BodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String getNavModule() {
		return navModule;
	}

	public void setNavModule(String navModule) {
		this.navModule = navModule;
	}

	public String getFooterType() {
		return footerType;
	}

	public void setFooterType(String footerType) {
		this.footerType = footerType;
	}

	public String getFooterContent() {
		return footerContent;
	}

	public void setFooterContent(String footerContent) {
		this.footerContent = footerContent;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPagestep() {
		return pagestep;
	}

	public void setPagestep(String pagestep) {
		this.pagestep = pagestep;
	}

	public String getStaticable() {
		return staticable;
	}

	public void setStaticable(String staticable) {
		this.staticable = staticable;
	}

	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	public List<LayoutmoduleDto> getLayoutmoduleList() {
		return layoutmoduleList;
	}

	public void setLayoutmoduleList(List<LayoutmoduleDto> layoutmoduleList) {
		this.layoutmoduleList = layoutmoduleList;
	}

}
