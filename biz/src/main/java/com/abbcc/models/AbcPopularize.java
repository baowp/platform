package com.abbcc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
import com.abbcc.service.AdminService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

/**
 * AbcAlbum entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index="abc")
@Entity
@Table(name = "ABC_POPULARIZE")
public class AbcPopularize implements java.io.Serializable {

	// Fields

	private String popularizeId;
	private String enterpriseId;
	private String adminId;
	@Field(name = "pName", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String pName;	
	@Field(name = "key", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String key;
	@Field(name = "content", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))    
	private String content;
	private Date addTime;
	private Date startTime;
	private Date endTime;
	private String url;
	private String state;
	private String type;
	private String enterpriseName;
	private double price;
	private String productId;
	public AbcPopularize(){
		
	}
	public AbcPopularize(String popularizeId, String type, String enterpriseId,
			String adminId, Date addTime, String pName, String content,
			Date endTime, String state, Date startTime, String url,
			double price, String enterpriseName) {
		this.popularizeId = popularizeId;
		this.type = type;
		this.enterpriseId = enterpriseId;
		this.adminId = adminId;
		this.addTime = addTime;
		this.pName = pName;
		this.content = content;
		this.endTime = endTime;
		this.state = state;
		this.enterpriseName = enterpriseName;
		this.price = price;
		this.content = content;
		this.url = url;
		this.startTime = startTime;
	}
	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Popularize", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Popularize"),
			@Parameter(name = "prefix", value = "Popularize") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Popularize")
	@Column(name = "Popularize_ID", unique = true, nullable = false, length = 64)
	public String getPopularizeId() {
		return popularizeId;
	}
	public void setPopularizeId(String popularizeId) {
		this.popularizeId = popularizeId;
	}
	@Column(name = "ENTERPRISE_ID")
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	@Column(name = "ADMIN_ID")
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name = "START_TIME")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "ENTERPRISE_NAME")
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Column(name = "product_id")
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String adminName(String adminId){
		AdminService adminService = (AdminService) BeansFactory.get("adminService");
		AbcAdmin aa = adminService.findById(adminId);
		return aa.getName();
	}

}