package com.abbcc.springrest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;

public class UserPrivacyInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		AbcUser user = (AbcUser) request.getSession().getAttribute(
				CommonConst.SESSIONUSER);
		if (user == null) {
			response.sendRedirect("/user/login.jsp");
			return false;
		}
		return true;
	}
}
