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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.SiteInfo;
import com.abbcc.helper.FtpHelper;
import com.abbcc.helper.ZipHelper;
import com.abbcc.models.SoaUser;
import com.abbcc.models.SoaWebserver;
import com.abbcc.service.BaseService;
import com.abbcc.soa.client.WebServiceClient;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.StringUtil;
import com.abbcc.util.ThreeDesUtil;
import com.abbcc.util.UploadUtil;

public class ExportTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	BaseService service;

	String sql;
	PrintWriter out;

	String owner = "abbcc_dev98".toUpperCase();
	String enterpriseId = "Enterp_0000000000000000000000001";
	String username = "ggggfj";
	String userFolder;
	SoaUser soaUser;

	@Before
	public void before() throws IOException {
		service = (BaseService) BeansFactory.get("baseService");

		SoaUser example = new SoaUser();
		example.setUsername(username);
		@SuppressWarnings("unchecked")
		List<SoaUser> list = service.findByExample(example);
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
		String sqlPath = userFolder + "db.sql";
		File file = new File(sqlPath);
		if (file.exists())
			file.delete();
		file.createNewFile();
		out = new PrintWriter(file);
	}

	@Test
	public void test() {
		sql = "select table_name from dba_tables t where t.owner='" + owner
				+ "' order by table_name";

		service.query(sql, new RowMapper() {

			@Override
			public Object mapRow(final ResultSet rs, int rowNum)
					throws SQLException {
				// if(rowNum>10)return null;
				// log.info(rowNum);

				sql = "select column_name from all_tab_columns where owner='"
						+ owner + "' and table_name='" + rs.getString(1)
						+ "' and column_name='ENTERPRISE_ID'";
				service.query(sql, new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs2, int rowNum2)
							throws SQLException {
						final String table = rs.getString(1).toLowerCase();
						// log.info(table);
						sql = "select * from " + table
								+ " where enterprise_id='" + enterpriseId + "'";
						service.query(sql, new ResultSetExtractor() {

							@Override
							public Object extractData(ResultSet rs)
									throws SQLException, DataAccessException {
								String str = "create table " + table + "(";
								for (int i = 1; i <= rs.getMetaData()
										.getColumnCount(); i++) {
									str += "["
											+ rs.getMetaData().getColumnName(i)
													.toLowerCase() + "]";
									str += type(
											rs.getMetaData().getColumnType(i),
											rs.getMetaData()
													.getColumnDisplaySize(i))
											+ ",";
								}
								str = str.replaceAll(",$", ")");
								out.println(str);
								while (rs.next()) {
									str = "insert into " + table + " values(";
									for (int i = 1; i <= rs.getMetaData()
											.getColumnCount(); i++) {
										str += value(rs, i) + ",";
									}
									str = str.replaceAll(",$", ")");
									out.println(StringUtil.native2ascii(str));
								}
								return null;
							}
						});

						return null;
					}
				});

				return null;
			}
		});

		out.flush();
		out.close();
	}

	//@After
	public void zip() throws com.abbcc.exception.DataAccessException {
		String userFolder = ConfConst.FILE_UPLOAD_DIR
				+ UploadUtil.getRelativeFolder(soaUser.getUsername());
		String zipFilePath = ConfConst.TEMP_ZIP_FOLDER + soaUser.getUsername()
				+ ".zip";
		String includeFolder = null;
		String excludeFolder = "**/" + CommonConst.FOLDER_HTML + "/,**/"
				+ CommonConst.FOLDER_TEMPLATE + "/";
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
		putFile(soaUser, fileName, zipFilePath, destPath);

		File file = new File(zipFilePath);
		file.deleteOnExit();
	}

	private String putFile(SoaUser soaUser, String fileName, String dir,
			String serverDir) throws com.abbcc.exception.DataAccessException {
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
		String webservice = "http://" + soaWebserver.getIp()
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
			s = wsc.syncResource(encryptUsername, encryptSrcZipPath, encryptFolder,
					ic);
		} catch (Exception e) {
			throw new com.abbcc.exception.DataAccessException("连接webservice失败！"
					+ e.toString());
		}
		return s;
	}

	@After
	public void zj() {
	}

	@After
	public void after2() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("d:/out.txt"));
		String s;
		AccessTest at = new AccessTest();
		at.list = new ArrayList<String>();
		while ((s = br.readLine()) != null) {
			at.list.add(s);
		}
		br.close();
		at.test();
	}

	private String value(ResultSet rs, int i) throws SQLException {
		if (rs.getObject(i) == null)
			return "null";
		switch (rs.getMetaData().getColumnType(i)) {
		case Types.CLOB:
			BufferedReader br = new BufferedReader(rs.getClob(i)
					.getCharacterStream());
			String str2 = "",
			s;
			try {
				while ((s = br.readLine()) != null) {
					str2 += s + "\\r\\n";
				}
			} catch (IOException e) {
				log.info(e);
			}
			return "'" + str2.replaceAll("'", "''") + "'";
		case Types.VARCHAR:
			return "'"
					+ rs.getString(i).replaceAll("'", "''")
							.replaceAll("\\r\\n", "\\\\r\\\\n") + "'";
		case Types.CHAR:
			return "'" + rs.getString(i) + "'";
		case Types.NUMERIC:
			return rs.getObject(i).toString();
		case Types.TIMESTAMP:
			return "'"
					+ rs.getTimestamp(i).toString().replaceAll("\\.\\d*$", "")
					+ "'";
		}
		return rs.getInt(i) + "";
	}

	private String type(int type, int len) {
		String s = "int";
		switch (type) {
		case Types.VARCHAR:
			if (len > 255)
				s = "memo";
			else
				s = "varchar(" + len + ")";
			break;
		case Types.CLOB:
			s = "memo";
			break;
		case Types.CHAR:
			s = "char(" + len + ")";
			break;
		case Types.NUMERIC:
			s = "double";
			break;
		case Types.TIMESTAMP:
			s = "datetime";
			break;
		}
		return s;
	}

}
