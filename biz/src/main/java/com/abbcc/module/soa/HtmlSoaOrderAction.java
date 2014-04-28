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
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcOrder;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.module.usersite.SiteBaseAction;
import com.abbcc.service.OrderService;
import com.abbcc.service.SoaUserService;
import com.abbcc.service.UserService;
import com.abbcc.util.MD5EncryptUtil;
import com.abbcc.util.constant.OrderDealState;

/**
 * HtmlSoaOrderAction
 * @author RayStone
 *
 */
@SuppressWarnings("serial")
public class HtmlSoaOrderAction extends SiteBaseAction<AbcOrder>{
	private UserService userService;
	private OrderService orderService;
	protected String userName;

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
	 * 下订单
	 * @return
	 * @throws IOException
	 */
	public void doOrder() throws IOException{
		try {
			userName = decodeStr(userName);
			AbcUser urlUser = userService.getUserByDomain(userDomain);
			if(checkLogin(userName+"_"+userDomain)==null){
				output(f+"('"+CommonConst.FAILURE+"', '请先登录！');");
			}
			else if(urlUser==null){
				output(f+"('"+CommonConst.FAILURE+"', '不存在该公司！');");
			}
			else {
				AbcUser loginUser = checkLogin(userName+"_"+userDomain);
				entity.setOrderUser(loginUser);
				entity.setContent(decodeStr(entity.getContent()));
				entity.setAmount(entity.getAmount());
				entity.setOdesc(decodeStr(entity.getOdesc()));
				entity.setDealState(OrderDealState.DA);
				entity.setOrderTime(Calendar.getInstance().getTime());
				entity.setState(CommonConst.STATEINIT);
				orderService.save(entity);
				output(f+"('"+CommonConst.SUCCESS+"');");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			output(f+"('"+CommonConst.FAILURE+"', '"+e.toString()+"');");
			//this.result = f+"('"+e.toString()+"')";
		}
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	
}
