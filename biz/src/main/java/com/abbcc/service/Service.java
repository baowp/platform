package com.abbcc.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.ResultTransformer;
import org.springframework.jdbc.core.RowMapper;

import com.abbcc.common.PaginationSupport;

public interface Service<E> {

	public Serializable save(E entity);

	public void delete(E entity);

	public void deleteAll(Collection<E> entites);

	public E get(Serializable id);

	public void load(E entity, Serializable id);

	public void refresh(E entity);

	public List<E> findByExample(E example);

	public List<E> loadAll();

	public void saveOrUpdate(E entity);

	public void update(E entity);
	
	public Integer newSort(SimpleExpression... expression);
	
	/**
	 * 修改某个属性值(比如: 显示或隐藏)
	 * @param key		[主键值]
	 * @param property	[字段名称]
	 * @param value		[字段值]
	 * @return 修改的记录数
	 */
	public Integer updatePropery(String key, String property, String value);

	public void saveOrUpdateAll(Collection<E> entities);

	public List<E> find(String hql, Object... values);

	public int bulkUpdate(String hql, Object... values);

	public List<E> query(String sql, RowMapper<E> rowMapper);

	public int[] batchUpdate(String[] sql);

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria);

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex);

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex);

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex,
			ResultTransformer resultTransformer);

	public List<E> findAllByCriteria(DetachedCriteria detachedCriteria);

	public int getCountByCriteria(DetachedCriteria detachedCriteria);

	public List<E> searchList(String term, String... fields)
			throws ParseException;

	public PaginationSupport<E> searchPage(int pageSize, int startIndex,
			String term, String... fields) throws ParseException;
}
