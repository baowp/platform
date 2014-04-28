/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "SynchronizeAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-7-17           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.helper.FtpHelper;
import com.abbcc.helper.ZipHelper;
import com.abbcc.models.AbcUser;
import com.abbcc.models.SoaUser;
import com.abbcc.models.SoaWebserver;
import com.abbcc.soa.client.WebServiceClient;
import com.abbcc.util.ThreeDesUtil;
import com.abbcc.util.UploadUtil;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

@SuppressWarnings("serial")
public class SynchronizeAction<T> extends SiteBaseAction<SoaUser> {

	private String sql;
	private Set<String> includeTables;
	private Set<String> excludeTables;

	private String owner;
	private String enterpriseId;
	private String username;
	private String userFolder;
	private SoaUser soaUser;
	private String includeFolder;
	private String excludeFolder;
	public boolean excludedb;
	public boolean excludepic;

	private Database db;
	private String dbname;

	private String websoaPort;

	private boolean before() throws IOException {
		AbcUser user = getCurrentUser();
		{
			username = user.getUsername();
			owner = ConfConst.db_user.toUpperCase();
			enterpriseId = user.getEnterpriseId();
			userFolder = ConfConst.FILE_UPLOAD_DIR
					+ UploadUtil.getRelativeFolder(username);
			excludeFolder = "**/" + CommonConst.FOLDER_HTML + "/,**/"
					+ CommonConst.FOLDER_TEMPLATE + "/";
			dbname = "database.asp";

			includeTables = new HashSet<String>();
			includeTables.add("abc_message");
			includeTables.add("abc_syscode");
			excludeTables = new HashSet<String>();
			excludeTables.add("abc_log");
			excludeTables.add("abc_viewlog");
		}
		SoaUser example = new SoaUser();
		example.setUsername(username);
		@SuppressWarnings("unchecked")
		List<SoaUser> list = baseService.findByExample(example);
		Iterator<SoaUser> it = list.iterator();
		if (it.hasNext())
			soaUser = it.next();
		else
			return false;

		File folder = new File(userFolder);
		if (!folder.exists())
			folder.mkdirs();

		db = Database.create(new File(userFolder + dbname));

		return true;
	}

	public void sync() throws IOException,
			com.abbcc.exception.DataAccessException {
		log.info("sync");
		if (before()) {
			if (excludedb) {
				excludeFolder += ",**/" + dbname;
			} else if (excludepic) {
				export();
				excludeFolder += ",**/" + CommonConst.FOLDER_PICTURE + "/";
			} else {
				export();
			}
			zip();
		}
	}

	private void export() throws com.abbcc.exception.DataAccessException,
			IOException {
		long t1 = System.currentTimeMillis();
		sql = "select table_name from dba_tables t where t.owner='" + owner
				+ "' order by table_name";

		baseService.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String upperTableName = rs.getString(1);
				final String tableName = rs.getString(1).toLowerCase();

				if (excludeTables.contains(tableName))
					return null;

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
                    log.error(e.getMessage(),e);
                }
                return null;
			}
		});
		export(includeTables);
		db.close();
		long t2 = System.currentTimeMillis();
		long t = t2 - t1;
		log.info(t);
		log.info("end");
	}

	private void export(Set<String> tableNames) {
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

	private void zip() throws com.abbcc.exception.DataAccessException {
		String userFolder = ConfConst.FILE_UPLOAD_DIR
				+ UploadUtil.getRelativeFolder(soaUser.getUsername());
		String zipFilePath = ConfConst.TEMP_ZIP_FOLDER + soaUser.getUsername()
				+ ".zip";

		String destPath = UploadUtil.getDatePath();
		ZipHelper zipHelper = new ZipHelper();
		try {
			zipHelper.compress(userFolder, zipFilePath, includeFolder,
					excludeFolder);
		} catch (Exception e) {
			throw new com.abbcc.exception.DataAccessException("zip打包失败！"
					+ e.toString());
		}
		String fileName = soaUser.getUsername() + ".zip";
		putFile(fileName, zipFilePath, destPath);

		File file = new File(zipFilePath);
		file.delete();
	}

	private String putFile(String fileName, String dir, String serverDir)
			throws com.abbcc.exception.DataAccessException {
		String s = "";
		FtpHelper ftpHelper = new FtpHelper();
		SoaWebserver soaWebserver = soaUser.webServer();
		ftpHelper.setHost(soaWebserver.getIp());
		ftpHelper.setPort(21);
		ftpHelper.setUsername(soaWebserver.getFtpUsername());
		ftpHelper.setPassword(soaWebserver.getFtpPassword());
		ftpHelper.setDestPath(serverDir);
		ftpHelper.setSrcFile(dir);
		try {
			ftpHelper.upload();
		} catch (Exception e) {
			throw new com.abbcc.exception.DataAccessException("连接ftp失败！"
					+ e.toString());
		}
		WebServiceClient wsc = new WebServiceClient();
		String webservice = "http://" + soaWebserver.getIp() + ":" + websoaPort
				+ "/Abbcc-WEBSOA/soa/SiteSync";
		wsc.setAddress(webservice);

		String srcZipPath = serverDir + "/" + soaUser.getUsername() + ".zip";
		String encryptUsername = ThreeDesUtil
				.encryptMode(soaUser.getUsername());
		String encryptSrcZipPath = ThreeDesUtil.encryptMode(srcZipPath);
		String encryptFolder = ThreeDesUtil.encryptMode(soaUser.getFolder());
		String ic = ThreeDesUtil.encryptMode(encryptUsername
				+ encryptSrcZipPath + encryptFolder
				+ CommonConst.SITEINFO.syncKey);
		try {
			s = wsc.syncSite(encryptUsername, encryptSrcZipPath, encryptFolder,
					ic);
		} catch (Exception e) {
			throw new com.abbcc.exception.DataAccessException("连接webservice失败！"
					+ e.toString());
		}
		return s;
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

	public void setWebsoaPort(String websoaPort) {
		this.websoaPort = websoaPort;
	}
}
