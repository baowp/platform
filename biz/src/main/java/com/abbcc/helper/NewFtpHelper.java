/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "Ftp4jHelper.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-8-7           yixiugg                      initial
 **/

package com.abbcc.helper;
/*
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;*/

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * *Ftp4jHelper.java
 */
public class NewFtpHelper {
	private String host;
	private int port;
	private String username;
	private String password;
	private String destPath;
	private String destFile;
	private String srcFile;
	private String srcPath;
	private List<String> destFiles;
	private List<String> srcFiles;

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDestFile() {
		return destFile;
	}

	public void setDestFile(String destFile) {
		this.destFile = destFile;
	}

	public String getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(String srcFile) {
		this.srcFile = srcFile;
	}

	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	public List<String> getDestFiles() {
		return destFiles;
	}

	public void setDestFiles(List<String> destFiles) {
		this.destFiles = destFiles;
	}

	public List<String> getSrcFiles() {
		return srcFiles;
	}

	public void setSrcFiles(List<String> srcFiles) {
		this.srcFiles = srcFiles;
	}

	/**
	 * 上传文件
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws FTPAbortedException
	 * @throws FTPDataTransferException
	 */
	/*public void download() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException, FTPDataTransferException,
			FTPAbortedException {
		FTPClient client = new FTPClient();
		client.connect(host *//* ,21 *//*);
		client.login(username, password);
		String[] dirs = srcPath.split("/");
		for (String dir : dirs) {
			client.changeDirectory(dir);
		}
		client.download(srcFile, new File(destFile));
		client.disconnect(false);
	}*/

	/**
	 * 上传多个文件
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws FTPAbortedException
	 * @throws FTPDataTransferException
	 */
	/*public void uploads() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException, FTPDataTransferException,
			FTPAbortedException {
		FTPClient client = new FTPClient();
		client.connect(host *//* ,21 *//*);
		client.login(username, password);
		String[] dirs = destPath.split("/");
		for (String dir : dirs) {
			client.changeDirectory(dir);
		}
		for (String srcFile : srcFiles) {
			client.upload(new File(srcFile));
		}
		client.disconnect(false);
	}*/

	/**
	 * 下载文件
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws FTPAbortedException
	 * @throws FTPDataTransferException
	 */
	public void upload() throws IllegalStateException, IOException/*,
			FTPIllegalReplyException, FTPException, FTPDataTransferException,
			FTPAbortedException*/ {
		/*FTPClient client = new FTPClient();
		client.connect(host *//* ,21 *//*);
		client.login(username, password);
		String[] dirs = destPath.split("/");
		for (String dir : dirs) {
			client.changeDirectory(dir);
		}
		client.upload(new File(srcFile));
		client.disconnect(false);*/
	}

	/**
	 * 下载多个文件
	 * 
	 * @throws FTPException
	 * @throws FTPIllegalReplyException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws FTPAbortedException
	 * @throws FTPDataTransferException
	 */
	/*public void downloads() throws IllegalStateException, IOException,
			FTPIllegalReplyException, FTPException, FTPDataTransferException,
			FTPAbortedException {
		FTPClient client = new FTPClient();
		client.connect(host *//* ,21 *//*);
		client.login(username, password);
		String[] dirs = srcPath.split("/");
		for (String dir : dirs) {
			client.changeDirectory(dir);
		}
		for (String srcFile : srcFiles) {
			client.download(srcFile, new File(destPath + srcFile));
		}

		client.disconnect(false);
	}*/
}
