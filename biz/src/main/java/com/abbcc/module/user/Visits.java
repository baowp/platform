package com.abbcc.module.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.abbcc.common.CommonConst;
import com.abbcc.common.SiteInfo;
import com.abbcc.helper.XmlHelper;
import com.abbcc.models.AbcAdmin;
import com.abbcc.module.cache.CacheService;
import com.abbcc.util.BeansFactory;

public class Visits {
	public void jlcount(Date date, String ip) {
		String i = ip;
		Date d = date;
		int c=0;
    	CacheService cacheService =(CacheService) BeansFactory.get("cacheService");
    	String sid = cacheService.getKey();
    	Map session = (Map)cacheService.get(sid);
    	String visits = (String)session.get(CommonConst.SESSIONVISITS);
    	if(visits==null||visits==""){
    		visits="1";
    	}else{
    		if(!visits.equals("10")){
    			visits=(Integer.parseInt(visits)+1)+"";
    		}else{
    			String count = CommonConst.SITEINFO.pageCount;
    			
    			CommonConst.SITEINFO.pageCount=(Integer.parseInt(count)+10)+"";
    			XmlHelper.setSiteInfoXml(CommonConst.SITEINFO, CommonConst.SITEINFOFILEPATH);
    			visits="0";
    		}
    	}
    	session.put(CommonConst.SESSIONVISITS, visits);
    	

	}
}
