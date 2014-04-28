package com.abbcc.springrest.controller.user.group;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.abbcc.models.GroupNavigator;
import com.abbcc.service.GroupNavigatorService;
import com.abbcc.springrest.controller.BaseController;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.group.GroupPage;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/group/navigator")
public class NavigatorController extends BaseController<GroupNavigator> {

	@Autowired
	private GroupNavigatorService navigatorService;
	Map<String, String> map = new LinkedHashMap<String, String>();

	@RequestMapping(value = "/list")
	public String list() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(GroupNavigator.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		// 设置分类路径
		Map<String, String> map1 = new LinkedHashMap<String, String>();
		if (entity.getNavigatorId() != null) {
			getRoute(entity.getNavigatorId());
			map1 = this.map;
			ObjectUtil.reverse(map1);
		}
		this.deposit("position", map1);
		if (entity.getNavigatorId() == null) {
			detachedCriteria.add(Restrictions.eq("grade", 1));
		} else {
			detachedCriteria.add(Restrictions.eq("parentId",
					entity.getNavigatorId()));
		}
		detachedCriteria.addOrder(Order.asc("sort"));
		this.resultList = navigatorService.findAllByCriteria(detachedCriteria);
		this.deposit("parentId", entity.getNavigatorId());
		for(GroupNavigator g : resultList) {
			detachedCriteria = DetachedCriteria.forClass(GroupNavigator.class);
			detachedCriteria.add(Property.forName("parentId").eq(g.getNavigatorId()));
			g.setChildSize(navigatorService.getCountByCriteria(detachedCriteria));
		}
		return "group/navigator/list";
	}

	private boolean getRoute(String navigatorId) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(GroupNavigator.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		// detachedCriteria.add(Restrictions.eq("grade", grade));
		detachedCriteria.add(Restrictions.eq("navigatorId", navigatorId));
		detachedCriteria.addOrder(Order.asc("sort"));
		this.resultList = navigatorService.findAllByCriteria(detachedCriteria);
		this.map.put(navigatorId, resultList.get(0).getName());
		navigatorId = resultList.get(0).getParentId();
		int grade = resultList.get(0).getGrade();
		if ((--grade) != 0) {
			getRoute(navigatorId);
		}
		return true;
	}

	@RequestMapping(value = "/save")
	public String save(
			@ModelAttribute("model") @Valid GroupNavigator groupNavigator,
			BindingResult result) {
		if (hasFieldError(result, "name", "page")) {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(GroupNavigator.class);
			detachedCriteria.add(Restrictions.eq("enterpriseId", this
					.getCurrentUser().getEnterpriseId()));
			detachedCriteria.addOrder(Order.asc("sort"));
			if (entity.getParentId().trim().equals("")) {

				detachedCriteria.add(Restrictions.eq("grade", 1));
			} else {
				detachedCriteria.add(Restrictions.eq("parentId",
						entity.getParentId()));
			}
			this.resultList = navigatorService
					.findAllByCriteria(detachedCriteria);
			return "/group/navigator/list";
		}
		if (checkPage(entity.getPage())) {
			result.rejectValue("page", null, "该页面路径已存在！");
			this.resultList = list1(entity.getGrade(), entity.getParentId());
			return "/group/navigator/list";
		}
		String enterpriseId = this.getCurrentUser().getEnterpriseId();
		entity.setEnterpriseId(enterpriseId);
		entity.setDisplay(new String("1"));
		entity.setSort(newsort());
		navigatorService.save(entity);
		if (entity.getGrade() == 1) {
			return "redirect:list?grade=" + entity.getGrade();
		}
		return "redirect:list?grade=" + entity.getGrade() + "&navigatorId="
				+ entity.getParentId();
	}

