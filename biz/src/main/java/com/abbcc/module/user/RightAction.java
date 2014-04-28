package com.abbcc.module.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcBind;
import com.abbcc.models.AbcCellbind;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcInquiry;
import com.abbcc.models.AbcMessage;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcStat;
import com.abbcc.models.AbcUser;
import com.abbcc.news.models.NewsNews;
import com.abbcc.news.service.NewsNewsService;
import com.abbcc.service.BindService;
import com.abbcc.service.CellbindService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.MessageService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.PayuserService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SoaTemplateService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.StatService;
import com.abbcc.service.UserService;
import com.abbcc.util.DateUtil;
import com.abbcc.util.ObjectUtil;

public class RightAction extends BaseAction<AbcUser> {
	private MessageService messageService;
	private EnterpriseService enterpriseService;
	private CellbindService cellbindService;
	private ProductService productService;
	private PayuserService payuserService;
	private PaylogService paylogService;
	private SoaUserService soaUserService;
	private SoaTemplateService soaTemplateService;
	@Autowired
	private BindService bindService;
	@Autowired
	private StatService statService;
	@Autowired
	private NewsNewsService newsNewsService;
	private Long howdate;

	public void setHowdate(Long howdate) {
		this.howdate = howdate;
	}

	public Long getHowdate() {
		return howdate;
	}

