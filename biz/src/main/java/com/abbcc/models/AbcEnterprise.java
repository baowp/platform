package com.abbcc.models;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.module.album.WatermarkAction;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.BaseService;
import com.abbcc.service.BindService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.SupplyService;
import com.abbcc.service.SyscodeService;
import com.abbcc.service.UserService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;

/**
 * AbcEnterprise entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_ENTERPRISE")
public class AbcEnterprise implements java.io.Serializable {

	// Fields
	private String enterpriseId;
	private String userId;
	@Field(name = "name", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String name;
	@Field(name = "edesc", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String edesc;
	private String type;
	private String dist;
	private String address;
	private String businessType;
	private String mainBusiness;
	private String industry;
	private String businessPattern;
	private String regTime;
	private String legalPre;
	private String annualTurnover;
	private String staffSum;
	private String brand;
	private String registeredCapital;
	private String customer;
	private String market;
	private String annualExport;
	private String annualImport;
	private String bank;
	private String bankAccount;
	private String oem;
	private String rdSum;
	private String monthProd;
	private String factoryArea;
	private String qualityControl;
	private String qasyscert;
	private String url;
	private String icp;
	private String key;
	private String broadcast;
	private String mapaddress;


	// Constructors
	/** default constructor */
	public AbcEnterprise() {
	}

	/** minimal constructor */
	public AbcEnterprise(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/** full constructor */
	public AbcEnterprise(String enterpriseId, String userId, String name,
			String edesc, String type, String dist, String address,
			String businessType, String mainBusiness, String industry,
			String businessPattern, String regTime, String legalPre,
			String annualTurnover, String staffSum, String brand,
			String registeredCapital, String customer, String market,
			String annualExport, String annualImport, String bank,
			String bankAccount, String oem, String rdSum, String monthProd,
			String factoryArea, String qualityControl, String qasyscert) {
		this.enterpriseId = enterpriseId;
		this.userId = userId;
		this.name = name;
		this.edesc = edesc;
		this.type = type;
		this.dist = dist;
		this.address = address;
		this.businessType = businessType;
		this.mainBusiness = mainBusiness;
		this.industry = industry;
		this.businessPattern = businessPattern;
		this.regTime = regTime;
		this.legalPre = legalPre;
		this.annualTurnover = annualTurnover;
		this.staffSum = staffSum;
		this.brand = brand;
		this.registeredCapital = registeredCapital;
		this.customer = customer;
		this.market = market;
		this.annualExport = annualExport;
		this.annualImport = annualImport;
		this.bank = bank;
		this.bankAccount = bankAccount;
		this.oem = oem;
		this.rdSum = rdSum;
		this.monthProd = monthProd;
		this.factoryArea = factoryArea;
		this.qualityControl = qualityControl;
		this.qasyscert = qasyscert;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_Enterprise", parameters = {
			@Parameter(name = "sequence", value = "SEQ_Enterprise"),
			@Parameter(name = "prefix", value = "Enterp") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_Enterprise")
	@Column(name = "ENTERPRISE_ID", unique = true, nullable = false, length = 64)
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

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EDESC")
	public String getEdesc() {
		return this.edesc;
	}

	public void setEdesc(String edesc) {
		this.edesc = edesc;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "DIST", length = 64)
	public String getDist() {
		return this.dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	@Column(name = "ADDRESS", length = 400)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "BUSINESS_TYPE", length = 2)
	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "MAIN_BUSINESS", length = 400)
	public String getMainBusiness() {
		return this.mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	@Column(name = "INDUSTRY", length = 64)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "BUSINESS_PATTERN", length = 4)
	public String getBusinessPattern() {
		return this.businessPattern;
	}

	public void setBusinessPattern(String businessPattern) {
		this.businessPattern = businessPattern;
	}

	@Column(name = "REG_TIME", length = 20)
	public String getRegTime() {
		return this.regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	@Column(name = "LEGAL_PRE", length = 20)
	public String getLegalPre() {
		return this.legalPre;
	}

	public void setLegalPre(String legalPre) {
		this.legalPre = legalPre;
	}

	@Column(name = "ANNUAL_TURNOVER", length = 40)
	public String getAnnualTurnover() {
		return this.annualTurnover;
	}

	public void setAnnualTurnover(String annualTurnover) {
		this.annualTurnover = annualTurnover;
	}

	@Column(name = "STAFF_SUM", length = 16)
	public String getStaffSum() {
		return this.staffSum;
	}

	public void setStaffSum(String staffSum) {
		this.staffSum = staffSum;
	}

	@Column(name = "BRAND", length = 200)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "REGISTERED_CAPITAL", length = 40)
	public String getRegisteredCapital() {
		return this.registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	@Column(name = "CUSTOMER", length = 800)
	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Column(name = "MARKET", length = 800)
	public String getMarket() {
		return this.market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	@Column(name = "ANNUAL_EXPORT", length = 200)
	public String getAnnualExport() {
		return this.annualExport;
	}

	public void setAnnualExport(String annualExport) {
		this.annualExport = annualExport;
	}

	@Column(name = "ANNUAL_IMPORT", length = 200)
	public String getAnnualImport() {
		return this.annualImport;
	}

	public void setAnnualImport(String annualImport) {
		this.annualImport = annualImport;
	}

	@Column(name = "BANK", length = 400)
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "BANK_ACCOUNT", length = 100)
	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Column(name = "OEM", length = 2)
	public String getOem() {
		return this.oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	@Column(name = "RD_SUM", length = 24)
	public String getRdSum() {
		return this.rdSum;
	}

	public void setRdSum(String rdSum) {
		this.rdSum = rdSum;
	}

	@Column(name = "MONTH_PROD", length = 64)
	public String getMonthProd() {
		return this.monthProd;
	}

	public void setMonthProd(String monthProd) {
		this.monthProd = monthProd;
	}

	@Column(name = "FACTORY_AREA", length = 200)
	public String getFactoryArea() {
		return this.factoryArea;
	}

	public void setFactoryArea(String factoryArea) {
		this.factoryArea = factoryArea;
	}

	@Column(name = "QUALITY_CONTROL", length = 200)
	public String getQualityControl() {
		return this.qualityControl;
	}

	public void setQualityControl(String qualityControl) {
		this.qualityControl = qualityControl;
	}

	@Column(name = "QASYSCERT", length = 200)
	public String getQasyscert() {
		return this.qasyscert;
	}

	public void setQasyscert(String qasyscert) {
		this.qasyscert = qasyscert;
	}

	@Transient
	public String getUrl() {
		return new WatermarkAction().enterprisePath(this.enterpriseId);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Transient
	public String getEntAddress() {
		return entAddress(enterpriseId);
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMapaddress() {
		return mapaddress;
	}

	public void setMapaddress(String mapaddress) {
		this.mapaddress = mapaddress;
	}

	public String getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}
	public String entName(String id) {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");

		return enterpriseService.findById(id).getName();
	}

	public String entAddress(String entId) {
		String entAddress = "未填写";
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		AbcEnterprise ent = enterpriseService.findById(entId);
		if (ent != null && ent.getAddress() != null) {
			try {
				String[] address = ent.getAddress().split(",");
				if (address.length < 2)
					return entAddress;
				SyscodeService syscodeService = (SyscodeService) BeansFactory
						.get("syscodeService");

				entAddress = syscodeService.findById(address[0]).getName()
						+ "-" + syscodeService.findById(address[1]).getName();
			} catch (Exception e) {
				return entAddress;
			}

		}
		return entAddress;
	}

	public String enterprisePath(String entId) {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory
				.get("soaUserService");
		AbcEnterprise ent = enterpriseService.findById(entId);
		AbcUser userIds = userService.findById(ent.getUserId());
		SoaUser su = new SoaUser();
		su.setUsername(userIds.getUsername());
		List<SoaUser> sou = (List) soaUserService.findByExample(su);
		if (sou.size() != 0) {
			String url = "http://" + sou.get(0).getDomain();
			return "?url=" + StringUtil.encode(url);
		}

		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		String[] types = { "00", "01", "02", "03" };
		detachedCriteria.add(Restrictions.in("type", types));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("enterpriseId", entId),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if (userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)) {
				return "?url=/site/" + path;
			} else if (userList.get(0).getGrade()
					.equals(CommonConst.USERGRADEONE)) {
				return "?url=http://" + path + "."
						+ userList.get(0).getDomain();
			}
		}
		return "?url=http://" + path + ".51archetype.com";
	}

	public String enterprisePhone(String entId) {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory
				.get("soaUserService");
		AbcEnterprise ent = enterpriseService.findById(entId);
		AbcUser userIds = userService.findById(ent.getUserId());
		SoaUser su = new SoaUser();
		su.setUsername(userIds.getUsername());
		List<SoaUser> sou = (List) soaUserService.findByExample(su);
		if (sou.size() != 0) {
			String url = "http://" + sou.get(0).getDomain();
			return "?url=" + StringUtil.encode(url);
		}

		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		String[] types = { "00", "01", "02", "03" };
		detachedCriteria.add(Restrictions.in("type", types));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("enterpriseId", entId),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if (userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)) {
				return "?url=/site/" + path;
			} else if (userList.get(0).getGrade()
					.equals(CommonConst.USERGRADEONE)) {
				return "?url=http://" + path + "."
						+ userList.get(0).getDomain();
			}
		}
		return "?url=http://" + path + "." + userList.get(0).getDomain();
	}

	public String enterpriseSupper() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory
				.get("soaUserService");
		AbcEnterprise ent = enterpriseService.findById(this.getEnterpriseId());
		AbcUser userIds = userService.findById(ent.getUserId());
		SoaUser su = new SoaUser();
		su.setUsername(userIds.getUsername());
		List<SoaUser> sou = (List) soaUserService.findByExample(su);
		if (sou.size() != 0) {
			String url = sou.get(0).getDomain();
			return "?url=http://" + StringUtil.encode(url);
		}

		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		String[] types = { "00", "01", "02", "03" };
		detachedCriteria.add(Restrictions.in("type", types));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("enterpriseId", this.getEnterpriseId()),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if (userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)) {
				return "?url=/site/" + path + "/product";
			}
		}
		return "?url=http://" + path + "." + userIds.getDomain()
				+ "/supply.html";
	}

	public String supplyPath(String supplyId) {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory
				.get("soaUserService");
		SupplyService ss = (SupplyService) BeansFactory.get("supplyService");
		AbcSupply as = ss.findById(supplyId);
		if (as != null) {
			AbcUser au = userService.findById(enterpriseService.findById(
					as.getEnterpriseId()).getUserId());
			SoaUser su = new SoaUser();
			su.setUsername(au.getUsername());
			List<SoaUser> sou = (List) soaUserService.findByExample(su);
			if (sou.size() != 0) {
				String url = sou.get(0).getDomain();
				return "?url=http://" + StringUtil.encode(url);
			} else {
				if (au.getGrade().equals(CommonConst.USERGRADEONE)) {
					return "?url="
							+ StringUtil.encode("http://" + au.getUsername()
									+ "." + as.getDomain() + "/supply-detail-"
									+ supplyId.replaceFirst("^[^1-9]+", "")
									+ ".html");
				} else {
					return "?url="
							+ StringUtil.encode("http://" + as.getDomain()
									+ "/site/" + au.getUsername() + "/supply");
				}
			}
		}
		return "";
	}

	public String enterprisePath() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		BindService bd = (BindService) BeansFactory.get("bindService");
		UserService userService = (UserService) BeansFactory.get("userService");
		SoaUserService soaUserService = (SoaUserService) BeansFactory
				.get("soaUserService");
		AbcEnterprise ent = enterpriseService.findById(this.getEnterpriseId());
		AbcUser userIds = userService.findById(ent.getUserId());
		SoaUser su = new SoaUser();
		su.setUsername(userIds.getUsername());
		List<SoaUser> sou = (List) soaUserService.findByExample(su);
		if (sou.size() != 0) {
			String url = "http://" + sou.get(0).getDomain();
			return "?url=" + StringUtil.encode(url);
		}

		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		String[] types = { "00", "01", "02", "03" };
		detachedCriteria.add(Restrictions.in("type", types));
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("enterpriseId", this.getEnterpriseId()),
				Restrictions.ne("state", CommonConst.STATEDEL)));
		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if (userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)) {
				return "?url=/site/" + path;
			}
		}
		String hql = "from AbcBind where username='" + userIds.getUsername()
				+ "' and state='" + CommonConst.STATENORMAL + "'";
		@SuppressWarnings("unchecked")
		List<AbcBind> ab = (List<AbcBind>) bd.find(hql);
		if (ab.size() != 0)
			return "?url=" + ab.get(0).getAddress();

		if (userList.get(0).getGrade().equals(CommonConst.USERGRADEONE)) {
			return "?url=" + "http://" + userList.get(0).getUsername() + "."
					+ userList.get(0).getDomain();
		}
		return "?url=" + "http://" + path + ".51archetype.com";
	}

	public String businessTypeName() {
		if (this.getBusinessType() != null) {
			if (this.getBusinessType().equals("00"))
				return "生产加工";
			if (this.getBusinessType().equals("01"))
				return "经营批发";
			if (this.getBusinessType().equals("02"))
				return "招商代理";
			if (this.getBusinessType().equals("03"))
				return "商业服务";
		}
		return "还未填写";
	}

	public String productPath(String entId, String pId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		UserService userService = (UserService) BeansFactory.get("userService");
		ProductService productService = (ProductService) BeansFactory
				.get("productService");
		String path = "";
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.eq("enterpriseId", entId),
				Restrictions.eq("state", CommonConst.STATENORMAL)));

		List<AbcUser> userList = userService
				.findAllByCriteria(detachedCriteria);
		if (userList.size() != 0) {
			path = userList.get(0).getUsername();
			if (userList.get(0).getGrade().equals(CommonConst.USERGRADENONE)) {
				return "?url="
						+ StringUtil.encode("/site/"
								+ userList.get(0).getUsername()
								+ "/product_detail?productId=" + pId);
			} else {
				return "?url="
						+ StringUtil.encode("http://"
								+ userList.get(0).getUsername() + "."
								+ productService.findById(pId).getDomain()
								+ "/product-detail-"
								+ pId.replaceFirst("^[^1-9]+", "") + ".html");
			}
		}
		return "";
	}

	public String productPic(String productId) {
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		AbcAttachment attachment = new AbcAttachment();
		attachment.setBelongId(productId);
		attachment.setBelongType(ModelType.BG);
		attachment.setFiledesc("main_pic");
		List<AbcAttachment> list = attachmentService.findByExample(attachment);
		if (list.size() > 0) {
			String pic = list.get(0).getServerPath();
			return ConfConst.FILE_SVR + pic.substring(0, pic.lastIndexOf("."))
					+ "_5."
					+ pic.substring(pic.lastIndexOf(".") + 1, pic.length());
		} else {
			return ConfConst.FILE_SVR + "images" + CommonConst.SEP
					+ "no_photo.gif";
		}
	}

	public String searchContent(String text, String key) {
		int i = 50;
		String content = StringUtil.parseHTMLtoText(text);
		int wz = content.lastIndexOf(key);
		String result = "";
		if (wz == -1) {
			if (content.length() > i)
				result = content.substring(0, i);
			else
				result = content;
			return result + "....";
		}

		int length = content.length();
		content = content.substring(0, wz) + "<em>"
				+ content.substring(wz, wz + key.length()) + "</em>"
				+ content.substring(wz + key.length(), length);
		wz = content.lastIndexOf(key);
		length = content.length();
		if (wz < 20) {
			if (length - wz > 20)
				result = content.substring(0, wz + 20);

			else
				result = content.substring(0, content.length());

			return result;
		}

		else {
			if (length - wz > 20)
				result = content.substring(wz - 40, wz + 40);

			else

				result = content.substring(wz - 50 > 0 ? wz - 50
						: wz - 30 > 0 ? wz - 30 : wz - 20 > 0 ? wz - 20 : wz,
						length);

			return "...." + result + "....";
		}

	}

	public String subEdesc(int i) {
		if (this.edesc.length() > i) {
			return edesc.substring(0, 100);
		}
		return edesc;
	}

	/**
	 * 保留介绍内容
	 * 
	 * @param i
	 * @return
	 */
	public String subTextEdesc(int i) {
		String s = StringUtil.parseHTMLtoText(this.edesc);
		if (s.length() > i) {
			return s.substring(0, i).trim();
		}
		return s.trim();
	}

	/**
	 * 企业的添加人
	 * 
	 * @return
	 */
	public AbcUser addUser() {
		UserService userService = (UserService) BeansFactory.get("userService");
		return userService.findById(this.userId);
	}

	public String logoPath(String path) {
		if (path != null)
			return ConfConst.FILE_SVR + path;
		return ConfConst.FILE_SVR;
	}

	public String industyString() {
		BaseService baseService = (BaseService) BeansFactory.get("baseService");
		if (StringUtil.isNotBlank(this.industry)) {
			return ((AbcSyscode) baseService.findById(AbcSyscode.class,
					industry)).getName();
		} else {
			return "无";
		}
	}

	public String businessString() {
		if (StringUtil.isNotBlank(this.type)) {
			return type.equals("00") ? "个人用户" : type
					.equals("01") ? "企业单位" : type.equals("02") ? "个体经营"
					: type.equals("03") ? "事业单位或者团体" : "";
		} else {
			return "无";
		}
	}

	public String entLogo(String entId) {
		// 用于LOGO显示
		AbcAttachment at = new AbcAttachment();
		at.setBelongId(entId);
		at.setBelongType(ModelType.AP);
		at.setType(CommonConst.ATTACHTYPELOGO);
		@SuppressWarnings("unused")
		AttachmentService attachmentService = (AttachmentService) BeansFactory
				.get("attachmentService");
		List<AbcAttachment> logoList = attachmentService.findByExample(at);
		if (logoList.size() != 0)
			return ConfConst.FILE_SVR + logoList.get(0).getServerPath();
		return ConfConst.FILE_SVR + "images" + CommonConst.SEP + "no_photo.gif";
	}

	public static void main(String[] args) {
		String pic = "/upload/a/b/aaa.jpg";
		String url = pic.substring(0, pic.lastIndexOf(".")) + "_5."
				+ pic.substring(pic.lastIndexOf(".") + 1, pic.length());
		System.out.println(url);
	}
}