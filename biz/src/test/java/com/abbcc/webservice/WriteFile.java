/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "WriteFile.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-7-13           baowp                      initial
 */

package com.abbcc.webservice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class WriteFile {
	// @Test
	// public void writeFile() throws IOException{
	// String s =" 按时utf-8";
	// s = new String(s.getBytes("utf-8"),"gbk");
	// File file = new File("d://t.txt");
	// if (file.exists())
	// file.delete();
	// file.createNewFile();
	// PrintWriter out = new PrintWriter(file);
	// out.print(s);
	// out.flush();
	// out.close();
	// }
	@Test
	public void testWebService() throws IOException {
		WebServiceClientTest wsc = new WebServiceClientTest();
		wsc.setAddress("http://localhost:8080/service/HelloWorld");

		/*
		 * BufferedReader br = new BufferedReader(new FileReader(
		 * "conf/site/out.txt")); String s; while ((s = br.readLine()) != null)
		 * { wsc.syncSite(s); } br.close();
		 */
		String s = wsc.syncSite("电脑");
		// System.out.println(s);
	}
}
