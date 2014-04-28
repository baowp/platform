/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DBTemplateLoader.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-4-17           yixiugg                      initial
 **/

package com.abbcc.helper;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import freemarker.cache.TemplateLoader;

/**
 * *DBTemplateLoader.java
 */
public class StringTemplateLoader implements TemplateLoader {

	@Override
	public void closeTemplateSource(Object arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object findTemplateSource(String arg0) throws IOException {
		// TODO Auto-generated method stub
		Clob c = null;
		try {
			arg0 = arg0.replaceAll("_zh_CN", "");
			c = new javax.sql.rowset.serial.SerialClob(arg0.toCharArray());
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return c;
	}

	@Override
	public long getLastModified(Object arg0) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public Reader getReader(Object arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub
		Clob c = (Clob) arg0;
		try {
			return c.getCharacterStream();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
