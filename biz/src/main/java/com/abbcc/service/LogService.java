/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "AdminService.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-9           wangjin                      initial
 **/

package com.abbcc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcLog;

/**
 * *AdminService.java
 */
public interface LogService extends BaseService{
	/**
	 * 
	 * @param name 是要操作的对象名称，如：证书、新闻等
	 * @param desc 操作对象的标题，如：新闻的标题，证书的名字
	 * @param logType 对对象的哪种操作（增，删，改），在commonConst里有说明
	 */
	public void savaLog(String name,String desc,String logType);
	public void savaAdminLog(String name,String desc,String logType);

	

}
