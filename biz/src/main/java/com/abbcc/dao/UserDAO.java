/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "df.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-7           wangjin                      initial
 */
package com.abbcc.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.models.AbcUser;

public interface UserDAO {
	public void save(AbcUser transientInstance);

	public void delete(AbcUser persistentInstance);

	public AbcUser findById(String id);

	public int updateUser(Serializable id, String user, String value);

	public List<AbcUser> findByExample(AbcUser instance);

	public List<AbcUser> findAll();

	public void saveOrUpdate(AbcUser instance);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex);

	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex);

	public List findAllByCriteria(final DetachedCriteria detachedCriteria);

	public int getCountByCriteria(final DetachedCriteria detachedCriteria);

	public void callProcedure(String procString, List<Object> params)
			throws Exception;

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception;

	public List<AbcUser> findUserByParams(String[] fields, String key)
			throws ParseException;

	public List findUsersByHql(String hql);

	/**
	 * 根据网站的域名查找到域名对应的用户
	 * 
	 * @param domain 域名
	 * @return 对应用户
	 */
	public AbcUser getUserByDomain(String domain);
}
