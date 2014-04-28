/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MemberManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-21           yixiugg                      initial
 **/

package com.abbcc.module.member;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.helper.XmlHelper;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcBind;
import com.abbcc.models.AbcBrand;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcCustom;
import com.abbcc.models.AbcEnterpcontact;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcFavourite;
import com.abbcc.models.AbcInquiry;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcLayout;
import com.abbcc.models.AbcLink;
import com.abbcc.models.AbcLog;
import com.abbcc.models.AbcMail;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcOrder;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcPayuser;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcRecycle;
import com.abbcc.models.AbcSeo;
import com.abbcc.models.AbcStat;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcSyscode;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcViewlog;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.GroupLayoutService;
import com.abbcc.service.LayoutService;
import com.abbcc.service.MailService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.SyscodeService;
import com.abbcc.service.UserService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.FileUtil;
import com.abbcc.util.StringUtil;

/**
 * *MemberManageAction.java
 */
@SuppressWarnings("serial")
public class MemberManageAction extends BaseAction<AbcUser> {
	private UserService userService;
	private String searchWord;
	public String memberState;
	public String memberGrade;
	public String entName;
	private static final String PAYLIST = "payMemberList";
	private PayuserService payuserService;
	private PaylogService paylogService;
	public String frontTime;
	public String backTime;
	public String payTime;
	public String sum;
	public String broadcast;
	public String applyContent;
	private EnterpriseService enterpriseService;
	private SyscodeService syscodeService;
	private MailService mailService;
	public String payType;
	public String paydesc;
	public String pageType;
	@Autowired
	private LayoutService layoutService;
	@Autowired
	private GroupLayoutService grouplayoutService;
	// 防止被替换掉
	String mainDomain = "abbcc" + ".net";

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setSyscodeService(SyscodeService syscodeService) {
		this.syscodeService = syscodeService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public void setPayuserService(PayuserService payuserService) {
		this.payuserService = payuserService;
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void getMapList() {
		Map<String, String> industyMap = new LinkedHashMap<String, String>();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcSyscode.class);
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.eq("type", CommonConst.SYSCODETYPEINDUSTY)));
		List<AbcSyscode> list = syscodeService
				.findAllByCriteria(detachedCriteria);
		for (AbcSyscode as : list) {
			industyMap.put(as.getSyscodeId(), as.getName());
		}
		getRequest().setAttribute("industyMap", industyMap);
	}

	public void getTypeList() {
		Map<String, String> typeMap = new LinkedHashMap<String, String>();
		typeMap.put("00", "个人用户");
		typeMap.put("01", "企业单位");
		typeMap.put("02", "个体经营");
		typeMap.put("03", "事业单位或者团体");
		getRequest().setAttribute("typeMap", typeMap);

	}

	public void getAdderssMap() {
		List<AbcSyscode> pList = new ArrayList<AbcSyscode>();
		Map<AbcSyscode, List<AbcSyscode>> sysMap = new HashMap<AbcSyscode, List<AbcSyscode>>();
		String hql = "from AbcSyscode where type='01' and (state<3 or state>3) order by type";
		List<AbcSyscode> addressList = syscodeService.findByHql(hql);
		for (int i = 0; i < addressList.size(); i++) {
			String hql1 = "from AbcSyscode where type='02' and (state<>3 and fatherdict='"
					+ addressList.get(i).getSyscodeId() + "') order by type";
			List<AbcSyscode> addressList1 = syscodeService.findByHql(hql1);

			sysMap.put(addressList.get(i), addressList1);
		}
		getRequest().setAttribute("sysMap", sysMap);
	}

	public void getBusinessType() {
		Map<String, String> BusinessTypeMap = new LinkedHashMap<String, String>();
		BusinessTypeMap.put("00", "生产加工");
		BusinessTypeMap.put("01", "经营批发");
		BusinessTypeMap.put("02", "招商代理");
		BusinessTypeMap.put("03", "商业服务");
		BusinessTypeMap.put("04", "以上都不是");
		getRequest().setAttribute("BusinessTypeMap", BusinessTypeMap);
	}

	/**
	 * 查看以及搜索
	 */
	public String view() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		if (StringUtil.isNotBlank(entity.getState())&&!entity.getState().equals("null"))
			detachedCriteria.add(Restrictions.eq("state", entity.getState()));
		if (StringUtil.isNotBlank(entity.getUsername()))
			detachedCriteria.add(Restrictions.like("username",
					entity.getUsername() + "%"));
		if (memberState != null) {
			if (memberState.equals("10"))
				detachedCriteria.add(Restrictions.ne("state",
						CommonConst.STATEDEL));
			else
				detachedCriteria.add(Restrictions.eq("state", memberState));
		}
		if (memberGrade != null) {
			if (!(memberGrade.equals("10")))
				detachedCriteria.add(Restrictions.eq("grade", memberGrade));
		}
		if (StringUtil.isNotBlank(entName)) {
			DetachedCriteria detachedCriteria1 = DetachedCriteria
					.forClass(AbcEnterprise.class);
			detachedCriteria1.add(
					Restrictions.like("name", "%" + entName + "%"))
					.setProjection(Projections.property("userId"));
			List<String> list = baseService
					.findAllByCriteria(detachedCriteria1);
			if (list.size() > 0)
				detachedCriteria.add(Restrictions.in("userId", list));

		}

		detachedCriteria.add(Restrictions.ne("type", CommonConst.SUBACCOUNT));
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("type", CommonConst.CONTACTNAME),
				Restrictions.ne("type", CommonConst.SUBMEMBER)));
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin()
					.getDomain()));
		}
		detachedCriteria.addOrder(Order.desc("addTime"));
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return INPUT;
	}

	public String info() {
		AbcEnterprise ent = enterpriseService
				.findById(entity.getEnterpriseId());
		if (ent.getAddress() != null) {
			String[] addressOld = ent.getAddress().split(",");
			getRequest().setAttribute("addressOne", addressOld[0]);
			getRequest().setAttribute("addressTwo", addressOld[1]);
		}
		this.getMapList();
		this.getTypeList();
		this.getAdderssMap();
		this.getBusinessType();
		getRequest().setAttribute("AbcUser", entity);
		getRequest().setAttribute("AbcEnterprise", ent);
		return "info";
	}

	/**
	 * 系统后台对会员进行审核
	 */
	public String pass() {
		AbcPayuser ap = new AbcPayuser();
		ap.setAuditState(CommonConst.STATENORMAL);
		ap.setPaytype(payType);
		ap.setAuditTime(new Date());
		ap.setAuditAdmin(this.getCurrentAdmin().getAdminId());
		ap.setUserId(entity.getUserId());
		ap.setSum(sum);
		ap.setApplyContent(applyContent);
		payuserService.saveOrUpdate(ap);
		if (payType != null && payType.equals(CommonConst.PAYUSERTYPEONE)) {
			// 修改用户等级
			baseService.load(entity, entity.getUserId());
			entity.setGrade(CommonConst.USERGRADEONE);
			userService.saveOrUpdate(entity);
			// 初始AbcLayout
			layoutService.initialize(entity);
		}
		AbcPaylog al = new AbcPaylog();
		al.setType(ap.getPaytype());
		al.setPayuserId(ap.getPayuserId());
		al.setState(CommonConst.STATENORMAL);
		al.setPayTime(DateUtil.strToDate(payTime));
		al.setStartTime(DateUtil.strToDate(frontTime));
		al.setEndTime(DateUtil.strToDate(backTime));
		al.setAmount(ap.getSum());
		al.setUserId(payuserService.findById(ap.getPayuserId()).getUserId());
		paylogService.save(al);
		if (payType != null && payType.equals(CommonConst.PAYUSERTYPEONE)) {
			// 恢复旺铺使用
			if (al.getEndTime().after(new Date())) {
				File oldFile = new File(ConfConst.FILE_UPLOAD_DIR + "vipsite"
						+ CommonConst.SEP + entity.getUsername());
				File newFile = new File(ConfConst.FILE_UPLOAD_DIR + "vipsite"
						+ CommonConst.SEP + entity.getUsername() + "-old");
				if (newFile.exists()) {
					newFile.renameTo(oldFile);
				}
			}
			this.savaAdminLog("会员审核", entity.getUsername(),
					CommonConst.LOGUPDATE);
		}
		if(StringUtil.isNotBlank(pageType)){
			if(pageType.equals("payend"))
				return "returnPayend";
		}
			
		return "returnShow";
	}

	/**
	 * 付费会员
	 * 
	 * @return
	 */
	public String pay() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		String[] allpayGrade = { CommonConst.USERGRADEONE,
				CommonConst.USERGRADETWO };
		if (StringUtil.isNotBlank(entity.getUsername()))
			detachedCriteria.add(Restrictions.like("username",
					entity.getUsername(), MatchMode.ANYWHERE));
		detachedCriteria.add(Restrictions.ne("type", CommonConst.SUBACCOUNT));
		detachedCriteria.add(Restrictions.ne("type", CommonConst.SUBMEMBER));
		detachedCriteria.add(Restrictions.ne("type", CommonConst.CONTACTNAME));
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin()
					.getDomain()));
		if (StringUtil.isNotBlank(paydesc)) {
			DetachedCriteria dc = DetachedCriteria.forClass(AbcPayuser.class);
			dc.add(Restrictions.like("applyContent", paydesc,
					MatchMode.ANYWHERE)).setProjection(
					Projections.property("userId"));

			List list = baseService.findAllByCriteria(dc);
			if (list.size() != 0)
				detachedCriteria.add(Restrictions.in("userId", list));
		}
		if (memberGrade != null) {
			if (memberGrade.equals(CommonConst.USERGRADEPAY)
					|| memberGrade == null || memberGrade.equals(""))
				detachedCriteria.add(Restrictions.in("grade", allpayGrade));
			else
				detachedCriteria.add(Restrictions.eq("grade", memberGrade));
		} else {
			detachedCriteria.add(Restrictions.in("grade", allpayGrade));
		}

		detachedCriteria.addOrder(Order.desc("grade")).addOrder(
				Order.desc("addTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return PAYLIST;
	}

	public String closeUser() {
		AbcUser au = userService.findById(entity.getUserId());
		if (au != null) {
			au.setGrade(CommonConst.USERGRADENONE);
			userService.saveOrUpdate(au);
			//AbcPayuser ap = new AbcPayuser();
			//ap.setUserId(au.getUserId());
			
			DetachedCriteria dc = DetachedCriteria.forClass(AbcPayuser.class);
			dc.add(Restrictions.ne("payway","02"));
			dc.add(Restrictions.eq("userId", entity.getUserId()));
			List<AbcPayuser> list=baseService.findAllByCriteria(dc);
			
			//List<AbcPayuser> list = baseService.findByExample(ap);
			for(AbcPayuser a:list){
				if(a.getAuditState().equals(CommonConst.STATEINIT))
					baseService.delete(a);
				else{
					a.setAuditState(CommonConst.STATEDEL);
					baseService.saveOrUpdate(a);
				}
			}
			FileUtil.delAllFile(ConfConst.FILE_UPLOAD_DIR + "vipsite"
					+ CommonConst.SEP + au.getUsername());

			/*
			 * File newFile = new File(ConfConst.FILE_UPLOAD_DIR + "vipsite" +
			 * CommonConst.SEP + au.getUsername() + "-old"); if
			 * (oldFile.exists()) { oldFile.renameTo(newFile); }
			 */
		}
		return pay();
	}

	public String revertLayout() {
		AbcUser au = userService.findById(entity.getUserId());
		if (au != null) {
			if(au.getGrade().equals(CommonConst.USERGRADETWO)) {
				grouplayoutService.revertLayout(au);
			} else {
				layoutService.revertLayout(au);
			}
		}
		return pay();
	}

	// 定时查找过期会员对其进行，email提醒，禁用旺铺
	public void allUserCheckMember() {
		String quartzString = CommonConst.SITEINFO.staticNumber;
		if (StringUtil.isNotBlank(quartzString)) {
			if (quartzString.equals("0")) {
				DetachedCriteria dc = DetachedCriteria.forClass(AbcUser.class);
				dc.add(Restrictions.eq("domain", domain))
						.add(Restrictions.eq("state", CommonConst.STATENORMAL))
						.add(Restrictions.or(Restrictions.eq("grade",
								CommonConst.USERGRADEONE), Restrictions.eq(
								"grade", CommonConst.USERGRADETWO)));
				List<AbcUser> userList = userService.findAllByCriteria(dc);
				for (AbcUser au : userList) {
					DetachedCriteria dc1 = DetachedCriteria
							.forClass(AbcPaylog.class);
					dc1.add(Restrictions.and(
							Restrictions.eq("userId", au.getUserId()),
							Restrictions.ne("state", CommonConst.STATEDEL)));
					dc1.addOrder(Order.desc("endTime"));
					List<AbcPaylog> aplist = paylogService
							.findAllByCriteria(dc1);
					if (aplist.size() > 0) {
						if (!aplist.get(0).getEndTime().after(new Date())) {
							if (hasSend(au)) {
								try {
									String title = "会员到期提醒";
									String content = "尊敬的用户:，你的会员账号"
											+ au.getUsername()
											+ "已经过期,到期后，您的旺铺将不能使用，为了保证客户能及时看到您的更新信息，请及时续费！<br><a href=\"http://51archetype.com/user/login.jsp\">点些登陆续费</a>";
									userService.sendMemberMail(au,
											CommonConst.MAILPAYENDREMIND,
											title, content);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							File oldFile = new File(ConfConst.FILE_UPLOAD_DIR
									+ "vipsite" + CommonConst.SEP
									+ au.getUsername());
							File newFile = new File(ConfConst.FILE_UPLOAD_DIR
									+ "vipsite" + CommonConst.SEP
									+ au.getUsername() + "-old");
							if (oldFile.exists()) {
								oldFile.renameTo(newFile);
							}
						}
					}
				}
				CommonConst.SITEINFO.staticNumber = "1";
				XmlHelper.setSiteInfoXml(CommonConst.SITEINFO,
						CommonConst.SITEINFOFILEPATH);
			} else {
				CommonConst.SITEINFO.staticNumber = "0";
				XmlHelper.setSiteInfoXml(CommonConst.SITEINFO,
						CommonConst.SITEINFOFILEPATH);
			}
		}
	}

	/**
	 * 搜索
	 * 
	 * @return
	 */
	public String search() {
		List<AbcUser> searchList = (List<AbcUser>) this.getSession()
				.getAttribute("adminSearchUserList");
		if (searchList == null)
			searchList = userService.findUserByAdminSearch(searchWord);
		List<AbcUser> items = new ArrayList<AbcUser>();
		this.startIndex = (this.page - 1) * pageSize;
		for (int i = startIndex; i < startIndex + pageSize; i++) {
			if (i > (searchList.size() - 1))
				break;
			AbcUser user = searchList.get(i);
			items.add(user);
		}
		pageList = new PaginationSupport(items, searchList.size(), pageSize,
				startIndex);
		return INPUT;
	}

	/**
	 * 搜索
	 * 
	 * @return
	 */
	public String searchPay() {
		List<AbcUser> searchList = (List<AbcUser>) this.getSession()
				.getAttribute("adminSearchUserList");
		if (searchList == null)
			searchList = userService.findUserByAdminSearch(searchWord);
		for (AbcUser user : searchList) {
			if (user.getGrade() == null
					|| user.getGrade().equals(CommonConst.USERGRADENONE))
				searchList.remove(user);
		}
		List<AbcUser> items = new ArrayList<AbcUser>();
		this.startIndex = (this.page - 1) * pageSize;
		for (int i = startIndex; i < startIndex + pageSize; i++) {
			if (i > (searchList.size() - 1))
				break;
			AbcUser user = searchList.get(i);
			items.add(user);
		}
		pageList = new PaginationSupport(items, searchList.size(), pageSize,
				startIndex);
		return PAYLIST;
	}

	/**
	 * 判断是否在30天内有发送过的记录
	 * 
	 * @param au
	 * @return
	 */
	public boolean hasSend(AbcUser au) {
		Calendar cal = Calendar.getInstance();
		Date startDate = new Date(cal.getTime().getTime()
				- (1000 * 60 * 60 * 24 * 29L));
		DetachedCriteria dc = DetachedCriteria.forClass(AbcMail.class);
		dc.add(Restrictions.eq("receiver", au.getEmail()))
				.add(Restrictions.eq("type", CommonConst.MAILPAYENDREMIND))
				.addOrder(Order.desc("mailId"));
		List<AbcMail> al = mailService.findAllByCriteria(dc);
		if (al.size() != 0) {
			if (al.get(0).getAddTime().getTime() - startDate.getTime() > 0)
				return false;

		}

		return true;
	}

	/**
	 * 设置企业是否推荐00,不推荐，01推荐
	 * 
	 * @return
	 */
	public void setBroadcast() {
		if (StringUtil.isNotBlank(entity.getEnterpriseId())) {
			AbcEnterprise ae = enterpriseService.findById(entity
					.getEnterpriseId());
			if (StringUtil.isNotBlank(broadcast)) {
				ae.setBroadcast(broadcast.equals("00") ? "01" : "00");
			} else {
				ae.setBroadcast("01");
			}
			enterpriseService.saveOrUpdate(ae);
		}
	}

	/**
	 * 删除会员及其所有关联资料
	 * 
	 * @return
	 */
	public String del() {
		if (StringUtil.isNotBlank(entity.getUserId())) {
			DetachedCriteria newsIds = DetachedCriteria.forClass(AbcNews.class);
			newsIds.add(
					Restrictions.eq("enterpriseId", entity.getEnterpriseId()))
					.setProjection(Projections.property("newsId"));
			List newsIdList = baseService.findAllByCriteria(newsIds);

			DetachedCriteria certIds = DetachedCriteria
					.forClass(AbcCertificate.class);
			certIds.add(
					Restrictions.eq("enterpriseId", entity.getEnterpriseId()))
					.setProjection(Projections.property("certificateId"));
			List certIdList = baseService.findAllByCriteria(certIds);

			AbcAlbum album = new AbcAlbum();
			album.setBelongId(entity.getEnterpriseId());
			List<AbcAlbum> albumList = baseService.findByExample(album);
			if (StringUtil.isNotBlank(entity.getEnterpriseId())) {
				for (AbcAlbum aa : albumList) {
					baseService.delete(aa);
				}
			}

			AbcBind bind = new AbcBind();
			bind.setUsername(entity.getUsername());
			List<AbcBind> bindList = baseService.findByExample(bind);
			if (StringUtil.isNotBlank(entity.getUsername()))
				for (AbcBind aa : bindList) {
					baseService.delete(aa);
				}

			AbcBrand brand = new AbcBrand();
			brand.setEnterpriseId(entity.getEnterpriseId());
			List<AbcBrand> brandList = baseService.findByExample(brand);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcBrand aa : brandList) {
					baseService.delete(aa);
				}

			AbcCategory category = new AbcCategory();
			category.setEnterpriseId(entity.getEnterpriseId());
			List<AbcCategory> categoryList = baseService
					.findByExample(category);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcCategory aa : categoryList) {
					baseService.delete(aa);
				}

			AbcCertificate cert = new AbcCertificate();
			cert.setEnterpriseId(entity.getEnterpriseId());
			List<AbcCertificate> certList = baseService.findByExample(cert);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcCertificate aa : certList) {
					baseService.delete(aa);
				}

			AbcCustom custom = new AbcCustom();
			custom.setEnterpriseId(entity.getEnterpriseId());
			List<AbcCustom> customList = baseService.findByExample(custom);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcCustom aa : customList) {
					baseService.delete(aa);
				}
			AbcEnterpcontact cent = new AbcEnterpcontact();
			cent.setEnterpriseId(entity.getEnterpriseId());
			List<AbcEnterpcontact> centList = baseService.findByExample(cent);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcEnterpcontact aa : centList) {
					baseService.delete(aa);
				}
			AbcFavourite af = new AbcFavourite();
			af.setUserId(entity.getUserId());
			List<AbcFavourite> afList = baseService.findByExample(af);
			for (AbcFavourite aa : afList) {
				baseService.delete(aa);
			}
			AbcInquiry inquiry = new AbcInquiry();
			inquiry.setRecvEnt(entity.getEnterpriseId());
			List<AbcInquiry> inquiryList = baseService.findByExample(inquiry);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcInquiry aa : inquiryList) {
					baseService.delete(aa);
				}
			AbcJob job = new AbcJob();
			job.setEnterpriseId(entity.getEnterpriseId());
			List<AbcJob> jobList = baseService.findByExample(job);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcJob aa : jobList) {
					baseService.delete(aa);
				}
			try {

				AbcLayout layout = new AbcLayout();
				layout.setEnterpriseId(entity.getEnterpriseId());
				List<AbcLayout> layoutList = baseService.findByExample(layout);
				if (StringUtil.isNotBlank(entity.getEnterpriseId()))
					for (AbcLayout aa : layoutList) {
						baseService.delete(aa);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			AbcLink link = new AbcLink();
			link.setEnterpriseId(entity.getEnterpriseId());
			List<AbcLink> linkList = baseService.findByExample(link);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcLink aa : linkList) {
					baseService.delete(aa);
				}
			AbcLog log = new AbcLog();
			log.setEnterpriseId(entity.getEnterpriseId());
			List<AbcLog> logList = baseService.findByExample(log);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcLog aa : logList) {
					baseService.delete(aa);
				}
			AbcNews news = new AbcNews();
			news.setEnterpriseId(entity.getEnterpriseId());
			List<AbcNews> newsList = baseService.findByExample(news);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcNews aa : newsList) {
					baseService.delete(aa);
				}
			try {
				AbcOrder order = new AbcOrder();
				order.setOrderEnt((AbcEnterprise) baseService.findById(
						AbcEnterprise.class, entity.getEnterpriseId()));
				List<AbcOrder> orderList = baseService.findByExample(order);
				if (StringUtil.isNotBlank(entity.getEnterpriseId()))
					for (AbcOrder aa : orderList) {
						baseService.delete(aa);
					}

			} catch (Exception e) {
				e.printStackTrace();
			}
			AbcPayuser payuser = new AbcPayuser();
			payuser.setUserId(entity.getUserId());
			List<AbcPayuser> payuserList = baseService.findByExample(payuser);
			for (AbcPayuser aa : payuserList) {
				baseService.delete(aa);
			}
			AbcPaylog paylog = new AbcPaylog();
			paylog.setUserId(entity.getUserId());
			List<AbcPaylog> paylogList = baseService.findByExample(paylog);
			for (AbcPaylog aa : paylogList) {
				baseService.delete(aa);
			}
			try {
				AbcProduct product = new AbcProduct();
				product.setEnterpriseId(entity.getEnterpriseId());
				List<AbcProduct> productList = baseService
						.findByExample(product);
				if (StringUtil.isNotBlank(entity.getEnterpriseId()))
					for (AbcProduct aa : productList) {
						baseService.delete(aa);
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			AbcRecycle recycle = new AbcRecycle();
			recycle.setUserId(entity.getUserId());
			List<AbcRecycle> recycleList = baseService.findByExample(recycle);
			for (AbcRecycle aa : recycleList) {
				baseService.delete(aa);
			}
			AbcSeo seo = new AbcSeo();
			seo.setEnterpriseId(entity.getEnterpriseId());
			List<AbcSeo> seoList = baseService.findByExample(seo);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcSeo aa : seoList) {
					baseService.delete(aa);
				}
			AbcStat stat = new AbcStat();
			stat.setEnterpriseId(entity.getEnterpriseId());
			List<AbcStat> statList = baseService.findByExample(stat);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcStat aa : statList) {
					baseService.delete(aa);
				}
			AbcSupply supply = new AbcSupply();
			supply.setEnterpriseId(entity.getEnterpriseId());
			List<AbcSupply> supplyList = baseService.findByExample(supply);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcSupply aa : supplyList) {
					baseService.delete(aa);
				}
			AbcViewlog viewlog = new AbcViewlog();
			viewlog.setBeviewId(entity.getEnterpriseId());
			List<AbcViewlog> viewlogList = baseService.findByExample(viewlog);
			if (StringUtil.isNotBlank(entity.getEnterpriseId()))
				for (AbcViewlog aa : viewlogList) {
					baseService.delete(aa);
				}
			try {

				if (StringUtil.isNotBlank(entity.getEnterpriseId())) {
					DetachedCriteria dc = DetachedCriteria
							.forClass(AbcAttachment.class);
					dc.add(Restrictions.or(Restrictions.or(
							Restrictions.in("belongId", newsIdList),
							Restrictions.in("belongId", certIdList)),
							Restrictions.or(
									Restrictions.eq("belongId",
											entity.getEnterpriseId()),
									Restrictions.eq("userId",
											entity.getUserId()))));
					List<AbcAttachment> al = baseService.findAllByCriteria(dc);
					for (AbcAttachment att : al) {
						FileUtil.delAllFile(att.getServerPath());
						baseService.delete(att);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (StringUtil.isNotBlank(entity.getEnterpriseId())){
				AbcEnterprise ae = (AbcEnterprise) baseService.findById(AbcEnterprise.class,entity.getEnterpriseId());
				baseService.delete(ae);
			}
				
			baseService.delete(entity);
		}
		entity.setUsername("");
		return view();
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getMemberState() {
		return memberState;
	}

	public void setMemberState(String memberState) {
		this.memberState = memberState;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

}
