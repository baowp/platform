/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminLoginAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-5           yixiugg                      initial
 **/
package com.abbcc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.abbcc.common.CommonConst;
import com.abbcc.helper.UrlHelper;
import com.abbcc.models.AbcUser;

public class LoginFilter implements Filter {

	private String notFilterDir = "";
	protected Log log = LogFactory.getLog(this.getClass());
	private FilterConfig filterConfig;

	public void destroy() {
		notFilterDir = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String uri = req.getRequestURI();
			String realUri = UrlHelper.requestRelayString(req);
			String[] notFilterDirs = notFilterDir.split(",");
			for (int i = 0; i < notFilterDirs.length; i++) {
				String notFilterDirValue = notFilterDirs[i];
				if (uri.indexOf(notFilterDirValue) != -1) {
					chain.doFilter(request, response);
					return;
				}
			}
			if (uri.indexOf(notFilterDir) != -1
					|| uri.indexOf(CommonConst.USERLOGINPAGE) != -1) {
				chain.doFilter(request, response);
				return;
			}
			HttpSession session = req.getSession();
			AbcUser user = (AbcUser) session
					.getAttribute(CommonConst.SESSIONUSER);
			if (user == null)
				res.sendRedirect(this.filterConfig.getServletContext()
						.getContextPath()
						+ CommonConst.USERLOGINPAGE);
			else
				chain.doFilter(request, response);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		notFilterDir = filterConfig.getInitParameter("notFilterDir");
	}

}
