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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.NewsDAO;
import com.abbcc.models.AbcNews;

public class NewsDAOImpl extends BaseDAOImpl implements NewsDAO {

	private static final Log log = LogFactory.getLog(NewsDAOImpl.class);

	/**
	 * 保存
	 * @param transientInstance
	 */
	public void save(AbcNews transientInstance) {
		super.save(transientInstance);
		}
	/**
	 * 删除
	 */
	public void delete(AbcNews persistentInstance) {
		super.delete(persistentInstance);
	}
	/**
	 * 根据主键查找
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public AbcNews findById(String id) {
		log.debug("getting Object instance with id: " + id);
		try {
			AbcNews instance = (AbcNews)super.findById(AbcNews.class, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public void loadById(AbcNews news,String id){
		super.loadById(news, id);
	}
	/**
	 * 查询
	 * 
	 * @param instance
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AbcNews> findByExample(AbcNews instance) {
		log.debug("finding Object instance by example");
		try {
			List<AbcNews> results = (List<AbcNews>) super.findByExample(instance);
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
	public List<AbcNews> findAll() {
		log.debug("finding all Object instances");
		try {
			return (List<AbcNews>)super.findAll(AbcNews.class);
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
	public void saveOrUpdate(AbcNews instance) {
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
		return super.findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE,
				startIndex);
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
	/**
	 * 直接调用存储过程
	 * @param procString
	 * @author kongqz
	 * @throws Exception 
	 * @date 2009-03-03
	 * **/
	public void callProcedure(String procString,List<Object> params) throws Exception {
		super.callProcedure(procString, params);
	}
	/**
	 * 直接调用存储过程，然后返回结果
	 * @param procString
	 * @author kongqz
	 * @throws Exception 
	 * @date 2009-03-03
	 * **/
	public List getCallProcedureResult(String procString,List<Object> params) throws Exception {
		return super.getCallProcedureResult(procString, params);
	}
}
