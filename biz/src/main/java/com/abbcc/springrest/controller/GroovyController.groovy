package com.abbcc.springrest.controller

import java.util.List;

import javax.validation.Valid;

import com.abbcc.models.AbcBrand
import com.abbcc.models.AbcUser;
import com.abbcc.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("prototype")
@RequestMapping("/groovy")
class GroovyController extends BaseController<AbcBrand> {
	@Autowired
	private BrandService brandService;

	@RequestMapping(value = "/index")
	public String sample(@ModelAttribute("model") @Valid AbcBrand us,
	BindingResult result, String name) {
		List<?> list = brandService.findAll();
		list = baseService.findAll(AbcBrand.class);
		deposit("list", list);
		deposit("message", "message is hello");
		deposit("mm", "");
		AbcUser user = getCurrentUser();
		deposit("user", user);
		log.info(entity.getName());
		this.result = "result";
		return "sample/springrest/sample";
	}

	@RequestMapping(value = "/index/{id}")
	public String name(AbcUser us, @PathVariable String id) {
		List<?> list = brandService.findAll();
		list = baseService.findAll(AbcBrand.class);
		deposit("list", list);
		deposit("name", id);
		log.info(id);
		return "sample/springrest/sample.part1";
	}

	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String form(AbcUser user) {
		log.info(user.getUsername());
		result = "result";
		deposit("user", user);
		return "redirect:index/bao";
	}

	public void getStr() {
		deposit("name3", "name3");
	}

	public void getStr(String s) {
		deposit("name4", "name4");
	}
}
