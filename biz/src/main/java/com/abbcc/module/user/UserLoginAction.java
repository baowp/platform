/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminLoginAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-5           yixiugg                      initial
 **/

package com.abbcc.module.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.ActionConst;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCustom;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcMenu;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcUserpriv;
import com.abbcc.models.AbcUserprivilege;
import com.abbcc.service.AlbumService;
import com.abbcc.service.AttachmentService;
import com.abbcc.service.CustomService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.LogService;
import com.abbcc.service.NewsService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.UserService;
import com.abbcc.service.UserprivService;
import com.abbcc.service.UserprivilegeService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;
import com.opensymphony.xwork2.ActionContext;

/**
 * *AdminLoginAction.java
 */
@SuppressWarnings({ "unchecked", "serial" })
public class UserLoginAction extends BaseAction<AbcUser> {

	/**
	 * serial id
	 */
	@Autowired
	private UserprivService userprivService;
	@Autowired
	private UserService userService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private PaylogService paylogService;
	@Autowired
	private CustomService customService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private LogService logService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private AttachmentService attachmentService;
	public String pageType;
	public String loginPage;

	public String getLoginPage() {
		return loginPage;
	}

	public String getPageType() {
		return pageType;
	}

	Map<String, String> albumList = new LinkedHashMap<String, String>();

	private String message;
	private String valiCode;

	public String getValiCode() {
		return valiCode;
	}

