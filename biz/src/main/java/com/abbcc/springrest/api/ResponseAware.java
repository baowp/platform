package com.abbcc.springrest.api;

import javax.servlet.http.HttpServletResponse;

public interface ResponseAware {

	HttpServletResponse getResponse();

	void setResponse(HttpServletResponse response);
}
