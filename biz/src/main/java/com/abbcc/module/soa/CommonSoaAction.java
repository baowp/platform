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
 * 2010-2-25           ray                      initial
 **/

package com.abbcc.module.soa;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.MD5EncryptUtil;

/**
 * *CommonSoaAction.java
 */
@SuppressWarnings("serial")
public class CommonSoaAction extends BaseAction<AbcUser>{
	private UserService userService;
	private String f;
	private String domain;
	private String url;
	private String secondPwd;
	
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	
	public String getDomian() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getSecondPwd() {
		return secondPwd;
	}
	public void setSecondPwd(String secondPwd) {
		this.secondPwd = secondPwd;
	}
	
	/**
	 * 判断是否已登录
	 * @param sessionName
	 * @return
	 */
	private boolean isLogin(String sessionName){
		HttpSession session = this.getSession();
		if(session.getAttribute(sessionName)==null){
			return false;
		}
		else {
			return true;
		}
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
	 * 注册
	 * @return
	 */
	public String register(){
		try {
			if (!(entity.getPassword().equals(secondPwd))) {
				this.addFieldError("password", "您两次输入的密码不一致！");
				output(f+"('1', '"+domain+"');");
			}
			AbcUser user1 = new AbcUser();
			user1.setUsername(entity.getUsername());
			user1.setType(CommonConst.SUBMEMBER);
			List list1 = userService.findByExample(user1);
			if (list1.size() != 0) {
				this.addFieldError("username", "对不起，您输入的账号已经被使用！");
				return INPUT;
			}
			AbcUser user2 = new AbcUser();
			user2.setEmail(entity.getEmail());
			user2.setType(CommonConst.SUBMEMBER);
			List list2 = userService.findByExample(user2);
			if (list2.size() != 0) {
				this.addFieldError("email", "对不起，您输入的EMAIL已经被使用！");
				return INPUT;
			}
			AbcUser user = (AbcUser) getSession().getAttribute(CommonConst.SESSIONUSER);

			String password = entity.getPassword();
			password = MD5EncryptUtil.md5Encry(password);
			Calendar cal = Calendar.getInstance();
			entity.setAddTime(cal.getTime());
			entity.setState(CommonConst.STATENORMAL);
			entity.setPassword(password);
			entity.setType(CommonConst.SUBMEMBER);
			entity.setEnterpriseId(user.getEnterpriseId());
			userService.save(entity);
		} catch (Exception e) {
			// TODO: handle exception
			this.result = e.toString();
		}
		return SUCCESS;
	}
	
	/**
	 * 登录
	 * @return
	 * @throws IOException 
	 */
	public String login() throws IOException{
		try {
			String userName = decodeStr(entity.getUsername());
//			System.out.println("userName--------------------->"+userName);
//			System.out.println("password---------------------->"+entity.getPassword());
//			System.out.println("domain------------------------>"+domain);
//			System.out.println("sessionName------------------------>"+userName+"_"+domain);
//			HttpSession session = this.getSession();
//			session.setAttribute(userName+"_"+domain, "sessionObj");
//			System.out.println("sessionValue------------->"+session.getAttribute(userName+"_"+domain));
//			output(f+"('"+CommonConst.SUCCESS+"', '"+userName+"', '"+domain+"');");
			AbcUser abcUser = userService.findSubUserByUsernamePassword(userName, entity.getPassword(), domain);
			if(abcUser!=null){
				HttpSession session = this.getSession();
				session.setAttribute(userName+"_"+domain, abcUser);
				output(f+"('"+CommonConst.SUCCESS+"', '"+userName+"', '"+domain+"');");
			}
			else {
				output(f+"('"+CommonConst.LOGINERROR+"');");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+ex.toString()+"');");
		}
		return SUCCESS;
	}
	
	/**
	 * 判断是否已登录
	 * @return
	 * @throws IOException 
	 */
	public String isLogin() throws IOException{
		try {
			String userName = decodeStr(entity.getUsername());
			boolean isLoginVal = isLogin(userName+"_"+domain);
//			System.out.println("sessionName------------------------>"+userName+"_"+domain);
//			System.out.println("isLogin------------------->"+isLoginVal);
			if(isLoginVal){
				output(f+"('"+CommonConst.SUCCESS+"', '"+userName+"');");
			}
			else {
				output(f+"('"+CommonConst.FAILURE+"');");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			output(f+"('"+ex.toString()+"');");
		}
		return SUCCESS;
	}
	
	/**
	 * 注销
	 * @return
	 */
	public String loginOut() throws IOException{
		try {
			this.getSession().invalidate();
			output(f+"('"+CommonConst.SUCCESS+"');");
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+e.toString()+"')");
		}
		return SUCCESS;
	}
	
	/**
	 * 找回密码
	 * @return
	 * @throws IOException
	 */
	public String getPwd() throws IOException{
		try {
			output(f+"('"+CommonConst.SUCCESS+"');");
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
		return SUCCESS;
	}
	
	/**
	 * 产品留言
	 * @return
	 * @throws IOException
	 */
	public String doMsg() throws IOException{
		try {
			output(f+"('"+CommonConst.SUCCESS+"');");
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
		return SUCCESS;
	}
	
	/**
	 * 下订单
	 * @return
	 * @throws IOException
	 */
	public String doOrder() throws IOException{
		try {
			output(f+"('"+CommonConst.SUCCESS+"');");
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
		return SUCCESS;
	}
	
	/**
	 * 网站访问量
	 * @return
	 * @throws IOException
	 */
	public String viewRecord() throws IOException{
		try {
			output(f+"('"+CommonConst.SUCCESS+"');");
		} catch (Exception e) {
			// TODO: handle exception
			output(f+"('"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
		return SUCCESS;
	}
	
}
