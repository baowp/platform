package com.abbcc.springrest.controller.user.site;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abbcc.models.GroupLaymod;
import com.abbcc.models.GroupLayout;
import com.abbcc.models.GroupLaytheme;
import com.abbcc.models.GroupUserdefined;
import com.abbcc.models.GroupWidth;
import com.abbcc.service.GroupLayoutService;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/layout")
public class LayoutController extends BaseSiteController<GroupLayout> {
	@Autowired
	private GroupLayoutService layoutService;
	private List<GroupLaymod> layoutmoduleList;
	private List<GroupUserdefined> userDefinedList;
	private GroupLaytheme laytheme;
	private GroupWidth listWidth;
	private String belongPage;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save() {
		layoutService.cascadeSave(this);
		return SUCCESS;
	}

	public List<GroupLaymod> getLayoutmoduleList() {
		return layoutmoduleList;
	}

	public void setLayoutmoduleList(List<GroupLaymod> layoutmoduleList) {
		this.layoutmoduleList = layoutmoduleList;
	}

	public GroupLaytheme getLaytheme() {
		return laytheme;
	}

	public void setLaytheme(GroupLaytheme laytheme) {
		this.laytheme = laytheme;
	}

	public String getBelongPage() {
		return belongPage;
	}

	public void setBelongPage(String belongPage) {
		this.belongPage = belongPage;
	}

	public GroupWidth getListWidth() {
		return listWidth;
	}

	public void setListWidth(GroupWidth listWidth) {
		this.listWidth = listWidth;
	}

	public List<GroupUserdefined> getUserDefinedList() {
		return userDefinedList;
	}

	public void setUserDefinedList(List<GroupUserdefined> userDefinedList) {
		this.userDefinedList = userDefinedList;
	}

}
