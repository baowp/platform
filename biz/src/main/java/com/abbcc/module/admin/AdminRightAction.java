package com.abbcc.module.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.impl.CriteriaImpl.Subcriteria;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcAudit;
import com.abbcc.models.AbcCertificate;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcJob;
import com.abbcc.models.AbcMail;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcPayuser;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcUser;
import com.abbcc.module.member.MemberManageAction;
import com.abbcc.service.CertificateService;
import com.abbcc.service.JobService;
import com.abbcc.service.MailService;
import com.abbcc.service.NewsService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SupplyService;
import com.abbcc.service.UserService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;

public class AdminRightAction extends BaseAction<AbcPaylog> {
	private String certState = CommonConst.STATEINIT;
	private String newsState = CommonConst.STATEINIT;
	private String state = CommonConst.STATEINIT;
	private String supplyState = CommonConst.STATEINIT;
	private String prodState = CommonConst.STATEINIT;
	// private SupplyType supplyType = SupplyType.SE;
	private UserService userService;
	private PaylogService paylogService;
	private PayuserService payuserService;
	private NewsService newsService;
	private CertificateService certificateService;
	private JobService jobService;
	private SupplyService supplyService;
	private ProductService productService;
	@Autowired
	private MailService mailService;
	public String pageType;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setSupplyService(SupplyService supplyService) {
		this.supplyService = supplyService;
	}

