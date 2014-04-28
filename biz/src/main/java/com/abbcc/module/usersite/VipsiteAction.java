/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "VipsiteAction.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2010-1-20           baowp                      initial
 */

package com.abbcc.module.usersite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcLayout;
import com.abbcc.models.AbcLayoutmodule;
import com.abbcc.models.AbcLaytheme;
import com.abbcc.models.AbcModule;
import com.abbcc.models.AbcSeo;
import com.abbcc.models.AbcTheme;
import com.abbcc.models.AbcUser;
import com.abbcc.service.LayoutService;
import com.abbcc.service.LaythemeService;
import com.abbcc.service.ModuleService;
import com.abbcc.service.SeoService;
import com.abbcc.service.ThemeService;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.constant.layout.BelongPage;
import com.abbcc.util.constant.layout.LaythemeState;
import com.abbcc.util.constant.layout.Piece;
import com.abbcc.util.constant.layout.PieceType;

@SuppressWarnings("serial")
public class VipsiteAction<T> extends SubsiteAction<AbcUser> {

	@Autowired
	private LayoutService layoutService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	protected LaythemeService laythemeService;
	@Autowired
	protected ThemeService themeService;
	@Autowired
	protected SeoService seoService;

	public AbcLayout layout;

	public List<AbcModule> moduleList;
	public Set<AbcModule> moduleHeadSet;
	public Set<AbcModule> moduleSideSet;
	public Set<AbcModule> moduleMainSet;
	public Set<AbcModule> moduleNavSet;
	public Set<AbcModule> moduleProductSet;
	public List<String> moduleIdHeadList;
	public List<String> moduleIdSideList;
	public List<String> moduleIdMainList;
	public List<String> moduleIdNavList;
	public List<String> moduleIdProductList;
	public Map<String, String> lan = CommonConst.languagePack;

	public BelongPage belongPage;

	public boolean maintainable; // 是否可维护，即是否旺铺后台

	private String defaultThemeId = "theme1";
	public AbcTheme pageTheme;
	protected LaythemeState layts;

	public VipsiteAction() {
		layts = LaythemeState.C;// 二级用户看到的是已经应用的风格
		layout = new AbcLayout();
	}

	protected boolean beforeAction() {
		if (super.beforeAction()) {
			if (entity.getGrade() == null
					|| entity.getGrade().equals(CommonConst.USERGRADENONE)
					|| "03".equals(getSession().getAttribute("memberState"))) {
				try {
					getResponse().sendRedirect(
							"http://51archetype.com/site/" + entity.getUsername());
				} catch (IOException e) {
					log.warn(e.getMessage());
				}
				return false;
			}
			deposit("layout", layout);
			AbcLayout lay = new AbcLayout();
			lay.setEnterpriseId(entity.getEnterpriseId());
			lay.setState(CommonConst.STATENORMAL);
			List<AbcLayout> layoutList = layoutService.findByExample(lay);
			if (layoutList.size() > 0) {
				layoutList.get(0).setBelongPage(layout.getBelongPage());
				ObjectUtil.extend(layout, layoutList.get(0));
			}
			if (layout.getLayoutId() != null) {
				AbcLaytheme layt = new AbcLaytheme();
				layt.setLayoutId(layout.getLayoutId());
				layt.setState(layts);
				@SuppressWarnings("unchecked")
				List<AbcLaytheme> list = laythemeService.findByExample(layt);
				if (list.size() > 0) {
					layout.setLaytheme(list.get(0));
					laytheme();
					JSONObject log = (JSONObject) layout.getJsonSign().get(
							"log");
					if (log != null) {
						deposit("jsonSign", layout.getJsonSign());
					}
					pageTheme = layout.getLaytheme().getTheme();
				} else {
					pageTheme = (AbcTheme) themeService.findById(
							AbcTheme.class, defaultThemeId);
				}
				JSONObject jsonSign = layout.getJsonSign();
				JSONObject setting = jsonSign.getJSONObject("setting");
				if (setting.isNullObject())
					setting = new JSONObject();
				if (setting.get("pshow") == null)
					setting.put("pshow", 1);// 产品列表默认只显示产品名称
				deposit("setting", setting);
			} else {
				pageTheme = (AbcTheme) themeService.findById(AbcTheme.class,
						defaultThemeId);
			}

			{
				List<AbcLayoutmodule> navmodules = layout.getNavmoduleList();
				if (navmodules == null) {
					navmodules = new ArrayList<AbcLayoutmodule>();
					for (Piece piece : Piece.defaultNavs()) {
						navmodules.add(new AbcLayoutmodule(moduleService
								.findById(piece.name()), layout));
					}
					layout.setNavmoduleList(navmodules);
				}
			}

			{
				moduleList = moduleService.findAll();
				Map<String, String> map = new HashMap<String, String>();
				for (AbcModule module : moduleList) {
					map.put(module.getModuleId(), module.getName());
				}
				deposit("moduleMap", map);
			}
			return true;
		}
		return false;
	}

