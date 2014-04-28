/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "CategoryAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-10           baowp                      initial
 */

package com.abbcc.module.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcSyscode;
import com.abbcc.models.AbcUser;
import com.abbcc.module.toolbox.Recycle;
import com.abbcc.service.CategoryService;
import com.abbcc.util.StringUtil;
import com.abbcc.util.checkKey.CheckKey;

@SuppressWarnings("serial")
public class CategoryAction extends BaseAction<AbcCategory> {

	private CategoryService categoryService;

	public int affectRows;

	public String syscode;
	public boolean hasBelongId;
	public boolean hasSyscode;

	public List<AbcCategory> resultList;

	public String[] category;
	
	public String clue;

	public CategoryAction() {
		// pageSize = 2;
	}

	@SuppressWarnings( { "unchecked" })
	public String show() {
		AbcUser user = getCurrentUser();

		String belongId = entity.getBelongId();
		hasBelongId = belongId != null && belongId.length() > 0;
		entity.setState(CommonConst.STATENORMAL);
		entity.setEnterpriseId(user.getEnterpriseId());
		entity.setType(CommonConst.CATEGORYTYPEPRODUCT);
		if (!hasBelongId) {
			entity.setBelongId(null);
			entity.setIsroot("01");
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(entity.getClass());
			detachedCriteria.add(Example.create(entity)).addOrder(
					Order.asc("sort"));
			resultList = categoryService.findAllByCriteria(detachedCriteria);
			pageList = categoryService.findPageByCriteria(detachedCriteria,
					pageSize, startIndex);

			String hql = "from AbcSyscode where type=? and state=?";
			List<AbcSyscode> syscodeList = (List<AbcSyscode>) categoryService
					.find(hql, "20", CommonConst.STATENORMAL);
			getRequest().setAttribute("syscodeList", syscodeList);
		} else {
			String hql = "select count(*) from AbcCategory where categoryId=?";
			List<Long> countList = (List<Long>) categoryService.find(hql,
					belongId);
			hasSyscode = countList.get(0).intValue() == 0;

			// 查询前如果有分类描述约束则先清空
			String cdesc = entity.getCdesc();
			if (cdesc != null) {
				entity.setCdesc(null);
			}

			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(entity.getClass());
			detachedCriteria.add(Example.create(entity)).addOrder(
					Order.asc("sort"));
			resultList = categoryService.findAllByCriteria(detachedCriteria);
			pageList = categoryService.findPageByCriteria(detachedCriteria,
					pageSize, startIndex);
			if (hasSyscode) {
				if (cdesc == null) {
					hql = "select distinct cdesc from AbcCategory where enterpriseId=? and belongId=?";
					List<String> cdescList = (List<String>) categoryService
							.find(hql, user.getEnterpriseId(), belongId);
					if (cdescList.size() > 0)
						cdesc = cdescList.get(0);
					else {
						cdesc = cdesc("", belongId);
					}
				}
				entity.setCdesc(cdesc);
				syscode = belongId;
			} else
				categoryService.load(entity, belongId);

			/* 构建显示路径 */
			if (entity.getCdesc() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				setLocationMap(map, entity);

				// 调整分类路径的显示次序
				Map<String, String> map2 = new LinkedHashMap<String, String>();
				Map.Entry<String, String>[] entrys = map.entrySet().toArray(
						new Map.Entry[0]);
				for (int i = entrys.length - 1; i >= 0; i--) {
					map2.put(entrys[i].getKey(), entrys[i].getValue());
				}

				getRequest().setAttribute("position", map2);
			}
		}
		setupLeaf((AbcCategory[])pageList.getItems().toArray(new AbcCategory[0]));

		// 构造结果集在同级的排序
		int i = 1;
		Map<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
		for (AbcCategory cate : resultList)
			sortMap.put(cate.getCategoryId(), i++);
		getRequest().setAttribute("sortMap", sortMap);

		return "show";
	}
	
