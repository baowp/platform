package com.abbcc.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.abbcc.action.FileUploadAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.interfaces.EnterpriseId;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.CategoryService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.ProductService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;
import com.abbcc.util.constant.ProductType;

/**
 * AbcProduct entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_PRODUCT")
public class AbcProduct implements java.io.Serializable, EnterpriseId {

	// Fields

	private String productId;
	private String enterpriseId;
	private String adduserId;
	@Field(name = "name", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String name;
	private String prodtype;
	@Field(name = "pkey", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String pkey;
	private String state;
	private String category;
	private Date addTime;
	private Date endTime;
	private Float price;
	private String unit;
	private String proddesc;
	private AbcBrand brand;
	private String entType;
	private String tag;
	private String contactId;
	private String folder;
	private String publish;
	private Date publishTime;
	private Date unpublishTime;
	private String viewsum;
	private String activity;
	private ProductType type;
	private Integer sort;
	private String isdisplay;
	private String ads;
	private String loginView;
	private String domain;
	private AbcAttachment mainPic;
	private List<AbcAttachment> attachList;
	private String username;
	private String broadcast;
	private String pModel;
	private String detailTitle1;
	private String detailTitle2;
	private String detailTitle3;
	private String detail1;
	private String detail2;
	private String detail3;
	private JSONObject jsonDetail3;

	// Constructors
	/** default constructor */
	public AbcProduct() {
	}

	/** minimal constructor */
	public AbcProduct(String productId) {
		this.productId = productId;
	}

	/** full constructor */
	public AbcProduct(String productId, String enterpriseId, String adduserId,
			String name, String prodtype, String pkey, String state,
			String category, Date addTime, Date endTime, Float price,
			String unit, String proddesc, AbcBrand brand, String entType,
			String tag, String contactId, String folder, String publish,
			Date publishTime, Date unpublishTime, String viewsum,
			String activity) {
		this.productId = productId;
		this.enterpriseId = enterpriseId;
		this.adduserId = adduserId;
		this.name = name;
		this.prodtype = prodtype;
		this.pkey = pkey;
		this.state = state;
		this.category = category;
		this.addTime = addTime;
		this.endTime = endTime;
		this.price = price;
		this.unit = unit;
		this.proddesc = proddesc;
		this.brand = brand;
		this.entType = entType;
		this.tag = tag;
		this.contactId = contactId;
		this.folder = folder;
		this.publish = publish;
		this.publishTime = publishTime;
		this.unpublishTime = unpublishTime;
		this.viewsum = viewsum;
		this.activity = activity;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Product", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Product"),
			@Parameter(name = "prefix", value = "Product") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Product")
	@Column(name = "PRODUCT_ID", unique = true, nullable = false, length = 64)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Transient
	public String getProductId2() {
		return productId.replaceFirst("^[^1-9]+", "");
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "p_model")
	public String getpModel() {
		return pModel;
	}

	public void setpModel(String pModel) {
		this.pModel = pModel;
	}

	@Column(name = "ADDUSER_ID", length = 64)
	public String getAdduserId() {
		return this.adduserId;
	}

	public void setAdduserId(String adduserId) {
		this.adduserId = adduserId;
	}

	@Column(name = "NAME", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PRODTYPE", length = 100)
	public String getProdtype() {
		return this.prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	@Column(name = "PKEY", length = 200)
	public String getPkey() {
		return this.pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	@Column(name = "STATE", length = 4)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "CATEGORY", length = 64)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "END_TIME")
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "PRICE", length = 40)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "UNIT", length = 100)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "PRODDESC")
	public String getProddesc() {
		return this.proddesc;
	}

	public void setProddesc(String proddesc) {
		this.proddesc = proddesc;
	}

	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	public AbcBrand getBrand() {
		return this.brand;
	}

	public void setBrand(AbcBrand brand) {
		this.brand = brand;
	}

	@Column(name = "ENT_TYPE", length = 64)
	public String getEntType() {
		return this.entType;
	}

	public void setEntType(String entType) {
		this.entType = entType;
	}

	@Column(name = "TAG", length = 100)
	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	@Column(name = "PUBLISH", length = 2)
	public String getPublish() {
		return this.publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	@Column(name = "PUBLISH_TIME")
	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "UNPUBLISH_TIME")
	public Date getUnpublishTime() {
		return this.unpublishTime;
	}

	public void setUnpublishTime(Date unpublishTime) {
		this.unpublishTime = unpublishTime;
	}

	@Column(name = "VIEWSUM", length = 32)
	public String getViewsum() {
		return this.viewsum;
	}

	public void setViewsum(String viewsum) {
		this.viewsum = viewsum;
	}

	@Column(name = "ACTIVITY", length = 2)
	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Enumerated(EnumType.STRING)
	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsdisplay() {
		return isdisplay;
	}

	public void setIsdisplay(String isdisplay) {
		this.isdisplay = isdisplay;
	}

	@Column(name = "login_view")
	public String getLoginView() {
		return loginView;
	}

	public void setLoginView(String loginView) {
		this.loginView = loginView;
	}

	public String getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}

	@Column(name = "DETAIL_TITLE1")
	public String getDetailTitle1() {
		return detailTitle1;
	}

	public void setDetailTitle1(String detailTitle1) {
		this.detailTitle1 = detailTitle1;
	}

	@Column(name = "DETAIL_TITLE2")
	public String getDetailTitle2() {
		return detailTitle2;
	}

	public void setDetailTitle2(String detailTitle2) {
		this.detailTitle2 = detailTitle2;
	}

	@Column(name = "DETAIL_TITLE3")
	public String getDetailTitle3() {
		return detailTitle3;
	}

	public void setDetailTitle3(String detailTitle3) {
		this.detailTitle3 = detailTitle3;
	}

	@Column(name = "DETAIL1")
	public String getDetail1() {
		return detail1;
	}

	public void setDetail1(String detail1) {
		this.detail1 = detail1;
	}

	@Column(name = "DETAIL2")
	public String getDetail2() {
		return detail2;
	}

	public void setDetail2(String detail2) {
		this.detail2 = detail2;
	}

	@Column(name = "DETAIL3")
	public String getDetail3() {
		return detail3;
	}

	public void setDetail3(String detail3) {
		if (StringUtil.isBlank(detail3)) {
			detail3 = "{}";
		}
		this.detail3 = detail3;
		jsonDetail3 = JSONObject.fromObject(this.detail3);
	}

	@Transient
	public JSONObject getJsonDetail3() {
		return this.jsonDetail3;
	}

	@Transient
	public String getUrl() {
		return productPath(productId);
	}

	@Transient
	public String getProPic() {
		return new AbcEnterprise().productPic(productId);
	}
	@Transient
	public String getProductPath() {
		return productPath(productId);
	}

	@Transient
	public String getCountryImages() {
		UserService userService = (UserService) BeansFactory.get("userService");
		AbcUser a = new AbcUser();
		a.setEnterpriseId(enterpriseId);
		List<AbcUser> ae = userService.findByExample(a);
		if (ae.get(0).getCountry() != null)
			return "/home/images/company/"
					+ ae.get(0).getCountry().toLowerCase() + ".gif";
		else
			return "/home/images/company/cn.gif";
	}

	@Transient
	public String getEnDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMdd", Locale.ENGLISH);
		String times = sdf.format(addTime);
		return times;
	}

	public AbcEnterprise enterprise() {
		AbcEnterprise ae = new AbcEnterprise();
		ae.setName("未知");
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		if (StringUtil.isNotBlank(this.getEnterpriseId()))
			return enterpriseService.findById(this.getEnterpriseId());
		return ae;
	}

	public String addTimeString() {
		return DateUtil.formatDate(this.getAddTime(), "yyyy-MM-dd");
	}

	public AbcUser addUser() {

		UserService userService = (UserService) BeansFactory.get("userService");
		if (StringUtil.isNotBlank(this.getAdduserId())) {
			AbcUser au = userService.findById(this.getAdduserId());
			if (au != null) {
				return au;
			} else {
				AbcUser auu = new AbcUser();
				auu.setName("未知");
				auu.setUsername("未知");
				return auu;
			}
		} else {
			AbcUser auu = new AbcUser();
			auu.setName("未知");
			auu.setUsername("未知");
			return auu;
		}
	}

	public String categoryName() {
		AbcCategory cat = prodCategory();
		if (cat == null)
			return "";
		else
			return cat.getName();
	}

	public AbcCategory prodCategory() {
		if (StringUtil.isBlank(this.getCategory()))
			return null;
		CategoryService categoryService = (CategoryService) BeansFactory
				.get("categoryService");
		return categoryService.findById(this.getCategory());
	}

	@Transient
	public AbcAttachment getMainPic() {
		return mainPic;
	}

	public void setMainPic(AbcAttachment mainPic) {
		this.mainPic = mainPic;
	}

	@Transient
	public List<AbcAttachment> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AbcAttachment> attachList) {
		this.attachList = attachList;
	}

	public String productPic() {
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(productId);
		attachment.setBelongType(ModelType.BG);
		List list = attachmentService.findByExample(attachment);
		if (list.size() > 0) {
			System.out.println(((AbcAttachment) list.get(0)).getServerPath()
					.replaceAll(
							ConfConst.FOLDER_UPLOAD
									+ "/"
									+ FileUploadAction
											.getUserPersonalfolder(this
													.addUser().getUsername()),
							""));
			return ((AbcAttachment) list.get(0)).getServerPath().replaceAll(
					ConfConst.FOLDER_UPLOAD
							+ "/"
							+ FileUploadAction.getUserPersonalfolder(this
									.addUser().getUsername()), "");
		} else {
			return "/images/product.jpg";
		}
	}

	/**
	 * 得到产品的主图
	 * 
	 * @return
	 */
	public AbcAttachment productMainPic() {
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		AbcAttachment example = new AbcAttachment();
		example.setBelongId(this.getProductId());
		example.setBelongType(ModelType.BG);
		example.setType(CommonConst.ATTACHTYPEPIC);
		example.setFiledesc(CommonConst.ATTACHPICMAIN);
		for (AbcAttachment attach : attachmentService.findByExample(example)) {
			this.setMainPic(attach);
		}
		return this.mainPic;
	}

	public String htmlPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		return sdf.format(this.getAddTime()) + this.getProductId() + ".html";
	}

	public String getAds() {
		return ads;
	}

	public void setAds(String ads) {
		this.ads = ads;
	}

	@Transient
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String shortDesc(int number) {
		if (this.proddesc == null)
			return "";
		if (this.proddesc.length() < number)
			return this.proddesc;
		else
			return StringUtils.abbreviate(this.proddesc, number);
	}

	/**
	 * 得到同类的产品
	 * 
	 * @param num
	 * @return
	 */
	public List<AbcProduct> getSameCategoryProd(int num) {
		int span = (num + 1) / 2;
		ProductService productService = (ProductService) BeansFactory
				.get("productService");
		AbcProduct prod = new AbcProduct();
		prod.setCategory(this.getCategory());
		prod.setState(CommonConst.STATENORMAL);
		List<AbcProduct> all = productService.findByExample(prod);
		List<AbcProduct> prods = new ArrayList<AbcProduct>();
		for (int i = 0; i < all.size(); i++) {
			AbcProduct temp = all.get(i);
			if (temp.getProductId().equals(this.getProductId())) {
				prods = parseGetIndexProducts(i, span, all);
			}
		}
		return prods;
	}

	/**
	 * 得到毗邻的产品
	 * 
	 * @param i
	 * @param span
	 * @param products
	 * @return
	 */
	public List<AbcProduct> parseGetIndexProducts(int i, int span,
			List<AbcProduct> products) {
		List<AbcProduct> prods = new ArrayList<AbcProduct>();
		if (products.size() > (span * 2)) {
			if (i > span && i < (products.size() - span)) {
				for (int k = 0; k < span; k++) {
					prods.add(products.get(i - k));
					prods.add(products.get(i + k));
				}
			} else if (i > span & i >= (products.size() - span)) {
				for (int k = 0; k < (span * 2); k++) {
					prods.add(products.get(prods.size() - 1 - k));
				}
			} else {
				for (int k = 0; k < (span * 2); k++) {
					prods.add(products.get(k));
				}
			}
		} else {
			products.remove(i);
			prods = products;
		}
		return prods;
	}

	public String subTextEdesc(int i, String text) {
		String s = StringUtil.parseHTMLtoText(text);
		if (s.length() > i) {
			String newStr = s.replaceAll("(^[ |　]*|[ |　]*$)", "");
			return newStr.substring(0, i - 1);
		} else {

			return s.replaceAll("(^[ |　]*|[ |　]*$)", "");
		}
	}

	@Transient
	public String isBroadcast() {
		ProductService productService = (ProductService) BeansFactory
				.get("productService");
		if (StringUtil.isNotBlank(enterpriseId)) {
			AbcProduct ae = productService.findById(this.productId);
			return ae.getBroadcast();
		}
		return "";
	}

	public String productPath(String productId) {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		ProductService productService = (ProductService) BeansFactory
				.get("productService");
		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("enterpriseId", this.enterpriseId),
				Restrictions.eq("state", CommonConst.STATENORMAL)));
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("type", CommonConst.SUBACCOUNT),
				Restrictions.ne("type", CommonConst.SUBMEMBER)));

		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if (userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)) {
				return StringUtil.encode("/site/"
						+ userList.get(0).getUsername()
						+ "/product_detail?productId=" + productId);
			} else {
				return StringUtil.encode("http://"+userList.get(0).getUsername() + "."
						+ domain + "/product-detail-"
						+ productId.replaceFirst("^[^1-9]+", "") + ".html");
			}
		}
		return "";
	}
}