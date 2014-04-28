package com.abbcc.module.session;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

public class EhcacheSession extends CacheEventListenerFactory{
	     public CacheEventListener createCacheEventListener(Properties arg0) {  
		         return new CacheEvtLstn();  
		     }
}