	public void setCertificateService(CertificateService certificateService) {
		this.certificateService = certificateService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setPayuserService(PayuserService payuserService) {
		this.payuserService = payuserService;
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DetachedCriteria getDetachedCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		return detachedCriteria;
	}

	public PaginationSupport getAllMember() {
		DetachedCriteria detachedCriteria = this.getDetachedCriteria();
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.in("type", CommonConst.memberType)));
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport getTodayMember() {
		DetachedCriteria detachedCriteria = this.getDetachedCriteria();
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.in("type", CommonConst.memberType)));
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		Date date = new Date();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		detachedCriteria.add(Restrictions.between("addTime", date, new Date()));
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;

	}

	public PaginationSupport getFreeMember() {
		DetachedCriteria detachedCriteria = this.getDetachedCriteria();
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.in("type", CommonConst.memberType)));
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		detachedCriteria.add(Restrictions
				.eq("grade", CommonConst.USERGRADENONE));
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport getCommonMember() {
		DetachedCriteria detachedCriteria = this.getDetachedCriteria();
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.in("type", CommonConst.memberType)));
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		detachedCriteria
				.add(Restrictions.eq("grade", CommonConst.USERGRADEONE));
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport getAdvancedMember() {
		DetachedCriteria detachedCriteria = this.getDetachedCriteria();
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.in("type", CommonConst.memberType)));
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		detachedCriteria
				.add(Restrictions.eq("grade", CommonConst.USERGRADETWO));
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;

	}

	// 即将到期会员，一个月以内到期的
	public List getMonthMember() {
		List ListAdd = new ArrayList();
		DetachedCriteria detachedCriteria = this.getDetachedCriteria();
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.in("type", CommonConst.memberType)));
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		List<AbcUser> memberList = userService
				.findAllByCriteria(detachedCriteria);
		for (AbcUser user : memberList) {
			if (user.getGrade() != null
					&& (user.getGrade().equals(CommonConst.USERGRADEONE) || user
							.getGrade().equals(CommonConst.USERGRADETWO))) {
				Date date = new Date();
				if (date.getMonth() == 12) {
					date.setYear(date.getYear() + 1);
					date.setMonth(1);
				} else {
					date.setMonth(date.getMonth() + 1);
				}

				DetachedCriteria detachedCriteria1 = DetachedCriteria
						.forClass(AbcPaylog.class);
				detachedCriteria1.add(Restrictions.and(
						Restrictions.eq("userId", user.getUserId()),
						Restrictions.ne("state", CommonConst.STATEDEL)));
				detachedCriteria1.addOrder(Order.desc("paylogId"));
				/*
				 * detachedCriteria1 .add(Restrictions.between("endTime", date,
				 * new Date()));
				 */
				List<AbcPaylog> aplist = paylogService
						.findAllByCriteria(detachedCriteria1);
				if (aplist.size() > 0 && aplist.get(0).getEndTime() != null) {
					Date endDate = aplist.get(0).getEndTime();
					if (endDate.before(date) && (endDate).after(new Date())) {
						ListAdd.add(aplist.get(0).getPaylogId());
					}
				}

			}
		}
		return ListAdd;
	}

	public List getExpiredMember() {
		List ListExpirseAdd = new ArrayList();
		DetachedCriteria detachedCriteria = this.getDetachedCriteria();
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("state", CommonConst.STATEDEL),
				Restrictions.in("type", CommonConst.memberType)));
		if (!getCurrentAdmin().getUsername().equals("admin"))
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		List<AbcUser> memberList = userService
				.findAllByCriteria(detachedCriteria);
		for (AbcUser user : memberList) {
			if (user.getGrade() != null) {
				if (user.getGrade().equals(CommonConst.USERGRADEONE)
						|| user.getGrade().equals(CommonConst.USERGRADETWO)) {
					DetachedCriteria detachedCriteria1 = DetachedCriteria
							.forClass(AbcPaylog.class);
					detachedCriteria1
							.add(Restrictions.and(Restrictions.eq("userId",
									user.getUserId()), Restrictions.ne("state",
									CommonConst.STATEDEL)))
							.add(Restrictions.eq("type",
									CommonConst.PAYUSERTYPEONE))
							.addOrder(Order.desc("paylogId"));
					List<AbcPaylog> aplist = paylogService
							.findAllByCriteria(detachedCriteria1);
					if (aplist.size() > 0 && aplist.get(0).getEndTime() != null) {
						Date endDate = aplist.get(0).getEndTime();
						if (!endDate.after(new Date())) {
							ListExpirseAdd.add(aplist.get(0).getPaylogId());
						}
					}
				}
			}
		}
		return ListExpirseAdd;
	}

	public PaginationSupport getNotAuditMember() {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcPayuser.class,"user");
		detachedCriteria.add(Restrictions.eq("auditState",
				CommonConst.STATEINIT));
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria=ObjectUtil.splitSourceInDc(this.getUserIdsByDomain(), detachedCriteria, "in", "userId", 900);
		}
		detachedCriteria.addOrder(Order.desc("grade"));
		detachedCriteria.addOrder(Order.desc("applyTime"));
		pageList = payuserService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	/**
	 * 产品相关信息，主要是列出未审核信息
	 */
	public PaginationSupport getNews() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcNews.class,"user");
		detachedCriteria.add(Restrictions.eq("state", newsState));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.eq("newsType",
				CommonConst.NEWSTYPEUSER));
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		}
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = newsService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport getCert() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCertificate.class,"user");
		detachedCriteria.add(Restrictions.eq("state", certState));
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		}
		detachedCriteria.addOrder(Order.desc("addTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = certificateService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport getJob() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcJob.class,"user");
		detachedCriteria.add(Restrictions.eq("state", state));
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		}
		detachedCriteria.addOrder(Order.desc("addTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = jobService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport getSupply() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcSupply.class,"user");
		detachedCriteria.add(Restrictions.eq("state", supplyState));
		detachedCriteria.addOrder(Order.desc("addTime"));
		// detachedCriteria.add(Restrictions.eq("type", supplyType));
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		}
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = supplyService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport getProd() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class,"user");
		detachedCriteria.add(Restrictions.eq("state", prodState));
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain", getCurrentAdmin().getDomain()));
		}

		detachedCriteria.addOrder(Order.desc("addTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = productService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	private void getUserForUpdate() {
		deposit("userUpdate", uList("user").size());

	}

	private void getEntForUpdate() {
		deposit("entUpdate", uList("ent").size());
	}

	private void getEntInfoForUpdate() {
		deposit("entInfoUpdate", uList("entinfo").size());
	}

	private void getTechnicForUpdate() {
		deposit("technicUpdate", uList("technic").size());
	}

	private List uList(String className) {
		DetachedCriteria dc = DetachedCriteria.forClass(AbcAudit.class);
		dc.add(Restrictions.eq("className", className)).add(
				Restrictions.eq("state", CommonConst.STATEINIT));
		return baseService.findAllByCriteria(dc);
	}

	public String view() {
		/*
		 * try { allUserCheckMember(); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		getRequest()
				.setAttribute("memberCount", getAllMember().getTotalCount());
		getRequest().setAttribute("CommonMember",
				getCommonMember().getTotalCount());
		getRequest()
				.setAttribute("freeMember", getFreeMember().getTotalCount());
		getRequest().setAttribute("advancedMember",
				getAdvancedMember().getTotalCount());
		getRequest().setAttribute("todayMember",
				getTodayMember().getTotalCount());
		getRequest().setAttribute("monthMember", getMonthMember().size());
		getRequest().setAttribute("expiredMember", getExpiredMember().size());
		getRequest().setAttribute("notAuditMember",
				this.getNotAuditMember().getTotalCount());
		getRequest().setAttribute("news", this.getNews().getTotalCount());
		getRequest().setAttribute("cert", this.getCert().getTotalCount());
		getRequest().setAttribute("job", this.getJob().getTotalCount());
		getRequest().setAttribute("supply", this.getSupply().getTotalCount());
		getRequest().setAttribute("prod", this.getProd().getTotalCount());
		getTechnicForUpdate();
		getEntInfoForUpdate();
		getEntForUpdate();
		getUserForUpdate();
		return "view";
	}

	public String search() {
		List<AbcPaylog> plogList = new ArrayList();
		List<AbcUser> userList = new ArrayList();
		if (pageType.equals("monthMember")) {
			List payLogIdList = this.getMonthMember();
			for (int i = 0; i < payLogIdList.size(); i++) {
				AbcPaylog apl = paylogService.findById((String) payLogIdList
						.get(i));
				// userList.add(userService.findById((String)apl.getUserId()));
				plogList.add(apl);

			}
		}
		if (pageType.equals("expiredMember")) {
			List payLogIdList = this.getExpiredMember();
			for (int i = 0; i < payLogIdList.size(); i++) {
				AbcPaylog apl = paylogService.findById((String) payLogIdList
						.get(i));
				// userList.add(userService.findById((String)apl.getUserId()));
				plogList.add(apl);
			}

		}
		PaginationSupport ps = new PaginationSupport(plogList, plogList.size(),
				pageSize, startIndex);
		pageList = ps;
		return "monthMember";
	}

	private void allUserCheckMember() {
		DetachedCriteria dc = DetachedCriteria.forClass(AbcUser.class);
		if (StringUtil.isNotBlank(entity.getUserId()))
			dc.add(Restrictions.eq("userId", entity.getUserId()));
		dc.add(Restrictions.eq("domain", domain))
				.add(Restrictions.eq("state", CommonConst.STATENORMAL))
				.add(Restrictions.or(
						Restrictions.eq("grade", CommonConst.USERGRADEONE),
						Restrictions.eq("grade", CommonConst.USERGRADETWO)));
		List<AbcUser> userList = userService.findAllByCriteria(dc);
		for (AbcUser au : userList) {
			DetachedCriteria dc1 = DetachedCriteria.forClass(AbcPaylog.class);
			dc1.add(Restrictions.and(Restrictions.eq("userId", au.getUserId()),
					Restrictions.ne("state", CommonConst.STATEDEL)))
					.add(Restrictions.eq("type", CommonConst.PAYUSERTYPEONE))
					.addOrder(Order.desc("endTime"));
			List<AbcPaylog> aplist = paylogService.findAllByCriteria(dc1);
			if (aplist.size() > 0) {
				if (!aplist.get(0).getEndTime().after(new Date())) {
					if (hasSend(au)) {
						try {
							String title = "会员到期提醒";
							String content = "尊敬的用户:，你的会员账号"
									+ au.getUsername()
									+ "已经过期,到期后，您的旺铺将不能使用，为了保证客户能及时看到您的更新信息，请及时续费！<br><a href=\"http://51archetype.com/user/login.jsp\">点些登陆续费</a>";
							userService.sendMemberMail(au,
									CommonConst.MAILPAYENDREMIND, title,
									content);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					File oldFile = new File(ConfConst.FILE_UPLOAD_DIR
							+ "vipsite" + CommonConst.SEP + au.getUsername());
					File newFile = new File(ConfConst.FILE_UPLOAD_DIR
							+ "vipsite" + CommonConst.SEP + au.getUsername()
							+ "-old");
					if (oldFile.exists()) {
						oldFile.renameTo(newFile);
					}
				}
			}
		}
	}

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

}
