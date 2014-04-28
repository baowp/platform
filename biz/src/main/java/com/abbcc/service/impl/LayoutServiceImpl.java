/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "DomainbindServiceImpl.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-9           Wangjin                      initial
 */

package com.abbcc.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.common.ConfConst;
import com.abbcc.common.PaginationSupport;
import com.abbcc.dao.AttachmentDAO;
import com.abbcc.dao.CategoryDAO;
import com.abbcc.dao.LayoutDAO;
import com.abbcc.dao.LaythemeDAO;
import com.abbcc.dao.ProductDAO;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcLayout;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.models.AbcLaytheme;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.service.LayoutService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.ModelType;
import com.abbcc.util.constant.layout.BodyType;
import com.abbcc.util.constant.layout.LaythemeState;
import com.abbcc.util.constant.layout.PieceType;
import com.abbcc.util.constant.layout.Position;

public class LayoutServiceImpl extends BaseServiceImpl implements LayoutService {

	private LayoutDAO layoutDAO;
	@Autowired
	private LaythemeDAO laythemeDAO;

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	/**  **/
	private Map<String, String> categoryneIdMap;

	public void setLayoutDAO(LayoutDAO layoutDAO) {
		this.layoutDAO = layoutDAO;
		setBaseDAO(layoutDAO);
	}

	public void save(AbcLayout transientInstance) {
		layoutDAO.save(transientInstance);
	}

	public void delete(AbcLayout persistentInstance) {

	}

	public AbcLayout findById(String id) {
		return layoutDAO.findById(id);
	}

	public List<AbcLayout> findByExample(AbcLayout instance) {
		return layoutDAO.findByExample(instance);

	}

	public List<AbcLayout> findAll() {
		return layoutDAO.findAll();

	}

	public void saveOrUpdate(AbcLayout instance) {
		layoutDAO.saveOrUpdate(instance);

	}

	public AbcLayout merge(AbcLayout entity) {
		return layoutDAO.merge(entity);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria) {
		return layoutDAO.findPageByCriteria(detachedCriteria);
	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int startIndex) {
		return layoutDAO.findPageByCriteria(detachedCriteria, startIndex);

	}

