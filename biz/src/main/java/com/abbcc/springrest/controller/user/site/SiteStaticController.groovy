package com.abbcc.springrest.controller.user.site
import java.io.File
import java.io.IOException
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List
import java.util.Map

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import com.abbcc.common.CommonConst
import com.abbcc.common.ConfConst
import com.abbcc.common.PaginationSupport
import com.abbcc.helper.FreemarkerHelper
import com.abbcc.models.AbcAlbum;
import com.abbcc.models.AbcAttachment;
import com.abbcc.models.AbcCategory
import com.abbcc.models.AbcProduct
import com.abbcc.models.AbcUser
import com.abbcc.models.GroupSeo;
import com.abbcc.models.interfaces.EnterpriseId;
import com.abbcc.util.ObjectUtil;
import com.abbcc.util.StringUtil;
import com.abbcc.util.constant.AlbumType;
import com.abbcc.util.constant.group.GroupPage
import com.abbcc.util.constant.group.GroupStaticDetail;
import com.abbcc.util.constant.group.GroupStaticRely
import com.abbcc.util.constant.layout.StaticDetail;

import freemarker.ext.beans.BeansWrapper

@Controller
@Scope("prototype")
@RequestMapping("/static")
class SiteStaticController extends SiteController<AbcUser> {

	private Map<String, Object> root = [:];
	private FreemarkerHelper freemarkerHelper;
	private GroupPage groupPage;
	private String targetHtmlDir;
	private String targetHtmlPath;
	private String ftl = "index.ftl";
	private boolean isBefore;
	private String username;
	private Map seoMap;

	protected boolean before(){
		if (super.before()) {
			if (isBefore)
				return true;
			targetHtmlDir = ConfConst.FILE_UPLOAD_DIR + "vipsite" + CommonConst.SEP +
					username + CommonConst.SEP + CommonConst.FOLDER_HTML + CommonConst.SEP;
			freemarkerHelper = new FreemarkerHelper(getServletContext().getRealPath("group/static"));
			deposit("command", this);
			deposit("enums", BeansWrapper.getDefaultInstance().getEnumModels());
			deposit("statics", BeansWrapper.getDefaultInstance().getStaticModels());
			return isBefore = true;
		}
	}

	@RequestMapping(value = "/{username}/search")
	@ResponseBody
	void search(@PathVariable String username){
		entity.setUsername(this.username=username);
		keywords = keywords.replace('/', '-');
		navigator username, "search", null;
		targetHtmlPath = "${targetHtmlDir}search${CommonConst.SEP}${keywords};${priceStart};${priceEnd}.html";
		freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
		String html = StringUtil.readFromFile(targetHtmlPath, "utf8");
		output(html);
	}

	@RequestMapping(value = "/{username}/publish")
	@ResponseBody
	void publish(@PathVariable String username){
		entity.setUsername(this.username=username);
		pieceNavigator();
		def closure;
		closure = {
			initialize();
			staticize(it.page);
			it.subJson.each closure;
		}
		take("navigator").each closure;
		staticFlash();
		staticRely();
		FileUtils.deleteDirectory(new File(targetHtmlDir + "search"));
	}

	private void staticize(String page) {
		navigator username, page, null;
		targetHtmlPath = targetHtmlDir + page + ".html";
		this.setPage 1;
		freemarkerHelper.createHTML(root, ftl, targetHtmlPath);

		def closure;
		if(GroupPage.contain(page)){
			groupPage = GroupPage.valueOf page;
			if(groupPage.key){
				PaginationSupport ps = take(groupPage.key);
				if(ps){
					for (int i = 1; i <= ps.getPageCount(); i++) {
						setPage(i);
						targetHtmlPath = targetHtmlDir + page + CommonConst.SEP + i + ".html";
						freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
					}
					switch (page) {
						case "product":
							eachCategoryProducts(take("categoryList"));
							break;
						case "news":
							eachCategoryNews(take("newsRoots"));
							break;
					}
				}
			}
			if(groupPage.detail) {
				GroupStaticDetail detail = groupPage.getDetail();
				JSONObject json = JSONObject.fromObject(detail.getExample());
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setArrayMode(JsonConfig.MODE_OBJECT_ARRAY);
				jsonConfig.setRootClass(detail.getClazz());
				EnterpriseId example = JSONSerializer.toJava(json, jsonConfig);
				example.setEnterpriseId(entity.getEnterpriseId());
				List list = baseService.findByExample(example);
				if (example instanceof AbcProduct)
					productPic((AbcProduct[]) list.toArray(new AbcProduct[0]));
				for (Iterator<?> ite = list.iterator(); ite.hasNext();) {
					Serializable entity = ite.next();
					deposit(detail.name().toLowerCase(), entity);
					String name = detail.getId(entity);
					super.initialize();
					navigator username, page+"_detail", name;
					sameLaymod=true;
					if(groupPage==GroupPage.album)
						eachPic(name);
					targetHtmlPath = targetHtmlDir + page + CommonConst.SEP + "detail" +
							CommonConst.SEP + name.replaceFirst("^[^1-9]+", "") + ".html";
					freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
				}
			}
		}
	}


