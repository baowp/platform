package com.abbcc.module.usersite;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcLayout;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.models.AbcUser;
import com.abbcc.service.LayoutService;
import com.abbcc.util.ObjectUtil;

@SuppressWarnings("serial")
public class LayoutAction extends BaseAction<AbcLayout> {

	private LayoutService layoutService;

	public String save() {
		collect();
		for (AbcLayoutmodule lm : entity.getLayoutmoduleList())
			lm.setLayout(entity);

		layoutService.cascadeSave(entity);
		setUploadState(entity.getBannerContent());
		return "json";
	}

	public void simpleSave() throws IOException {
		collect();
		layoutService.save(entity);
		output(entity.getLayoutId());
	}

	private void collect() {
		AbcUser user = getCurrentUser();
		entity.setEnterpriseId(user.getEnterpriseId());
		entity.setState(CommonConst.STATENORMAL);
		if (entity.getLayoutId() == null) {
			AbcLayout example = new AbcLayout();
			example.setEnterpriseId(entity.getEnterpriseId());
			example.setState(entity.getState());
			List<AbcLayout> list = layoutService.findByExample(example);
			if (list.isEmpty()) {
			} else {
				ObjectUtil.extend(list.get(0), entity);
				ObjectUtil.extend(entity, list.get(0));
			}
		}
		entity.setUserId(user.getUserId());
		entity.setUpdateTime(new Date());
	}

	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}
}
