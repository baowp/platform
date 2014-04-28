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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;

public class DataImport {

	BaseService service;
	BufferedReader reader;
	JdbcTemplate jdbcTemplate;

	@Before
	public void before() throws FileNotFoundException {
		service = (BaseService) BeansFactory.get("baseService");

		DataSource dataSource = (DataSource) BeansFactory.get("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);

		reader = new BufferedReader(new FileReader(
				"F:/document/temp/www.sina.com.cn.htm"));
	}

	@Test
	public void test() throws IOException {

		Analyzer analyzer = new SimpleAnalyzer();
		TokenStream stream = analyzer.tokenStream(null, reader);
		List<String> list = new ArrayList<String>();
		int count = 0;
		/*for (Token t = stream.next(); t != null; t = stream.next()) {
			list.add("insert into test values(" + (++count) + ",'"
					+ t.termText() + "')");
		}*/
		int flag[] = jdbcTemplate.batchUpdate(list.toArray(new String[0]));
		System.out.println(flag.length);
		System.out.println("end,共" + count);
	}

	@After
	public void after() throws IOException {
		reader.close();
	}
}
