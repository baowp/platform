package com.abbcc.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.Id;

import org.apache.lucene.queryParser.ParseException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.Dao;
import com.abbcc.models.annotation.SortProperty;
import com.abbcc.service.Service;
import com.abbcc.util.AnnotationUtil;

/**
 * @author baowp
 * @date 2010-08
 * @param <E>
 * @param <D>
 */
public class ServiceImpl<E, D extends Dao<E>> implements Service<E> {

	protected final org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(getClass());
	protected Class<E> entityClass;
	protected D dao;

	@Autowired
	protected void setDao(D dao) {
		this.dao = dao;
		generic();
	}

	@SuppressWarnings("unchecked")
	private void generic() {
		if (entityClass == null)
			try {
				entityClass = (Class<E>) ((ParameterizedType) getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0];
			} catch (Exception ex) {
				log.warn("generic error");
			}
	}

	public Serializable save(E entity) {
		return dao.save(entity);
	}

	public void delete(E entity) {
		dao.delete(entity);
	}

	public void deleteAll(Collection<E> entities) {
		dao.deleteAll(entities);
	}

	public E get(Serializable id) {
		return (E) dao.get(id);
	}

	public void load(E entity, Serializable id) {
		dao.load(entity, id);
	}

	public void refresh(E entity) {
		dao.refresh(entity);
	}

	public List<E> findByExample(E example) {
		return dao.findByExample(example);
	}

	public List<E> loadAll() {
		return dao.loadAll();

	}

	public void saveOrUpdate(E entity) {
		dao.saveOrUpdate(entity);

	}

	public void update(E entity) {
		dao.update(entity);
	}

	public void saveOrUpdateAll(Collection<E> entities) {
		dao.saveOrUpdateAll(entities);
	}

	public List<E> find(String hql, Object... values) {
		return dao.find(hql, values);
	}

	public int bulkUpdate(String hql, Object... values) {
		return dao.bulkUpdate(hql, values);
	}

	public List<E> query(String sql, RowMapper<E> rowMapper) {
		return dao.query(sql, rowMapper);
	}

	public int[] batchUpdate(String[] sql) {
		return dao.batchUpdate(sql);
	}

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return dao.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return dao.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return dao.findPageByCriteria(detachedCriteria, pageSize, startIndex);
	}

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex,
			ResultTransformer resultTransformer) {
		return dao.findPageByCriteria(detachedCriteria, pageSize, startIndex,
				resultTransformer);
	}

	public List<E> findAllByCriteria(DetachedCriteria detachedCriteria) {
		return dao.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return dao.getCountByCriteria(detachedCriteria);

	}

	public List<E> searchList(String term, String... fields)
			throws ParseException {
		return dao.searchList(term, fields);
	}

	public PaginationSupport<E> searchPage(int pageSize, int startIndex,
			String term, String... fields) throws ParseException {
		return dao.searchPage(pageSize, startIndex, term, fields);
	}

	public Integer newSort(SimpleExpression... expressions) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		for (SimpleExpression ex : expressions) {
			dc.add(ex);
		}
		String property = AnnotationUtil.getProperty(entityClass,
				SortProperty.class);
		dc.setProjection(Projections.max(property));
		List<E> list = findAllByCriteria(dc);
		Integer sort = (Integer) list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}

	@Override
	public Integer updatePropery(String key, String property, String value) {
		String identity = AnnotationUtil.getProperty(entityClass, Id.class); // 主键字段
		String hql = "update " + entityClass.getSimpleName() + " set "
				+ property + "=? where " + identity + "=?";
		return dao.bulkUpdate(hql, value, key);
	}
}
