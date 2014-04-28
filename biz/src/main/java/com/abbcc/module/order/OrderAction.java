/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "OrderAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-28           baowp                      initial
 */

package com.abbcc.module.order;

import java.text.NumberFormat;
import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
//import org.junit.Before;
//import org.junit.Test;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcOrder;
import com.abbcc.models.AbcProduct;
import com.abbcc.service.OrderService;
import com.abbcc.util.BeansFactory;
import com.abbcc.util.DateUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.OrderDealState;

@SuppressWarnings("serial")
public class OrderAction extends BaseAction<AbcOrder> {

	private OrderService orderService;

	public NumberFormat numFmt;

	public String frontTime;
	public String backTime;

	public Integer statType;

	public OrderAction() {
		numFmt = NumberFormat.getInstance();
		numFmt.setMaximumFractionDigits(2);
		numFmt.setMinimumFractionDigits(2);
	}

	//@Before
	public void b() {
		orderService = (OrderService) BeansFactory.get("orderService");
	}

	@SuppressWarnings("unchecked")
	//@Test
	public void t2() {
		String hql = "select new map (product.productId as productId,product.name as productName,product.price as price,sum(orderSum)as total,"
				+ "sum(product.price*orderSum)as amount) from AbcOrder where dealState='"
				+ OrderDealState.DA.name()
				+ "'"
				+ " group by product.productId,product.name,product.price";
		List list = orderService.find(hql);
		log.info(list.size());
	}

	@SuppressWarnings("unchecked")
	public void t() {
		// resultList=orderService.findAll(entityClass);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		// getModel().setDealState(DealState.DB);
		detachedCriteria.add(Example.create(getModel()));
		detachedCriteria.add(Restrictions.eq("gerorderEnt.enterpriseId",
				"Enterp_0000000000000000000000027"));
		detachedCriteria.createAlias("orderEnt", "orderEnt");
		detachedCriteria.createAlias("product", "product");
		// detachedCriteria.add(Restrictions.sqlRestriction("orderEnt.name like '小王%'"));
		// detachedCriteria.add(Property.forName("amount").eq("01df"));
		// DetachedCriteria dc=DetachedCriteria.forClass(AbcProduct.class,
		// "pro").add(Subqueries.)

		resultList = orderService.findAllByCriteria(detachedCriteria);
		pageList = orderService.findPageByCriteria(detachedCriteria);
		log.info(pageList.getItems().size());
	}

	@SuppressWarnings("unchecked")
	public void tempEntList() {
		List<AbcEnterprise> list = orderService.findAll(AbcEnterprise.class);
		getRequest().setAttribute("entList", list);
	}

	@SuppressWarnings("unchecked")
	public void tempProductList() {
		List<AbcProduct> list = orderService.findAll(AbcProduct.class);
		getRequest().setAttribute("productList", list);
	}

	public String save() {
		orderService.saveOrUpdate(entity);
		return SUCCESS;
	}

	public String list() {
		AbcEnterprise ent = (AbcEnterprise) getSession().getAttribute(
				CommonConst.SESSIONENT);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(entityClass);
		entity.setState(CommonConst.STATENORMAL);
		detachedCriteria.add(Example.create(entity));
		detachedCriteria.add(Restrictions.eq("gerorderEnt.enterpriseId", ent
				.getEnterpriseId()));
		detachedCriteria.addOrder(Property.forName("orderTime").desc());
		if (entity.getProduct() != null
				&& StringUtil.isNotBlank(entity.getProduct().getName()))
			detachedCriteria.createAlias("product", "pro").add(
					Property.forName("pro.name").like(
							entity.getProduct().getName(), MatchMode.ANYWHERE));
		if (entity.getOrderEnt() != null
				&& StringUtil.isNotBlank(entity.getOrderEnt().getName()))
			detachedCriteria.createAlias("orderEnt", "ent")
					.add(
							Property.forName("ent.name").like(
									entity.getOrderEnt().getName(),
									MatchMode.ANYWHERE));
		if (StringUtil.isNotBlank(frontTime))
			detachedCriteria.add(Property.forName("orderTime").ge(
					DateUtil.strToDate(frontTime)));
		if (StringUtil.isNotBlank(backTime))
			detachedCriteria.add(Property.forName("orderTime").le(
					DateUtil.strToDate(backTime)));

		pageList = orderService.findPageByCriteria(detachedCriteria, pageSize,
				startIndex, CriteriaSpecification.ROOT_ENTITY);
		return LIST;
	}

	public String stat() {
		return "stat";
	}

	public String stating() {
		if (statType == 1)
			statProduct();
		else if (statType == 2)
			statCustom();
		return "stat";
	}

	private void statProduct() {
		String hql = "select new list (product.name as productName,product.price as price,sum(orderSum)as total,"
				+ "sum(product.price*orderSum)as amount) from AbcOrder ";
		hql = addRestriction(hql);
		hql += " group by product.name,product.price";
		@SuppressWarnings("unchecked")
		List list = orderService.find(hql);
		getRequest().setAttribute("statList", list);
	}

	private void statCustom() {
		String hql = "select new list (orderEnt.name as entName,sum(orderSum)as total,"
				+ "sum(product.price*orderSum)as amount) from AbcOrder ";
		hql = addRestriction(hql);
		hql += " group by orderEnt.name";
		@SuppressWarnings("unchecked")
		List list = orderService.find(hql);
		getRequest().setAttribute("statList", list);
	}

	private String addRestriction(String hql) {
		String where = "";
		if (StringUtil.isNotBlank(frontTime))
			where += "and orderTime>=to_date('" + frontTime
					+ "','yyyy-mm-dd') ";
		if (StringUtil.isNotBlank(backTime))
			where += "and orderTime<=to_date('" + backTime + "','yyyy-mm-dd') ";
		if (entity.getDealState() != null)
			where += "and dealState='" + entity.getDealState().name() + "' ";
		if (where.length() > 0)
			hql += "where " + where.substring(4);
		return hql;
	}
	
	public String dealState(){
		orderService.update(entity);
		return "json";
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}
