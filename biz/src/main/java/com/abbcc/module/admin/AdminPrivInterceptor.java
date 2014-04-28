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

package com.abbcc.module.admin;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;
import com.abbcc.models.AbcAdminprivilege;
import com.abbcc.models.AbcAdminuserpriv;
import com.abbcc.service.AdminprivilegeService;
import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 ** AdminPrivInterceptor.java
 **/
public class AdminPrivInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		// String actionName = invocation.getAction().toString();
		BaseService baseService = (BaseService)BeansFactory.get("baseService");
		ActionContext actionContext = invocation.getInvocationContext();
		// String methodName =
		// invocation.getProxy().getConfig().getMethodName();
		String namespace = invocation.getProxy().getNamespace();
		String actionname = invocation.getProxy().getActionName();
		Map session = actionContext.getSession();
		AbcAdmin admin = (AbcAdmin) session.get(CommonConst.SESSIONADMIN);
		if (admin.getType().equals(CommonConst.ADMINTYPESUPER))
			invocation.invoke();
		else {
			String privName = namespace + "/" + actionname;
			DetachedCriteria dc = DetachedCriteria.forClass(AbcAdminuserpriv.class);
			dc.add(Restrictions.eq("adminId",admin.getAdminId())).add(Restrictions.eq("state",CommonConst.STATENORMAL));
			List<AbcAdminuserpriv> privList = (List<AbcAdminuserpriv>)baseService.findAllByCriteria(dc);					
			if (privList == null || privList.size() == 0)
				return "nopriv";
			Iterator iter = privList.iterator();
			for(AbcAdminuserpriv aa:privList) {
				String adminprivilegeId = aa.getAdminprivilegeId();
				AdminprivilegeService adminprivilegeService = (AdminprivilegeService) BeansFactory
						.get("adminprivilegeService");
				AbcAdminprivilege aprivilege = adminprivilegeService
						.findById(adminprivilegeId);
				if (aprivilege.getUrl() != null) {
					if (privName.equals(aprivilege.getUrl())) {
						invocation.invoke();
					}
				}

			}

		}
		return "nopriv";
	}

	public HttpSession getSession() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		return session;
	}

}
