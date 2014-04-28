package com.abbcc.springrest.controller.user.site;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abbcc.models.GroupLaytheme;
import com.abbcc.service.GroupLaythemeService;
import com.abbcc.util.constant.group.GroupLaythemeType;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/laytheme")
public class LaythemeController  extends BaseSiteController<GroupLaytheme> {

	@Autowired
	private GroupLaythemeService laythemeService;
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public GroupLaytheme save() {
		return save(GroupLaythemeType.B);
	}

	/**
	 * 另存为 风格库
	 * @return
	 */
	@RequestMapping(value = "/saveAs")
	@ResponseBody
	public GroupLaytheme saveAs() {
		return save(GroupLaythemeType.A);
	}

	private GroupLaytheme save(GroupLaythemeType ls) {
		entity.setType(ls);		// 类型
		entity.setAddTime(new Date());
		laythemeService.saveOrUpdate(entity);
		return entity;
	}

	@RequestMapping(value = "/refresh")
	@ResponseBody
	public GroupLaytheme refresh() {
		laythemeService.refresh(entity);
		return entity;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete() throws IOException {
		laythemeService.delete(entity);
		return SUCCESS;
	}
}
