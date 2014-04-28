/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SiteBaseAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-24           baowp                      initial
 */

package com.abbcc.module.usersite;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;

@SuppressWarnings("serial")
public abstract class SiteBaseAction<T> extends BaseAction<T> {
	public String valiCode;
	protected String f;
	protected String userDomain;

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getUserDomain() {
		return userDomain;
	}

	public void setUserDomain(String domain) {
		this.userDomain = domain;
	}

	public AbcUser getCurrentSubuser() {
		return (AbcUser) getSession().getAttribute(userDomain);
	}

	protected boolean forbid(String enterpriseId) {
		AbcUser user = getCurrentUser();
		return (user == null || user.getEnterpriseId() == null || !user
				.getEnterpriseId().equals(enterpriseId));
	}

	protected boolean checkValiCode() {
		boolean b = this.valiCode.equalsIgnoreCase((String) getSession()
				.getAttribute(CommonConst.VERICODE));
		getSession().removeAttribute(CommonConst.VERICODE);
		return b;
	}

	protected boolean checkDomain() {
		if (userDomain != null)
			return true;
		return false;
	}
}