	public void setSoaTemplateService(SoaTemplateService soaTemplateService) {
		this.soaTemplateService = soaTemplateService;
	}

	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}

	public void setPayuserService(PayuserService payuserService) {
		this.payuserService = payuserService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCellbindService(CellbindService cellbindService) {
		this.cellbindService = cellbindService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private UserService userService;

	public PaginationSupport findVisitorRecv() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria.add(Restrictions.eq("recvEnt", this.getCurrentUser()
				.getEnterpriseId()));
		detachedCriteria.add(Restrictions
				.eq("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria.add(Restrictions.eq("recvState",
				CommonConst.STATENORMAL));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		// 查询所有接收的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findVisitorNotRead() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria.add(Restrictions.eq("recvEnt", this.getCurrentUser()
				.getEnterpriseId()));
		detachedCriteria.add(Restrictions
				.eq("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria.add(Restrictions
				.eq("recvState", CommonConst.STATEINIT));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		// 查询所有未读的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findRecv() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria
				.add(Restrictions.eq("recvUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions
				.ne("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		// 查询所有接收的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findSend() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria.add(Restrictions.eq("addUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions.eq("sendState",
				CommonConst.STATENORMAL));
		detachedCriteria.add(Restrictions
				.ne("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria.addOrder(Order.desc("addTime"));
		detachedCriteria.add(Restrictions.and(
				Restrictions.ne("type", CommonConst.MESSAGESYS),
				Restrictions.ne("sendState", CommonConst.STATEDEL)));
		// 查询所有发送的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	public PaginationSupport findNotRead() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcMessage.class);
		detachedCriteria
				.add(Restrictions.eq("recvUser", this.getLoginUserId()));
		detachedCriteria.add(Restrictions
				.ne("type", CommonConst.MESSAGEBROWSER));
		detachedCriteria.add(Restrictions
				.eq("recvState", CommonConst.STATEINIT));
		detachedCriteria
				.add(Restrictions.ne("recvState", CommonConst.STATEDEL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		// 查询所有未读的消息
		pageList = messageService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		return pageList;
	}

	// 用户登陆后的提示信息
	public void hint() {
		List ListAdd = new ArrayList();
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcEnterprise.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		List<AbcEnterprise> list = enterpriseService
				.findAllByCriteria(detachedCriteria);
		if (list.size() > 0) {
			AbcEnterprise ent = list.get(0);
			if (ent.getName() == null || ent.getDist() == null
					|| ent.getAddress() == null
					|| ent.getMainBusiness() == null
					|| ent.getIndustry() == null || ent.getRegTime() == null
					|| ent.getLegalPre() == null || ent.getStaffSum() == null
					|| ent.getBank() == null || ent.getBrand() == null
					|| ent.getRegisteredCapital() == null
					|| ent.getMarket() == null || ent.getBankAccount() == null) {

				ListAdd.add(message("您的公司信息尚未填写完整!", "立即填写",
						"javascript:openCompletion()", "completion"));
			}
			if (ent.getMapaddress() == null) {
				ListAdd.add(message("您的公司地图尚未进行定位!", "立即定位",
						"javascript:openMap()", "mapbar"));
			}
			AbcUser user = userService.findById(this.getCurrentUser()
					.getUserId());
			if (user.getAddress() == null || user.getCellphone() == null
					|| user.getEmail() == null || user.getName() == null
					|| user.getPhone() == null || user.getZipcode() == null) {
				ListAdd.add(message("您的个人信息尚未填写完整!", "立即填写",
						"javascript:openUpdateuser()", "user"));
			}
			if (user.getPwdAnswer() == null) {
				ListAdd.add(message(
						"您的密码保护尚未填写!",
						"立即填写",
						"javascript:addTab('密码保护', '/user/account/password/showPasswordProtection')",
						"protection"));
			}
			/*
			 * AbcCellbind cell = new AbcCellbind();
			 * cell.setUserId(this.getCurrentUser().getUserId());
			 * cell.setState(CommonConst.STATENORMAL); List<AbcCellbind>
			 * listCellBind = cellbindService .findByExample(cell); if
			 * (listCellBind.size() == 0) { ListAdd.add(message("您尚未进行手机绑定!",
			 * "立即绑定", "javascript:addTab(\"\",\"\")/user/cellbind/view", ""));
			 * }
			 */
			AbcProduct pro = new AbcProduct();
			pro.setEnterpriseId(this.getCurrentUser().getEnterpriseId());
			List<AbcProduct> listPro = productService.findByExample(pro);
			if (listPro.size() == 0) {
				ListAdd.add(message("您尚未发布任何产品信息!", "立即发布",
						"javascript:addTab('发布产品','/user/product/add')", ""));
			}
			/*
			 * AbcInquiry ai = new AbcInquiry();
			 * ai.setRecvEnt(getCurrentUser().getEnterpriseId());
			 * ai.setState(CommonConst.STATEINIT); List<AbcInquiry> la =
			 * baseService.findByExample(ai); if(la.size()!=0){
			 * ListAdd.add("<li class=\"font04\">您有"+la.size()+
			 * "条未读的询价信息!<span class=\"font03\"><a href=\"/inquiry/view\" >立即查看</a></span></li>"
			 * ); }
			 */

			getRequest().setAttribute("hintCount", ListAdd.size());
			getRequest().setAttribute("hintList", ListAdd);
		}
	}

	public void member() {
		@SuppressWarnings("unused")
		AbcUser user = userService.findById(this.getCurrentUser().getUserId());
		String grade = user.getGrade();
		if (grade != null) {
			if (!grade.equals(CommonConst.USERGRADENONE)) {
				DetachedCriteria detachedCriteria = DetachedCriteria
						.forClass(AbcPaylog.class);
				detachedCriteria.add(
						Restrictions.and(Restrictions.eq("userId", this
								.getCurrentUser().getUserId()), Restrictions
								.eq("state", CommonConst.STATENORMAL)))
						.addOrder(Order.desc("paylogId"));
				if (grade.equals(CommonConst.USERGRADEONE))
					detachedCriteria.add(Restrictions.eq("type",
							CommonConst.PAYUSERTYPEONE));
				else if (grade.equals(CommonConst.USERGRADETWO))
					detachedCriteria.add(Restrictions.eq("type",
							CommonConst.PAYUSERTYPETHREE));
				List<AbcPaylog> aplist = paylogService
						.findAllByCriteria(detachedCriteria);
				if (aplist.size() > 0) {
					getRequest().setAttribute("payUser", aplist.get(0));

					long howdate = 0;
					if (aplist.get(0).getEndTime().after(new Date())) {
						howdate = DateUtil
								.DateTimeSpace1(
										DateUtil.formatDate(new Date()),
										DateUtil.formatDate(aplist.get(0)
												.getEndTime()));

						if (howdate < 0) {
							getSession().setAttribute("memberState", "03");
						} else {
							AbcBind ab = new AbcBind();
							ab.setUsername(user.getUsername());
							List<AbcBind> list = bindService.findByExample(ab);
							if (list.size() != 0)
								deposit("bind", list.get(0));
						}
					}
					deposit("howdate", howdate);
				}
			}
		}
	}

	// 在后台首页显示系统新闻
	@SuppressWarnings("unchecked")
	public void sysNews() {
		DetachedCriteria dc = DetachedCriteria.forClass(NewsNews.class);
		dc.add(Restrictions.eq("domain", domain));
		dc.addOrder(Order.desc("newsId"));
		List<NewsNews> nnList = newsNewsService.findAllByCriteria(dc);
		deposit("sysNewsList", nnList);
	}

	public String right() {
		getRequest().setAttribute("recvCount", this.findRecv().getTotalCount());
		getRequest().setAttribute("sendCount", this.findSend().getTotalCount());
		getRequest().setAttribute("notReadCount",
				this.findNotRead().getTotalCount());
		getRequest().setAttribute("recvSysCount",
				this.findVisitorRecv().getTotalCount());
		getRequest().setAttribute("notReadSysCount",
				this.findVisitorNotRead().getTotalCount());
		this.hint();
		this.member();
		isBind();
		return "right";

	}

	public void isBind() {
		AbcBind ab = new AbcBind();
		ab.setUsername(this.getCurrentUser().getUsername());
		ab.setState(CommonConst.STATENORMAL);
		@SuppressWarnings("unchecked")
		List<AbcBind> bindList = bindService.findByExample(ab);
		if (bindList.size() != 0)
			getRequest().setAttribute("bind", bindList.get(0));

	}

	public void ent() {
		DetachedCriteria du = DetachedCriteria.forClass(AbcUser.class);
		du.add(Restrictions.eq("state", CommonConst.STATENORMAL)).add(
				Restrictions.eq("grade", CommonConst.USERGRADEONE));
		du.add(Property.forName("userId").eqProperty("ent.userId"));
		du.add(Restrictions.isNotNull("domain"));
		du.setProjection(Property.forName("userId"));
		DetachedCriteria dc = DetachedCriteria.forClass(AbcEnterprise.class,
				"ent");
		dc.add(Subqueries.exists(du));
		dc.add(Restrictions.eq("broadcast", "01"));
		pageList = baseService.findPageByCriteria(dc, 5, startIndex);
		deposit("ent", pageList);
	}

	public String message(String txt, String btnText, String href, String id) {
		/*
		 * String message =
		 * "<li><div class=\"ic-l\"><p class=\"ic-icon-3\" style=\"color:#3399FF;\"><img src=\"/admin/images/hi.gif\"></p></div><div class=\"ic-r\"><a class=\"ic-btn\" href=\""
		 * + href + "\" id=\"" + id + "\" ><em>" + btnText +
		 * "</em></a></div><div class=\"ic-m\"><p>" + txt + "</p></div></li>";
		 */
		String msg = "<li><a href=\"" + href + "\" id=\"" + id + "\">" + txt
				+ "</a></li>";
		return msg;
	}

}
