package com.abbcc.springrest.controller.user.group;

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

import com.abbcc.models.GroupGaim;
import com.abbcc.models.GroupUserdefined;
import com.abbcc.service.GroupUserdefinedService;
import com.abbcc.springrest.controller.BaseController;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/group/userdefined")
public class DefinedController extends BaseController<GroupUserdefined> {
	@Autowired
	private GroupUserdefinedService definedService;

	@RequestMapping(value = "/list")
	public String list() {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.add(Restrictions.eq("enterpriseId", this.getCurrentUser()
				.getEnterpriseId()));
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("userdefinedId"))
				.add(Property.forName("name")));
		dc.addOrder(Order.asc("serial"));
		this.resultList = definedService.findAllByCriteria(dc);
		return "group/userdefined/list";
	}

	/**
	 * 添加or修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage")
	public String editPage() {
		return "group/userdefined/edit";
	}

	@RequestMapping(value = "/save")
	public String save(
			@ModelAttribute("model") @Valid GroupUserdefined defined,
			BindingResult result) {
		if (hasFieldError(result, "name")) {
			return "group/userdefined/edit";
		}
		if (StringUtil.isBlank(entity.getUserdefinedId())) {
			Integer sort = definedService.newSort(Property.forName(
					"enterpriseId").eq(getCurrentUser().getEnterpriseId()));
			entity.setEnterpriseId(this.getCurrentUser().getEnterpriseId());
			entity.setSerial(sort);
			entity.setModuleId("user_defined" + sort);
			definedService.save(entity);
		} else {
			definedService.update(entity);
		}
		return "redirect:list";
	}

	@RequestMapping(value = "/delete")
	public String delete() {
		definedService.cascadeDel(entity);
		return "redirect:list";
	}
}
