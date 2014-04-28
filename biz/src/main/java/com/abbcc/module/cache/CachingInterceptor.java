package com.abbcc.module.cache;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class CachingInterceptor implements MethodInterceptor {

	private CacheService cacheService;

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();
		Object[] arguments = invocation.getArguments();

		String cacheKey = getCacheKey(targetName, methodName, arguments);

		Object result = cacheService.get(cacheKey);
		// 如果函数返回结果不在Cache中,执行函数并将结果放入Cache
		if (result == null) {
			result = invocation.proceed();
			cacheService.put(cacheKey, result);
		}
		return result;
	}

	/**
	 *创建一个缓存对象的标识: targetName.methodName.argument0.argument1...
	 */
	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}
		return sb.toString();
	}

}
