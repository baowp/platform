package com.abbcc.webservice;

import java.util.List;

import org.junit.Test;

import com.abbcc.models.AbcUser;
import com.abbcc.soa.client.WebServiceClient;
import com.abbcc.soa.service.HelloWorld;

public class HelloWorldTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	@Test
	public void test() {
		HelloWorld hello = WebServiceClient.generate(HelloWorld.class,
				"http://localhost:8080/service/HelloWorld");
		log.info(hello.sayHello("name"));
		AbcUser user = hello.user("user");
		log.info(user.getPhone());
		// Map<String, String> map = hello.map(user);
		// log.info(map);
		List<String> list = hello.list();
		log.info(list);
	}
}
