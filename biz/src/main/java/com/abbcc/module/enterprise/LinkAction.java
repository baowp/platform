package com.abbcc.module.enterprise;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcLink;
import com.abbcc.service.LinkService;
import com.abbcc.util.checkKey.CheckKey;

public class LinkAction extends BaseAction<AbcLink>{
	public int stepType;
	public String sourceCate;
	public int sourceSort;
	public String targetCate;
	public int targetSort;
	@Autowired
	private LinkService linkService;
	
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				this.getCurrentUser().getEnterpriseId()));
		detachedCriteria.addOrder(Order.asc("lorder"));
		this.resultList = linkService.findByCriteria(detachedCriteria);
		return LIST;
	}
	
	// 添加友情链接
	public String save() {
		String enterpriseId = this.getCurrentUser().getEnterpriseId();	
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		if (!CheckKey.checkKey(entity.getUrl())) {
			this.addFieldError("url", "存在非法字符！");
			return INPUT;
		}
		entity.setEnterpriseId(enterpriseId);
		entity.setUserId(this.getCurrentUser().getUserId());
		entity.setLorder(newSort());
		linkService.save(entity);
		result=CommonConst.ADDSUCCESS;
		return SUCCESS;
	}
	
	public String update() {
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		if (!CheckKey.checkKey(entity.getUrl())) {
			this.addFieldError("url", "存在非法字符！");
			return INPUT;
		}
		linkService.update(entity);
		result=CommonConst.EDITSUCCESS;
		return SUCCESS;
	}
	
	public String delete() {
		linkService.delete(entity);
		result=CommonConst.DELSUCCESS;
		return SUCCESS;
	}
	
	// 返回排序字段
	private Integer newSort() {
		DetachedCriteria dc = DetachedCriteria
				.forClass(entity.getClass())
				.add(Property.forName("enterpriseId").eq(
						getCurrentUser().getEnterpriseId()))
				.setProjection(Projections.max("lorder"));
		@SuppressWarnings("unchecked")
		List<Integer> list = linkService.findAllByCriteria(dc);
		Integer sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}
	
	// 调顺序
	public String step() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				this.getCurrentUser().getEnterpriseId()));

		if (stepType == 1) {
			detachedCriteria.addOrder(Order.asc("lorder"));
			detachedCriteria.add(Restrictions.gt("lorder", sourceSort));
		} else {
			detachedCriteria.addOrder(Order.desc("lorder"));
			detachedCriteria.add(Restrictions.lt("lorder", sourceSort));
		}
		pageList = linkService
				.findPageByCriteria(detachedCriteria, 1, 0);
		for (AbcLink ac : (List<AbcLink>) pageList.getItems()) {
			changeSort(sourceCate, sourceSort, ac.getLinkId(),
					ac.getLorder());
		}
		return SUCCESS;
	}
	
	private int[] changeSort(String sourceCate, int sourceSort,
			String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
				|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update abc_link set lorder=" + targetSort
				+ " where link_id='" + sourceCate + "'";
		sql[1] = "update abc_link set lorder=" + sourceSort
				+ " where link_id='" + targetCate + "'";
		return linkService.batchUpdate(sql);
	}
}
