package com.abbcc.springrest.controller.user.group

import java.util.List;

import javax.validation.Valid

import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Order
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody;

import com.abbcc.common.CommonConst;
import com.abbcc.models.GroupGaim
import com.abbcc.models.GroupNavigator;
import com.abbcc.service.GroupGaimService
import com.abbcc.springrest.controller.BaseController
import com.abbcc.util.StringUtil;

@Controller
@Scope("prototype")
@RequestMapping("/group/gaim")
public class GaimController extends BaseController<GroupGaim> {


	@Autowired
	private GroupGaimService gaimService;

	@RequestMapping(value = "/list")
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this.getCurrentUser().getEnterpriseId()));
		detachedCriteria.addOrder(Order.asc("sort"));
		this.resultList = gaimService.findAllByCriteria(detachedCriteria);
		return "group/gaim/list";
	}

	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value = "/addPage")
	public String addPage() {
		return "group/gaim/add";
	}

	@RequestMapping(value = "/editPage")
	public String editPage() {
		return "group/gaim/edit";
	}

	@RequestMapping(value = "/save")
	public String save(@ModelAttribute("model") @Valid GroupGaim gaim, BindingResult result) {
		if(hasFieldError(result, "account")) {
			return "group/gaim/add";
		}
		entity.setEnterpriseId this.getCurrentUser().getEnterpriseId();
		entity.setSort newSort(Property.forName("enterpriseId").eq(getCurrentUser().getEnterpriseId()));
		entity.setDisplay "1"
		gaimService.save entity
		return "redirect:list";
	}

	@RequestMapping(value = "/update")
	public String update(@ModelAttribute("model") @Valid GroupGaim gaim, BindingResult result) {
		if(hasFieldError(result, "account")) {
			return "group/gaim/add";
		}
		gaimService.update entity
		return "redirect:list";
	}

	@RequestMapping(value = "/delete")
	public String delete() {
		gaimService.delete entity;
		return "redirect:list";
	}

	@RequestMapping(value = "/display")
	@ResponseBody
	public void display() {
		String hql = "update GroupGaim set display=? where gaimId=?";
		gaimService.bulkUpdate(hql, entity.getDisplay(), entity.getGaimId());
	}

	@RequestMapping(value = "/step")
	public String step(Integer stepType, int sourceSort, String sourceCate) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		if (stepType == 1) {
			detachedCriteria.addOrder(Order.asc("sort"));
			detachedCriteria.add(Restrictions.gt("sort", sourceSort));
		} else {
			detachedCriteria.addOrder(Order.desc("sort"));
			detachedCriteria.add(Restrictions.lt("sort", sourceSort));
		}
		pageList = gaimService.findPageByCriteria(detachedCriteria, 1, 0);
		for (GroupGaim ac : (List<GroupGaim>) pageList.getItems()) {
			changeSort(sourceCate, sourceSort, ac.getGaimId(),ac.getSort());
		}
		return "redirect:list";
	}
	private int[] changeSort(String sourceCate, int sourceSort, String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
		|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update group_gaim set sort=${targetSort} where gaim_id='${sourceCate}'".toString()
		sql[1] = "update group_gaim set sort=${sourceSort} where gaim_id='${targetCate}'".toString()
		return gaimService.batchUpdate(sql);
	}
}
