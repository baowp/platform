package com.abbcc.springrest.controller.user.site;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Subqueries;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcUser;
import com.abbcc.springrest.controller.user.site.piece.UserDefinedPiece;

@Controller
@Scope("prototype")
@RequestMapping("/piece")
public class PieceController<T> extends UserDefinedPiece<AbcUser> {

	private static final long serialVersionUID = 1L;

	private boolean isBefore;

	protected boolean before() {
		if (isBlank(entity.getUsername())) {
			deposit("lan", CommonConst.languagePack);
			return false;
		}
		if (isBefore) {
			return isBefore;
		}
		entity.setState(CommonConst.STATENORMAL);
		DetachedCriteria dc = DetachedCriteria.forClass(AbcEnterprise.class)
				.add(Property.forName("userId").eqProperty("user.userId"))
				.setProjection(Property.forName("enterpriseId"));

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcUser.class, "user").add(Example.create(entity))
				.add(Subqueries.exists(dc));
		List<?> resultList = userService.findAllByCriteria(detachedCriteria);
		if (resultList.size() > 0) {
			entity = (AbcUser) resultList.get(0);
			deposit("user", entity);
			deposit("enterprise", entity.getEnterprise());
			deposit("lan", CommonConst.languagePack);
			return isBefore = true;
		}
		return false;
	}
}