	protected void laytheme() {
		if (servletContext.getContextPath().length() > 1)
			layout.getLaytheme().setStyle(
					layout.getLaytheme()
							.getStyle()
							.replaceAll("url\\((?!http)",
									"url(" + servletContext.getContextPath()));
	}

	public String layout() {
		beforeAction();
		return NONE;
	}

	public String home() {
		layout.setBelongPage(BelongPage.HOME);
		if (maintainable)
			return super.home();
		return flash();
	}

	public String index() {
		layout.setBelongPage(BelongPage.HOME);
		return super.index();
	}

	public String product() {
		layout.setBelongPage(BelongPage.PRODUCT);
		return super.product();
	}

	public String product_detail() {
		layout.setBelongPage(BelongPage.PRODUCT_DETAIL);
		return super.product_detail();
	}

	public String supply() {
		layout.setBelongPage(BelongPage.SUPPLY);
		return super.supply();
	}

	public String supply_detail() {
		layout.setBelongPage(BelongPage.SUPPLY_DETAIL);
		return super.supply_detail();
	}

	public String company() {
		layout.setBelongPage(BelongPage.COMPANY);
		return enterprise();
	}

	public String contact() {
		layout.setBelongPage(BelongPage.CONTACT);
		return person();
	}

	public String message() {
		layout.setBelongPage(BelongPage.MESSAGE);
		return super.message();
	}

	public String photo() {
		layout.setBelongPage(BelongPage.PHOTO);
		return super.photo();
	}

	public String technic() {
		layout.setBelongPage(BelongPage.TECHNIC);
		return super.technic();
	}

	public String news() {
		layout.setBelongPage(BelongPage.NEWS);
		return super.news();
	}

	public String news_detail() {
		layout.setBelongPage(BelongPage.NEWS_DETAIL);
		return super.news_detail();
	}

	public String recruit() {
		layout.setBelongPage(BelongPage.RECRUIT);
		return super.recruit();
	}

	public String cert() {
		layout.setBelongPage(BelongPage.CERT);
		return super.cert();
	}

	public String search() {
		layout.setBelongPage(BelongPage.SEARCH);
		return super.search();
	}

	@SuppressWarnings("unchecked")
	public String flash() {
		if (getRequest().getParameter("preview") != null)
			return "flash";
		else {
			if (beforeAction()) {
				JSONObject json = null;
				if (layout.getJsonSign().get("flash") != null)
					json = layout.getJsonSign().getJSONObject("flash");
				if (json != null) {
					if (json.get("src") != null && json.getBoolean("display")) {
						for (Map.Entry<String, ?> entry : (Set<Map.Entry<String, ?>>) json
								.entrySet())
							deposit(entry.getKey(), entry.getValue());
						return "flash";
					}
				}
			} else
				return "none";
		}
		return "index";
	}

	public String getMetaTitle() {
		String key = layout.getBelongPage().name() + "-meta-title";
		if (take(key) != null)
			return (String) take(key);

		List<AbcSeo> list = fetchSeoList();
		AbcSeo seo = null;
		for (AbcSeo as : list) {
			if (as.getBelongPage() == layout.getBelongPage()) {
				seo = as;
				break;
			}
		}
		String value = entity.getEnterprise().getName();
		if (seo != null) {
			value = seo.getTitle();
		}
		deposit(key, value);
		return value;
	}

	public String getMetaKeywords() {
		String key = layout.getBelongPage().name() + "-meta-keywords";
		if (take(key) != null)
			return (String) take(key);

		List<AbcSeo> list = fetchSeoList();
		AbcSeo seo = null;
		for (AbcSeo as : list) {
			if (as.getBelongPage() == layout.getBelongPage()) {
				seo = as;
				break;
			}
		}
		String value = null;
		if (seo != null) {
			value = seo.getKeywords();
		}
		deposit(key, value);
		return value;
	}

	public String getMetaDescription() {
		String key = layout.getBelongPage().name() + "-meta-description";
		if (take(key) != null)
			return (String) take(key);

		List<AbcSeo> list = fetchSeoList();
		AbcSeo seo = null;
		for (AbcSeo as : list) {
			if (as.getBelongPage() == layout.getBelongPage()) {
				seo = as;
				break;
			}
		}
		String value = null;
		if (seo != null) {
			value = seo.getDescription();
		}
		deposit(key, value);
		return value;
	}

	@SuppressWarnings("unchecked")
	protected List<AbcSeo> fetchSeoList() {
		String key = layout.getBelongPage().name() + "-meta";
		if (take(key) != null)
			return (List<AbcSeo>) take(key);
		else {
			AbcSeo seo = new AbcSeo(entity.getEnterpriseId(),
					layout.getBelongPage());
			List<AbcSeo> list = seoService.findByExample(seo);
			deposit(key, list);
			return list;
		}
	}

