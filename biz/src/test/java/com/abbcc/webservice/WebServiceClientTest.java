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

package com.abbcc.webservice;

import java.io.IOException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.abbcc.websoa.test.HelloWorld;


/**
 * *WebServiceClient.java
 */
public class WebServiceClientTest {
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String syncSite(String name) throws IOException {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		String s = "";
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress(address);
		Object obj = factory.create();
		if (obj != null) {
			HelloWorld sus = (HelloWorld) obj;
			s = sus.sayHello(name);
			System.out.println(s);
		}
		return s;
	}

}
