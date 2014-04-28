/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UserSearchAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-14           yixiugg                      initial
**/

package com.abbcc.module.user;

import java.util.List;

import net.sf.json.JSONArray;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;
import com.abbcc.service.UserService;
import com.abbcc.util.StringUtil;

/**
 **UserSearchAction.java
 **/
@SuppressWarnings("serial")
public class UserSearchAction extends BaseAction<AbcUser> {
	private UserService userService;
	private String searchKey;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	/**
	 * 根据名字查找企业
	 * @return
	 */
	public String byName() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class);
		if (!StringUtil.isBlank(searchKey))
			detachedCriteria.add(Restrictions.like("username", searchKey,
					MatchMode.ANYWHERE));
		List users = userService.findAllByCriteria(detachedCriteria);
		JSONArray jsonArray = JSONArray.fromObject(users);
		this.result = jsonArray.toString();
		return SUCCESS;
	}
	/**
	 * 利用Hibernate search查询
	 * @throws Exception 
	 */
	public String byUserName() throws Exception {
/*		String[] name={"username"};
		List<AbcUser> user = userService.findUserByParams(name,searchKey,AbcUser.class);
		for(int i=0;i<user.size();i++){
			if(!(user.get(i).getState().equals(CommonConst.STATENORMAL))){
				user.remove(i);
			}
		}*/
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcUser.class);
		detachedCriteria.add(Restrictions.and(Restrictions.ne("type", CommonConst.SUBMEMBER),Restrictions.eq("state",CommonConst.STATENORMAL)));
		detachedCriteria.add(Restrictions.like("username",searchKey, MatchMode.START));
		detachedCriteria.add(Restrictions.ne("type", CommonConst.SUBACCOUNT));
		detachedCriteria.add(Restrictions.and(Restrictions.ne("type", CommonConst.CONTACTNAME), Restrictions.ne("type", CommonConst.SUBMEMBER)));
		List<AbcUser> user = userService.findAllByCriteria(detachedCriteria);
		if(getLoginUserId()!=null){
			for(int i=0;i<user.size();i++){
				if(this.getLoginUserId().equals(user.get(i).getUserId()))
					user.remove(i);
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(user);
		this.result = jsonArray.toString();
		return SUCCESS;
	}
}

