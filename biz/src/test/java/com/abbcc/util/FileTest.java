/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "FileTest.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-5-13           baowp                      initial
 */

package com.abbcc.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

public class FileTest {
	org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());

	@Test
	public void m2() throws MalformedURLException, IOException {
		File file=new File("E:/temp////");
		log.info(file.exists());
//		FileUtils.copyURLToFile(new URL("http://www.baidu.com"), file);
	}

	public void m() {
		File folder = new File("F:/workspace/Abbcc/WebContent/user/viplate");
		m(folder);
	}

	private void m(File file) {
		if (file.isDirectory()) {
			for (File f : file.listFiles())
				m(f);
		} else if (file.isFile()) {
			file
					.renameTo(new File(file.getPath().replaceAll("\\.jsp",
							".ftl")));
		}
	}
}
