package com.abbcc.soa;

import java.io.IOException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.abbcc.common.ConfConst;
import com.abbcc.soa.service.HelloWorld;

public class HelloWorldClient {

	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://www.easthardware.com/service/HelloWorld");
		factory.setServiceClass(HelloWorld.class);
		HelloWorld hello = (HelloWorld) factory.create();
		System.out.println(hello.sayHello(""));
	}

}
