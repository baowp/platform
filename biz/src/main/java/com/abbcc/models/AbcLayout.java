package com.abbcc.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.layout.BelongPage;
import com.abbcc.util.constant.layout.BodyType;
import com.abbcc.util.constant.layout.PieceType;
import com.abbcc.util.constant.layout.Position;

/**
 * AbcLayout entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_LAYOUT")
public class AbcLayout implements java.io.Serializable {

	// Fields

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
	private List<AbcLayoutmodule> layoutmoduleList;
	private AbcLaytheme laytheme;

	private BelongPage belongPage;
	private List<AbcLayoutmodule> headmoduleList;
	private List<AbcLayoutmodule> sidemoduleList;
	private List<AbcLayoutmodule> mainmoduleList;
	private List<AbcLayoutmodule> navmoduleList;
	private List<AbcLayoutmodule> productmoduleList;
	private List<AbcLayoutmodule> belowmoduleList;

	private JSONObject jsonContent;
	private String jsonDefault = "{modified:{},title:{},gaim:{},visit:{}}";

	private JSONObject jsonBanner;

	private String signContent;
	private JSONObject jsonSign;
	
	private JSONObject jsonFooter;

	// Constructors

	/** default constructor */
	public AbcLayout() {
	}

	/** minimal constructor */
	public AbcLayout(String layboutId) {
		this.layoutId = layboutId;
	}

	/** full constructor */
	public AbcLayout(String layboutId, String enterpriseId, String userId,
			String type, String bannerType, String bannerContent,
			BodyType bodyType, String bodyContent, String navModule,
			String footerType, String footerContent, Date updateTime,
			String state, String pagestep, String staticable) {
		this.layoutId = layboutId;
		this.enterpriseId = enterpriseId;
		this.userId = userId;
		this.type = type;
		this.bannerType = bannerType;
		this.bannerContent = bannerContent;
		this.bodyType = bodyType;
		this.bodyContent = bodyContent;
		this.navModule = navModule;
		this.footerType = footerType;
		this.footerContent = footerContent;
		this.updateTime = updateTime;
		this.state = state;
		this.pagestep = pagestep;
		this.staticable = staticable;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Layout", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Layout"),
			@Parameter(name = "prefix", value = "Layout") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Layout")
	@Column(name = "LAYOUT_ID", unique = true, nullable = false, length = 64)
	public String getLayoutId() {
		return this.layoutId;
	}

	public void setLayoutId(String layboutId) {
		this.layoutId = layboutId;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "BANNER_TYPE", length = 2)
	public String getBannerType() {
		return this.bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	@Column(name = "BANNER_CONTENT")
	public String getBannerContent() {
		return this.bannerContent;
	}

	public void setBannerContent(String bannerContent) {
		if (StringUtil.isBlank(bannerContent)) {
			bannerContent = "{}";
		}
		this.bannerContent = bannerContent;
		jsonBanner = JSONObject.fromObject(bannerContent);
	}

	@Transient
	public JSONObject getJsonBanner() {
		if (jsonBanner == null)
			setBannerContent(null);
		return jsonBanner;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "BODY_TYPE", length = 2)
	public BodyType getBodyType() {
		return this.bodyType;
	}

	public void setBodyType(BodyType bodyType) {
		this.bodyType = bodyType;
	}

	@Column(name = "BODY_CONTENT")
	public String getBodyContent() {
		return this.bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
		if (StringUtil.isBlank(bodyContent)) {
			this.bodyContent = jsonDefault;
		}

		jsonContent = JSONObject.fromObject(this.bodyContent);
	}

	@Column(name = "NAV_MODULE")
	public String getNavModule() {
		return this.navModule;
	}

	public void setNavModule(String navModule) {
		this.navModule = navModule;
	}

	@Column(name = "FOOTER_TYPE", length = 2)
	public String getFooterType() {
		return this.footerType;
	}

	public void setFooterType(String footerType) {
		this.footerType = footerType;
	}
	
	@Column(name = "FOOTER_CONTENT")
	public String getFooterContent() {
		return this.footerContent;
	}

	public void setFooterContent(String footerContent) {
		this.footerContent = footerContent;
		if (StringUtil.isBlank(footerContent)) {
			this.footerContent = "{}";
		}
		jsonFooter = JSONObject.fromObject(this.footerContent);
	}
	
	@Transient
	public JSONObject getJsonFooter() {
		if (jsonFooter == null)
			setFooterContent(null);
		return jsonFooter;
	}

	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "PAGESTEP", length = 2)
	public String getPagestep() {
		return this.pagestep;
	}

	public void setPagestep(String pagestep) {
		this.pagestep = pagestep;
	}

	@Column(name = "STATICABLE", length = 1)
	public String getStaticable() {
		return this.staticable;
	}

	public void setStaticable(String staticable) {
		this.staticable = staticable;
	}

	@OrderBy("block,blockOrder")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "layout")
	public List<AbcLayoutmodule> getLayoutmoduleList() {
		return layoutmoduleList;
	}

	public void setLayoutmoduleList(List<AbcLayoutmodule> layoutmoduleList) {
		this.layoutmoduleList = layoutmoduleList;
		clearTransientModuleList();
	}

	@Transient
	public AbcLaytheme getLaytheme() {
		return laytheme;
	}

	public void setLaytheme(AbcLaytheme laytheme) {
		this.laytheme = laytheme;
	}

	public void clearTransientModuleList() {
		headmoduleList = null;
		sidemoduleList = null;
		mainmoduleList = null;
		navmoduleList = null;
		belowmoduleList = null;
	}

	public void clearMainmoduleList() {
		mainmoduleList = null;
	}

	@Transient
	public BelongPage getBelongPage() {
		return belongPage;
	}

	public void setBelongPage(BelongPage belongPage) {
		this.belongPage = belongPage;
	}

	@Transient
	public List<AbcLayoutmodule> getHeadmoduleList() {
		if (headmoduleList != null)
			return headmoduleList;
		if (layoutmoduleList != null && layoutmoduleList.size() > 0) {
			for (AbcLayoutmodule layoutmodule : layoutmoduleList) {
				if(layoutmodule.getPosition() == null) {
					if (layoutmodule.getModule().getType() == PieceType.HD) {
						if (headmoduleList == null)
							headmoduleList = new ArrayList<AbcLayoutmodule>();
						headmoduleList.add(layoutmodule);
					}
				} else {
					if (layoutmodule.getPosition() == Position.headList) {
						if (headmoduleList == null)
							headmoduleList = new ArrayList<AbcLayoutmodule>();
						headmoduleList.add(layoutmodule);
					}
				}
			}
		}
		return headmoduleList;
	}
	
	@Transient
	public List<AbcLayoutmodule> getBelowmoduleList() {
		if (belowmoduleList != null)
			return belowmoduleList;
		if (layoutmoduleList != null && layoutmoduleList.size() > 0) {
			for (AbcLayoutmodule layoutmodule : layoutmoduleList) {
				if (layoutmodule.getPosition() == Position.belowList) {
					if (belowmoduleList == null)
						belowmoduleList = new ArrayList<AbcLayoutmodule>();
					belowmoduleList.add(layoutmodule);
				}	
			}
		}
		return belowmoduleList;
	}

	@Transient
	public List<AbcLayoutmodule> getSidemoduleList() {
		if (sidemoduleList != null)
			return sidemoduleList;
		if (layoutmoduleList != null && layoutmoduleList.size() > 0) {
			for (AbcLayoutmodule layoutmodule : layoutmoduleList) {
				if(layoutmodule.getPosition() == null) {
					if (layoutmodule.getModule().getType() == PieceType.BS) {
						if (sidemoduleList == null)
							sidemoduleList = new ArrayList<AbcLayoutmodule>();
						sidemoduleList.add(layoutmodule);
					}
				} else {
					if (layoutmodule.getPosition() == Position.content1) {
						if (sidemoduleList == null)
							sidemoduleList = new ArrayList<AbcLayoutmodule>();
						sidemoduleList.add(layoutmodule);
					}
				}
			}
		}
		return sidemoduleList;
	}

	@Transient
	public List<AbcLayoutmodule> getMainmoduleList() {
		if (mainmoduleList != null)
			return mainmoduleList;
		if (layoutmoduleList != null && layoutmoduleList.size() > 0) {
			for (AbcLayoutmodule layoutmodule : layoutmoduleList) {
				if(layoutmodule.getPosition() == null) {
					if (layoutmodule.getModule().getType() == PieceType.BM) {
						if (getBelongPage() == null) {
							if (mainmoduleList == null)
								mainmoduleList = new ArrayList<AbcLayoutmodule>();
							mainmoduleList.add(layoutmodule);
						} else if (layoutmodule.getBlock() == getBelongPage()) {
							if (mainmoduleList == null)
								mainmoduleList = new ArrayList<AbcLayoutmodule>();
							mainmoduleList.add(layoutmodule);
						}
					}
				} else {
					if (layoutmodule.getPosition() == Position.content2) {
						if (getBelongPage() == null) {
							if (mainmoduleList == null)
								mainmoduleList = new ArrayList<AbcLayoutmodule>();
							mainmoduleList.add(layoutmodule);
						} else if (layoutmodule.getBlock() == getBelongPage()) {
							if (mainmoduleList == null)
								mainmoduleList = new ArrayList<AbcLayoutmodule>();
							mainmoduleList.add(layoutmodule);
						}
					}
				}
			}
		}
		return mainmoduleList;
	}

	@Transient
	public List<AbcLayoutmodule> getNavmoduleList() {
		if (navmoduleList != null)
			return navmoduleList;
		if (layoutmoduleList != null && layoutmoduleList.size() > 0) {
			for (AbcLayoutmodule layoutmodule : layoutmoduleList) {
				if(layoutmodule.getPosition() == null) {
					if (layoutmodule.getModule().getType() == PieceType.NV) {
						if (navmoduleList == null)
							navmoduleList = new ArrayList<AbcLayoutmodule>();
						navmoduleList.add(layoutmodule);
					}
				} else {
					if (layoutmodule.getPosition() == Position.list_nav) {
						if (navmoduleList == null)
							navmoduleList = new ArrayList<AbcLayoutmodule>();
						navmoduleList.add(layoutmodule);
					}
				}
			}
		}
		return navmoduleList;
	}

	@Transient
	public List<AbcLayoutmodule> getProductmoduleList() {
		if (productmoduleList != null)
			return productmoduleList;
		if (layoutmoduleList != null && layoutmoduleList.size() > 0) {
			for (AbcLayoutmodule layoutmodule : layoutmoduleList) {
				if (layoutmodule.getModule().getType() == PieceType.PD) {
					if (productmoduleList == null)
						productmoduleList = new ArrayList<AbcLayoutmodule>();
					productmoduleList.add(layoutmodule);
				}
			}
		}
		return productmoduleList;
	}

	public void setNavmoduleList(List<AbcLayoutmodule> navmoduleList) {
		this.navmoduleList = navmoduleList;
	}

	@Transient
	public JSONObject getJsonContent() {
		if (jsonContent == null) {
			jsonContent = JSONObject.fromObject(jsonDefault);
		}

		return jsonContent;
	}

	@Column(name = "sign_content")
	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
		if (StringUtil.isBlank(signContent)) {
			this.signContent = "{}";
		}

		jsonSign = JSONObject.fromObject(this.signContent);
	}

	@Transient
	public JSONObject getJsonSign() {
		if (jsonSign == null)
			setSignContent(null);
		return jsonSign;
	}

	public void setJsonSign(JSONObject jsonSign) {
		this.jsonSign = jsonSign;
	}

}