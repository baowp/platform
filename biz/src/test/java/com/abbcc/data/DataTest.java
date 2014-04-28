/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DataTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-7-19           baowp                      initial
 */

package com.abbcc.data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.abbcc.models.AbcMail;
import com.abbcc.service.BaseService;
import com.abbcc.service.MailService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;

public class DataTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	BaseService service;
	JdbcTemplate jdbcTemplate;
	MailService mailService ;

	@Before
	public void before() {
		service = (BaseService) BeansFactory.get("baseService");

		DataSource dataSource = (DataSource) BeansFactory.get("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);

	}


	public static void main(String[] args)  {
/*		String sql = "select * from test where name like '%i%' and rownum <100";
		
		long t1 = System.currentTimeMillis();
		
		List<?> list = jdbcTemplate.queryForList(sql);
		
		long t2 = System.currentTimeMillis();
		long t = t2 - t1;
		log.info(t);
		
		log.info(list);
		log.info(list.size());*/
		Calendar cal = Calendar.getInstance();
		Date startDate = new Date(cal.getTime().getTime()
				- (1000 * 60 * 60 * 24 * 29L));
		startDate = DateUtil.getDateStart(startDate);
		Date endDate = DateUtil.getDateEnd(cal.getTime());
		
		MailService mailService = (MailService)BeansFactory.get("mailService");
		AbcMail am = new AbcMail();
		am.setTitle("招程序员");
		am.setReceiver("123445914@qq.com");
		List<AbcMail> al = mailService.findByExample(am);
		Date oldD = al.get(0).getAddTime();
		System.out.println(oldD);
		System.out.println(new Date().getTime()-oldD.getTime());
	}
}
