/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminPrivInterceptor.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-6           yixiugg                      initial
 **/

package com.abbcc.module.user;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAdminprivilege;
import com.abbcc.models.AbcUser;
import com.abbcc.models.AbcUserpriv;
import com.abbcc.models.AbcUserprivilege;
import com.abbcc.service.UserprivilegeService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 **AdminPrivInterceptor.java
 **/
public class UserPrivInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		//String actionName = invocation.getAction().toString();
		//String methodName = invocation.getProxy().getConfig().getMethodName();
		String actionName = invocation.getProxy().getActionName();
		String namespace = invocation.getProxy().getNamespace();
		ActionContext actionContext = invocation.getInvocationContext();
		Map session = actionContext.getSession();
		AbcUser user = (AbcUser) session.get(CommonConst.SESSIONSUBACCOUNT);
		AbcUser auser = (AbcUser)session.get(CommonConst.SESSIONUSER);
		if((namespace+"/"+actionName).equals("/user/album/albumshowFlash")){
			if(StringUtil.isNotBlank(auser.getGrade()))
				if(auser.getGrade().equals(CommonConst.USERGRADENONE)){
					return "nopriv";
				}
		}
		
			
		if (user==null||!(user.getType().equals(CommonConst.SUBACCOUNT)))
			invocation.invoke();
		else {
			List<AbcUserpriv> privList = (List<AbcUserpriv>) session
					.get(CommonConst.SESSIONUSERPRIVILEGE);
			if (privList == null || privList.size() == 0)
				return "nopriv";
			Iterator iter = privList.iterator();
			while (iter.hasNext()) {
				AbcUserpriv au = (AbcUserpriv) iter.next();
				String userprivilegeId = au.getuserprivilegeId();
				UserprivilegeService userprivilegeService = (UserprivilegeService) BeansFactory
						.get("userprivilegeService");
				AbcUserprivilege aprivilege = userprivilegeService
						.findById(userprivilegeId);
				if(aprivilege.getUrl()!=null){
					String[] aprivileges = aprivilege.getUrl().split(",");
					for(int i=0;i<aprivileges.length;i++){
						if ((namespace+"/"+actionName).equals(aprivileges[i])) {
							invocation.invoke();
							break;
						}
					}
				}


			}
		}
		return "nopriv";
	}

}
