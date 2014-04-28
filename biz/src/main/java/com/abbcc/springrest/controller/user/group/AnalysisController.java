package com.abbcc.springrest.controller.user.group;

import javax.validation.Valid;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abbcc.models.GroupAnalysis;
import com.abbcc.service.GroupAnalysisService;
import com.abbcc.springrest.controller.BaseController;
import com.abbcc.util.StringUtil;

/**
 * 统计分析
 * 
 * @author better
 * 
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/group/analysis")
public class AnalysisController extends BaseController<GroupAnalysis> {
	@Autowired
	private GroupAnalysisService analysisService;

	@RequestMapping(value = "/list")
	public String list() {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.add(Restrictions.eq("enterpriseId", this.getCurrentUser()
				.getEnterpriseId()));
		this.resultList = analysisService.findAllByCriteria(dc);
		return "group/analysis/list";
	}

	/**
	 * 添加or修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage")
	public String editPage() {
		return "group/analysis/edit";
	}

	@RequestMapping(value = "/save")
	public String save(@ModelAttribute("model") @Valid GroupAnalysis defined, BindingResult result) {
		if (StringUtil.isBlank(entity.getAnalysisId())) {
			entity.setEnterpriseId(this.getCurrentUser().getEnterpriseId());
			entity.setDisplay("1");
			analysisService.save(entity);
		} else {
			analysisService.update(entity);
		}
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete() {
		analysisService.delete(entity);
		return "redirect:list";
	}
	
	@RequestMapping(value = "display")
	@ResponseBody
	public void display() {
		analysisService.updatePropery(entity.getAnalysisId(), "display", entity.getDisplay());
	}
}
