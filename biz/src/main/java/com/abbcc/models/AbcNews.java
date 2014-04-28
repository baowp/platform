package com.abbcc.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
import com.abbcc.service.AdminService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.CategoryService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.constant.ModelType;

/**
 * AbcNews entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_NEWS")
public class AbcNews implements java.io.Serializable,EnterpriseId {

	// Fields

	private String newsId;
	private String enterpriseId;
	private String newsType;
	private String adduserId;
	private String category;
	@Field(name = "title", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String title;
	@Field(name = "content", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String content;
	private String nkey;
	private Date addTime;
	@Field(name = "state", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = StandardAnalyzer.class))
	private String state;
	private String auditId;
	private Date auditTime;
	private String imagenews;
	private String rollingnews;
	private String topnews;
	private String staticed;
	private String staticpath;
	private String display;
	private Integer sort;
	private String origin;
	private String author;
	private String domain;

	// Constructors

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/** default constructor */
	public AbcNews() {
	}

	/** minimal constructor */
	public AbcNews(String newsId) {
		this.newsId = newsId;
	}

	/** full constructor */
	public AbcNews(String newsId, String enterpriseId, String newsType,
			String adduserId, String category, String title, String content,
			String nkey, Date addTime, String state, String auditId,
			Date auditTime, String imagenews, String rollingnews,
			String topnews, String staticed, String staticpath, String display) {
		this.newsId = newsId;
		this.enterpriseId = enterpriseId;
		this.newsType = newsType;
		this.adduserId = adduserId;
		this.category = category;
		this.title = title;
		this.content = content;
		this.nkey = nkey;
		this.addTime = addTime;
		this.state = state;
		this.auditId = auditId;
		this.auditTime = auditTime;
		this.imagenews = imagenews;
		this.rollingnews = rollingnews;
		this.topnews = topnews;
		this.staticed = staticed;
		this.staticpath = staticpath;
		this.display = display;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_News", parameters = {
			@Parameter(name = "sequence", value = "SEQ_News"),
			@Parameter(name = "prefix", value = "News") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_News")
	@Column(name = "NEWS_ID", unique = true, nullable = false, length = 64)
	public String getNewsId() {
		return this.newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	
	@Transient
	public String getNewsId2(){
		return newsId.replaceFirst("^[^1-9]+", "");
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "NEWS_TYPE", length = 2)
	public String getNewsType() {
		return this.newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	@Column(name = "ADDUSER_ID", length = 64)
	public String getAdduserId() {
		return this.adduserId;
	}

	public void setAdduserId(String adduserId) {
		this.adduserId = adduserId;
	}

	@Column(name = "CATEGORY", length = 64)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "TITLE", length = 400)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "NKEY", length = 200)
	public String getNkey() {
		return this.nkey;
	}

	public void setNkey(String nkey) {
		this.nkey = nkey;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "AUDIT_ID", length = 64)
	public String getAuditId() {
		return this.auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	@Column(name = "AUDIT_TIME")
	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	@Column(name = "IMAGENEWS", length = 2)
	public String getImagenews() {
		return this.imagenews;
	}

	public void setImagenews(String imagenews) {
		this.imagenews = imagenews;
	}

	@Column(name = "ROLLINGNEWS", length = 2)
	public String getRollingnews() {
		return this.rollingnews;
	}

	public void setRollingnews(String rollingnews) {
		this.rollingnews = rollingnews;
	}

	@Column(name = "TOPNEWS", length = 2)
	public String getTopnews() {
		return this.topnews;
	}

	public void setTopnews(String topnews) {
		this.topnews = topnews;
	}

	@Column(name = "STATICED", length = 2)
	public String getStaticed() {
		return this.staticed;
	}

	public void setStaticed(String staticed) {
		this.staticed = staticed;
	}

	@Column(name = "STATICPATH", length = 200)
	public String getStaticpath() {
		return this.staticpath;
	}

	public void setStaticpath(String staticpath) {
		this.staticpath = staticpath;
	}

	@Column(name = "DISPLAY", length = 2)
	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String categoryName() {
		CategoryService categoryService = (CategoryService) BeansFactory
				.get("categoryService");
		return categoryService.findById(this.getCategory()).getName();

	}

	public AbcCategory newsCategory() {
		CategoryService categoryService = (CategoryService) BeansFactory
				.get("categoryService");
		return categoryService.findById(this.getCategory());

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


	public AbcAdmin addAdmin() {
		AdminService adminService = (AdminService) BeansFactory
				.get("adminService");
		return adminService.findById(this.getAdduserId());
	}

	public AbcAdmin auditUser() {
		AdminService adminService = (AdminService) BeansFactory
				.get("adminService");
		return adminService.findById(this.getAuditId());
	}

	public String auditTimeString() {
		return DateUtil.formatDate(this.getAuditTime(), "yyyy-MM-dd");
	}

	public String helpTitle() {
		return this.getTitle();
	}

	public String helpContent() {
		return this.getContent();
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	@SuppressWarnings("deprecation")
	public String htmlPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		return sdf.format(this.getAddTime()) + this.getNewsId() + ".html";
	}

	/**
	 * 获取去除html标签后的内容的前60个字符
	 * 
	 * @return
	 */
	public String shortContent() {
		String content = this.getContent();
		content = content.replaceAll("</?[^>]+>", ""); // 剔出了<html>的标签
		content = content.replaceAll("\\s*|\t|\r|\n", "");// 去除字符串中的空格,回车,换行符,制表符
		content = content.substring(0, content.length() > 60 ? 60 : content
				.length());
		return content;
	}

	/**
	 * 得到新闻所有的图片
	 * 
	 * @return
	 */
	public List<AbcAttachment> newsImages() {
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(this.newsId);
		attachment.setBelongType(ModelType.BC);
		attachment.setState(CommonConst.STATENORMAL);
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		return attachmentService.findByExample(attachment);
	}

	/**
	 * 得到新闻的照片
	 * 
	 * @return
	 */
	public AbcAttachment newsDisplayImage() {
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(this.newsId);
		attachment.setType(CommonConst.NEWSTITLEPICTURE);
		attachment.setState(CommonConst.STATENORMAL);
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		List<AbcAttachment> l = attachmentService.findByExample(attachment);
		if (l != null && l.size() > 0)
			return l.get(0);
		else
			return new AbcAttachment();

	}

	public String[] newsPages() {
		String[] newsPages = this
				.getContent()
				.split(
						"<div style=\"page-break-after: always;\">\r\n\t<span style=\"display: none;\">&nbsp;</span></div>\r\n");
		// ie6传过来的分页符
		if (newsPages.length == 1) {
			newsPages = this
					.getContent()
					.split(
							"<div style=\"page-break-after: always\">\r\n\t<span style=\"display: none\">&nbsp;</span></div>\r\n");
		}
		return newsPages;
	}
	public String picUrlPath(){
		AttachmentService attachmentService = (AttachmentService)BeansFactory.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(this.getNewsId());
		attachment.setType(CommonConst.NEWSTITLEPICTURE);
		List list = attachmentService.findByExample(attachment);
		AbcAttachment att = (AbcAttachment)list.iterator().next();
		return  att.getServerPath();
	}	

	public String picUrl(){
		AttachmentService attachmentService = (AttachmentService)BeansFactory.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(this.getNewsId());
		attachment.setType(CommonConst.NEWSTITLEPICTURE);
		List list = attachmentService.findByExample(attachment);
		AbcAttachment att = (AbcAttachment)list.iterator().next();
		return  att.picUrl();
	}
}