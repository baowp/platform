package com.abbcc.springrest.controller.user.site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcUser;
import com.abbcc.models.GroupLaymod;
import com.abbcc.models.GroupLayout;
import com.abbcc.models.GroupLaytheme;
import com.abbcc.models.GroupModule;
import com.abbcc.models.GroupNavigator;
import com.abbcc.models.GroupSeo;
import com.abbcc.models.GroupTheme;
import com.abbcc.models.GroupWidth;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.group.GroupLaythemeType;
import com.abbcc.util.constant.group.GroupPage;
import com.abbcc.util.constant.group.GroupPieceType;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/site")
public class SiteController<T> extends PieceController<AbcUser> {

	protected GroupLayout layout;
	protected GroupTheme pageTheme;
	protected boolean maintainable;
	protected GroupLaythemeType layts;
	private String belongPage;
	private String defaultThemeId = "theme1";
	private boolean isBefore;
	protected boolean sameLaymod;

	protected List<GroupModule> moduleList; // 模块

	public SiteController() {
		this.pageSize = 16;
		layts = GroupLaythemeType.C;// 二级用户看到的是已经应用的风格
		layout = new GroupLayout();
	}

	protected boolean before() {
		if (super.before()) {
			if (isBefore)
				return true;
			layout();
			layoutModule();
			moduleMap();
			return isBefore = true;
		}
		return false;
	}

	/**
	 * 布局与主题
	 */
	private void layout() {
		if (take("layout") != null)
			return;

		layout.setEnterpriseId(entity.getEnterpriseId());
		layout.setState(CommonConst.STATENORMAL);
		List<GroupLayout> layoutList = layoutService.findByExample(layout);
		if (layoutList.size() > 0) {
			layout = layoutList.get(0);
		}
		if (layout.getLayoutId() != null) {
			GroupLaytheme layt = new GroupLaytheme();
			layt.setLayoutId(layout.getLayoutId());
			layt.setType(layts);
			List<GroupLaytheme> list = laythemeService.findByExample(layt);
			if (list.size() > 0) {
				GroupLaytheme laytheme = list.get(0);
				deposit("laytheme", laytheme);
				JSONObject log = (JSONObject) layout.getJsonSign().get("log");
				if (log != null) {
					deposit("jsonSign", layout.getJsonSign());
				}
				pageTheme = laytheme.getTheme();
			} else {
				pageTheme = themeService.get(defaultThemeId);
			}
		} else {
			pageTheme = themeService.get(defaultThemeId);
		}
		deposit("pageTheme", pageTheme);
		deposit("layout", layout);
	}

	/**
	 * 布局信息
	 */
	private void layoutModule() {
		if (belongPage == null || sameLaymod)
			return;

		headList = new ArrayList<GroupLaymod>();
		content = new ArrayList<GroupLaymod>();
		content1 = new ArrayList<GroupLaymod>();
		content2 = new ArrayList<GroupLaymod>();
		content3 = new ArrayList<GroupLaymod>();
		belowList = new ArrayList<GroupLaymod>();
		String page = checkLayModByPage(belongPage);
		for (GroupLaymod g : laymodlist) {
			switch (g.getPosition()) {
			case headList:
				headList.add(g);
				prefixPiece(g);
				break;
			case content:
				content.add(g);
				prefixPiece(g);
				break;
			case content1:
				content1.add(g);
				break;
			case content2:
				content2.add(g);
				break;
			case content3:
				content3.add(g);
				break;
			case belowList:
				belowList.add(g);
				prefixPiece(g);
				break;
			default:
				break;
			}
		}
		// 自定义模块
		userDefinedList(layout, page);
	}

	/**
	 * 检查传入页面 page 下是否有module,没有则递归上级
	 * 
	 * @param page
	 *            [传入页面]
	 * @return page [返回页面]
	 */
	private String checkLayModByPage(String page) {
		DetachedCriteria dc = DetachedCriteria.forClass(GroupLaymod.class);
		dc.add(Property.forName("layout.layoutId").eq(layout.getLayoutId()));
		dc.add(Property.forName("page").eq(page)); // 传入页面
		dc.addOrder(Order.asc("position"));
		dc.addOrder(Order.asc("pieceOrder"));
		laymodlist = laymodService.findAllByCriteria(dc);
		if (laymodlist.isEmpty() && !page.equals("index")) {
			DetachedCriteria dcGv = DetachedCriteria
					.forClass(GroupNavigator.class);
			dcGv.add(Property.forName("enterpriseId").eq(
					entity.getEnterpriseId()));
			dcGv.add(Property.forName("page").eq(page));
			List<GroupNavigator> listNav = navigatorService
					.findAllByCriteria(dcGv);
			String parentId = listNav.get(0).getParentId(); // 存在记录，没有parentId
															// 将拋异常
			if (StringUtil.isBlank(parentId)) { // 赋值page，并以首页的内容填充该page
				page = "index";
			} else {
				List<GroupNavigator> listNav2 = navigatorService.find(
						"from GroupNavigator where navigatorId=?", parentId);
				page = listNav2.get(0).getPage(); // 重新赋值 page
			}
			checkLayModByPage(page);
		}
		listWidth(page); // 发送页面宽度
		return page; // 返回页面
	}