	/**
	 * 递归产品类别,生成静态文件
	 * @param categoryList
	 * @param page
	 * @param despoitValue
	 */
	private void eachCategoryProducts(List<AbcCategory> categoryList) {
		if (categoryList != null) {
			for (AbcCategory category : categoryList) {
				categoryId = category.getCategoryId();
				String cateFolder = categoryId.replaceFirst("^[^1-9]+", "");
				pieceProducts();
				PaginationSupport ps = take(groupPage.key);
				int pageCount = ps.getPageCount() > 0 ? ps.getPageCount() : 1;
				for (int i = 1; i <= pageCount; i++) {
					setPage(i);
					targetHtmlPath = targetHtmlDir + groupPage.name() +
							CommonConst.SEP + cateFolder + CommonConst.SEP + i + ".html";
					freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
				}
				categoryId = null;
				eachCategoryProducts(category.getSonCate());
			}
		}
	}

	private void eachCategoryNews(List<AbcCategory> categoryList) {
		if (categoryList != null) {
			for (AbcCategory category : categoryList) {
				newsCategory = category.getCategoryId();
				String cateFolder = newsCategory.replaceFirst("^[^1-9]+", "");
				commonNews();
				PaginationSupport ps = take(groupPage.key);
				int pageCount = ps.getPageCount() > 0 ? ps.getPageCount() : 1;
				for (int i = 1; i <= pageCount; i++) {
					setPage(i);
					targetHtmlPath = targetHtmlDir + groupPage.name() +
							CommonConst.SEP + cateFolder + CommonConst.SEP + i + ".html";
					freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
				}
				newsCategory = null;
				eachCategoryNews(category.getSonCate());
			}
		}
	}

	/**
	 * 静态页,生成所有目录结构
	 */
	protected void setupCategory(AbcCategory... categories) {
		for (AbcCategory cate : categories) {
			if (CommonConst.CATEGORYTYPEPRODUCT.equals(cate.getType())) {
				cate.setSonCate(cate.subCategory3());
			} else if (CommonConst.CATEGORYTYPENEWS.equals(cate.getType())) {
				cate.setSonCate(cate.subCategory4());
			}
			setupCategory(cate.getSonCate().toArray(new AbcCategory[0]));
		}
	}

	private void staticFlash() {
		targetHtmlPath = targetHtmlDir + "index.htm";
		if (index(username).endsWith("flash")) {
			ftl = "flash.ftl";
			freemarkerHelper.createHTML(root, ftl, targetHtmlPath);
		} else {
			new File(targetHtmlPath).delete();
		}
	}

	private void staticRely(){
		for (GroupStaticRely rely : GroupStaticRely.values()) {
			String resDir = rely.getResourceDir();
			String buildDir = rely.getBuild();
			for (String name : rely.getResource().split(",")) {
				if (name.endsWith("*")) {
					File folder = new File(getServletContext().getRealPath(resDir
							+ "/" + (name = name.replace("*", ""))));
					if (folder.exists())
						try {
							FileUtils.copyDirectory(folder, new File(
									targetHtmlDir + buildDir + "/" + name));
						} catch (IOException e) {
							log.error(e);
						}
				} else {
					File file = new File(getServletContext().getRealPath(resDir
							+ "/" + name));
					if (file.exists())
						try {
							FileUtils.copyFile(file, new File(targetHtmlDir
									+ buildDir + "/" + name));
						} catch (IOException e) {
							log.error(e);
						}
				}
			}
		}
		File folder = new File(this.getServletContext().getRealPath("group/dynamic/theme/"
				+ pageTheme.getFolder()));
		if (folder.exists())
			try {
				File destDir = new File(targetHtmlDir + "theme");
				for (File file : folder.listFiles()) {
					if (file.isDirectory())
						FileUtils.copyDirectoryToDirectory(file, destDir);
					else if (file.isFile())
						FileUtils.copyFileToDirectory(file, destDir);
				}
			} catch (IOException e) {
				log.error(e);
			}
	}

	protected GroupSeo seo(String page) {
		if(!seoMap){
			seoMap = new HashMap();
			GroupSeo example = new GroupSeo();
			example.setEnterpriseId(entity.getEnterpriseId());
			List<GroupSeo> list = seoService.findByExample(example);
			list.each {
				seoMap.put it.page, it;
			}
		}
		return seoMap[page];
	}

	protected void initialize() {
		super.initialize();
		sameLaymod=false;
		remove("listWidth");
	}

	protected void deposit(String key, Object value) {
		root.put(key, value);
	}

	protected void remove(String key){
		root.remove key;
	}

	Object take(String key) {
		return root.get(key);
	}
}