	private String cdesc(String cdesc, String syscode) {
		AbcSyscode sys = (AbcSyscode) baseService.findById(AbcSyscode.class,
				syscode);
		cdesc = sys.getName() + " > " + cdesc;
		if (sys.getFatherdict() != null) {
			return cdesc(cdesc, sys.getFatherdict());
		} else {
			return cdesc;
		}
	}

	// 设置显示分类路径的map
	private void setLocationMap(Map<String, String> map, AbcCategory entity) {
		if (entity == null || entity.getGrade() == null
				|| Integer.parseInt(entity.getGrade().trim()) == 1) {
			String[] path = entity.getCdesc().split(">");
			String headPath = "";
			for (int i = 0; i < path.length - 2; i++)
				headPath += path[i];
			getRequest().setAttribute("headPath", headPath + ">");
			map.put(entity.getBelongId(), path[path.length - 2].trim());
			return;
		}

		entity = categoryService.findById(entity.getBelongId());
		map.put(entity.getCategoryId(), entity.getName());
		setLocationMap(map, entity);
	}

	public String save() {
		if (!CheckKey.checkKey(entity.getName())) {
			this.addFieldError("name", "存在非法字符！");
			return INPUT;
		}
		AbcUser user = getCurrentUser();
		entity.setName(entity.getName().trim());
		entity.setAdduserId(user.getUserId());
		entity.setEnterpriseId(user.getEnterpriseId());
		entity.setType(CommonConst.CATEGORYTYPEPRODUCT);
		entity.setAddTime(new Date());
		entity.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		entity.setState(CommonConst.STATENORMAL);
		entity.setSort(newSort());
		entity.setImage(entity.getImage());
		categoryService.save(entity);
		result=CommonConst.ADDSUCCESS;
		savaLog("产品分类",entity.getName(),CommonConst.LOGSAVE);
		return "save";
	}
	
	public void validateSave(){
		if(StringUtil.isBlank(entity.getName())){
			clue=StringUtil.encode(getFieldErrors().get("name").get(0));
		}
	}

	@Recycle
	public String remove() {
		entity.setState(CommonConst.STATEDEL);
		categoryService.update(entity);
		savaLog("产品分类",entity.getName(),CommonConst.LOGDEL);
		result=CommonConst.DELSUCCESS;
		return REMOVE;
	}

	public String display() {
		affectRows = categoryService.updateColumn(entity.getCategoryId(),
				"isdisplay", entity.getIsdisplay());
		return "json";
	}

	public String change() {
		String category1Id = getRequest().getParameter("category1Id");
		String category2Id = getRequest().getParameter("category2Id");
		AbcCategory category1 = categoryService.findById(category1Id);
		AbcCategory category2 = categoryService.findById(category2Id);
		categoryService.changeSort(category1, category2);
		return "change";
	}

	public String modify() {
		categoryService.update(entity);
		savaLog("产品分类",entity.getName(),CommonConst.LOGUPDATE);
		return "json";
	}

	// 查找下属分类条数
	@SuppressWarnings("unchecked")
	public String pertain() {
		String hql = "select count(categoryId) from AbcCategory where belongId='"
				+ entity.getBelongId()
				+ "' and state<>'"
				+ CommonConst.STATEDEL + "'";
		List<Long> list = (List<Long>) categoryService.find(hql);
		affectRows = list.get(0).intValue();
		return "json";
	}

	public String alter() {
		TreeMap<String, Map<String, Object>> map = new TreeMap<String, Map<String, Object>>();
		int count = alter(map, entity.getCategoryId(), 1);
		log.info("circle:" + count);

		getRequest().setAttribute("infoMap", map);
		String hql = "select count(distinct grade) from AbcCategory where enterpriseId=? and type=? and state=?";
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) categoryService.find(hql, entity
				.getEnterpriseId(), entity.getType(), entity.getState());
		int series = list.get(0).intValue();
		if (series > map.size()) {
			// alter(map);
			int size = map.size();
			for (int i = 0; i < series - size; i++) {
				int lastKey = Integer.parseInt(map.lastKey());
				String nextKey = lastKey < 9 ? "0" + (lastKey + 1) : String
						.valueOf((lastKey + 1));
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("list", new ArrayList<AbcCategory>());
				map.put(nextKey, map2);

			}
		}

