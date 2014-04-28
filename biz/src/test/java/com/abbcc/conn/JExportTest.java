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
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.SiteInfo;
import com.abbcc.models.SoaUser;
import com.abbcc.service.BaseService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.UploadUtil;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

public class JExportTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	BaseService baseService;

	String sql;

	String owner = "abbcc_dev98".toUpperCase();
	String enterpriseId = "Enterp_0000000000000000000000001";
	String username = "ggggfj";
	String userFolder;
	SoaUser soaUser;

	Database db;

	@Before
	public void before() throws IOException {
		baseService = (BaseService) BeansFactory.get("baseService");

		SoaUser example = new SoaUser();
		example.setUsername(username);
		@SuppressWarnings("unchecked")
		List<SoaUser> list = baseService.findByExample(example);
		Iterator<SoaUser> it = list.iterator();
		if (it.hasNext())
			soaUser = it.next();
		{
			ConfConst.FILE_UPLOAD_DIR = "E:\\Abbcc\\";
			ConfConst.FOLDER_UPLOAD = "upload";
			ConfConst.TEMP_ZIP_FOLDER = "E:\\temp\\";
			CommonConst.SEP = "/";
			if (CommonConst.SITEINFO == null)
				CommonConst.SITEINFO = new SiteInfo();
			CommonConst.SITEINFO.syncKey = "abbcc_2010_key";
		}
		userFolder = ConfConst.FILE_UPLOAD_DIR
				+ UploadUtil.getRelativeFolder(username);

		db = Database.create(new File(userFolder + "database.asp"));

	}

	@Test
	public void test() throws IOException {
		long t1 = System.currentTimeMillis();
		sql = "select table_name from dba_tables t where t.owner='" + owner
				+ "' order by table_name";

		baseService.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String upperTableName = rs.getString(1);
				final String tableName = rs.getString(1).toLowerCase();

				sql = "select column_name from all_tab_columns where owner='"
						+ owner + "' and table_name='" + upperTableName
						+ "' and column_name='ENTERPRISE_ID'";
				baseService.query(sql, new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						sql = "select * from " + tableName
								+ " where enterprise_id='" + enterpriseId + "'";
						export(sql, tableName);
						return null;
					}
				});

                try {
                    if (!db.getTableNames().contains(tableName)) {
                        sql = sql.replace("ENTERPRISE_ID", "USER_ID");
                        baseService.query(sql, new RowMapper() {

                            @Override
                            public Object mapRow(ResultSet rs, int rowNum)
                                    throws SQLException {
                                sql = "select * from "
                                        + tableName
                                        + " where user_id in (select user_id from abc_user where enterprise_id='"
                                        + enterpriseId + "')";
                                export(sql, tableName);
                                return null;
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
			}
		});
		export(new String[] { "abc_message", "abc_syscode" });
		db.close();
		long t2 = System.currentTimeMillis();
		long t = t2 - t1;
		log.info(t);
		log.info("end");
	}

	private void export(String[] tableNames) {
		for (String tableName : tableNames) {
			sql = "select * from " + tableName;
			if (tableName.equals("abc_message"))
				sql += " where add_ent='"
						+ enterpriseId
						+ "' or recv_ent='"
						+ enterpriseId
						+ "' or add_user in (select user_id from abc_user where enterprise_id='"
						+ enterpriseId
						+ "') or recv_user in (select user_id from abc_user where enterprise_id='"
						+ enterpriseId + "')";
			export(sql, tableName);
		}
	}

	private void export(String sql, final String tableName) {
		baseService.query(sql, new ResultSetExtractor() {

			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				log.info(tableName);
				TableBuilder newTable = new TableBuilder(tableName);
				Table table = null;

				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					ColumnBuilder cb = new ColumnBuilder(rs.getMetaData()
							.getColumnName(i).toLowerCase());
					cb.setSQLType(rs.getMetaData().getColumnType(i));
					if (rs.getMetaData().getColumnType(i) == Types.NUMERIC)
						;
					else
						cb.setLengthInUnits(rs.getMetaData()
								.getColumnDisplaySize(i));
					if (rs.getMetaData().getColumnDisplaySize(i) > 255)
						cb.setSQLType(Types.CLOB);
					newTable.addColumn(cb.toColumn());
				}
				try {
					long t1 = System.currentTimeMillis();
					table = newTable.toTable(db);
					long t2 = System.currentTimeMillis();
					long t = t2 - t1;
					log.info("create table " + t + "ms");
				} catch (IOException e) {
					log.error(e);
				}
				long t1 = System.currentTimeMillis();
				int count = 0;
				for (; rs.next(); count++) {
					List<Serializable> rows = new ArrayList<Serializable>();
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						if (tableName.equals("abc_attachment")
								&& rs.getMetaData().getColumnName(i)
										.equalsIgnoreCase("server_path")
								&& rs.getString(i) != null) {
							rows.add(rs.getString(i).replaceFirst(
									"^upload/\\w/\\w\\d/\\w+/", ""));
						} else
							rows.add(value(rs, i));
					}
					try {
						table.addRow(rows.toArray());
					} catch (IOException e) {
						log.error(e);
					}
				}
				long t2 = System.currentTimeMillis();
				long t = t2 - t1;
				log.info("insert records " + t + "ms");
				log.info("count records " + count);
				return null;
			}
		});
	}

	private Serializable value(ResultSet rs, int i) throws SQLException {
		if (rs.getObject(i) == null)
			return null;
		switch (rs.getMetaData().getColumnType(i)) {
		case Types.CLOB:
			BufferedReader br = new BufferedReader(rs.getClob(i)
					.getCharacterStream());
			String str2 = "",
			s;
			try {
				while ((s = br.readLine()) != null) {
					str2 += s + "\r\n";
				}
			} catch (IOException e) {
				log.info(e);
			}
			return str2;
		case Types.VARCHAR:
		case Types.CHAR:
			return rs.getString(i);
		case Types.INTEGER:
			return rs.getInt(i);
		case Types.NUMERIC:
			return rs.getDouble(i);
		case Types.TIMESTAMP:
			return rs.getTimestamp(i);
		}
		return rs.getInt(i);
	}

}
