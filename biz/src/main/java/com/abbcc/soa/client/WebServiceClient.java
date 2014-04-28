/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "WebServiceClient.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-5           yixiugg                      initial
 **/

package com.abbcc.soa.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.abbcc.websoa.siteSync.SyncUserSite;

/**
 * *WebServiceClient.java
 */
public class WebServiceClient {
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static <T> T generate(Class<T> clazz, String address) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(clazz);
		factory.setAddress(address);
		@SuppressWarnings("unchecked")
		T instance = (T) factory.create();
		return instance;
	}

	public String syncSite(String username, String srcPath, String targetPath,
			String ic) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		String s = "";
		factory.setServiceClass(SyncUserSite.class);
		factory.setAddress(address);
		Object obj = factory.create();
		if (obj != null) {
			SyncUserSite sus = (SyncUserSite) obj;
			s = sus.syncUserSite(username, srcPath, targetPath, ic);
			System.out.println(s);
		}
		return s;
	}

	public String syncResource(String username, String srcPath,
			String targetPath, String ic) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		String s = "";
		factory.setServiceClass(SyncUserSite.class);
		factory.setAddress(address);
		Object obj = factory.create();
		if (obj != null) {
			SyncUserSite sus = (SyncUserSite) obj;
			s = sus.syncUserResource(username, srcPath, targetPath, ic);
			System.out.println(s);
		}
		return s;
	}

}
