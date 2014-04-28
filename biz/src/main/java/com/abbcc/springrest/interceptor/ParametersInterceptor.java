package com.abbcc.springrest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.abbcc.springrest.api.ResultsExport;
import com.abbcc.util.SpelFactory;
import com.opensymphony.xwork2.ModelDriven;

public class ParametersInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof ModelDriven<?>) {
			ServletRequestDataBinder binder = new ServletRequestDataBinder(
					((ModelDriven<?>) handler).getModel());
			binder.bind(request);
		}
		ServletRequestDataBinder binder = new ServletRequestDataBinder(handler);
		binder.bind(request);
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView == null)
			return;
		if (handler instanceof ModelDriven<?>) {
			ModelMap modelMap = modelAndView.getModelMap();
			if (modelMap.get("model") == null) {
				Object model = ((ModelDriven<?>) handler).getModel();
				modelMap.addAttribute("model", model);
			}
		}
		if (handler instanceof ResultsExport<?>) {
			ResultsExport<?> results = (ResultsExport<?>) handler;
			modelAndView.addObject("resultList", results.getResultList());
			modelAndView.addObject("pageList", results.getPageList());
			modelAndView.addObject("pageSupport", results.getPageSupport());
		}
		modelAndView.addObject(handler);
		modelAndView.addObject("command", handler);
		request.setAttribute("spel", new SpelFactory());
	}
}
