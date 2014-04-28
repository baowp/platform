package com.abbcc.module.usersite;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcLayout;
import com.abbcc.service.LayoutService;
import com.abbcc.util.ObjectUtil;

@SuppressWarnings("serial")
public class AnalysisAction extends SiteBaseAction<AbcLayout> {

	@Autowired
	private LayoutService layoutService;

	public String edit() {
		String enterpriseId = this.getCurrentUser().getEnterpriseId();
		DetachedCriteria dc = DetachedCriteria
				.forClass(AbcLayout.class)
				.add(Property.forName("enterpriseId").eq(enterpriseId))
				.add(Property.forName("state").eq(CommonConst.STATENORMAL))
				.setProjection(
						Projections.projectionList()
								.add(Property.forName("layoutId"))
								.add(Property.forName("footerContent")));
		List<String[]> list = layoutService.findAllByCriteria(dc);
		log.info(3);
		if (!list.isEmpty()) {
			Object[] obj = list.get(0);
			String layoutId = (String)obj[0];
			String footerContent = (String)obj[1];
			entity.setLayoutId(layoutId);
			entity.setFooterContent(footerContent);
		}
		return EDIT;
	}

	public String update() {
		String hql = "UPDATE AbcLayout SET footerContent=? WHERE layoutId=?";
		layoutService.bulkUpdate(hql, entity.getFooterContent(),
				entity.getLayoutId());
		return NONE;
	}

}