	public String manage() {
		AbcLayout layout = new AbcLayout();
		layout.setEnterpriseId(getCurrentUser().getEnterpriseId());
		layout.setState(CommonConst.STATENORMAL);
		List<AbcLayout> layoutList = layoutService.findByExample(layout);
		layout = layoutList.size() > 0 ? layoutList.get(0) : layout;
		layout.setBelongPage(belongPage);

		moduleList = moduleService.findAll();
		moduleHeadSet = new TreeSet<AbcModule>();
		moduleSideSet = new TreeSet<AbcModule>();
		moduleMainSet = new TreeSet<AbcModule>();
		moduleNavSet = new TreeSet<AbcModule>();
		moduleProductSet = new TreeSet<AbcModule>();
		for (AbcModule module : moduleList) {
			if (module.getType() == PieceType.HD) {
				if (layout.getHeadmoduleList() != null)
					for (AbcLayoutmodule layoutmodule : layout
							.getHeadmoduleList())
						if (module.getModuleId().equals(
								layoutmodule.getModule().getModuleId())) {
							module.setOrder(layoutmodule.getBlockOrder());
							if (layoutmodule.getName() != null)
								module.setName(layoutmodule.getName());
							break;
						}
				moduleHeadSet.add(module);
			} else if (module.getType() == PieceType.BS) {
				if (layout.getSidemoduleList() != null)
					for (AbcLayoutmodule layoutmodule : layout
							.getSidemoduleList())
						if (module.getModuleId().equals(
								layoutmodule.getModule().getModuleId())) {
							module.setOrder(layoutmodule.getBlockOrder());
							if (layoutmodule.getName() != null)
								module.setName(layoutmodule.getName());
							break;
						}
				moduleSideSet.add(module);
			} else if (module.getType() == PieceType.BM) {
				if (layout.getMainmoduleList() != null)
					for (AbcLayoutmodule layoutmodule : layout
							.getMainmoduleList())
						if (module.getModuleId().equals(
								layoutmodule.getModule().getModuleId())) {
							module.setOrder(layoutmodule.getBlockOrder());
							if (layoutmodule.getName() != null)
								module.setName(layoutmodule.getName());
							break;
						}
				moduleMainSet.add(module);
			} else if (module.getType() == PieceType.NV) {
				if (layout.getNavmoduleList() != null)
					for (AbcLayoutmodule layoutmodule : layout
							.getNavmoduleList())
						if (module.getModuleId().equals(
								layoutmodule.getModule().getModuleId())) {
							module.setOrder(layoutmodule.getBlockOrder());
							if (layoutmodule.getName() != null)
								module.setName(layoutmodule.getName());
							break;
						}
				moduleNavSet.add(module);
			} else if (module.getType() == PieceType.PD) {
				if (layout.getProductmoduleList() != null)
					for (AbcLayoutmodule layoutmodule : layout
							.getProductmoduleList())
						if (module.getModuleId().equals(
								layoutmodule.getModule().getModuleId())) {
							module.setOrder(layoutmodule.getBlockOrder());
							if (layoutmodule.getName() != null)
								module.setName(layoutmodule.getName());
							break;
						}
				moduleProductSet.add(module);
			}
		}
		if (layout.getBodyContent() != null) {
			final JSONObject titles = layout.getJsonContent().getJSONObject(
					"title");
			if (titles != null) {
				class Ticket {
					void put(Set<AbcModule> set) {
						for (AbcModule module : set) {
							String name = (String) titles.get(module
									.getModuleId());
							if (name != null)
								module.setName(name);
						}
					}
				}
				Ticket ticket = new Ticket();
				ticket.put(moduleHeadSet);
				ticket.put(moduleSideSet);
				ticket.put(moduleMainSet);
				ticket.put(moduleNavSet);
			}
		}

		moduleIdHeadList = new ArrayList<String>();
		moduleIdSideList = new ArrayList<String>();
		moduleIdMainList = new ArrayList<String>();
		moduleIdNavList = new ArrayList<String>();
		moduleIdProductList = new ArrayList<String>();

		if (layout.getHeadmoduleList() != null)
			for (AbcLayoutmodule layoutmodule : layout.getHeadmoduleList())
				moduleIdHeadList.add(layoutmodule.getModule().getModuleId());
		if (layout.getSidemoduleList() != null)
			for (AbcLayoutmodule layoutmodule : layout.getSidemoduleList())
				moduleIdSideList.add(layoutmodule.getModule().getModuleId());
		if (layout.getMainmoduleList() != null)
			for (AbcLayoutmodule layoutmodule : layout.getMainmoduleList())
				moduleIdMainList.add(layoutmodule.getModule().getModuleId());
		if (layout.getNavmoduleList() != null)
			for (AbcLayoutmodule layoutmodule : layout.getNavmoduleList())
				moduleIdNavList.add(layoutmodule.getModule().getModuleId());
		if (layout.getProductmoduleList() != null)
			for (AbcLayoutmodule layoutmodule : layout.getProductmoduleList())
				moduleIdProductList.add(layoutmodule.getModule().getModuleId());

		return "manage";
	}

	public AbcLayout getLayout() {
		return layout;
	}

	public void setDefaultThemeId(String defaultThemeId) {
		this.defaultThemeId = defaultThemeId;
	}
}
