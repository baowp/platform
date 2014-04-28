package com.abbcc.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.abbcc.common.CommonConst;
import com.abbcc.models.interfaces.EnterpriseId;
import com.abbcc.service.BrandService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.SupplyType;

/**
 * AbcSupply entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_SUPPLY")
public class AbcSupply implements java.io.Serializable,EnterpriseId {

	// Fields

	private String supplyId;
	private String enterpriseId;
	private SupplyType type;
	private String adduserId;
	@Field(name = "title", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String title;
	private String productId;
	@Field(name = "skey", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String skey;
	private Date addTime;
	private Date startTime;
	private Date endTime;
	private String price;
	private String unit;
	private String wdesc;
	private String brandId;
	private String contactId;
	private String folder;
	private String contact;
	private String state;
	private String domain;
	private String username;

	// Constructors

	/** default constructor */
	public AbcSupply() {
	}

	/** minimal constructor */
	public AbcSupply(String supplyId) {
		this.supplyId = supplyId;
	}

	/** full constructor */
	public AbcSupply(String supplyId, String enterpriseId, SupplyType type,
			String adduserId, String title, String productId, String skey,
			Date addTime, Date startTime, Date endTime, String price,
			String unit, String wdesc, String brandId, String contactId,
			String folder, String contact) {
		this.supplyId = supplyId;
		this.enterpriseId = enterpriseId;
		this.type = type;
		this.adduserId = adduserId;
		this.title = title;
		this.productId = productId;
		this.skey = skey;
		this.addTime = addTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
		this.unit = unit;
		this.wdesc = wdesc;
		this.brandId = brandId;
		this.contactId = contactId;
		this.folder = folder;
		this.contact = contact;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Supply", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Supply"),
			@Parameter(name = "prefix", value = "Supply") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Supply")
	@Column(name = "SUPPLY_ID", unique = true, nullable = false, length = 64)
	public String getSupplyId() {
		return this.supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}
	
	@Transient
	public String getSupplyId2(){
		return supplyId.replaceFirst("^[^1-9]+", "");
	}
	@Transient
	public String getUrl() {
		return supplyHtml(supplyId);
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "TYPE", length = 2)
	@Enumerated(value = EnumType.STRING)
	public SupplyType getType() {
		return this.type;
	}

	public void setType(SupplyType type) {
		this.type = type;
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	@Column(name = "ADDUSER_ID", length = 64)
	public String getAdduserId() {
		return this.adduserId;
	}

	public void setAdduserId(String adduserId) {
		this.adduserId = adduserId;
	}

	@Column(name = "TITLE", length = 400)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "PRODUCT_ID", length = 400)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "SKEY", length = 200)
	public String getSkey() {
		return this.skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "START_TIME")
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME")
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "PRICE", length = 16)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "UNIT", length = 80)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "WDESC")
	public String getWdesc() {
		return this.wdesc;
	}

	public void setWdesc(String wdesc) {
		this.wdesc = wdesc;
	}

	@Column(name = "BRAND_ID", length = 64)
	public String getBrandId() {
		return this.brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	@Column(name = "CONTACT_ID", length = 64)
	public String getContactId() {
		return this.contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	@Column(name = "FOLDER", length = 400)
	public String getFolder() {
		return this.folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Column(name = "CONTACT", length = 400)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public AbcEnterprise enterprise() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		return enterpriseService.findById(this.getEnterpriseId());
	}

	public String addTimeString() {
		return DateUtil.formatDate(this.getAddTime(), "yyyy-MM-dd");
	}

	public AbcUser addUser() {
		UserService userService = (UserService) BeansFactory.get("userService");
		return userService.findById(this.getAdduserId());
	}
	public String startTimeString() {
		return DateUtil.formatDate(this.getStartTime(), "yyyy-MM-dd");
	}
	public String endTimeString() {
		return DateUtil.formatDate(this.getEndTime(), "yyyy-MM-dd");
	}
	public AbcBrand brand(){
		BrandService brandService = (BrandService)BeansFactory.get("brandService");
		AbcBrand brand = brandService.findById(this.getBrandId());
		return brand;
		
	}

	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String htmlPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		return sdf.format(this.getAddTime()) + this.getSupplyId() + ".html";
	}
	public String subTextHtml(int i,String text){
		return StringUtil.subTextEdesc(i,text);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String supplyHtml(String sId){
		EnterpriseService enterpriseService = (EnterpriseService)BeansFactory.get("enterpriseService");
		UserService userService = (UserService)BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory
		.get("soaUserService");
		AbcUser au = userService.findById(enterpriseService.findById(this.getEnterpriseId()).getUserId());
		if(au!=null){
			if(au.getGrade()!=null&&au.getGrade().equals(CommonConst.USERGRADENONE)){
				return "/site/"+au.getUsername()+"/supply";
			}else if(au.getGrade()!=null&&au.getGrade().equals(CommonConst.USERGRADEONE)){
				return "http://"+au.getUsername()+"."+au.getDomain()+"/supply-detail-"+getSupplyId2()+".html";
			}else if(au.getGrade()!=null&&au.getGrade().equals(CommonConst.USERGRADETWO)){
				SoaUser su = new SoaUser();
				su.setUsername(au.getUsername());
				List<SoaUser> sou = (List) soaUserService.findByExample(su);
				if (sou.size() != 0) {
					String url = "http://" + sou.get(0).getDomain();
					return StringUtil.encode(url);
				}
			}else{
				return "";
			}
		}
		return "";
	}
}