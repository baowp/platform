package com.abbcc.springrest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.abbcc.springrest.api.RequestAware;
import com.abbcc.springrest.api.ResponseAware;

public class RequestResponseInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof RequestAware) {
			((RequestAware) handler).setRequest(request);
		}
		if (handler instanceof ResponseAware) {
			((ResponseAware) handler).setResponse(response);
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (handler instanceof RequestAware) {
			((RequestAware) handler).setRequest(null);
		}
		if (handler instanceof ResponseAware) {
			((ResponseAware) handler).setResponse(null);
		}
	}
}
