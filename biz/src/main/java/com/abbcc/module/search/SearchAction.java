package com.abbcc.module.search;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.abbcc.action.BaseAction;
import com.abbcc.common.CommonConst;
import com.abbcc.models.AbcEnterprise;
import com.abbcc.models.AbcPopularize;
import com.abbcc.models.AbcProduct;
import com.abbcc.models.AbcSupply;
import com.abbcc.models.AbcTopSearch;
import com.abbcc.news.models.NewsNews;
import com.abbcc.news.service.NewsNewsService;
import com.abbcc.service.EnterpriseService;
import com.abbcc.service.PopularizeService;
import com.abbcc.service.ProductService;
import com.abbcc.service.SupplyService;
import com.abbcc.service.TopSearchService;
import com.abbcc.util.StringUtil;

@SuppressWarnings("serial")
public class SearchAction extends BaseAction<AbcEnterprise> {
	public String entName;
	public String pageType;
	public String pageTypeHidden;
	private EnterpriseService enterpriseService;
	private ProductService productService;
	private NewsNewsService newsNewsService;
	private SupplyService supplyService;
	@Autowired
	private TopSearchService topSearchService;

	private PopularizeService popularizeService;
	@SuppressWarnings({ "unused", "rawtypes" })
	private List listSearch;
	List list = null;
	int DEFAULT_PAGESIZE = 10;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String byName() throws Exception {
		listSearch = new ArrayList();
		int thispage = page;
		String[] product = { "name", "pkey" };
		String[] enterprise = { "name", "edesc" };
		String[] news = { "title", "content" };
		String[] popularize = { "pName", "key", "content" };
		String[] supply = { "title", "skey" };
		Format format = new DecimalFormat("0.000");
		if (pageTypeHidden == null)
			pageTypeHidden = "product";
		if (pageType == null)
			pageType = pageTypeHidden;
		if (entName == null)
			entName = "";
		long start = System.currentTimeMillis(); // 获取最初时间
		if (pageType.equals("product")) {
			topSearch("AbcProduct.class");
			this.pageList = productService.findPaginationUserByParams(product,
					entName, AbcProduct.class, page, DEFAULT_PAGESIZE);
			list = pageList.getItems();
			for (int i = 0; i < list.size(); i++) {
				AbcProduct pro = (AbcProduct) list.get(i);
				if (!(pro.getState().equals(CommonConst.STATENORMAL))) {
					list.remove(i);
				}
				if (i < 8) {
					String proname = pro.getName();
					if (proname != null && proname.length() > 10)
						proname = proname.substring(0, 9);
					listSearch
							.add("<a href=\"javascript:\" onclick=\"javascript:$('#entName').val('"
									+ proname
									+ "');$('#form1').submit()\">"
									+ proname + "</a>");
				}

			}
			// 显示推广的产品
			List popularizeList = popularizeService.findUserByParamsByPage(
					popularize, entName, AbcPopularize.class, page,
					DEFAULT_PAGESIZE);
			for (int i = 0; i < popularizeList.size(); i++) {
				AbcPopularize pop = (AbcPopularize) popularizeList.get(i);
				if (!(pop.getState().equals(CommonConst.STATENORMAL))
						|| pop.getEndTime().before(new Date())) {
					popularizeList.remove(i);
				}
			}
			if (popularizeList.size() == 0)
				getRequest().setAttribute("isNull", "01");
			getRequest().setAttribute("popularizeList", popularizeList);
		} else if (pageType.equals("enterprise")) {
			topSearch("AbcEnterprise.class");
			pageList = enterpriseService.findPaginationUserByParams(enterprise,
					entName, AbcEnterprise.class, page, DEFAULT_PAGESIZE);
			int i = 0;
			for (AbcEnterprise ae : (List<AbcEnterprise>) pageList.getItems()) {
				String entname = ae.getName();
				if (entname != null && entname.length() > 10)
					entname = entname.substring(0, 9);
				if (i < 8)
					listSearch
							.add("<a  href=\"javascript:\" onclick=\"javascript:$('#entName').val('"
									+ entname
									+ "');$('#form1').submit()\">"
									+ entname + "</a>");
				else
					break;
				i++;
			}
		}

		else if (pageType.equals("news")) {
			topSearch("NewsNews.class");
			pageList = newsNewsService.findPaginationUserByParams(news,
					entName, NewsNews.class, page, DEFAULT_PAGESIZE);
			int i = 0;
			for (NewsNews ae : (List<NewsNews>) pageList.getItems()) {
				String newtitle = ae.getTitle();
				if (newtitle != null && newtitle.length() > 10)
					newtitle = newtitle.substring(0, 9);
				if (i < 8)
					listSearch
							.add("<a href=\"javascript:\" onclick=\"javascript:$('#entName').val('"
									+ newtitle
									+ "');$('#form1').submit()\">"
									+ newtitle + "</a>");
				else
					break;
				i++;
			}
		} else if (pageType.equals("supply")) {
			topSearch("AbcSupply.class");
			pageList = supplyService.findPaginationUserByParams(supply,
					entName, AbcSupply.class, page, DEFAULT_PAGESIZE);
			int i = 0;
			for (AbcSupply ae : (List<AbcSupply>) pageList.getItems()) {
				if (ae.getState().equals(CommonConst.STATENORMAL)) {
					if (i < 8)
						listSearch
								.add("<a href=\"javascript:\" onclick=\"javascript:$('#entName').val('"
										+ ae.getTitle()
										+ "');$('#form1').submit()\">"
										+ ae.getTitle() + "</a>");
					else
						break;
					i++;
				}
			}
		}
		long end = System.currentTimeMillis();
		List countList = new ArrayList();
		countList.add("<li class=\"r\"><a href=\"?page=1&&entName=" + entName
				+ "&&pageType=" + pageType + "\">首页</a></li>");
		int pc = pageList.getPageCount();
		if (pc < 10) {
			for (int i = 1; i <= pc; i++) {
				if (thispage == i)
					countList.add("<li class=\"b\">" + i + "</li>");
				else
					countList.add("<li class=\"t\"><a href=\"?page=" + i
							+ "&&entName=" + entName + "&&pageType=" + pageType
							+ "\">" + i + "</a></li>");
			}

		} else {
			if (thispage > 6 && thispage <= pc) {
				countList.add("<li class=\"b\">...</li>");
				for (int i = (thispage - 4); i <= (thispage + 4 > pc ? pc
						: thispage + 4); i++) {
					if (thispage == i)
						countList.add("<li class=\"b\">" + i + "</li>");
					else
						countList.add("<li class=\"t\"><a href=\"?page=" + i
								+ "&&entName=" + entName + "&&pageType="
								+ pageType + "\">" + i + "</a></li>");
				}
				if (thispage + 5 < pc)
					countList.add("<li class=\"b\">...</li>");
				if (thispage + 4 < pc)
					countList.add("<li class=\"t\"><a href=\"?page=" + pc
							+ "&&entName=" + entName + "&&pageType=" + pageType
							+ "\">" + pc + "</a></li>");
			} else {
				for (int i = 1; i <= pc; i++) {
					if (i < 9) {
						if (thispage == i)
							countList.add("<li class=\"b\">" + i + "</li>");
						else
							countList.add("<li class=\"t\"><a href=\"?page="
									+ i + "&&entName=" + entName
									+ "&&pageType=" + pageType + "\">" + i
									+ "</a></li>");
					}
				}
				countList.add("<li class=\"b\">...</li>");
				countList.add("<li class=\"t\"><a href=\"?page=" + pc
						+ "&&entName=" + entName + "&&pageType=" + pageType
						+ "\">" + pc + "</a></li>");
			}
		}
		countList.add("<li class=\"r\"><a href=\"?page="
				+ pageList.getPageCount() + "&&entName=" + entName
				+ "&&pageType=" + pageType + "\">尾页</a></li>");
		String runTime = format.format((double) (end - start) / 1000);
		getRequest().setAttribute("runTime", runTime);
		getRequest().setAttribute("searchName", entName);
		getRequest().setAttribute("countList", countList);
		getRequest().setAttribute("resultSize", pageList.getTotalCount());
		if (pageType.equals("product"))
			getRequest().setAttribute("pageType", "product");
		if (pageType.equals("enterprise"))
			getRequest().setAttribute("pageType", "enterprise");
		if (pageType.equals("news"))
			getRequest().setAttribute("pageType", "news");

		return LIST;
	}

	@SuppressWarnings("unused")
	public void topSearch(String className) {
		if (StringUtil.isNotBlank(entName)) {
			AbcTopSearch at = null;
			at = new AbcTopSearch();
			at.setDomain(domain);
			at.setSearchKey(entName);
			at.setModelClass(className);
			List<AbcTopSearch> alist = topSearchService.findByExample(at);
			if (alist.size() != 0) {
				AbcTopSearch ats = alist.get(0);
				ats.setViews(ats.getViews() == null ? "1" : String
						.valueOf(Integer.parseInt(ats.getViews()) + 1));
				ats.setLastTime(new Date());
				topSearchService.saveOrUpdate(ats);
			} else {
				at.setViews("1");
				at.setLastTime(new Date());
				topSearchService.save(at);
			}
		}
	}

	public String news() {
		return LIST;
	}

	public List getListSearch() {
		return listSearch;
	}

	public List getList() {
		return list;
	}

	public void setPopularizeService(PopularizeService popularizeService) {
		this.popularizeService = popularizeService;
	}

	public void setNewsNewsService(NewsNewsService newsNewsService) {
		this.newsNewsService = newsNewsService;
	}

	public void setSupplyService(SupplyService supplyService) {
		this.supplyService = supplyService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

}
