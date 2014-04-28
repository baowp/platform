package com.abbcc.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.abbcc.dao.Dao;
import com.abbcc.common.PaginationSupport;

/**
 * 
 * @author baowp
 * @date 2010-08
 * @param <E>
 */
public class DaoImpl<E> implements Dao<E> {

	protected Log log = LogFactory.getLog(getClass());

	private Class<E> entityClass;

	private HibernateTemplate hibernateTemplate;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		if (this.hibernateTemplate == null
				|| sessionFactory != hibernateTemplate.getSessionFactory()) {
			this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		}
		generic();
	}

	@SuppressWarnings("unchecked")
	private void generic() {
		try {
			entityClass = (Class<E>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception ex) {
			log.warn("generic error");
		}
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		if (this.jdbcTemplate == null
				|| dataSource != this.jdbcTemplate.getDataSource()) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
	}

	protected HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	protected JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	public Serializable save(E entity) {
		return fullTextSession().save(entity);
	}

	public void delete(E entity) {
		fullTextSession().delete(entity);
	}

	public void deleteAll(Collection<E> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	public E get(Serializable id) {
		E entity = (E) getHibernateTemplate().get(entityClass, id);
		return entity;
	}

	public void load(E entity, Serializable id) {
		getHibernateTemplate().load(entity, id);
	}

	public void refresh(E entity) {
		getHibernateTemplate().refresh(entity);
	}

	public List<E> findByExample(E entity) {
		@SuppressWarnings("unchecked")
		List<E> results = getHibernateTemplate().findByExample(entity);
		log.debug("find by example successful, result size: " + results.size());
		return results;
	}

	public List<E> loadAll() {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public void saveOrUpdate(E entity) {
		fullTextSession().saveOrUpdate(entity);
	}

	public void update(E entity) {
		fullTextSession().update(entity);
	}

	public void saveOrUpdateAll(Collection<E> entities) {
		getHibernateTemplate().saveOrUpdateAll(entities);
	}

	public List<E> find(String hql, Object... values) {
		@SuppressWarnings("unchecked")
		List<E> list = getHibernateTemplate().find(hql, values);
		return list;
	}

	public int bulkUpdate(String hql, Object... values) {
		return getHibernateTemplate().bulkUpdate(hql, values);
	}

	public List<E> query(String sql, RowMapper<E> rowMapper)
			throws DataAccessException {
		return getJdbcTemplate().query(sql, rowMapper);
	}

	public int[] batchUpdate(String[] sql) {
		return getJdbcTemplate().batchUpdate(sql);
	}

	public PaginationSupport<E> findPageByCriteria(
			final DetachedCriteria detachedCriteria) {
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE,
				0);
	}

	public PaginationSupport<E> findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int startIndex) {
		return findPageByCriteria(detachedCriteria, PaginationSupport.PAGESIZE,
				startIndex);
	}

	public PaginationSupport<E> findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return findPageByCriteria(detachedCriteria, pageSize, startIndex, null);
	}

	public PaginationSupport<E> findPageByCriteria(
			final DetachedCriteria detachedCriteria, final int pageSize,
			final int startIndex, final ResultTransformer resultTransformer) {
		return (PaginationSupport<E>) getHibernateTemplate().execute(
				new HibernateCallback<PaginationSupport<E>>() {
					public PaginationSupport<E> doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						int totalCount = ((Long) criteria.setProjection(
								Projections.rowCount()).uniqueResult())
								.intValue();
						int index = startIndex;
						if (totalCount <= index) {
							index = totalCount - totalCount % pageSize;
							if (index == totalCount)
								index = index - pageSize;
							if (index < 0)
								index = 0;
						}
						criteria.setProjection(null);
						if (resultTransformer != null)
							criteria.setResultTransformer(resultTransformer);
						@SuppressWarnings("unchecked")
						List<E> items = criteria.setFirstResult(index)
								.setMaxResults(pageSize).list();
						PaginationSupport<E> ps = new PaginationSupport<E>(
								items, totalCount, pageSize, index);
						return ps;
					}
				});
	}

	public List<E> findAllByCriteria(final DetachedCriteria detachedCriteria) {
		return getHibernateTemplate().execute(new HibernateCallback<List<E>>() {
			@SuppressWarnings("unchecked")
			public List<E> doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				return criteria.list();
			}
		});
	}

	public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback<Long>() {
					public Long doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return (Long) criteria.setProjection(
								Projections.rowCount()).uniqueResult();
					}
				});
		return count.intValue();
	}

	public List<E> searchList(String term, String... fields)
			throws ParseException {
		MultiFieldQueryParser parser = new MultiFieldQueryParser(
				getLuceneVersion(), fields, new ChineseAnalyzer());
		org.apache.lucene.search.Query luceneQuery = parser.parse(term);
		FullTextQuery query = fullTextSession().createFullTextQuery(
				luceneQuery, entityClass);
		@SuppressWarnings("unchecked")
		List<E> list = query.list();
		return list;
	}

	public PaginationSupport<E> searchPage(int pageSize, int startIndex,
			String term, String... fields) throws ParseException {
		MultiFieldQueryParser parser = new MultiFieldQueryParser(
				getLuceneVersion(), fields, new ChineseAnalyzer());
		org.apache.lucene.search.Query luceneQuery = parser.parse(term);
		FullTextQuery query = fullTextSession().createFullTextQuery(
				luceneQuery, entityClass);

		int totalCount = query.getResultSize();
		query.setMaxResults(pageSize);
		query.setFirstResult(startIndex);
		@SuppressWarnings("unchecked")
		List<E> list = query.list();
		PaginationSupport<E> ps = new PaginationSupport<E>(list, totalCount,
				pageSize, startIndex);
		return ps;
	}

	private FullTextSession fullTextSession() {
		return Search.getFullTextSession(currentSession());
	}

	private Session currentSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	private Version getLuceneVersion() {
		return Version.LUCENE_29;
	}
}
