/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserSiteManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-6           RayStone                    initial
 **/

package com.abbcc.module.soa;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcCustom;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcNews;
import com.abbcc.models.AbcPaylog;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcUserpriv;
import com.abbcc.models.AbcViewlog;
import com.abbcc.module.usersite.SiteBaseAction;
import com.abbcc.service.AlbumService;
import com.abbcc.service.CustomService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.LogService;
import com.abbcc.service.NewsService;
import com.abbcc.service.PaylogService;
import com.abbcc.service.UserService;
import com.abbcc.service.UserprivService;
import com.abbcc.service.ViewlogService;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.ipUtil.IPSeeker;



/**
 * *HtmlSoaUserAction.java
 */
@SuppressWarnings("serial")
public class HtmlSoaUserAction extends SiteBaseAction<AbcUser>{
	private UserService userService;
	private ViewlogService viewlogService;
	protected String hostIP;
	private UserprivService userprivService;
	private EnterpriseService enterpriseService;
	private PaylogService paylogService;
	private CustomService customService;
	private AlbumService albumService;
	private LogService logService;
	private NewsService newsService;
	public String name;
	public String entName;
	public String phone;
	public String cellphone;

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	Map<String, String> albumList = new LinkedHashMap<String, String>();

	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
	}

	public void setCustomService(CustomService customService) {
		this.customService = customService;
	}

	public void setPaylogService(PaylogService paylogService) {
		this.paylogService = paylogService;
	}



	public EnterpriseService getEnterpriseService() {
		return enterpriseService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	/**
	 * 判断是否已登录
	 * @param sessionName
	 * @return
	 */
	private AbcUser checkLogin(String sessionName){
		HttpSession session = this.getSession();
		if(session.getAttribute(sessionName)==null)
			return null;
		else 
			return (AbcUser)session.getAttribute(sessionName);
	}
	
	/**
	 * 解码中文参数
	 * @param encodeStr
	 * @return
	 */
	private String decodeStr(String encodeStr) throws IOException{
		return URLDecoder.decode(URLDecoder.decode(encodeStr, "utf-8"), "utf-8");
	}
	
	/**
	 * 初始化soa页面插件
	 * @return
	 */
	public String initSOA(){
		return "initSOA";
	}
	
	/**
	 * 检测用户名唯一性
	 * @return
	 * @throws IOException
	 */
	public void checkRegName()  throws IOException{
		try {
			AbcUser au = new AbcUser();
			au.setUsername(decodeStr(entity.getUsername()));
			List list = userService.findByExample(au);
			if (list.size() != 0) {
				output(f+"('"+CommonConst.FAILURE+"', '对不起，您输入的账号已经被使用！');");
			}
			else {
				output(f+"('"+CommonConst.SUCCESS+"');");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+ex.toString()+"');");
		}
	}
	
	/**
	 * 检测邮箱唯一性
	 * @return
	 * @throws IOException
	 */
	public void checkRegEmail()  throws IOException{
		try {
			AbcUser au = new AbcUser();
			au.setEmail(entity.getEmail());
			List list = userService.findByExample(au);
			if (list.size() != 0) {
				output(f+"('"+CommonConst.FAILURE+"', '对不起，您输入的Email已经被使用！');");
			}
			else {
				output(f+"('"+CommonConst.SUCCESS+"');");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+ex.toString()+"');");
		}
	}
	
	/**
	 * 注册
	 * @return
	 */
	public void register() throws IOException{
		try {
			DetachedCriteria dc = DetachedCriteria.forClass(AbcUser.class);
			dc.add(Restrictions.eq("username",decodeStr(entity.getUsername())));
			List<AbcUser> l = baseService.findAllByCriteria(dc);
			if(l.size()!=0){
				output(f+"('"+CommonConst.FAILURE+"', '用户名已存在');");
			}
			DetachedCriteria de = DetachedCriteria.forClass(AbcUser.class);
			de.add(Restrictions.eq("email", entity.getEmail()));
			List<AbcUser> le = baseService.findAllByCriteria(de);
			if(le.size()!=0){
				output(f+"('"+CommonConst.FAILURE+"', '邮箱已存在');");
			}
			String randomString32 = new StringUtil().getRndString(32);
			entity.setDomain(domain);
			entity.setName(decodeStr(name));
			entity.setType("01");
			entity.setAddTime(new Date());
			entity.setGrade(CommonConst.USERGRADENONE);
			entity.setState(CommonConst.STATEINIT);
			entity.setVericode(randomString32);
			String temp = userService.userRegister(entity, "01", decodeStr(entName),
					"", "", "", "", randomString32);
			HttpSession session = this.getSession();
			session.setAttribute(entity.getUsername()+"_"+domain, entity);
			output(f+"('"+CommonConst.SUCCESS+"', '"+entity.getName()+"');");
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+ex.toString()+"');");
		}
	}
	
	/**
	 * 登录
	 * @return
	 * @throws IOException 
	 */
	public void login() throws IOException{
		try {
			String userName = decodeStr(entity.getUsername());
			AbcUser abcUser = userService.findSubUserByUsernamePassword(userName, entity.getPassword(), domain);
			if(abcUser!=null){
				HttpSession session = this.getSession();
				session.setAttribute(userName+"_"+domain, abcUser);
				output(f+"('"+CommonConst.SUCCESS+"', '"+userName+"');");
			}
			else {
				output(f+"('"+CommonConst.FAILURE+"', '"+CommonConst.LOGINERROR+"');");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+ex.toString()+"');");
		}
	}
	/**
	 * 上门网啊，地板网等的登陆
	 * @throws IOException
	 */
	public void loginUser() throws IOException{
		try {
			String userName = decodeStr(entity.getUsername());
			String password = decodeStr(entity.getPassword());
			String password2=password;
			password = MD5EncryptUtil.md5Encry(password);
			password2= MD5EncryptUtil.md5Encry16(password2);
			DetachedCriteria dc = DetachedCriteria
					.forClass(AbcUser.class);
			dc.add(Restrictions.and(Restrictions.eq("username",
					userName), Restrictions.or(Restrictions.eq("password",password2), Restrictions.eq("password", password))));
			dc.add(Restrictions.ne("type", CommonConst.SUBMEMBER));
			List<AbcUser> list = userService.findAllByCriteria(dc);	
			if(list.size()!=0){
				if(!(list.get(0).getState().equals(CommonConst.STATENORMAL))){
					output(f+"('"+CommonConst.FAILURE+"', '"+CommonConst.NOTACTIVEERROR+"');");
				}
				HttpSession session = this.getSession();
				session.setAttribute(userName+"_"+domain, list);
				output(f+"('"+CommonConst.SUCCESS+"', '"+userName+"');");
			}
			else {
				output(f+"('"+CommonConst.FAILURE+"', '"+CommonConst.LOGINERROR+"');");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+ex.toString()+"');");
		}
	}
	public String intoUserCenter(){
		HttpSession session = this.getSession();
		try {
			String userName = decodeStr(entity.getUsername());
			String password = decodeStr(entity.getPassword());
			String password2=password;
			password = MD5EncryptUtil.md5Encry(password);
			password2= MD5EncryptUtil.md5Encry16(password2);
			DetachedCriteria dc = DetachedCriteria
					.forClass(AbcUser.class);
			dc.add(Restrictions.and(Restrictions.eq("username",
					userName), Restrictions.or(Restrictions.eq("password",password2), Restrictions.eq("password", password))));
			dc.add(Restrictions.ne("type", CommonConst.SUBMEMBER));
			List<AbcUser> list = userService.findAllByCriteria(dc);	
			if(list.size()!=0){
				AbcUser au = list.get(0);
			String userId = au.getUserId();
			getSession().setAttribute(CommonConst.SESSIONLOGINUSERID, userId);

			// 判断当企业信息为空且用户类型为二级用户的时候执行
			if ("04".equals(au.getType())) {
				// 权限检查
				AbcUserpriv auser = new AbcUserpriv();
				auser.setuserId(userId);
				auser.setState(CommonConst.STATENORMAL);
				List<AbcUserpriv> privList = (List<AbcUserpriv>) userprivService
						.findByExample(auser);

				getSession().setAttribute(CommonConst.SESSIONUSERPRIVILEGE,
						privList);
				AbcEnterprise entUser = enterpriseService.findById(au
						.getEnterpriseId());
				AbcUser userSession = (AbcUser) userService.findById(entUser
						.getUserId());
				getSession().setAttribute(CommonConst.SESSIONUSER, userSession);
				this.getSession().setAttribute(CommonConst.SESSIONENT,
						enterpriseService.findById(au.getEnterpriseId()));
				getSession().setAttribute(CommonConst.SESSIONSUBACCOUNT, au);
				logService.savaLog("帐号", au.getUsername(),
						CommonConst.LOGLOGIN);
			} else {
				ServletContext application = session.getServletContext();
				List onlineUserList = new ArrayList();
				application.setAttribute("onlineUserList", onlineUserList
						.add(au.getUsername()));
				// 用于自定义菜单的显示
				String hql = "from AbcCustom where enterpriseId='"
						+ au.getEnterpriseId() + "' and display='"
						+ CommonConst.DISPLAY + "' order by sort";
				List<AbcCustom> customList = customService.findByHql(hql);
				getSession().setAttribute("customList", customList);
				// 用于系统公告显示
				String announceHql = "from AbcNews where newsType='"
						+ CommonConst.NEWSANNOUNCE + "' order by addTime";
				List<AbcNews> news = newsService.findByHql(announceHql);
				getSession().setAttribute("announceList", news);
				// 用于相册显示
				String albumHql = "from AbcAlbum where belongId='"
						+ au.getEnterpriseId() + "' and state='"
						+ CommonConst.STATENORMAL + "' order by addTime";
				List<AbcAlbum> album = albumService.findByHql(albumHql);
				if (album.size() != 0) {
					for (AbcAlbum al : album) {
						albumList.put(al.getAlbumId(), al.getName());
					}
				}
				getSession().setAttribute("albumList", albumList);

				AbcEnterprise enterprise = enterpriseService.findById(au
						.getEnterpriseId());
				this.getSession().setAttribute(CommonConst.SESSIONENT,
						enterprise);
				this.getSession().setAttribute(CommonConst.SESSIONUSER, au);

				// 判断付费用户是否过期
				String grade = au.getGrade();
				if (grade != null) {
					if (!grade.equals(CommonConst.USERGRADENONE)) {
						DetachedCriteria detachedCriteria1 = DetachedCriteria
								.forClass(AbcPaylog.class);
						detachedCriteria1.add(Restrictions.and(Restrictions.eq(
								"userId", au.getUserId()), Restrictions.ne(
								"state", CommonConst.STATEDEL)));
						detachedCriteria1.addOrder(Order.desc("endTime"));
						List<AbcPaylog> aplist = paylogService
								.findAllByCriteria(detachedCriteria1);
						if (aplist.size() > 0) {
							if (!aplist.get(0).getEndTime().after(new Date())) {
								System.out.println("帐号已过期");
								getSession().setAttribute("memberState", "03");
							}
						}
					}
				}
				logService.savaLog("帐号", au.getUsername(),
						CommonConst.LOGLOGIN);
			}
		}} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断是否已登录
	 * @return
	 * @throws IOException 
	 */
	public void isLogin() throws IOException{
		try {
			String userName = decodeStr(entity.getUsername());
			AbcUser isLoginVal = checkLogin(userName+"_"+domain);
			if(isLoginVal!=null){
				output(f+"('"+CommonConst.SUCCESS+"');");
			}
			else {
				output(f+"('"+CommonConst.FAILURE+"');");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+ex.toString()+"');");
		}
		//return SUCCESS;
	}
	
	/**
	 * 注销
	 * @return
	 */
	public void loginOut() throws IOException{
		try {
			this.getSession().invalidate();
			output(f+"('"+CommonConst.SUCCESS+"');");
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+e.toString()+"')");
		}
	}
	
	/**
	 * 找回密码
	 * @return
	 * @throws IOException
	 */
	public void getPwd() throws IOException{
		try {
			output(f+"('"+CommonConst.SUCCESS+"');");
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
	}
	
	/**
	 * 记录访问记录
	 * @return
	 * @throws IOException 
	 */
	public void viewRecord() throws IOException{
		try {
			String userName = decodeStr(entity.getUsername());
			AbcUser urlUser = userService.getUserByDomain(domain);
			if(urlUser==null){
				output(f+"('"+CommonConst.FAILURE+"', '"+CommonConst.ENTERPRISENULLERROR+"');");
				return;
			}
			AbcUser isLoginVal = checkLogin(userName+"_"+domain);
			System.out.println("login userName---->"+userName);
			String userID = "";
			String location = IPSeeker.getInstance().getAddress(hostIP);
			if(isLoginVal!=null){
				userID = isLoginVal.getUserId();
			}
			Calendar cal = Calendar.getInstance();
			AbcViewlog abcViewlog = new AbcViewlog();
			abcViewlog.setIp(hostIP);
			abcViewlog.setViewTime(cal.getTime());
			abcViewlog.setUserId(userID);
			abcViewlog.setUsername(userName);
			abcViewlog.setLocation(location);
			abcViewlog.setBeviewId(urlUser.getEnterpriseId());
			abcViewlog.setViewtype("0");
			viewlogService.save(abcViewlog);
			output(f+"('"+CommonConst.SUCCESS+"');");
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+ex.toString()+"');");
		}
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ViewlogService getViewlogService() {
		return viewlogService;
	}
	public void setViewlogService(ViewlogService viewlogService) {
		this.viewlogService = viewlogService;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}
}