	/**
	 * 模块
	 */
	private void moduleMap() {
		if (take("moduleMap") != null)
			return;

		moduleList = moduleService.loadAll();
		String hql = "select distinct module.moduleId,name from GroupLaymod where layout.layoutId=?";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) baseService.find(hql,
				layout.getLayoutId());
		Map<String, String> map = new HashMap<String, String>();
		for (Object[] str : list) {
			String name = (String) str[1];
			if (name != null) // name 不为空,放入map
				map.put((String) str[0], (String) str[1]);
		}
		Map<String, String> moduleMap = new HashMap<String, String>();
		for (GroupModule module : moduleList) {
			String name = map.get(module.getModuleId());
			if (name != null) { // name 不为空
				module.setName(name); // 设置模块名称
				map.remove(module.getModuleId()); // 移除
			}
			moduleMap.put(module.getModuleId(), module.getName());
		}
		// 自定义模块
		for (Map.Entry<String, String> entry : userDefinedMap().entrySet()) {
			moduleMap.put(entry.getKey(), entry.getValue());
			GroupModule module = new GroupModule();
			module.setModuleId(entry.getKey());
			module.setName(entry.getValue());
			module.setType(GroupPieceType.UE); // 用户扩展的
			moduleList.add(module);
		}
		deposit("moduleMap", moduleMap);
	}

	private void listWidth(String page) {
		GroupWidth example = new GroupWidth();
		example.setLayoutId(layout.getLayoutId());
		example.setPage(page);
		List<GroupWidth> widthList = widthService.findByExample(example);
		if (!widthList.isEmpty()) {
			deposit("listWidth", widthList.get(0));
		}
	}

	/**
	 * 主入口
	 * @param username [用户名]
	 * @return
	 */
	@RequestMapping(value = "/{username}")
	public String index(@PathVariable String username) {
		if (maintainable) {
			return navigator(username, GroupPage.index.name(), null);
		}
		return flash(username);
	}

	@RequestMapping(value = "/{username}/flash")
	public String flash(@PathVariable String username) {
		if (getRequest().getParameter("preview") != null)
			return "group/dynamic/flash";
		else {
			entity.setUsername(username);
			if (super.before()) {
				GroupLayout lay = new GroupLayout();
				lay.setEnterpriseId(entity.getEnterpriseId());
				lay.setState(CommonConst.STATENORMAL);
				List<GroupLayout> layoutList = layoutService.findByExample(lay);
				if (layoutList.size() > 0) {
					lay = layoutList.get(0);
					JSONObject json = null;
					if (lay.getJsonSign().get("flash") != null)
						json = lay.getJsonSign().getJSONObject("flash");
					if (json != null) {
						if (json.get("src") != null
								&& json.getBoolean("display")) {
							for (Map.Entry<String, ?> entry : (Set<Map.Entry<String, ?>>) json
									.entrySet())
								deposit(entry.getKey(), entry.getValue()); // 发送flash数据
							return "group/dynamic/flash";
						}
					}
				}
			}
		}
		return navigator(username, GroupPage.index.name(), null);
	}

	@RequestMapping(value = "/{username}/{belongPage}")
	public String navigator(@PathVariable String username,
			@PathVariable String belongPage, String itemId) {
		this.belongPage = belongPage;		// 传入页面
		entity.setUsername(username);		// 设置用户名
		before();
		after();
		seo(belongPage, itemId);
		deposit("itemId", itemId);
		return viewName("group/dynamic/index");
	}

	protected void after() {
		deposit("belongPage", belongPage);
		deposit("maintainable", maintainable);

		deposit("laymods", laymodlist);
		deposit("headList", headList);
		deposit("content", content);
		deposit("content1", content1);
		deposit("content2", content2);
		deposit("content3", content3);
		deposit("belowList", belowList);
	}

	/**
	 * seo 输出
	 * 
	 * @param page
	 *            [打开页面]
	 * @param itemId
	 *            [详细页面项目的ID]
	 */
	private void seo(String page, String itemId) {
		GroupSeo seoExample = new GroupSeo();
		if (GroupPage.contain(page) && page.indexOf("detail") > -1) { // 系统自带导航页面
																		// &&
																		// 详细页面
			switch (GroupPage.valueOf(page)) {
			case product_detail:
				pieceProductDetail(itemId);
				seoExample.setTitle(product.getName());
				seoExample.setKeywords(product.getPkey());
				break;
			case news_detail:
				pieceNewsDetail(itemId);
				seoExample.setTitle(news.getTitle());
				seoExample.setKeywords(news.getNkey());
				break;
			case supply_detail:
				pieceSupplyDetail(itemId);
				seoExample.setTitle(supply.getTitle());
				seoExample.setKeywords(supply.getSkey());
				break;
			case album_detail:
				pieceAlbumDetail(itemId);
				seoExample.setTitle(album.getName());
				break;
			}
		} else {
			GroupSeo temp = seo(page);
			if (temp == null) {
				seoExample.setTitle(entity.getEnterprise().getName());
			} else {
				seoExample = temp;
			}
		}
		this.deposit("seo", seoExample);
	}

	protected GroupSeo seo(String page) {
		GroupSeo example = new GroupSeo(); // 查询groupSeo表
		example.setPage(page);
		example.setEnterpriseId(entity.getEnterpriseId());
		List<GroupSeo> list = seoService.findByExample(example);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	protected void initialize() {
		isBefore = false;
	}

	public void pagination(int page, int totalPage) {
		int start = 1, end = totalPage;
		if (totalPage > 10) {
			start = page - 4;
			end = page + 5;
			if (start < 1) {
				start = 1;
				end += 1 - start;
			} else if (end > totalPage) {
				start -= end - totalPage;
				end = totalPage;
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int i = start; i <= end; i++)
			list.add(i);
		deposit("pagination", list);
	}
}
