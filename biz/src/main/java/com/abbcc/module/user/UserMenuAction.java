package com.abbcc.module.user;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCustom;
import com.abbcc.models.AbcMenu;
import com.abbcc.models.AbcUser;
import com.abbcc.service.CustomService;
import com.abbcc.util.FileUtil;
import com.abbcc.util.StringUtil;

public class UserMenuAction extends BaseAction<AbcCustom> {
	public String pageType;
	private CustomService customService;
	public int stepType;
	public String sourceCate;
	public int sourceSort;
	public String targetCate;
	public int targetSort;
	public String mname;
	public String action;
	public String moduleName;
	public String menuIds;
	public String unCheckId;

	public void setCustomService(CustomService customService) {
		this.customService = customService;
	}

	private List MenuList() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCustom.class);
		detachedCriteria.add(Restrictions.and(Restrictions.eq("enterpriseId",
				this.getCurrentUser().getEnterpriseId()), Restrictions.eq(
				"display", CommonConst.DISPLAY)));
		detachedCriteria.addOrder(Order.asc("sort"));
		List<AbcCustom> customList = customService
				.findAllByCriteria(detachedCriteria);
		return customList;
	}

	public String allMenuList() throws IOException {
		/*JSONArray json1 = JSONArray.fromObject(FileUtil.readToString(new File("c:\\2.txt")));
		AbcMenu[] menuArray =  (AbcMenu[]) JSONArray.toArray(json1, AbcMenu.class);
		for(AbcMenu am:menuArray){
			baseService.save(am);
		}*/
		DetachedCriteria dc = DetachedCriteria.forClass(AbcMenu.class);
		dc.addOrder(Order.desc("moduleName"));
		deposit("menuList", baseService.findAllByCriteria(dc));
		return "allMenuList";
	}

	public String show() {

		List<AbcCustom> customList = MenuList();
		getRequest().setAttribute("customList", customList);
		return "show";
	}

	public String list() {

		List<AbcCustom> customList = MenuList();
		JSONArray jsonArray = JSONArray.fromObject(customList);
		this.result = jsonArray.toString();
		return "json1";
	}

	public String edit() {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCustom.class);
		detachedCriteria.add(Restrictions.eq("enterpriseId", this
				.getCurrentUser().getEnterpriseId()));
		detachedCriteria.addOrder(Order.asc("sort"));
		if (StringUtil.isNotBlank(pageType)) {
			detachedCriteria.add(Restrictions.eq("display", "01"));
		}
		this.pageList = customService.findPageByCriteria(detachedCriteria,
				pageSize, startIndex);
		resultList = customService.findAllByCriteria(detachedCriteria);
		deposit("resultList", resultList);
		int i = 1;
		Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		for (AbcCustom cate : resultList)
			sortMap.put(cate.getCustomId() + "," + cate.getSort(), i++);
		getRequest().setAttribute("sortMap", sortMap);
		return "showEdit";
	}

	public String display() {
		customService.update(entity);
		List<AbcCustom> customList = MenuList();
		getSession().setAttribute("customList", customList);
		return JSON;
	}

	public String setName() {
		customService.update(entity);
		List<AbcCustom> customList = MenuList();
		getSession().setAttribute("customList", customList);
		return JSON;
	}

	public String homeEdit() {

		String[] mIds = menuIds.split(",");
		String[] unCheckIds = unCheckId.split(",");
		if (StringUtil.isNotBlank(menuIds)) {
			for (String mId : mIds) {
				AbcMenu am = (AbcMenu) baseService.findById(AbcMenu.class, mId);
				AbcCustom ac = new AbcCustom();
				ac.setMenuId(mId);
				ac.setEnterpriseId(getCurrentUser().getEnterpriseId());
				List<AbcCustom> list = baseService.findByExample(ac);
				if (list.size() == 0) {
					ac.setName(am.getMname());
					ac.setDisplay("01");
					ac.setState("01");
					ac.setSort(newSort());
					ac.setAction(am.getAction().replace("domain",domain).replace("username",getCurrentUser().getUsername()));
					ac.setModuleName(am.getModuleName());
					baseService.save(ac);
				}
			}
		}
		if (StringUtil.isNotBlank(unCheckId)) {
			for (String uId : unCheckIds) {
				AbcCustom ac = new AbcCustom();
				ac.setMenuId(uId);
				ac.setEnterpriseId(getCurrentUser().getEnterpriseId());
				List<AbcCustom> list = baseService.findByExample(ac);
				if (list.size() != 0) {
					baseService.delete(list.get(0));
				}
			}
		}
		return JSON;
	}

	/**
	 * 调序
	 */
	public String change() {
		changeSort(sourceCate, sourceSort, targetCate, targetSort);
		List<AbcCustom> customList = MenuList();
		getSession().setAttribute("customList", customList);
		return "returnshow";
	}

	private int[] changeSort(String sourceCate, int sourceSort,
			String targetCate, int targetSort) {
		if (sourceCate == null || sourceSort == 0 || targetCate == null
				|| targetSort == 0)
			return new int[0];
		String[] sql = new String[2];
		sql[0] = "update abc_custom set sort=" + targetSort
				+ " where custom_id='" + sourceCate + "'";
		sql[1] = "update abc_custom set sort=" + sourceSort
				+ " where custom_id='" + targetCate + "'";
		return customService.batchUpdate(sql);
	}

	@SuppressWarnings("unchecked")
	public String step() {
		AbcUser user = getCurrentUser();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.add(Restrictions.eq("enterpriseId",
				user.getEnterpriseId()));

		if (stepType == 1) {
			detachedCriteria.addOrder(Order.asc("sort"));
			detachedCriteria.add(Restrictions.gt("sort", sourceSort));
		} else {
			detachedCriteria.addOrder(Order.desc("sort"));
			detachedCriteria.add(Restrictions.lt("sort", sourceSort));
		}
		pageList = customService.findPageByCriteria(detachedCriteria, 1, 0);
		for (AbcCustom ac : (List<AbcCustom>) pageList.getItems()) {
			changeSort(sourceCate, sourceSort, ac.getCustomId(), ac.getSort());
		}
		List<AbcCustom> customList = MenuList();
		getSession().setAttribute("customList", customList);
		return "returnshow";
	}

	private Integer newSort() {
		String hql = "select max(sort) from AbcCustom";

		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) baseService.find(hql);
		Integer sort = null;
		if (list.size() > 0)
			sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}

}
