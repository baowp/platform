/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "MemberServiceAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-4           yixiugg                      initial
**/

package com.abbcc.module.member;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;
import com.abbcc.service.UserService;
import com.abbcc.util.StringUtil;

/**
 **MemberServiceAction.java
 **/
@SuppressWarnings("serial")
public class MemberServiceAction  extends BaseAction<AbcUser>{
	private UserService userService;
	private String memberGrade="";
	private String searchName;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String view(){
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(AbcUser.class);
		String[] allpayGrade = { CommonConst.USERGRADEONE,
				CommonConst.USERGRADETWO};
		if (!StringUtil.isBlank(memberGrade)&&memberGrade.equals(CommonConst.USERGRADEPAY) )
			detachedCriteria.add(Restrictions.in("grade", allpayGrade));
		if(!StringUtil.isBlank(searchName))
			detachedCriteria.add(Restrictions.like("username", searchName, MatchMode.ANYWHERE));
		detachedCriteria.addOrder(Order.desc("addTime"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = userService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return LIST;
	}
	
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

}

