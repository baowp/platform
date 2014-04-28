package com.abbcc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abbcc.common.CommonConst;
import com.abbcc.dao.CategoryDAO;
import com.abbcc.dao.GroupLaymodDao;
import com.abbcc.dao.GroupLayoutDao;
import com.abbcc.dao.GroupLaythemeDao;
import com.abbcc.dao.GroupNavigatorDao;
import com.abbcc.dao.GroupUserdefinedDao;
import com.abbcc.dao.GroupWidthDao;
import com.abbcc.dao.ProductDAO;
import com.abbcc.models.AbcCategory;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcUser;
import com.abbcc.models.GroupLaymod;
import com.abbcc.models.GroupLayout;
import com.abbcc.models.GroupLaytheme;
import com.abbcc.models.GroupNavigator;
import com.abbcc.models.GroupUserdefined;
import com.abbcc.models.GroupWidth;
import com.abbcc.service.GroupLayoutService;
import com.abbcc.springrest.controller.user.site.LayoutController;
import com.abbcc.util.StringUtil;

@Service
public class GroupLayoutServiceImpl extends
		ServiceImpl<GroupLayout, GroupLayoutDao> implements GroupLayoutService {

	@Autowired
	private GroupLaythemeDao groupLaythemeDao;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private GroupNavigatorDao navigatorDao;
	@Autowired
	private GroupLaymodDao groupLaymodDao;
	@Autowired
	private GroupWidthDao groupWidthDao;
	@Autowired
	private GroupUserdefinedDao userDefineDao;

	/**  **/
	private Map<String, String> categoryneIdMap;

	/**
	 * 恢复布局
	 */
	@Override
	public void revertLayout(AbcUser user) {
		delLayout(user.getEnterpriseId());
		this.initLayout(user);
	}

	@Override
	public void initLay(AbcUser user) {
		GroupLayout example = new GroupLayout();
		example.setEnterpriseId(user.getEnterpriseId());
		List<GroupLayout> list = dao.findByExample(example);
		if (list.isEmpty()) {
			initLayout(user);
		}
	}

	private void initLayout(AbcUser user) {
		GroupLayout layout = new GroupLayout();
		layout.setEnterpriseId(user.getEnterpriseId());
		layout.setUserId(user.getUserId());
		layout.setCreateTime(new Date());
		layout.setState(CommonConst.STATENORMAL);

		List<GroupLaymod> list = new ArrayList<GroupLaymod>();
		// String path = ServletActionContext.getServletContext().getRealPath(
		// "WEB-INF/classes/deploy/grouplayoutmodules");
		String path = CommonConst.CLASSPATH + "deploy/grouplayoutmodules";
		String initStr = StringUtil.readFromFile(path);
		JSONArray jsonArray = JSONArray.fromObject(initStr);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		jsonConfig.setRootClass(GroupLaymod.class);
		GroupLaymod[] lays = (GroupLaymod[]) JSONSerializer.toJava(jsonArray,
				jsonConfig);
		for (GroupLaymod a : lays) {
			a.setLayout(layout);
			list.add(a);
		}
		groupLaymodDao.saveOrUpdateAll(list);
		dao.save(layout);
		initWidth(layout.getLayoutId());
	}

	/**
	 * 列宽
	 */
	private void initWidth(String layoutId) {
		List<GroupWidth> list = new ArrayList<GroupWidth>();
		String path = CommonConst.CLASSPATH + "deploy/groupwidth";
		String initStr = StringUtil.readFromFile(path);
		JSONArray jsonArray = JSONArray.fromObject(initStr);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		jsonConfig.setRootClass(GroupWidth.class);
		GroupWidth[] widths = (GroupWidth[]) JSONSerializer.toJava(jsonArray,
				jsonConfig);
		for (GroupWidth a : widths) {
			a.setLayoutId(layoutId);
			list.add(a);
		}
		groupWidthDao.saveOrUpdateAll(list);
	}

	@Override
	public void initNav(AbcUser user) {
		GroupNavigator example = new GroupNavigator();
		example.setEnterpriseId(user.getEnterpriseId());
		List<GroupNavigator> list = navigatorDao.findByExample(example);
		if (list.isEmpty()) {
			initNavigator(user);
		}
	}

	private void initNavigator(AbcUser user) {
		String enterpriseId = user.getEnterpriseId();
		List<GroupNavigator> list = new ArrayList<GroupNavigator>();
		// String path = ServletActionContext.getServletContext().getRealPath(
		// "WEB-INF/classes/deploy/groupnavigator");
		String path = CommonConst.CLASSPATH + "deploy/groupnavigator";
		String initStr = StringUtil.readFromFile(path);
		JSONArray jsonArray = JSONArray.fromObject(initStr);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
		jsonConfig.setRootClass(GroupNavigator.class);
		GroupNavigator[] navs = (GroupNavigator[]) JSONSerializer.toJava(
				jsonArray, jsonConfig);
		for (GroupNavigator n : navs) {
			n.setEnterpriseId(enterpriseId); // 设置企业标识
			list.add(n);
		}
		navigatorDao.saveOrUpdateAll(list);
	}

	/**
	 * 覆盖布局
	 */
	@Override
	public void layover(GroupLayout layout, List<GroupLaytheme> themeList) {
		this.delLayout(layout.getEnterpriseId());
		dao.save(layout);
		for (GroupLaytheme e : themeList) {
			e.setLayoutId(layout.getLayoutId());
		}
		groupLaythemeDao.saveOrUpdateAll(themeList);
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

	/**
	 * 删除布局
	 * 
	 * @param enterpriseId
	 */
	private void delLayout(String enterpriseId) {
		String layoutId = null;
		GroupLayout layout = null;
		GroupLayout example = new GroupLayout();
		example.setEnterpriseId(enterpriseId);
		example.setState(CommonConst.STATENORMAL);
		List<GroupLayout> list = dao.findByExample(example);
		if (!list.isEmpty()) {
			layout = list.get(0);
		}
		if (layout != null) {
			layoutId = layout.getLayoutId();
			dao.bulkUpdate("DELETE FROM GroupLaytheme WHERE layoutId=?",
					layoutId);
			dao.bulkUpdate("DELETE FROM GroupLaymod WHERE layout.layoutId=?",
					layoutId);
			dao.bulkUpdate("DELETE FROM GroupWidth WHERE layoutId=?", layoutId);
			dao.delete(layout);
		}
	}

	private void delAttachRecord(List<String> belongIdList) {
		// 用productDAO,删除attachement记录
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < belongIdList.size(); i++) {
			sb.append("?").append(",");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		productDAO.bulkUpdate("DELETE FROM AbcAttachment WHERE belongId IN ("
				+ sb.toString() + ")", belongIdList.toArray());
	}

	@Override
	public void cascadeSave(LayoutController handle) {
		// TODO Auto-generated method stub
		GroupLayout gl = handle.getModel();
		gl.setUpdateTime(new Date());
		dao.update(gl);
		List<GroupLaymod> belongList = handle.getLayoutmoduleList();
		String hql = "delete GroupLaymod where page=? and layout.layoutId=?";
		groupLaymodDao
				.bulkUpdate(hql, handle.getBelongPage(), gl.getLayoutId());
		groupLaymodDao.saveOrUpdateAll(setLaymodeNames(handle, belongList));

		GroupLaytheme gltheme = handle.getLaytheme();
		gltheme.setAddTime(new Date());
		groupLaythemeDao.saveOrUpdate(gltheme);

		groupWidthDao.saveOrUpdate(handle.getListWidth());

		if (handle.getUserDefinedList() != null) {
			List<String> list = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			list.add(handle.getCurrentUser().getEnterpriseId());
			for (GroupUserdefined g : handle.getUserDefinedList()) {
				if (g.getUserdefinedId() == null) {
					list.add(g.getModuleId());
					sb.append("?").append(",");
				}
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
			if (list.size() > 1) {
				hql = "DELETE FROM GroupUserdefined WHERE enterpriseId=? AND moduleId IN ("
						+ sb + ")";
				userDefineDao.bulkUpdate(hql, list.toArray());
			}
			userDefineDao.saveOrUpdateAll(handle.getUserDefinedList());
		}
	}

	
	@Override
	public void initUser(AbcUser user) {
		this.initNav(user);
		this.initLayout(user);
	}

	/**
	 * 统一模块名称
	 * 
	 * @param handle
	 * @param glm
	 * @return
	 */
	private List<GroupLaymod> setLaymodeNames(LayoutController handle,
			List<GroupLaymod> glm) {
		List<GroupLaymod> gnewList = new ArrayList<GroupLaymod>();
		String hql = "from GroupLaymod where page<>? and layout.layoutId=? and module.moduleId=?";
		for (GroupLaymod g : glm) {
			gnewList.add(g);
			if (g.getName() == null
					|| g.getModule().getModuleId().startsWith("user_defined"))
				continue;
			List<GroupLaymod> list = groupLaymodDao.find(hql, handle
					.getBelongPage(), handle.getModel().getLayoutId(), g
					.getModule().getModuleId());
			for (GroupLaymod mod : list) {
				mod.setName(g.getName());
				gnewList.add(mod);
			}
		}

		return gnewList;
	}
}
