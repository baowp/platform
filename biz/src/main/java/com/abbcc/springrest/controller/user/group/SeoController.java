package com.abbcc.springrest.controller.user.group;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.models.GroupNavigator;
import com.abbcc.models.GroupSeo;
import com.abbcc.service.GroupNavigatorService;
import com.abbcc.service.GroupSeoService;
import com.abbcc.springrest.controller.BaseController;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/group/seo")
public class SeoController extends BaseController<GroupSeo> {

	@Autowired
	private GroupSeoService seoService;
	@Autowired
	private GroupNavigatorService navigatorService;

	private Map<String, String> navigatorRouteMap = new LinkedHashMap<String, String>();

	@RequestMapping(value = "/list")
	public String list(String navigatorId) {
		DetachedCriteria dc = DetachedCriteria.forClass(GroupNavigator.class);
		dc.add(Property.forName("enterpriseId").eq(
				this.getCurrentUser().getEnterpriseId()));
		dc.add(Property.forName("display").eq("1"));
		if (navigatorId == null) {
			dc.add(Restrictions.eq("grade", 1));
		} else {
			dc.add(Restrictions.eq("parentId", navigatorId));
		}
		dc.addOrder(Order.asc("sort"));
		List<GroupNavigator> list = navigatorService.findAllByCriteria(dc);
		for(GroupNavigator g : list) {
			dc = DetachedCriteria.forClass(GroupNavigator.class);
			dc.add(Property.forName("parentId").eq(g.getNavigatorId()));
			g.setChildSize(navigatorService.getCountByCriteria(dc));
		}
		navigatorRoute(navigatorId);
		this.deposit("navigatorList", list);
		return "group/seo/list";
	}

	@RequestMapping(value = "/editPage")
	public String editPage(String page) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.add(Property.forName("enterpriseId").eq(this.getCurrentUser().getEnterpriseId()));
		dc.add(Property.forName("page").eq(page));
		this.resultList = seoService.findAllByCriteria(dc);
		if(!resultList.isEmpty()) {
			 this.entity = resultList.get(0);
		}
		
//		dc = DetachedCriteria.forClass(GroupNavigator.class);
//		dc.add(Restrictions.eq("enterpriseId", this.getCurrentUser()
//				.getEnterpriseId())).add(Restrictions.eq("page", page));
//		dc.setProjection(Property.forName("name"));
//		deposit("page", page);
//		deposit("name", navigatorService.findAllByCriteria(dc).get(0));
		return "group/seo/edit";
	}
	
	@RequestMapping(value = "/save")
	public String save() {
		entity.setEnterpriseId(this.getCurrentUser().getEnterpriseId());
		seoService.saveOrUpdate(entity);
		
		String navigatorId = this.getRequest().getParameter("navigatorId");
		String parentId = null;
		if(StringUtil.isNotBlank(navigatorId)) {
			DetachedCriteria dc = DetachedCriteria.forClass(GroupNavigator.class);
			dc.add(Property.forName("navigatorId").eq(navigatorId));
			parentId = navigatorService.findAllByCriteria(dc).get(0).getParentId();
		}
		return list(parentId);
	}
	

	/**
	 * 递归,返回路径
	 * 
	 * @param navigatorId
	 */
	private void navigatorRoute(String navigatorId) {
		DetachedCriteria dc = DetachedCriteria.forClass(GroupNavigator.class);
		dc.add(Restrictions.eq("enterpriseId", this.getCurrentUser()
				.getEnterpriseId()));
		dc.add(Restrictions.eq("navigatorId", navigatorId));
		List<GroupNavigator> list = navigatorService.findAllByCriteria(dc);
		if (!list.isEmpty()) {
			navigatorRouteMap.put(navigatorId, list.get(0).getName());
			navigatorRoute(list.get(0).getParentId());
		}
		ObjectUtil.reverse(navigatorRouteMap);
		this.deposit("navigatorRoute", navigatorRouteMap);
	}
}
