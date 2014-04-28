package com.abbcc.module.session;

import java.util.HashMap;
import java.util.Map;

import com.abbcc.module.cache.CacheService;
import com.abbcc.util.BeansFactory;


public class SessionService {

	private static SessionService instance = null;

	public CacheService getCacheService() {
		return cacheService;
	}

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	private CacheService cacheService;


	public static synchronized SessionService getInstance() {
		if (instance == null) {
			instance = (SessionService)BeansFactory.get("sessionService");
		}
		return instance;
	}


	public Map getSession(String id) {

		Map session = (Map)cacheService.get(id);
		if (session == null) {
			session = new HashMap();
			cacheService.put(id, session);
		}
		return session;
	}

	public void saveSession(String id, Map session) {
		cacheService.remove(id);
		cacheService.put(id, session);
	}

	public void removeSession(String id) {
		cacheService.remove(id);
	}



}