		return "alter";
	}

	private int alter(Map<String, Map<String, Object>> map, String id, int count) {
		AbcCategory ac = categoryService.findById(id);
		List<AbcCategory> list;
		if ("01".equals(ac.getGrade()))
			list = getRootList();
		else
			list = getListByBelongId(ac.getBelongId());

		Map<String, Object> innerMap = new HashMap<String, Object>();
		innerMap.put("selectedId", ac.getCategoryId());
		innerMap.put("list", list);

		map.put(ac.getGrade(), innerMap);
		if ("01".equals(ac.getIsroot())) {
			return count;
		} else {
			return alter(map, ac.getBelongId(), ++count);
		}
	}

	@SuppressWarnings("unused")
	private void alter(Map<String, Map<String, Object>> map) {
		List<AbcCategory> list = getListByBelongId(id);
		if (list.size() > 0) {
			Map<String, Object> innerMap = new HashMap<String, Object>();
			innerMap.put("list", list);
			map.put(list.get(0).getGrade(), innerMap);
		}
	}

	public String fetchSubList() {
		resultList = getListByBelongId(entity.getBelongId());
		return "json";
	}
	
	private List<AbcCategory> getRootList() {
		AbcCategory example = new AbcCategory();
		AbcUser user = getCurrentUser();

		example.setEnterpriseId(user.getEnterpriseId());
		example.setType(CommonConst.CATEGORYTYPEPRODUCT);
		example.setState(CommonConst.STATENORMAL);
		example.setGrade("01");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.addOrder(Order.asc("sort"));
		detachedCriteria.add(Example.create(example));

		@SuppressWarnings("unchecked")
		List<AbcCategory> list = categoryService
				.findAllByCriteria(detachedCriteria);
		return list;
	}

	private List<AbcCategory> getListByBelongId(String belongId) {
		AbcCategory example = new AbcCategory();
		AbcUser user = getCurrentUser();

		example.setEnterpriseId(user.getEnterpriseId());
		example.setType(CommonConst.CATEGORYTYPEPRODUCT);
		example.setState(CommonConst.STATENORMAL);
		example.setBelongId(belongId);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity
				.getClass());
		detachedCriteria.addOrder(Order.asc("sort"));
		detachedCriteria.add(Example.create(example));

		@SuppressWarnings("unchecked")
		List<AbcCategory> list = categoryService
				.findAllByCriteria(detachedCriteria);
		return list;
	}

	public String altering() {
		String oldBelongId = entity.getBelongId();
		if (category == null) {
			return altering(getRequest().getParameter("topId"), oldBelongId);
		}
		String belongId = category[category.length - 1];
		if (belongId == null || belongId.equals(entity.getBelongId())
				|| belongId.equals(entity.getCategoryId()))
			return "change";
		AbcCategory parent = categoryService.findById(belongId);

		String grade = parent.getGrade();
		int gr = Integer.parseInt(grade) + 1;
		if (gr < 10)
			grade = "0" + gr;
		else
			grade = String.valueOf(gr);

		entity.setBelongId(belongId);
		entity.setGrade(grade);
		entity.setIsroot("00");
		entity.setSort(newSort());
		categoryService.update(entity);
		entity.setBelongId(oldBelongId);

		int records = altering(entity.getCategoryId(), entity.getGrade(), 0);
		log.info("update:" + records + " records");
		return "change";
	}

	// 移动到系统分类
	private String altering(String belongId, String oldBelongId) {
		entity.setBelongId(belongId);
		entity.setGrade("01");
		entity.setIsroot("01");
		entity.setSort(newSort());
		categoryService.update(entity);
		entity.setBelongId(oldBelongId);

		int records = altering(entity.getCategoryId(), entity.getGrade(), 0);
		log.info("update:" + records + " records");
		entity.setBelongId(null);
		return "change";
	}

	// 递归修改下属记录grade
	private int altering(String belongId, String grade, int records) {
		AbcUser user = getCurrentUser();
		String hql = "update AbcCategory set grade=? where type=? and adduserId=? and belongId=?";
		int gr = Integer.parseInt(grade) + 1;
		if (gr < 10)
			grade = "0" + gr;
		else
			grade = String.valueOf(gr);

		records += categoryService.bulkUpdate(hql, grade,
				CommonConst.CATEGORYTYPEPRODUCT, user.getUserId(), belongId);
		hql = "from AbcCategory where type=? and enterpriseId=? and belongId=?";
		@SuppressWarnings("unchecked")
		List<AbcCategory> list = (List<AbcCategory>) categoryService.find(hql,
				CommonConst.CATEGORYTYPEPRODUCT, user.getEnterpriseId(), belongId);
		for (AbcCategory ac : list) {
			records = altering(ac.getCategoryId(), ac.getGrade(), records);
		}
		return records;
	}

	@SuppressWarnings("unchecked")
	public String categoryInnerList() {
		AbcCategory ac = new AbcCategory();
		ac.setEnterpriseId(getCurrentUser().getEnterpriseId());
		ac.setType(CommonConst.CATEGORYTYPEPRODUCT);
		ac.setState(CommonConst.STATENORMAL);
		ac.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCategory.class);
		detachedCriteria.add(Example.create(ac));
		resultList = categoryService.findAllByCriteria(detachedCriteria);
		return "innerList";
	}

	public void validateCategoryInnerList() {
		this.clearFieldErrors();
	}
	
	public void expandCategory() {
		AbcCategory ac = new AbcCategory();
		ac.setEnterpriseId(getCurrentUser().getEnterpriseId());
		ac.setType(CommonConst.CATEGORYTYPEPRODUCT);
		ac.setState(CommonConst.STATENORMAL);
		ac.setIsroot(CommonConst.CATEGORYISROOT);
		ac.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(AbcCategory.class);
		detachedCriteria.add(Example.create(ac)).addOrder(Property.forName("sort").asc());
		@SuppressWarnings("unchecked")
		List<AbcCategory> rootList = categoryService.findAllByCriteria(detachedCriteria);
		List<AbcCategory> list=new ArrayList<AbcCategory>();
		expandCategory(list, rootList);
		getRequest().setAttribute("expandCategory", list);
	}
	
	private void expandCategory(List<AbcCategory> list,List<AbcCategory> parentList) {
		for (AbcCategory cate : parentList) {
			list.add(cate);
			AbcCategory example = new AbcCategory();
			example.setBelongId(cate.getCategoryId());
			example.setEnterpriseId(getCurrentUser().getEnterpriseId());
			example.setState(CommonConst.STATENORMAL);
			example.setIsdisplay(CommonConst.CATEGORYISDISPLAY);
			DetachedCriteria dc = DetachedCriteria.forClass(AbcCategory.class)
					.add(Example.create(example)).addOrder(Order.asc("sort"));
			@SuppressWarnings("unchecked")
			List<AbcCategory> curList=categoryService.findAllByCriteria(dc);
			expandCategory(list, curList);
		}
	}

	private Integer newSort() {
		AbcUser user = getCurrentUser();
		String hql = "select max(sort) from AbcCategory where type=? and enterpriseId=?";
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) categoryService.find(hql,
				CommonConst.CATEGORYTYPEPRODUCT, user.getEnterpriseId());
		Integer sort = list.get(0);
		if (sort == null)
			sort = 1;
		else
			sort++;
		return sort;
	}

	private void setupLeaf(AbcCategory... categories) {
		for (AbcCategory cate : categories) {
			String hql = "select count(*) from AbcCategory where belongId=? and state=?";
			@SuppressWarnings("unchecked")
			List<Long> list = (List<Long>) categoryService.find(hql, cate
					.getCategoryId(),CommonConst.STATENORMAL);
			int count = list.get(0).intValue();
			cate.setIfLeaf(count == 0);
		}
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public int getAffectRows() {
		return affectRows;
	}

	public List<AbcCategory> getResultList() {
		return resultList;
	}
}
