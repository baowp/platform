/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "ProdManageAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-11           yixiugg                      initial
 **/

package com.abbcc.module.entManage;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.service.ProductService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;

/**
 ** ProdManageAction.java
 **/
@SuppressWarnings("serial")
public class ProdManageAction extends BaseAction<AbcProduct> {
	private ProductService productService;
	private String entId;
	private String[] prodIds;
	private String entName;
	private String searchKey;
	private String prodState = CommonConst.STATEINIT;
	private String SEARCH = "search";
	private String LISTSEARCH = "listsearch";
	public boolean isBroadcast;
	public boolean checkwp;

	/**
	 * 查看审核
	 * 
	 * @return
	 */
	public String viewAudit() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcProduct.class);
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain",getCurrentAdmin().getDomain()));
		}
		detachedCriteria.add(Restrictions.eq("state", prodState));
		detachedCriteria.addOrder(Order.desc("addTime"));
		if (StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("name", searchKey,
					MatchMode.ANYWHERE));
		if (isBroadcast)
			detachedCriteria.add(Restrictions.eq("broadcast", "01"));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = productService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex, CriteriaSpecification.ROOT_ENTITY);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return INPUT;
	}

	/**
	 * 审核产品
	 * 
	 * @return
	 */
	public String editAudit() {
		for (String prodId : prodIds) {
			String[] prodIdAndState = StringUtil.splitBySemicolon(prodId);
			AbcProduct prod = productService.findById(prodIdAndState[0]);
			prod.setState(prodIdAndState[1]);
			productService.saveOrUpdate(prod);
			this.savaAdminLog("产品审核", prod.getName(), CommonConst.LOGUPDATE);
		}
		result = "审核成功！";
		return VIEW;
	}

	/**
	 * 查询产品
	 * 
	 * @return
	 */
	public String search() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(
				AbcProduct.class, "pro");
		if (!getCurrentAdmin().getUsername().equals("admin")) {
			detachedCriteria.add(Restrictions.eq("domain",getCurrentAdmin().getDomain()));
		}
		detachedCriteria.add(Restrictions.eq("state", CommonConst.STATENORMAL));
		detachedCriteria.addOrder(Order.desc("addTime"));
		if (checkwp){
			AbcUser au = new AbcUser();
			au.setGrade(CommonConst.USERGRADEONE);
			au.setState(CommonConst.STATENORMAL);
			DetachedCriteria dc = DetachedCriteria
					.forClass(AbcUser.class)
					.add(Example.create(au)).setProjection(Projections.property("enterpriseId"));
			List<String> secondList = baseService.findAllByCriteria(dc);
			if(secondList.size()!=0){
				List<List> ob = ObjectUtil.getInDataByList(secondList);
				for(int i=0;i<ob.size();i++){
					detachedCriteria.add(Restrictions.in("enterpriseId",ob.get(i)));
				}
			}
		}
		if(isBroadcast)
			detachedCriteria.add(Restrictions.eq("broadcast","01"));
		if (StringUtil.isNotBlank(entId))
			detachedCriteria.add(Restrictions.eq("enterpriseId", entId));
		if (StringUtil.isNotBlank(searchKey))
			detachedCriteria.add(Restrictions.like("name", searchKey,
					MatchMode.ANYWHERE));
		this.startIndex = (this.page - 1) * pageSize;
		this.pageList = productService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex, CriteriaSpecification.ROOT_ENTITY);
		if (pageList.getTotalCount() == 0)
			result = CommonConst.NORESULT;
		return SEARCH;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		for (String prodId : prodIds) {
			String[] prodIdAndState = StringUtil.splitBySemicolon(prodId);
			AbcProduct prod = productService.findById(prodIdAndState[0]);
			prod.setState(prodIdAndState[1]);
			productService.saveOrUpdate(prod);
			this.savaAdminLog("产品", prod.getName(), CommonConst.LOGDEL);
		}
		result = "删除成功！";
		return LISTSEARCH;
	}

	public String info() {
		return "proinfo";
	}

	/**
	 * 设置企业是否推荐00,不推荐，01推荐
	 * 
	 * @return
	 */
	public void setBroadcast() {
		if (StringUtil.isNotBlank(entity.getProductId())) {
			AbcProduct ae = productService.findById(entity.getProductId());
			if (StringUtil.isNotBlank(entity.getBroadcast())) {
				ae.setBroadcast(entity.getBroadcast().equals("00") ? "01"
						: "00");
			} else {
				ae.setBroadcast("01");
			}
			productService.saveOrUpdate(ae);
		}
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public String getEntId() {
		return entId;
	}

	public void setEntId(String entId) {
		this.entId = entId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getProdState() {
		return prodState;
	}

	public void setProdState(String prodState) {
		this.prodState = prodState;
	}

	public String[] getProdIds() {
		return prodIds;
	}

	public void setProdIds(String[] prodIds) {
		this.prodIds = prodIds;
	}

}
