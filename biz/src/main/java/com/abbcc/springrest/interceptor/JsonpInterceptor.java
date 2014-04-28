package com.abbcc.springrest.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.support.HandlerMethodInvoker;
import org.springframework.web.bind.annotation.support.HandlerMethodResolver;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.abbcc.springrest.controller.BaseController;

public class JsonpInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof BaseController) {
			BaseController<?> contro = ((BaseController<?>) handler);
			String callback = contro.getCallback();
			if (callback != null && callback.length() > 20) {
				WebApplicationContext context = (WebApplicationContext) request
						.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
				AnnotationMethodHandlerAdapter ha = context
						.getBean(AnnotationMethodHandlerAdapter.class);
				Method getMethodResolver = AnnotationMethodHandlerAdapter.class
						.getDeclaredMethod("getMethodResolver", Object.class);
				ReflectionUtils.makeAccessible(getMethodResolver);
				HandlerMethodResolver methodResolver = (HandlerMethodResolver) getMethodResolver
						.invoke(ha, handler);

				Method resolveHandlerMethod = methodResolver.getClass()
						.getMethod("resolveHandlerMethod",
								HttpServletRequest.class);
				ReflectionUtils.makeAccessible(resolveHandlerMethod);
				Method handlerMethod = (Method) resolveHandlerMethod.invoke(
						methodResolver, request);

				if (AnnotationUtils.findAnnotation(handlerMethod,
						ResponseBody.class) != null) {
					HandlerMethodInvoker methodInvoker = new HandlerMethodInvoker(
							methodResolver);
					ServletWebRequest webRequest = new ServletWebRequest(
							request, response);
					ExtendedModelMap implicitModel = new BindingAwareModelMap();

					Object result = methodInvoker.invokeHandlerMethod(
							handlerMethod, handler, webRequest, implicitModel);
					// System.out.println(JSONSerializer.toJSON(result));
					response.setContentType("text/javascript;charset=utf-8");
					StringBuilder sb = new StringBuilder(callback).append("(");
					sb.append(JSONSerializer.toJSON(result)).append(")");
					response.getWriter().print(sb);
					return false;
				}
			}
		}

		return true;
	}
}
