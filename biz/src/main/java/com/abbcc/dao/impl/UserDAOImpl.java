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
package com.abbcc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.UserDAO;
import com.abbcc.models.AbcUser;

public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

	private static final Log log = LogFactory.getLog(UserDAOImpl.class);

	/**
	 * 保存
	 * 
	 * @param transientInstance
	 */
	public void save(AbcUser transientInstance) {
		super.save(transientInstance);
	}

	/**
	 * 删除
	 */
	public void delete(AbcUser persistentInstance) {
		super.delete(persistentInstance);
	}

	/**
	 * 根据主键查找
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public AbcUser findById(String id) {
		log.debug("getting Object instance with id: " + id);
		try {
			AbcUser instance = (AbcUser) super.findById(AbcUser.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
/**
 * 自定义查找
 */
	public int updateUser(Serializable id, String user, String value) {
		String hql = "update AbcUser set " + user + "='" + value
				+ "' where userId='" + id + "'";
		return getHibernateTemplate().bulkUpdate(hql);
	}

	/**
	 * 查询
	 * 
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AbcUser> findByExample(AbcUser instance) {
		log.debug("finding Object instance by example");
		try {
			List<AbcUser> results = (List<AbcUser>) super
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/**
	 * 查找全部
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AbcUser> findAll() {
		log.debug("finding all Object instances");
		try {
			return (List<AbcUser>) super.findAll(AbcUser.class);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 修改
	 * 
	 * @param instance
	 */
	public void saveOrUpdate(AbcUser instance) {
		log.debug("attaching dirty Object instance");
		try {
			super.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 分页拿到第一页
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria) {
		return super.findPageByCriteria(detachedCriteria);
	}

	/**
	 * 分页，从某条记录开始
	 * 
	 * @param detachedCriteria
	 * @param startIndex
	 * @return
	 */
	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex) {
		return super.findPageByCriteria(detachedCriteria,
				PaginationSupport.PAGESIZE, startIndex);
	}

	/**
	 * 分页，提供页大小和起始
	 * 
	 * @param detachedCriteria
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public PaginationSupport findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex) {
		return super.findPageByCriteria(detachedCriteria, pageSize, startIndex);
	}

	/**
	 * 得到全部
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public List findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return super.findAllByCriteria(detachedCriteria);
	}

	/**
	 * 得到总数
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		return super.getCountByCriteria(detachedCriteria);
	}

	/***************************************************************************
	 * 直接调用存储过程
	 * 
	 * @param procString
	 * @author kongqz
	 * @throws Exception
	 * @date 2009-03-03
	 **************************************************************************/
	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		super.callProcedure(procString, params);
	}

	/***************************************************************************
	 * 直接调用存储过程，然后返回结果
	 * 
	 * @param procString
	 * @author kongqz
	 * @throws Exception
	 * @date 2009-03-03
	 **************************************************************************/
	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return super.getCallProcedureResult(procString, params);
	}

	public List<AbcUser> findUserByParams(String[] fields, String key)
			throws ParseException {
		return super.findUserByParams(fields, key, AbcUser.class);
	}
	public List findUsersByHql(String hql){
		return getHibernateTemplate().find(hql); 
	}
	
	public AbcUser getUserByDomain(String domain){
		String hql=" from AbcUser a where a.username =(select s.username from com.abbcc.models.SoaUser s where s.domain=?)";
		List<AbcUser> list = getHibernateTemplate().find(hql, domain);
		if(list.size()>0)
			return list.get(0);
		else
			return null;
	}
	

}
