package com.abbcc.springrest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcAdmin;

public class AdminPrivacyInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		AbcAdmin admin = (AbcAdmin) request.getSession().getAttribute(
				CommonConst.SESSIONADMIN);
		if (admin == null) {
			response.sendRedirect("/admin/login.jsp");
			return false;
		}
		return true;
	}
}
