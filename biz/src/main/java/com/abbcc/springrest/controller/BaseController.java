package com.abbcc.springrest.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.springframework.validation.BindingResult;

import com.abbcc.action.BaseAction;
import com.abbcc.springrest.api.Preparable;
import com.abbcc.springrest.api.RequestAware;
import com.abbcc.springrest.api.ResponseAware;
import com.abbcc.springrest.api.ResultsExport;

public class BaseController<T> extends BaseAction<T> implements RequestAware,
		ResponseAware, Preparable, ResultsExport<T> {

	public static String VIEW_NAME = "springmvc_view_name";
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public ServletContext getServletContext() {
		return getRequest().getServletContext();
	}

	protected String viewName(String viewName) {
		deposit(VIEW_NAME, viewName);
		return "";
	}

	protected boolean hasFieldError(BindingResult result, String... field) {
		for (String f : field) {
			if (result.hasFieldErrors(f))
				return true;
		}
		return false;
	}

	protected Object take(String key) {
		return getRequest().getAttribute(key);
	}

	protected void remove(String key) {
		getRequest().removeAttribute(key);
	}

	public JSON toJSON(Object object) {
		return JSONSerializer.toJSON(object);
	}

}