	private List<GroupNavigator> list1(Integer grade, String navigatorId) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(GroupNavigator.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		if (entity.getGrade() == null || entity.getGrade() == 1) {
			detachedCriteria.add(Restrictions.eq("grade", 1));
		} else {
			detachedCriteria.add(Restrictions.eq("grade", grade));
			detachedCriteria.add(Restrictions.eq("parentId", navigatorId));
		}
		detachedCriteria.addOrder(Order.asc("sort"));
		this.resultList = navigatorService.findAllByCriteria(detachedCriteria);
		return this.resultList;
	}

	private boolean checkPage(String page) {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(GroupNavigator.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("page", page));
		detachedCriteria.addOrder(Order.asc("sort"));
		this.resultList = navigatorService.findAllByCriteria(detachedCriteria);
		if (resultList.size() > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public void update(
			@ModelAttribute("model") @Valid GroupNavigator groupNavigator,
			BindingResult result) {
		navigatorService.update(entity);
	}

	@RequestMapping(value = "/setDisplay")
	@ResponseBody
	public boolean setDisplay(
			@ModelAttribute("model") @Valid GroupNavigator groupNavigator,
			BindingResult result) {
		if (entity.getGrade() == 1) {
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(GroupNavigator.class);
			detachedCriteria.add(Restrictions.eq("enterpriseId", this
					.getCurrentUser().getEnterpriseId()));
			detachedCriteria.add(Restrictions.eq("display", "1"));
			detachedCriteria.add(Restrictions.isNull("parentId"));
			this.resultList = navigatorService
					.findAllByCriteria(detachedCriteria);
			if (entity.getDisplay().equals("1")) {
				if (resultList.size() > 7)
					return false;
			}
		}
		navigatorService.update(entity);
		return true;
	}

	@RequestMapping(value = "/deleteNav")
	@ResponseBody
	public boolean deleteNav() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(GroupNavigator.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("parentId",
				entity.getNavigatorId()));
		detachedCriteria.addOrder(Order.asc("sort"));
		this.resultList = navigatorService.findAllByCriteria(detachedCriteria);
		if (resultList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/delete")
	public String delete() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(GroupNavigator.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		detachedCriteria.add(Restrictions.eq("parentId",
				entity.getNavigatorId()));
		detachedCriteria.addOrder(Order.asc("sort"));
		this.resultList = navigatorService.findAllByCriteria(detachedCriteria);
		if (resultList.size() > 0) {
			return "/group/navigator/list";
		}
		navigatorService.deleteNav(entity);
		if (entity.getGrade() == 1) {
			return "redirect:list?grade=" + entity.getGrade();
		}
		return "redirect:list?grade=" + entity.getGrade() + "&navigatorId="
				+ entity.getParentId();
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
		pageList = navigatorService.findPageByCriteria(detachedCriteria, 1, 0);
		for (GroupNavigator ac : (List<GroupNavigator>) pageList.getItems()) {
			changeSort(sourceCate, sourceSort, ac.getNavigatorId(),
					ac.getSort());
		}
		if (StringUtil.isBlank(entity.getNavigatorId())) {
			return "redirect:list";
		}
		return "redirect:list?&navigatorId=" + entity.getNavigatorId();
	}

	private int[] changeSort(String sourceCate, int sourceSort,
			String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
				|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update group_navigator set sort=" + targetSort
				+ " where navigator_id='" + sourceCate + "'";
		sql[1] = "update group_navigator set sort=" + sourceSort
				+ " where navigator_id='" + targetCate + "'";
		return navigatorService.batchUpdate(sql);
	}

	private Integer newsort() {
		DetachedCriteria dc = DetachedCriteria
				.forClass(entity.getClass())
				.add(Property.forName("enterpriseId").eq(
						getCurrentUser().getEnterpriseId()))
				.setProjection(Projections.max("sort"));
		@SuppressWarnings("unchecked")
		List<Integer> list = baseService.findAllByCriteria(dc);
		Integer sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}

	public boolean preDelete(String value) {
		return GroupPage.contain(value);
	}
}
