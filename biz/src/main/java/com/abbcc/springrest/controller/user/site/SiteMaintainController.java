package com.abbcc.springrest.controller.user.site;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abbcc.models.AbcUser;
import com.abbcc.models.GroupLaymod;
import com.abbcc.models.GroupLaytheme;
import com.abbcc.models.GroupModule;
import com.abbcc.util.constant.group.GroupLaythemeType;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@RequestMapping("/maintain")
public class SiteMaintainController extends SiteController<AbcUser> {

	/** type: head below **/
	private List<GroupModule> headbelowList = new ArrayList<GroupModule>();
	/** type: content **/
	private List<GroupModule> contentList = new ArrayList<GroupModule>();
	/** kind: wide **/
	private List<GroupModule> wideList = new ArrayList<GroupModule>();
	/** kind: narrow **/
	private List<GroupModule> narrowList = new ArrayList<GroupModule>();
	/** type: UE **/
	private List<GroupModule> userDefinedList = new ArrayList<GroupModule>();
	/** type: DL **/
	private List<GroupModule> detaiList = new ArrayList<GroupModule>();

	private boolean isBefore;

	public SiteMaintainController() {
		maintainable = true;
	}

	protected boolean before() {
		if (isBefore)
			return true;
		if (super.before()) {
			{// 没有相应权限跳转页面
				if (forbid(entity.getEnterpriseId())) {
					try {
						getResponse().sendRedirect(
								"http://51archetype.com/rest/site/"
										+ entity.getUsername());
					} catch (IOException e) {
						log.error(e);
					}
				}
			}
			if (this.getSession().getAttribute("memberState") != null
					&& this.getSession().getAttribute("memberState")
							.equals("03")) {
				try {
					getResponse().sendRedirect("h51archetype.comcc.net/404.html");
				} catch (IOException e) {
					log.error(e);
				}
			}

			deposit("themeList", themeService.loadAll());
			DetachedCriteria dc = DetachedCriteria
					.forClass(GroupLaytheme.class)
					.add(Property.forName("layoutId").eq(layout.getLayoutId()))
					.add(Property.forName("type").eq(GroupLaythemeType.A))
					.addOrder(Property.forName("addTime").asc());
			deposit("mythemeList", laythemeService.findAllByCriteria(dc));

			prepareTool();
			prepareAddPiece();
			return isBefore = true;
		}
		return false;
	}

	private void prepareTool() {
		Set<String> bannerSet = new HashSet<String>();
		deposit("bannerSet", bannerSet);
		Set<String> navBgSet = new HashSet<String>();
		deposit("navBgSet", navBgSet);
		Set<String> titleBgSet = new HashSet<String>();
		deposit("titleBgSet", titleBgSet);
		Set<String> inBgSet = new HashSet<String>();
		Set<String> outBgSet = new HashSet<String>();
		Set<String> bgSet = new HashSet<String>();

		String path = "/group/dynamic/theme";
		String realPath = this.getServletContext().getRealPath(path);
		File themeFolder = new File(realPath);
		for (File theme : themeFolder.listFiles()) {
			if (theme.isDirectory()) {
				File imageFolder = new File(theme, "images");
				if (imageFolder.exists()) {
					File banner = fetchPic(imageFolder, "banner.min");
					if (banner.exists())
						bannerSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), banner.getName()));

					File navBg = fetchPic(imageFolder, "navBg");
					if (navBg.exists())
						navBgSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), navBg.getName()));

					File titleBg = fetchPic(imageFolder, "titleBg");
					if (titleBg.exists())
						titleBgSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), titleBg.getName()));

					File inBg = fetchPic(imageFolder, "inBg");
					if (inBg.exists())
						inBgSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), inBg.getName()));

					File outBg = fetchPic(imageFolder, "outBg");
					if (outBg.exists())
						outBgSet.add(buildPath(path, theme.getName(),
								imageFolder.getName(), outBg.getName()));
				}
			}
		}

		bgSet.addAll(inBgSet);
		bgSet.addAll(outBgSet);
		deposit("bgSet", bgSet);
	}

	protected void after() {
		super.after();
		deposit("headbelowList", headbelowList);
		deposit("contentList", contentList);
		deposit("wideList", wideList);
		deposit("narrowList", narrowList);
		deposit("userDefinedList", userDefinedList);
		deposit("detailList", detaiList);
	}

	// 前台添加piece
	private void prepareAddPiece() {
		for (GroupModule module : this.moduleList) {
			switch (module.getType()) {
			case HB:
				headbelowList.add(module);
				break;
			case CT:
				contentList.add(module);
				switch (module.getKind()) {
				case NR:
					narrowList.add(module); // 类别下kind
					break;
				case WD:
					wideList.add(module);
					break;
				default:
					break;
				}
				break;
			case UE:
				userDefinedList.add(module);	
				break;
			case DL:
				detaiList.add(module);
				break;
			default:
				break;
			}
		}
	}

	private File fetchPic(File folder, String name) {
		File file = new File(folder, name + ".gif");
		if (!file.exists())
			file = new File(folder, name + ".jpg");
		return file;
	}

	private String buildPath(String... str) {
		String string = this.getServletContext().getContextPath();
		for (String s : str)
			string += s + "/";
		return string.replaceAll("/$", "");
	}

}