	public PaginationSupport findPageByCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int startIndex) {
		return layoutDAO.findPageByCriteria(detachedCriteria, pageSize,
				startIndex);
	}

	public List findAllByCriteria(DetachedCriteria detachedCriteria) {
		return layoutDAO.findAllByCriteria(detachedCriteria);

	}

	public int getCountByCriteria(DetachedCriteria detachedCriteria) {
		return layoutDAO.getCountByCriteria(detachedCriteria);

	}

	public void callProcedure(String procString, List<Object> params)
			throws Exception {
		layoutDAO.callProcedure(procString, params);
	}

	public List getCallProcedureResult(String procString, List<Object> params)
			throws Exception {
		return layoutDAO.getCallProcedureResult(procString, params);
	}

	@Override
	public void cascadeSave(AbcLayout entity) {
		String hql = "delete from AbcLayoutmodule where layout.layoutId=? and (block=? or module.moduleId in (select moduleId from AbcModule where type in(?,?,?)))";
		if (entity.getNavmoduleList() != null) {
			bulkUpdate(hql, entity.getLayoutId(), entity.getBelongPage(),
					PieceType.HD, PieceType.BS, PieceType.NV);
		} else {
			hql = "delete from AbcLayoutmodule where layout.layoutId=? and (block=? and position<>? or module.moduleId in (select moduleId from AbcModule where type in(?,?)))";
			bulkUpdate(hql, entity.getLayoutId(), entity.getBelongPage(),
					Position.list_nav, PieceType.HD, PieceType.BS);
		}
		saveOrUpdate(entity);

		{// 风格维护
			hql = "delete from AbcLaytheme where layoutId=? and state=?";
			bulkUpdate(hql, entity.getLayoutId(), entity.getLaytheme()
					.getState());
			entity.getLaytheme().setLayoutId(entity.getLayoutId());
			entity.getLaytheme().setAddTime(new Date());
			save(entity.getLaytheme());

			if (entity.getLaytheme().getState() == LaythemeState.C) {// 如果应用，那么最后编辑也重新保存
				bulkUpdate(hql, entity.getLayoutId(), LaythemeState.B);
				AbcLaytheme laytheme = ObjectUtil.extendz(entity.getLaytheme());
				laytheme.setState(LaythemeState.B);
				save(laytheme);
			}
		}

		{// 清理不再使用的背景图片
			DetachedCriteria dc = DetachedCriteria.forClass(AbcLaytheme.class)
					.add(Property.forName("layoutId").eq(entity.getLayoutId()))
					.setProjection(Projections.property("style"));
			List<String> list = findAllByCriteria(dc);

			Set<String> set = new HashSet<String>();
			for (Object obj : entity.getJsonSign().values()) {
				if (obj instanceof JSONObject) {
					JSONObject json = (JSONObject) obj;
					if (json.get("src") != null)
						set.add(json.getString("src").replaceFirst(
								ConfConst.FILE_SVR, ""));
				}
			}
			for (String style : list) {
				if (style != null) {
					Pattern p = Pattern.compile("url\\(http[^)]+\\)");
					Matcher m = p.matcher(style);
					while (m.find()) {
						set.add(m.group().replaceAll(
								"url\\(" + ConfConst.FILE_SVR + "([^)]+)\\)",
								"$1"));
					}
				}
			}

			AbcAttachment attach = new AbcAttachment();
			attach.setBelongType(ModelType.AE);
			attach.setBelongId(entity.getLayoutId());
			List<AbcAttachment> attachList = findByExample(attach);

			Set<AbcAttachment> attachSet = new HashSet<AbcAttachment>();
			for (AbcAttachment am : attachList) {
				if (set.contains(am.getServerPath())) {

				} else {
					attachSet.add(am);
				}
			}
			if (attachSet.size() > 0) {
				deleteAll(attachSet);
				for (final AbcAttachment am : attachSet) {
					File folder = new File(
							(ConfConst.FILE_UPLOAD_DIR + am.getServerPath()))
							.getParentFile();
					if (folder.exists()) {
						for (File img : folder.listFiles(new FilenameFilter() {

							@Override
							public boolean accept(File dir, String name) {
								if (name.startsWith(am.getFilename()
										.replaceAll("(.+)\\.\\w+$", "$1")))
									return true;
								return false;
							}
						}))
							img.delete();
					}
				}
			}
		}
	}

	/**
	 * 删除布局
	 * 
	 * @param enterpriseId
	 */
	private void delLayout(String enterpriseId) {
		String layoutId = null;
		AbcLayout layout = null;
		AbcLayout example = new AbcLayout();
		example.setEnterpriseId(enterpriseId);
		example.setState(CommonConst.STATENORMAL);
		List<AbcLayout> list = layoutDAO.findByExample(example);
		if (!list.isEmpty()) {
			layout = list.get(0);
		}
		if (layout != null) {
			layoutId = layout.getLayoutId();
			laythemeDAO.bulkUpdate("DELETE FROM AbcLaytheme WHERE layoutId=?",
					layoutId);
			layoutDAO.delete(layout);
		}
	}

	/**
	 * 恢复布局
	 */
	@Override
	public void revertLayout(AbcUser user) {
		delLayout(user.getEnterpriseId());
		this.initLayout(user);
	}

	private void initLayout(AbcUser user) {
		AbcLayout layout = new AbcLayout();
		layout.setEnterpriseId(user.getEnterpriseId());
		layout.setUserId(user.getUserId());
		layout.setUpdateTime(new Date());
		layout.setState(CommonConst.STATENORMAL);
		layout.setBodyType(BodyType.SL);

		List<AbcLayoutmodule> list = new ArrayList<AbcLayoutmodule>();
		String path = ServletActionContext.getServletContext().getRealPath(
				"WEB-INF/classes/deploy/layoutmodules");
		String initStr = StringUtil.readFromFile(path);
		JSONArray jsonArray = JSONArray.fromObject(initStr);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		jsonConfig.setRootClass(AbcLayoutmodule.class);
		AbcLayoutmodule[] lays = (AbcLayoutmodule[]) JSONSerializer.toJava(
				jsonArray, jsonConfig);
		for (AbcLayoutmodule a : lays) {
			a.setLayout(layout);
			list.add(a);
		}
		layout.setLayoutmoduleList(list);
		layoutDAO.save(layout);
	}

	@Override
	public void initialize(AbcUser user) {
		AbcLayout example = new AbcLayout();
		example.setEnterpriseId(user.getEnterpriseId());
		List<AbcLayout> list = layoutDAO.findByExample(example);
		if (list.isEmpty()) {
			initLayout(user);
		}
	}

	/**
	 * 覆盖布局
	 */
	@Override
	public void layover(AbcLayout layout, List<AbcLaytheme> themeList) {
		delLayout(layout.getEnterpriseId());
		layoutDAO.save(layout);
		for (AbcLaytheme theme : themeList) {
			theme.setLayoutId(layout.getLayoutId());
		}
		laythemeDAO.saveOrUpdateAll(themeList);
	}

	/**
	 * 覆盖分类与产品信息
	 * 
	 * @param categoryList
	 */
	@Override
	public void layoverCategoryAndProduct(List<AbcCategory> categoryList,
			List<AbcProduct> productList) {
		this.delCategoryAndProduct(categoryList.get(0).getEnterpriseId());
		categoryneIdMap = new HashMap<String, String>();
		for (AbcCategory cate : categoryList) {
			String oldId = cate.getCategoryId();
			cate.setCategoryId(null);
			categoryDAO.save(cate);
			String categoryId = cate.getCategoryId(); // 获得生成的标识
			categoryneIdMap.put(oldId, categoryId); // 放入键值对
		}
		for (AbcCategory cate : categoryList) {
			String belongId = cate.getBelongId();
			String newBelongId = categoryneIdMap.get(belongId);
			if (newBelongId != null) {
				cate.setBelongId(newBelongId);
				categoryDAO.update(cate);
			}
		}
		List<String> proudctIdList = new ArrayList<String>();
		for (AbcProduct p : productList) {
			String old = p.getCategory();
			proudctIdList.add(p.getProductId());
			p.setProductId(null);
			p.setCategory(categoryneIdMap.get(old)); // 设置新的类别ID
		}
		productDAO.saveOrUpdateAll(productList);
		this.delAttachRecord(proudctIdList);
	}

	private void delCategoryAndProduct(String enterpriseId) {
		productDAO.bulkUpdate("DELETE FROM AbcProduct WHERE enterpriseId=?",
				enterpriseId);
		categoryDAO.bulkUpdate("DELETE FROM AbcCategory WHERE enterpriseId=?",
				enterpriseId);
	}

	private void delAttachRecord(List<String> belongIdList) {
		// 用productDAO,删除attachement记录
		StringBuffer sb = new StringBuffer();
		for (String str : belongIdList) {
			sb.append("?").append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		productDAO.bulkUpdate("DELETE FROM AbcAttachment WHERE belongId IN ("
				+ sb.toString() + ")", belongIdList.toArray());
	}
}
