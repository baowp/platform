package com.abbcc.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotBlank;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConstHelper;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.UserprivService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.IDUtil;
import com.abbcc.util.StringUtil;

/**
 * AbcUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Indexed(index = "abc")
@Entity
@Table(name = "ABC_USER")
public class AbcUser implements java.io.Serializable {

	// Fields
	private String userId;
	@Field(name = "username", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	@NotBlank(message = "请输入用户名!")
	private String username;
	private String password;
	private String type;
	private String domain;
	private String grade;
	private String state;
	private String vericode;
	private Date addTime;
	private String pwdQuestion;
	private String pwdAnswer;
	private String email;
	@Field(name = "name", store = Store.NO, index = Index.TOKENIZED, analyzer = @Analyzer(impl = ChineseAnalyzer.class))
	private String name;
	private String position;
	private String dist;
	private String address;
	private String zipcode;
	private String url;
	private String qq;
	private String phone;
	private String cellphone;
	private String fax;
	private String enterpriseId;
	private String score;
	private String msn;
	private String sex;
	private AbcEnterprise enterprise;
	private String country;

	// Constructors

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/** default constructor */
	public AbcUser() {
	}

	/** minimal constructor */
	public AbcUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public AbcUser(String userId, String username, String password,
			String type, String grade, String state, String vericode,
			Date addTime, String pwdQuestion, String pwdAnswer, String email,
			String name, String position, String dist, String address,
			String zipcode, String url, String qq, String phone,
			String cellphone, String fax, String enterpriseId, String score,
			String msn) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.type = type;
		this.grade = grade;
		this.state = state;
		this.vericode = vericode;
		this.addTime = addTime;
		this.pwdQuestion = pwdQuestion;
		this.pwdAnswer = pwdAnswer;
		this.email = email;
		this.name = name;
		this.position = position;
		this.dist = dist;
		this.address = address;
		this.zipcode = zipcode;
		this.url = url;
		this.qq = qq;
		this.phone = phone;
		this.cellphone = cellphone;
		this.fax = fax;
		this.enterpriseId = enterpriseId;
		this.score = score;
		this.msn = msn;
	}

	// Property accessors
	@Id
	@DocumentId
	@GenericGenerator(name = "SEQ_User", parameters = {
			@Parameter(name = "sequence", value = "SEQ_User"),
			@Parameter(name = "prefix", value = "User") }, strategy = IDUtil.className)
	@GeneratedValue(generator = "SEQ_User")
	@Column(name = "USER_ID", unique = true, nullable = false, length = 64)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USERNAME", length = 40)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "GRADE", length = 2)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "STATE", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "VERICODE", length = 20)
	public String getVericode() {
		return this.vericode;
	}

	public void setVericode(String vericode) {
		this.vericode = vericode;
	}

	@Column(name = "ADD_TIME")
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Column(name = "PWD_QUESTION", length = 200)
	public String getPwdQuestion() {
		return this.pwdQuestion;
	}

	public void setPwdQuestion(String pwdQuestion) {
		this.pwdQuestion = pwdQuestion;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "PWD_ANSWER", length = 200)
	public String getPwdAnswer() {
		return this.pwdAnswer;
	}

	public void setPwdAnswer(String pwdAnswer) {
		this.pwdAnswer = pwdAnswer;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "POSITION", length = 100)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "DIST", length = 64)
	public String getDist() {
		return this.dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	@Column(name = "ADDRESS", length = 800)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ZIPCODE", length = 20)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "URL", length = 400)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "QQ", length = 30)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "PHONE", length = 40)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "CELLPHONE", length = 24)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "FAX", length = 40)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "ENTERPRISE_ID", length = 64)
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Column(name = "SCORE", length = 32)
	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Column(name = "MSN", length = 32)
	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Transient
	public String getUrl2() {
		if (this.url == null || this.url.equals("")) {
			return url;
		}
		String url2 = this.url;
		if (this.url.indexOf("http://") > -1) {
			url2 = this.url.replaceAll("(^http://)|(/.*$)", "");
		}
		return url2;
	}

	/**
	 * 用户所属的企业
	 * 
	 * @return
	 */
	public AbcEnterprise enterprise() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		if (this.enterpriseId != null)
			return enterpriseService.findById(this.enterpriseId);
		else
			return null;
	}

	public String publishName(String type) {
		String name = "";
		if (type.equals(CommonConst.STATADDJOB))
			return "招聘信息";
		if (type.equals(CommonConst.STATADDNEWS))
			return "新闻信息";
		if (type.equals(CommonConst.STATADDPRODUCT))
			return "产品信息";
		if (type.equals(CommonConst.STATADDSUPPLY))
			return "供求信息";

		return name;
	}

	public AbcEnterprise enterprise(String entId) {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		if (entId != null)
			return enterpriseService.findById(entId);
		else
			return null;
	}

	public String enterpriseName() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		AbcUser user = (AbcUser) session.getAttribute(CommonConst.SESSIONUSER);
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		if (user.getEnterpriseId() != null)
			return enterpriseService.findById(user.getEnterpriseId()).getName();
		else
			return null;
	}

	public String userName() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		AbcUser user = (AbcUser) session.getAttribute(CommonConst.SESSIONUSER);
		return user.getName();
	}

	public String stateName() {
		return ConstHelper.parseStatetype(this.getState());
	}

	public String typeName() {
		return ConstHelper.parseUsertype(this.getType());
	}

	public String gradeName() {
		return ConstHelper.parseUsergrade(this.getGrade());
	}

	public String addTimeString() {
		return DateUtil.formatDate(this.getAddTime());
	}

	public String payEnd() {
		PaylogService paylogService = (PaylogService) BeansFactory
				.get("paylogService");
		DetachedCriteria dc = DetachedCriteria.forClass(AbcPaylog.class);
		dc.add(Restrictions.eq("userId", getUserId()))
				.add(Restrictions.eq("state", CommonConst.STATENORMAL))
				.addOrder(Order.desc("paylogId"));
		List<AbcPaylog> paylog = paylogService.findAllByCriteria(dc);
		if (paylog.size() != 0)
			return DateUtil
					.formatDate(paylog.get(0).getEndTime(), "yyyy-MM-dd");
		else
			return "";

	}

	public String payStart() {
		PaylogService paylogService = (PaylogService) BeansFactory
				.get("paylogService");
		DetachedCriteria dc = DetachedCriteria.forClass(AbcPaylog.class);
		dc.add(Restrictions.eq("userId", getUserId()))
				.add(Restrictions.eq("state", CommonConst.STATENORMAL))
				.addOrder(Order.desc("paylogId"));
		List<AbcPaylog> paylog = paylogService.findAllByCriteria(dc);
		if (paylog.size() != 0)
			return DateUtil.formatDate(paylog.get(0).getStartTime(),
					"yyyy-MM-dd");
		else
			return "";

	}

	public String payDate() {
		PayuserService payuserService = (PayuserService) BeansFactory
				.get("payuserService");
		AbcPayuser ap = new AbcPayuser();
		ap.setUserId(this.getUserId());
		List<AbcPayuser> aps = payuserService.findByExample(ap);
		if (aps.size() != 0) {
			PaylogService paylogService = (PaylogService) BeansFactory
					.get("paylogService");
			AbcPaylog paylog = paylogService.getUserLastestPayLog(aps.get(0)
					.getPayuserId());
			if (paylog != null)
				return DateUtil.formatDate(paylog.getPayTime(), "yyyy-MM-dd");
			else
				return "";
		} else
			return "";
	}

	@Transient
	public AbcEnterprise getEnterprise() {
		if (enterprise == null)
			enterprise = enterprise();
		return enterprise;
	}

	public void setEnterprise(AbcEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String moudelName(String modelId) {
		if (modelId.equals("01")) {
			return "公司管理";
		}
		if (modelId.equals("02")) {
			return "产品管理";
		}
		if (modelId.equals("03")) {
			return "新闻管理";
		}
		if (modelId.equals("04")) {
			return "订单管理";
		}
		if (modelId.equals("05")) {
			return "会员管理";
		}
		if (modelId.equals("06")) {
			return "商业信息";
		}
		if (modelId.equals("07")) {
			return "我的钱包";
		}
		if (modelId.equals("08")) {
			return "管理旺铺";
		}
		if (modelId.equals("15")) {
			return "账号管理";
		}
		if (modelId.equals("09")) {
			return "工具箱";
		}
		if (modelId.equals("10")) {
			return "置顶菜单";
		}
		return "";
	}

	public String isCheck(String userId, String privId) {
		UserprivService userprivService = (UserprivService) BeansFactory
				.get("userprivService");
		AbcUserpriv au = new AbcUserpriv();
		au.setuserId(userId);
		au.setuserprivilegeId(privId);
		List<AbcUserpriv> list = userprivService.findByExample(au);
		if (list.size() != 0)
			return list.get(0).getState();
		return "";
	}

	public String mailPath(String mail) {
		String email = "";
		if (mail != null) {
			String[] path = mail.split("@");
			email = "http://mail" + "." + path[1];
		}

		return email;
	}

	public String entName() {
		EnterpriseService e = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		List<AbcEnterprise> ae = (List<AbcEnterprise>) e.findById(this
				.getEnterpriseId());
		if (ae.size() != 0)
			return ae.get(0).getName();
		return "";
	}

	public String stateName1() {
		if (this.getState().equals(CommonConst.STATEINIT))
			return "未审核";
		else
			return "已审核";

	}

	public String regType(String type) {
		if (type.equals("11"))
			return "个人用户";
		if (type.equals("12"))
			return "生产企业";
		if (type.equals("13"))
			return "经销商";
		if (type.equals("14"))
			return "协会组织";
		if (type.equals("15"))
			return "展会公司";
		return "";
	}

	@Transient
	public String isBroadcast() {
		EnterpriseService enterpriseService = (EnterpriseService) BeansFactory
				.get("enterpriseService");
		if (StringUtil.isNotBlank(enterpriseId)) {
			AbcEnterprise ae = enterpriseService.findById(this.enterpriseId);
			return ae.getBroadcast();
		}
		return "";
	}

	public String entLogo(String entId) {
		return "";
	}
	
	
	public String endTime() {
		List<AbcPaylog> plogList = new ArrayList();
		PaylogService paylogService = (PaylogService) BeansFactory
				.get("paylogService");
		String hql = "from AbcPaylog where userId='" +userId
				+ "' order by endTime desc";
		plogList = paylogService.findByHql(hql);
		if (plogList.size() != 0) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String endTime = df.format(plogList.get(0).getEndTime());
			String startTime = df.format(plogList.get(0).getStartTime());
			return startTime + "到" + endTime;
		} else {
			return null;
		}
	}
	

}