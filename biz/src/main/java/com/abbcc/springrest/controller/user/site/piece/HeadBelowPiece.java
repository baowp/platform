package com.abbcc.springrest.controller.user.site.piece;

import java.util.List;

import net.sf.json.JSONArray;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;
import com.abbcc.models.GroupAnalysis;
import com.abbcc.models.GroupLayout;
import com.abbcc.models.GroupNavigator;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
public class HeadBelowPiece<T> extends AutowiredService<AbcUser> {

	@RequestMapping(value = "/{username}/hunk_logo")
	public String pieceLogo() {
		if (before()) {
		}
		return viewName("group/dynamic/piece/hunk/hunk_logo");
	}

	@RequestMapping(value = "/{username}/hunk_navigator")
	public String pieceNavigator() {
		if (before()) {
			List<GroupNavigator> list = navigatorList(1, null);
			for (GroupNavigator gn : list) {
				List<GroupNavigator> subList = navigatorList(2,
						gn.getNavigatorId());
				for (GroupNavigator gns : subList) {
					gns.setSubJson(JSONArray.fromObject(navigatorList(3,
							gns.getNavigatorId())));
				}
				gn.setSubJson(JSONArray.fromObject(subList));
			}
			deposit("navigator", list);
		}
		return viewName("group/dynamic/piece/hunk/hunk_navigator");
	}

	private List<GroupNavigator> navigatorList(int i, String navigatorId) {
		GroupNavigator example = new GroupNavigator();
		example.setEnterpriseId(entity.getEnterpriseId());
		example.setGrade(i);
		if (StringUtil.isNotBlank(navigatorId))
			example.setParentId(navigatorId);
		example.setDisplay("1");
		DetachedCriteria dc = DetachedCriteria.forClass(GroupNavigator.class)
				.add(Example.create(example))
				.addOrder(Property.forName("sort").asc());
		List<GroupNavigator> list = navigatorService.findAllByCriteria(dc);

		return list;
	}

	@RequestMapping(value = "/{username}/hunk_flash")
	public String pieceFlash() {
		if (before()) {
			if (take("layout") == null) {
				GroupLayout layout = new GroupLayout();
				layout.setEnterpriseId(entity.getEnterpriseId());
				layout.setState(CommonConst.STATENORMAL);
				List<GroupLayout> layoutList = layoutService
						.findByExample(layout);
				if (layoutList.size() > 0) {
					layout = layoutList.get(0);
				}
				deposit("layout", layout);
			}
		}
		return viewName("group/dynamic/piece/hunk/hunk_flash");
	}

	@RequestMapping(value = "/{username}/hunk_foot")
	public String pieceFoot() {
		if (before()) {
		}
		return viewName("group/dynamic/piece/hunk/hunk_foot");
	}

	// 统计分析
	public void analysis(String enterpriseId) {
		DetachedCriteria dc = DetachedCriteria.forClass(GroupAnalysis.class);
		dc.add(Property.forName("enterpriseId").eq(enterpriseId));
		dc.add(Property.forName("display").eq("1"));
		dc.setProjection(Projections.property("code"));
		List<GroupAnalysis> list = analysisService.findAllByCriteria(dc);
		deposit("analysisList", list);
	}

	@RequestMapping(value = "/{username}/hunk_show")
	public String pieceShow() {
		if (before()) {
		}
		return viewName("group/dynamic/piece/hunk/hunk_show");
	}
}