/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "df.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-7           wangjin                      initial
 */
package com.abbcc.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryParser.ParseException;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.UserDAO;
import com.abbcc.helper.JavaMailHelper;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcCustom;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcMail;
import com.abbcc.models.AbcMenu;
import com.abbcc.models.AbcMessage;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.module.jms.MessageSender;
import com.abbcc.service.AlbumService;
import com.abbcc.service.CategoryService;
import com.abbcc.service.CustomService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.MailService;
import com.abbcc.service.MenuService;
import com.abbcc.service.MessageService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.constant.AlbumType;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
	private UserDAO userDAO;
	private UserService userService;
	private CategoryService categoryService;
	private EnterpriseService enterpriseService;
	private MenuService menuService;
	private CustomService customService;
	private SoaUserService soaUserService;
	private AlbumService albumService;
	private MessageSender messageSender;
	private MailService mailService;
	private MessageService messageService;

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCustomService(CustomService customService) {
		this.customService = customService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	public void setSoaUserService(SoaUserService soaUserService) {
		this.soaUserService = soaUserService;
	}

	public void setuserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void save(AbcUser transientInstance) {
		userDAO.save(transientInstance);
	}

	public void delete(AbcUser persistentInstance) {
		userDAO.delete(persistentInstance);
	}

	public AbcUser findById(String id) {
		return userDAO.findById(id);
	}

	public List<AbcUser> findByExample(AbcUser instance) {
		return userDAO.findByExample(instance);

	}

	public List<AbcUser> findAll() {
		return userDAO.findAll();

	}

	public void saveOrUpdate(AbcUser instance) {
		userDAO.saveOrUpdate(instance);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return userDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return userDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return userDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return userDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return userDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		userDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return userDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public AbcUser findByUsernamePassword(String username, String password) {
		// TODO Auto-generated method stub
		AbcUser user = new AbcUser();
		user.setUsername(username);
		user.setPassword(password);
		List<AbcUser> list = userDAO.findByExample(user);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public AbcUser findSeniorUser(String username, String password) {
		AbcUser user = new AbcUser();
		user.setUsername(username.toLowerCase());
		user.setPassword(password);
		DetachedCriteria dc = DetachedCriteria.forClass(AbcUser.class);
		dc.add(Example.create(user)).add(
				Restrictions.ne("type", CommonConst.SUBMEMBER));
		List<AbcUser> list = userDAO.findAllByCriteria(dc);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public AbcUser findSeniorUser(String username, String password,
			String password2) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(AbcUser.class);
		dc.add(Restrictions.or(Restrictions.eq("username",username.trim().toLowerCase()),Restrictions.eq("email",username.trim().toLowerCase())))
				.add(Restrictions.or(Restrictions.eq("password", password),
						Restrictions.eq("password", password2)))
				.add(Restrictions.ne("type", CommonConst.SUBMEMBER));
		List<AbcUser> list = userDAO.findAllByCriteria(dc);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public AbcUser findSubUserByUsernamePassword(String username,
			String password, String domain) {
		SoaUser soaUser = new SoaUser();
		soaUser.setDomain(domain);
		List<SoaUser> soaList = soaUserService.findByExample(soaUser);
		if (soaList.size() > 0) {
			soaUser = soaList.get(0);
		} else {
			return null;
		}
		AbcUser urlUser = getUserByUsername(soaUser.getUsername());
		AbcUser user = new AbcUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setEnterpriseId(urlUser.getEnterpriseId());
		List<AbcUser> list = userDAO.findByExample(user);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public AbcUser getUserByUsername(String username) {
		// TODO Auto-generated method stub
		AbcUser user = new AbcUser();
		user.setUsername(username);
		List<AbcUser> list = userDAO.findByExample(user);
		if (list == null || list.size() == 0)
			return null;
		else {
			for (AbcUser au : list) {
				if (au.getType().equals(CommonConst.USERTYPEENTERPRISE)
						|| au.getType().equals(CommonConst.USERTYPEGROUP)
						|| au.getType().equals(CommonConst.USERTYPEPERSON)) {
					return au;
				}
			}
			return null;
		}

	}

	@Override
	public AbcUser getUserByEmail(String email) {
		// TODO Auto-generated method stub
		AbcUser user = new AbcUser();
		user.setEmail(email);
		List<AbcUser> list = userDAO.findByExample(user);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	private Integer newSort(String enterpriseId) {
		String hql = "";

		hql = "select max(sort) from AbcCategory where enterpriseId='"
				+ enterpriseId + "'and type='" + CommonConst.CATEGORYTYPENEWS
				+ "' and grade='" + "00" + "'";

		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) categoryService.find(hql);
		Integer sort = null;
		if (list.size() > 0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}

	@Override
	public String userRegister(AbcUser user, String genre, String eName,
			String eAddress, String industry, String mainPro, String mainBuy,
			String randomString32) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		user.setUsername(user.getUsername().toLowerCase());
		userDAO.save(user);
		// 首次注册发送一封系统留言
		AbcMessage am = new AbcMessage();
		am.setRecvUser(user.getUserId());
		am.setAddTime(new Date());
		am.setContent("尊敬的会员：" + user.getName() + "欢迎注册东方五金系统");
		am.setTitle("欢迎注册东方五金系统");
		am.setAddUser("123");
		am.setType(CommonConst.MESSAGESYS);
		am.setRecvState(CommonConst.STATEINIT);
		am.setSendState(CommonConst.STATENORMAL);
		messageService.save(am);

		userService.sendMail(user.getName(), user.getUsername(),
				user.getEmail(), randomString32);
		request.setAttribute("name", user.getName());
		request.setAttribute("email", user.getEmail());

		AbcEnterprise ent = new AbcEnterprise();
		ent.setUserId(user.getUserId());
		ent.setType(genre);
		ent.setName(eName);
		ent.setAddress(eAddress);
		ent.setIndustry(industry);
		ent.setMainBusiness("销售:" + mainPro + " " + "采购:" + mainBuy);
		enterpriseService.save(ent);
		AbcUser uu = (AbcUser) userService.findById(user.getUserId());
		uu.setUserId(uu.getUserId());
		uu.setEnterpriseId(ent.getEnterpriseId());
		userService.saveOrUpdate(uu);
		/**
		 * 创建一个默认的新闻分类
		 */
		AbcCategory ac = new AbcCategory();
		ac.setName("默认分类");
		ac.setIsroot(CommonConst.CATEGORYISROOT);
		ac.setGrade("00");
		ac.setSort(newSort(user.getEnterpriseId()));
		ac.setEnterpriseId(user.getEnterpriseId());
		ac.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		ac.setState(CommonConst.STATENORMAL);
		ac.setType(CommonConst.CATEGORYTYPENEWS);
		categoryService.save(ac);
		/**
		 * 创建一个默认的企业相册
		 */
		AbcAlbum aa = new AbcAlbum();
		aa.setName("默认相册");
		aa.setAdesc("这是系统默认的相册");
		aa.setBlongType(AlbumType.AP.name());
		aa.setBelongId(user.getEnterpriseId());
		aa.setState(CommonConst.STATENORMAL);
		aa.setUserId(user.getUserId());
		albumService.save(aa);
		return CommonConst.SUCCESS;
	}

	@Override
	public void sendMail(String name, String username, String email,
			String randomString32) {
		// TODO Auto-generated method stub
		String content = "<link href=\"http://abbcc.net/css/e-mail.css\" rel=\"stylesheet\" type=\"text/css\" />"
				+ "<div id=\"naf\">"
				+ "<div style=\"width:770px;height:55px;padding-top:5px;\">"
				+ "<div style=\"width:180px;height:50px;float:left;\"><img src=\"http://abbcc.net/images/logo.jpg\" width=\"163\" height=\"47\" /></div>"
				+ "<div style=\"margin-left:5px;border-left:#999999 2px solid;height:25px;font-size:16px;line-height:25px;color:#333333;margin-top:20px;float:left;\">&nbsp;东方五金商贸中心</div>"
				+ "<div style=\"margin-top:30px;height:20px;float:right;color:#333333;font-size:12px;text-align:right;padding-right:10px;\"> <a href=\"http://abbcc.net/user\">登陆</a></div>"
				+ "</div>"
				+ "<div id=\"content\" style=\"width:770px;height:100%;border:#BFE0F0 1px solid;\">"
				+ "<table width=\"100%\" height=\"290\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
				+ "<tr>"
				+ "<td height=\"116\" valign=\"top\"><table width=\"98%\" height=\"152\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
				+ "<tr>"
				+ "<td width=\"1%\" rowspan=\"3\">&nbsp;</td>"
				+ "<td width=\"98%\" height=\"5\">&nbsp;</td>"
				+ "<td width=\"1%\" rowspan=\"3\">&nbsp;</td>"
				+ "</tr><tr>"
				+ "<td height=\"35\" align=\"left\" valign=\"middle\" bgcolor=\"#E6F0FB\">&nbsp;尊敬的"
				+ name
				+ "：</td>"
				+ "</tr><tr>"
				+ "<td height=\"86\" align=\"left\" valign=\"top\" bgcolor=\"#E6F0FB\"><table width=\"100%\" height=\"77\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
				+ "<tr><td width=\"1%\" height=\"77\">&nbsp;</td>"
				+ "<td width=\"99%\" align=\"left\" valign=\"top\"><p><br />"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;您好！感谢您注册东方五金会员（会员登录名："
				+ username
				+ "）<br />"
				+ "<br />"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;您注册时填写的邮箱：<a href=\"mailto:"
				+ email
				+ "\" target=\"_blank\" swaped=\"true\">"
				+ email
				+ "</a>还未通过验证！</p>"
				+ "</td></tr></table></td></tr><tr><td>&nbsp;</td>"
				+ "<td height=\"19\" align=\"left\"><div class=\"cont\"></div></td>"
				+ "<td>&nbsp;</td> </tr></table></td></tr><tr>"
				+ "<td height=\"174\" valign=\"top\"><table width=\"98%\" height=\"196\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
				+ "<tr><td width=\"1%\" rowspan=\"3\">&nbsp;</td>"
				+ "<td width=\"98%\" height=\"5\" bgcolor=\"#FAF8E4\">&nbsp;</td>"
				+ "<td width=\"1%\" rowspan=\"3\">&nbsp;</td>"
				+ "</tr><tr><td height=\"25\" align=\"left\" valign=\"top\" bgcolor=\"#FAF8E4\">&nbsp;请确认此邮箱是您本人使用，保证潜在商业伙伴联系到您！</td>"
				+ "</tr><tr><td height=\"56\" align=\"left\" valign=\"top\" bgcolor=\"#FAF8E4\">&nbsp;<a href=\"http://abbcc.net/user/userActivation?username="
				+ username
				+ "&&randomString32="
				+ randomString32
				+ "\">点此验证</a></td>"
				+ "</tr><tr><td colspan=\"2\">如果无法验证也可以复制链接到地址栏进行验证，链接地址:http://abbcc.net/user/userActivation?username="
				+ username
				+ "&&randomString32="
				+ randomString32
				+ "</td></tr><tr><td>&nbsp;</td>"
				+ "<td height=\"68\" align=\"left\" bgcolor=\"#FAF8E4\"><table width=\"100%\" height=\"20\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"
				+ "<tr><td width=\"3%\">&nbsp;</td><td width=\"25%\">&nbsp;</td>"
				+ "<td width=\"72%\">如果有任何疑问，欢迎 <img src=\"http://abbcc.net/images/icon_point_16.gif\" width=\"16\" height=\"16\" alt=\"点此验证\" title=\"点此验证\"/> <a href=\"#\">给我们留言</a>，我们将尽快给您回复！</td>"
				+ "</tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td> </tr></table></td><td>&nbsp;</td>"
				+ "</tr><tr><td>&nbsp;</td><td height=\"35\" align=\"left\"><div class=\"cont\"></div></td>"
				+ "<td>&nbsp;</td></tr></table></td></tr></table></div></div>";

		// 格式：CommonConst.JMS_MAIL,userId[**]接收人[**]抄送[**]密送[**]标题[**]内容[**]复件名字[**]复件路径
		AbcMail mail = new AbcMail();
		// mail.setAdminId(null);
		mail.setContent(content);
		mail.setAddTime(Calendar.getInstance().getTime());
		mail.setTitle("赶快验证，马上享受更全面的东方五金会员服务!");
		mail.setReceiver(email);
		mail.setType(CommonConst.MAIL_TYPE_REGISTER);
		mail.setState(CommonConst.STATEINIT);
		mailService.save(mail);
		try {
			messageSender.sendString(CommonConst.JMS_MAIL, email + "[**]"
					+ "[**]" + "[**]" + "赶快验证，马上享受更全面的东方五金会员服务!" + "[**]"
					+ content + "[**]" + "[**]");
			mail.setState(CommonConst.STATENORMAL);
			mailService.saveOrUpdate(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			new JavaMailHelper().sendMail("123445914@qq.com", "", "", username,
					content, "", "");
		}

	}

	@Override
	public AbcUser getUserByUsernameAndRandomString(String username,
			String randomString) {
		// TODO Auto-generated method stub
		AbcUser user = new AbcUser();
		user.setUsername(username);
		user.setVericode(randomString);
		List<AbcUser> list = userDAO.findByExample(user);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public List<AbcUser> findUserByAdminSearch(String key) {
		String[] fields = { "username", "name", "email" };
		return findUserByParams(fields, key);
	}

	public List<AbcUser> findUserByParams(String[] fields, String key) {
		try {
			return userDAO.findUserByParams(fields, key);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<AbcUser>();
		}
	}

	@Override
	public int updateUser(Serializable id, String user, String value) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(id, user, value);

	}

	public List<AbcUser> getUsersByGroupId(String groupId) {
		String hql = "from AbcUser a where a.state='" + CommonConst.STATENORMAL
				+ "' and a.userId in("
				+ "select m.userId from AbcGroupmember m where m.usergroupId='"
				+ groupId + "'" + " and m.state='" + CommonConst.STATENORMAL
				+ "')";
		return userDAO.findUsersByHql(hql);
	}

	public AbcUser getUserByDomain(String domain) {
		return userDAO.getUserByDomain(domain);
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	@Override
	public void sendMemberMail(AbcUser au, String type, String title,
			String content) {
		// TODO Auto-generated method stub

		// 格式：CommonConst.JMS_MAIL,userId[**]接收人[**]抄送[**]密送[**]标题[**]内容[**]复件名字[**]复件路径
		AbcMail mail = new AbcMail();
		// mail.setAdminId(null);
		mail.setContent(content);
		mail.setAddTime(Calendar.getInstance().getTime());
		mail.setTitle(title);
		mail.setReceiver(au.getEmail());
		mail.setType(type);
		mail.setState(CommonConst.STATEINIT);
		mailService.save(mail);
		try {
			messageSender.sendString(CommonConst.JMS_MAIL, au.getEmail()
					+ "[**]" + "[**]" + "[**]" + "东方五金:您的会员账号已经到期" + "[**]"
					+ content + "[**]" + "[**]");
			mail.setState(CommonConst.STATENORMAL);
			mailService.saveOrUpdate(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
