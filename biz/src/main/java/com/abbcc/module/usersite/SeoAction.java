package com.abbcc.module.usersite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcLayout;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.models.AbcModule;
import com.abbcc.models.AbcSeo;
import com.abbcc.module.album.WatermarkAction;
import com.abbcc.service.LayoutmoduleService;
import com.abbcc.service.ModuleService;
import com.abbcc.service.SeoService;
import com.abbcc.util.constant.layout.BelongPage;
import com.abbcc.util.constant.layout.Piece;
import com.abbcc.util.constant.layout.PieceType;

@SuppressWarnings("serial")
public class SeoAction extends BaseAction<AbcSeo> {
	@Autowired
	private SeoService seoService;
	@Autowired
	private LayoutmoduleService layoutmoduleService;
	@Autowired
	private ModuleService moduleService;

	AbcEnterprise ent;

	public String edit() {
		ent = getCurrentEnt();
		DetachedCriteria dc = DetachedCriteria.forClass(AbcLayoutmodule.class);
		DetachedCriteria subdc = DetachedCriteria
				.forClass(AbcLayout.class)
				.add(Property.forName("enterpriseId").eq(ent.getEnterpriseId()))
				.add(Property.forName("state").eq(CommonConst.STATENORMAL))
				.setProjection(Property.forName("layoutId"));
		dc.add(Property.forName("layout.layoutId").eq(subdc))
				.add(Property.forName("module.moduleId").like("NAV",
						MatchMode.START))
				.addOrder(Property.forName("blockOrder").asc())
				.setProjection(Property.forName("module.moduleId"));
		List<?> strList = layoutmoduleService.findAllByCriteria(dc);
		List<Piece> pieceList = new ArrayList<Piece>();
		for (Object str : strList)
			pieceList.add(Piece.valueOf(str.toString()));
		if (pieceList.isEmpty())
			pieceList = Arrays.asList(Piece.defaultNavs());
		List<BelongPage> belongList = BelongPage.toPages(pieceList);

		dc = DetachedCriteria
				.forClass(entityClass)
				.add(Property.forName("enterpriseId").eq(ent.getEnterpriseId()))
				.add(Property.forName("belongPage").in(belongList));
		List<AbcSeo> list = seoService.findAllByCriteria(dc);
		resultList = new ArrayList<AbcSeo>();
		for (BelongPage page : belongList) {
			for (int i = 0; i < list.size(); i++) {
				AbcSeo seo = list.get(i);
				if (page == seo.getBelongPage()) {
					resultList.add(seo);
					break;
				}
				if (i == list.size() - 1) {
					persist(page);
				}
			}
			if (list.isEmpty()) {
				persist(page);
			}
		}

		dc = DetachedCriteria
				.forClass(AbcLayout.class)
				.add(Property.forName("enterpriseId").eq(ent.getEnterpriseId()))
				.add(Property.forName("state").eq(CommonConst.STATENORMAL))
				.setProjection(Property.forName("bodyContent"));
		List<?> bc = baseService.findAllByCriteria(dc);
		JSONObject json = new JSONObject();
		if (!bc.isEmpty() && !isBlank((String) bc.get(0))) {
			json = JSONObject.fromObject(bc.get(0)).getJSONObject("title");
		}
		dc = DetachedCriteria.forClass(AbcModule.class).add(
				Property.forName("type").eq(PieceType.NV));
		@SuppressWarnings("unchecked")
		List<AbcModule> moduleList = moduleService.findAllByCriteria(dc);
		Map<String, String> map = new HashMap<String, String>();
		for (AbcModule module : moduleList) {
			String name = module.getName();
			if (json.get(module.getModuleId()) != null)
				name = json.getString(module.getModuleId());
			map.put(module.getModuleId(), name);
		}

		deposit("pieceList", pieceList);
		deposit("map", map);
		deposit("url",new WatermarkAction().enterprisePath(getCurrentUser().getEnterpriseId()));
		return EDIT;
	}

	private void persist(BelongPage page) {
		AbcSeo seo = new AbcSeo(ent.getEnterpriseId(), page, ent.getName());
		seoService.save(seo);
		resultList.add(seo);
	}

	public String update() {
		seoService.update(entity);
		return NONE;
	}
}