	public void setValiCode(String valiCode) {
		this.valiCode = valiCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	public String login() {
		
		HttpSession session = this.getSession();
		if (StringUtil.isBlank(pageType)) {
			if (!this.valiCode.equalsIgnoreCase((String) session
					.getAttribute(CommonConst.VERICODE))) {
				this.addFieldError("valiCode", "图片验证码错误！");
				if(StringUtil.isNotBlank(loginPage))
					return "login2";
				return INPUT;
			}
		}
		String password = entity.getPassword();
		String password2 = password;
		password = MD5EncryptUtil.md5Encry(password);
		password2 = MD5EncryptUtil.md5Encry16(password2);
		AbcUser temp = userService.findSeniorUser(entity.getUsername(),
				password, password2);
		if (temp == null) {
			this.addFieldError("topError", "用户名或密码错误！");
			if(StringUtil.isNotBlank(loginPage))
				return "login2";
			return INPUT;
		}
		if (temp.getState().equals(CommonConst.STATEDEL)
				|| temp.getState().equals(CommonConst.STATEDENY)) {

			this.addFieldError("topError", "用户被删除或被屏蔽！");
			if(StringUtil.isNotBlank(loginPage))
				return "login2";
			return INPUT;
		} else if (temp.getState().equals(CommonConst.STATEINIT)) {
			this.addFieldError(
					"topError",
					"帐户未激活！<a href=\"#\" onclick=sendMail(\""
							+ temp.getUserId() + "\")>点此重发邮件</a>,或联系0579-87171989激活");
			if(StringUtil.isNotBlank(loginPage))
				return "login2";
			return INPUT;
		} else if (temp.getState().equals(CommonConst.STATENORMAL)) {
			if (temp.getType().equals(CommonConst.SUBMEMBER)) {
				this.addFieldError("topError", "用户名或密码错误！");
				if(StringUtil.isNotBlank(loginPage))
					return "login2";
				return INPUT;
			}
			String userId = temp.getUserId();
			session.setAttribute(CommonConst.SESSIONLOGINUSERID, userId);

			// 判断当企业信息为空且用户类型为二级用户的时候执行
			if ("04".equals(temp.getType())) {
				// 权限检查
				AbcUserpriv auser = new AbcUserpriv();
				auser.setuserId(userId);
				auser.setState(CommonConst.STATENORMAL);
				List<AbcUserpriv> privList = (List<AbcUserpriv>) userprivService
						.findByExample(auser);

				session.setAttribute(CommonConst.SESSIONUSERPRIVILEGE, privList);
				AbcEnterprise entUser = enterpriseService.findById(temp
						.getEnterpriseId());
				AbcUser userSession = (AbcUser) userService.findById(entUser
						.getUserId());
				session.setAttribute(CommonConst.SESSIONUSER, userSession);
				session.setAttribute(CommonConst.SESSIONENT,
						enterpriseService.findById(temp.getEnterpriseId()));
				session.setAttribute(CommonConst.SESSIONSUBACCOUNT, temp);
				logService.savaLog("帐号", temp.getUsername(),
						CommonConst.LOGLOGIN);
			} else {
				ServletContext application = session.getServletContext();
				List onlineUserList = new ArrayList();
				application.setAttribute("onlineUserList",
						onlineUserList.add(temp.getUsername()));
				// 用于自定义菜单的显示
				String hql = "from AbcCustom where enterpriseId='"
						+ temp.getEnterpriseId() + "' and display='"
						+ CommonConst.DISPLAY + "' order by sort desc";
				List<AbcCustom> customList = customService.findByHql(hql);
				session.setAttribute("customList", customList);
				// 用于系统公告显示
				String announceHql = "from AbcNews where newsType='"
						+ CommonConst.NEWSANNOUNCE + "' order by addTime desc";
				List<AbcNews> news = newsService.findByHql(announceHql);
				session.setAttribute("announceList", news);
				// 用于相册显示
				String albumHql = "from AbcAlbum where belongId='"
						+ temp.getEnterpriseId() + "' and state='"
						+ CommonConst.STATENORMAL + "' order by addTime desc";
				List<AbcAlbum> album = albumService.findByHql(albumHql);
				if (album.size() != 0) {
					for (AbcAlbum al : album) {
						albumList.put(al.getAlbumId(), al.getName());
					}
				}
				session.setAttribute("albumList", albumList);

				AbcEnterprise enterprise = enterpriseService.findById(temp
						.getEnterpriseId());
				session.setAttribute(CommonConst.SESSIONENT, enterprise);
				session.setAttribute(CommonConst.SESSIONUSER, temp);
				// 上门网经销商权限
				if (isExpired(temp, CommonConst.PAYUSERTYPETWO))
					session.setAttribute("dealer", "00");
				else
					session.setAttribute("dealer", "01");
				System.out.println(session.getAttribute("dealer"));

				// 判断付费用户是否过期
				String grade = temp.getGrade();
				if (grade != null) {
					if (!grade.equals(CommonConst.USERGRADENONE)) {
						if (isExpired(temp, CommonConst.PAYUSERTYPEONE))
							session.setAttribute("memberState", "03");
					}
					if (grade.equals(CommonConst.USERGRADENONE)) {
						session.setAttribute("addState", CommonConst.STATEINIT);
					} else {
						if (session.getAttribute("memberState") != null
								&& session.getAttribute("memberState").equals(
										"03")) {
							session.setAttribute("addState",
									CommonConst.STATEINIT);
						} else {
							session.setAttribute("addState",
									CommonConst.STATENORMAL);
						}
					}
				}
				logService.savaLog("帐号", temp.getUsername(),
						CommonConst.LOGLOGIN);

			}
			delNoUpload();
			// 用于LOGO显示
			AbcAttachment at = new AbcAttachment();
			at.setBelongId(getCurrentUser().getEnterpriseId());
			at.setBelongType(ModelType.AP);
			at.setType(CommonConst.ATTACHTYPELOGO);
			@SuppressWarnings("unused")
			List<AbcAttachment> logoList = attachmentService.findByExample(at);
			if (logoList.size() != 0)
				session.setAttribute("logoEnt", ConfConst.FILE_SVR
						+ logoList.get(0).getServerPath());
			if (StringUtil.isNotBlank(pageType)) {
				return "index";
			}
		} else {
			this.addFieldError("topError", "用户名或密码错误！");
			if(StringUtil.isNotBlank(loginPage))
				return "login2";
			return INPUT;
		}

		return SUCCESS;
	}

	private boolean isExpired(AbcUser temp, String field) {
		DetachedCriteria detachedCriteria1 = DetachedCriteria
				.forClass(AbcPaylog.class);
		detachedCriteria1
				.add(Restrictions.and(
						Restrictions.eq("userId", temp.getUserId()),
						Restrictions.eq("state", CommonConst.STATENORMAL)))
				.add(Restrictions.eq("type", field))
				.addOrder(Order.desc("paylogId"));
		List<AbcPaylog> aplist = paylogService
				.findAllByCriteria(detachedCriteria1);
		if (aplist.size() > 0) {
			long howdate = 0;
			if (aplist.get(0).getEndTime().after(new Date())) {
				howdate = DateUtil.DateTimeSpace1(
						DateUtil.formatDate(new Date()),
						DateUtil.formatDate(aplist.get(0).getEndTime()));
				if (howdate > 0)
					return false;
				else
					return true;
			} else {
				return true;
			}
		}
		return true;
	}

}
