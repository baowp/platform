/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "UploadUtil.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-2-6           yixiugg                      initial
**/

package com.abbcc.util;

import java.util.Calendar;
import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;

/**
 **UploadUtil.java
 **/
public class UploadUtil {
	public static String getRelativeFolder(String name){
		StringBuffer uri = new StringBuffer().append(ConfConst.FOLDER_UPLOAD).append(CommonConst.SEP);
		char c0 = name.charAt(0);
		uri.append(c0).append(CommonConst.SEP).append(c0);
		char c = name.charAt(1);
		if (c >= 'a' && c < 'f')
			uri.append(1);
		else if (c >= 'f' && c < 'k')
			uri.append(2);
		else if (c >= 'k' && c < 'p')
			uri.append(3);
		else if (c >= 'p' && c < 'u')
			uri.append(4);
		else if (c >= 'u' && c < 'z')
			uri.append(5);
		else
			uri.append(6);
		uri.append(CommonConst.SEP).append(name).append(CommonConst.SEP);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String date = format.format(new Date());
//		for (String d : date.split("-"))
//			uri.append(CommonConst.SEP).append(d);
		return uri.toString();
	}
	public static String getWebRelativeFolder(String name){
		StringBuffer uri = new StringBuffer().append(ConfConst.FOLDER_UPLOAD).append("/");
		char c0 = name.charAt(0);
		uri.append(c0).append("/").append(c0);
		char c = name.charAt(1);
		if (c >= 'a' && c < 'f')
			uri.append(1);
		else if (c >= 'f' && c < 'k')
			uri.append(2);
		else if (c >= 'k' && c < 'p')
			uri.append(3);
		else if (c >= 'p' && c < 'u')
			uri.append(4);
		else if (c >= 'u' && c < 'z')
			uri.append(5);
		else
			uri.append(6);
		uri.append("/").append(name).append("/");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String date = format.format(new Date());
//		for (String d : date.split("-"))
//			uri.append(CommonConst.SEP).append(d);
		return uri.toString();
	}
	public static String getDatePath(){
		Calendar c = Calendar.getInstance();
		return "/"+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DATE);
	}
}

