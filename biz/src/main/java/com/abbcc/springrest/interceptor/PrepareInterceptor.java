package com.abbcc.springrest.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.abbcc.springrest.api.Preparable;
import com.abbcc.springrest.controller.BaseController;

public class PrepareInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof Preparable) {
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) request
					.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			String id = null;
			if (map != null)
				id = map.get("id");
			if (id == null)
				id = request.getParameter("id");
			Preparable prepare = ((Preparable) handler);
			prepare.setId(id);
			prepare.prepare();
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		if (mv == null)
			return;
		String viewName = (String) request
				.getAttribute(BaseController.VIEW_NAME);
		if (viewName != null)
			mv.setViewName(viewName);
	}
}
