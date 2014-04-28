package com.abbcc.springrest.controller.user.site;

import com.abbcc.models.AbcUser;
import com.abbcc.springrest.controller.BaseController;

public class BaseSiteController<T> extends BaseController<T> {

	private static final long serialVersionUID = 1L;

	protected boolean before() {
		return true;
	}

	protected void after() {
	}

	protected boolean forbid(String enterpriseId) {
		AbcUser user = getCurrentUser();
		return (user == null || user.getEnterpriseId() == null || !user
				.getEnterpriseId().equals(enterpriseId));
	}

}
