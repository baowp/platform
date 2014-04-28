package com.abbcc.springrest.controller.user.site.piece;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.models.AbcUser;
import com.abbcc.models.GroupLaymod;
import com.abbcc.models.GroupLayout;
import com.abbcc.models.GroupModule;
import com.abbcc.models.GroupUserdefined;
import com.abbcc.util.constant.group.GroupPiece;
import com.abbcc.util.constant.group.GroupPosition;

@SuppressWarnings("serial")
public class UserDefinedPiece<T> extends DetailContentPiece<AbcUser> {

	protected List<GroupLaymod> laymodlist;
	protected List<GroupLaymod> headList = new ArrayList<GroupLaymod>();
	protected List<GroupLaymod> content = new ArrayList<GroupLaymod>();
	protected List<GroupLaymod> content1 = new ArrayList<GroupLaymod>();
	protected List<GroupLaymod> content2 = new ArrayList<GroupLaymod>();
	protected List<GroupLaymod> content3 = new ArrayList<GroupLaymod>();
	protected List<GroupLaymod> belowList = new ArrayList<GroupLaymod>();

	/** 用户自定义module 模版 **/
	private GroupModule userDefinedModule;

	/**
	 * 自定义模块
	 */
	@RequestMapping(value = "/{username}/user_defined{order}")
	public String pieceUserDefined(String defined, @PathVariable String order) {
		if (defined == null) {
			defined = "user_defined".concat(order);
		}
		if (before()) {
			GroupUserdefined example = new GroupUserdefined();
			example.setModuleId(defined);
			example.setEnterpriseId(entity.getEnterpriseId());
			List<GroupUserdefined> list = userDefinedService
					.findByExample(example);
			if (!list.isEmpty()) {
				example = list.get(0);
			}
			deposit("sign", defined);
			deposit(defined, example);
		}
		return viewName("group/dynamic/piece/user/user_defined");
	}

	/**
	 * 获取用户自定义模块列表
	 * 
	 * @param layout
	 *            布局
	 * @param belongPage
	 *            传入页面
	 */
	protected void userDefinedList(GroupLayout layout, String belongPage) {
		DetachedCriteria dc = DetachedCriteria.forClass(GroupLaymod.class);
		dc.add(Property.forName("layout.layoutId").eq(layout.getLayoutId()));
		dc.add(Property.forName("page").eq(belongPage)); // 传入页面
		dc.add(Property.forName("module.moduleId").like("user_defined",
				MatchMode.START));
		dc.addOrder(Order.asc("position"));
		dc.addOrder(Order.asc("pieceOrder"));
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("module.moduleId"))
				.add(Property.forName("position"))
				.add(Property.forName("pieceOrder")));
		@SuppressWarnings("unchecked")
		List<Object[]> list = baseService.findAllByCriteria(dc);
		for (Object[] o : list) {
			GroupModule mod = userDefinedModule().clone(); // 克隆
			GroupLaymod lay = new GroupLaymod();
			mod.setModuleId((String) o[0]); // 改变moduleId
			lay.setModule(mod);
			lay.setPosition((GroupPosition) o[1]);
			lay.setPieceOrder((Integer) o[2]);
			GroupPosition pos = lay.getPosition();
			switch (pos) {
			case headList:
				headList.add(lay.getPieceOrder() - 1, lay);   // 顺序减一
				prefixPiece(lay);
				break;
			case content1:
				content1.add(lay.getPieceOrder() - 1, lay);
				break;
			case content2:
				content2.add(lay.getPieceOrder() - 1, lay);
				break;
			case content3:
				content3.add(lay.getPieceOrder() - 1, lay);
				break;
			case belowList:
				belowList.add(lay.getPieceOrder() - 1, lay);
				prefixPiece(lay);
				break;
			default:
				break;
			}
		}
	}

	protected void prefixPiece(GroupLaymod gl) {
		StringBuilder sb = new StringBuilder("piece/");
		sb.append(gl.getModule().getMsign());
		gl.getModule().setMsign(sb.toString());
	}

	/**
	 * 自定义模块Module
	 * 
	 * @return
	 */
	private GroupModule userDefinedModule() {
		if (userDefinedModule == null) {
			userDefinedModule = moduleService.get(GroupPiece.user_defined
					.name());
		}
		return userDefinedModule;
	}

	/**
	 * 返回用户自定义模块的 moduleMap
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, String> userDefinedMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		DetachedCriteria dc = DetachedCriteria.forClass(GroupUserdefined.class);
		dc.add(Property.forName("enterpriseId").eq(entity.getEnterpriseId()));
		dc.setProjection(Projections.projectionList()
				.add(Property.forName("moduleId"))
				.add(Property.forName("name")));
		dc.addOrder(Property.forName("serial").asc());
		List<Object[]> list = baseService.findAllByCriteria(dc);
		for (Object[] o : list) {
			map.put((String) o[0], (String) o[1]);
		}
		return map;
	}
}
