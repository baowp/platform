/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AccessTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-7-10           baowp                      initial
 */

package com.abbcc.conn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class AccessTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());
	public List<String> list;

	@Test
	public void test() {
		Connection con = null;
		Statement state = null;
		File srcFile = null;
		File destFile = null;
		try {
			srcFile = new File("d://access.mdb");
			destFile = new File("d://temp.mdb");
			FileUtils.copyFile(srcFile, destFile);

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String dbUrl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=d://temp.mdb";

			con = DriverManager.getConnection(dbUrl);
			con.setAutoCommit(false);
			state = con.createStatement();

			if (list == null) {
				BufferedReader br = new BufferedReader(new FileReader(
						"d:/out.txt"));
				String s;
				while ((s = br.readLine()) != null) {
					s = s.replaceAll("\\\\r", "\r");
					s = s.replaceAll("\\\\n", "\n");
				//	s = Native2AsciiUtils.ascii2Native(s);
					state.addBatch(s);
				}
				br.close();
				// 执行SQL语句
				/*
				 * String sql =
				 * "create   table   tSc7   (sno   char(5),aB varchar(255))";
				 * state.addBatch(sql); sql =
				 * "insert into tsc7 values ('a','b')"; state.addBatch(sql);
				 * 
				 * sql =
				 * "create table ABC_ENTERPRISE(TYPE char(2),REGISTERED_CAPITAL varchar(20),CUSTOMER varchar(200))"
				 * ; state.addBatch(sql); sql =
				 * "insert into ABC_ENTERPRISE values('01','''','电脑')";
				 * state.addBatch(sql);
				 */
			} else {
				for (String sql : list) {
					sql = sql.replaceAll("\\\\r", "\r");
					sql = sql.replaceAll("\\\\n", "\n");
					state.addBatch(sql);
				}
			}

			state.executeBatch();
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		} finally {
			try {
				state.close();
			} catch (SQLException e) {
			}
			try {
				con.close();
			} catch (SQLException e) {
			}
			// destFile.delete();
			System.out.println();
		}
	}
}
