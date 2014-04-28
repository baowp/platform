/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "JsessionIdRemoveFilter.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-3-31           baowp                      initial
 */

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
import javax.servlet.http.HttpServletResponseWrapper;

import com.abbcc.util.StringUtil;

public class JsessionIdRemoveFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		if (!(req instanceof HttpServletRequest)) {
			chain.doFilter(req, res);
			return;
		}

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// Redirect requests with JSESSIONID in URL to clean version (old links
		// bookmarked/stored by bots)
		// This is ONLY triggered if the request did not also contain a
		// JSESSIONID cookie! Which should be fine for bots...
		if (request.isRequestedSessionIdFromURL()) {
			String url = request
					.getRequestURL()
					.append(request.getQueryString() != null ? "?"
							+ request.getQueryString() : "").toString();
			response.setHeader("Location", url);
			response.sendError(HttpServletResponse.SC_MOVED_PERMANENTLY);
			return;
		}

		// Prevent rendering of JSESSIONID in URLs for all outgoing links
		HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(
				response) {
			@Override
			public String encodeRedirectUrl(String url) {
				return url;
			}

			@Override
			public String encodeRedirectURL(String url) {
				return StringUtil.encodeURI(url);
			}

			@Override
			public String encodeUrl(String url) {
				return url;
			}

			@Override
			public String encodeURL(String url) {
				return url;
			}
		};
		chain.doFilter(req, wrappedResponse);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
