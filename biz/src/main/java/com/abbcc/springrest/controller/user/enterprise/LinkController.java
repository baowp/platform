package com.abbcc.springrest.controller.user.enterprise;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcLink;
import com.abbcc.service.LinkService;
import com.abbcc.springrest.controller.BaseController;
import com.abbcc.util.checkKey.CheckKey;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/user/enterprise/link")
public class LinkController extends BaseController<AbcLink> {

	@Autowired
	private LinkService linkService;
	
	@RequestMapping(value = "/list")
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				this.getCurrentUser().getEnterpriseId()));
		detachedCriteria.addOrder(Order.asc("lorder"));
		this.resultList = linkService.findByCriteria(detachedCriteria);
		return "user/enterprise/link/list";
	}
	
	// 添加友情链接
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute("model") @Valid AbcLink link, BindingResult result) {
		if(hasFieldError(result, "name", "url")) {
			return "user/enterprise/link/add";
		}
		String enterpriseId = this.getCurrentUser().getEnterpriseId();	
		entity.setEnterpriseId(enterpriseId);
		entity.setUserId(this.getCurrentUser().getUserId());
		entity.setLorder(newSort());
		linkService.save(entity);
		this.result = CommonConst.ADDSUCCESS;
		return "redirect:list";
	}
	
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute("model") @Valid AbcLink link, BindingResult result) {
		if(hasFieldError(result, "name", "url")) {
			return "user/enterprise/link/edit";
		}
		linkService.update(entity);
		this.result = CommonConst.ADDSUCCESS;
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete() {
		linkService.delete(entity);
		result = CommonConst.ADDSUCCESS;
		return "redirect:list";
	}
	
	@RequestMapping(value = "/addPage")
	public String addPage() {
		return "user/enterprise/link/add";
	}
	
	@RequestMapping(value = "/editPage")
	public String editPage() {
		return "user/enterprise/link/edit";
	}
	
	@RequestMapping(value = "/step")
	public String step(Integer stepType, int sourceSort, String sourceCate ) {
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
		return "redirect:list";
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
	
}
