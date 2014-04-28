package com.abbcc.springrest.api;

import javax.servlet.http.HttpServletRequest;

public interface RequestAware {

	void setRequest(HttpServletRequest request);

	HttpServletRequest getRequest();
}
